/**
 * JDK 8 Features - Lambda Expressions (2014)
 * Key Feature: Functional Programming Support
 */
package jdk8;

import java.util.*;
import java.util.function.*;

public class Example01_JDK8_Lambda {
    public static void main(String[] args) {

        // 1. Lambda with Comparator
        List<String> names = Arrays.asList("Charlie", "Alice", "Bob");
        Collections.sort(names, (a, b) -> a.compareTo(b));
        System.out.println("Sorted: " + names);

        // 2. forEach with Lambda
        names.forEach(name -> System.out.println("Hello, " + name));

        // 3. Predicate
        Predicate<Integer> isAdult = age -> age >= 18;
        System.out.println("Is 20 adult? " + isAdult.test(20));

        // 4. Function
        Function<String, Integer> length = str -> str.length();
        System.out.println("Length: " + length.apply("Lambda"));

        // 5. Consumer
        Consumer<String> printer = msg -> System.out.println("Message: " + msg);
        printer.accept("JDK 8 Rocks!");
    }
}
