/**
 * JVM Architecture - Just-In-Time (JIT) Compilation
 * Demonstrates: JIT compilation, HotSpot optimization, Performance
 */
package jvm_architecture;

public class Example12_JITCompilation {

    private static final int WARMUP_ITERATIONS = 10000;
    private static final int BENCHMARK_ITERATIONS = 100000;

    public static void main(String[] args) {
        System.out.println("=== JIT Compilation Demonstration ===\n");

        // 1. Understanding JIT compilation
        explainJITCompilation();

        // 2. Code optimization example
        demonstrateCodeOptimization();

        // 3. Loop unrolling
        demonstrateLoopUnrolling();

        // 4. Inlining
        demonstrateInlining();

        // 5. Escape analysis
        demonstrateEscapeAnalysis();

        // 6. Performance impact
        demonstratePerformanceImpact();
    }

    /**
     * Explain JIT Compilation process
     */
    static void explainJITCompilation() {
        System.out.println("=== JIT Compilation Process ===");
        System.out.println();
        System.out.println("Java Execution Flow:");
        System.out.println("1. Source Code (.java) -> Java Compiler -> Bytecode (.class)");
        System.out.println("2. Bytecode -> JVM -> Interpreter (slow)");
        System.out.println("3. Hot Code Detected -> JIT Compiler -> Native Machine Code (fast)");
        System.out.println();
        System.out.println("JIT Compilers in HotSpot JVM:");
        System.out.println("- C1 (Client Compiler): Fast compilation, moderate optimization");
        System.out.println("- C2 (Server Compiler): Slower compilation, aggressive optimization");
        System.out.println("- Tiered Compilation: Use both C1 and C2 (default in Java 8+)");
        System.out.println();
    }

    /**
     * Code optimization by JIT
     */
    static void demonstrateCodeOptimization() {
        System.out.println("=== Code Optimization ===");

        // Dead code elimination
        int x = 10;
        int y = 20;
        int z = x + y;  // Used
        int w = x - y;  // If never used, JIT will eliminate it

        System.out.println("Result: " + z);

        // Constant folding
        demonstrateConstantFolding();

        // Bounds check elimination
        demonstrateBoundsCheckElimination();
    }

    static void demonstrateConstantFolding() {
        System.out.println("\n--- Constant Folding ---");

        // JIT will compute this at compile time
        int result1 = 10 * 5 + 3;  // Folded to: 53
        System.out.println("Constant expression: " + result1);

        // JIT cannot fold this (runtime value)
        int a = (int) (Math.random() * 100);
        int result2 = a * 5 + 3;
        System.out.println("Runtime expression: " + result2);
    }

    static void demonstrateBoundsCheckElimination() {
        System.out.println("\n--- Bounds Check Elimination ---");

        int[] array = new int[100];

        // JIT can eliminate bounds check here
        for (int i = 0; i < array.length; i++) {
            array[i] = i;  // Provably safe - no bounds check needed
        }

        System.out.println("Array filled with " + array.length + " elements");
    }

    /**
     * Loop unrolling optimization
     */
    static void demonstrateLoopUnrolling() {
        System.out.println("\n=== Loop Unrolling ===");

        int sum1 = loopNormal();
        int sum2 = loopUnrolled();

        System.out.println("Normal loop sum: " + sum1);
        System.out.println("Unrolled loop sum: " + sum2);
        System.out.println("JIT automatically unrolls simple loops for better performance");
    }

    // Normal loop - JIT may unroll this
    static int loopNormal() {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        return sum;
    }

    // Manually unrolled loop (JIT does this automatically)
    static int loopUnrolled() {
        int sum = 0;
        for (int i = 0; i < 100; i += 4) {
            sum += i;
            sum += i + 1;
            sum += i + 2;
            sum += i + 3;
        }
        return sum;
    }

    /**
     * Method inlining
     */
    static void demonstrateInlining() {
        System.out.println("\n=== Method Inlining ===");

        // Small methods are inlined by JIT
        int result = 0;
        for (int i = 0; i < 1000; i++) {
            result += square(i);  // JIT will inline square() method
        }

        System.out.println("Result with inlining: " + result);
        System.out.println("Small methods like square() are automatically inlined");
    }

    // Small method - candidate for inlining
    static int square(int x) {
        return x * x;
    }

    /**
     * Escape analysis
     * JIT can optimize object allocation if object doesn't escape method
     */
    static void demonstrateEscapeAnalysis() {
        System.out.println("\n=== Escape Analysis ===");

        // Object doesn't escape - JIT can allocate on stack
        int result = calculateWithLocalObject();
        System.out.println("Result: " + result);
        System.out.println("Point object doesn't escape - may be stack-allocated");

        // Object escapes - must be heap-allocated
        Point p = createPointThatEscapes();
        System.out.println("Point escapes: " + p.x + ", " + p.y);
    }

    static int calculateWithLocalObject() {
        Point p = new Point(10, 20);  // Doesn't escape - stack allocation possible
        return p.x + p.y;
    }

    static Point createPointThatEscapes() {
        return new Point(30, 40);  // Escapes - heap allocation required
    }

    /**
     * Performance impact of JIT compilation
     */
    static void demonstratePerformanceImpact() {
        System.out.println("\n=== Performance Impact ===");

        // Warm up JIT
        System.out.println("Warming up JIT compiler...");
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            computeIntensive(100);
        }

        // Benchmark after JIT compilation
        System.out.println("\nBenchmarking after warm-up:");

        long startTime = System.nanoTime();
        for (int i = 0; i < BENCHMARK_ITERATIONS; i++) {
            computeIntensive(100);
        }
        long endTime = System.nanoTime();

        double duration = (endTime - startTime) / 1_000_000.0;
        System.out.println("Time for " + BENCHMARK_ITERATIONS + " iterations: " + duration + " ms");
        System.out.println("Average per iteration: " + (duration / BENCHMARK_ITERATIONS) + " ms");

        System.out.println("\nNote: First few iterations are slower (interpreted)");
        System.out.println("After JIT compilation, performance improves significantly");
    }

    static long computeIntensive(int n) {
        long result = 0;
        for (int i = 1; i <= n; i++) {
            result += fibonacci(i % 20);  // Keep numbers small
        }
        return result;
    }

    static long fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

/*
 * JIT Compilation Summary:
 *
 * 1. COMPILATION PROCESS:
 *    - Interpret bytecode initially
 *    - Profile "hot" code (frequently executed)
 *    - Compile hot code to native machine code
 *    - Cache compiled code for reuse
 *
 * 2. OPTIMIZATION TECHNIQUES:
 *    - Dead Code Elimination: Remove unused code
 *    - Constant Folding: Compute constants at compile time
 *    - Loop Unrolling: Reduce loop overhead
 *    - Method Inlining: Replace method calls with method body
 *    - Escape Analysis: Optimize object allocation
 *    - Bounds Check Elimination: Remove redundant array checks
 *    - Lock Elision: Remove unnecessary synchronization
 *
 * 3. HOTSPOT JVM COMPILERS:
 *    - C1 (Client): Fast compilation, -client
 *    - C2 (Server): Aggressive optimization, -server
 *    - Tiered Compilation: Use both (default)
 *
 * 4. JIT TUNING OPTIONS:
 *    -XX:+TieredCompilation      # Enable tiered compilation (default)
 *    -XX:CompileThreshold=10000  # Invocations before compilation
 *    -XX:+PrintCompilation       # Print JIT compilation activity
 *    -XX:+UnlockDiagnosticVMOptions -XX:+PrintInlining  # Print inlining
 *    -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly  # Print native code
 *
 * 5. COMPILATION LEVELS (Tiered):
 *    Level 0: Interpreter
 *    Level 1: C1 with no profiling
 *    Level 2: C1 with basic profiling
 *    Level 3: C1 with full profiling
 *    Level 4: C2 (maximum optimization)
 *
 * 6. PERFORMANCE TIPS:
 *    - Warm up code before benchmarking
 *    - Keep methods small for inlining
 *    - Use final for constants
 *    - Avoid premature optimization
 *    - Profile before optimizing
 *
 * 7. DEOPTIMIZATION:
 *    - JIT can deoptimize if assumptions violated
 *    - Falls back to interpreted code
 *    - May recompile with different assumptions
 */
