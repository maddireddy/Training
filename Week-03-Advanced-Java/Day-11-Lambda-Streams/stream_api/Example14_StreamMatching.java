/**
 * Stream Matching Operations
 * Demonstrates: anyMatch, allMatch, noneMatch, findFirst, findAny
 */
package stream_api;

import java.util.*;
import java.util.stream.*;

public class Example14_StreamMatching {
    public static void main(String[] args) {
        System.out.println("=== Stream Matching Operations ===\n");

        List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 10, 12, 14, 16, 18, 20);
        List<Integer> mixed = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // anyMatch - at least one matches
        System.out.println("=== anyMatch ===");
        boolean hasEven = mixed.stream().anyMatch(n -> n % 2 == 0);
        boolean hasGreaterThan100 = mixed.stream().anyMatch(n -> n > 100);
        System.out.println("Has even number: " + hasEven);
        System.out.println("Has number > 100: " + hasGreaterThan100);

        // allMatch - all elements match
        System.out.println("\n=== allMatch ===");
        boolean allEven = numbers.stream().allMatch(n -> n % 2 == 0);
        boolean allPositive = numbers.stream().allMatch(n -> n > 0);
        System.out.println("All even: " + allEven);
        System.out.println("All positive: " + allPositive);

        // noneMatch - no elements match
        System.out.println("\n=== noneMatch ===");
        boolean noneNegative = numbers.stream().noneMatch(n -> n < 0);
        boolean noneOdd = numbers.stream().noneMatch(n -> n % 2 != 0);
        System.out.println("None negative: " + noneNegative);
        System.out.println("None odd: " + noneOdd);

        // findFirst - first element
        System.out.println("\n=== findFirst ===");
        Optional<Integer> first = mixed.stream()
            .filter(n -> n > 5)
            .findFirst();
        System.out.println("First > 5: " + first.orElse(null));

        // findAny - any element (useful in parallel)
        System.out.println("\n=== findAny ===");
        Optional<Integer> any = mixed.parallelStream()
            .filter(n -> n > 5)
            .findAny();
        System.out.println("Any > 5: " + any.orElse(null));

        // Real-world examples
        List<Person> people = Arrays.asList(
            new Person("Alice", 25),
            new Person("Bob", 30),
            new Person("Charlie", 35)
        );

        boolean hasAdult = people.stream().anyMatch(p -> p.age >= 18);
        boolean allAdults = people.stream().allMatch(p -> p.age >= 18);
        Optional<Person> firstOver30 = people.stream()
            .filter(p -> p.age > 30)
            .findFirst();

        System.out.println("\nHas adult: " + hasAdult);
        System.out.println("All adults: " + allAdults);
        System.out.println("First over 30: " + firstOver30.map(p -> p.name).orElse("None"));
    }

    static class Person {
        String name;
        int age;
        Person(String name, int age) { this.name = name; this.age = age; }
    }
}
