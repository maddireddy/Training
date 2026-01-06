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
