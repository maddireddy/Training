/**
 * JDK 8 Features - Optional Class
 * Key Feature: NullPointerException Prevention
 */
package jdk8;

import java.util.Optional;

public class Example03_JDK8_Optional {
    public static void main(String[] args) {

        // 1. Creating Optional
        Optional<String> optional1 = Optional.of("Hello");
        Optional<String> optional2 = Optional.ofNullable(null);
        Optional<String> optional3 = Optional.empty();

        // 2. isPresent()
        if (optional1.isPresent()) {
            System.out.println("Value: " + optional1.get());
        }

        // 3. ifPresent() - Better approach
        optional1.ifPresent(value -> System.out.println("ifPresent: " + value));

        // 4. orElse() - Default value
        String result1 = optional2.orElse("Default Value");
        System.out.println("orElse: " + result1);

        // 5. orElseGet() - Lazy evaluation
        String result2 = optional2.orElseGet(() -> "Computed Default");
        System.out.println("orElseGet: " + result2);

        // 6. orElseThrow()
        try {
            String result3 = optional2.orElseThrow(() -> new RuntimeException("Value not present"));
        } catch (RuntimeException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        // 7. map()
        Optional<Integer> length = optional1.map(String::length);
        System.out.println("Length: " + length.orElse(0));

        // 8. filter()
        Optional<String> filtered = optional1.filter(s -> s.startsWith("H"));
        System.out.println("Filtered: " + filtered.orElse("No match"));

        // 9. flatMap()
        Optional<String> flattened = optional1.flatMap(s -> Optional.of(s.toUpperCase()));
        System.out.println("FlatMapped: " + flattened.orElse(""));

        // 10. Real-world usage
        String name = findUserById(1)
            .map(User::getName)
            .orElse("Unknown User");
        System.out.println("User name: " + name);
    }

    static Optional<User> findUserById(int id) {
        return id == 1 ? Optional.of(new User("John Doe")) : Optional.empty();
    }

    static class User {
        private String name;
        User(String name) { this.name = name; }
        String getName() { return name; }
    }
}
