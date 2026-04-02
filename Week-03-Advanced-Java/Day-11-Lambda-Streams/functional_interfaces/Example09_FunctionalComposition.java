/**
 * Functional Composition and Chaining
 * Demonstrates: andThen, compose, chaining functional interfaces
 */
package functional_interfaces;

import java.util.function.*;

public class Example09_FunctionalComposition {

    public static void main(String[] args) {
        System.out.println("=== Functional Composition ===\n");

        // 1. Function composition
        demonstrateFunctionComposition();

        // 2. Predicate composition
        demonstratePredicateComposition();

        // 3. Consumer composition
        demonstrateConsumerComposition();

        // 4. Comparator composition
        demonstrateComparatorComposition();

        // 5. Real-world composition
        demonstrateRealWorldComposition();
    }

    /**
     * Function composition: andThen and compose
     */
    static void demonstrateFunctionComposition() {
        System.out.println("=== Function Composition ===");

        Function<Integer, Integer> multiplyBy2 = x -> x * 2;
        Function<Integer, Integer> add3 = x -> x + 3;
        Function<Integer, Integer> square = x -> x * x;

        // andThen: execute first, then second
        Function<Integer, Integer> multiplyThenAdd = multiplyBy2.andThen(add3);
        System.out.println("multiplyBy2.andThen(add3) with 5: " + multiplyThenAdd.apply(5));
        // Result: (5 * 2) + 3 = 13

        // compose: execute second first, then first
        Function<Integer, Integer> addThenMultiply = multiplyBy2.compose(add3);
        System.out.println("multiplyBy2.compose(add3) with 5: " + addThenMultiply.apply(5));
        // Result: (5 + 3) * 2 = 16

        // Chain multiple operations
        Function<Integer, Integer> complex = multiplyBy2
            .andThen(add3)
            .andThen(square);
        System.out.println("multiplyBy2 -> add3 -> square with 5: " + complex.apply(5));
        // Result: ((5 * 2) + 3)² = 169

        // String transformations
        Function<String, String> toLower = String::toLowerCase;
        Function<String, String> trim = String::trim;
        Function<String, String> addGreeting = s -> "Hello, " + s;

        Function<String, String> processName = trim
            .andThen(toLower)
            .andThen(addGreeting);

        System.out.println("\n" + processName.apply("  ALICE  "));
        // Result: "Hello, alice"
    }

    /**
     * Predicate composition: and, or, negate
     */
    static void demonstratePredicateComposition() {
        System.out.println("\n=== Predicate Composition ===");

        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<Integer> greaterThan10 = n -> n > 10;

        // and: both must be true
        Predicate<Integer> evenAndPositive = isEven.and(isPositive);
        System.out.println("10 is even AND positive: " + evenAndPositive.test(10));
        System.out.println("-4 is even AND positive: " + evenAndPositive.test(-4));

        // or: at least one must be true
        Predicate<Integer> evenOrGreaterThan10 = isEven.or(greaterThan10);
        System.out.println("15 is even OR >10: " + evenOrGreaterThan10.test(15));
        System.out.println("3 is even OR >10: " + evenOrGreaterThan10.test(3));

        // negate: reverse the predicate
        Predicate<Integer> isOdd = isEven.negate();
        System.out.println("5 is odd: " + isOdd.test(5));
        System.out.println("4 is odd: " + isOdd.test(4));

        // Complex composition
        Predicate<Integer> complex = isEven
            .and(isPositive)
            .and(greaterThan10);
        System.out.println("12 is even, positive, and >10: " + complex.test(12));
        System.out.println("8 is even, positive, and >10: " + complex.test(8));

        // String predicates
        Predicate<String> hasLength = s -> s.length() > 5;
        Predicate<String> startsWithA = s -> s.startsWith("A");
        Predicate<String> endsWithE = s -> s.endsWith("e");

        Predicate<String> validName = hasLength
            .and(startsWithA.or(endsWithE));

        System.out.println("\n\"Alice\" is valid: " + validName.test("Alice"));
        System.out.println("\"Bob\" is valid: " + validName.test("Bob"));
    }

    /**
     * Consumer composition: andThen
     */
    static void demonstrateConsumerComposition() {
        System.out.println("\n=== Consumer Composition ===");

        Consumer<String> print = s -> System.out.print(s);
        Consumer<String> addSpace = s -> System.out.print(" ");
        Consumer<String> addNewline = s -> System.out.println();

        // Combine consumers
        Consumer<String> printWithSpace = print.andThen(addSpace);
        printWithSpace.accept("Hello");
        printWithSpace.accept("World");
        addNewline.accept("");

        // Multiple chained consumers
        Consumer<Person> printName = p -> System.out.print("Name: " + p.getName());
        Consumer<Person> printAge = p -> System.out.print(", Age: " + p.getAge());
        Consumer<Person> printNewline = p -> System.out.println();

        Consumer<Person> printPerson = printName
            .andThen(printAge)
            .andThen(printNewline);

        Person person = new Person("Alice", 30);
        printPerson.accept(person);

        // Validation and logging
        Consumer<Order> validate = o -> {
            if (o.total < 0) throw new IllegalArgumentException("Invalid total");
        };
        Consumer<Order> log = o -> System.out.println("Processing order: " + o.id);
        Consumer<Order> process = o -> System.out.println("Order processed: $" + o.total);

        Consumer<Order> orderPipeline = validate
            .andThen(log)
            .andThen(process);

        Order order = new Order(1, 99.99);
        orderPipeline.accept(order);
    }

    /**
     * Comparator composition
     */
    static void demonstrateComparatorComposition() {
        System.out.println("\n=== Comparator Composition ===");

        java.util.List<Person> people = java.util.Arrays.asList(
            new Person("Alice", 30),
            new Person("Bob", 25),
            new Person("Alice", 25),
            new Person("Charlie", 30)
        );

        // Sort by name
        System.out.println("Sort by name:");
        people.stream()
            .sorted(java.util.Comparator.comparing(Person::getName))
            .forEach(System.out::println);

        // Sort by age
        System.out.println("\nSort by age:");
        people.stream()
            .sorted(java.util.Comparator.comparing(Person::getAge))
            .forEach(System.out::println);

        // thenComparing: secondary sort
        System.out.println("\nSort by name, then age:");
        people.stream()
            .sorted(java.util.Comparator.comparing(Person::getName)
                .thenComparing(Person::getAge))
            .forEach(System.out::println);

        // reversed: reverse order
        System.out.println("\nSort by age (descending):");
        people.stream()
            .sorted(java.util.Comparator.comparing(Person::getAge).reversed())
            .forEach(System.out::println);

        // Null-safe comparator
        java.util.List<Person> peopleWithNull = java.util.Arrays.asList(
            new Person("Alice", 30),
            null,
            new Person("Bob", 25)
        );

        System.out.println("\nNull-safe sort:");
        peopleWithNull.stream()
            .sorted(java.util.Comparator.nullsLast(
                java.util.Comparator.comparing(Person::getName)))
            .forEach(p -> System.out.println(p == null ? "null" : p));
    }

    /**
     * Real-world composition examples
     */
    static void demonstrateRealWorldComposition() {
        System.out.println("\n=== Real-World Composition ===");

        // Example 1: Data transformation pipeline
        Function<String, String> sanitize = s -> s.trim().toLowerCase();
        Function<String, String> removeSpecialChars = s -> s.replaceAll("[^a-z0-9]", "");
        Function<String, String> capitalize = s -> s.substring(0, 1).toUpperCase() + s.substring(1);

        Function<String, String> cleanUsername = sanitize
            .andThen(removeSpecialChars)
            .andThen(capitalize);

        System.out.println("Cleaned: " + cleanUsername.apply("  Alice@123!  "));

        // Example 2: Validation pipeline
        Predicate<User> hasValidEmail = u -> u.email != null && u.email.contains("@");
        Predicate<User> hasValidAge = u -> u.age >= 18 && u.age <= 100;
        Predicate<User> hasValidName = u -> u.name != null && u.name.length() >= 3;

        Predicate<User> isValidUser = hasValidName
            .and(hasValidEmail)
            .and(hasValidAge);

        User user1 = new User("Alice", "alice@example.com", 25);
        User user2 = new User("Bo", "invalid-email", 15);

        System.out.println("\nUser1 valid: " + isValidUser.test(user1));
        System.out.println("User2 valid: " + isValidUser.test(user2));

        // Example 3: Price calculation pipeline
        Function<Double, Double> applyDiscount = price -> price * 0.9;  // 10% off
        Function<Double, Double> applyTax = price -> price * 1.08;      // 8% tax
        Function<Double, Double> roundTo2Decimals = price ->
            Math.round(price * 100.0) / 100.0;

        Function<Double, Double> calculateFinalPrice = applyDiscount
            .andThen(applyTax)
            .andThen(roundTo2Decimals);

        System.out.println("\nOriginal: $100.00");
        System.out.println("Final: $" + calculateFinalPrice.apply(100.0));

        // Example 4: Processing pipeline with logging
        Consumer<String> logStart = s -> System.out.println("Start: " + s);
        Consumer<String> logEnd = s -> System.out.println("End: " + s);
        Consumer<String> actualProcessing = s -> {
            // Simulate processing
            System.out.println("Processing: " + s);
        };

        Consumer<String> fullPipeline = logStart
            .andThen(actualProcessing)
            .andThen(logEnd);

        System.out.println("\nProcessing pipeline:");
        fullPipeline.accept("Data Item");
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}

class Order {
    int id;
    double total;

    public Order(int id, double total) {
        this.id = id;
        this.total = total;
    }
}

class User {
    String name;
    String email;
    int age;

    public User(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
}

/*
 * Functional Composition Summary:
 *
 * 1. FUNCTION COMPOSITION:
 *    - andThen(Function): Execute first, then second
 *      f.andThen(g) = g(f(x))
 *
 *    - compose(Function): Execute second first, then first
 *      f.compose(g) = f(g(x))
 *
 *    - identity(): Returns input unchanged
 *      Function.identity() = x -> x
 *
 * 2. PREDICATE COMPOSITION:
 *    - and(Predicate): Both must be true (&&)
 *      p1.and(p2) = p1(x) && p2(x)
 *
 *    - or(Predicate): At least one must be true (||)
 *      p1.or(p2) = p1(x) || p2(x)
 *
 *    - negate(): Reverse the predicate (!)
 *      p.negate() = !p(x)
 *
 *    - isEqual(Object): Check equality
 *      Predicate.isEqual(obj) = x -> x.equals(obj)
 *
 * 3. CONSUMER COMPOSITION:
 *    - andThen(Consumer): Execute both in sequence
 *      c1.andThen(c2) = { c1(x); c2(x); }
 *
 * 4. COMPARATOR COMPOSITION:
 *    - comparing(Function): Create comparator
 *    - thenComparing(Function): Secondary sort
 *    - reversed(): Reverse order
 *    - nullsFirst(), nullsLast(): Handle nulls
 *
 * 5. COMPOSITION BENEFITS:
 *    ✓ Build complex logic from simple pieces
 *    ✓ Reusable components
 *    ✓ Improved readability
 *    ✓ Better testability
 *    ✓ Functional programming style
 *
 * 6. COMPOSITION PATTERNS:
 *
 *    // Data transformation
 *    Function<Input, Output> pipeline = step1
 *        .andThen(step2)
 *        .andThen(step3);
 *
 *    // Validation
 *    Predicate<T> validator = rule1
 *        .and(rule2)
 *        .and(rule3);
 *
 *    // Processing
 *    Consumer<T> processor = validate
 *        .andThen(transform)
 *        .andThen(save);
 *
 * 7. BEST PRACTICES:
 *    ✓ Keep individual functions simple and focused
 *    ✓ Name functions descriptively
 *    ✓ Compose for clarity, not cleverness
 *    ✓ Test individual pieces before composing
 *    ✓ Use method references when possible
 *
 * 8. COMMON USE CASES:
 *    - Data transformation pipelines
 *    - Validation chains
 *    - Logging and monitoring
 *    - Complex sorting
 *    - Business rule composition
 *    - Processing workflows
 */
