#!/bin/bash

# Base directory for Java sources
BASE_SRC="core-java/src/main/java/com/javatraining"

# Create new package structure
mkdir -p "$BASE_SRC/core/"{"basics/","collections/","oops/","concurrency/","io/","exceptions/","advanced/"}

# 1. Basics Package
echo "Creating basic examples..."
cat > "$BASE_SRC/core/basics/VariablesExample.java" << 'EOL'
package com.javatraining.core.basics;

/**
 * Demonstrates variable declarations and data types in Java
 */
public class VariablesExample {
    // Instance variables
    private int instanceVar = 10;
    private static String staticVar = "Static Variable";
    
    public static void main(String[] args) {
        // Primitive types
        byte byteVar = 127;
        short shortVar = 32000;
        int intVar = 2000000;
        long longVar = 1234567890123L;
        float floatVar = 3.14f;
        double doubleVar = 3.14159265359;
        char charVar = 'A';
        boolean boolVar = true;
        
        // Reference type
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
    }
}
EOL

# 2. Control Flow
echo "Creating control flow examples..."
cat > "$BASE_SRC/core/basics/ControlFlowExamples.java" << 'EOL'
package com.javatraining.core.basics;

/**
 * Demonstrates control flow statements in Java
 */
public class ControlFlowExamples {
    public static void main(String[] args) {
        // If-else example
        int number = 10;
        if (number > 0) {
            System.out.println("Number is positive");
        } else if (number < 0) {
            System.out.println("Number is negative");
        } else {
            System.out.println("Number is zero");
        }
        
        // Switch expression (Java 14+)
        String day = "MONDAY";
        String typeOfDay = switch (day) {
            case "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" -> "Weekday";
            case "SATURDAY", "SUNDAY" -> "Weekend";
            default -> throw new IllegalArgumentException("Invalid day: " + day);
        };
        System.out.println(day + " is a " + typeOfDay);
        
        // For loop
        System.out.println("\nFor loop:");
        for (int i = 1; i <= 5; i++) {
            System.out.println("Count: " + i);
        }
        
        // Enhanced for loop (for-each)
        System.out.println("\nFor-each loop:");
        int[] numbers = {1, 2, 3, 4, 5};
        for (int num : numbers) {
            System.out.println("Number: " + num);
        }
        
        // While loop
        System.out.println("\nWhile loop:");
        int count = 1;
        while (count <= 3) {
            System.out.println("Count: " + count);
            count++;
        }
        
        // Do-while loop
        System.out.println("\nDo-while loop:");
        int num = 1;
        do {
            System.out.println("Number: " + num);
            num++;
        } while (num <= 3);
    }
}
EOL

# 3. OOP Examples
echo "Creating OOP examples..."
mkdir -p "$BASE_SRC/core/oops/"{"inheritance","polymorphism","encapsulation","abstraction"}

# Inheritance
cat > "$BASE_SRC/core/oops/inheritance/Vehicle.java" << 'EOL'
package com.javatraining.core.oops.inheritance;

// Base class
public class Vehicle {
    protected String brand;
    protected int year;
    
    public Vehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }
    
    public void start() {
        System.out.println("Starting the vehicle...");
    }
    
    public void displayInfo() {
        System.out.println("Brand: " + brand + ", Year: " + year);
    }
}
EOL

cat > "$BASE_SRC/core/oops/inheritance/Car.java" << 'EOL'
package com.javatraining.core.oops.inheritance;

// Derived class
public class Car extends Vehicle {
    private int numberOfDoors;
    
    public Car(String brand, int year, int numberOfDoors) {
        super(brand, year);
        this.numberOfDoors = numberOfDoors;
    }
    
    @Override
    public void start() {
        System.out.println("Starting the car...");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Number of doors: " + numberOfDoors);
    }
    
    public static void main(String[] args) {
        Car myCar = new Car("Toyota", 2022, 4);
        myCar.start();
        myCar.displayInfo();
    }
}
EOL

# 4. Collections Framework
echo "Creating collections examples..."
cat > "$BASE_SRC/core/collections/CollectionsDemo.java" << 'EOL'
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
EOL

# 5. Concurrency
echo "Creating concurrency examples..."
cat > "$BASE_SRC/core/concurrency/ConcurrencyDemo.java" << 'EOL'
package com.javatraining.core.concurrency;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Demonstrates Java Concurrency features
 */
public class ConcurrencyDemo {
    private static final AtomicInteger counter = new AtomicInteger(0);
    
    public static void main(String[] args) throws Exception {
        // Thread creation
        Thread thread1 = new Thread(() -> 
            System.out.println("Thread 1: " + Thread.currentThread().getName())
        );
        
        // Thread with Runnable
        Runnable task = () -> {
            System.out.println("Thread 2: " + Thread.currentThread().getName());
        };
        Thread thread2 = new Thread(task);
        
        thread1.start();
        thread2.start();
        
        // Thread pool example
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                int count = counter.incrementAndGet();
                System.out.println("Task " + count + " executed by " + Thread.currentThread().getName());
            });
        }
        
        // Shutdown the executor
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);
        
        // CompletableFuture example (Java 8+)
        CompletableFuture.supplyAsync(() -> "Hello")
            .thenApply(s -> s + " World")
            .thenAccept(System.out::println);
    }
}
EOL

# 6. Advanced Features
echo "Creating advanced examples..."
cat > "$BASE_SRC/core/advanced/StreamsDemo.java" << 'EOL'
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
EOL

# 7. Exception Handling
echo "Creating exception handling examples..."
cat > "$BASE_SRC/core/exceptions/ExceptionHandlingDemo.java" << 'EOL'
package com.javatraining.core.exceptions;

import java.io.*;

/**
 * Demonstrates exception handling in Java
 */
public class ExceptionHandlingDemo {
    public static void main(String[] args) {
        // Try-catch-finally
        try {
            int result = 10 / 0; // This will throw ArithmeticException
        } catch (ArithmeticException e) {
            System.err.println("Error: Division by zero");
        } finally {
            System.out.println("This block always executes");
        }
        
        // Try-with-resources (Java 7+)
        try (BufferedReader br = new BufferedReader(new FileReader("nonexistent.txt"))) {
            System.out.println(br.readLine());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        
        // Custom exception
        try {
            validateAge(15);
        } catch (InvalidAgeException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    // Method that throws a custom exception
    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be 18 or older");
        }
        System.out.println("Age is valid");
    }
}

// Custom exception class
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}
EOL

# 8. I/O Operations
echo "Creating I/O examples..."
cat > "$BASE_SRC/core/io/FileOperationsDemo.java" << 'EOL'
package com.javatraining.core.io;

import java.io.*;
import java.nio.file.*;
import java.util.stream.*;

/**
 * Demonstrates file operations in Java
 */
public class FileOperationsDemo {
    public static void main(String[] args) {
        // Using java.nio.file (Java 7+)
        Path path = Paths.get("example.txt");
        
        // Writing to a file
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write("Hello, Java!\n");
            writer.write("This is a sample file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Reading from a file
        try (Stream<String> lines = Files.lines(path)) {
            System.out.println("File content:");
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Copying files
        try {
            Path dest = Paths.get("example_copy.txt");
            Files.copy(path, dest, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("\nFile copied successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
EOL

echo "Project reorganization completed successfully!"
