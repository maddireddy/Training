/**
 * Try-Catch Basics
 * Demonstrates: Basic exception handling, multiple catch blocks
 */
package basics;

public class Example01_TryCatchBasics {
    public static void main(String[] args) {
        System.out.println("=== Try-Catch Basics ===\n");

        // Basic try-catch
        try {
            int result = 10 / 0;  // ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("Error: Division by zero");
        }

        // Multiple catch blocks
        try {
            String str = null;
            System.out.println(str.length());  // NullPointerException
        } catch (NullPointerException e) {
            System.out.println("Error: Null pointer");
        } catch (Exception e) {
            System.out.println("Error: General exception");
        }

        // Array index exception
        try {
            int[] arr = {1, 2, 3};
            System.out.println(arr[5]);  // ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Array index out of bounds");
        }

        // Number format exception
        try {
            int num = Integer.parseInt("abc");  // NumberFormatException
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format");
        }

        System.out.println("\nProgram continues...");
    }
}
