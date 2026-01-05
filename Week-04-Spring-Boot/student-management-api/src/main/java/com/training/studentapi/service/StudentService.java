package com.training.studentapi.service;

import com.training.studentapi.model.Student;
import com.training.studentapi.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Student Service - Business Logic Layer
 *
 * This class contains business logic for student operations.
 *
 * @Service - Marks this as a service component (business logic)
 * @RequiredArgsConstructor - Lombok: Generates constructor for final fields
 * @Transactional - Ensures database operations are atomic
 *
 * Layer Architecture:
 * Controller → Service → Repository → Database
 *
 * Real-world: This pattern is used in all enterprise applications
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentService {

    private final StudentRepository studentRepository;

    /**
     * Get all students
     * Used in: Dashboard, Admin panel, Reports
     */
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * Get student by ID
     * Used in: Student profile, Edit student, View details
     *
     * @param id Student ID
     * @return Optional<Student> - May or may not find student
     */
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    /**
     * Create new student
     * Used in: Registration form, Admin panel
     *
     * @param student Student object to create
     * @return Created student with generated ID
     * @throws IllegalArgumentException if email already exists
     */
    @Transactional
    public Student createStudent(Student student) {
        // Business validation: Check if email already exists
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new IllegalArgumentException(
                "Student with email " + student.getEmail() + " already exists"
            );
        }

        // Set default values
        if (student.getIsActive() == null) {
            student.setIsActive(true);
        }

        return studentRepository.save(student);
    }

    /**
     * Update existing student
     * Used in: Edit student form, Profile update
     *
     * @param id Student ID to update
     * @param studentDetails Updated student data
     * @return Updated student
     * @throws RuntimeException if student not found
     */
    @Transactional
    public Student updateStudent(Long id, Student studentDetails) {
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        // Update fields
        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());
        student.setAge(studentDetails.getAge());
        student.setCourse(studentDetails.getCourse());
        student.setPhoneNumber(studentDetails.getPhoneNumber());
        student.setAddress(studentDetails.getAddress());

        if (studentDetails.getIsActive() != null) {
            student.setIsActive(studentDetails.getIsActive());
        }

        return studentRepository.save(student);
    }

    /**
     * Delete student
     * Used in: Admin panel
     *
     * @param id Student ID to delete
     * @throws RuntimeException if student not found
     */
    @Transactional
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }

    /**
     * Search students by name
     * Used in: Search functionality
     */
    public List<Student> searchStudentsByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }

    /**
     * Get students by course
     * Used in: Course-wise reports, Analytics
     */
    public List<Student> getStudentsByCourse(String course) {
        return studentRepository.findByCourse(course);
    }

    /**
     * Get active students only
     * Used in: Active students list, Attendance
     */
    public List<Student> getActiveStudents() {
        return studentRepository.findByIsActiveTrue();
    }

    /**
     * Get student count
     * Used in: Dashboard statistics
     */
    public long getTotalStudentCount() {
        return studentRepository.count();
    }

    /**
     * Get course statistics
     * Used in: Analytics dashboard
     */
    public long getStudentCountByCourse(String course) {
        return studentRepository.countByCourse(course);
    }
}

/*
 * SERVICE LAYER RESPONSIBILITIES:
 *
 * 1. Business Logic:
 *    - Validation (email uniqueness, data constraints)
 *    - Business rules (enrollment limits, eligibility)
 *    - Calculations (fees, grades, attendance %)
 *
 * 2. Transaction Management:
 *    - @Transactional ensures data consistency
 *    - Rollback on errors
 *
 * 3. Orchestration:
 *    - Combining multiple repository operations
 *    - Calling external services
 *    - Complex workflows
 *
 * 4. Error Handling:
 *    - Meaningful exception messages
 *    - Custom exceptions
 *
 * REAL-WORLD EXAMPLES:
 *
 * E-commerce OrderService:
 * - Check product availability
 * - Calculate total amount
 * - Apply discounts
 * - Create order
 * - Update inventory
 * - Send email notification
 * - All in one transaction!
 *
 * Banking TransactionService:
 * - Validate account balance
 * - Check daily limits
 * - Debit from source account
 * - Credit to destination account
 * - Log transaction
 * - Send SMS notification
 */
