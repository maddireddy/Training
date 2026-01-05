# Week 9-10: AWS Cloud & DevOps

## 🎯 Learning Objectives
- Deploy applications to AWS Cloud
- Containerize apps with Docker
- Implement CI/CD pipelines
- Understand DevOps practices
- Monitor and scale applications

## 📚 Topics Covered

### Week 9: AWS Fundamentals
- **Day 41**: EC2 - Deploy Spring Boot app
- **Day 42**: S3 & CloudFront - Host React app
- **Day 43**: RDS - Production database
- **Day 44**: Lambda - Serverless functions
- **Day 45**: Load Balancer & Auto-scaling

### Week 10: DevOps
- **Day 46**: Docker basics
- **Day 47**: Docker Compose
- **Day 48**: Kubernetes basics
- **Day 49**: GitHub Actions CI/CD
- **Day 50**: Monitoring & Logging

## 🛠️ Prerequisites
- AWS Account (Free Tier)
- Docker installed
- kubectl installed
- GitHub account

## 📁 Directory Structure
```
Week-09-AWS-DevOps/
├── deployment-guides/
│   ├── aws-ec2-deployment.md
│   ├── aws-s3-react-hosting.md
│   ├── aws-rds-setup.md
│   └── aws-lambda-guide.md
├── docker-examples/
│   ├── Dockerfile.backend
│   ├── Dockerfile.frontend
│   └── docker-compose.yml
├── kubernetes-examples/
│   ├── deployment.yaml
│   ├── service.yaml
│   └── ingress.yaml
└── ci-cd-examples/
    └── .github/workflows/deploy.yml
```

## 🚀 Quick Start Guide

### 1. Deploy Spring Boot to AWS EC2

**Steps**:
1. Create EC2 instance (t2.micro - free tier)
2. Install Java on EC2
3. Upload JAR file
4. Run application
5. Configure security groups

**Commands**:
```bash
# SSH into EC2
ssh -i keypair.pem ec2-user@<public-ip>

# Install Java
sudo yum install java-17-amazon-corretto

# Upload JAR
scp -i keypair.pem target/app.jar ec2-user@<public-ip>:~/

# Run application
java -jar app.jar
```

### 2. Host React App on S3

**Steps**:
1. Build React app
2. Create S3 bucket
3. Enable static website hosting
4. Upload build files
5. Configure CloudFront (optional)

**Commands**:
```bash
# Build React app
npm run build

# Upload to S3
aws s3 sync build/ s3://your-bucket-name

# Make public
aws s3 website s3://your-bucket-name --index-document index.html
```

### 3. Containerize with Docker

**Dockerfile for Spring Boot**:
```dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

**Dockerfile for React**:
```dockerfile
FROM node:18-alpine as build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:alpine
COPY --from=build /app/build /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
```

**Docker Compose**:
```yaml
version: '3.8'
services:
  backend:
    build: ./backend
    ports:
      - "8080:8080"
    environment:
      - SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/studentdb
    depends_on:
      - db

  frontend:
    build: ./frontend
    ports:
      - "3000:80"
    depends_on:
      - backend

  db:
    image: mysql:8.0
    environment:
      - MYSQL_ROOT_PASSWORD=rootpass
      - MYSQL_DATABASE=studentdb
    volumes:
      - db-data:/var/lib/mysql

volumes:
  db-data:
```

### 4. GitHub Actions CI/CD

```yaml
name: Deploy to AWS

on:
  push:
    branches: [ main ]

jobs:
  build-and-deploy:
    runs-on: ubuntu-latest

    steps:
    - uses: actions/checkout@v3

    - name: Set up JDK 17
      uses: actions/setup-java@v3
      with:
        java-version: '17'
        distribution: 'corretto'

    - name: Build with Maven
      run: mvn clean package

    - name: Deploy to EC2
      env:
        PRIVATE_KEY: ${{ secrets.EC2_SSH_KEY }}
        HOST: ${{ secrets.EC2_HOST }}
      run: |
        echo "$PRIVATE_KEY" > private_key.pem
        chmod 600 private_key.pem
        scp -i private_key.pem target/*.jar ec2-user@$HOST:~/
        ssh -i private_key.pem ec2-user@$HOST 'sudo systemctl restart myapp'
```

## 🎓 Real-World Deployment Architecture

### Development → Staging → Production

```
Developer
    ↓ (git push)
GitHub
    ↓ (trigger)
GitHub Actions
    ↓ (build & test)
Docker Image
    ↓ (push)
AWS ECR
    ↓ (deploy)
ECS / EKS
    ↓ (serve)
Users
```

## 💰 AWS Free Tier Resources

### What's Free:
- **EC2**: 750 hours/month of t2.micro (1 year)
- **S3**: 5GB storage, 20,000 GET, 2,000 PUT
- **RDS**: 750 hours/month of db.t2.micro (1 year)
- **Lambda**: 1M requests/month (always free)
- **CloudFront**: 50GB data transfer
- **Load Balancer**: 750 hours (1 year)

### Important:
⚠️ **Set billing alerts immediately!**
⚠️ **Stop resources when not in use**
⚠️ **Use tags to track costs**

## 📝 Hands-On Exercises

### Exercise 1: Deploy Full Stack App to EC2
- Backend on port 8080
- Frontend on port 80 (nginx)
- MySQL database

### Exercise 2: Containerize Entire App
- Create Docker images
- Use docker-compose for local dev
- Push images to Docker Hub

### Exercise 3: Setup CI/CD Pipeline
- Auto-build on push
- Run tests
- Deploy to staging
- Manual approval for production

### Exercise 4: Implement Blue-Green Deployment
- Zero-downtime deployment
- Rollback capability

### Exercise 5: Setup Monitoring
- CloudWatch for AWS
- Application logs
- Performance metrics

## 🔧 DevOps Best Practices

### 1. Infrastructure as Code
```hcl
# Terraform example
resource "aws_instance" "app_server" {
  ami           = "ami-0c55b159cbfafe1f0"
  instance_type = "t2.micro"
  tags = {
    Name = "student-api-server"
  }
}
```

### 2. Environment Variables
Never hardcode:
- Database passwords
- API keys
- Service URLs

Use:
- AWS Secrets Manager
- Environment variables
- .env files (not in git!)

### 3. Health Checks
```java
@RestController
public class HealthController {
    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
```

### 4. Logging
```java
private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

logger.info("Creating student: {}", student.getName());
logger.error("Error creating student", exception);
```

## 🐛 Common Issues & Solutions

### 1. Security Group Not Allowing Traffic
**Solution**: Add inbound rules for ports 80, 8080, 22

### 2. Connection Refused from Frontend to Backend
**Solution**: Update API base URL, check CORS settings

### 3. Docker Image Too Large
**Solution**: Use multi-stage builds, Alpine base images

### 4. Out of Memory on EC2
**Solution**: Use t2.small or optimize JVM settings

## 🎯 Week 9-10 Checklist

- [ ] Created AWS account
- [ ] Setup billing alerts
- [ ] Deployed backend to EC2
- [ ] Hosted React app on S3
- [ ] Setup RDS database
- [ ] Created Docker images
- [ ] Wrote docker-compose file
- [ ] Setup CI/CD pipeline
- [ ] Implemented monitoring
- [ ] Deployed full-stack app to production

## 📚 Resources

- [AWS Free Tier](https://aws.amazon.com/free/)
- [Docker Documentation](https://docs.docker.com/)
- [Kubernetes Tutorials](https://kubernetes.io/docs/tutorials/)
- [GitHub Actions](https://docs.github.com/en/actions)

---

**This is industry-standard deployment!** Used by companies like Netflix, Amazon, Uber.
