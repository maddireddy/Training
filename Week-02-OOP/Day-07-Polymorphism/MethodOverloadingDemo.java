/**
 * Day 7 - Example 1: Method Overloading (Compile-Time Polymorphism)
 *
 * Enhanced version from your Learning repository
 * (Original: polyLearning/methodOverLoading/MethodOverloadLearning.java)
 *
 * Real-World Context:
 * Method overloading is used everywhere in real applications:
 * - System.out.println() - Can print int, String, double, Object, etc.
 * - Math.max() - Works with int and double
 * - String constructors - Multiple ways to create String
 * - Database queries - search(String), search(int), search(String, int)
 *
 * Compile-Time Polymorphism:
 * The compiler decides which method to call based on:
 * - Number of parameters
 * - Type of parameters
 * - Order of parameters
 *
 * Decision is made at COMPILE TIME, hence "Compile-Time Polymorphism"
 */

/**
 * Pattern Printer Class
 * Demonstrates method overloading with different parameter types
 */
class PatternPrinter {

    /**
     * Method 1: No parameters
     * Default pattern with asterisk (*)
     */
    public void display() {
        System.out.println("Default Pattern (No parameters):");
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    /**
     * Method 2: Single char parameter
     * Custom character pattern
     *
     * @param symbol Character to use in pattern
     */
    public void display(char symbol) {
        System.out.println("Custom Character Pattern (char parameter):");
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(symbol + " ");
            }
            System.out.println();
        }
    }

    /**
     * Method 3: String parameter
     * Pattern with string symbols
     *
     * @param symbol String to use in pattern
     */
    public void display(String symbol) {
        System.out.println("String Pattern (String parameter):");
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(symbol + " ");
            }
            System.out.println();
        }
    }

    /**
     * Method 4: int parameter
     * Number of rows for pattern
     *
     * @param rows Number of rows
     */
    public void display(int rows) {
        System.out.println("Variable Rows Pattern (int parameter):");
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    /**
     * Method 5: char and int parameters
     * Custom character with custom rows
     *
     * @param symbol Character to use
     * @param rows Number of rows
     */
    public void display(char symbol, int rows) {
        System.out.println("Custom Character + Rows (char, int parameters):");
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(symbol + " ");
            }
            System.out.println();
        }
    }

    /**
     * Method 6: int and char parameters (Different order!)
     * Shows that parameter ORDER matters
     *
     * @param rows Number of rows
     * @param symbol Character to use
     */
    public void display(int rows, char symbol) {
        System.out.println("Rows + Custom Character (int, char parameters):");
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(symbol + " ");
            }
            System.out.println();
        }
    }
}

/**
 * Calculator Class
 * Real-world example of method overloading
 */
class Calculator {

    /**
     * Add two integers
     */
    public int add(int a, int b) {
        System.out.println("Adding two integers");
        return a + b;
    }

    /**
     * Add three integers
     */
    public int add(int a, int b, int c) {
        System.out.println("Adding three integers");
        return a + b + c;
    }

    /**
     * Add two doubles
     */
    public double add(double a, double b) {
        System.out.println("Adding two doubles");
        return a + b;
    }

    /**
     * Add int and double (auto type promotion)
     */
    public double add(int a, double b) {
        System.out.println("Adding int and double");
        return a + b;
    }

    /**
     * Concatenate two strings (different interpretation of "add")
     */
    public String add(String a, String b) {
        System.out.println("Concatenating two strings");
        return a + b;
    }
}

/**
 * Main Class: Demonstrates Method Overloading
 */
public class MethodOverloadingDemo {

    public static void main(String[] args) {

        System.out.println("=".repeat(70));
        System.out.println("        METHOD OVERLOADING - COMPILE-TIME POLYMORPHISM");
        System.out.println("=".repeat(70));

        // ==========================================
        // 1. PATTERN PRINTER - FROM YOUR LEARNING REPO
        // ==========================================
        System.out.println("\n1. PATTERN PRINTER (From your Learning repository):");
        System.out.println("-".repeat(70));

        PatternPrinter printer = new PatternPrinter();

        // Call method without any argument
        printer.display();
        System.out.println();

        // Call method with a single char argument
        printer.display('#');
        System.out.println();

        // Call method with String argument
        printer.display("~");
        System.out.println();

        // Call method with int argument
        printer.display(7);
        System.out.println();

        // Call method with char and int
        printer.display('@', 4);
        System.out.println();

        // Call method with int and char (different order!)
        printer.display(6, '&');

        // ==========================================
        // 2. CALCULATOR - REAL-WORLD EXAMPLE
        // ==========================================
        System.out.println("\n2. CALCULATOR - REAL-WORLD OVERLOADING:");
        System.out.println("-".repeat(70));

        Calculator calc = new Calculator();

        // Compiler decides which method to call based on parameters
        int result1 = calc.add(5, 10);
        System.out.println("Result: " + result1);
        System.out.println();

        int result2 = calc.add(5, 10, 15);
        System.out.println("Result: " + result2);
        System.out.println();

        double result3 = calc.add(5.5, 10.5);
        System.out.println("Result: " + result3);
        System.out.println();

        double result4 = calc.add(5, 10.5);
        System.out.println("Result: " + result4);
        System.out.println();

        String result5 = calc.add("Hello", " World");
        System.out.println("Result: " + result5);

        // ==========================================
        // 3. METHOD OVERLOADING RULES
        // ==========================================
        System.out.println("\n3. METHOD OVERLOADING RULES:");
        System.out.println("-".repeat(70));

        System.out.println("✅ VALID OVERLOADING (Different signatures):");
        System.out.println("   - void method(int a)");
        System.out.println("   - void method(double a)");
        System.out.println("   - void method(int a, int b)");
        System.out.println("   - void method(int a, double b)");
        System.out.println("   - void method(double a, int b)  // Order matters!");
        System.out.println();

        System.out.println("❌ INVALID OVERLOADING (Same signature):");
        System.out.println("   - void method(int a)");
        System.out.println("   - int method(int a)  // ERROR! Return type doesn't matter");
        System.out.println();

        System.out.println("📝 KEY POINTS:");
        System.out.println("   1. Method name must be SAME");
        System.out.println("   2. Parameters must be DIFFERENT (number, type, or order)");
        System.out.println("   3. Return type CAN be different but doesn't matter for overloading");
        System.out.println("   4. Access modifiers can be different");

        // ==========================================
        // 4. REAL-WORLD EXAMPLES FROM JAVA API
        // ==========================================
        System.out.println("\n4. METHOD OVERLOADING IN JAVA API:");
        System.out.println("-".repeat(70));

        // System.out.println() is overloaded!
        System.out.println("println(int): " + 100);
        System.out.println("println(String): " + "Hello");
        System.out.println("println(double): " + 3.14);
        System.out.println("println(boolean): " + true);

        System.out.println();

        // String constructor is overloaded!
        String s1 = new String();                    // No argument
        String s2 = new String("Hello");             // String argument
        String s3 = new String(new char[]{'H','i'}); // char array argument

        System.out.println("String constructors: " + s1 + ", " + s2 + ", " + s3);

        // ==========================================
        // 5. TYPE PROMOTION IN OVERLOADING
        // ==========================================
        System.out.println("\n5. TYPE PROMOTION:");
        System.out.println("-".repeat(70));

        Promotion demo = new Promotion();

        demo.show(100);        // Exact match: show(int)
        demo.show('A');        // char promoted to int
        demo.show(100L);       // long promoted to double
        demo.show(3.14f);      // float promoted to double

        System.out.println("\n" + "=".repeat(70));
        System.out.println("End of Method Overloading Demo");
        System.out.println("=".repeat(70));
    }
}

/**
 * Helper class to demonstrate type promotion
 */
class Promotion {

    public void show(int a) {
        System.out.println("show(int): " + a);
    }

    public void show(double a) {
        System.out.println("show(double): " + a);
    }
}

/*
 * KEY CONCEPTS:
 *
 * 1. COMPILE-TIME POLYMORPHISM:
 *    - Decision made by compiler
 *    - Based on method signature (name + parameters)
 *    - Also called "Static Polymorphism"
 *
 * 2. METHOD SIGNATURE:
 *    - Method name + parameter list
 *    - Does NOT include return type
 *    - Does NOT include access modifiers
 *
 * 3. OVERLOADING RULES:
 *    ✅ Different number of parameters
 *    ✅ Different type of parameters
 *    ✅ Different order of parameters
 *    ❌ Different return type alone (not enough)
 *    ❌ Different access modifiers alone (not enough)
 *
 * 4. TYPE PROMOTION:
 *    byte → short → int → long → float → double
 *    char → int
 *
 *    If exact match not found, compiler promotes to next larger type
 *
 * 5. ADVANTAGES:
 *    - Code readability (same name for similar operations)
 *    - Flexibility (multiple ways to call same functionality)
 *    - Used extensively in Java API
 *
 * REAL-WORLD APPLICATIONS:
 *
 * - Database queries:
 *   search(String name)
 *   search(int id)
 *   search(String name, int age)
 *
 * - Payment processing:
 *   processPayment(double amount)
 *   processPayment(double amount, String currency)
 *   processPayment(double amount, String currency, String gateway)
 *
 * - Logging:
 *   log(String message)
 *   log(String message, int level)
 *   log(String message, int level, Exception e)
 *
 * INTERVIEW QUESTIONS:
 *
 * Q: Can we overload main method?
 * A: Yes! But JVM calls public static void main(String[])
 *
 * Q: Can we overload based on return type?
 * A: No. Method signature doesn't include return type.
 *
 * Q: Can we overload static methods?
 * A: Yes. Overloading applies to both static and non-static methods.
 *
 * Q: What's the difference between overloading and overriding?
 * A: Overloading = Same class, different parameters (compile-time)
 *    Overriding = Different class (child), same signature (runtime)
 */
