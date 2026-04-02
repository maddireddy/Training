/**
 * Custom Functional Interfaces
 * Demonstrates: Creating and using custom functional interfaces, @FunctionalInterface
 */
package functional_interfaces;

import java.util.*;

@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);

    // Default methods allowed
    default int square(int n) {
        return n * n;
    }

    // Static methods allowed
    static int cube(int n) {
        return n * n * n;
    }
}

@FunctionalInterface
interface StringProcessor {
    String process(String input);
}

@FunctionalInterface
interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}

@FunctionalInterface
interface Validator<T> {
    boolean validate(T value);

    default Validator<T> and(Validator<T> other) {
        return value -> this.validate(value) && other.validate(value);
    }

    default Validator<T> or(Validator<T> other) {
        return value -> this.validate(value) || other.validate(value);
    }
}

@FunctionalInterface
interface DataTransformer<I, O> {
    O transform(I input) throws Exception;

    default O safeTransform(I input, O defaultValue) {
        try {
            return transform(input);
        } catch (Exception e) {
            return defaultValue;
        }
    }
}

public class Example12_CustomFunctionalInterfaces {

    public static void main(String[] args) {
        System.out.println("=== Custom Functional Interfaces ===\n");

        // 1. Basic custom interface
        demonstrateCalculator();

        // 2. String processing interface
        demonstrateStringProcessor();

        // 3. Three-parameter function
        demonstrateTriFunction();

        // 4. Validator with composition
        demonstrateValidator();

        // 5. Data transformer with error handling
        demonstrateDataTransformer();

        // 6. Real-world examples
        demonstrateRealWorld();
    }

    /**
     * Calculator interface
     */
    static void demonstrateCalculator() {
        System.out.println("=== Calculator Interface ===");

        // Implement with lambda
        Calculator add = (a, b) -> a + b;
        Calculator subtract = (a, b) -> a - b;
        Calculator multiply = (a, b) -> a * b;
        Calculator divide = (a, b) -> b != 0 ? a / b : 0;

        System.out.println("10 + 5 = " + add.calculate(10, 5));
        System.out.println("10 - 5 = " + subtract.calculate(10, 5));
        System.out.println("10 * 5 = " + multiply.calculate(10, 5));
        System.out.println("10 / 5 = " + divide.calculate(10, 5));

        // Use default method
        System.out.println("Square of 5: " + add.square(5));

        // Use static method
        System.out.println("Cube of 3: " + Calculator.cube(3));
    }

    /**
     * StringProcessor interface
     */
    static void demonstrateStringProcessor() {
        System.out.println("\n=== StringProcessor Interface ===");

        StringProcessor toUpper = String::toUpperCase;
        StringProcessor toLower = String::toLowerCase;
        StringProcessor reverse = s -> new StringBuilder(s).reverse().toString();
        StringProcessor removeSpaces = s -> s.replaceAll("\\s+", "");

        String input = "Hello World";

        System.out.println("Original: " + input);
        System.out.println("Upper: " + toUpper.process(input));
        System.out.println("Lower: " + toLower.process(input));
        System.out.println("Reverse: " + reverse.process(input));
        System.out.println("No spaces: " + removeSpaces.process(input));

        // Chain processors
        StringProcessor pipeline = s -> removeSpaces.process(toUpper.process(s));
        System.out.println("Pipeline: " + pipeline.process(input));
    }

    /**
     * TriFunction - three parameters
     */
    static void demonstrateTriFunction() {
        System.out.println("\n=== TriFunction Interface ===");

        // Sum of three numbers
        TriFunction<Integer, Integer, Integer, Integer> sum =
            (a, b, c) -> a + b + c;

        System.out.println("Sum of 1, 2, 3: " + sum.apply(1, 2, 3));

        // Concatenate three strings
        TriFunction<String, String, String, String> concat =
            (a, b, c) -> a + " " + b + " " + c;

        System.out.println("Concat: " + concat.apply("Hello", "from", "Java"));

        // Calculate area of triangle
        TriFunction<Double, Double, Double, Double> triangleArea =
            (base, height, scale) -> 0.5 * base * height * scale;

        System.out.println("Triangle area: " + triangleArea.apply(10.0, 5.0, 2.0));
    }

    /**
     * Validator with composition
     */
    static void demonstrateValidator() {
        System.out.println("\n=== Validator Interface ===");

        // Define validators
        Validator<String> notNull = s -> s != null;
        Validator<String> notEmpty = s -> !s.isEmpty();
        Validator<String> hasMinLength = s -> s.length() >= 3;
        Validator<String> hasMaxLength = s -> s.length() <= 20;
        Validator<String> isAlphabetic = s -> s.matches("[a-zA-Z]+");

        // Combine validators
        Validator<String> validName = notNull
            .and(notEmpty)
            .and(hasMinLength)
            .and(hasMaxLength)
            .and(isAlphabetic);

        // Test validation
        String[] names = {"Alice", "Bo", null, "", "VeryLongNameThatExceedsTwentyCharacters", "John123"};

        for (String name : names) {
            boolean valid = false;
            try {
                valid = validName.validate(name);
            } catch (Exception e) {
                // Handle null
            }
            System.out.println(name + " is valid: " + valid);
        }

        // Numeric validator
        Validator<Integer> positive = n -> n > 0;
        Validator<Integer> lessThan100 = n -> n < 100;
        Validator<Integer> even = n -> n % 2 == 0;

        Validator<Integer> validAge = positive.and(lessThan100);
        Validator<Integer> validEvenAge = validAge.and(even);

        System.out.println("\n30 is valid age: " + validAge.validate(30));
        System.out.println("30 is valid even age: " + validEvenAge.validate(30));
        System.out.println("25 is valid even age: " + validEvenAge.validate(25));
    }

    /**
     * DataTransformer with error handling
     */
    static void demonstrateDataTransformer() {
        System.out.println("\n=== DataTransformer Interface ===");

        // String to Integer transformer
        DataTransformer<String, Integer> stringToInt = Integer::parseInt;

        try {
            Integer num = stringToInt.transform("123");
            System.out.println("Transformed: " + num);

            // This will throw exception
            Integer invalid = stringToInt.transform("abc");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Using safe transform
        Integer result1 = stringToInt.safeTransform("456", 0);
        Integer result2 = stringToInt.safeTransform("invalid", 0);

        System.out.println("Safe transform '456': " + result1);
        System.out.println("Safe transform 'invalid': " + result2);

        // JSON to Object transformer
        DataTransformer<String, Map<String, String>> jsonParser = json -> {
            Map<String, String> map = new HashMap<>();
            // Simplified JSON parsing
            String content = json.replaceAll("[{}]", "");
            for (String pair : content.split(",")) {
                String[] kv = pair.split(":");
                if (kv.length == 2) {
                    map.put(kv[0].trim(), kv[1].trim());
                }
            }
            return map;
        };

        Map<String, String> parsed = jsonParser.safeTransform(
            "{name:Alice,age:30}",
            new HashMap<>()
        );
        System.out.println("\nParsed JSON: " + parsed);
    }

    /**
     * Real-world examples
     */
    static void demonstrateRealWorld() {
        System.out.println("\n=== Real-World Examples ===");

        // Email validator
        Validator<String> emailValidator = email ->
            email != null &&
            email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

        System.out.println("alice@example.com valid: " +
            emailValidator.validate("alice@example.com"));
        System.out.println("invalid-email valid: " +
            emailValidator.validate("invalid-email"));

        // Price calculator
        Calculator priceWithTax = (price, taxRate) ->
            price + (price * taxRate / 100);

        System.out.println("\nPrice $100 with 10% tax: $" +
            priceWithTax.calculate(100, 10));

        // Password strength validator
        Validator<String> strongPassword = password ->
            password != null &&
            password.length() >= 8 &&
            password.matches(".*[A-Z].*") &&      // Has uppercase
            password.matches(".*[a-z].*") &&      // Has lowercase
            password.matches(".*[0-9].*");         // Has digit

        System.out.println("\n'Password123' is strong: " +
            strongPassword.validate("Password123"));
        System.out.println("'weak' is strong: " +
            strongPassword.validate("weak"));
    }
}

/*
 * Custom Functional Interfaces Summary:
 *
 * 1. @FunctionalInterface ANNOTATION:
 *    - Optional but recommended
 *    - Compiler checks: exactly one abstract method
 *    - Documents intent
 *    - Prevents accidental modification
 *
 * 2. RULES:
 *    - Exactly ONE abstract method
 *    - Can have multiple default methods
 *    - Can have multiple static methods
 *    - Can override Object methods
 *
 * 3. WHEN TO CREATE CUSTOM:
 *    ✓ Domain-specific operations
 *    ✓ Need default/static methods
 *    ✓ Three or more parameters
 *    ✓ Checked exceptions
 *    ✓ Better naming for clarity
 *    ✗ Built-in functional interfaces sufficient
 *
 * 4. BUILT-IN VS CUSTOM:
 *
 *    Built-in (prefer when possible):
 *    - Function, Predicate, Consumer, Supplier
 *    - BiFunction, BiPredicate, BiConsumer
 *    - UnaryOperator, BinaryOperator
 *
 *    Custom (when needed):
 *    - Three+ parameters (TriFunction)
 *    - Domain-specific names (Calculator)
 *    - Custom default methods
 *    - Checked exceptions
 *
 * 5. COMMON PATTERNS:
 *
 *    @FunctionalInterface
 *    interface MyInterface<T, R> {
 *        R operation(T input);  // Single abstract method
 *
 *        default R safeOperation(T input, R defaultValue) {
 *            // Default implementation
 *        }
 *
 *        static R staticHelper() {
 *            // Static helper
 *        }
 *    }
 *
 * 6. COMPOSITION SUPPORT:
 *
 *    default MyInterface<T> and(MyInterface<T> other) {
 *        return t -> this.method(t) && other.method(t);
 *    }
 *
 *    default MyInterface<T> or(MyInterface<T> other) {
 *        return t -> this.method(t) || other.method(t);
 *    }
 *
 * 7. EXCEPTION HANDLING:
 *
 *    // With checked exception
 *    @FunctionalInterface
 *    interface ThrowingFunction<T, R> {
 *        R apply(T t) throws Exception;
 *    }
 *
 *    // With safe wrapper
 *    default R safeApply(T t, R defaultValue) {
 *        try {
 *            return apply(t);
 *        } catch (Exception e) {
 *            return defaultValue;
 *        }
 *    }
 *
 * 8. BEST PRACTICES:
 *    ✓ Use @FunctionalInterface annotation
 *    ✓ Name meaningfully for domain
 *    ✓ Add useful default methods
 *    ✓ Document expected behavior
 *    ✓ Consider generics for flexibility
 *    ✗ Don't create when built-in exists
 *    ✗ Don't add multiple abstract methods
 *
 * 9. USE CASES:
 *    - Business logic: Calculator, Validator, Processor
 *    - Data transformation: Parser, Converter, Mapper
 *    - Multi-parameter functions: TriFunction, QuadFunction
 *    - Domain-specific operations
 *    - Error handling wrappers
 *
 * 10. EXAMPLES:
 *
 *     // Calculator
 *     Calculator add = (a, b) -> a + b;
 *
 *     // Validator
 *     Validator<String> notEmpty = s -> !s.isEmpty();
 *
 *     // Transformer
 *     DataTransformer<String, Integer> parse = Integer::parseInt;
 *
 *     // TriFunction
 *     TriFunction<Integer, Integer, Integer, Integer> sum =
 *         (a, b, c) -> a + b + c;
 */
