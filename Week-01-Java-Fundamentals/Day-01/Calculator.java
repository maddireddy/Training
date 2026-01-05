/**
 * Day 1 - Example 3: Arithmetic Calculator
 *
 * This program demonstrates:
 * - Arithmetic operators (+, -, *, /, %)
 * - User input using Scanner class
 * - Basic calculations
 *
 * Learning Points:
 * - Scanner for user input
 * - Arithmetic operations
 * - Integer vs decimal division
 * - Modulo operator
 */

import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        // Create Scanner object for reading input
        Scanner scanner = new Scanner(System.in);

        System.out.println("=".repeat(50));
        System.out.println("         BASIC ARITHMETIC CALCULATOR");
        System.out.println("=".repeat(50));

        // Get first number
        System.out.print("\nEnter first number: ");
        double num1 = scanner.nextDouble();

        // Get second number
        System.out.print("Enter second number: ");
        double num2 = scanner.nextDouble();

        System.out.println("\n" + "-".repeat(50));
        System.out.println("CALCULATION RESULTS:");
        System.out.println("-".repeat(50));

        // Addition
        double sum = num1 + num2;
        System.out.printf("Addition: %.2f + %.2f = %.2f%n", num1, num2, sum);

        // Subtraction
        double difference = num1 - num2;
        System.out.printf("Subtraction: %.2f - %.2f = %.2f%n", num1, num2, difference);

        // Multiplication
        double product = num1 * num2;
        System.out.printf("Multiplication: %.2f × %.2f = %.2f%n", num1, num2, product);

        // Division
        if (num2 != 0) {
            double quotient = num1 / num2;
            System.out.printf("Division: %.2f ÷ %.2f = %.2f%n", num1, num2, quotient);
        } else {
            System.out.println("Division: Cannot divide by zero!");
        }

        // Modulo (remainder) - works with decimals in Java
        if (num2 != 0) {
            double remainder = num1 % num2;
            System.out.printf("Modulo: %.2f %% %.2f = %.2f%n", num1, num2, remainder);
        }

        System.out.println("-".repeat(50));

        // Demonstrating integer division
        System.out.println("\nINTEGER DIVISION DEMO:");
        System.out.println("-".repeat(50));

        int a = 17;
        int b = 5;

        int intQuotient = a / b;  // Integer division
        int intRemainder = a % b; // Remainder

        System.out.println(a + " ÷ " + b + " = " + intQuotient + " (quotient)");
        System.out.println(a + " % " + b + " = " + intRemainder + " (remainder)");
        System.out.println("So, " + a + " = (" + b + " × " + intQuotient + ") + " + intRemainder);

        double decimalResult = (double) a / b; // Cast to double for decimal result
        System.out.printf("As decimal: %d ÷ %d = %.2f%n", a, b, decimalResult);

        // More operations
        System.out.println("\n" + "-".repeat(50));
        System.out.println("ADDITIONAL OPERATIONS:");
        System.out.println("-".repeat(50));

        // Power (using Math.pow)
        double power = Math.pow(num1, 2);
        System.out.printf("Square of %.2f = %.2f%n", num1, power);

        // Square root
        double sqrt = Math.sqrt(num1);
        System.out.printf("Square root of %.2f = %.2f%n", num1, sqrt);

        // Absolute value
        double abs = Math.abs(num1 - num2);
        System.out.printf("Absolute difference = %.2f%n", abs);

        // Max and Min
        double max = Math.max(num1, num2);
        double min = Math.min(num1, num2);
        System.out.printf("Maximum: %.2f%n", max);
        System.out.printf("Minimum: %.2f%n", min);

        System.out.println("\n" + "=".repeat(50));

        // Close scanner to prevent resource leak
        scanner.close();
    }
}

/*
 * SAMPLE OUTPUT:
 * ==================================================
 *          BASIC ARITHMETIC CALCULATOR
 * ==================================================
 *
 * Enter first number: 25.5
 * Enter second number: 4.2
 *
 * --------------------------------------------------
 * CALCULATION RESULTS:
 * --------------------------------------------------
 * Addition: 25.50 + 4.20 = 29.70
 * Subtraction: 25.50 - 4.20 = 21.30
 * Multiplication: 25.50 × 4.20 = 107.10
 * Division: 25.50 ÷ 4.20 = 6.07
 * Modulo: 25.50 % 4.20 = 0.90
 * --------------------------------------------------
 *
 * INTEGER DIVISION DEMO:
 * --------------------------------------------------
 * 17 ÷ 5 = 3 (quotient)
 * 17 % 5 = 2 (remainder)
 * So, 17 = (5 × 3) + 2
 * As decimal: 17 ÷ 5 = 3.40
 *
 * --------------------------------------------------
 * ADDITIONAL OPERATIONS:
 * --------------------------------------------------
 * Square of 25.50 = 650.25
 * Square root of 25.50 = 5.05
 * Absolute difference = 21.30
 * Maximum: 25.50
 * Minimum: 4.20
 *
 * ==================================================
 */

/*
 * KEY CONCEPTS:
 *
 * 1. ARITHMETIC OPERATORS:
 *    + (Addition)
 *    - (Subtraction)
 *    * (Multiplication)
 *    / (Division)
 *    % (Modulo/Remainder)
 *
 * 2. INTEGER DIVISION:
 *    17 / 5 = 3 (not 3.4!)
 *    Use: (double) 17 / 5 for decimal result
 *
 * 3. DIVISION BY ZERO:
 *    Always check before dividing!
 *    if (num2 != 0) { ... }
 *
 * 4. SCANNER CLASS:
 *    nextInt() - reads integer
 *    nextDouble() - reads decimal
 *    next() - reads single word
 *    nextLine() - reads entire line
 *
 * 5. PRINTF FORMATTING:
 *    %.2f - 2 decimal places
 *    %d - integer
 *    %s - string
 *    %n - newline
 *
 * 6. MATH CLASS:
 *    Math.pow(base, exponent) - power
 *    Math.sqrt(number) - square root
 *    Math.abs(number) - absolute value
 *    Math.max(a, b) - maximum
 *    Math.min(a, b) - minimum
 */
