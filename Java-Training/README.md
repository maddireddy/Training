# 🚀 Java Training Program

A comprehensive Java training curriculum designed to take you from Java fundamentals to advanced enterprise application development. This program combines theoretical knowledge with practical examples and hands-on exercises.

## 📚 Program Structure

The training is divided into three main modules, each building upon the previous one:

### 1. Core Java
- **Fundamentals**: Syntax, data types, control flow
- **OOP Concepts**: Classes, objects, inheritance, polymorphism
- **Core APIs**: Collections, I/O, Concurrency
- **Advanced Features**: Lambda, Streams, Optional

### 2. Java Advanced
- **Concurrency**: Threads, Executors, Concurrent Collections
- **Performance Tuning**: JVM, Memory Management, Profiling
- **Security**: Cryptography, Secure Coding
- **New Features**: Java 8-17 Features

### 3. Spring Boot
- **Spring Core**: DI, AOP, Configuration
- **Web Development**: REST APIs, MVC, Validation
- **Data Access**: JPA, Hibernate, Transactions
- **Microservices**: Cloud-Native Development

## 🛠️ Prerequisites

- **JDK 17** or later
- **Maven** 3.6.0 or later
- **Git**
- **Docker** (for containerized development)
- **IDE**: IntelliJ IDEA (recommended) or VS Code

## 🚀 Getting Started

1. **Clone the repository**:
   ```bash
   git clone https://github.com/maddireddy/Training.git
   cd Training/Java-Training
   ```

2. **Build the project**:
   ```bash
   mvn clean install
   ```

3. **Explore the modules**:
   ```bash
   # Core Java examples
   cd core-java
   mvn test
   
   # Run Spring Boot application
   cd ../spring-boot
   mvn spring-boot:run
   ```

## 📂 Project Structure

```
Java-Training/
├── core-java/         # Core Java concepts and examples
│   ├── src/
│   │   ├── main/java/com/javatraining/core/
│   │   │   ├── basics/       # Basic Java concepts
│   │   │   ├── oops/         # Object-Oriented Programming
│   │   │   ├── collections/  # Collections Framework
│   │   │   ├── exceptions/   # Exception handling
│   │   │   ├── io/          # Input/Output operations
│   │   │   ├── threads/     # Concurrency
│   │   │   └── advanced/    # Advanced Java features
│   │   └── resources/       # Configuration files
│   └── pom.xml
│
├── java-advanced/     # Advanced Java topics
│   ├── src/
│   │   ├── main/java/com/javatraining/advanced/
│   │   │   ├── concurrency/
│   │   │   ├── performance/
│   │   │   ├── security/
│   │   │   └── features/
│   │   └── resources/
│   └── pom.xml
│
├── spring-boot/       # Spring Boot applications
│   ├── src/
│   │   ├── main/java/com/javatraining/springboot/
│   │   │   ├── config/      # Configuration classes
│   │   │   ├── controller/  # REST Controllers
│   │   │   ├── model/       # Entity classes
│   │   │   ├── repository/  # Data repositories
│   │   │   ├── service/     # Business logic
│   │   │   └── dto/         # Data Transfer Objects
│   │   └── resources/       # Application properties
│   └── pom.xml
│
├── original-content/  # Original content from source repositories
├── docs/              # Additional documentation
├── pom.xml            # Parent POM
└── README.md          # This file
```

## 🎓 Learning Path

1. **Beginner**
   - Start with `core-java` module
   - Complete basic exercises
   - Understand OOP principles

2. **Intermediate**
   - Explore `java-advanced` module
   - Work on concurrency and performance
   - Practice with real-world examples

3. **Advanced**
   - Dive into `spring-boot`
   - Build RESTful APIs
   - Learn microservices architecture

## 🧪 Testing

Run tests using Maven:

```bash
# Run all tests in core-java
cd core-java
mvn test

# Run specific test class
mvn test -Dtest=ClassNameTest
```

## 🚀 Deployment

### Build and Run
```bash
# Build all modules
mvn clean install

# Run Spring Boot application
cd spring-boot
mvn spring-boot:run
```

### Docker
```bash
# Build Docker image
docker build -t java-training .

# Run container
docker run -p 8080:8080 java-training
```

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📜 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Oracle Java Documentation
- Spring Framework Team
- Baeldung Tutorials
- Java Community
- All Open Source Contributors

---

**Happy Coding!** 🚀
