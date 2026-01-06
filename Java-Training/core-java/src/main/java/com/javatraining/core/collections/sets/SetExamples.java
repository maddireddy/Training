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
