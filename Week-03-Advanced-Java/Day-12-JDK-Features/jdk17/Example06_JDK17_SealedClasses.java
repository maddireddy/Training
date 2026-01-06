/**
 * JDK 17 Features - Sealed Classes (2021 LTS)
 * Key Feature: Restricted Class Hierarchies
 */
package jdk17;

public class Example06_JDK17_SealedClasses {
    public static void main(String[] args) {

        Shape circle = new Circle(5.0);
        Shape rectangle = new Rectangle(4.0, 6.0);
        Shape triangle = new Triangle(3.0, 4.0);

        System.out.println("Circle area: " + getArea(circle));
        System.out.println("Rectangle area: " + getArea(rectangle));
        System.out.println("Triangle area: " + getArea(triangle));

        // Pattern matching with sealed classes
        System.out.println("\nPattern Matching:");
        describeShape(circle);
        describeShape(rectangle);
        describeShape(triangle);
    }

    static double getArea(Shape shape) {
        // Exhaustive switch (compiler ensures all permitted types)
        return switch (shape) {
            case Circle c -> Math.PI * c.radius() * c.radius();
            case Rectangle r -> r.length() * r.width();
            case Triangle t -> 0.5 * t.base() * t.height();
        };
    }

    static void describeShape(Shape shape) {
        String description = switch (shape) {
            case Circle c -> "Circle with radius " + c.radius();
            case Rectangle r -> "Rectangle " + r.length() + "x" + r.width();
            case Triangle t -> "Triangle with base " + t.base();
        };
        System.out.println(description);
    }
}

// Sealed class - restricts which classes can extend it
sealed interface Shape permits Circle, Rectangle, Triangle {}

final class Circle implements Shape {
    private final double radius;
    Circle(double radius) { this.radius = radius; }
    double radius() { return radius; }
}

final class Rectangle implements Shape {
    private final double length, width;
    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    double length() { return length; }
    double width() { return width; }
}

final class Triangle implements Shape {
    private final double base, height;
    Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }
    double base() { return base; }
    double height() { return height; }
}
