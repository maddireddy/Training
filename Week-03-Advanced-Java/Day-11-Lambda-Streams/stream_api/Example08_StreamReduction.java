/**
 * Stream Reduction Operations
 * Demonstrates: reduce(), collect(), min(), max(), sum(), count()
 */
package stream_api;

import java.util.*;
import java.util.stream.*;

public class Example08_StreamReduction {

    public static void main(String[] args) {
        System.out.println("=== Stream Reduction Operations ===\n");

        // 1. Basic reduce operations
        demonstrateBasicReduce();

        // 2. reduce() with identity and accumulator
        demonstrateReduceWithIdentity();

        // 3. reduce() with combiner (parallel)
        demonstrateReduceWithCombiner();

        // 4. Terminal reduction operations
        demonstrateTerminalOperations();

        // 5. Custom reduction
        demonstrateCustomReduction();

        // 6. Mutable reduction (collect)
        demonstrateMutableReduction();

        // 7. Real-world reduction examples
        demonstrateRealWorldExamples();
    }

    /**
     * Basic reduce operations
     */
    static void demonstrateBasicReduce() {
        System.out.println("=== Basic Reduce ===");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Sum using reduce
        Optional<Integer> sum = numbers.stream()
            .reduce((a, b) -> a + b);
        System.out.println("Sum: " + sum.orElse(0));

        // Same using Integer::sum method reference
        Optional<Integer> sum2 = numbers.stream()
            .reduce(Integer::sum);
        System.out.println("Sum (method ref): " + sum2.orElse(0));

        // Product
        Optional<Integer> product = numbers.stream()
            .reduce((a, b) -> a * b);
        System.out.println("Product: " + product.orElse(1));

        // Max
        Optional<Integer> max = numbers.stream()
            .reduce((a, b) -> a > b ? a : b);
        System.out.println("Max: " + max.orElse(0));

        // Same using Integer::max
        Optional<Integer> max2 = numbers.stream()
            .reduce(Integer::max);
        System.out.println("Max (method ref): " + max2.orElse(0));

        // Min
        Optional<Integer> min = numbers.stream()
            .reduce(Integer::min);
        System.out.println("Min: " + min.orElse(0));
    }

    /**
     * reduce() with identity value
     */
    static void demonstrateReduceWithIdentity() {
        System.out.println("\n=== Reduce with Identity ===");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Sum with identity (no Optional!)
        int sum = numbers.stream()
            .reduce(0, (a, b) -> a + b);
        System.out.println("Sum with identity: " + sum);

        // Product with identity
        int product = numbers.stream()
            .reduce(1, (a, b) -> a * b);
        System.out.println("Product with identity: " + product);

        // Concatenate strings
        List<String> words = Arrays.asList("Hello", "World", "from", "Java");

        String concatenated = words.stream()
            .reduce("", (a, b) -> a + " " + b);
        System.out.println("Concatenated: " + concatenated.trim());

        // Count elements (unusual but demonstrates reduce)
        long count = numbers.stream()
            .reduce(0, (total, element) -> total + 1, Integer::sum);
        System.out.println("Count using reduce: " + count);
    }

    /**
     * reduce() with combiner for parallel streams
     */
    static void demonstrateReduceWithCombiner() {
        System.out.println("\n=== Reduce with Combiner (Parallel) ===");

        List<Integer> numbers = IntStream.rangeClosed(1, 10).boxed().toList();

        // Three-argument reduce: identity, accumulator, combiner
        int sum = numbers.parallelStream()
            .reduce(
                0,                      // identity
                (subtotal, element) -> { // accumulator
                    System.out.println("Accumulator: " + subtotal + " + " + element +
                        " on " + Thread.currentThread().getName());
                    return subtotal + element;
                },
                (sum1, sum2) -> {       // combiner (for parallel)
                    System.out.println("Combiner: " + sum1 + " + " + sum2 +
                        " on " + Thread.currentThread().getName());
                    return sum1 + sum2;
                }
            );

        System.out.println("Parallel sum: " + sum);

        // Example: Sum of lengths
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");

        int totalLength = words.parallelStream()
            .reduce(
                0,
                (total, word) -> total + word.length(),  // accumulator
                Integer::sum                              // combiner
            );

        System.out.println("\nTotal length: " + totalLength);
    }

    /**
     * Terminal reduction operations
     */
    static void demonstrateTerminalOperations() {
        System.out.println("\n=== Terminal Reduction Operations ===");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // count()
        long count = numbers.stream()
            .filter(n -> n % 2 == 0)
            .count();
        System.out.println("Even count: " + count);

        // min()
        Optional<Integer> min = numbers.stream()
            .min(Integer::compareTo);
        System.out.println("Min: " + min.orElse(null));

        // max()
        Optional<Integer> max = numbers.stream()
            .max(Integer::compareTo);
        System.out.println("Max: " + max.orElse(null));

        // sum() (IntStream)
        int sum = numbers.stream()
            .mapToInt(Integer::intValue)
            .sum();
        System.out.println("Sum: " + sum);

        // average() (IntStream)
        OptionalDouble avg = numbers.stream()
            .mapToInt(Integer::intValue)
            .average();
        System.out.println("Average: " + avg.orElse(0.0));

        // summaryStatistics()
        IntSummaryStatistics stats = numbers.stream()
            .mapToInt(Integer::intValue)
            .summaryStatistics();

        System.out.println("\nSummary Statistics:");
        System.out.println("  Count: " + stats.getCount());
        System.out.println("  Sum: " + stats.getSum());
        System.out.println("  Min: " + stats.getMin());
        System.out.println("  Max: " + stats.getMax());
        System.out.println("  Average: " + stats.getAverage());
    }

    /**
     * Custom reduction logic
     */
    static void demonstrateCustomReduction() {
        System.out.println("\n=== Custom Reduction ===");

        List<Product> products = Arrays.asList(
            new Product("Laptop", 999.99, 5),
            new Product("Mouse", 29.99, 10),
            new Product("Keyboard", 79.99, 8),
            new Product("Monitor", 299.99, 3)
        );

        // Total value (price * quantity)
        double totalValue = products.stream()
            .map(p -> p.price * p.quantity)
            .reduce(0.0, Double::sum);
        System.out.printf("Total inventory value: $%.2f%n", totalValue);

        // Using reduce to find most expensive
        Optional<Product> mostExpensive = products.stream()
            .reduce((p1, p2) -> p1.price > p2.price ? p1 : p2);
        mostExpensive.ifPresent(p ->
            System.out.println("Most expensive: " + p.name + " ($" + p.price + ")"));

        // Custom aggregation: concatenate names
        String allProducts = products.stream()
            .map(p -> p.name)
            .reduce("Products: ", (a, b) -> a + b + ", ");
        System.out.println(allProducts);

        // Sum of squares
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sumOfSquares = numbers.stream()
            .map(n -> n * n)
            .reduce(0, Integer::sum);
        System.out.println("Sum of squares: " + sumOfSquares);
    }

    /**
     * Mutable reduction with collect()
     */
    static void demonstrateMutableReduction() {
        System.out.println("\n=== Mutable Reduction (collect) ===");

        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");

        // Collect to StringBuilder (mutable)
        StringBuilder sb = words.stream()
            .collect(
                StringBuilder::new,           // supplier
                (builder, word) -> builder    // accumulator
                    .append(word)
                    .append(", "),
                StringBuilder::append         // combiner
            );
        System.out.println("Collected: " + sb);

        // Collect to custom container
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        Statistics stats = numbers.stream()
            .collect(
                Statistics::new,
                Statistics::accept,
                Statistics::combine
            );

        System.out.println("Custom statistics:");
        System.out.println("  Count: " + stats.getCount());
        System.out.println("  Sum: " + stats.getSum());
        System.out.println("  Average: " + stats.getAverage());
    }

    /**
     * Real-world reduction examples
     */
    static void demonstrateRealWorldExamples() {
        System.out.println("\n=== Real-World Examples ===");

        List<Employee> employees = Arrays.asList(
            new Employee("Alice", 50000, "Engineering"),
            new Employee("Bob", 60000, "Sales"),
            new Employee("Charlie", 55000, "Engineering"),
            new Employee("David", 45000, "Sales"),
            new Employee("Eve", 70000, "Engineering")
        );

        // Total salary
        double totalSalary = employees.stream()
            .mapToDouble(Employee::getSalary)
            .sum();
        System.out.printf("Total salary: $%.2f%n", totalSalary);

        // Average salary
        double avgSalary = employees.stream()
            .mapToDouble(Employee::getSalary)
            .average()
            .orElse(0.0);
        System.out.printf("Average salary: $%.2f%n", avgSalary);

        // Highest paid employee
        Optional<Employee> highestPaid = employees.stream()
            .reduce((e1, e2) -> e1.salary > e2.salary ? e1 : e2);
        highestPaid.ifPresent(e ->
            System.out.println("Highest paid: " + e.name + " ($" + e.salary + ")"));

        // Department with most employees
        Map<String, Long> deptCount = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.counting()
            ));

        Optional<Map.Entry<String, Long>> maxDept = deptCount.entrySet().stream()
            .reduce((e1, e2) -> e1.getValue() > e2.getValue() ? e1 : e2);
        maxDept.ifPresent(e ->
            System.out.println("Largest department: " + e.getKey() + " (" + e.getValue() + " employees)"));

        // Concatenate all names
        String allNames = employees.stream()
            .map(Employee::getName)
            .reduce("", (a, b) -> a.isEmpty() ? b : a + ", " + b);
        System.out.println("All employees: " + allNames);
    }
}

class Product {
    String name;
    double price;
    int quantity;

    Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}

class Employee {
    String name;
    double salary;
    String department;

    Employee(String name, double salary, String department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public String getName() { return name; }
    public double getSalary() { return salary; }
    public String getDepartment() { return department; }
}

class Statistics {
    private int count = 0;
    private int sum = 0;

    public void accept(int value) {
        count++;
        sum += value;
    }

    public void combine(Statistics other) {
        count += other.count;
        sum += other.sum;
    }

    public int getCount() { return count; }
    public int getSum() { return sum; }
    public double getAverage() { return count > 0 ? (double) sum / count : 0; }
}

/*
 * Stream Reduction Summary:
 *
 * 1. REDUCTION CONCEPT:
 *    - Combine stream elements into single result
 *    - Terminal operation
 *    - Two types: immutable (reduce) and mutable (collect)
 *
 * 2. REDUCE VARIANTS:
 *
 *    a) Optional<T> reduce(BinaryOperator<T> accumulator)
 *       - No identity value
 *       - Returns Optional (empty if stream empty)
 *       Example: reduce((a, b) -> a + b)
 *
 *    b) T reduce(T identity, BinaryOperator<T> accumulator)
 *       - With identity value
 *       - Returns T (not Optional)
 *       Example: reduce(0, (a, b) -> a + b)
 *
 *    c) <U> U reduce(U identity, BiFunction<U,T,U> accumulator, BinaryOperator<U> combiner)
 *       - For parallel streams
 *       - Combiner merges partial results
 *       Example: reduce(0, (sum, n) -> sum + n, Integer::sum)
 *
 * 3. TERMINAL REDUCTION OPERATIONS:
 *    - count(): Count elements
 *    - min(Comparator): Minimum element
 *    - max(Comparator): Maximum element
 *    - sum(): Sum (primitive streams)
 *    - average(): Average (primitive streams)
 *    - summaryStatistics(): All stats at once
 *
 * 4. COLLECT (MUTABLE REDUCTION):
 *    collect(
 *      Supplier supplier,        // Create container
 *      BiConsumer accumulator,   // Add to container
 *      BiConsumer combiner       // Merge containers
 *    )
 *
 * 5. IDENTITY VALUE REQUIREMENTS:
 *    - Must be identity for accumulator: f(identity, x) = x
 *    - Examples:
 *      * Addition: 0 (0 + x = x)
 *      * Multiplication: 1 (1 * x = x)
 *      * String concat: "" ("" + x = x)
 *      * List append: empty list
 *
 * 6. ACCUMULATOR REQUIREMENTS:
 *    - Must be associative: f(f(a,b),c) = f(a,f(b,c))
 *    - Allows parallel execution
 *    - Examples: +, *, max, min
 *
 * 7. COMBINER REQUIREMENTS (PARALLEL):
 *    - Combines partial results
 *    - Must be associative and compatible
 *    - combiner.apply(identity, i) = i
 *
 * 8. COMMON PATTERNS:
 *
 *    // Sum
 *    reduce(0, Integer::sum)
 *
 *    // Product
 *    reduce(1, (a, b) -> a * b)
 *
 *    // String concatenation
 *    reduce("", (a, b) -> a + b)
 *
 *    // Max
 *    reduce(Integer::max)
 *
 *    // Custom aggregation
 *    reduce((a, b) -> custom_logic)
 *
 * 9. REDUCE VS COLLECT:
 *    - reduce: Immutable reduction (new value each time)
 *    - collect: Mutable reduction (modify container)
 *    - collect is more efficient for collections
 *
 * 10. BEST PRACTICES:
 *     ✓ Use specialized methods when available (sum, max, count)
 *     ✓ Use identity variant to avoid Optional
 *     ✓ Use collect for accumulating to collections
 *     ✓ Ensure associative operations for parallel
 *     ✗ Don't use reduce with mutable objects
 *     ✗ Avoid side effects in accumulator
 */
