package com.javatraining.core.advanced;

import java.util.*;
import java.util.stream.*;

/**
 * Demonstrates Java 8+ Streams API
 */
public class StreamsDemo {
    public static void main(String[] args) {
        // Create sample data
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Filter and forEach
        System.out.println("Names starting with 'A':");
        names.stream()
             .filter(name -> name.startsWith("A"))
             .forEach(System.out::println);
        
        // Map and collect
        List<String> upperCaseNames = names.stream()
                                         .map(String::toUpperCase)
                                         .collect(Collectors.toList());
        System.out.println("\nUppercase names: " + upperCaseNames);
        
        // Reduce
        int sum = numbers.stream()
                        .reduce(0, Integer::sum);
        System.out.println("\nSum of numbers: " + sum);
        
        // Parallel stream
        System.out.println("\nParallel stream processing:");
        numbers.parallelStream()
               .forEach(num -> System.out.println(Thread.currentThread().getName() + " - " + num));
    }
}
