/**
 * Example 02: Built-in Functional Interfaces
 * Topic: Java 8 Functional Interfaces (java.util.function)
 *
 * Covers: Predicate, Consumer, Supplier, Function, UnaryOperator, BinaryOperator
 */
package functional_interfaces;

import java.util.function.*;

public class Example02_BuiltInFunctionalInterfaces {

    public static void main(String[] args) {

        // 1. Predicate<T> - Returns boolean
        System.out.println("=== Predicate<T> ===");
        Predicate<Integer> isEven = num -> num % 2 == 0;
        System.out.println("Is 4 even? " + isEven.test(4));
        System.out.println("Is 5 even? " + isEven.test(5));

        Predicate<String> startsWithA = str -> str.startsWith("A");
        System.out.println("Does 'Apple' start with A? " + startsWithA.test("Apple"));

        // 2. Consumer<T> - Accepts input, returns nothing
        System.out.println("\n=== Consumer<T> ===");
        Consumer<String> print = str -> System.out.println("Value: " + str);
        print.accept("Hello Lambda");

        Consumer<Integer> printSquare = num -> System.out.println(num + "² = " + (num * num));
        printSquare.accept(5);

        // 3. Supplier<T> - No input, returns value
        System.out.println("\n=== Supplier<T> ===");
        Supplier<Double> randomValue = () -> Math.random();
        System.out.println("Random: " + randomValue.get());

        Supplier<String> greeting = () -> "Hello from Supplier!";
        System.out.println(greeting.get());

        // 4. Function<T, R> - Takes input, returns output
        System.out.println("\n=== Function<T, R> ===");
        Function<String, Integer> stringLength = str -> str.length();
        System.out.println("Length of 'Lambda': " + stringLength.apply("Lambda"));

        Function<Integer, String> intToString = num -> "Number: " + num;
        System.out.println(intToString.apply(100));

        // 5. UnaryOperator<T> - Function<T, T>
        System.out.println("\n=== UnaryOperator<T> ===");
        UnaryOperator<Integer> square = x -> x * x;
        System.out.println("Square of 6: " + square.apply(6));

        UnaryOperator<String> toUpperCase = String::toUpperCase;
        System.out.println(toUpperCase.apply("lambda"));

        // 6. BinaryOperator<T> - BiFunction<T, T, T>
        System.out.println("\n=== BinaryOperator<T> ===");
        BinaryOperator<Integer> add = (a, b) -> a + b;
        System.out.println("5 + 3 = " + add.apply(5, 3));

        BinaryOperator<String> concat = (s1, s2) -> s1 + " " + s2;
        System.out.println(concat.apply("Hello", "World"));

        // 7. BiPredicate<T, U>
        System.out.println("\n=== BiPredicate<T, U> ===");
        BiPredicate<String, Integer> lengthCheck = (str, len) -> str.length() == len;
        System.out.println("Is 'Java' length 4? " + lengthCheck.test("Java", 4));

        // 8. BiConsumer<T, U>
        System.out.println("\n=== BiConsumer<T, U> ===");
        BiConsumer<String, Integer> printWithNumber = (str, num) ->
            System.out.println(str + ": " + num);
        printWithNumber.accept("Employee ID", 12345);

        // 9. BiFunction<T, U, R>
        System.out.println("\n=== BiFunction<T, U, R> ===");
        BiFunction<Integer, Integer, String> mathOperation = (a, b) ->
            a + " + " + b + " = " + (a + b);
        System.out.println(mathOperation.apply(10, 20));

        // Chaining functional interfaces
        System.out.println("\n=== Chaining ===");
        Function<Integer, Integer> multiplyBy2 = x -> x * 2;
        Function<Integer, Integer> add10 = x -> x + 10;
        Function<Integer, Integer> combined = multiplyBy2.andThen(add10);
        System.out.println("(5 * 2) + 10 = " + combined.apply(5));
    }
}
