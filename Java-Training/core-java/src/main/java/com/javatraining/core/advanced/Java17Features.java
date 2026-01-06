package com.javatraining.core.advanced;

import java.time.LocalDate;
import java.util.List;

/**
 * Demonstrates Java 17 features including records, sealed classes,
 * pattern matching, and text blocks.
 */
public class Java17Features {
    
    // Record example (immutable data carrier)
    public record Person(String name, LocalDate birthDate) {
        // Compact constructor for validation
        public Person {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Name cannot be empty");
            }
        }
        
        // Additional method
        public int getAge() {
            return LocalDate.now().getYear() - birthDate.getYear();
        }
    }
    
    // Sealed interface
    public sealed interface Shape 
        permits Circle, Rectangle, Triangle {
        double area();
    }
    
    // Sealed class implementations
    public record Circle(double radius) implements Shape {
        @Override
        public double area() {
            return Math.PI * radius * radius;
        }
    }
    
    public record Rectangle(double width, double height) implements Shape {
        @Override
        public double area() {
            return width * height;
        }
    }
    
    public record Triangle(double base, double height) implements Shape {
        @Override
        public double area() {
            return 0.5 * base * height;
        }
    }
    
    // Pattern matching for instanceof
    public static void processShape(Shape shape) {
        // Pattern matching for instanceof (Java 16+)
        if (shape instanceof Circle c) {
            System.out.printf("Circle with radius %.2f (area=%.2f)%n", 
                c.radius(), c.area());
        } 
        // Pattern matching with switch expressions (Java 17+)
        else {
            String shapeInfo = switch(shape) {
                case Rectangle r -> String.format("Rectangle (%.2fx%.2f) with area=%.2f", 
                    r.width(), r.height(), r.area());
                case Triangle t -> String.format("Triangle (base=%.2f, height=%.2f) with area=%.2f",
                    t.base(), t.height(), t.area());
                default -> "Unknown shape";
            };
            System.out.println(shapeInfo);
        }
    }
    
    // Text blocks (Java 15+)
    public static String getHtmlTemplate() {
        return """
            <!DOCTYPE html>
            <html>
                <head>
                    <title>%s</title>
                </head>
                <body>
                    <h1>%s</h1>
                    <p>%s</p>
                </body>
            </html>
            """.formatted("Java 17 Features", "Welcome", "This page demonstrates Java 17 features!");
    }
    
    // Helpful NullPointerExceptions (Java 14+)
    public static void demonstrateHelpfulNPE() {
        String[] words = { "Hello", null, "World" };
        try {
            // This will throw a NullPointerException with a helpful message
            System.out.println(words[1].toLowerCase());
        } catch (NullPointerException e) {
            System.out.println("Helpful NPE message: " + e.getMessage());
        }
    }
    
    // Records with pattern matching (Java 17+)
    public static void processPeople(List<Person> people) {
        for (Person p : people) {
            // Pattern matching for switch (preview in Java 17)
            String message = switch (p) {
                case Person(var name, var bd) when p.getAge() >= 18 -> 
                    name + " is an adult";
                case Person p2 when p2.name().startsWith("A") -> 
                    p2.name() + " starts with 'A' and is " + p2.getAge() + " years old";
                default -> "Other person: " + p.name();
            };
            System.out.println(message);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Java 17 Features Demo ===\n");
        
        // 1. Records
        Person alice = new Person("Alice", LocalDate.of(1990, 5, 15));
        System.out.println("Person: " + alice);
        System.out.println("Age: " + alice.getAge());
        
        // 2. Sealed classes and pattern matching
        List<Shape> shapes = List.of(
            new Circle(5.0),
            new Rectangle(4.0, 6.0),
            new Triangle(3.0, 4.0)
        );
        
        System.out.println("\nProcessing shapes:");
        shapes.forEach(Java17Features::processShape);
        
        // 3. Text blocks
        System.out.println("\nHTML Template:");
        System.out.println(getHtmlTemplate());
        
        // 4. Helpful NullPointerExceptions
        System.out.println("\nDemonstrating helpful NPE:");
        demonstrateHelpfulNPE();
        
        // 5. Records with pattern matching
        System.out.println("\nProcessing people:");
        List<Person> people = List.of(
            alice,
            new Person("Bob", LocalDate.of(2010, 8, 20)),
            new Person("Anna", LocalDate.of(1995, 3, 10))
        );
        processPeople(people);
    }
}
