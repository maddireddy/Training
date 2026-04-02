/**
 * Finally Block
 * Demonstrates: Finally block execution, resource cleanup
 */
package basics;

import java.io.*;

public class Example02_FinallyBlock {
    public static void main(String[] args) {
        System.out.println("=== Finally Block ===\n");

        // Finally always executes
        try {
            System.out.println("In try block");
            int result = 10 / 2;
        } catch (Exception e) {
            System.out.println("In catch block");
        } finally {
            System.out.println("In finally block - always executes!");
        }

        // Finally with exception
        try {
            System.out.println("\nTrying division by zero");
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Caught exception");
        } finally {
            System.out.println("Cleanup in finally");
        }

        // Resource cleanup
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("test.txt"));
            // Read file...
        } catch (IOException e) {
            System.out.println("\nFile not found");
        } finally {
            // Cleanup
            try {
                if (reader != null) reader.close();
                System.out.println("Resources closed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Try-with-resources (better)
        try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))) {
            // Use resource
        } catch (IOException e) {
            System.out.println("Using try-with-resources");
        }
    }
}
