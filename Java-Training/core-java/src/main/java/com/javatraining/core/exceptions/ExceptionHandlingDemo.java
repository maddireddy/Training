package com.javatraining.core.exceptions;

import java.io.*;

/**
 * Demonstrates exception handling in Java
 */
public class ExceptionHandlingDemo {
    public static void main(String[] args) {
        // Try-catch-finally
        try {
            int result = 10 / 0; // This will throw ArithmeticException
        } catch (ArithmeticException e) {
            System.err.println("Error: Division by zero");
        } finally {
            System.out.println("This block always executes");
        }
        
        // Try-with-resources (Java 7+)
        try (BufferedReader br = new BufferedReader(new FileReader("nonexistent.txt"))) {
            System.out.println(br.readLine());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        
        // Custom exception
        try {
            validateAge(15);
        } catch (InvalidAgeException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    // Method that throws a custom exception
    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be 18 or older");
        }
        System.out.println("Age is valid");
    }
}

// Custom exception class
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}
