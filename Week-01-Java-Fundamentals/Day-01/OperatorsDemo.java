/**
 * Day 1 - Example 5: Operators Demonstration
 *
 * This program demonstrates all types of operators in Java:
 * - Arithmetic operators
 * - Relational/Comparison operators
 * - Logical operators
 * - Assignment operators
 * - Unary operators
 * - Ternary operator
 * - Bitwise operators (bonus)
 *
 * Learning Points:
 * - Understanding operator precedence
 * - Short-circuit evaluation
 * - Increment/Decrement operators
 * - Compound assignment operators
 */

public class OperatorsDemo {

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("              JAVA OPERATORS DEMONSTRATION");
        System.out.println("=".repeat(60));

        // ========================================
        // 1. ARITHMETIC OPERATORS
        // ========================================
        System.out.println("\n1. ARITHMETIC OPERATORS (+, -, *, /, %):");
        System.out.println("-".repeat(60));

        int a = 10, b = 3;
        System.out.println("a = " + a + ", b = " + b);
        System.out.println("a + b = " + (a + b));  // Addition
        System.out.println("a - b = " + (a - b));  // Subtraction
        System.out.println("a * b = " + (a * b));  // Multiplication
        System.out.println("a / b = " + (a / b));  // Division (integer)
        System.out.println("a % b = " + (a % b));  // Modulo (remainder)

        // ========================================
        // 2. RELATIONAL/COMPARISON OPERATORS
        // ========================================
        System.out.println("\n2. RELATIONAL OPERATORS (==, !=, >, <, >=, <=):");
        System.out.println("-".repeat(60));

        int x = 15, y = 20;
        System.out.println("x = " + x + ", y = " + y);
        System.out.println("x == y: " + (x == y));  // Equal to
        System.out.println("x != y: " + (x != y));  // Not equal to
        System.out.println("x > y:  " + (x > y));   // Greater than
        System.out.println("x < y:  " + (x < y));   // Less than
        System.out.println("x >= y: " + (x >= y));  // Greater than or equal
        System.out.println("x <= y: " + (x <= y));  // Less than or equal

        // ========================================
        // 3. LOGICAL OPERATORS
        // ========================================
        System.out.println("\n3. LOGICAL OPERATORS (&&, ||, !):");
        System.out.println("-".repeat(60));

        boolean isStudent = true;
        boolean hasDiscount = false;
        int age = 22;

        System.out.println("isStudent = " + isStudent);
        System.out.println("hasDiscount = " + hasDiscount);
        System.out.println("age = " + age);
        System.out.println();

        // AND operator (both must be true)
        boolean canGetStudentDiscount = isStudent && age < 25;
        System.out.println("isStudent && age < 25: " + canGetStudentDiscount);

        // OR operator (at least one must be true)
        boolean eligibleForDiscount = isStudent || hasDiscount;
        System.out.println("isStudent || hasDiscount: " + eligibleForDiscount);

        // NOT operator (negation)
        boolean notStudent = !isStudent;
        System.out.println("!isStudent: " + notStudent);

        // Complex condition
        boolean complexCondition = (age > 18 && age < 65) || isStudent;
        System.out.println("(age > 18 && age < 65) || isStudent: " + complexCondition);

        // ========================================
        // 4. SHORT-CIRCUIT EVALUATION
        // ========================================
        System.out.println("\n4. SHORT-CIRCUIT EVALUATION:");
        System.out.println("-".repeat(60));

        int num = 0;
        // && short-circuits: if first is false, second is not evaluated
        boolean result1 = (num != 0) && (10 / num > 5); // Safe! Won't divide by zero
        System.out.println("(num != 0) && (10 / num > 5): " + result1);
        System.out.println("Second condition not evaluated (short-circuit)");

        int score = 85;
        // || short-circuits: if first is true, second is not evaluated
        boolean passed = (score >= 60) || (score >= 40 && hasExtraCredit());
        System.out.println("Passed: " + passed + " (method call avoided due to short-circuit)");

        // ========================================
        // 5. ASSIGNMENT OPERATORS
        // ========================================
        System.out.println("\n5. ASSIGNMENT OPERATORS (=, +=, -=, *=, /=, %=):");
        System.out.println("-".repeat(60));

        int value = 100;
        System.out.println("Initial value: " + value);

        value += 10;  // value = value + 10
        System.out.println("After value += 10: " + value);

        value -= 30;  // value = value - 30
        System.out.println("After value -= 30: " + value);

        value *= 2;   // value = value * 2
        System.out.println("After value *= 2: " + value);

        value /= 4;   // value = value / 4
        System.out.println("After value /= 4: " + value);

        value %= 10;  // value = value % 10
        System.out.println("After value %= 10: " + value);

        // ========================================
        // 6. UNARY OPERATORS
        // ========================================
        System.out.println("\n6. UNARY OPERATORS (++, --, +, -, !):");
        System.out.println("-".repeat(60));

        int count = 5;
        System.out.println("Initial count: " + count);

        // Post-increment (use value, then increment)
        System.out.println("count++: " + count++);  // Prints 5, then becomes 6
        System.out.println("After post-increment: " + count);  // Prints 6

        // Pre-increment (increment, then use value)
        System.out.println("++count: " + ++count);  // Becomes 7, then prints 7
        System.out.println("After pre-increment: " + count);  // Prints 7

        // Post-decrement
        System.out.println("count--: " + count--);  // Prints 7, then becomes 6
        System.out.println("After post-decrement: " + count);  // Prints 6

        // Pre-decrement
        System.out.println("--count: " + --count);  // Becomes 5, then prints 5

        // Unary plus and minus
        int positive = +10;
        int negative = -10;
        System.out.println("Unary +: " + positive);
        System.out.println("Unary -: " + negative);

        // ========================================
        // 7. TERNARY OPERATOR
        // ========================================
        System.out.println("\n7. TERNARY OPERATOR (condition ? true : false):");
        System.out.println("-".repeat(60));

        int studentAge = 17;
        String eligibility = (studentAge >= 18) ? "Eligible to vote" : "Not eligible";
        System.out.println("Age: " + studentAge);
        System.out.println("Voting eligibility: " + eligibility);

        // Nested ternary
        int marks = 75;
        String grade = (marks >= 90) ? "A" :
                       (marks >= 80) ? "B" :
                       (marks >= 70) ? "C" :
                       (marks >= 60) ? "D" : "F";
        System.out.println("Marks: " + marks);
        System.out.println("Grade: " + grade);

        // Finding max of two numbers
        int num1 = 45, num2 = 78;
        int max = (num1 > num2) ? num1 : num2;
        System.out.println("Max of " + num1 + " and " + num2 + ": " + max);

        // ========================================
        // 8. INSTANCEOF OPERATOR
        // ========================================
        System.out.println("\n8. INSTANCEOF OPERATOR:");
        System.out.println("-".repeat(60));

        String text = "Hello";
        Integer number = 100;

        System.out.println("\"Hello\" instanceof String: " + (text instanceof String));
        System.out.println("100 instanceof Integer: " + (number instanceof Integer));
        System.out.println("\"Hello\" instanceof Object: " + (text instanceof Object));

        // ========================================
        // 9. OPERATOR PRECEDENCE
        // ========================================
        System.out.println("\n9. OPERATOR PRECEDENCE:");
        System.out.println("-".repeat(60));

        int result = 10 + 5 * 2;  // Multiplication before addition
        System.out.println("10 + 5 * 2 = " + result + " (not 30!)");

        int result2 = (10 + 5) * 2;  // Parentheses first
        System.out.println("(10 + 5) * 2 = " + result2);

        boolean complexResult = 5 > 3 && 10 < 20 || false;
        System.out.println("5 > 3 && 10 < 20 || false = " + complexResult);

        // ========================================
        // 10. PRACTICAL EXAMPLE
        // ========================================
        System.out.println("\n10. PRACTICAL EXAMPLE - Shopping Discount Calculator:");
        System.out.println("-".repeat(60));

        double purchaseAmount = 1500.0;
        boolean isMember = true;
        boolean hasPromoCode = false;
        int loyaltyPoints = 250;

        System.out.println("Purchase Amount: ₹" + purchaseAmount);
        System.out.println("Is Member: " + isMember);
        System.out.println("Has Promo Code: " + hasPromoCode);
        System.out.println("Loyalty Points: " + loyaltyPoints);
        System.out.println();

        // Calculate discount
        double discount = 0;

        // Member discount: 10%
        if (isMember) {
            discount += purchaseAmount * 0.10;
        }

        // Promo code discount: 5%
        if (hasPromoCode) {
            discount += purchaseAmount * 0.05;
        }

        // Loyalty points: ₹1 per 10 points
        discount += loyaltyPoints / 10.0;

        // Bulk purchase discount: 15% for purchases above ₹1000
        if (purchaseAmount > 1000) {
            discount += purchaseAmount * 0.15;
        }

        double finalAmount = purchaseAmount - discount;

        System.out.println("Total Discount: ₹" + discount);
        System.out.println("Final Amount: ₹" + finalAmount);
        System.out.println("You saved: ₹" + discount + " (" +
                String.format("%.1f", (discount / purchaseAmount * 100)) + "%)");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("End of Operators Demo!");
        System.out.println("=".repeat(60));
    }

    // Helper method for short-circuit example
    static boolean hasExtraCredit() {
        System.out.println("  [hasExtraCredit() method was called]");
        return true;
    }
}

/*
 * OPERATOR PRECEDENCE (Highest to Lowest):
 * 1. Postfix: expr++, expr--
 * 2. Unary: ++expr, --expr, +expr, -expr, !
 * 3. Multiplicative: *, /, %
 * 4. Additive: +, -
 * 5. Relational: <, >, <=, >=, instanceof
 * 6. Equality: ==, !=
 * 7. Logical AND: &&
 * 8. Logical OR: ||
 * 9. Ternary: ? :
 * 10. Assignment: =, +=, -=, *=, /=, %=
 *
 * Use parentheses () to make precedence explicit!
 */

/*
 * KEY TAKEAWAYS:
 *
 * 1. ARITHMETIC: +, -, *, /, %
 *    - Watch for integer division!
 *
 * 2. RELATIONAL: ==, !=, >, <, >=, <=
 *    - Result is always boolean
 *
 * 3. LOGICAL: &&, ||, !
 *    - Short-circuit evaluation in && and ||
 *
 * 4. UNARY: ++, --
 *    - Pre-increment: ++i (increment first, use later)
 *    - Post-increment: i++ (use first, increment later)
 *
 * 5. TERNARY: condition ? value1 : value2
 *    - Compact if-else
 *    - Avoid deeply nested ternaries
 *
 * 6. ASSIGNMENT: =, +=, -=, *=, /=, %=
 *    - Compound operators modify and assign
 *
 * 7. PRECEDENCE:
 *    - (), *, /, %, +, -, <, >, ==, !=, &&, ||, =
 *    - Use parentheses for clarity!
 */
