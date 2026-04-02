/**
 * Lambda Closures and Variable Capture
 * Demonstrates: Capturing variables, effectively final, closures
 */
package lambda_expressions;

import java.util.function.*;

public class Example11_LambdaClosuresAndCapture {

    private static int staticCounter = 0;
    private int instanceCounter = 0;

    public static void main(String[] args) {
        System.out.println("=== Lambda Closures and Variable Capture ===\n");

        Example11_LambdaClosuresAndCapture demo = new Example11_LambdaClosuresAndCapture();

        // 1. Effectively final variables
        demo.demonstrateEffectivelyFinal();

        // 2. Capturing local variables
        demo.demonstrateCapturingLocal();

        // 3. Capturing instance variables
        demo.demonstrateCapturingInstance();

        // 4. Capturing static variables
        demonstrateCapturingStatic();

        // 5. Closures in action
        demo.demonstrateClosures();

        // 6. Common pitfalls
        demo.demonstratePitfalls();
    }

    /**
     * Effectively final variables
     */
    void demonstrateEffectivelyFinal() {
        System.out.println("=== Effectively Final ===");

        // ✓ Final variable - can be captured
        final int finalVar = 10;
        Supplier<Integer> supplier1 = () -> finalVar;
        System.out.println("Final variable: " + supplier1.get());

        // ✓ Effectively final - never modified after initialization
        int effectivelyFinal = 20;
        Supplier<Integer> supplier2 = () -> effectivelyFinal;
        System.out.println("Effectively final: " + supplier2.get());

        // ❌ Not effectively final - compilation error if uncommented
        // int notFinal = 30;
        // Supplier<Integer> supplier3 = () -> notFinal;
        // notFinal = 40;  // This makes it non-effectively-final

        System.out.println("\nVariables captured in lambdas must be effectively final");
    }

    /**
     * Capturing local variables
     */
    void demonstrateCapturingLocal() {
        System.out.println("\n=== Capturing Local Variables ===");

        String name = "Alice";
        int age = 25;

        // Lambda captures local variables
        Supplier<String> getInfo = () ->
            "Name: " + name + ", Age: " + age;

        System.out.println(getInfo.get());

        // Multiple lambdas can capture same variable
        Predicate<Integer> isOlderThan = threshold -> age > threshold;
        System.out.println("Older than 20? " + isOlderThan.test(20));
        System.out.println("Older than 30? " + isOlderThan.test(30));

        // Capturing in loop (each iteration creates new scope)
        for (int i = 0; i < 3; i++) {
            final int index = i;  // Must be final/effectively final
            Runnable task = () -> System.out.println("Task " + index);
            task.run();
        }
    }

    /**
     * Capturing instance variables
     */
    void demonstrateCapturingInstance() {
        System.out.println("\n=== Capturing Instance Variables ===");

        // Lambda can access and modify instance variables
        Runnable incrementer = () -> {
            instanceCounter++;
            System.out.println("Instance counter: " + instanceCounter);
        };

        incrementer.run();
        incrementer.run();
        incrementer.run();

        // Instance variable can be modified outside lambda too
        this.instanceCounter += 10;
        System.out.println("After external modification: " + instanceCounter);

        // Accessing 'this' in lambda
        Supplier<String> thisRef = () -> "This object: " + this.toString();
        System.out.println(thisRef.get());
    }

    /**
     * Capturing static variables
     */
    static void demonstrateCapturingStatic() {
        System.out.println("\n=== Capturing Static Variables ===");

        // Lambda can access and modify static variables
        Runnable incrementer = () -> {
            staticCounter++;
            System.out.println("Static counter: " + staticCounter);
        };

        incrementer.run();
        incrementer.run();
        incrementer.run();

        // Static variable modified outside
        staticCounter += 10;
        incrementer.run();
    }

    /**
     * Closures in action
     */
    void demonstrateClosures() {
        System.out.println("\n=== Closures ===");

        // Factory function that returns a function
        Function<Integer, IntUnaryOperator> multiplierFactory = factor ->
            num -> num * factor;

        // Create specific multipliers
        IntUnaryOperator double Value = multiplierFactory.apply(2);
        IntUnaryOperator tripleValue = multiplierFactory.apply(3);
        IntUnaryOperator tenTimes = multiplierFactory.apply(10);

        System.out.println("Double 5: " + doubleValue.applyAsInt(5));
        System.out.println("Triple 5: " + tripleValue.applyAsInt(5));
        System.out.println("Ten times 5: " + tenTimes.applyAsInt(5));

        // Counter closure
        Supplier<IntSupplier> counterFactory = () -> {
            final int[] count = {0};  // Array to allow modification
            return () -> ++count[0];
        };

        IntSupplier counter1 = counterFactory.get();
        IntSupplier counter2 = counterFactory.get();

        System.out.println("\nCounter1: " + counter1.getAsInt());  // 1
        System.out.println("Counter1: " + counter1.getAsInt());  // 2
        System.out.println("Counter2: " + counter2.getAsInt());  // 1 (separate state)
        System.out.println("Counter1: " + counter1.getAsInt());  // 3

        // Greeting closure
        Function<String, Consumer<String>> greeterFactory = greeting ->
            name -> System.out.println(greeting + ", " + name + "!");

        Consumer<String> casualGreeter = greeterFactory.apply("Hello");
        Consumer<String> formalGreeter = greeterFactory.apply("Good morning");

        casualGreeter.accept("Alice");
        formalGreeter.accept("Mr. Smith");
    }

    /**
     * Common pitfalls
     */
    void demonstratePitfalls() {
        System.out.println("\n=== Common Pitfalls ===");

        System.out.println("Pitfall 1: Loop variable capture");
        // ❌ Wrong way - doesn't work
        // for (int i = 0; i < 3; i++) {
        //     Runnable task = () -> System.out.println(i);  // Error!
        // }

        // ✓ Correct way
        for (int i = 0; i < 3; i++) {
            final int index = i;
            Runnable task = () -> System.out.println("Index: " + index);
            task.run();
        }

        System.out.println("\nPitfall 2: Mutable object vs primitive");

        // Primitive - must be final
        int primitive = 10;
        // primitive = 20;  // Would break lambda

        // Mutable object - reference is final, but content can change
        final StringBuilder sb = new StringBuilder("Hello");
        Runnable modifier = () -> sb.append(" World");
        modifier.run();
        System.out.println("StringBuilder: " + sb);

        System.out.println("\nPitfall 3: Array workaround for mutable local");
        // Using array to modify "effectively final" variable
        final int[] counter = {0};
        IntSupplier increment = () -> ++counter[0];

        System.out.println("Count: " + increment.getAsInt());
        System.out.println("Count: " + increment.getAsInt());
        System.out.println("Count: " + increment.getAsInt());

        System.out.println("\nNote: Prefer instance/static variables over this workaround");
    }
}

/*
 * Lambda Closures and Variable Capture Summary:
 *
 * 1. WHAT CAN BE CAPTURED:
 *    ✓ Local variables (must be final or effectively final)
 *    ✓ Instance variables (can be modified)
 *    ✓ Static variables (can be modified)
 *    ✓ Method parameters (must be effectively final)
 *
 * 2. EFFECTIVELY FINAL:
 *    - Variable never modified after initialization
 *    - Doesn't need 'final' keyword
 *    - Lambda captures value, not variable
 *    - Reason: Thread safety and consistency
 *
 * 3. VARIABLE TYPES:
 *
 *    Local Variables:
 *    - Must be effectively final
 *    - Stored on stack (method scope)
 *    - Lambda captures value
 *
 *    Instance Variables:
 *    - Can be modified
 *    - Stored in object (heap)
 *    - Lambda captures reference to object
 *
 *    Static Variables:
 *    - Can be modified
 *    - Stored in method area
 *    - Shared across all instances
 *
 * 4. CLOSURE:
 *    - Function that captures variables from enclosing scope
 *    - Retains access even after scope exits
 *    - Each closure has own captured state
 *
 * 5. COMMON PATTERNS:
 *
 *    // Factory pattern
 *    Function<T, Function<U, R>> factory = ...
 *
 *    // Counter
 *    Supplier<IntSupplier> counter = () -> {
 *        int[] count = {0};
 *        return () -> ++count[0];
 *    };
 *
 *    // Configuration
 *    Function<Config, Processor> configure = config ->
 *        data -> process(data, config);
 *
 * 6. WORKAROUNDS FOR MUTABLE LOCALS:
 *
 *    // ❌ Doesn't work
 *    int count = 0;
 *    () -> count++;  // Error!
 *
 *    // ✓ Array workaround
 *    int[] count = {0};
 *    () -> count[0]++;
 *
 *    // ✓ AtomicInteger
 *    AtomicInteger count = new AtomicInteger(0);
 *    () -> count.incrementAndGet();
 *
 *    // ✓ Use instance variable instead
 *    this.count++;
 *
 * 7. LOOP VARIABLE CAPTURE:
 *
 *    // ❌ Wrong
 *    for (int i = 0; i < 10; i++) {
 *        () -> System.out.println(i);  // Error!
 *    }
 *
 *    // ✓ Correct
 *    for (int i = 0; i < 10; i++) {
 *        final int index = i;
 *        () -> System.out.println(index);
 *    }
 *
 * 8. BEST PRACTICES:
 *    ✓ Prefer final variables when possible
 *    ✓ Use instance variables for mutable state
 *    ✓ Understand scope and lifetime
 *    ✓ Be aware of thread safety
 *    ✗ Avoid array workaround for primitives
 *    ✗ Don't capture too many variables
 *
 * 9. THREAD SAFETY:
 *    - Captured variables should be thread-safe
 *    - Instance/static variables may need synchronization
 *    - Effectively final locals are inherently thread-safe
 *
 * 10. MEMORY IMPLICATIONS:
 *     - Lambda holds reference to captured variables
 *     - Can prevent garbage collection
 *     - Be careful with large objects
 *     - Closure retains entire object, not just field
 */
