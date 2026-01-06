/**
 * JDK 17 Features - Records (Final in JDK 16)
 * Key Feature: Immutable Data Classes
 */
package jdk17;

import java.util.Objects;

public class Example08_JDK17_Records {
    public static void main(String[] args) {

        // 1. Create record instances
        Person person1 = new Person("John Doe", 30);
        Person person2 = new Person("Jane Smith", 25);
        Person person3 = new Person("John Doe", 30);

        // 2. Auto-generated toString()
        System.out.println(person1);  // Person[name=John Doe, age=30]

        // 3. Auto-generated equals() and hashCode()
        System.out.println("person1.equals(person3): " + person1.equals(person3));
        System.out.println("person1.hashCode(): " + person1.hashCode());

        // 4. Accessor methods (no get prefix)
        System.out.println("Name: " + person1.name());
        System.out.println("Age: " + person1.age());

        // 5. Records are immutable
        // person1.name = "New Name";  // Compile error!

        // 6. Records with validation
        try {
            Student student = new Student("Alice", -5);  // Invalid age
        } catch (IllegalArgumentException e) {
            System.out.println("Validation error: " + e.getMessage());
        }

        Student validStudent = new Student("Bob", 20);
        System.out.println(validStudent);

        // 7. Records with methods
        Point point1 = new Point(3, 4);
        System.out.println("Distance from origin: " + point1.distanceFromOrigin());

        // 8. Record with static methods
        Employee emp = Employee.createEmployee("Tom", 50000);
        System.out.println(emp);

        // 9. Compact constructor
        Product product = new Product("Laptop", 999.99);
        System.out.println(product);
    }
}

// Basic record
record Person(String name, int age) {}

// Record with compact constructor for validation
record Student(String name, int age) {
    // Compact constructor
    public Student {
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("Invalid age: " + age);
        }
    }
}

// Record with instance methods
record Point(double x, double y) {
    public double distanceFromOrigin() {
        return Math.sqrt(x * x + y * y);
    }
}

// Record with static factory method
record Employee(String name, double salary) {
    public static Employee createEmployee(String name, double salary) {
        return new Employee(name, salary);
    }
}

// Record with data transformation
record Product(String name, double price) {
    // Normalize name in compact constructor
    public Product {
        name = name.trim().toUpperCase();
        if (price < 0) {
            price = 0.0;
        }
    }
}
