# Free Tools & Online Practice Labs Setup Guide

## 🎥 Video Conferencing Solutions (FREE)

### 1. **Google Meet** (Recommended for Teaching)
- **Free Tier**: 60-minute sessions, unlimited meetings
- **Features**: Screen sharing, recording, chat, up to 100 participants
- **Setup**:
  1. Create free Gmail account
  2. Go to meet.google.com
  3. Click "New Meeting" → Share link with students
- **Best For**: Regular classes, small batches (<100 students)
- **URL**: https://meet.google.com

### 2. **Jitsi Meet** (Unlimited Time!)
- **Free Tier**: UNLIMITED time, no account needed
- **Features**: Screen sharing, recording, chat, no participant limit
- **Setup**:
  1. Go to meet.jit.si
  2. Create a room name
  3. Share link with students
- **Best For**: Long coding sessions, no time restrictions
- **URL**: https://meet.jit.si

### 3. **Discord** (Best for Community Building)
- **Free Tier**: Unlimited voice/video, screen sharing, text channels
- **Features**:
  - Separate channels for each week/topic
  - Code sharing with syntax highlighting
  - Voice channels for doubt sessions
  - Stream to up to 50 people
- **Setup**:
  1. Create Discord account
  2. Create server for your training
  3. Create channels: #general, #doubt-session, #week-1, etc.
  4. Invite students via link
- **Best For**: Community building, async communication, code sharing
- **URL**: https://discord.com

### 4. **Microsoft Teams (Free)**
- **Free Tier**: 60-minute meetings, up to 100 participants
- **Features**: Screen sharing, file sharing, chat, recording
- **Setup**:
  1. Create Microsoft account
  2. Download Teams or use web version
  3. Schedule meetings
- **URL**: https://teams.microsoft.com

### 5. **StreamYard** (Professional Broadcasting)
- **Free Tier**: Unlimited streaming, up to 20 participants
- **Features**:
  - Stream to YouTube/Facebook simultaneously
  - Record sessions
  - Professional overlays
- **Best For**: Recording sessions for future reference
- **URL**: https://streamyard.com

---

## 💻 Online Practice Labs (FREE)

### 1. **GitHub Codespaces** (Recommended)
**FREE: 120 core hours/month (60 hours of 2-core machine)**

**Features**:
- Full VS Code in browser
- Pre-configured dev environments
- GitHub integration
- Can run Java, Node.js, databases
- Students can code from any device

**Setup for Students**:
```yaml
# .devcontainer/devcontainer.json
{
  "name": "Java Full Stack",
  "image": "mcr.microsoft.com/devcontainers/java:17",
  "features": {
    "ghcr.io/devcontainers/features/java:1": {
      "version": "17",
      "installMaven": true
    },
    "ghcr.io/devcontainers/features/node:1": {
      "version": "18"
    },
    "ghcr.io/devcontainers/features/docker-in-docker:1": {}
  },
  "customizations": {
    "vscode": {
      "extensions": [
        "vscjava.vscode-java-pack",
        "dbaeumer.vscode-eslint",
        "esbenp.prettier-vscode"
      ]
    }
  },
  "postCreateCommand": "java -version && node -v"
}
```

**How Students Use It**:
1. Fork your repository
2. Click "Code" → "Codespaces" → "Create codespace"
3. Start coding in browser
4. All changes auto-saved to their fork

**Billing**:
- 60 hours/month FREE for 2-core
- Perfect for 2-3 hour daily practice

---

### 2. **Gitpod**
**FREE: 50 hours/month**

**Features**:
- Similar to Codespaces
- VS Code or JetBrains IDEs
- Pre-configured workspaces
- One-click setup

**Setup**:
```yaml
# .gitpod.yml
image:
  file: .gitpod.Dockerfile

tasks:
  - name: Setup Java
    init: |
      sdk install java 17.0.7-oracle
      sdk install maven
      mvn --version
  - name: Setup Node
    init: |
      nvm install 18
      npm install -g create-react-app

vscode:
  extensions:
    - vscjava.vscode-java-pack
    - dsznajder.es7-react-js-snippets
```

**How Students Use It**:
1. Prefix any GitHub repo URL with `gitpod.io/#/`
2. Example: `gitpod.io/#/https://github.com/yourusername/Training`
3. Workspace launches automatically

**URL**: https://gitpod.io

---

### 3. **AWS Free Tier** (12 Months + Always Free)
**FREE: 750 hours/month EC2, RDS, and more**

**12-Month Free Tier**:
- EC2 t2.micro instance (750 hours/month)
- RDS db.t2.micro (750 hours/month)
- 5GB S3 storage
- 1GB data transfer

**Always Free**:
- Lambda: 1M requests/month
- DynamoDB: 25GB storage
- API Gateway: 1M calls/month
- CloudWatch: 10 metrics

**Setup for Students**:
1. Create AWS account (requires credit card, but won't charge if staying in free tier)
2. Setup billing alerts at $1, $5, $10
3. Use IAM users, not root account
4. Enable MFA

**Best Practices**:
- Set up billing alarms IMMEDIATELY
- Stop instances when not in use
- Use t2.micro only (free tier eligible)
- Delete resources after practice
- Review costs daily

**Lab Ideas**:
- Deploy Spring Boot on EC2
- Host React app on S3
- Database on RDS
- Serverless APIs with Lambda

**URL**: https://aws.amazon.com/free

---

### 4. **Oracle Cloud Free Tier** (Always Free!)
**FREE: FOREVER, no time limit**

**Always Free Resources**:
- 2 AMD Compute VMs (1/8 OCPU, 1GB RAM each)
- 4 ARM Ampere A1 cores (24GB RAM total)
- 200GB block storage
- 10GB object storage
- 2 databases (20GB each)

**Why Oracle Cloud**:
- NO TIME LIMIT (unlike AWS 12 months)
- No credit card charges
- More generous than AWS free tier
- ARM instances are powerful

**Setup**:
1. Create Oracle Cloud account
2. Get $300 credits for 30 days (optional)
3. Use "Always Free" resources
4. Deploy apps permanently for FREE

**Best For**:
- Long-term deployments
- Students can keep projects running
- Portfolio projects
- Learning cloud without costs

**URL**: https://cloud.oracle.com/free

---

### 5. **Replit**
**FREE: Unlimited public repls**

**Features**:
- Code in browser
- Supports Java, JavaScript, Python, etc.
- Instant preview
- Collaborative coding
- No setup required

**Best For**:
- Quick coding practice
- Live coding demos
- Beginner-friendly

**Limitations**:
- Limited resources
- Public repls only (free tier)
- Not suitable for large projects

**URL**: https://replit.com

---

### 6. **CodeSandbox**
**FREE: Unlimited public sandboxes**

**Features**:
- React/Node.js projects
- Instant preview
- VS Code experience
- Live collaboration

**Best For**:
- React frontend practice
- Quick prototypes
- Live coding sessions

**URL**: https://codesandbox.io

---

### 7. **Railway**
**FREE: $5 credit/month**

**Features**:
- Deploy Spring Boot apps
- PostgreSQL/MySQL databases
- Automatic deployments
- GitHub integration

**What $5 Gets You**:
- Small backend service
- Database
- Perfect for student projects

**URL**: https://railway.app

---

## 🗄️ Free Databases for Practice

### 1. **MySQL/PostgreSQL**
- **Locally**: Free, unlimited
- **Docker**: Run in containers
- **Cloud Options**:
  - AWS RDS Free Tier (750 hours/month)
  - Oracle Cloud (2 free databases forever)
  - Railway ($5/month credit)
  - ElephantSQL (PostgreSQL, 20MB free)

### 2. **MongoDB Atlas**
**FREE: 512MB storage**
- Shared clusters
- Perfect for learning
- Always free

**URL**: https://mongodb.com/cloud/atlas

### 3. **Firebase/Firestore**
**FREE: 1GB storage, 50K reads/day**
- Real-time database
- NoSQL
- Authentication included

**URL**: https://firebase.google.com

---

## 🛠️ Development Tools (FREE)

### 1. **IntelliJ IDEA Community Edition**
- FREE forever
- Java development
- Spring Boot support
- Git integration
- **Download**: https://jetbrains.com/idea/download

### 2. **Visual Studio Code**
- FREE forever
- Extensions for Java, React
- Git integration
- Live Share for collaboration
- **Download**: https://code.visualstudio.com

### 3. **Git & GitHub**
- FREE for public repositories
- **GitHub Pro FREE for students** (via GitHub Education)
  - Private repositories
  - GitHub Codespaces hours boost
  - Free domain via Namecheap
- **Apply**: https://education.github.com/pack

### 4. **Postman**
- FREE tier
- API testing
- Collections
- Team collaboration
- **URL**: https://postman.com

### 5. **DBeaver** (Database Client)
- FREE
- Supports all databases
- SQL editor
- ER diagrams
- **Download**: https://dbeaver.io

---

## 📚 Collaboration & Project Management (FREE)

### 1. **GitHub Projects**
- FREE
- Kanban boards
- Issue tracking
- Integrated with repos

### 2. **Trello**
- FREE tier
- Unlimited boards
- Power-Ups
- **URL**: https://trello.com

### 3. **Notion**
- FREE for personal use
- Documentation
- Notes
- Databases
- **URL**: https://notion.so

### 4. **Slack**
- FREE tier
- 10K message history
- 10 integrations
- Voice/video calls
- **URL**: https://slack.com

---

## 🎓 Recommended Setup for Your Training

### For You (Instructor):

**Communication**:
1. **Primary**: Discord (create server with channels)
2. **Live Classes**: Jitsi Meet (unlimited time)
3. **Recording**: StreamYard → YouTube (for future students)
4. **Announcements**: Slack/Discord

**Code Repository**:
1. **GitHub Organization** (free)
   - Create org for your training
   - Separate repos for each week
   - Students fork repos
2. **GitHub Discussions** (Q&A forum)

**Practice Labs**:
1. **Setup Codespaces** for each week's repo
2. **Provide devcontainer.json** configurations
3. Students get 60 hours/month FREE

### For Students:

**Required Accounts** (All FREE):
1. GitHub account
2. AWS account (with billing alerts)
3. Discord account
4. Gmail (for Google Meet backup)

**Optional** (Recommended):
1. Oracle Cloud account (always free resources)
2. MongoDB Atlas account
3. Railway account

**Local Setup** (If they have laptop):
1. IntelliJ IDEA Community
2. VS Code
3. JDK 17 (or 21)
4. Node.js 18+
5. Git
6. Docker Desktop (if possible)

**Zero-Setup Option** (For students without laptops):
1. Use GitHub Codespaces exclusively
2. Code from any device with browser
3. Mobile phones can work for reading code/docs

---

## 💰 Cost Management for Students

### AWS Free Tier Alerts:
```bash
# Setup billing alerts immediately
1. AWS Console → Billing → Budgets
2. Create budget: $5/month
3. Alert at 50%, 75%, 90%, 100%
4. Email notifications
```

### Best Practices:
1. **Stop resources when not in use**
   - Stop EC2 instances (don't terminate, just stop)
   - Pause RDS databases
   - Delete unused S3 objects

2. **Use tags for tracking**
   - Tag all resources: "training", "week-9", etc.
   - Easy to find and delete

3. **Set auto-shutdown**
   - EC2 instance scheduler
   - Lambda function to stop instances at night

4. **Use Oracle Cloud for permanent deployments**
   - Always free tier
   - No time limit
   - Keep portfolio projects running

---

## 📊 Recording & Sharing Sessions

### Option 1: StreamYard + YouTube
1. Create YouTube channel
2. Use StreamYard to stream
3. Videos saved on YouTube
4. Students can rewatch
5. **Cost**: FREE

### Option 2: OBS Studio + Local Recording
1. Download OBS (free)
2. Record screen + audio
3. Upload to YouTube/Google Drive
4. **Cost**: FREE

### Option 3: Discord Screen Share
1. Discord allows recording via bots
2. Or students can record using OBS
3. Share recordings in Discord
4. **Cost**: FREE

---

## 🎯 Sample Setup Timeline for Students

### Day 0 (Before Course Starts):
- [ ] Create GitHub account
- [ ] Install Git locally
- [ ] Create Discord account
- [ ] Join course Discord server
- [ ] Create AWS account + setup billing alerts
- [ ] Install IntelliJ IDEA Community Edition
- [ ] Install VS Code

### Day 1 (First Class):
- [ ] Fork Week-1 repository
- [ ] Create first Codespace
- [ ] Hello World in Codespace
- [ ] Commit & push changes
- [ ] Join first Jitsi call

### Week 4:
- [ ] Setup MongoDB Atlas account
- [ ] Connect Spring Boot to MongoDB

### Week 9:
- [ ] Create Oracle Cloud account
- [ ] Deploy first app to cloud
- [ ] Create S3 bucket for file uploads

---

## 🚀 Quick Start Commands for Students

### GitHub Codespaces:
```bash
# Automatically starts with all tools installed
# Just start coding!

# To run Spring Boot:
./mvnw spring-boot:run

# To run React:
cd frontend
npm start
```

### Local Setup:
```bash
# Verify installations:
java -version
node -v
npm -v
git --version
mvn -version

# Clone course repo:
git clone https://github.com/yourusername/Training.git
cd Training/Week-01-Java-Fundamentals

# Open in IntelliJ:
idea .

# Or VS Code:
code .
```

---

## 📞 Support Channels (All FREE)

1. **Doubt Sessions**: Daily on Discord voice channel
2. **Async Q&A**: GitHub Discussions
3. **Code Reviews**: GitHub Pull Requests
4. **Community Help**: Discord channels
5. **1-on-1**: Schedule Google Meet sessions

---

## 💡 Pro Tips

1. **Use GitHub Education Pack**:
   - Free GitHub Pro
   - Free domain
   - Increased Codespaces hours
   - Many other free tools

2. **Record Everything**:
   - Build a library of sessions
   - Students can rewatch
   - Help future batches

3. **Create Templates**:
   - Template repos for each week
   - Students just fork and code
   - Saves setup time

4. **Automate Grading**:
   - GitHub Actions for auto-tests
   - Automatic feedback on PRs
   - Saves your time

5. **Build Community**:
   - Discord for peer learning
   - Study groups
   - Alumni network

---

## 📋 Monthly Cost Breakdown (All FREE!)

| Service | Cost | Usage |
|---------|------|-------|
| Jitsi Meet | $0 | Unlimited classes |
| Discord | $0 | Community & communication |
| GitHub | $0 | Code repositories |
| Codespaces | $0 | 60 hours/student/month |
| AWS Free Tier | $0 | 12 months practice |
| Oracle Cloud | $0 | FOREVER free |
| YouTube | $0 | Video hosting |
| MongoDB Atlas | $0 | Database practice |
| IntelliJ CE | $0 | IDE |
| VS Code | $0 | IDE |
| **TOTAL** | **$0** | **Everything FREE!** |

---

## ⚠️ Important Warnings

1. **AWS Billing**:
   - ALWAYS setup billing alerts
   - Stop resources when not in use
   - Review costs daily for first week
   - Communicate limits to students

2. **GitHub Codespaces**:
   - 60 hours = 2 hours/day for 30 days
   - Teach students to stop codespaces when done
   - Show how to check usage

3. **Free Tier Limits**:
   - Educate students about limits
   - Teach responsible cloud usage
   - Monitor usage together

---

## 🎉 You Can Run This ENTIRE Training Without Spending a Penny!

With these tools, you can:
- ✅ Conduct live classes (unlimited time)
- ✅ Provide practice environments (cloud-based)
- ✅ Record sessions for future
- ✅ Build a community
- ✅ Deploy real applications
- ✅ Give hands-on cloud experience
- ✅ All completely FREE!

**No excuses to not start teaching!** 🚀

---

*Remember: The best investment is your time and expertise. All these tools are free - focus on delivering great content!*
