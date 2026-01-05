package com.training.studentapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Student Entity
 *
 * This class represents a student in the database.
 *
 * Annotations explained:
 * @Entity - Marks this class as a JPA entity (database table)
 * @Table - Specifies the table name in database
 * @Data - Lombok: Generates getters, setters, toString, equals, hashCode
 * @NoArgsConstructor - Lombok: Generates no-argument constructor
 * @AllArgsConstructor - Lombok: Generates constructor with all fields
 *
 * Real-world: Similar entities exist for Employee, Product, Order, etc.
 */
@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    /**
     * Primary Key
     * @Id - Marks this field as primary key
     * @GeneratedValue - Auto-generates ID value
     * IDENTITY strategy: Database auto-increments the ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Student Name
     * @NotBlank - Validation: Cannot be null or empty
     * @Size - Validation: Length between 2 and 100 characters
     * @Column - Maps to 'student_name' column, cannot be null
     */
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    @Column(name = "student_name", nullable = false)
    private String name;

    /**
     * Email Address
     * @Email - Validation: Must be valid email format
     * @NotBlank - Cannot be null or empty
     * unique=true - No duplicate emails allowed
     */
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * Age
     * @Min - Minimum value is 16
     * @Max - Maximum value is 100
     */
    @Min(value = 16, message = "Age must be at least 16")
    @Max(value = 100, message = "Age must be less than 100")
    @Column(nullable = false)
    private Integer age;

    /**
     * Course/Major
     */
    @Column(length = 100)
    private String course;

    /**
     * Contact Number
     * @Pattern - Validation: Must match phone number pattern
     */
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * Address
     */
    @Column(length = 500)
    private String address;

    /**
     * Enrollment Status
     */
    @Column(name = "is_active")
    private Boolean isActive = true;
}

/*
 * Without Lombok, you would need to write:
 * - Getters for all fields (getName(), getEmail(), etc.)
 * - Setters for all fields (setName(), setEmail(), etc.)
 * - toString() method
 * - equals() and hashCode() methods
 * - Constructors
 *
 * Lombok generates all of this automatically!
 *
 * This is a REAL-WORLD example - similar to entities in:
 * - HR Systems (Employee entity)
 * - E-commerce (Product entity)
 * - Banking (Account entity)
 * - Healthcare (Patient entity)
 */
