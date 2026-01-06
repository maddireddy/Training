/**
 * Example 03: Stream API Basics
 * Topic: Stream Operations - Intermediate and Terminal
 *
 * Demonstrates: filter, map, sorted, forEach, collect
 */
package stream_api;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Example03_StreamBasics {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // 1. Filter - Intermediate operation
        System.out.println("=== Filter ===");
        List<Integer> evenNumbers = numbers.stream()
            .filter(n -> n % 2 == 0)
            .collect(Collectors.toList());
        System.out.println("Even numbers: " + evenNumbers);

        // 2. Map - Transform elements
        System.out.println("\n=== Map ===");
        List<Integer> squares = numbers.stream()
            .map(n -> n * n)
            .collect(Collectors.toList());
        System.out.println("Squares: " + squares);

        // 3. Sorted
        System.out.println("\n=== Sorted ===");
        List<Integer> sorted = numbers.stream()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());
        System.out.println("Reverse sorted: " + sorted);

        // 4. forEach - Terminal operation
        System.out.println("\n=== forEach ===");
        System.out.print("All numbers: ");
        numbers.stream().forEach(n -> System.out.print(n + " "));

        // 5. Chaining operations
        System.out.println("\n\n=== Chaining Operations ===");
        List<Integer> result = numbers.stream()
            .filter(n -> n > 5)              // Filter > 5
            .map(n -> n * 2)                 // Double each
            .sorted()                         // Sort
            .collect(Collectors.toList());   // Collect
        System.out.println("Filtered, Doubled, Sorted: " + result);

        // 6. count, min, max
        System.out.println("\n=== Aggregations ===");
        long count = numbers.stream().count();
        System.out.println("Count: " + count);

        Optional<Integer> max = numbers.stream().max(Integer::compareTo);
        System.out.println("Max: " + max.orElse(0));

        Optional<Integer> min = numbers.stream().min(Integer::compareTo);
        System.out.println("Min: " + min.orElse(0));

        // 7. reduce
        System.out.println("\n=== Reduce ===");
        int sum = numbers.stream()
            .reduce(0, (a, b) -> a + b);
        System.out.println("Sum: " + sum);

        int product = numbers.stream()
            .reduce(1, (a, b) -> a * b);
        System.out.println("Product: " + product);

        // 8. distinct
        System.out.println("\n=== Distinct ===");
        List<Integer> withDuplicates = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 4, 4, 4);
        List<Integer> unique = withDuplicates.stream()
            .distinct()
            .collect(Collectors.toList());
        System.out.println("Unique: " + unique);

        // 9. limit and skip
        System.out.println("\n=== Limit and Skip ===");
        List<Integer> first5 = numbers.stream()
            .limit(5)
            .collect(Collectors.toList());
        System.out.println("First 5: " + first5);

        List<Integer> skip5 = numbers.stream()
            .skip(5)
            .collect(Collectors.toList());
        System.out.println("Skip first 5: " + skip5);

        // 10. anyMatch, allMatch, noneMatch
        System.out.println("\n=== Matching ===");
        boolean hasEven = numbers.stream().anyMatch(n -> n % 2 == 0);
        System.out.println("Has even number? " + hasEven);

        boolean allPositive = numbers.stream().allMatch(n -> n > 0);
        System.out.println("All positive? " + allPositive);

        boolean noNegative = numbers.stream().noneMatch(n -> n < 0);
        System.out.println("No negative? " + noNegative);

        // 11. findFirst, findAny
        System.out.println("\n=== Finding Elements ===");
        Optional<Integer> firstEven = numbers.stream()
            .filter(n -> n % 2 == 0)
            .findFirst();
        System.out.println("First even: " + firstEven.orElse(-1));

        // 12. String operations
        System.out.println("\n=== String Stream ===");
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");

        String concatenated = names.stream()
            .collect(Collectors.joining(", "));
        System.out.println("Joined: " + concatenated);

        List<String> upperCase = names.stream()
            .map(String::toUpperCase)
            .collect(Collectors.toList());
        System.out.println("Uppercase: " + upperCase);

        // 13. Parallel streams
        System.out.println("\n=== Parallel Stream ===");
        int parallelSum = numbers.parallelStream()
            .reduce(0, Integer::sum);
        System.out.println("Parallel sum: " + parallelSum);

        // 14. Stream.of()
        System.out.println("\n=== Stream.of() ===");
        Stream.of("Java", "Python", "JavaScript")
            .forEach(System.out::println);

        // 15. IntStream, LongStream, DoubleStream
        System.out.println("\n=== Primitive Streams ===");
        java.util.stream.IntStream.range(1, 6)
            .forEach(n -> System.out.print(n + " "));
        System.out.println();

        int rangeSum = java.util.stream.IntStream.rangeClosed(1, 10).sum();
        System.out.println("Sum of 1-10: " + rangeSum);
    }
}
