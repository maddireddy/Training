/**
 * Day 1 - Example 1: Hello World
 *
 * This is your first Java program!
 * It demonstrates the basic structure of a Java program.
 *
 * Learning Points:
 * - Public class declaration
 * - main method (entry point)
 * - System.out.println() for output
 * - Comments in Java
 *
 * How to run:
 * 1. Compile: javac HelloWorld.java
 * 2. Run: java HelloWorld
 */

public class HelloWorld {

    /**
     * The main method is the entry point of any Java application.
     * JVM starts execution from here.
     *
     * @param args Command-line arguments (not used in this example)
     */
    public static void main(String[] args) {
        // This is a single-line comment
        // println() prints text and moves to next line
        System.out.println("Hello, World!");
        System.out.println("Welcome to Java Full Stack Training!");

        /*
         * This is a multi-line comment
         * You can write multiple lines here
         * Useful for detailed explanations
         */
        System.out.println("This is Day 1 of your journey to becoming a Full Stack Developer!");

        // print() vs println()
        System.out.print("This will not move to next line. ");
        System.out.print("See? Same line!");
        System.out.println(); // Just moves to next line

        System.out.println("This is on a new line.");

        // Special characters
        System.out.println("Tab\tExample");
        System.out.println("New\nLine\nExample");
        System.out.println("Quote Example: \"Hello\"");
        System.out.println("Backslash: \\");

        // Multiple outputs
        System.out.println("=".repeat(50)); // Java 11+ feature
        System.out.println("Your Java journey starts now!");
        System.out.println("=".repeat(50));
    }
}

/*
 * OUTPUT:
 * -------
 * Hello, World!
 * Welcome to Java Full Stack Training!
 * This is Day 1 of your journey to becoming a Full Stack Developer!
 * This will not move to next line. See? Same line!
 * This is on a new line.
 * Tab    Example
 * New
 * Line
 * Example
 * Quote Example: "Hello"
 * Backslash: \
 * ==================================================
 * Your Java journey starts now!
 * ==================================================
 */
