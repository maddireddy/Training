/**
 * JDK 11 Features - New String Methods (2018 LTS)
 * Key Features: isBlank, lines, strip, repeat
 */
package jdk11;

public class Example04_JDK11_NewStringMethods {
    public static void main(String[] args) {

        // 1. isBlank() - Checks if string is empty or whitespace
        String str1 = "   ";
        String str2 = "Hello";
        System.out.println("'   '.isBlank(): " + str1.isBlank());  // true
        System.out.println("'Hello'.isBlank(): " + str2.isBlank()); // false

        // 2. lines() - Stream of lines
        String multiline = "Line 1\nLine 2\nLine 3";
        System.out.println("\nlines():");
        multiline.lines().forEach(System.out::println);

        // 3. strip(), stripLeading(), stripTrailing()
        String str3 = "  Hello World  ";
        System.out.println("\nOriginal: '" + str3 + "'");
        System.out.println("strip(): '" + str3.strip() + "'");
        System.out.println("stripLeading(): '" + str3.stripLeading() + "'");
        System.out.println("stripTrailing(): '" + str3.stripTrailing() + "'");

        // 4. repeat() - Repeat string n times
        String str4 = "Java ";
        System.out.println("\nrepeat(3): " + str4.repeat(3));

        // 5. Practical example - Data cleaning
        String data = "  user@example.com  ";
        String cleaned = data.strip();
        System.out.println("\nCleaned email: '" + cleaned + "'");

        // 6. Lines processing
        String csv = "Name,Age\nAlice,25\nBob,30";
        long lineCount = csv.lines().count();
        System.out.println("\nCSV lines: " + lineCount);
    }
}
