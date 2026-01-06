package com.javatraining.core.collections;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Demonstrates Java Collections Framework
 */
public class CollectionsDemo {
    public static void main(String[] args) {
        // List examples
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Java");
        arrayList.add("Python");
        arrayList.add("JavaScript");
        
        // Set examples
        Set<String> hashSet = new HashSet<>();
        hashSet.add("Apple");
        hashSet.add("Banana");
        hashSet.add("Apple"); // Duplicate will be ignored
        
        // Map examples
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("John", 25);
        hashMap.put("Alice", 30);
        hashMap.put("Bob", 22);
        
        // Java 8+ Streams
        System.out.println("Languages starting with 'J':");
        arrayList.stream()
                .filter(lang -> lang.startsWith("J"))
                .forEach(System.out::println);
        
        // Immutable collections (Java 9+)
        List<String> immutableList = List.of("One", "Two", "Three");
        Set<String> immutableSet = Set.of("Red", "Green", "Blue");
        Map<String, Integer> immutableMap = Map.of("One", 1, "Two", 2, "Three", 3);
    }
}
