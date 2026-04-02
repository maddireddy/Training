# Week 10: Docker & Kubernetes (Days 46-50)

## 🎯 Week Overview
Master containerization, orchestration, and CI/CD for production deployments.

## 📅 Daily Projects

### Day 46: Docker Basics
**Project**: Containerized Spring Boot App
```dockerfile
# Multi-stage Dockerfile
FROM maven:3.8-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

FROM openjdk:17-slim
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
```

### Day 47: Docker Compose
**Project**: Full Stack with Docker Compose
```yaml
version: '3.8'
services:
  backend:
    build: ./backend
    ports:
      - "8080:8080"
    environment:
      - SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/app
    depends_on:
      - db
  
  frontend:
    build: ./frontend
    ports:
      - "3000:80"
  
  db:
    image: mysql:8
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: app
    volumes:
      - db-data:/var/lib/mysql

volumes:
  db-data:
```

### Day 48: Kubernetes Basics
**Project**: K8s Deployment
```yaml
# deployment.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: app-deployment
spec:
  replicas: 3
  selector:
    matchLabels:
      app: myapp
  template:
    metadata:
      labels:
        app: myapp
    spec:
      containers:
      - name: app
        image: myapp:latest
        ports:
        - containerPort: 8080
---
apiVersion: v1
kind: Service
metadata:
  name: app-service
spec:
  type: LoadBalancer
  ports:
  - port: 80
    targetPort: 8080
  selector:
    app: myapp
```

### Day 49: CI/CD with GitHub Actions
**Project**: Automated Pipeline
```yaml
# .github/workflows/deploy.yml
name: CI/CD Pipeline
on:
  push:
    branches: [ main ]
jobs:
  build-and-deploy:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Build
        run: mvn clean package
      - name: Build Docker Image
        run: docker build -t myapp:${{ github.sha }} .
      - name: Push to Docker Hub
        run: docker push myapp:${{ github.sha }}
      - name: Deploy to AWS
        run: |
          # Deploy commands
```

### Day 50: Jenkins Pipeline
**Project**: Enterprise CI/CD
```groovy
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Docker Build') {
            steps {
                sh 'docker build -t myapp .'
            }
        }
        stage('Deploy') {
            steps {
                sh 'kubectl apply -f k8s/'
            }
        }
    }
}
```

## 🚀 Complete DevOps Pipeline

```
Developer Push
      ↓
GitHub Repository
      ↓
GitHub Actions / Jenkins
      ↓
Build & Test
      ↓
Docker Image
      ↓
Container Registry (Docker Hub)
      ↓
Kubernetes Cluster (AWS EKS)
      ↓
Production Deployment
```

## 🎓 Learning Outcomes
- Containerize applications
- Orchestrate with Docker Compose
- Deploy on Kubernetes
- Build CI/CD pipelines
- Automate deployments
- Monitor production systems

---
**Next**: Week 11-12 - Capstone Project
