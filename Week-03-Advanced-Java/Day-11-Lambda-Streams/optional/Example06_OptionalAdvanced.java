/**
 * Optional Advanced Patterns
 * Demonstrates: Optional usage, chaining, flatMap, orElse, best practices
 */
package optional;

import java.util.*;
import java.util.function.*;

public class Example06_OptionalAdvanced {

    public static void main(String[] args) {
        System.out.println("=== Optional Advanced Patterns ===\n");

        // 1. Creating Optional
        demonstrateCreatingOptional();

        // 2. Retrieving values
        demonstrateRetrievingValues();

        // 3. Conditional actions
        demonstrateConditionalActions();

        // 4. Transforming values
        demonstrateTransformingValues();

        // 5. Filtering values
        demonstrateFilteringValues();

        // 6. Chaining Optionals
        demonstrateChainingOptionals();

        // 7. Optional in real scenarios
        demonstrateRealWorldScenarios();

        // 8. Anti-patterns to avoid
        demonstrateAntiPatterns();
    }

    /**
     * Creating Optional
     */
    static void demonstrateCreatingOptional() {
        System.out.println("=== Creating Optional ===");

        // Empty Optional
        Optional<String> empty = Optional.empty();
        System.out.println("Empty: " + empty);

        // Optional with non-null value
        Optional<String> nonNull = Optional.of("Hello");
        System.out.println("Non-null: " + nonNull);

        // Optional that may be null
        String value = Math.random() > 0.5 ? "Value" : null;
        Optional<String> nullable = Optional.ofNullable(value);
        System.out.println("Nullable: " + nullable);

        // DON'T: Optional.of(null) throws NullPointerException
        try {
            Optional<String> bad = Optional.of(null);
        } catch (NullPointerException e) {
            System.out.println("Optional.of(null) throws NPE!");
        }
    }

    /**
     * Retrieving values from Optional
     */
    static void demonstrateRetrievingValues() {
        System.out.println("\n=== Retrieving Values ===");

        Optional<String> present = Optional.of("Hello");
        Optional<String> absent = Optional.empty();

        // get() - throws if empty (avoid!)
        System.out.println("get(): " + present.get());

        // orElse() - default value
        System.out.println("orElse(): " + absent.orElse("Default"));

        // orElseGet() - lazy default value
        System.out.println("orElseGet(): " + absent.orElseGet(() -> "Computed Default"));

        // orElseThrow() - custom exception
        try {
            absent.orElseThrow(() -> new IllegalStateException("Value missing!"));
        } catch (IllegalStateException e) {
            System.out.println("orElseThrow(): " + e.getMessage());
        }

        // isPresent() + get() pattern (old way)
        if (present.isPresent()) {
            System.out.println("Value: " + present.get());
        }

        // isEmpty() - Java 11+
        System.out.println("isEmpty(): " + absent.isEmpty());
    }

    /**
     * Conditional actions with Optional
     */
    static void demonstrateConditionalActions() {
        System.out.println("\n=== Conditional Actions ===");

        Optional<String> name = Optional.of("Alice");

        // ifPresent() - perform action if present
        name.ifPresent(value -> System.out.println("Hello, " + value));

        // ifPresentOrElse() - Java 9+
        Optional<String> maybeName = Optional.empty();
        maybeName.ifPresentOrElse(
            value -> System.out.println("Hello, " + value),
            () -> System.out.println("Hello, Guest")
        );

        // Using with method references
        Optional<String> message = Optional.of("Important message");
        message.ifPresent(System.out::println);
    }

    /**
     * Transforming values with map()
     */
    static void demonstrateTransformingValues() {
        System.out.println("\n=== Transforming Values ===");

        Optional<String> name = Optional.of("alice");

        // map() - transform value
        Optional<String> upper = name.map(String::toUpperCase);
        System.out.println("Uppercase: " + upper.orElse(""));

        // Chaining map operations
        Optional<Integer> length = name
            .map(String::trim)
            .map(String::toUpperCase)
            .map(String::length);
        System.out.println("Length: " + length.orElse(0));

        // map vs flatMap
        Optional<User> user = Optional.of(new User("Bob", "bob@example.com"));

        // map returns Optional<Optional<String>>
        Optional<Optional<String>> emailMap = user.map(User::getEmail);

        // flatMap returns Optional<String>
        Optional<String> emailFlatMap = user.flatMap(User::getEmail);
        System.out.println("Email: " + emailFlatMap.orElse("N/A"));
    }

    /**
     * Filtering Optional values
     */
    static void demonstrateFilteringValues() {
        System.out.println("\n=== Filtering Values ===");

        Optional<Integer> number = Optional.of(42);

        // filter() - keep value if predicate matches
        Optional<Integer> even = number.filter(n -> n % 2 == 0);
        System.out.println("Even number: " + even.orElse(null));

        Optional<Integer> greaterThan50 = number.filter(n -> n > 50);
        System.out.println("Greater than 50: " + greaterThan50.orElse(null));

        // Combining filter and map
        Optional<User> user = Optional.of(new User("Alice", "alice@example.com"));

        Optional<String> validEmail = user
            .flatMap(User::getEmail)
            .filter(email -> email.contains("@"))
            .map(String::toLowerCase);

        System.out.println("Valid email: " + validEmail.orElse("Invalid"));
    }

    /**
     * Chaining Optionals
     */
    static void demonstrateChainingOptionals() {
        System.out.println("\n=== Chaining Optionals ===");

        // flatMap for nested Optionals
        Optional<User> user = Optional.of(new User("Alice", "alice@example.com"));

        String email = user
            .flatMap(User::getEmail)
            .orElse("no-email@example.com");
        System.out.println("Email: " + email);

        // Multiple levels of Optional
        Optional<Company> company = Optional.of(new Company("TechCorp"));

        String ceoEmail = company
            .flatMap(Company::getCEO)
            .flatMap(User::getEmail)
            .orElse("N/A");
        System.out.println("CEO Email: " + ceoEmail);

        // or() - Java 9+ (alternative Optional supplier)
        Optional<String> primary = Optional.empty();
        Optional<String> secondary = Optional.of("Secondary");

        Optional<String> result = primary.or(() -> secondary);
        System.out.println("or() result: " + result.orElse("None"));
    }

    /**
     * Real-world scenarios
     */
    static void demonstrateRealWorldScenarios() {
        System.out.println("\n=== Real-World Scenarios ===");

        // Scenario 1: Repository pattern
        UserRepository repo = new UserRepository();

        Optional<User> user = repo.findById(1);
        user.ifPresent(u -> System.out.println("Found: " + u.getName()));

        // Scenario 2: Processing user email
        String emailDomain = repo.findById(1)
            .flatMap(User::getEmail)
            .map(email -> email.substring(email.indexOf('@') + 1))
            .orElse("unknown");
        System.out.println("Email domain: " + emailDomain);

        // Scenario 3: Default value with computation
        String username = repo.findById(999)
            .map(User::getName)
            .orElseGet(() -> {
                System.out.println("Computing default username...");
                return "guest_" + System.currentTimeMillis();
            });
        System.out.println("Username: " + username);

        // Scenario 4: Throwing custom exception
        try {
            User requiredUser = repo.findById(999)
                .orElseThrow(() -> new UserNotFoundException("User 999 not found"));
        } catch (UserNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Scenario 5: Stream of Optionals
        List<Integer> ids = Arrays.asList(1, 2, 999, 3);

        List<User> users = ids.stream()
            .map(repo::findById)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .toList();
        System.out.println("Found users: " + users.size());

        // Better with flatMap (Java 9+)
        List<User> usersFlat = ids.stream()
            .map(repo::findById)
            .flatMap(Optional::stream)
            .toList();
        System.out.println("Found users (flatMap): " + usersFlat.size());
    }

    /**
     * Anti-patterns to avoid
     */
    static void demonstrateAntiPatterns() {
        System.out.println("\n=== Anti-Patterns to Avoid ===");

        Optional<String> optional = Optional.of("value");

        // ❌ BAD: Using get() without checking
        System.out.println("❌ optional.get() - can throw NoSuchElementException");

        // ✓ GOOD: Use orElse, orElseGet, or orElseThrow
        System.out.println("✓ optional.orElse(\"default\")");

        // ❌ BAD: isPresent() + get()
        System.out.println("❌ if (opt.isPresent()) opt.get() - old style");

        // ✓ GOOD: Use ifPresent or map
        System.out.println("✓ optional.ifPresent(v -> ...)");

        // ❌ BAD: Optional as field
        System.out.println("❌ class User { Optional<String> email; } - avoid!");

        // ✓ GOOD: Return Optional from methods
        System.out.println("✓ Optional<User> findById(int id)");

        // ❌ BAD: Optional.of(null)
        System.out.println("❌ Optional.of(null) - throws NPE");

        // ✓ GOOD: Use ofNullable
        System.out.println("✓ Optional.ofNullable(value)");

        // ❌ BAD: Nested Optionals
        System.out.println("❌ Optional<Optional<String>> - confusing");

        // ✓ GOOD: Use flatMap
        System.out.println("✓ optional.flatMap(...)");
    }
}

class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }
}

class Company {
    private String name;
    private User ceo;

    public Company(String name) {
        this.name = name;
        this.ceo = null;  // No CEO set
    }

    public Optional<User> getCEO() {
        return Optional.ofNullable(ceo);
    }
}

class UserRepository {
    private Map<Integer, User> users = Map.of(
        1, new User("Alice", "alice@example.com"),
        2, new User("Bob", "bob@example.com"),
        3, new User("Charlie", null)
    );

    public Optional<User> findById(int id) {
        return Optional.ofNullable(users.get(id));
    }
}

class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}

/*
 * Optional Best Practices:
 *
 * 1. WHEN TO USE OPTIONAL:
 *    ✓ Return type from methods that may not return a value
 *    ✓ Chaining operations on potentially null values
 *    ✓ API design to indicate "no value" is valid
 *    ✗ Fields in classes (use null instead)
 *    ✗ Method parameters (use overloading or null)
 *    ✗ Collections (empty list is better than null)
 *
 * 2. CREATING OPTIONAL:
 *    - Optional.empty() - no value
 *    - Optional.of(value) - non-null value (throws if null)
 *    - Optional.ofNullable(value) - may be null
 *
 * 3. RETRIEVING VALUES:
 *    - orElse(defaultValue) - return default if empty
 *    - orElseGet(supplier) - lazy default (better performance)
 *    - orElseThrow(supplier) - custom exception
 *    - get() - AVOID! Throws NoSuchElementException
 *
 * 4. CONDITIONAL ACTIONS:
 *    - ifPresent(consumer) - action if present
 *    - ifPresentOrElse(consumer, runnable) - action or else (Java 9+)
 *
 * 5. TRANSFORMING:
 *    - map(function) - transform value
 *    - flatMap(function) - flatten nested Optionals
 *    - filter(predicate) - keep if matches
 *
 * 6. CHAINING:
 *    - or(supplier) - alternative Optional (Java 9+)
 *    - stream() - convert to Stream (Java 9+)
 *
 * 7. CHECKING:
 *    - isPresent() - has value
 *    - isEmpty() - no value (Java 11+)
 *
 * 8. COMMON PATTERNS:
 *    // Instead of null check:
 *    return Optional.ofNullable(user)
 *        .map(User::getEmail)
 *        .orElse("no-email@example.com");
 *
 *    // Instead of if-else:
 *    optional.ifPresentOrElse(
 *        value -> process(value),
 *        () -> handleEmpty()
 *    );
 *
 *    // Chaining:
 *    user.flatMap(User::getAddress)
 *        .flatMap(Address::getCity)
 *        .orElse("Unknown");
 */
