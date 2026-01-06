# Week 05: Advanced Spring Boot (Days 21-25)

## 🎯 Week Overview
Master advanced Spring Boot features including security, authentication, testing, logging, and caching for production-ready applications.

## 📅 Daily Projects

### Day 21: Spring Security Basics
**Project**: Secured REST API
```java
Features:
- In-memory authentication
- Role-based access control (ADMIN, USER)
- Password encoding (BCrypt)
- Method-level security
```

**Endpoints**:
```
POST /api/auth/login       - Login
GET  /api/public/*        - Public access
GET  /api/user/*          - USER role required
GET  /api/admin/*         - ADMIN role required
```

---

### Day 22: JWT Authentication
**Project**: Stateless Authentication System
```java
Features:
- JWT token generation
- Token validation
- Refresh token mechanism
- Logout functionality
- Token blacklisting
```

**Flow**:
```
1. POST /auth/login       → Returns JWT token
2. Use token in Header    → Authorization: Bearer <token>
3. POST /auth/refresh     → Get new token
4. POST /auth/logout      → Invalidate token
```

---

### Day 23: Testing Spring Boot
**Project**: Comprehensive Test Suite
```java
Test Types:
- Unit tests (Service layer with Mockito)
- Integration tests (Full stack with @SpringBootTest)
- API tests (MockMVC)
- Repository tests (@DataJpaTest)
```

**Example Tests**:
```java
@Test
void testCreateStudent() {
    // Given
    StudentRequest request = new StudentRequest("John", "john@example.com");

    // When
    StudentDTO result = studentService.createStudent(request);

    // Then
    assertThat(result.getName()).isEqualTo("John");
}
```

---

### Day 24: Logging & Monitoring
**Project**: Production Logging System
```
Features:
- Structured logging (Logback)
- Different log levels (INFO, DEBUG, ERROR)
- Log rotation and archival
- Spring Boot Actuator
- Health checks
- Metrics endpoints
- Custom health indicators
```

**Actuator Endpoints**:
```
GET /actuator/health       - Application health
GET /actuator/metrics      - Application metrics
GET /actuator/info         - Application info
GET /actuator/env          - Environment properties
GET /actuator/loggers      - Logger configuration
```

---

### Day 25: Caching & Performance
**Project**: High-Performance API with Redis
```java
Features:
- Spring Cache abstraction
- Redis as cache provider
- Method-level caching (@Cacheable)
- Cache eviction strategies
- Cache statistics
- Performance optimization
```

**Caching Examples**:
```java
@Cacheable(value = "students", key = "#id")
public Student findById(Long id) {
    return repository.findById(id);
}

@CacheEvict(value = "students", key = "#id")
public void deleteStudent(Long id) {
    repository.deleteById(id);
}

@CachePut(value = "students", key = "#result.id")
public Student updateStudent(Student student) {
    return repository.save(student);
}
```

---

## 🚀 Week Project: Complete E-Commerce Backend

### Tech Stack
```
- Spring Boot 3.x
- Spring Security + JWT
- Spring Data JPA
- Redis (Caching)
- MySQL/PostgreSQL
- Spring Boot Actuator
- JUnit 5 + Mockito
- Swagger/OpenAPI
```

### Features
```
✅ User authentication (JWT)
✅ Role-based authorization
✅ Product CRUD with caching
✅ Order management
✅ Payment processing (simulation)
✅ Comprehensive tests (80%+ coverage)
✅ Health checks & monitoring
✅ Performance optimization
```

### Project Structure
```
ecommerce-backend/
├── src/main/java/com/training/ecommerce/
│   ├── config/
│   │   ├── SecurityConfig.java
│   │   ├── CacheConfig.java
│   │   └── SwaggerConfig.java
│   ├── security/
│   │   ├── JwtTokenProvider.java
│   │   ├── JwtAuthenticationFilter.java
│   │   └── CustomUserDetailsService.java
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── model/
│   ├── dto/
│   └── exception/
├── src/test/java/
│   ├── unit/
│   ├── integration/
│   └── api/
└── docker-compose.yml (MySQL + Redis)
```

## 🧪 Testing Strategy

### Test Pyramid
```
           /\
          /  \     Unit Tests (70%)
         /____\
        /      \   Integration Tests (20%)
       /________\
      /          \ API/E2E Tests (10%)
     /____________\
```

### Example Test Suite
```bash
# Run all tests
mvn test

# Run with coverage
mvn test jacoco:report

# Integration tests only
mvn test -Dtest=*IntegrationTest

# Skip tests (for quick builds)
mvn clean install -DskipTests
```

## 📊 Performance Metrics

### Without Caching
```
GET /api/products → 250ms
GET /api/products/1 → 100ms
```

### With Redis Caching
```
GET /api/products → 5ms (cached)
GET /api/products/1 → 2ms (cached)
```

**Performance Improvement**: 50x faster! 🚀

## 🔒 Security Best Practices

```java
✅ Use HTTPS in production
✅ Encode passwords with BCrypt
✅ Validate all input data
✅ Use parameterized queries (JPA handles this)
✅ Implement rate limiting
✅ Add CORS configuration
✅ Enable CSRF protection
✅ Use secure JWT secret keys
✅ Implement token expiration
✅ Log security events
```

## 📈 Monitoring Dashboard

```
Health Status:    ✅ UP
Database:         ✅ Connected
Redis Cache:      ✅ Available
Memory Usage:     245MB / 512MB
Active Sessions:  15
Request Rate:     120 req/min
Error Rate:       0.1%
Avg Response:     45ms
```

## 🎓 Learning Outcomes
- Implement JWT authentication
- Secure REST APIs
- Write comprehensive tests
- Monitor production applications
- Optimize performance with caching
- Follow security best practices
- Deploy production-ready apps

## 🏆 Industry Standards
All practices taught in this week are used by:
- Netflix, Amazon, Google
- Startups to enterprises
- Fintech companies
- SaaS platforms

---
**Next**: Week 06 - Microservices Architecture
