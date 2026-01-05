package com.training.studentapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Student Management API - Main Application
 *
 * Week 4: Spring Boot Training
 *
 * This is the entry point of the Spring Boot application.
 * @SpringBootApplication annotation enables:
 * - @Configuration: Marks class as source of bean definitions
 * - @EnableAutoConfiguration: Auto-configures Spring based on classpath
 * - @ComponentScan: Scans for components in this package and sub-packages
 *
 * Real-world usage: Every Spring Boot application has one main class like this
 */
@SpringBootApplication
public class StudentApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentApiApplication.class, args);

        System.out.println("\n" + "=".repeat(70));
        System.out.println("🚀 Student Management API is running!");
        System.out.println("📍 URL: http://localhost:8080");
        System.out.println("📊 H2 Console: http://localhost:8080/h2-console");
        System.out.println("📚 API Endpoints:");
        System.out.println("   GET    /api/students          - Get all students");
        System.out.println("   GET    /api/students/{id}     - Get student by ID");
        System.out.println("   POST   /api/students          - Create new student");
        System.out.println("   PUT    /api/students/{id}     - Update student");
        System.out.println("   DELETE /api/students/{id}     - Delete student");
        System.out.println("=".repeat(70) + "\n");
    }
}
