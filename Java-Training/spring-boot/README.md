# Spring Boot Training Module

This module covers modern Java web development using Spring Boot, including REST APIs, data access, security, and cloud-native development.

## 🚀 Getting Started

### Prerequisites
- Java 17 or later
- Maven 3.6.0 or later
- Docker (for containerized development)
- Basic understanding of Spring Framework

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/maddireddy/Training.git
   cd Training/spring-boot
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. Open in your browser:
   - Application: http://localhost:8080
   - Actuator: http://localhost:8080/actuator
   - OpenAPI Docs: http://localhost:8080/swagger-ui.html

## 📚 Topics Covered

### 1. Spring Boot Basics
- Auto-configuration
- Starters and Dependencies
- Externalized Configuration
- Profiles
- Actuator

### 2. Web Development
- RESTful Web Services
- Exception Handling
- Validation
- OpenAPI/Swagger Documentation
- File Upload/Download

### 3. Data Access
- Spring Data JPA
- Database Migrations with Flyway
- QueryDSL
- Transaction Management
- Caching

### 4. Security
- Spring Security
- JWT Authentication
- OAuth2
- Method Level Security
- CSRF Protection

### 5. Testing
- Unit Testing
- Integration Testing
- Test Containers
- MockMVC
- Test Slices

### 6. Advanced Topics
- Asynchronous Processing
- Caching
- Scheduling
- WebSockets
- RSocket

### 7. Cloud Native
- Spring Cloud
- Config Server
- Service Discovery
- Circuit Breaker
- API Gateway

## 🛠️ Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/javatraining/springboot/
│   │       ├── config/           # Configuration classes
│   │       ├── controller/       # REST Controllers
│   │       ├── model/            # Entity classes
│   │       ├── repository/       # Data repositories
│   │       ├── service/          # Business logic
│   │       ├── dto/              # Data Transfer Objects
│   │       ├── exception/        # Exception handling
│   │       └── Application.java  # Main application class
│   └── resources/
│       ├── static/              # Static resources
│       ├── templates/           # Template files
│       ├── application.yml      # Application properties
│       └── db/                  # Database migrations
└── test/                        # Test files
```

## 🧪 Testing

Run all tests:
```bash
mvn test
```

Run a specific test:
```bash
mvn test -Dtest=YourTestClass
```

## 🚀 Deployment

### Build JAR
```bash
mvn clean package
java -jar target/spring-boot-1.0-SNAPSHOT.jar
```

### Docker Build
```bash
docker build -t java-training/spring-boot .
docker run -p 8080:8080 java-training/spring-boot
```

## 📚 Resources
- [Spring Boot Reference Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Guides](https://spring.io/guides)
- [Baeldung Spring Tutorials](https://www.baeldung.com/spring-tutorial)
- [Spring Initializr](https://start.spring.io/)

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📜 License

This project is licensed under the MIT License - see the [LICENSE](../LICENSE) file for details.
