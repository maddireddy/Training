# Complete Deployment Guide: Student Management System

## 🎯 Goal
Deploy the complete Full Stack application to AWS:
- **Frontend**: React app on AWS S3 + CloudFront
- **Backend**: Spring Boot on AWS EC2
- **Database**: PostgreSQL on AWS RDS

## 🏗️ Architecture

```
                    Internet
                       ↓
                  CloudFront (CDN)
                       ↓
┌─────────────────────────────────────────────┐
│  S3 Bucket (React App)                      │
│  https://your-app.s3.amazonaws.com          │
└─────────────────────────────────────────────┘
                       ↓ (API calls)
┌─────────────────────────────────────────────┐
│  EC2 Instance (Spring Boot)                 │
│  http://ec2-xx-xx-xx-xx.compute.amazonaws.com:8080│
└─────────────────────────────────────────────┘
                       ↓
┌─────────────────────────────────────────────┐
│  RDS (PostgreSQL)                           │
│  database.cxxxx.region.rds.amazonaws.com    │
└─────────────────────────────────────────────┘
```

---

## Phase 1: Setup AWS Account & Security

### 1.1 Create AWS Account
1. Go to https://aws.amazon.com
2. Click "Create an AWS Account"
3. Enter email, password, account name
4. Provide payment method (won't be charged in free tier)
5. Verify phone number
6. Choose Free tier support plan

### 1.2 Setup Billing Alerts (CRITICAL!)
```bash
# Go to AWS Console → Billing → Budgets
# Create budget:
# - Budget amount: $5
# - Alert at: 50%, 75%, 90%, 100%
# - Email: your-email@example.com
```

### 1.3 Create IAM User (Best Practice)
```bash
# Don't use root account for daily operations!

1. AWS Console → IAM → Users → Add User
2. Username: developer
3. Access type: Programmatic + Console
4. Permissions: AdministratorAccess (for learning)
5. Download credentials CSV
6. Enable MFA (Multi-Factor Authentication)
```

---

## Phase 2: Deploy Database (RDS)

### 2.1 Create RDS PostgreSQL Instance

**Via AWS Console**:
```
1. AWS Console → RDS → Create Database
2. Engine: PostgreSQL
3. Template: Free tier
4. DB Instance identifier: studentdb
5. Master username: postgres
6. Master password: YourSecurePassword123!
7. DB instance class: db.t3.micro (free tier)
8. Storage: 20 GB (free tier)
9. Public access: Yes (for development)
10. Database name: studentdb
11. Create database
```

**Wait 5-10 minutes for database to be available**

### 2.2 Configure Security Group
```
1. RDS → studentdb → Security Groups
2. Inbound Rules → Edit
3. Add rule:
   - Type: PostgreSQL
   - Port: 5432
   - Source: 0.0.0.0/0 (for development; restrict in production)
4. Save
```

### 2.3 Test Connection
```bash
# Note down endpoint from RDS console
# Example: studentdb.c1234.us-east-1.rds.amazonaws.com

# Test locally
psql -h studentdb.c1234.us-east-1.rds.amazonaws.com \
     -U postgres \
     -d studentdb
```

**Endpoint**: Save this for Spring Boot configuration

---

## Phase 3: Deploy Backend (EC2)

### 3.1 Create EC2 Instance

```
1. AWS Console → EC2 → Launch Instance
2. Name: student-api-server
3. AMI: Amazon Linux 2023
4. Instance type: t2.micro (free tier)
5. Key pair: Create new → Download .pem file
6. Security group: Create new
   - Allow SSH (22) from your IP
   - Allow HTTP (80) from anywhere
   - Allow Custom TCP (8080) from anywhere
7. Launch instance
```

### 3.2 Connect to EC2
```bash
# Change key permissions
chmod 400 your-keypair.pem

# SSH into EC2
ssh -i your-keypair.pem ec2-user@<EC2-PUBLIC-IP>
```

### 3.3 Install Java on EC2
```bash
# Update system
sudo yum update -y

# Install Java 17
sudo yum install java-17-amazon-corretto-devel -y

# Verify
java -version
```

### 3.4 Configure Spring Boot for Production

**Update application.properties**:
```properties
# src/main/resources/application.properties

server.port=8080

# PostgreSQL Configuration
spring.datasource.url=jdbc:postgresql://studentdb.c1234.us-east-1.rds.amazonaws.com:5432/studentdb
spring.datasource.username=postgres
spring.datasource.password=YourSecurePassword123!
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

# Create tables on first run
spring.jpa.hibernate.ddl-auto=update

# Production settings
spring.jpa.show-sql=false
logging.level.root=INFO
```

**Update pom.xml** (add PostgreSQL dependency):
```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```

### 3.5 Build JAR File Locally
```bash
# On your local machine
cd Week-04-Spring-Boot/student-management-api

# Clean build
mvn clean package -DskipTests

# JAR file created at: target/student-management-api-1.0.0.jar
```

### 3.6 Upload to EC2
```bash
# From your local machine
scp -i your-keypair.pem \
    target/student-management-api-1.0.0.jar \
    ec2-user@<EC2-PUBLIC-IP>:~/app.jar
```

### 3.7 Run Application on EC2
```bash
# SSH into EC2
ssh -i your-keypair.pem ec2-user@<EC2-PUBLIC-IP>

# Run application
java -jar app.jar

# Or run in background
nohup java -jar app.jar > app.log 2>&1 &

# Check if running
curl http://localhost:8080/api/students
```

### 3.8 Setup as System Service (Production)

**Create systemd service**:
```bash
sudo nano /etc/systemd/system/studentapi.service
```

**Add content**:
```ini
[Unit]
Description=Student API Service
After=network.target

[Service]
Type=simple
User=ec2-user
WorkingDirectory=/home/ec2-user
ExecStart=/usr/bin/java -jar /home/ec2-user/app.jar
Restart=always
RestartSec=10

[Install]
WantedBy=multi-user.target
```

**Enable and start**:
```bash
sudo systemctl daemon-reload
sudo systemctl enable studentapi
sudo systemctl start studentapi
sudo systemctl status studentapi

# View logs
sudo journalctl -u studentapi -f
```

### 3.9 Test Backend
```bash
# From your local machine
curl http://<EC2-PUBLIC-IP>:8080/api/students
```

---

## Phase 4: Deploy Frontend (S3 + CloudFront)

### 4.1 Update React App

**Update API URL** in `src/services/api.js`:
```javascript
const API_BASE_URL = 'http://<EC2-PUBLIC-IP>:8080/api/students';
```

### 4.2 Build React App
```bash
cd Week-07-React-Basics/student-dashboard

# Install dependencies
npm install

# Build for production
npm run build

# Creates optimized build in build/ folder
```

### 4.3 Create S3 Bucket
```
1. AWS Console → S3 → Create Bucket
2. Bucket name: student-dashboard-app (must be globally unique)
3. Region: us-east-1
4. Uncheck "Block all public access"
5. Acknowledge public access warning
6. Create bucket
```

### 4.4 Enable Static Website Hosting
```
1. Bucket → Properties → Static website hosting
2. Enable
3. Index document: index.html
4. Error document: index.html (for React Router)
5. Save
```

### 4.5 Set Bucket Policy
```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Sid": "PublicReadGetObject",
      "Effect": "Allow",
      "Principal": "*",
      "Action": "s3:GetObject",
      "Resource": "arn:aws:s3:::student-dashboard-app/*"
    }
  ]
}
```

### 4.6 Upload Build Files
```bash
# Using AWS CLI
aws s3 sync build/ s3://student-dashboard-app

# Or upload manually via AWS Console
```

### 4.7 Access Your App
```
http://student-dashboard-app.s3-website-us-east-1.amazonaws.com
```

### 4.8 Setup CloudFront (Optional - for HTTPS & CDN)
```
1. AWS Console → CloudFront → Create Distribution
2. Origin domain: student-dashboard-app.s3.amazonaws.com
3. Default cache behavior: Redirect HTTP to HTTPS
4. Create distribution
5. Wait 10-15 minutes for deployment
6. Access via: https://d1234abcd.cloudfront.net
```

---

## Phase 5: Final Configuration

### 5.1 Update CORS in Spring Boot

**Add to StudentController**:
```java
@CrossOrigin(origins = {
    "http://student-dashboard-app.s3-website-us-east-1.amazonaws.com",
    "https://d1234abcd.cloudfront.net"
})
```

### 5.2 Rebuild and Redeploy Backend
```bash
# Build
mvn clean package -DskipTests

# Upload to EC2
scp -i your-keypair.pem target/student-management-api-1.0.0.jar \
    ec2-user@<EC2-PUBLIC-IP>:~/app.jar

# Restart service
ssh -i your-keypair.pem ec2-user@<EC2-PUBLIC-IP>
sudo systemctl restart studentapi
```

---

## 🎉 Deployment Complete!

### Your Application is Now Live:
- **Frontend**: http://student-dashboard-app.s3-website-us-east-1.amazonaws.com
- **Backend**: http://<EC2-PUBLIC-IP>:8080/api/students
- **Database**: RDS PostgreSQL

---

## 🔒 Security Improvements (Production)

### 1. Use Environment Variables
```bash
# On EC2
export DB_URL=jdbc:postgresql://...
export DB_USERNAME=postgres
export DB_PASSWORD=secret

# In application.properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

### 2. Use AWS Secrets Manager
```bash
# Store database credentials
aws secretsmanager create-secret \
    --name studentdb-credentials \
    --secret-string '{"username":"postgres","password":"secret"}'
```

### 3. Restrict Security Groups
```
RDS:
  - Allow 5432 only from EC2 security group

EC2:
  - Allow 22 only from your IP
  - Allow 8080 from anywhere (or CloudFront)
```

### 4. Use HTTPS
```
- Add SSL certificate to CloudFront
- Use AWS Certificate Manager (ACM)
- Free SSL certificates!
```

### 5. Use Load Balancer
```
- Create Application Load Balancer
- Distribute traffic to multiple EC2 instances
- Health checks
- Auto-scaling
```

---

## 💰 Cost Optimization

### Free Tier Usage:
```
EC2: 750 hours/month = 1 instance running 24/7 ✅
RDS: 750 hours/month = 1 database running 24/7 ✅
S3: 5GB storage, 20K GET requests ✅
CloudFront: 50GB data transfer ✅
```

### To Avoid Charges:
```
✅ Stop EC2 when not in use (don't terminate)
✅ Stop RDS instance overnight
✅ Monitor billing dashboard daily
✅ Setup budget alerts
✅ Delete unused resources
```

---

## 🐛 Troubleshooting

### Backend not accessible:
```
✅ Check EC2 security group (port 8080 open)
✅ Check if app is running: sudo systemctl status studentapi
✅ Check logs: sudo journalctl -u studentapi -f
✅ Check EC2 public IP hasn't changed
```

### Database connection failed:
```
✅ Check RDS security group (port 5432 open)
✅ Check RDS endpoint in application.properties
✅ Check username/password
✅ Check if RDS is running
```

### Frontend can't connect to backend:
```
✅ Check CORS configuration
✅ Check API base URL in api.js
✅ Check browser console for errors
✅ Verify backend is accessible: curl http://EC2-IP:8080/api/students
```

### S3 Access Denied:
```
✅ Check bucket policy (allow public access)
✅ Check "Block public access" is disabled
✅ Check file permissions
```

---

## 📋 Deployment Checklist

- [ ] AWS account created
- [ ] Billing alerts configured
- [ ] RDS database created and accessible
- [ ] EC2 instance launched
- [ ] Java installed on EC2
- [ ] Backend JAR uploaded
- [ ] Backend running as service
- [ ] Backend API tested
- [ ] React app built
- [ ] S3 bucket created
- [ ] Static hosting enabled
- [ ] Build files uploaded
- [ ] Frontend accessible
- [ ] CORS configured
- [ ] Full stack working end-to-end

---

## 🎓 What You've Learned

✅ Cloud infrastructure setup
✅ Database as a service (RDS)
✅ Virtual machine management (EC2)
✅ Object storage (S3)
✅ CDN (CloudFront)
✅ Security groups & networking
✅ Application deployment
✅ Production configuration
✅ Cost management

**This is EXACTLY how real-world applications are deployed!**

---

**Congratulations! You've deployed a production-ready Full Stack application to AWS!** 🎉
