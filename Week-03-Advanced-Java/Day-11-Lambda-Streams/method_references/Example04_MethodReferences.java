/**
 * Method References - All Types
 * Demonstrates: Static, Instance, Constructor, and Arbitrary Object method references
 */
package method_references;

import java.util.*;
import java.util.function.*;

public class Example04_MethodReferences {

    public static void main(String[] args) {
        System.out.println("=== Method References Demonstration ===\n");

        // 1. Static method reference
        demonstrateStaticMethodReference();

        // 2. Instance method reference on specific object
        demonstrateInstanceMethodReference();

        // 3. Instance method reference on arbitrary object
        demonstrateArbitraryObjectMethodReference();

        // 4. Constructor reference
        demonstrateConstructorReference();

        // 5. Array constructor reference
        demonstrateArrayConstructorReference();

        // 6. Comparing lambda vs method reference
        compareLambdaVsMethodReference();
    }

    /**
     * Type 1: Static Method Reference
     * Syntax: ClassName::staticMethodName
     */
    static void demonstrateStaticMethodReference() {
        System.out.println("=== Static Method Reference ===");

        // Lambda way
        Function<String, Integer> lambda = str -> Integer.parseInt(str);
        System.out.println("Lambda: " + lambda.apply("123"));

        // Method reference way
        Function<String, Integer> methodRef = Integer::parseInt;
        System.out.println("Method Ref: " + methodRef.apply("456"));

        // More examples
        List<String> numbers = Arrays.asList("1", "2", "3", "4", "5");

        // Using static method reference with map
        List<Integer> integers = numbers.stream()
            .map(Integer::parseInt)
            .toList();
        System.out.println("Parsed integers: " + integers);

        // Using static method reference with Math
        List<Double> values = Arrays.asList(-1.5, 2.3, -4.7, 5.1);
        List<Double> absolutes = values.stream()
            .map(Math::abs)
            .toList();
        System.out.println("Absolute values: " + absolutes);
    }

    /**
     * Type 2: Instance Method Reference (on specific object)
     * Syntax: objectReference::instanceMethodName
     */
    static void demonstrateInstanceMethodReference() {
        System.out.println("\n=== Instance Method Reference ===");

        String prefix = "Number: ";

        // Lambda way
        Function<Integer, String> lambda = num -> prefix + num;
        System.out.println("Lambda: " + lambda.apply(42));

        // Instance method reference
        Printer printer = new Printer("Item: ");
        Function<String, String> methodRef = printer::format;
        System.out.println("Method Ref: " + methodRef.apply("Book"));

        // Using with forEach
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        System.out.print("Names: ");
        names.forEach(System.out::print);  // Instance method of System.out
        System.out.println();

        // Using StringBuilder instance
        StringBuilder sb = new StringBuilder();
        Consumer<String> appender = sb::append;
        names.forEach(appender);
        System.out.println("Concatenated: " + sb);
    }

    /**
     * Type 3: Instance Method Reference (on arbitrary object of specific type)
     * Syntax: ClassName::instanceMethodName
     */
    static void demonstrateArbitraryObjectMethodReference() {
        System.out.println("\n=== Arbitrary Object Method Reference ===");

        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");

        // Lambda way
        words.stream()
            .map(str -> str.toUpperCase())
            .forEach(System.out::println);

        System.out.println();

        // Method reference way
        words.stream()
            .map(String::toUpperCase)  // Arbitrary object method reference
            .forEach(System.out::println);

        // Sorting using compareTo
        List<String> names = Arrays.asList("Charlie", "Alice", "Bob");
        names.sort(String::compareToIgnoreCase);  // Arbitrary object method
        System.out.println("\nSorted names: " + names);

        // Using with Person objects
        List<Person> people = Arrays.asList(
            new Person("Alice", 30),
            new Person("Bob", 25),
            new Person("Charlie", 35)
        );

        List<String> personNames = people.stream()
            .map(Person::getName)  // Arbitrary object method reference
            .toList();
        System.out.println("Person names: " + personNames);
    }

    /**
     * Type 4: Constructor Reference
     * Syntax: ClassName::new
     */
    static void demonstrateConstructorReference() {
        System.out.println("\n=== Constructor Reference ===");

        // Lambda way
        Function<String, Person> lambda = name -> new Person(name);
        Person p1 = lambda.apply("Alice");
        System.out.println("Lambda: " + p1);

        // Constructor reference way
        Function<String, Person> constructorRef = Person::new;
        Person p2 = constructorRef.apply("Bob");
        System.out.println("Constructor Ref: " + p2);

        // Using with BiFunction (2 parameters)
        BiFunction<String, Integer, Person> personCreator = Person::new;
        Person p3 = personCreator.apply("Charlie", 30);
        System.out.println("Created: " + p3);

        // Creating list of objects
        List<String> names = Arrays.asList("David", "Eve", "Frank");
        List<Person> persons = names.stream()
            .map(Person::new)
            .toList();
        System.out.println("Persons created: " + persons);
    }

    /**
     * Array Constructor Reference
     * Syntax: Type[]::new
     */
    static void demonstrateArrayConstructorReference() {
        System.out.println("\n=== Array Constructor Reference ===");

        // Lambda way
        IntFunction<String[]> lambda = size -> new String[size];
        String[] array1 = lambda.apply(5);
        System.out.println("Array created (lambda): length = " + array1.length);

        // Array constructor reference
        IntFunction<String[]> arrayConstructor = String[]::new;
        String[] array2 = arrayConstructor.apply(10);
        System.out.println("Array created (ref): length = " + array2.length);

        // Using with toArray()
        List<String> list = Arrays.asList("A", "B", "C", "D");
        String[] array3 = list.stream()
            .toArray(String[]::new);
        System.out.println("Array from stream: " + Arrays.toString(array3));

        // Creating array of custom objects
        List<Person> people = Arrays.asList(
            new Person("Alice", 30),
            new Person("Bob", 25)
        );
        Person[] personArray = people.toArray(Person[]::new);
        System.out.println("Person array: " + Arrays.toString(personArray));
    }

    /**
     * Comparing Lambda vs Method Reference
     */
    static void compareLambdaVsMethodReference() {
        System.out.println("\n=== Lambda vs Method Reference Comparison ===");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Example 1: Static method
        System.out.println("\n1. Static Method:");
        System.out.println("Lambda:     numbers.forEach(n -> System.out.println(n))");
        System.out.println("Method Ref: numbers.forEach(System.out::println)");

        // Example 2: Instance method
        System.out.println("\n2. Instance Method:");
        System.out.println("Lambda:     str -> str.length()");
        System.out.println("Method Ref: String::length");

        // Example 3: Constructor
        System.out.println("\n3. Constructor:");
        System.out.println("Lambda:     name -> new Person(name)");
        System.out.println("Method Ref: Person::new");

        System.out.println("\nBoth are functionally equivalent!");
        System.out.println("Method references are more concise when applicable.");
    }

    // Helper method
    static String formatNumber(int num) {
        return "Number: " + num;
    }
}

class Person {
    private String name;
    private int age;

    // Constructor with single parameter
    public Person(String name) {
        this.name = name;
        this.age = 0;
    }

    // Constructor with two parameters
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}

class Printer {
    private String prefix;

    public Printer(String prefix) {
        this.prefix = prefix;
    }

    public String format(String text) {
        return prefix + text;
    }
}

/*
 * Method Reference Summary:
 *
 * 1. FOUR TYPES OF METHOD REFERENCES:
 *
 *    a) Static Method Reference
 *       Syntax: ClassName::staticMethod
 *       Example: Integer::parseInt
 *       Lambda: str -> Integer.parseInt(str)
 *
 *    b) Instance Method (on specific object)
 *       Syntax: object::instanceMethod
 *       Example: System.out::println
 *       Lambda: x -> System.out.println(x)
 *
 *    c) Instance Method (on arbitrary object)
 *       Syntax: ClassName::instanceMethod
 *       Example: String::toUpperCase
 *       Lambda: str -> str.toUpperCase()
 *
 *    d) Constructor Reference
 *       Syntax: ClassName::new
 *       Example: ArrayList::new
 *       Lambda: () -> new ArrayList()
 *
 * 2. WHEN TO USE METHOD REFERENCES:
 *    ✓ Method already exists
 *    ✓ Simple one-to-one mapping
 *    ✓ Improved readability
 *    ✗ Complex logic needed
 *    ✗ Multiple statements
 *    ✗ Different parameter handling
 *
 * 3. ADVANTAGES:
 *    - More concise than lambda
 *    - Better readability
 *    - Reuses existing methods
 *    - Less boilerplate
 *
 * 4. COMMON PATTERNS:
 *    - map(String::toUpperCase)
 *    - forEach(System.out::println)
 *    - filter(Objects::nonNull)
 *    - map(Integer::parseInt)
 *    - sorted(String::compareToIgnoreCase)
 *    - toArray(String[]::new)
 */
