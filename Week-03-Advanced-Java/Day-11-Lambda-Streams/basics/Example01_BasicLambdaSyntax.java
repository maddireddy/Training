/**
 * Example 01: Basic Lambda Syntax
 * Topic: Lambda Expressions - Basic Syntax
 *
 * Learning: Lambda syntax variations
 */
package basics;

public class Example01_BasicLambdaSyntax {

    public static void main(String[] args) {
        // Traditional Anonymous Class
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Traditional anonymous class");
            }
        };

        // Lambda - Single statement
        Runnable r2 = () -> System.out.println("Lambda - single statement");

        // Lambda - Multiple statements
        Runnable r3 = () -> {
            System.out.println("Lambda - multiple statements");
            System.out.println("Line 2");
        };

        // Execute all
        r1.run();
        r2.run();
        r3.run();

        System.out.println("\n=== Lambda with Parameters ===");

        // Single parameter - parentheses optional
        MathOperation square = x -> x * x;
        System.out.println("Square of 5: " + square.operate(5));

        // Multiple parameters - parentheses required
        MathOperation2 add = (a, b) -> a + b;
        System.out.println("5 + 3 = " + add.operate(5, 3));

        // With type declaration
        MathOperation2 multiply = (int a, int b) -> a * b;
        System.out.println("5 * 3 = " + multiply.operate(5, 3));
    }
}

@FunctionalInterface
interface MathOperation {
    int operate(int x);
}

@FunctionalInterface
interface MathOperation2 {
    int operate(int a, int b);
}
