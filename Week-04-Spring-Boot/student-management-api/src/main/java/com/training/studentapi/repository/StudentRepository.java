package com.training.studentapi.repository;

import com.training.studentapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Student Repository Interface
 *
 * This interface handles all database operations for Student entity.
 *
 * JpaRepository provides built-in methods:
 * - findAll() - Get all students
 * - findById(id) - Get student by ID
 * - save(student) - Create or update student
 * - deleteById(id) - Delete student by ID
 * - count() - Count total students
 * - existsById(id) - Check if student exists
 *
 * No implementation needed! Spring Data JPA generates it automatically.
 *
 * Real-world: This is how data access works in all modern Java applications.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Find student by email
     * Method name follows Spring Data JPA naming convention
     * Spring automatically generates the query: SELECT * FROM students WHERE email = ?
     */
    Optional<Student> findByEmail(String email);

    /**
     * Find all students by course
     * Query: SELECT * FROM students WHERE course = ?
     */
    List<Student> findByCourse(String course);

    /**
     * Find students by age greater than
     * Query: SELECT * FROM students WHERE age > ?
     */
    List<Student> findByAgeGreaterThan(Integer age);

    /**
     * Find students by age between
     * Query: SELECT * FROM students WHERE age BETWEEN ? AND ?
     */
    List<Student> findByAgeBetween(Integer minAge, Integer maxAge);

    /**
     * Find students by name containing (case-insensitive)
     * Query: SELECT * FROM students WHERE LOWER(student_name) LIKE LOWER(?)
     * Used for search functionality
     */
    List<Student> findByNameContainingIgnoreCase(String name);

    /**
     * Find active students
     * Query: SELECT * FROM students WHERE is_active = true
     */
    List<Student> findByIsActiveTrue();

    /**
     * Custom JPQL Query
     * @Query annotation allows writing custom queries
     * JPQL (Java Persistence Query Language) uses entity names, not table names
     */
    @Query("SELECT s FROM Student s WHERE s.course = :course AND s.isActive = true")
    List<Student> findActiveStudentsByCourse(@Param("course") String course);

    /**
     * Native SQL Query
     * When you need complex queries, use native SQL
     * nativeQuery = true tells Spring this is SQL, not JPQL
     */
    @Query(value = "SELECT * FROM students WHERE age >= :minAge ORDER BY age ASC",
           nativeQuery = true)
    List<Student> findStudentsAboveAge(@Param("minAge") Integer minAge);

    /**
     * Count students by course
     * Query: SELECT COUNT(*) FROM students WHERE course = ?
     */
    Long countByCourse(String course);

    /**
     * Check if email exists
     * Query: SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM students WHERE email = ?
     */
    boolean existsByEmail(String email);

    /**
     * Delete students by course
     * @Modifying annotation required for DELETE/UPDATE queries
     * Returns number of deleted records
     */
    // @Modifying
    // @Query("DELETE FROM Student s WHERE s.course = :course")
    // int deleteByCourse(@Param("course") String course);
}

/*
 * DERIVED QUERY METHODS:
 * Spring Data JPA can generate queries from method names!
 *
 * Naming Convention:
 * findBy + FieldName + Operation
 *
 * Examples:
 * - findByName(String name)
 * - findByNameAndAge(String name, Integer age)
 * - findByNameOrEmail(String name, String email)
 * - findByAgeGreaterThan(Integer age)
 * - findByAgeLessThan(Integer age)
 * - findByNameContaining(String name)
 * - findByNameStartingWith(String prefix)
 * - findByNameEndingWith(String suffix)
 * - findByAgeIn(List<Integer> ages)
 * - findByNameOrderByAgeDesc(String name)
 *
 * This is PRODUCTION-READY code used in real applications!
 */

/*
 * REAL-WORLD APPLICATIONS:
 *
 * E-commerce:
 * - ProductRepository.findByCategoryAndPriceRange()
 * - OrderRepository.findByCustomerIdAndStatus()
 *
 * Banking:
 * - AccountRepository.findByAccountNumberAndIsActive()
 * - TransactionRepository.findByDateBetween()
 *
 * Social Media:
 * - PostRepository.findByUserIdOrderByCreatedDateDesc()
 * - CommentRepository.findByPostIdAndIsVisible()
 *
 * HR Systems:
 * - EmployeeRepository.findByDepartmentAndDesignation()
 * - LeaveRepository.findByEmployeeIdAndStatus()
 */
