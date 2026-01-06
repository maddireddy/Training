/**
 * JDK 11 Features - var in Lambda Parameters (Enhanced from JDK 10)
 * Key Feature: Local Variable Type Inference
 */
package jdk11;

import java.util.*;
import java.util.function.*;

public class Example05_JDK11_LocalVariableTypeInference {
    public static void main(String[] args) {

        // 1. var with lambda parameters (JDK 11)
        BiFunction<Integer, Integer, Integer> add = (var a, var b) -> a + b;
        System.out.println("Sum: " + add.apply(5, 3));

        // 2. var with annotations in lambda
        Consumer<String> printer = (var str) -> System.out.println(str);
        printer.accept("Hello JDK 11");

        // 3. var in loops
        var numbers = List.of(1, 2, 3, 4, 5);
        for (var num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();

        // 4. var with complex types
        var map = Map.of("Java", 11, "Python", 3, "JavaScript", 6);
        map.forEach((var key, var value) ->
            System.out.println(key + " version: " + value));

        // 5. var limitations (Compile errors if uncommented)
        // var x;  // Cannot infer type
        // var y = null;  // Cannot infer from null
        // var z = {1, 2, 3};  // Array initializer not allowed
    }
}
