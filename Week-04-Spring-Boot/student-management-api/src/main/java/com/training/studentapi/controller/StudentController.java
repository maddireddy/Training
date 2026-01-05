package com.training.studentapi.controller;

import com.training.studentapi.model.Student;
import com.training.studentapi.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Student REST Controller
 *
 * This class handles HTTP requests for student operations.
 *
 * @RestController - Combines @Controller + @ResponseBody
 *                   All methods return data (JSON/XML), not views
 * @RequestMapping - Base URL for all endpoints in this controller
 * @CrossOrigin - Allows requests from React frontend (different port)
 * @RequiredArgsConstructor - Lombok: Constructor injection
 *
 * Real-world: This is how REST APIs are built in production
 */
@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*") // In production, specify exact frontend URL
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    /**
     * GET /api/students
     * Get all students
     *
     * @return List of all students
     *
     * Example: curl http://localhost:8080/api/students
     */
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    /**
     * GET /api/students/{id}
     * Get student by ID
     *
     * @param id Student ID from URL path
     * @return Student details or 404 Not Found
     *
     * Example: curl http://localhost:8080/api/students/1
     */
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST /api/students
     * Create new student
     *
     * @param student Student data from request body
     * @return Created student with 201 Created status
     *
     * Example:
     * curl -X POST http://localhost:8080/api/students \
     *   -H "Content-Type: application/json" \
     *   -d '{"name":"John Doe","email":"john@example.com","age":22}'
     */
    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        try {
            Student createdStudent = studentService.createStudent(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
        } catch (IllegalArgumentException e) {
            // Email already exists
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * PUT /api/students/{id}
     * Update existing student
     *
     * @param id Student ID to update
     * @param student Updated student data
     * @return Updated student or 404 Not Found
     *
     * Example:
     * curl -X PUT http://localhost:8080/api/students/1 \
     *   -H "Content-Type: application/json" \
     *   -d '{"name":"John Updated","email":"john@example.com","age":23}'
     */
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody Student student) {
        try {
            Student updatedStudent = studentService.updateStudent(id, student);
            return ResponseEntity.ok(updatedStudent);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE /api/students/{id}
     * Delete student
     *
     * @param id Student ID to delete
     * @return 204 No Content or 404 Not Found
     *
     * Example: curl -X DELETE http://localhost:8080/api/students/1
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * GET /api/students/search?name=John
     * Search students by name
     *
     * @param name Search query
     * @return List of matching students
     *
     * Example: curl http://localhost:8080/api/students/search?name=John
     */
    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchStudents(@RequestParam String name) {
        List<Student> students = studentService.searchStudentsByName(name);
        return ResponseEntity.ok(students);
    }

    /**
     * GET /api/students/course/{course}
     * Get students by course
     *
     * @param course Course name
     * @return List of students in that course
     *
     * Example: curl http://localhost:8080/api/students/course/Computer%20Science
     */
    @GetMapping("/course/{course}")
    public ResponseEntity<List<Student>> getStudentsByCourse(@PathVariable String course) {
        List<Student> students = studentService.getStudentsByCourse(course);
        return ResponseEntity.ok(students);
    }

    /**
     * GET /api/students/active
     * Get only active students
     *
     * @return List of active students
     *
     * Example: curl http://localhost:8080/api/students/active
     */
    @GetMapping("/active")
    public ResponseEntity<List<Student>> getActiveStudents() {
        List<Student> students = studentService.getActiveStudents();
        return ResponseEntity.ok(students);
    }

    /**
     * GET /api/students/stats
     * Get statistics
     *
     * @return Statistics about students
     *
     * Example: curl http://localhost:8080/api/students/stats
     */
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStatistics() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalStudents", studentService.getTotalStudentCount());
        stats.put("activeStudents", studentService.getActiveStudents().size());

        return ResponseEntity.ok(stats);
    }
}

/*
 * HTTP STATUS CODES EXPLAINED:
 *
 * 200 OK - Request successful
 * 201 Created - Resource created successfully
 * 204 No Content - Request successful, no content to return
 * 400 Bad Request - Invalid data
 * 404 Not Found - Resource doesn't exist
 * 500 Internal Server Error - Server error
 *
 * ANNOTATIONS EXPLAINED:
 *
 * @GetMapping - HTTP GET request
 * @PostMapping - HTTP POST request (create)
 * @PutMapping - HTTP PUT request (update)
 * @DeleteMapping - HTTP DELETE request
 *
 * @PathVariable - Extract value from URL path (/students/{id})
 * @RequestParam - Extract value from query string (?name=John)
 * @RequestBody - Extract data from request body (JSON)
 * @Valid - Trigger validation annotations on Student
 *
 * REAL-WORLD API DESIGN:
 *
 * This controller follows REST principles:
 * - GET for reading
 * - POST for creating
 * - PUT for updating
 * - DELETE for deleting
 * - Proper HTTP status codes
 * - Meaningful URL structure
 *
 * Used in production by:
 * - Netflix, Amazon, Google (microservices)
 * - Banking apps (transaction APIs)
 * - E-commerce (product APIs)
 * - Social media (post/comment APIs)
 */

/*
 * POSTMAN COLLECTION FOR TESTING:
 *
 * 1. Get All Students
 *    GET http://localhost:8080/api/students
 *
 * 2. Get Student by ID
 *    GET http://localhost:8080/api/students/1
 *
 * 3. Create Student
 *    POST http://localhost:8080/api/students
 *    Body (JSON):
 *    {
 *      "name": "John Doe",
 *      "email": "john@example.com",
 *      "age": 22,
 *      "course": "Computer Science",
 *      "phoneNumber": "9876543210"
 *    }
 *
 * 4. Update Student
 *    PUT http://localhost:8080/api/students/1
 *    Body (JSON): [same as create]
 *
 * 5. Delete Student
 *    DELETE http://localhost:8080/api/students/1
 *
 * 6. Search Students
 *    GET http://localhost:8080/api/students/search?name=John
 *
 * 7. Get Students by Course
 *    GET http://localhost:8080/api/students/course/Computer Science
 */
