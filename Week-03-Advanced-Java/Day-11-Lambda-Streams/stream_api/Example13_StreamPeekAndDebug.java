/**
 * Stream Peek and Debugging
 * Demonstrates: peek(), debugging streams, intermediate operations inspection
 */
package stream_api;

import java.util.*;
import java.util.stream.*;

public class Example13_StreamPeekAndDebug {
    public static void main(String[] args) {
        System.out.println("=== Stream Peek and Debugging ===\n");

        // 1. Basic peek usage
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println("=== Basic peek ===");
        List<Integer> result = numbers.stream()
            .peek(n -> System.out.println("Original: " + n))
            .filter(n -> n % 2 == 0)
            .peek(n -> System.out.println("After filter: " + n))
            .map(n -> n * 2)
            .peek(n -> System.out.println("After map: " + n))
            .toList();

        System.out.println("Final result: " + result);

        // 2. Debugging complex pipeline
        System.out.println("\n=== Debugging Pipeline ===");
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");

        long count = words.stream()
            .peek(s -> System.out.println("1. Original: " + s))
            .filter(s -> s.length() > 5)
            .peek(s -> System.out.println("2. After length filter: " + s))
            .map(String::toUpperCase)
            .peek(s -> System.out.println("3. After uppercase: " + s))
            .filter(s -> s.contains("E"))
            .peek(s -> System.out.println("4. Contains E: " + s))
            .count();

        System.out.println("Count: " + count);

        // 3. Performance monitoring
        System.out.println("\n=== Performance Monitoring ===");
        numbers.stream()
            .peek(n -> System.out.println("Processing: " + n))
            .filter(n -> {
                boolean result = isPrime(n);
                System.out.println("  " + n + " is prime: " + result);
                return result;
            })
            .forEach(n -> System.out.println("Prime found: " + n));

        // 4. Lazy evaluation demonstration
        System.out.println("\n=== Lazy Evaluation ===");
        System.out.println("Creating stream (no output yet):");
        Stream<Integer> stream = numbers.stream()
            .peek(n -> System.out.println("Peek: " + n))
            .filter(n -> n > 5);

        System.out.println("Stream created but not executed");
        System.out.println("Now consuming:");
        stream.forEach(n -> System.out.println("Result: " + n));
    }

    static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }
}
