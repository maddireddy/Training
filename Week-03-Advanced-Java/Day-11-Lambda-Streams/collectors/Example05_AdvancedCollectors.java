/**
 * Advanced Collectors
 * Demonstrates: groupingBy, partitioningBy, custom collectors, downstream collectors
 */
package collectors;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Example05_AdvancedCollectors {

    public static void main(String[] args) {
        System.out.println("=== Advanced Collectors Demonstration ===\n");

        // 1. Basic collectors
        demonstrateBasicCollectors();

        // 2. groupingBy
        demonstrateGroupingBy();

        // 3. partitioningBy
        demonstratePartitioningBy();

        // 4. Downstream collectors
        demonstrateDownstreamCollectors();

        // 5. Joining and summarizing
        demonstrateJoiningAndSummarizing();

        // 6. Custom collectors
        demonstrateCustomCollectors();

        // 7. Collectors composition
        demonstrateCollectorsComposition();
    }

    /**
     * Basic Collectors
     */
    static void demonstrateBasicCollectors() {
        System.out.println("=== Basic Collectors ===");

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");

        // toList()
        List<String> list = names.stream()
            .filter(name -> name.length() > 3)
            .collect(Collectors.toList());
        System.out.println("toList: " + list);

        // toSet()
        Set<Integer> lengths = names.stream()
            .map(String::length)
            .collect(Collectors.toSet());
        System.out.println("toSet (lengths): " + lengths);

        // toMap()
        Map<String, Integer> nameToLength = names.stream()
            .collect(Collectors.toMap(
                name -> name,           // key
                name -> name.length()   // value
            ));
        System.out.println("toMap: " + nameToLength);

        // toCollection() - specific collection type
        TreeSet<String> treeSet = names.stream()
            .collect(Collectors.toCollection(TreeSet::new));
        System.out.println("toCollection (TreeSet): " + treeSet);
    }

    /**
     * groupingBy - Group elements by classifier
     */
    static void demonstrateGroupingBy() {
        System.out.println("\n=== groupingBy ===");

        List<Person> people = Arrays.asList(
            new Person("Alice", 25, "Engineering"),
            new Person("Bob", 30, "Sales"),
            new Person("Charlie", 25, "Engineering"),
            new Person("David", 35, "Sales"),
            new Person("Eve", 30, "HR")
        );

        // Group by department
        Map<String, List<Person>> byDepartment = people.stream()
            .collect(Collectors.groupingBy(Person::getDepartment));

        System.out.println("Grouped by department:");
        byDepartment.forEach((dept, persons) ->
            System.out.println("  " + dept + ": " + persons));

        // Group by age
        Map<Integer, List<Person>> byAge = people.stream()
            .collect(Collectors.groupingBy(Person::getAge));

        System.out.println("\nGrouped by age:");
        byAge.forEach((age, persons) ->
            System.out.println("  " + age + ": " + persons));

        // Count by department
        Map<String, Long> countByDept = people.stream()
            .collect(Collectors.groupingBy(
                Person::getDepartment,
                Collectors.counting()
            ));

        System.out.println("\nCount by department:");
        countByDept.forEach((dept, count) ->
            System.out.println("  " + dept + ": " + count));
    }

    /**
     * partitioningBy - Split into two groups (true/false)
     */
    static void demonstratePartitioningBy() {
        System.out.println("\n=== partitioningBy ===");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Partition into even and odd
        Map<Boolean, List<Integer>> evenOdd = numbers.stream()
            .collect(Collectors.partitioningBy(n -> n % 2 == 0));

        System.out.println("Even: " + evenOdd.get(true));
        System.out.println("Odd: " + evenOdd.get(false));

        // Partition people by age
        List<Person> people = Arrays.asList(
            new Person("Alice", 25, "Engineering"),
            new Person("Bob", 30, "Sales"),
            new Person("Charlie", 17, "Intern"),
            new Person("David", 35, "Sales")
        );

        Map<Boolean, List<Person>> adults = people.stream()
            .collect(Collectors.partitioningBy(p -> p.getAge() >= 18));

        System.out.println("\nAdults: " + adults.get(true));
        System.out.println("Minors: " + adults.get(false));

        // Partition with counting
        Map<Boolean, Long> adultCount = people.stream()
            .collect(Collectors.partitioningBy(
                p -> p.getAge() >= 18,
                Collectors.counting()
            ));

        System.out.println("\nAdult count: " + adultCount.get(true));
        System.out.println("Minor count: " + adultCount.get(false));
    }

    /**
     * Downstream Collectors
     */
    static void demonstrateDownstreamCollectors() {
        System.out.println("\n=== Downstream Collectors ===");

        List<Person> people = Arrays.asList(
            new Person("Alice", 25, "Engineering"),
            new Person("Bob", 30, "Sales"),
            new Person("Charlie", 35, "Engineering"),
            new Person("David", 28, "Sales"),
            new Person("Eve", 32, "Engineering")
        );

        // Group by department and get names
        Map<String, List<String>> namesByDept = people.stream()
            .collect(Collectors.groupingBy(
                Person::getDepartment,
                Collectors.mapping(Person::getName, Collectors.toList())
            ));

        System.out.println("Names by department:");
        namesByDept.forEach((dept, names) ->
            System.out.println("  " + dept + ": " + names));

        // Group by department and calculate average age
        Map<String, Double> avgAgeByDept = people.stream()
            .collect(Collectors.groupingBy(
                Person::getDepartment,
                Collectors.averagingInt(Person::getAge)
            ));

        System.out.println("\nAverage age by department:");
        avgAgeByDept.forEach((dept, avg) ->
            System.out.printf("  %s: %.1f%n", dept, avg));

        // Group by department and get oldest person
        Map<String, Optional<Person>> oldestByDept = people.stream()
            .collect(Collectors.groupingBy(
                Person::getDepartment,
                Collectors.maxBy(Comparator.comparing(Person::getAge))
            ));

        System.out.println("\nOldest person by department:");
        oldestByDept.forEach((dept, person) ->
            System.out.println("  " + dept + ": " + person.orElse(null)));
    }

    /**
     * Joining and Summarizing
     */
    static void demonstrateJoiningAndSummarizing() {
        System.out.println("\n=== Joining and Summarizing ===");

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        // joining()
        String joined = names.stream()
            .collect(Collectors.joining());
        System.out.println("Joined: " + joined);

        // joining with delimiter
        String commaSeparated = names.stream()
            .collect(Collectors.joining(", "));
        System.out.println("Comma separated: " + commaSeparated);

        // joining with delimiter, prefix, and suffix
        String formatted = names.stream()
            .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("Formatted: " + formatted);

        // Summarizing int
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        IntSummaryStatistics stats = numbers.stream()
            .collect(Collectors.summarizingInt(Integer::intValue));

        System.out.println("\nSummary Statistics:");
        System.out.println("Count: " + stats.getCount());
        System.out.println("Sum: " + stats.getSum());
        System.out.println("Min: " + stats.getMin());
        System.out.println("Max: " + stats.getMax());
        System.out.println("Average: " + stats.getAverage());

        // summarizingDouble
        List<Double> prices = Arrays.asList(10.5, 20.3, 15.7, 30.2);
        DoubleSummaryStatistics priceStats = prices.stream()
            .collect(Collectors.summarizingDouble(Double::doubleValue));

        System.out.println("\nPrice Statistics:");
        System.out.printf("Total: $%.2f%n", priceStats.getSum());
        System.out.printf("Average: $%.2f%n", priceStats.getAverage());
    }

    /**
     * Custom Collectors
     */
    static void demonstrateCustomCollectors() {
        System.out.println("\n=== Custom Collectors ===");

        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");

        // Custom collector to join with line numbers
        String numbered = words.stream()
            .collect(Collector.of(
                () -> new StringJoiner("\n"),          // Supplier
                (joiner, word) -> {                     // Accumulator
                    int index = joiner.toString().split("\n").length;
                    if (joiner.length() == 0) {
                        joiner.add("1. " + word);
                    } else {
                        joiner.add((index + 1) + ". " + word);
                    }
                },
                StringJoiner::merge,                    // Combiner
                StringJoiner::toString                  // Finisher
            ));

        System.out.println("Numbered list:");
        System.out.println(numbered);

        // Custom collector to find min and max
        List<Integer> numbers = Arrays.asList(3, 7, 2, 9, 1, 5, 8);

        MinMax minMax = numbers.stream()
            .collect(Collector.of(
                MinMax::new,                            // Supplier
                MinMax::accept,                         // Accumulator
                MinMax::combine                         // Combiner
            ));

        System.out.println("\nMin: " + minMax.getMin());
        System.out.println("Max: " + minMax.getMax());
    }

    /**
     * Collectors Composition
     */
    static void demonstrateCollectorsComposition() {
        System.out.println("\n=== Collectors Composition ===");

        List<Person> people = Arrays.asList(
            new Person("Alice", 25, "Engineering"),
            new Person("Bob", 30, "Sales"),
            new Person("Charlie", 35, "Engineering"),
            new Person("David", 28, "Sales"),
            new Person("Eve", 32, "Engineering"),
            new Person("Frank", 27, "HR")
        );

        // Complex composition: Group by department, then by age range
        Map<String, Map<String, List<Person>>> complexGrouping = people.stream()
            .collect(Collectors.groupingBy(
                Person::getDepartment,
                Collectors.groupingBy(p -> {
                    if (p.getAge() < 30) return "20s";
                    else return "30s";
                })
            ));

        System.out.println("Grouped by department and age range:");
        complexGrouping.forEach((dept, ageGroups) -> {
            System.out.println(dept + ":");
            ageGroups.forEach((ageRange, persons) ->
                System.out.println("  " + ageRange + ": " + persons));
        });

        // Collect to Map with value transformation
        Map<String, String> deptEmployees = people.stream()
            .collect(Collectors.groupingBy(
                Person::getDepartment,
                Collectors.mapping(
                    Person::getName,
                    Collectors.joining(", ")
                )
            ));

        System.out.println("\nDepartment employees:");
        deptEmployees.forEach((dept, names) ->
            System.out.println(dept + ": " + names));
    }
}

class Person {
    private String name;
    private int age;
    private String department;

    public Person(String name, int age, String department) {
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getDepartment() { return department; }

    @Override
    public String toString() {
        return name + "(" + age + ")";
    }
}

class MinMax {
    private Integer min;
    private Integer max;

    public void accept(Integer value) {
        if (min == null || value < min) min = value;
        if (max == null || value > max) max = value;
    }

    public MinMax combine(MinMax other) {
        if (other.min != null) accept(other.min);
        if (other.max != null) accept(other.max);
        return this;
    }

    public Integer getMin() { return min; }
    public Integer getMax() { return max; }
}

/*
 * Collectors Summary:
 *
 * 1. BASIC COLLECTORS:
 *    - toList(), toSet(), toMap()
 *    - toCollection(Supplier)
 *    - counting()
 *
 * 2. GROUPING COLLECTORS:
 *    - groupingBy(Function)
 *    - groupingBy(Function, Collector)
 *    - groupingBy(Function, Supplier, Collector)
 *
 * 3. PARTITIONING COLLECTORS:
 *    - partitioningBy(Predicate)
 *    - partitioningBy(Predicate, Collector)
 *
 * 4. DOWNSTREAM COLLECTORS:
 *    - mapping(Function, Collector)
 *    - filtering(Predicate, Collector)
 *    - flatMapping(Function, Collector)
 *    - collectingAndThen(Collector, Function)
 *
 * 5. AGGREGATION COLLECTORS:
 *    - counting()
 *    - summingInt/Long/Double()
 *    - averagingInt/Long/Double()
 *    - summarizingInt/Long/Double()
 *    - maxBy(Comparator)
 *    - minBy(Comparator)
 *
 * 6. STRING COLLECTORS:
 *    - joining()
 *    - joining(delimiter)
 *    - joining(delimiter, prefix, suffix)
 *
 * 7. CUSTOM COLLECTOR:
 *    Collector.of(
 *      supplier,      // Create container
 *      accumulator,   // Add element
 *      combiner,      // Merge containers (parallel)
 *      finisher       // Transform result (optional)
 *    )
 *
 * 8. COMMON PATTERNS:
 *    - Group and count: groupingBy(f, counting())
 *    - Group and sum: groupingBy(f, summingInt())
 *    - Group and get names: groupingBy(f, mapping(g, toList()))
 *    - Partition and count: partitioningBy(p, counting())
 */
