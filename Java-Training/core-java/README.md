# Core Java Training Module

This module contains a comprehensive collection of Java examples and exercises, carefully organized to help you master Java programming from basics to advanced concepts.

## 🚀 Getting Started

### Prerequisites
- Java 17 or later
- Maven 3.6.0 or later
- Your favorite IDE (IntelliJ IDEA, Eclipse, or VS Code)

### Project Structure

```
core-java/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/javatraining/core/
│   │   │       ├── basics/          # Basic Java concepts
│   │   │       │   ├── variables/   # Variables and data types
│   │   │       │   ├── operators/   # Operators and expressions
│   │   │       │   ├── controlflow/ # Control structures
│   │   │       │   ├── arrays/      # Arrays and collections
│   │   │       │   └── strings/     # String manipulation
│   │   │       │
│   │   │       ├── oops/            # Object-Oriented Programming
│   │   │       │   ├── classes/     # Classes and objects
│   │   │       │   ├── inheritance/
│   │   │       │   ├── polymorphism/
│   │   │       │   ├── abstraction/
│   │   │       │   └── interfaces/
│   │   │       │
│   │   │       ├── collections/     # Collections Framework
│   │   │       │   ├── lists/
│   │   │       │   ├── sets/
│   │   │       │   ├── maps/
│   │   │       │   └── queues/
│   │   │       │
│   │   │       ├── exceptions/      # Exception handling
│   │   │       ├── io/              # Input/Output operations
│   │   │       ├── threads/         # Concurrency
│   │   │       └── advanced/        # Advanced Java features
│   │   │           ├── generics/
│   │   │           ├── annotations/
│   │   │           ├── reflection/
│   │   │           ├── lambda/
│   │   │           ├── streams/
│   │   │           └── optional/
│   │   │
│   │   └── resources/             # Configuration and resource files
│   │       └── docs/              # Documentation and reference materials
│   │
│   └── test/                      # Test files (JUnit 5)
│       └── java/com/javatraining/core/
│           └── [matching test structure]
│
└── pom.xml                        # Maven build configuration
```

## 📚 Learning Path

### 1. Java Basics
- Variables and Data Types
- Operators and Expressions
- Control Flow Statements
- Arrays and Strings
- Methods and Recursion

### 2. Object-Oriented Programming
- Classes and Objects
- Inheritance and Polymorphism
- Abstraction and Interfaces
- Encapsulation
- Exception Handling

### 3. Core Java APIs
- Collections Framework
- I/O and NIO.2
- Concurrency and Multithreading
- Date/Time API
- Regular Expressions

### 4. Advanced Features
- Generics
- Annotations
- Reflection
- Lambda Expressions
- Streams API
- Optional and Other New Features

## 🛠️ How to Use This Repository

1. **Clone the repository**:
   ```bash
   git clone https://github.com/maddireddy/Training.git
   cd Training/Java-Training/core-java
   ```

2. **Build the project**:
   ```bash
   mvn clean install
   ```

3. **Run examples**:
   ```bash
   # Run a specific example
   mvn exec:java -Dexec.mainClass="com.javatraining.core.basics.controlflow.LoopExamples"
   
   # Run all tests
   mvn test
   ```

4. **Explore the code**:
   - Navigate through the package structure
   - Read the inline documentation
   - Experiment with the examples

## 🧪 Testing

This project uses JUnit 5 for testing. To run tests:

```bash
# Run all tests
mvn test

# Run a specific test class
mvn test -Dtest=ClassNameTest

# Run a specific test method
mvn test -Dtest=ClassNameTest#methodName
```

## 📖 Documentation

- [Java Documentation](https://docs.oracle.com/en/java/)
- [Java Tutorials](https://docs.oracle.com/javase/tutorial/)
- [Java API Documentation](https://docs.oracle.com/en/java/javase/17/docs/api/index.html)

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📜 License

This project is licensed under the MIT License - see the [LICENSE](../LICENSE) file for details.

## 🙏 Acknowledgments

- Oracle Java Documentation Team
- Java Community
- Open Source Contributors
