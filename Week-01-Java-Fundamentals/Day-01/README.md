# Day 1: Environment Setup & Java Basics

## 🎯 Learning Objectives
- Set up Java development environment
- Understand JDK versions (8, 11, 17, 21)
- Write and run your first Java program
- Learn about variables and data types
- Master basic operators

## 📚 Theory

### What is Java?
Java is a **platform-independent**, **object-oriented** programming language used for:
- Enterprise applications (Banking, E-commerce)
- Android mobile apps
- Web applications
- Cloud services
- Big Data processing

### JDK Versions - What's Different?

#### JDK 8 (2014) - Still widely used in industry
- Lambda expressions
- Stream API
- Default methods in interfaces
- Date/Time API

#### JDK 11 (2018) - LTS (Long Term Support)
- String methods (isBlank, lines, strip)
- Local variable syntax (var)
- HTTP Client API

#### JDK 17 (2021) - Current LTS
- Sealed classes
- Pattern matching for switch
- Records
- Text blocks

#### JDK 21 (2023) - Latest LTS
- Virtual threads
- Sequenced collections
- Pattern matching enhancements
- Record patterns

**For this course, we'll use JDK 17 (industry standard) and learn features from all versions.**

### Java Program Structure
```java
public class ClassName {
    public static void main(String[] args) {
        // Your code here
    }
}
```

**Key Points**:
- `public class ClassName` - Must match filename (ClassName.java)
- `main` method - Entry point of program
- `String[] args` - Command-line arguments
- Curly braces `{}` - Define code blocks
- Semicolon `;` - End of statements

### Data Types

#### Primitive Types (8 types):
| Type | Size | Range | Example |
|------|------|-------|---------|
| byte | 1 byte | -128 to 127 | `byte age = 25;` |
| short | 2 bytes | -32,768 to 32,767 | `short year = 2024;` |
| int | 4 bytes | -2^31 to 2^31-1 | `int salary = 50000;` |
| long | 8 bytes | -2^63 to 2^63-1 | `long population = 8000000000L;` |
| float | 4 bytes | Decimal numbers | `float price = 99.99f;` |
| double | 8 bytes | Decimal numbers | `double pi = 3.14159;` |
| char | 2 bytes | Single character | `char grade = 'A';` |
| boolean | 1 bit | true/false | `boolean isActive = true;` |

#### Reference Types:
- String
- Arrays
- Classes
- Interfaces

### Variables
```java
// Declaration
int age;

// Initialization
age = 25;

// Declaration + Initialization
int salary = 50000;

// Multiple variables
int a = 10, b = 20, c = 30;

// Constants (cannot be changed)
final double PI = 3.14159;
```

### Operators

#### Arithmetic Operators:
```java
int a = 10, b = 3;
int sum = a + b;      // 13
int diff = a - b;     // 7
int product = a * b;  // 30
int quotient = a / b; // 3 (integer division)
int remainder = a % b; // 1 (modulo)
```

#### Comparison Operators:
```java
a == b  // Equal to
a != b  // Not equal to
a > b   // Greater than
a < b   // Less than
a >= b  // Greater than or equal to
a <= b  // Less than or equal to
```

#### Logical Operators:
```java
&&  // AND
||  // OR
!   // NOT
```

#### Assignment Operators:
```java
int x = 10;
x += 5;  // x = x + 5 → 15
x -= 3;  // x = x - 3 → 12
x *= 2;  // x = x * 2 → 24
x /= 4;  // x = x / 4 → 6
x %= 4;  // x = x % 4 → 2
```

## 💻 Hands-On Examples

### Example 1: Hello World
**File**: `HelloWorld.java`
- Basic program structure
- Print to console

### Example 2: Variables and Data Types
**File**: `DataTypesDemo.java`
- All primitive types
- Type casting
- Constants

### Example 3: Arithmetic Calculator
**File**: `Calculator.java`
- All arithmetic operations
- User input using Scanner

### Example 4: Type Conversion
**File**: `TypeConversion.java`
- Implicit conversion (widening)
- Explicit conversion (narrowing)
- String to number conversion

### Example 5: Operators Demo
**File**: `OperatorsDemo.java`
- Arithmetic operators
- Comparison operators
- Logical operators

## 🏆 Real-World Challenge: Employee Salary Calculator

**File**: `EmployeeSalaryCalculator.java`

**Requirements**:
Build a program that calculates employee take-home salary:
1. Input: Basic salary, HRA %, DA %, Tax %
2. Calculate:
   - Gross Salary = Basic + HRA + DA
   - Tax Amount = Gross Salary × Tax %
   - Net Salary = Gross Salary - Tax
3. Output: Detailed salary slip

**Example Output**:
```
======= SALARY SLIP =======
Employee Name: John Doe
Basic Salary: ₹50,000
HRA (20%): ₹10,000
DA (15%): ₹7,500
---------------------------
Gross Salary: ₹67,500
Tax (10%): ₹6,750
---------------------------
Net Salary: ₹60,750
===========================
```

## 📝 Exercises

### Exercise 1: Personal Info
Create a program that stores and displays:
- Name (String)
- Age (int)
- Height in meters (double)
- Gender (char: 'M'/'F')
- Is Student (boolean)

### Exercise 2: Simple Interest Calculator
Formula: SI = (P × R × T) / 100
- P = Principal amount
- R = Rate of interest
- T = Time period

### Exercise 3: Temperature Converter
Convert Celsius to Fahrenheit
Formula: F = (C × 9/5) + 32

### Exercise 4: Swap Two Numbers
Swap without using third variable

### Exercise 5: Area Calculator
Calculate area of:
- Circle (πr²)
- Rectangle (l × w)
- Triangle (½ × b × h)

## 🎓 Interview Questions

1. What is the difference between JDK, JRE, and JVM?
2. Why is Java platform-independent?
3. What is the difference between `int` and `Integer`?
4. What is type casting? Explain with example.
5. Can we change the value of a `final` variable?

## 🔍 Common Mistakes to Avoid

1. ❌ Filename doesn't match class name
   ```java
   // File: Test.java
   public class MyClass { } // ERROR!
   ```

2. ❌ Missing semicolon
   ```java
   int age = 25 // ERROR!
   ```

3. ❌ Using uninitialized variable
   ```java
   int x;
   System.out.println(x); // ERROR!
   ```

4. ❌ Integer division
   ```java
   int result = 5 / 2; // result = 2 (not 2.5!)
   double correct = 5.0 / 2; // 2.5
   ```

## 🚀 Next Steps

After completing Day 1:
1. Run all 5 examples
2. Complete all 5 exercises
3. Solve the Employee Salary Calculator challenge
4. Commit your code to GitHub
5. Share your first program on Discord!

## 📚 Additional Resources

- [Java Naming Conventions](https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html)
- [Java Style Guide](https://google.github.io/styleguide/javaguide.html)

---

**Remember**: The best way to learn programming is to write code, make mistakes, fix them, and repeat!

**Time to code!** 💪
