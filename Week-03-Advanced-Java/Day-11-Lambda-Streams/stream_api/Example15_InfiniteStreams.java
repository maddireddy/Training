/**
 * Infinite Streams
 * Demonstrates: Stream.generate(), Stream.iterate(), limit(), takeWhile()
 */
package stream_api;

import java.util.*;
import java.util.stream.*;

public class Example15_InfiniteStreams {
    public static void main(String[] args) {
        System.out.println("=== Infinite Streams ===\n");

        // 1. Stream.generate() - supplier based
        System.out.println("=== Stream.generate() ===");
        Stream.generate(Math::random)
            .limit(5)
            .forEach(n -> System.out.printf("%.4f%n", n));

        // 2. Stream.iterate() - seed and function
        System.out.println("\n=== Stream.iterate() ===");
        Stream.iterate(0, n -> n + 2)
            .limit(10)
            .forEach(n -> System.out.print(n + " "));

        // 3. Fibonacci sequence
        System.out.println("\n\n=== Fibonacci ===");
        Stream.iterate(new int[]{0, 1}, f -> new int[]{f[1], f[0] + f[1]})
            .limit(10)
            .map(f -> f[0])
            .forEach(n -> System.out.print(n + " "));

        // 4. takeWhile (Java 9+)
        System.out.println("\n\n=== takeWhile ===");
        Stream.iterate(1, n -> n + 1)
            .takeWhile(n -> n <= 10)
            .forEach(n -> System.out.print(n + " "));

        // 5. dropWhile (Java 9+)
        System.out.println("\n\n=== dropWhile ===");
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .dropWhile(n -> n < 5)
            .forEach(n -> System.out.print(n + " "));

        // 6. Random numbers
        System.out.println("\n\n=== Random numbers ===");
        new Random()
            .ints(1, 100)
            .limit(10)
            .forEach(n -> System.out.print(n + " "));

        System.out.println();
    }
}
