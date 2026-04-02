/**
 * JVM Architecture - Memory Structure
 * Demonstrates: Heap, Stack, Method Area, and Memory Allocation
 */
package jvm_architecture;

import java.util.ArrayList;
import java.util.List;

public class Example10_JVMMemoryStructure {

    // Class variable - stored in Method Area (Metaspace in Java 8+)
    private static int classCounter = 0;

    // Instance variable - stored in Heap
    private String instanceName;
    private int instanceId;

    public Example10_JVMMemoryStructure(String name) {
        this.instanceName = name;  // Stored in Heap
        this.instanceId = ++classCounter;
    }

    public static void main(String[] args) {
        System.out.println("=== JVM Memory Structure Demonstration ===\n");

        // 1. Stack Memory Example
        demonstrateStackMemory();

        // 2. Heap Memory Example
        demonstrateHeapMemory();

        // 3. Method Area Example
        demonstrateMethodArea();

        // 4. Memory Allocation Pattern
        demonstrateMemoryAllocation();

        // 5. Out of Memory Scenarios
        System.out.println("\n=== Memory Limits ===");
        printMemoryInfo();
    }

    /**
     * Stack Memory:
     * - Stores local variables and method call frames
     * - LIFO (Last In First Out)
     * - Thread-specific (each thread has its own stack)
     * - Smaller in size
     */
    static void demonstrateStackMemory() {
        System.out.println("=== Stack Memory ===");

        // All these variables are stored in Stack
        int localInt = 10;              // Primitive - Stack
        double localDouble = 20.5;      // Primitive - Stack
        String localString = "Hello";   // Reference in Stack, Object in Heap

        System.out.println("Local variables stored in Stack:");
        System.out.println("localInt: " + localInt);
        System.out.println("localDouble: " + localDouble);
        System.out.println("localString reference: Stack, Object: Heap");

        // Method calls create stack frames
        stackMethod1();
    }

    static void stackMethod1() {
        int x = 1;  // New stack frame created
        stackMethod2();
        // Stack frame destroyed after method returns
    }

    static void stackMethod2() {
        int y = 2;  // Another stack frame
        System.out.println("Stack depth demonstration complete");
    }

    /**
     * Heap Memory:
     * - Stores objects and instance variables
     * - Shared among all threads
     * - Larger in size
     * - Managed by Garbage Collector
     */
    static void demonstrateHeapMemory() {
        System.out.println("\n=== Heap Memory ===");

        // Objects are created in Heap
        Example10_JVMMemoryStructure obj1 = new Example10_JVMMemoryStructure("Object1");
        Example10_JVMMemoryStructure obj2 = new Example10_JVMMemoryStructure("Object2");

        System.out.println("Objects created in Heap:");
        System.out.println("obj1: " + obj1.instanceName + " (ID: " + obj1.instanceId + ")");
        System.out.println("obj2: " + obj2.instanceName + " (ID: " + obj2.instanceId + ")");

        // Arrays are also stored in Heap
        int[] array = new int[5];  // Array object in Heap
        String[] stringArray = {"A", "B", "C"};  // Array + String objects in Heap

        System.out.println("Arrays stored in Heap: " + array.length + " elements");
    }

    /**
     * Method Area (Metaspace in Java 8+):
     * - Stores class metadata, static variables, constant pool
     * - Shared among all threads
     * - Part of Non-Heap memory
     */
    static void demonstrateMethodArea() {
        System.out.println("\n=== Method Area (Metaspace) ===");

        System.out.println("Class metadata stored in Method Area:");
        System.out.println("Class name: " + Example10_JVMMemoryStructure.class.getName());
        System.out.println("Static variable classCounter: " + classCounter);

        // String literals stored in String Pool (part of Heap in Java 7+)
        String literal1 = "Java";
        String literal2 = "Java";
        System.out.println("\nString Pool (Heap):");
        System.out.println("literal1 == literal2: " + (literal1 == literal2));  // true
    }

    /**
     * Memory Allocation Pattern
     */
    static void demonstrateMemoryAllocation() {
        System.out.println("\n=== Memory Allocation Pattern ===");

        // Step-by-step allocation
        Person person = new Person("Alice", 25);
        /*
         * Memory breakdown:
         * 1. Reference 'person' -> Stack
         * 2. Person object -> Heap
         * 3. String "Alice" -> Heap (String Pool if literal)
         * 4. int 25 -> Heap (as part of Person object)
         * 5. Person.class metadata -> Method Area
         */

        System.out.println("Person object allocation:");
        System.out.println("- Reference 'person': Stack");
        System.out.println("- Person object: Heap");
        System.out.println("- Instance variables: Heap (part of object)");
        System.out.println("- Class metadata: Method Area");

        person.display();
    }

    /**
     * Print current memory information
     */
    static void printMemoryInfo() {
        Runtime runtime = Runtime.getRuntime();

        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        long maxMemory = runtime.maxMemory();

        System.out.println("Total Memory: " + formatBytes(totalMemory));
        System.out.println("Free Memory: " + formatBytes(freeMemory));
        System.out.println("Used Memory: " + formatBytes(usedMemory));
        System.out.println("Max Memory: " + formatBytes(maxMemory));
    }

    static String formatBytes(long bytes) {
        return String.format("%.2f MB", bytes / (1024.0 * 1024.0));
    }
}

class Person {
    // Instance variables - stored in Heap
    private String name;
    private int age;

    // Static variable - stored in Method Area
    private static int count = 0;

    public Person(String name, int age) {
        this.name = name;  // Heap
        this.age = age;    // Heap
        count++;           // Method Area
    }

    public void display() {
        // Local variables - stored in Stack
        String message = "Person: " + name + ", Age: " + age;
        System.out.println(message);
        System.out.println("Total persons created: " + count);
    }
}

/*
 * JVM Memory Areas Summary:
 *
 * 1. STACK (Thread-specific):
 *    - Local variables (primitives)
 *    - Object references
 *    - Method call frames
 *    - Faster access
 *
 * 2. HEAP (Shared):
 *    - Objects
 *    - Instance variables
 *    - Arrays
 *    - String Pool (Java 7+)
 *    - Managed by GC
 *
 * 3. METHOD AREA / METASPACE (Shared):
 *    - Class metadata
 *    - Static variables
 *    - Constant pool
 *    - Method bytecode
 *
 * 4. PC REGISTER (Thread-specific):
 *    - Current instruction address
 *
 * 5. NATIVE METHOD STACK (Thread-specific):
 *    - Native method calls (JNI)
 */
