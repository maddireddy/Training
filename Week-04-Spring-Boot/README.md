# Week 4: Spring Boot Basics

## 🎯 Learning Objectives
- Understand Spring Framework and Spring Boot
- Create RESTful APIs
- Implement CRUD operations
- Handle HTTP requests and responses
- Use Spring Data JPA
- Connect to databases

## 📚 Topics Covered

### Day 16: Spring Boot Introduction
- Spring vs Spring Boot
- Auto-configuration
- Dependency Injection
- Creating first Spring Boot app

### Day 17: REST API Development
- @RestController
- HTTP Methods (GET, POST, PUT, DELETE)
- @RequestMapping annotations
- Request and Response handling

### Day 18: Validation & Exception Handling
- @Valid annotation
- Custom validators
- @ControllerAdvice
- Global exception handling

### Day 19-20: Spring Data JPA
- Entity mapping
- Repository interfaces
- Custom queries
- Database integration

## 🛠️ Prerequisites
- Java 17
- Maven
- IntelliJ IDEA / VS Code
- Postman (for API testing)
- MySQL / PostgreSQL (optional - we'll use H2 in-memory DB)

## 🚀 Getting Started

### Option 1: GitHub Codespaces
1. Open this folder in Codespaces
2. All dependencies pre-installed
3. Start coding!

### Option 2: Local Setup
```bash
# Navigate to project
cd Week-04-Spring-Boot/student-management-api

# Build project
mvn clean install

# Run application
mvn spring-boot:run

# Application will start on http://localhost:8080
```

## 📁 Project Structure
```
student-management-api/
├── src/
│   ├── main/
│   │   ├── java/com/training/studentapi/
│   │   │   ├── controller/      # REST Controllers
│   │   │   ├── model/           # Entity classes
│   │   │   ├── repository/      # Data access layer
│   │   │   ├── service/         # Business logic
│   │   │   └── StudentApiApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── data.sql         # Sample data
│   └── test/                    # Test cases
└── pom.xml                      # Maven dependencies
```

## 🔑 Key Concepts

### 1. Dependency Injection
```java
@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;
}
```

### 2. REST Controller
```java
@RestController
@RequestMapping("/api/students")
public class StudentController {
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.findAll();
    }
}
```

### 3. JPA Entity
```java
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
```

## 🧪 Testing APIs

### Using Postman:

**Get all students:**
```
GET http://localhost:8080/api/students
```

**Get student by ID:**
```
GET http://localhost:8080/api/students/1
```

**Create new student:**
```
POST http://localhost:8080/api/students
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "age": 22
}
```

**Update student:**
```
PUT http://localhost:8080/api/students/1
Content-Type: application/json

{
  "name": "John Updated",
  "email": "john.updated@example.com",
  "age": 23
}
```

**Delete student:**
```
DELETE http://localhost:8080/api/students/1
```

## 📝 Exercises

### Exercise 1: Add Phone Number
Add a `phoneNumber` field to Student entity

### Exercise 2: Search by Name
Implement: `GET /api/students/search?name=John`

### Exercise 3: Pagination
Add pagination support to GET endpoint

### Exercise 4: Custom Exception
Create custom exception for student not found

### Exercise 5: DTO Pattern
Implement DTOs instead of exposing entities directly

## 🏆 Mini-Project: Library Management API

Build a REST API for library management:
- Books (CRUD)
- Authors (CRUD)
- Book-Author relationship (Many-to-Many)
- Search books by author
- Search books by title/genre

## 🎓 Real-World Applications

This Student Management API pattern is used in:
- **Education sector**: School/College management systems
- **HR systems**: Employee management
- **E-commerce**: Product catalog management
- **Healthcare**: Patient management

## 📚 Resources
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Initializr](https://start.spring.io/)
- [Baeldung Spring Tutorials](https://www.baeldung.com/spring-boot)

## 🐛 Common Issues

### Port already in use:
```properties
# Change in application.properties
server.port=8081
```

### Database connection error:
```properties
# For H2 in-memory DB
spring.datasource.url=jdbc:h2:mem:testdb
spring.h2.console.enabled=true
```

## 🎯 Week 4 Checklist
- [ ] Created first Spring Boot application
- [ ] Implemented REST CRUD operations
- [ ] Used Spring Data JPA
- [ ] Tested APIs with Postman
- [ ] Handled exceptions globally
- [ ] Completed mini-project
- [ ] Deployed locally

---

**Next Week**: Advanced Spring Boot - Security, Caching, Testing
