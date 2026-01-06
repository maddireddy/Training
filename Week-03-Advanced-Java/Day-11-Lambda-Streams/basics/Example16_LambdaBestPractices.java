/**
 * Lambda Best Practices and Anti-patterns
 * Demonstrates: Good practices, common mistakes, performance tips
 */
package basics;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Example16_LambdaBestPractices {
    public static void main(String[] args) {
        System.out.println("=== Lambda Best Practices ===\n");

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        // ✓ GOOD: Use method references when possible
        System.out.println("=== Method References ===");
        names.stream().forEach(System.out::println);  // Good
        // names.stream().forEach(s -> System.out.println(s));  // Less concise

        // ✓ GOOD: Keep lambdas short and simple
        System.out.println("\n=== Simple Lambdas ===");
        Predicate<String> isLong = s -> s.length() > 5;  // Good: single expression

        // ❌ BAD: Complex logic in lambda
        // Predicate<String> complex = s -> {
        //     if (s == null) return false;
        //     String trimmed = s.trim();
        //     return trimmed.length() > 5 && trimmed.contains("a");
        // };  // Extract to method instead!

        // ✓ GOOD: Extract complex logic
        Predicate<String> isValid = Example16_LambdaBestPractices::isValidName;

        // ✓ GOOD: Avoid side effects
        System.out.println("\n=== Avoid Side Effects ===");
        List<String> upper = names.stream()
            .map(String::toUpperCase)
            .toList();  // Good: pure function

        // ❌ BAD: Side effects in lambda
        List<String> result = new ArrayList<>();
        // names.stream().forEach(s -> result.add(s.toUpperCase()));  // Use collect!

        // ✓ GOOD: Use collect for accumulation
        List<String> collected = names.stream()
            .map(String::toUpperCase)
            .collect(Collectors.toList());

        // ✓ GOOD: Reuse lambdas
        System.out.println("\n=== Reuse Lambdas ===");
        Predicate<Integer> isEven = n -> n % 2 == 0;

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        long evenCount = numbers.stream().filter(isEven).count();
        List<Integer> evens = numbers.stream().filter(isEven).toList();

        // ✓ GOOD: Type inference
        System.out.println("\n=== Type Inference ===");
        Function<String, Integer> length = s -> s.length();  // Good
        // Function<String, Integer> length2 = (String s) -> s.length();  // Verbose

        // ✓ GOOD: Avoid returning null from lambdas
        Function<String, Optional<String>> safe = s -> Optional.ofNullable(s);

        System.out.println("Best practices demonstrated!");
    }

    static boolean isValidName(String name) {
        if (name == null) return false;
        String trimmed = name.trim();
        return trimmed.length() > 2 && trimmed.length() < 20;
    }
}
