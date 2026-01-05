/**
 * Day 1 - Example 4: Type Conversion and Casting
 *
 * This program demonstrates:
 * - Implicit type conversion (Widening)
 * - Explicit type casting (Narrowing)
 * - String to number conversion
 * - Number to String conversion
 *
 * Learning Points:
 * - Automatic type promotion
 * - Manual type casting
 * - Loss of precision
 * - Parsing strings
 */

public class TypeConversion {

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("           TYPE CONVERSION AND CASTING DEMO");
        System.out.println("=".repeat(60));

        // ========================================
        // 1. IMPLICIT CONVERSION (WIDENING)
        // ========================================
        System.out.println("\n1. IMPLICIT CONVERSION (Widening - Automatic):");
        System.out.println("-".repeat(60));
        System.out.println("Conversion order: byte → short → int → long → float → double");
        System.out.println();

        byte byteValue = 10;
        short shortValue = byteValue; // byte to short (automatic)
        int intValue = shortValue;     // short to int (automatic)
        long longValue = intValue;     // int to long (automatic)
        float floatValue = longValue;  // long to float (automatic)
        double doubleValue = floatValue; // float to double (automatic)

        System.out.println("byte value: " + byteValue);
        System.out.println("Converted to short: " + shortValue);
        System.out.println("Converted to int: " + intValue);
        System.out.println("Converted to long: " + longValue);
        System.out.println("Converted to float: " + floatValue);
        System.out.println("Converted to double: " + doubleValue);

        // ========================================
        // 2. EXPLICIT CASTING (NARROWING)
        // ========================================
        System.out.println("\n2. EXPLICIT CASTING (Narrowing - Manual):");
        System.out.println("-".repeat(60));
        System.out.println("Conversion order: double → float → long → int → short → byte");
        System.out.println("Warning: May lose precision or overflow!\n");

        double d = 100.99;
        float f = (float) d;      // double to float
        long l = (long) f;        // float to long (decimal part lost!)
        int i = (int) l;          // long to int
        short s = (short) i;      // int to short
        byte b = (byte) s;        // short to byte

        System.out.println("double value: " + d);
        System.out.println("Casted to float: " + f);
        System.out.println("Casted to long: " + l + " (decimal part lost)");
        System.out.println("Casted to int: " + i);
        System.out.println("Casted to short: " + s);
        System.out.println("Casted to byte: " + b);

        // ========================================
        // 3. PRECISION LOSS EXAMPLES
        // ========================================
        System.out.println("\n3. PRECISION LOSS EXAMPLES:");
        System.out.println("-".repeat(60));

        // Example 1: Double to Int
        double price = 99.99;
        int priceInt = (int) price; // Decimal part is truncated (not rounded!)
        System.out.println("Original double: " + price);
        System.out.println("Casted to int: " + priceInt + " (decimal truncated)");

        // Example 2: Large number to smaller type
        int largeNumber = 130;
        byte smallNumber = (byte) largeNumber; // Overflow!
        System.out.println("\nOriginal int: " + largeNumber);
        System.out.println("Casted to byte: " + smallNumber + " (overflow!)");
        System.out.println("Explanation: byte range is -128 to 127");

        // ========================================
        // 4. CHAR AND INT CONVERSION
        // ========================================
        System.out.println("\n4. CHAR AND INT CONVERSION:");
        System.out.println("-".repeat(60));

        char letter = 'A';
        int asciiValue = letter; // char to int (automatic)
        System.out.println("Character 'A' has ASCII value: " + asciiValue);

        char nextLetter = (char) (asciiValue + 1); // int to char (casting needed)
        System.out.println("Next character: " + nextLetter);

        // Number to char
        int code = 65;
        char character = (char) code;
        System.out.println("ASCII " + code + " represents: '" + character + "'");

        // ========================================
        // 5. STRING CONVERSIONS
        // ========================================
        System.out.println("\n5. STRING TO NUMBER CONVERSION:");
        System.out.println("-".repeat(60));

        // String to int
        String strNumber = "123";
        int number = Integer.parseInt(strNumber);
        System.out.println("String \"" + strNumber + "\" to int: " + number);

        // String to double
        String strDecimal = "45.67";
        double decimal = Double.parseDouble(strDecimal);
        System.out.println("String \"" + strDecimal + "\" to double: " + decimal);

        // String to long
        String strLong = "9876543210";
        long longNum = Long.parseLong(strLong);
        System.out.println("String \"" + strLong + "\" to long: " + longNum);

        // String to boolean
        String strBool = "true";
        boolean bool = Boolean.parseBoolean(strBool);
        System.out.println("String \"" + strBool + "\" to boolean: " + bool);

        // ========================================
        // 6. NUMBER TO STRING CONVERSION
        // ========================================
        System.out.println("\n6. NUMBER TO STRING CONVERSION:");
        System.out.println("-".repeat(60));

        // Method 1: String concatenation
        int age = 25;
        String ageStr1 = "" + age;
        System.out.println("Method 1 (concatenation): " + ageStr1 + " (type: String)");

        // Method 2: String.valueOf()
        String ageStr2 = String.valueOf(age);
        System.out.println("Method 2 (String.valueOf): " + ageStr2 + " (type: String)");

        // Method 3: Integer.toString()
        String ageStr3 = Integer.toString(age);
        System.out.println("Method 3 (Integer.toString): " + ageStr3 + " (type: String)");

        // ========================================
        // 7. PRACTICAL EXAMPLE
        // ========================================
        System.out.println("\n7. PRACTICAL EXAMPLE - User Age Calculation:");
        System.out.println("-".repeat(60));

        String birthYearStr = "1998";
        int birthYear = Integer.parseInt(birthYearStr);
        int currentYear = 2024;
        int calculatedAge = currentYear - birthYear;

        System.out.println("Birth Year (String): \"" + birthYearStr + "\"");
        System.out.println("Birth Year (int): " + birthYear);
        System.out.println("Current Year: " + currentYear);
        System.out.println("Calculated Age: " + calculatedAge + " years");

        // ========================================
        // 8. COMMON ERRORS
        // ========================================
        System.out.println("\n8. COMMON ERRORS TO AVOID:");
        System.out.println("-".repeat(60));

        System.out.println("❌ int x = 3.14;  // Compilation error!");
        System.out.println("✅ int x = (int) 3.14;  // OK, but x = 3");
        System.out.println();
        System.out.println("❌ String s = 123;  // Compilation error!");
        System.out.println("✅ String s = String.valueOf(123);  // OK");
        System.out.println();
        System.out.println("❌ int num = Integer.parseInt(\"abc\");  // Runtime error!");
        System.out.println("✅ Use try-catch or validate before parsing");

        // ========================================
        // 9. TYPE PROMOTION IN EXPRESSIONS
        // ========================================
        System.out.println("\n9. TYPE PROMOTION IN EXPRESSIONS:");
        System.out.println("-".repeat(60));

        byte b1 = 10;
        byte b2 = 20;
        // byte b3 = b1 + b2; // Error! Result is promoted to int
        int b3 = b1 + b2;  // Correct!
        System.out.println("byte + byte = int (automatic promotion)");
        System.out.println(b1 + " + " + b2 + " = " + b3);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("End of Type Conversion Demo!");
        System.out.println("=".repeat(60));
    }
}

/*
 * KEY TAKEAWAYS:
 *
 * 1. WIDENING (Implicit - Automatic):
 *    - Smaller type to larger type
 *    - No data loss
 *    - No casting needed
 *    - byte → short → int → long → float → double
 *
 * 2. NARROWING (Explicit - Manual):
 *    - Larger type to smaller type
 *    - May lose precision or overflow
 *    - Requires explicit cast: (type) value
 *    - double → float → long → int → short → byte
 *
 * 3. STRING PARSING:
 *    - Integer.parseInt(string) → int
 *    - Double.parseDouble(string) → double
 *    - Long.parseLong(string) → long
 *    - Boolean.parseBoolean(string) → boolean
 *    - Throws NumberFormatException if invalid
 *
 * 4. TO STRING:
 *    - String.valueOf(number) - recommended
 *    - Integer.toString(number) - type-specific
 *    - "" + number - quick but not recommended
 *
 * 5. TYPE PROMOTION:
 *    - byte, short, char promoted to int in expressions
 *    - If any operand is double, result is double
 *    - If any operand is float, result is float
 *    - If any operand is long, result is long
 *
 * 6. COMMON MISTAKES:
 *    - Forgetting to cast when narrowing
 *    - Not handling NumberFormatException
 *    - Assuming int division gives decimal result
 *    - Overflow when casting large values to small types
 */
