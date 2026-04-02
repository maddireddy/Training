/**
 * JDK 17 Features - Pattern Matching for switch (Preview)
 * Key Feature: Enhanced switch expressions
 */
package jdk17;

public class Example07_JDK17_PatternMatching {
    public static void main(String[] args) {

        // 1. Traditional instanceof
        Object obj1 = "Hello";
        if (obj1 instanceof String) {
            String str = (String) obj1;  // Cast needed
            System.out.println("Traditional: " + str.toUpperCase());
        }

        // 2. Pattern matching for instanceof (JDK 16+)
        if (obj1 instanceof String str) {  // No cast needed!
            System.out.println("Pattern matching: " + str.toUpperCase());
        }

        // 3. Multiple type checks
        processObject("Hello");
        processObject(100);
        processObject(3.14);
        processObject(true);
        processObject(new int[]{1, 2, 3});

        // 4. Null handling
        processObject(null);

        // 5. Guards with patterns
        checkNumberRange(5);
        checkNumberRange(15);
        checkNumberRange(25);
    }

    static void processObject(Object obj) {
        String result = switch (obj) {
            case null -> "It's null";
            case String s -> "String of length " + s.length();
            case Integer i -> "Integer value: " + i;
            case Double d -> "Double value: " + d;
            case Boolean b -> "Boolean: " + b;
            case int[] arr -> "Array of length " + arr.length;
            default -> "Unknown type: " + obj.getClass().getSimpleName();
        };
        System.out.println(result);
    }

    static void checkNumberRange(Integer num) {
        String range = switch (num) {
            case Integer i when i < 10 -> "Less than 10";
            case Integer i when i < 20 -> "10 to 19";
            case Integer i when i < 30 -> "20 to 29";
            default -> "30 or more";
        };
        System.out.println(num + " is " + range);
    }
}
