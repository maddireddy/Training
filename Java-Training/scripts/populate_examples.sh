#!/bin/bash

# Create necessary directories if they don't exist
mkdir -p core-java/src/main/java/com/javatraining/core/basics/controlflow
mkdir -p core-java/src/main/java/com/javatraining/core/basics/variables
mkdir -p core-java/src/main/java/com/javatraining/core/basics/operators
mkdir -p core-java/src/main/java/com/javatraining/core/collections/lists
mkdir -p core-java/src/main/java/com/javatraining/core/collections/sets
mkdir -p core-java/src/main/java/com/javatraining/core/collections/maps
mkdir -p core-java/src/main/java/com/javatraining/core/collections/queues

# 1. Basic Control Flow Examples
cat > core-java/src/main/java/com/javatraining/core/basics/controlflow/IfElseExample.java << 'EOL'
package com.javatraining.core.basics.controlflow;

/**
 * Demonstrates basic if-else control flow in Java
 */
public class IfElseExample {
    public static void main(String[] args) {
        int number = 10;
        
        // Simple if-else
        if (number > 0) {
            System.out.println("Number is positive");
        } else if (number < 0) {
            System.out.println("Number is negative");
        } else {
            System.out.println("Number is zero");
        }
        
        // Nested if-else
        if (number != 0) {
            if (number % 2 == 0) {
                System.out.println("Number is even");
            } else {
                System.out.println("Number is odd");
            }
        }
        
        // Ternary operator
        String result = (number > 0) ? "Positive" : (number < 0) ? "Negative" : "Zero";
        System.out.println("Using ternary operator: " + result);
    }
}
EOL

# 2. Variables and Data Types
cat > core-java/src/main/java/com/javatraining/core/basics/variables/VariableExamples.java << 'EOL'
package com.javatraining.core.basics.variables;

/**
 * Demonstrates variable declarations and data types in Java
 */
public class VariableExamples {
    // Instance variables
    private int instanceVar = 10;
    private static String staticVar = "Static Variable";
    
    public static void main(String[] args) {
        // Local variables
        byte byteVar = 127;
        short shortVar = 32000;
        int intVar = 2000000;
        long longVar = 1234567890123L;
        float floatVar = 3.14f;
        double doubleVar = 3.14159265359;
        char charVar = 'A';
        boolean boolVar = true;
        String stringVar = "Hello, Java!";
        
        // Using var (Java 10+)
        var inferredVar = "Type inferred as String";
        
        // Displaying values
        System.out.println("byte: " + byteVar);
        System.out.println("short: " + shortVar);
        System.out.println("int: " + intVar);
        System.out.println("long: " + longVar);
        System.out.println("float: " + floatVar);
        System.out.println("double: " + doubleVar);
        System.out.println("char: " + charVar);
        System.out.println("boolean: " + boolVar);
        System.out.println("String: " + stringVar);
        System.out.println("Inferred: " + inferredVar);
        
        // Using instance and static variables
        VariableExamples example = new VariableExamples();
        System.out.println("Instance variable: " + example.instanceVar);
        System.out.println("Static variable: " + staticVar);
    }
}
EOL

# 3. List Examples
cat > core-java/src/main/java/com/javatraining/core/collections/lists/ListExamples.java << 'EOL'
package com.javatraining.core.collections.lists;

import java.util.*;

/**
 * Demonstrates various List implementations in Java
 */
public class ListExamples {
    public static void main(String[] args) {
        // ArrayList Example
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Java");
        arrayList.add("Python");
        arrayList.add("JavaScript");
        System.out.println("ArrayList: " + arrayList);
        
        // LinkedList Example
        List<Integer> linkedList = new LinkedList<>();
        linkedList.add(10);
        linkedList.add(20);
        linkedList.add(30);
        System.out.println("LinkedList: " + linkedList);
        
        // Vector Example (thread-safe)
        List<Double> vector = new Vector<>();
        vector.add(3.14);
        vector.add(2.71);
        System.out.println("Vector: " + vector);
        
        // Common List operations
        System.out.println("First element: " + arrayList.get(0));
        System.out.println("List size: " + linkedList.size());
        System.out.println("Contains 20? " + linkedList.contains(20));
        
        // Iterating through a list
        System.out.println("\nIterating using for-each:");
        for (String language : arrayList) {
            System.out.println("Language: " + language);
        }
        
        // Using Java 8+ forEach
        System.out.println("\nUsing forEach with lambda:");
        arrayList.forEach(lang -> System.out.println("-> " + lang));
    }
}
EOL

# 4. Set Examples
cat > core-java/src/main/java/com/javatraining/core/collections/sets/SetExamples.java << 'EOL'
package com.javatraining.core.collections.sets;

import java.util.*;

/**
 * Demonstrates Set implementations in Java
 */
public class SetExamples {
    public static void main(String[] args) {
        // HashSet Example (no order)
        Set<String> hashSet = new HashSet<>();
        hashSet.add("Apple");
        hashSet.add("Banana");
        hashSet.add("Orange");
        hashSet.add("Apple"); // Duplicate will be ignored
        System.out.println("HashSet: " + hashSet);
        
        // TreeSet Example (sorted order)
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(30);
        treeSet.add(10);
        treeSet.add(20);
        System.out.println("TreeSet (sorted): " + treeSet);
        
        // LinkedHashSet Example (insertion order)
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("Third");
        linkedHashSet.add("First");
        linkedHashSet.add("Second");
        System.out.println("LinkedHashSet (insertion order): " + linkedHashSet);
        
        // Set operations
        Set<String> fruits = new HashSet<>(Arrays.asList("Apple", "Banana", "Cherry"));
        Set<String> tropical = new HashSet<>(Arrays.asList("Banana", "Mango", "Pineapple"));
        
        // Union
        Set<String> union = new HashSet<>(fruits);
        union.addAll(tropical);
        System.out.println("Union: " + union);
        
        // Intersection
        Set<String> intersection = new HashSet<>(fruits);
        intersection.retainAll(tropical);
        System.out.println("Intersection: " + intersection);
        
        // Difference
        Set<String> difference = new HashSet<>(fruits);
        difference.removeAll(tropical);
        System.out.println("Difference (fruits - tropical): " + difference);
    }
}
EOL

# 5. Map Examples
cat > core-java/src/main/java/com/javatraining/core/collections/maps/MapExamples.java << 'EOL'
package com.javatraining.core.collections.maps;

import java.util.*;

/**
 * Demonstrates Map implementations in Java
 */
public class MapExamples {
    public static void main(String[] args) {
        // HashMap Example
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("John", 25);
        hashMap.put("Alice", 30);
        hashMap.put("Bob", 22);
        System.out.println("HashMap: " + hashMap);
        
        // TreeMap Example (sorted by keys)
        Map<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("Orange", 5);
        treeMap.put("Apple", 10);
        treeMap.put("Banana", 8);
        System.out.println("TreeMap (sorted by key): " + treeMap);
        
        // LinkedHashMap Example (insertion order)
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("Third", 3);
        linkedHashMap.put("First", 1);
        linkedHashMap.put("Second", 2);
        System.out.println("LinkedHashMap (insertion order): " + linkedHashMap);
        
        // Common Map operations
        System.out.println("\nMap Operations:");
        System.out.println("Contains key 'John': " + hashMap.containsKey("John"));
        System.out.println("Age of Alice: " + hashMap.get("Alice"));
        System.out.println("All keys: " + hashMap.keySet());
        System.out.println("All values: " + hashMap.values());
        
        // Iterating through a map
        System.out.println("\nIterating through map entries:");
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey() + " is " + entry.getValue() + " years old");
        }
        
        // Using Java 8+ forEach
        System.out.println("\nUsing forEach with lambda:");
        hashMap.forEach((name, age) -> System.out.println(name + " -> " + age));
    }
}
EOL

# 6. Queue Examples
cat > core-java/src/main/java/com/javatraining/core/collections/queues/QueueExamples.java << 'EOL'
package com.javatraining.core.collections.queues;

import java.util.*;

/**
 * Demonstrates Queue and Deque implementations in Java
 */
public class QueueExamples {
    public static void main(String[] args) {
        // Queue implementation using LinkedList
        Queue<String> queue = new LinkedList<>();
        queue.add("First");
        queue.add("Second");
        queue.add("Third");
        
        System.out.println("Queue: " + queue);
        System.out.println("Peek: " + queue.peek());
        System.out.println("Poll: " + queue.poll());
        System.out.println("Queue after poll: " + queue);
        
        // PriorityQueue Example
        Queue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(30);
        priorityQueue.add(10);
        priorityQueue.add(20);
        
        System.out.println("\nPriorityQueue (natural ordering):");
        while (!priorityQueue.isEmpty()) {
            System.out.print(priorityQueue.poll() + " ");
        }
        
        // Deque (Double Ended Queue) Example
        Deque<String> deque = new ArrayDeque<>();
        deque.addFirst("First");
        deque.addLast("Last");
        deque.addFirst("New First");
        
        System.out.println("\n\nDeque: " + deque);
        System.out.println("Peek First: " + deque.peekFirst());
        System.out.println("Peek Last: " + deque.peekLast());
        
        // Stack operations using Deque
        System.out.println("\nUsing Deque as Stack:");
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        
        while (!stack.isEmpty()) {
            System.out.println("Popped: " + stack.pop());
        }
    }
}
EOL

# Make the script executable
chmod +x populate_examples.sh

echo "Example files have been created successfully!"
