/**
 * Day 1 - Example 2: Variables and Data Types
 *
 * This program demonstrates all primitive data types in Java
 * and how to declare, initialize, and use variables.
 *
 * Learning Points:
 * - 8 primitive data types
 * - Variable declaration and initialization
 * - Constants using final keyword
 * - Type sizes and ranges
 */

public class DataTypesDemo {

    public static void main(String[] args) {
        System.out.println("=== JAVA DATA TYPES DEMO ===\n");

        // 1. INTEGER TYPES
        System.out.println("1. INTEGER TYPES:");
        System.out.println("-".repeat(40));

        byte age = 25;
        System.out.println("byte age = " + age);
        System.out.println("Size: 1 byte | Range: -128 to 127\n");

        short year = 2024;
        System.out.println("short year = " + year);
        System.out.println("Size: 2 bytes | Range: -32,768 to 32,767\n");

        int salary = 75000;
        System.out.println("int salary = " + salary);
        System.out.println("Size: 4 bytes | Range: -2^31 to 2^31-1\n");

        long population = 8000000000L; // Note the 'L' suffix for long
        System.out.println("long population = " + population);
        System.out.println("Size: 8 bytes | Range: -2^63 to 2^63-1\n");

        // 2. FLOATING-POINT TYPES
        System.out.println("\n2. FLOATING-POINT TYPES:");
        System.out.println("-".repeat(40));

        float price = 99.99f; // Note the 'f' suffix for float
        System.out.println("float price = " + price);
        System.out.println("Size: 4 bytes | Precision: ~7 decimal digits\n");

        double pi = 3.14159265359;
        System.out.println("double pi = " + pi);
        System.out.println("Size: 8 bytes | Precision: ~15 decimal digits\n");

        // 3. CHARACTER TYPE
        System.out.println("\n3. CHARACTER TYPE:");
        System.out.println("-".repeat(40));

        char grade = 'A';
        char symbol = '$';
        System.out.println("char grade = '" + grade + "'");
        System.out.println("char symbol = '" + symbol + "'");
        System.out.println("Size: 2 bytes | Unicode characters\n");

        // 4. BOOLEAN TYPE
        System.out.println("\n4. BOOLEAN TYPE:");
        System.out.println("-".repeat(40));

        boolean isJavaFun = true;
        boolean isPythonBetter = false;
        System.out.println("boolean isJavaFun = " + isJavaFun);
        System.out.println("boolean isPythonBetter = " + isPythonBetter);
        System.out.println("Size: 1 bit | Values: true or false\n");

        // 5. STRING (Reference Type)
        System.out.println("\n5. STRING (Reference Type):");
        System.out.println("-".repeat(40));

        String name = "John Doe";
        String course = "Java Full Stack Development";
        System.out.println("String name = \"" + name + "\"");
        System.out.println("String course = \"" + course + "\"\n");

        // 6. CONSTANTS (final keyword)
        System.out.println("\n6. CONSTANTS:");
        System.out.println("-".repeat(40));

        final double PI = 3.14159;
        final int MAX_STUDENTS = 50;
        System.out.println("final double PI = " + PI);
        System.out.println("final int MAX_STUDENTS = " + MAX_STUDENTS);
        System.out.println("Constants cannot be changed after initialization\n");

        // Trying to change constant (this will cause compilation error)
        // PI = 3.14; // ERROR: cannot assign a value to final variable

        // 7. MULTIPLE DECLARATIONS
        System.out.println("\n7. MULTIPLE DECLARATIONS:");
        System.out.println("-".repeat(40));

        int a = 10, b = 20, c = 30;
        System.out.println("int a = " + a + ", b = " + b + ", c = " + c);

        // 8. DEFAULT VALUES (for class variables only)
        System.out.println("\n8. DEFAULT VALUES (for class variables):");
        System.out.println("-".repeat(40));
        System.out.println("int: 0");
        System.out.println("double: 0.0");
        System.out.println("boolean: false");
        System.out.println("char: '\\u0000' (null character)");
        System.out.println("Reference types: null");
        System.out.println("Note: Local variables must be initialized before use!");

        // 9. VARIABLE NAMING CONVENTIONS
        System.out.println("\n9. NAMING CONVENTIONS:");
        System.out.println("-".repeat(40));
        System.out.println("Variables: camelCase (firstName, totalAmount)");
        System.out.println("Constants: UPPER_CASE (MAX_VALUE, PI)");
        System.out.println("Classes: PascalCase (EmployeeSalaryCalculator)");

        // 10. PRACTICAL EXAMPLE
        System.out.println("\n10. PRACTICAL EXAMPLE - Student Info:");
        System.out.println("-".repeat(40));

        String studentName = "Alice Smith";
        int studentAge = 22;
        char studentGrade = 'A';
        double studentGPA = 3.85;
        boolean isScholarship = true;

        System.out.println("Name: " + studentName);
        System.out.println("Age: " + studentAge + " years");
        System.out.println("Grade: " + studentGrade);
        System.out.println("GPA: " + studentGPA);
        System.out.println("Scholarship: " + isScholarship);

        System.out.println("\n" + "=".repeat(40));
        System.out.println("End of Data Types Demo!");
        System.out.println("=".repeat(40));
    }
}

/*
 * KEY TAKEAWAYS:
 * 1. Java has 8 primitive types: byte, short, int, long, float, double, char, boolean
 * 2. Use 'int' for most integer calculations
 * 3. Use 'double' for decimal numbers
 * 4. Use 'boolean' for true/false conditions
 * 5. String is a reference type (class), not primitive
 * 6. Constants use 'final' keyword and UPPER_CASE naming
 * 7. Choose appropriate type based on range and memory requirements
 * 8. Local variables must be initialized before use
 */
