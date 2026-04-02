/**
 * Parallel Streams
 * Demonstrates: Parallel processing, performance, thread safety, when to use
 */
package stream_api;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class Example07_ParallelStreams {

    public static void main(String[] args) {
        System.out.println("=== Parallel Streams Demonstration ===\n");

        // 1. Creating parallel streams
        demonstrateCreatingParallelStreams();

        // 2. Sequential vs Parallel comparison
        demonstrateSequentialVsParallel();

        // 3. Performance benefits
        demonstratePerformanceBenefits();

        // 4. Thread safety issues
        demonstrateThreadSafety();

        // 5. When to use parallel streams
        demonstrateWhenToUse();

        // 6. Fork/Join framework
        demonstrateForkJoin();

        // 7. Controlling parallelism
        demonstrateControllingParallelism();
    }

    /**
     * Creating Parallel Streams
     */
    static void demonstrateCreatingParallelStreams() {
        System.out.println("=== Creating Parallel Streams ===");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Method 1: From collection
        Stream<Integer> parallel1 = numbers.parallelStream();

        // Method 2: Convert sequential to parallel
        Stream<Integer> parallel2 = numbers.stream().parallel();

        // Check if parallel
        System.out.println("Is parallel? " + parallel1.isParallel());

        // Convert back to sequential
        Stream<Integer> sequential = parallel1.sequential();
        System.out.println("Is parallel? " + sequential.isParallel());

        // Demonstrate parallel execution
        System.out.println("\nParallel execution (note different threads):");
        numbers.parallelStream()
            .forEach(n -> System.out.println(
                "Number: " + n + " | Thread: " + Thread.currentThread().getName()
            ));
    }

    /**
     * Sequential vs Parallel
     */
    static void demonstrateSequentialVsParallel() {
        System.out.println("\n=== Sequential vs Parallel ===");

        List<Integer> numbers = IntStream.rangeClosed(1, 10)
            .boxed()
            .toList();

        // Sequential stream - maintains order
        System.out.println("Sequential:");
        numbers.stream()
            .map(n -> n * 2)
            .forEach(n -> System.out.print(n + " "));

        System.out.println("\n\nParallel (may not maintain order):");
        numbers.parallelStream()
            .map(n -> n * 2)
            .forEach(n -> System.out.print(n + " "));

        // Use forEachOrdered to maintain order
        System.out.println("\n\nParallel with forEachOrdered:");
        numbers.parallelStream()
            .map(n -> n * 2)
            .forEachOrdered(n -> System.out.print(n + " "));

        System.out.println();
    }

    /**
     * Performance Benefits
     */
    static void demonstratePerformanceBenefits() {
        System.out.println("\n=== Performance Benefits ===");

        // Large dataset
        List<Integer> largeList = IntStream.rangeClosed(1, 1_000_000)
            .boxed()
            .toList();

        // Sequential processing
        long start = System.currentTimeMillis();
        long sumSequential = largeList.stream()
            .map(Example07_ParallelStreams::expensiveOperation)
            .reduce(0, Integer::sum);
        long timeSequential = System.currentTimeMillis() - start;

        System.out.println("Sequential sum: " + sumSequential);
        System.out.println("Time taken: " + timeSequential + "ms");

        // Parallel processing
        start = System.currentTimeMillis();
        long sumParallel = largeList.parallelStream()
            .map(Example07_ParallelStreams::expensiveOperation)
            .reduce(0, Integer::sum);
        long timeParallel = System.currentTimeMillis() - start;

        System.out.println("\nParallel sum: " + sumParallel);
        System.out.println("Time taken: " + timeParallel + "ms");

        double speedup = (double) timeSequential / timeParallel;
        System.out.printf("\nSpeedup: %.2fx faster%n", speedup);

        System.out.println("\nAvailable processors: " +
            Runtime.getRuntime().availableProcessors());
    }

    static int expensiveOperation(int n) {
        // Simulate expensive computation
        return n % 2 == 0 ? n : -n;
    }

    /**
     * Thread Safety Issues
     */
    static void demonstrateThreadSafety() {
        System.out.println("\n=== Thread Safety Issues ===");

        List<Integer> numbers = IntStream.rangeClosed(1, 100).boxed().toList();

        // ❌ UNSAFE: Using non-thread-safe collection
        System.out.println("❌ Unsafe (ArrayList is not thread-safe):");
        List<Integer> unsafeList = new ArrayList<>();
        numbers.parallelStream()
            .forEach(unsafeList::add);
        System.out.println("Size (may be incorrect): " + unsafeList.size());

        // ✓ SAFE: Using thread-safe collection
        System.out.println("\n✓ Safe (CopyOnWriteArrayList is thread-safe):");
        List<Integer> safeList = new CopyOnWriteArrayList<>();
        numbers.parallelStream()
            .forEach(safeList::add);
        System.out.println("Size (correct): " + safeList.size());

        // ✓ BETTER: Use collect instead
        System.out.println("\n✓ Better (using collect):");
        List<Integer> collectedList = numbers.parallelStream()
            .collect(Collectors.toList());
        System.out.println("Size (correct): " + collectedList.size());

        // ❌ UNSAFE: Shared mutable state
        System.out.println("\n❌ Unsafe shared state:");
        Counter unsafeCounter = new Counter();
        numbers.parallelStream()
            .forEach(n -> unsafeCounter.increment());
        System.out.println("Count (incorrect): " + unsafeCounter.getCount());

        // ✓ SAFE: Using reduce
        System.out.println("\n✓ Safe with reduce:");
        int count = numbers.parallelStream()
            .map(n -> 1)
            .reduce(0, Integer::sum);
        System.out.println("Count (correct): " + count);
    }

    /**
     * When to Use Parallel Streams
     */
    static void demonstrateWhenToUse() {
        System.out.println("\n=== When to Use Parallel Streams ===");

        System.out.println("✓ GOOD CANDIDATES:");
        System.out.println("  - Large datasets (thousands+ elements)");
        System.out.println("  - CPU-intensive operations");
        System.out.println("  - Independent operations (no shared state)");
        System.out.println("  - Operations that can be split/combined");
        System.out.println();

        System.out.println("❌ BAD CANDIDATES:");
        System.out.println("  - Small datasets (overhead > benefit)");
        System.out.println("  - I/O-bound operations");
        System.out.println("  - Operations with side effects");
        System.out.println("  - Order-dependent operations");
        System.out.println("  - Shared mutable state");
        System.out.println();

        // Good example: CPU-intensive on large dataset
        List<Integer> large = IntStream.rangeClosed(1, 10000).boxed().toList();

        long sum = large.parallelStream()
            .filter(n -> isPrime(n))
            .mapToInt(Integer::intValue)
            .sum();

        System.out.println("Prime numbers sum: " + sum);
        System.out.println("✓ Good use: CPU-intensive prime checking");

        // Bad example: I/O-bound operations
        System.out.println("\n❌ Bad use: I/O operations (use async instead)");
    }

    static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

    /**
     * Fork/Join Framework
     */
    static void demonstrateForkJoin() {
        System.out.println("\n=== Fork/Join Framework ===");

        System.out.println("Parallel streams use Fork/Join framework:");
        System.out.println("1. Split task into subtasks (Fork)");
        System.out.println("2. Process subtasks in parallel");
        System.out.println("3. Combine results (Join)");
        System.out.println();

        // Show common pool
        ForkJoinPool pool = ForkJoinPool.commonPool();
        System.out.println("Common pool parallelism: " + pool.getParallelism());
        System.out.println("Common pool size: " + pool.getPoolSize());

        // Example with custom pool
        ForkJoinPool customPool = new ForkJoinPool(2);
        try {
            customPool.submit(() -> {
                List<Integer> numbers = IntStream.rangeClosed(1, 10).boxed().toList();
                numbers.parallelStream()
                    .forEach(n -> System.out.println(
                        "Number: " + n + " | Pool: custom"
                    ));
            }).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        customPool.shutdown();
    }

    /**
     * Controlling Parallelism
     */
    static void demonstrateControllingParallelism() {
        System.out.println("\n=== Controlling Parallelism ===");

        // Default parallelism
        System.out.println("Default parallelism: " +
            ForkJoinPool.commonPool().getParallelism());

        // Set custom parallelism (via system property)
        System.out.println("To set custom parallelism:");
        System.out.println("-Djava.util.concurrent.ForkJoinPool.common.parallelism=4");

        // Characteristics
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        System.out.println("\nStream characteristics:");
        Spliterator<Integer> spliterator = numbers.parallelStream().spliterator();
        System.out.println("ORDERED: " + spliterator.hasCharacteristics(Spliterator.ORDERED));
        System.out.println("SIZED: " + spliterator.hasCharacteristics(Spliterator.SIZED));
        System.out.println("CONCURRENT: " + spliterator.hasCharacteristics(Spliterator.CONCURRENT));

        // Using unordered for better performance
        long count = numbers.parallelStream()
            .unordered()  // May improve performance
            .filter(n -> n % 2 == 0)
            .count();
        System.out.println("\nEven count: " + count);
    }
}

class Counter {
    private int count = 0;

    public void increment() {
        count++;  // Not thread-safe!
    }

    public int getCount() {
        return count;
    }
}

/*
 * Parallel Streams Summary:
 *
 * 1. CREATING PARALLEL STREAMS:
 *    - collection.parallelStream()
 *    - stream().parallel()
 *    - Convert back: parallel().sequential()
 *
 * 2. PERFORMANCE:
 *    Pros:
 *    ✓ Faster for CPU-intensive operations
 *    ✓ Automatic parallelism
 *    ✓ Utilizes multiple cores
 *
 *    Cons:
 *    ✗ Overhead for small datasets
 *    ✗ Not suitable for I/O operations
 *    ✗ Can be slower if not used correctly
 *
 * 3. THREAD SAFETY:
 *    ❌ Avoid:
 *    - Non-thread-safe collections (ArrayList, HashMap)
 *    - Shared mutable state
 *    - Side effects
 *
 *    ✓ Safe:
 *    - Stateless operations
 *    - Thread-safe collections (ConcurrentHashMap)
 *    - Use collect() instead of forEach()
 *    - Immutable objects
 *
 * 4. WHEN TO USE:
 *    ✓ Good for:
 *    - Large datasets (10,000+ elements)
 *    - CPU-intensive operations
 *    - Independent computations
 *    - Stateless operations
 *
 *    ❌ Avoid for:
 *    - Small datasets (< 1000 elements)
 *    - I/O operations
 *    - Order-dependent operations
 *    - Operations with side effects
 *
 * 5. FORK/JOIN FRAMEWORK:
 *    - Uses ForkJoinPool.commonPool()
 *    - Default parallelism = available processors - 1
 *    - Work-stealing algorithm
 *    - Recursive decomposition
 *
 * 6. ORDERING:
 *    - forEach(): May not preserve order
 *    - forEachOrdered(): Preserves order (slower)
 *    - unordered(): Allows better performance
 *    - sorted(), distinct(): May impact parallelism
 *
 * 7. BEST PRACTICES:
 *    - Profile before parallelizing
 *    - Use for CPU-bound operations only
 *    - Avoid shared mutable state
 *    - Use collect() over forEach()
 *    - Be aware of overhead
 *    - Test for correctness
 *    - Measure performance gain
 *
 * 8. COMMON PITFALLS:
 *    - Using with small datasets
 *    - Non-thread-safe operations
 *    - Blocking operations (I/O)
 *    - Assuming faster performance
 *    - Side effects in streams
 *
 * 9. TUNING:
 *    // Set parallelism level
 *    -Djava.util.concurrent.ForkJoinPool.common.parallelism=8
 *
 *    // Custom pool
 *    ForkJoinPool customPool = new ForkJoinPool(4);
 *    customPool.submit(() -> {
 *        list.parallelStream()...
 *    }).get();
 *
 * 10. BENCHMARKING:
 *     - Always measure actual performance
 *     - Compare sequential vs parallel
 *     - Consider dataset size
 *     - Account for overhead
 *     - Use JMH for accurate benchmarks
 */
