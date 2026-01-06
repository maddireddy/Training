/**
 * FlatMap Operations
 * Demonstrates: flatMap(), flatMapToInt(), nested collections
 */
package stream_api;

import java.util.*;
import java.util.stream.*;

public class Example10_FlatMapOperations {

    public static void main(String[] args) {
        System.out.println("=== FlatMap Operations ===\n");

        // 1. Basic flatMap
        demonstrateBasicFlatMap();

        // 2. map vs flatMap
        demonstrateMapVsFlatMap();

        // 3. Flattening nested collections
        demonstrateNestedCollections();

        // 4. flatMapToInt/Long/Double
        demonstrateFlatMapPrimitives();

        // 5. Real-world flatMap scenarios
        demonstrateRealWorldScenarios();
    }

    /**
     * Basic flatMap usage
     */
    static void demonstrateBasicFlatMap() {
        System.out.println("=== Basic FlatMap ===");

        // List of lists
        List<List<Integer>> listOfLists = Arrays.asList(
            Arrays.asList(1, 2, 3),
            Arrays.asList(4, 5),
            Arrays.asList(6, 7, 8, 9)
        );

        // Flatten to single stream
        List<Integer> flattened = listOfLists.stream()
            .flatMap(List::stream)
            .toList();

        System.out.println("Original: " + listOfLists);
        System.out.println("Flattened: " + flattened);

        // Split strings and flatten
        List<String> sentences = Arrays.asList(
            "Hello World",
            "Java Streams",
            "FlatMap Example"
        );

        List<String> words = sentences.stream()
            .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
            .toList();

        System.out.println("\nSentences: " + sentences);
        System.out.println("Words: " + words);
    }

    /**
     * map vs flatMap
     */
    static void demonstrateMapVsFlatMap() {
        System.out.println("\n=== map vs flatMap ===");

        List<String> words = Arrays.asList("Hello", "World");

        // map: Stream<Stream<String>>
        System.out.println("Using map (returns Stream of Streams):");
        Stream<Stream<String>> mapResult = words.stream()
            .map(word -> Arrays.stream(word.split("")));
        // This is not what we want!

        // flatMap: Stream<String>
        System.out.println("Using flatMap (returns flat Stream):");
        List<String> letters = words.stream()
            .flatMap(word -> Arrays.stream(word.split("")))
            .toList();
        System.out.println("Letters: " + letters);

        // Another example
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        // map returns List<List<Integer>>
        List<List<Integer>> mapSquareAndCube = numbers.stream()
            .map(n -> Arrays.asList(n * n, n * n * n))
            .toList();
        System.out.println("\nmap result: " + mapSquareAndCube);

        // flatMap returns List<Integer>
        List<Integer> flatMapSquareAndCube = numbers.stream()
            .flatMap(n -> Arrays.asList(n * n, n * n * n).stream())
            .toList();
        System.out.println("flatMap result: " + flatMapSquareAndCube);
    }

    /**
     * Flattening nested collections
     */
    static void demonstrateNestedCollections() {
        System.out.println("\n=== Nested Collections ===");

        // Create departments with employees
        List<Department> departments = Arrays.asList(
            new Department("Engineering", Arrays.asList(
                new Employee("Alice", 50000),
                new Employee("Bob", 60000)
            )),
            new Department("Sales", Arrays.asList(
                new Employee("Charlie", 45000),
                new Employee("David", 55000)
            )),
            new Department("HR", Arrays.asList(
                new Employee("Eve", 40000)
            ))
        );

        // Get all employees from all departments
        List<Employee> allEmployees = departments.stream()
            .flatMap(dept -> dept.getEmployees().stream())
            .toList();

        System.out.println("All employees:");
        allEmployees.forEach(System.out::println);

        // Get all employee names
        List<String> allNames = departments.stream()
            .flatMap(dept -> dept.getEmployees().stream())
            .map(Employee::getName)
            .toList();

        System.out.println("\nAll names: " + allNames);

        // Total salary across all departments
        double totalSalary = departments.stream()
            .flatMap(dept -> dept.getEmployees().stream())
            .mapToDouble(Employee::getSalary)
            .sum();

        System.out.println("\nTotal salary: $" + totalSalary);

        // Employees with salary > 50000
        List<Employee> highPaid = departments.stream()
            .flatMap(dept -> dept.getEmployees().stream())
            .filter(emp -> emp.getSalary() > 50000)
            .toList();

        System.out.println("\nHigh paid employees:");
        highPaid.forEach(System.out::println);
    }

    /**
     * flatMapToInt/Long/Double
     */
    static void demonstrateFlatMapPrimitives() {
        System.out.println("\n=== FlatMap Primitives ===");

        List<Order> orders = Arrays.asList(
            new Order(1, Arrays.asList(10, 20, 30)),
            new Order(2, Arrays.asList(15, 25)),
            new Order(3, Arrays.asList(5, 10, 15, 20))
        );

        // flatMapToInt
        int totalItems = orders.stream()
            .flatMapToInt(order -> order.getItemPrices().stream()
                .mapToInt(Integer::intValue))
            .sum();

        System.out.println("Total value: $" + totalItems);

        // Count all items
        long itemCount = orders.stream()
            .flatMapToInt(order -> order.getItemPrices().stream()
                .mapToInt(Integer::intValue))
            .count();

        System.out.println("Total items: " + itemCount);

        // Average item price
        double avgPrice = orders.stream()
            .flatMapToInt(order -> order.getItemPrices().stream()
                .mapToInt(Integer::intValue))
            .average()
            .orElse(0.0);

        System.out.println("Average price: $" + avgPrice);
    }

    /**
     * Real-world scenarios
     */
    static void demonstrateRealWorldScenarios() {
        System.out.println("\n=== Real-World Scenarios ===");

        // Scenario 1: Extract all tags from blog posts
        List<BlogPost> posts = Arrays.asList(
            new BlogPost("Java Tutorial", Arrays.asList("java", "programming", "tutorial")),
            new BlogPost("Python Basics", Arrays.asList("python", "programming", "basics")),
            new BlogPost("Java Streams", Arrays.asList("java", "streams", "functional"))
        );

        List<String> allTags = posts.stream()
            .flatMap(post -> post.getTags().stream())
            .distinct()
            .sorted()
            .toList();

        System.out.println("All unique tags: " + allTags);

        // Scenario 2: Find all courses by students
        List<Student> students = Arrays.asList(
            new Student("Alice", Arrays.asList("Math", "Physics", "Chemistry")),
            new Student("Bob", Arrays.asList("Math", "Biology")),
            new Student("Charlie", Arrays.asList("Physics", "Biology", "English"))
        );

        List<String> allCourses = students.stream()
            .flatMap(student -> student.getCourses().stream())
            .distinct()
            .sorted()
            .toList();

        System.out.println("\nAll courses: " + allCourses);

        // Count occurrences
        Map<String, Long> courseCount = students.stream()
            .flatMap(student -> student.getCourses().stream())
            .collect(Collectors.groupingBy(
                course -> course,
                Collectors.counting()
            ));

        System.out.println("\nCourse enrollment:");
        courseCount.forEach((course, count) ->
            System.out.println("  " + course + ": " + count + " students"));

        // Scenario 3: Matrix operations
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        List<Integer> allElements = Arrays.stream(matrix)
            .flatMapToInt(Arrays::stream)
            .boxed()
            .toList();

        System.out.println("\nMatrix elements: " + allElements);

        int sum = Arrays.stream(matrix)
            .flatMapToInt(Arrays::stream)
            .sum();

        System.out.println("Matrix sum: " + sum);
    }
}

class Department {
    private String name;
    private List<Employee> employees;

    public Department(String name, List<Employee> employees) {
        this.name = name;
        this.employees = employees;
    }

    public String getName() { return name; }
    public List<Employee> getEmployees() { return employees; }
}

class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() { return name; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return name + " ($" + salary + ")";
    }
}

class Order {
    private int id;
    private List<Integer> itemPrices;

    public Order(int id, List<Integer> itemPrices) {
        this.id = id;
        this.itemPrices = itemPrices;
    }

    public int getId() { return id; }
    public List<Integer> getItemPrices() { return itemPrices; }
}

class BlogPost {
    private String title;
    private List<String> tags;

    public BlogPost(String title, List<String> tags) {
        this.title = title;
        this.tags = tags;
    }

    public String getTitle() { return title; }
    public List<String> getTags() { return tags; }
}

class Student {
    private String name;
    private List<String> courses;

    public Student(String name, List<String> courses) {
        this.name = name;
        this.courses = courses;
    }

    public String getName() { return name; }
    public List<String> getCourses() { return courses; }
}

/*
 * FlatMap Summary:
 *
 * 1. WHAT IS FLATMAP:
 *    - Flattens nested structures into single stream
 *    - Each element -> Stream -> flattened into one stream
 *    - Signature: <R> Stream<R> flatMap(Function<T, Stream<R>> mapper)
 *
 * 2. MAP VS FLATMAP:
 *    map:     Stream<T> -> Stream<R>
 *    flatMap: Stream<T> -> Stream<Stream<R>> -> Stream<R>
 *
 *    Example:
 *    map:     [[1,2], [3,4]] -> [Stream<1,2>, Stream<3,4>]
 *    flatMap: [[1,2], [3,4]] -> [1, 2, 3, 4]
 *
 * 3. VARIANTS:
 *    - flatMap(Function<T, Stream<R>>)
 *    - flatMapToInt(Function<T, IntStream>)
 *    - flatMapToLong(Function<T, LongStream>)
 *    - flatMapToDouble(Function<T, DoubleStream>)
 *
 * 4. COMMON USE CASES:
 *    - Flatten nested collections
 *    - Split strings into words/characters
 *    - Extract elements from objects
 *    - Combine multiple streams
 *    - Process hierarchical data
 *
 * 5. PATTERNS:
 *
 *    // Flatten List<List<T>>
 *    listOfLists.stream()
 *        .flatMap(List::stream)
 *
 *    // Split and flatten strings
 *    strings.stream()
 *        .flatMap(s -> Arrays.stream(s.split(" ")))
 *
 *    // Extract nested fields
 *    departments.stream()
 *        .flatMap(d -> d.getEmployees().stream())
 *
 *    // Optional to Stream (Java 9+)
 *    optionals.stream()
 *        .flatMap(Optional::stream)
 *
 * 6. WHEN TO USE:
 *    ✓ Multiple values per input element
 *    ✓ Nested collections
 *    ✓ Splitting strings
 *    ✓ Unwrapping wrappers
 *    ✗ One-to-one mapping (use map instead)
 *
 * 7. PERFORMANCE:
 *    - Lazy evaluation (like map)
 *    - Intermediate operation
 *    - No additional overhead vs manual flattening
 *    - Composable and chainable
 *
 * 8. EXAMPLES:
 *
 *    // Get all words from sentences
 *    sentences.stream()
 *        .flatMap(s -> Arrays.stream(s.split(" ")))
 *        .collect(Collectors.toList());
 *
 *    // Get all items from orders
 *    orders.stream()
 *        .flatMap(o -> o.getItems().stream())
 *        .collect(Collectors.toList());
 *
 *    // Flatten 2D array
 *    Arrays.stream(matrix)
 *        .flatMapToInt(Arrays::stream)
 *        .toArray();
 */
