package com.javatraining.core.basics.controlflow;

/**
 * Demonstrates basic if-else control flow in Java
 */
public class IfElseExample {
    public static void main(String[] args) {
        int number = 10;
        
        // Simple if-else
        if (number > 0) {
            System.out.println("Number is positive");
        } else if (number < 0) {
            System.out.println("Number is negative");
        } else {
            System.out.println("Number is zero");
        }
        
        // Nested if-else
        if (number != 0) {
            if (number % 2 == 0) {
                System.out.println("Number is even");
            } else {
                System.out.println("Number is odd");
            }
        }
        
        // Ternary operator
        String result = (number > 0) ? "Positive" : (number < 0) ? "Negative" : "Zero";
        System.out.println("Using ternary operator: " + result);
    }
}
