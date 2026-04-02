# Week 06: Microservices Architecture (Days 26-30)

## 🎯 Week Overview
Build production-ready microservices with service discovery, API gateway, circuit breakers, and message queues.

## 📅 Daily Projects

### Day 26: Microservices Introduction
**Project**: Product & Order Microservices
- Service decomposition
- Inter-service communication (RestTemplate/WebClient)
- Separate databases per service

### Day 27: Service Discovery & API Gateway
**Project**: E-Commerce Microservices Ecosystem
- Eureka Server (Service Registry)
- API Gateway (Spring Cloud Gateway)
- Client-side load balancing

### Day 28: Circuit Breaker & Resilience
**Project**: Fault-Tolerant Microservices
- Resilience4j Circuit Breaker
- Retry and fallback mechanisms
- Bulkhead pattern

### Day 29: Distributed Config & Tracing
**Project**: Centralized Configuration
- Spring Cloud Config Server
- Sleuth for distributed tracing
- Zipkin for trace visualization

### Day 30: Message Queues (RabbitMQ)
**Project**: Event-Driven Microservices
- Async messaging with RabbitMQ
- Pub-Sub pattern
- Order processing workflow

## 🏗️ Microservices Architecture

```
                    ┌─────────────────┐
                    │   API Gateway   │
                    │   Port: 8080    │
                    └────────┬────────┘
                             │
              ┌──────────────┼──────────────┐
              │              │              │
    ┌─────────▼────────┐ ┌──▼──────────┐ ┌▼─────────────┐
    │ Product Service  │ │Order Service│ │User Service  │
    │   Port: 8081     │ │ Port: 8082  │ │ Port: 8083   │
    └─────────┬────────┘ └──┬──────────┘ └┬─────────────┘
              │              │              │
    ┌─────────▼────────┐ ┌──▼──────────┐ ┌▼─────────────┐
    │  Product DB      │ │  Order DB   │ │  User DB     │
    └──────────────────┘ └─────────────┘ └──────────────┘

              ┌──────────────────────────┐
              │  Eureka Discovery Server │
              │       Port: 8761         │
              └──────────────────────────┘

              ┌──────────────────────────┐
              │   Config Server          │
              │       Port: 8888         │
              └──────────────────────────┘
```

## 🚀 Complete Project: E-Commerce Microservices

### Services
1. **API Gateway** (Port 8080) - Single entry point
2. **Eureka Server** (Port 8761) - Service registry
3. **Config Server** (Port 8888) - Centralized config
4. **Product Service** (Port 8081) - Product management
5. **Order Service** (Port 8082) - Order processing
6. **User Service** (Port 8083) - User management
7. **Notification Service** (Port 8084) - Email/SMS

### Tech Stack per Service
```
Spring Boot 3.x
Spring Cloud Netflix (Eureka)
Spring Cloud Gateway
Spring Cloud Config
Resilience4j
RabbitMQ
MySQL (per service)
Docker & Docker Compose
```

## 🎓 Learning Outcomes
- Design microservices architecture
- Implement service discovery
- Build API Gateway
- Add fault tolerance
- Use message queues
- Deploy with Docker

---
**Next**: Week 07 - React Basics
