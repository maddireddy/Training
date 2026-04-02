/**
 * JVM Architecture - Garbage Collection
 * Demonstrates: GC behavior, finalize(), Memory management
 */
package jvm_architecture;

import java.util.ArrayList;
import java.util.List;

public class Example11_GarbageCollection {

    private static int objectCount = 0;
    private int id;

    public Example11_GarbageCollection() {
        this.id = ++objectCount;
        System.out.println("Object " + id + " created");
    }

    public static void main(String[] args) {
        System.out.println("=== Garbage Collection Demonstration ===\n");

        // 1. Basic GC demonstration
        demonstrateBasicGC();

        // 2. Object eligibility for GC
        demonstrateGCEligibility();

        // 3. Memory leak prevention
        demonstrateMemoryLeak();

        // 4. Weak references
        demonstrateWeakReferences();

        // 5. finalize() method (deprecated but educational)
        demonstrateFinalizeMethod();

        // 6. System.gc() behavior
        demonstrateSystemGC();
    }

    /**
     * Basic Garbage Collection
     * Objects without references become eligible for GC
     */
    static void demonstrateBasicGC() {
        System.out.println("=== Basic GC ===");

        Example11_GarbageCollection obj1 = new Example11_GarbageCollection();
        Example11_GarbageCollection obj2 = new Example11_GarbageCollection();

        // obj1 becomes eligible for GC
        obj1 = null;
        System.out.println("obj1 set to null - eligible for GC");

        // obj2 is still referenced
        System.out.println("obj2 still has reference: " + obj2.id);

        printMemoryBefore();
        System.gc();  // Request GC (not guaranteed)
        printMemoryAfter();
    }

    /**
     * Different ways an object becomes eligible for GC
     */
    static void demonstrateGCEligibility() {
        System.out.println("\n=== GC Eligibility Scenarios ===");

        // Scenario 1: Nullifying reference
        Example11_GarbageCollection obj1 = new Example11_GarbageCollection();
        obj1 = null;  // Eligible for GC
        System.out.println("Scenario 1: Reference set to null");

        // Scenario 2: Reassigning reference
        Example11_GarbageCollection obj2 = new Example11_GarbageCollection();
        obj2 = new Example11_GarbageCollection();  // First object eligible for GC
        System.out.println("Scenario 2: Reference reassigned");

        // Scenario 3: Object created inside method
        createTemporaryObject();  // Object eligible after method returns
        System.out.println("Scenario 3: Object out of scope");

        // Scenario 4: Island of isolation
        demonstrateIslandOfIsolation();
    }

    static void createTemporaryObject() {
        Example11_GarbageCollection temp = new Example11_GarbageCollection();
        // temp becomes eligible for GC when method completes
    }

    /**
     * Island of Isolation
     * Objects referencing each other but not from main program
     */
    static void demonstrateIslandOfIsolation() {
        System.out.println("\n=== Island of Isolation ===");

        Node node1 = new Node(1);
        Node node2 = new Node(2);

        // Circular reference
        node1.next = node2;
        node2.next = node1;

        // Both still referenced from stack
        System.out.println("Nodes created with circular reference");

        // Break external references - island of isolation
        node1 = null;
        node2 = null;
        System.out.println("External references removed - island eligible for GC");
    }

    /**
     * Potential memory leak scenarios
     */
    static void demonstrateMemoryLeak() {
        System.out.println("\n=== Memory Leak Prevention ===");

        // Memory leak example - static collection
        LeakyClass leaky = new LeakyClass();
        leaky.addData("Item 1");
        leaky.addData("Item 2");

        System.out.println("Data added to static collection");
        System.out.println("Warning: Static collections can cause memory leaks!");

        // Good practice - clear when done
        // LeakyClass.clear();
    }

    /**
     * Weak References demonstration
     */
    static void demonstrateWeakReferences() {
        System.out.println("\n=== Weak References ===");

        // Strong reference (normal)
        String strongRef = new String("Strong Reference");

        // Weak reference (will be GC'd even if memory is available)
        java.lang.ref.WeakReference<String> weakRef =
            new java.lang.ref.WeakReference<>(new String("Weak Reference"));

        System.out.println("Strong reference: " + strongRef);
        System.out.println("Weak reference: " + weakRef.get());

        System.gc();  // Request GC

        System.out.println("\nAfter GC:");
        System.out.println("Strong reference: " + strongRef);
        System.out.println("Weak reference: " + weakRef.get());  // Likely null
    }

    /**
     * finalize() method demonstration (Deprecated in Java 9+)
     * Use try-with-resources or Cleaner API instead
     */
    static void demonstrateFinalizeMethod() {
        System.out.println("\n=== finalize() Method (Deprecated) ===");

        FinalizableObject obj = new FinalizableObject(101);
        obj = null;  // Eligible for GC

        System.out.println("Object set to null");
        System.gc();  // Request GC
        System.runFinalization();  // Request finalization

        try {
            Thread.sleep(100);  // Give GC time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Note: finalize() is deprecated - use Cleaner API");
    }

    /**
     * System.gc() behavior
     */
    static void demonstrateSystemGC() {
        System.out.println("\n=== System.gc() Behavior ===");

        printMemoryBefore();

        // Create many objects
        List<byte[]> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new byte[1024 * 1024]);  // 1MB each
        }

        System.out.println("Created 100MB of objects");
        printMemoryAfter();

        // Clear references
        list.clear();
        list = null;

        System.out.println("\nAfter clearing references:");
        printMemoryBefore();
        System.gc();  // Suggest GC
        printMemoryAfter();

        System.out.println("\nNote: System.gc() is a suggestion, not guarantee!");
    }

    static void printMemoryBefore() {
        Runtime runtime = Runtime.getRuntime();
        long usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024;
        System.out.println("Memory before: " + usedMemory + " MB");
    }

    static void printMemoryAfter() {
        Runtime runtime = Runtime.getRuntime();
        long usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024;
        System.out.println("Memory after: " + usedMemory + " MB");
    }

    // Override finalize (deprecated)
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Object " + id + " being garbage collected");
        super.finalize();
    }
}

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        System.out.println("Node created: " + data);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Node " + data + " garbage collected");
        super.finalize();
    }
}

class LeakyClass {
    // Static collection - potential memory leak
    private static List<String> staticData = new ArrayList<>();

    public void addData(String data) {
        staticData.add(data);
        // Data stays in memory even if LeakyClass instance is GC'd
    }

    public static void clear() {
        staticData.clear();
    }
}

class FinalizableObject {
    private int id;

    public FinalizableObject(int id) {
        this.id = id;
        System.out.println("FinalizableObject " + id + " created");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("FinalizableObject " + id + " finalized!");
        super.finalize();
    }
}

/*
 * Garbage Collection Summary:
 *
 * 1. WHEN OBJECTS BECOME ELIGIBLE FOR GC:
 *    - Reference set to null
 *    - Reference reassigned
 *    - Object created inside method (out of scope)
 *    - Island of isolation (circular references without external ref)
 *
 * 2. GC TYPES:
 *    - Minor GC: Young Generation (Eden, Survivor spaces)
 *    - Major GC: Old Generation (Tenured)
 *    - Full GC: Entire Heap
 *
 * 3. GC ALGORITHMS:
 *    - Serial GC: Single-threaded
 *    - Parallel GC: Multiple threads (throughput)
 *    - CMS (Concurrent Mark Sweep): Low pause time
 *    - G1 GC: Balanced (default in Java 9+)
 *    - ZGC: Ultra-low latency (Java 11+)
 *    - Shenandoah: Low pause time
 *
 * 4. BEST PRACTICES:
 *    - Don't rely on finalize() (deprecated)
 *    - Use try-with-resources for cleanup
 *    - Clear collections when done
 *    - Avoid static collections for temporary data
 *    - Don't call System.gc() in production
 *
 * 5. JVM GC OPTIONS:
 *    -XX:+UseG1GC           # Use G1 garbage collector
 *    -XX:+UseZGC            # Use Z garbage collector
 *    -Xms512m               # Initial heap size
 *    -Xmx4g                 # Maximum heap size
 *    -XX:+PrintGCDetails    # Print GC information
 */
