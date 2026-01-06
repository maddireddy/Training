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
