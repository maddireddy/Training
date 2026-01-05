# Detailed Day-Wise Training Plan
## "Day-1 Job Ready" Standard - Real-World Scenarios

---

## 📘 How to Read This Plan

Each day follows this structure:
```
DAY X: TOPIC
├── Why This Matters (Real-World Context)
├── Morning Theory (30 mins)
├── Live Coding Demo (45 mins)
├── Lab Exercise (Student Practice)
├── Real-World Scenario (Capstone Exercise)
├── Git Commits (Exact commit messages)
└── Homework (Due next day)
```

---

# WEEK 1: Java Fundamentals

## DAY 1: Environment Setup & First Program

### 🌍 Real-World Context
**Why This Matters:**
- Companies use JDK 17 or 21 in production (LTS versions)
- IntelliJ is the industry standard IDE (used by Google, Amazon, Netflix)
- Every developer writes "Hello World" differently - comments matter!

### 📚 Morning Theory (9:00 AM - 9:30 AM)
1. JDK vs JRE vs JVM
2. Why Java is platform-independent
3. How compilation works: .java → javac → .class → JVM
4. Package structure in real projects

### 💻 Live Coding Demo (9:30 AM - 10:15 AM)
**Instructor codes while students watch:**

```java
// Show them how you actually code in industry:
// 1. Create package structure
// 2. Add class with proper naming
// 3. Write comments explaining WHY, not WHAT
// 4. Use System.out for debugging (yes, even in production!)
```

### 🎯 Lab Exercise (10:15 AM - 11:30 AM)
**Students code these (in order):**

1. **HelloWorld.java** (15 mins)
   - Print your name and batch number
   - Use `\n`, `\t` for formatting
   - Add multi-line comment explaining the program

2. **DataTypesDemo.java** (30 mins)
   - Create variables for all 8 primitive types
   - Print size and range of each
   - Demonstrate type overflow (byte example)

3. **Calculator.java** (45 mins)
   - Take 2 numbers as input
   - Perform all 5 arithmetic operations
   - Handle division by zero
   - Display results with 2 decimal precision

### 🏢 Real-World Scenario (11:30 AM - 12:30 PM)
**Scenario:** You're at TCS/Infosys on Day 1. Your manager asks you to calculate employee take-home salary.

**Task: EmployeeSalaryCalculator.java**

```
Input:
- Employee name
- Basic salary
- HRA percentage
- DA percentage
- Tax percentage

Process:
- Calculate gross salary
- Calculate deductions
- Calculate net salary

Output:
- Professional formatted salary slip
```

**Why This Exercise:**
- Uses variables (storage)
- Uses operators (calculations)
- Uses Scanner (input)
- Uses printf (formatted output)
- Solves a REAL HR department problem

### 📝 Git Commits (Exact Messages)

```bash
# After HelloWorld
git add Week-01-Java-Fundamentals/Day-01/HelloWorld.java
git commit -m "feat(day-01): add Hello World program

- Demonstrates basic Java structure
- Shows print vs println difference
- Includes special character examples
- Adds multi-line comments"

# After DataTypesDemo
git commit -m "feat(day-01): demonstrate all primitive data types

- Shows byte, short, int, long examples
- Demonstrates float vs double precision
- Includes char and boolean usage
- Shows type overflow behavior"

# After Calculator
git commit -m "feat(day-01): implement basic calculator

- Takes user input with Scanner
- Performs 5 arithmetic operations
- Handles division by zero
- Formats output with 2 decimal places"

# After TypeConversion
git commit -m "feat(day-01): demonstrate type conversion and casting

- Shows implicit widening conversion
- Demonstrates explicit narrowing cast
- Includes String to number parsing
- Handles NumberFormatException"

# After OperatorsDemo
git commit -m "feat(day-01): showcase all Java operators

- Arithmetic operators with examples
- Relational and logical operators
- Demonstrates short-circuit evaluation
- Includes ternary operator usage"

# After EmployeeSalaryCalculator
git commit -m "feat(day-01): build employee salary calculator

- Real-world HR payroll simulation
- Calculates gross and net salary
- Applies tax computation logic
- Generates formatted salary slip

This simulates a production payroll system used in companies."

# End of day - push all
git push origin day-01-java-basics
```

### 📚 Homework (Due Tomorrow Morning)
1. **Modify Calculator** to handle 3 numbers instead of 2
2. **Enhance Salary Calculator** to add bonus and overtime calculations
3. **Read:** Java naming conventions (share link)
4. **Challenge:** Build a simple interest calculator (P × R × T / 100)

### 🎤 Instructor Script (What to Say)
```
"In real companies, you won't just write code. You'll write code that OTHER
developers read 6 months later. That's why comments and clean variable names
matter MORE than the code itself.

When you join TCS tomorrow, and they ask you to calculate employee salary,
you won't Google 'how to calculate salary in Java'. You'll remember:
'Oh, I did this in training!' That's the goal."
```

---

## DAY 2: Control Flow & Decision Making

### 🌍 Real-World Context
**Why This Matters:**
- Every application has conditional logic (if user is premium, if payment is successful)
- Loops process collections (orders, products, users)
- Switch statements handle states (order status, user role)

**Real Example:** Amazon's checkout process uses dozens of if-else statements:
- If user is Prime member → free shipping
- If cart total > $25 → free shipping
- If out of stock → show message
- If payment fails → retry logic

### 📚 Morning Theory (9:00 AM - 9:30 AM)
1. Boolean logic and truth tables
2. Nested if-else vs else-if ladder
3. When to use switch vs if-else
4. Loop selection: for vs while vs do-while
5. break vs continue (and why to avoid them)

### 💻 Live Coding Demo (9:30 AM - 10:15 AM)
**Instructor codes ATM Machine logic:**

```java
// Show real ATM logic
int balance = 10000;
int choice = scanner.nextInt();

switch (choice) {
    case 1: // Check balance
        System.out.println("Balance: " + balance);
        break;
    case 2: // Withdraw
        // ... show withdrawal logic
        break;
    case 3: // Deposit
        // ... show deposit logic
        break;
    default:
        System.out.println("Invalid choice");
}

// Explain: This is EXACTLY how ATM software works!
```

### 🎯 Lab Exercise (10:15 AM - 11:30 AM)

1. **GradeCalculator.java** (20 mins)
   - Input: marks (0-100)
   - Output: Grade (A/B/C/D/F)
   - Use if-else ladder
   - Validate input range

2. **PatternPrinter.java** (30 mins)
   - Print 5 different patterns using nested loops
   - Pattern 1: Right triangle
   - Pattern 2: Inverted triangle
   - Pattern 3: Pyramid
   - Pattern 4: Diamond
   - Pattern 5: Number pyramid

3. **MenuDrivenCalculator.java** (40 mins)
   - Display menu (1.Add, 2.Sub, 3.Mul, 4.Div, 5.Exit)
   - Use switch statement
   - Loop until user exits
   - Validate inputs

### 🏢 Real-World Scenario (11:30 AM - 12:30 PM)
**Scenario:** You're building banking software. Implement ATM withdrawal logic.

**Task: ATMSimulator.java**

```
Features:
1. Check Balance
2. Withdraw Money
   - Validate amount (multiple of 100)
   - Check sufficient balance
   - Check daily limit (₹50,000)
   - Deduct and show new balance
3. Deposit Money
   - Accept amount
   - Update balance
4. Mini Statement (last 5 transactions)
5. Exit

Real-World Rules:
- Daily withdrawal limit
- Minimum balance ₹500
- Transaction limits
- Invalid PIN after 3 attempts (lock account)
```

**Why This Exercise:**
- State management (balance, transactions)
- Input validation (real banking rules)
- Business logic implementation
- Error handling (insufficient balance)
- This is ACTUAL banking software logic!

### 📝 Git Commits

```bash
git commit -m "feat(day-02): implement grade calculator with validation

- Accepts marks input with range validation
- Converts marks to letter grade
- Uses if-else ladder for grade ranges
- Handles edge cases (negative marks, >100)"

git commit -m "feat(day-02): create pattern printer with nested loops

- Implements 5 different patterns
- Uses nested for loops
- Demonstrates loop control flow
- Useful for understanding iteration logic"

git commit -m "feat(day-02): build menu-driven calculator

- Implements calculator with switch statement
- Loops until user exits
- Validates user input
- Demonstrates state management in loops"

git commit -m "feat(day-02): simulate ATM machine operations

- Implements real banking logic
- Validates withdrawal limits
- Maintains transaction history
- Handles account locking after failed attempts

This mirrors production banking software used by HDFC, ICICI banks."
```

### 📚 Homework
1. Add "Transfer Money" feature to ATM
2. Build a simple login system (3 attempts, then lock)
3. Create a number guessing game (1-100, max 7 attempts)

---

## DAY 3: Arrays & Strings

### 🌍 Real-World Context
**Where Arrays Are Used:**
- E-commerce: Array of products, prices, quantities
- Netflix: Array of movies, ratings
- Instagram: Array of posts, likes, comments
- EVERY application uses collections!

**Why Strings Matter:**
- User input is always String
- APIs send/receive JSON (String)
- Passwords, emails, names → all Strings
- String manipulation is 30% of interview questions!

### 📚 Morning Theory (9:00 AM - 9:30 AM)
1. Arrays vs ArrayList (when to use which)
2. Why arrays are 0-indexed
3. String immutability (why it matters for performance)
4. String pool concept
5. StringBuilder vs StringBuffer

### 💻 Live Coding Demo (9:30 AM - 10:15 AM)
**Show real e-commerce product array:**

```java
String[] products = {"Laptop", "Mouse", "Keyboard", "Monitor"};
double[] prices = {50000, 500, 1500, 15000};

// Find most expensive product
// Calculate total cart value
// Search for a product
// Sort products by price

// Explain: This is how Amazon stores cart items in memory!
```

### 🎯 Lab Exercise (10:15 AM - 11:30 AM)

1. **ArrayOperations.java** (30 mins)
   - Find max and min in array
   - Calculate average
   - Reverse array
   - Search element (linear search)
   - Remove duplicates

2. **StringManipulation.java** (30 mins)
   - Reverse a string
   - Check palindrome
   - Count vowels and consonants
   - Remove spaces
   - Convert case

3. **Matrix Operations.java** (30 mins)
   - 2D array (3×3 matrix)
   - Add two matrices
   - Transpose matrix
   - Print diagonal elements

### 🏢 Real-World Scenario (11:30 AM - 12:30 PM)
**Scenario:** You're building a student management system for a college.

**Task: StudentManagementSystem.java**

```
Store data for 5 students:
- Names (String array)
- Roll numbers (int array)
- Marks in 3 subjects (2D array)

Features:
1. Add student
2. Display all students
3. Search student by roll number
4. Calculate average marks
5. Find topper
6. Find subject-wise topper
7. Display students sorted by marks
8. Generate grade sheet

Real Constraints:
- Max 100 students
- Marks: 0-100
- Validate all inputs
```

**Why This Exercise:**
- Multi-array coordination (real database simulation)
- CRUD operations (Create, Read, Update, Delete)
- Search and sort algorithms
- Data validation
- This is how school management software works!

### 📝 Git Commits

```bash
git commit -m "feat(day-03): implement array operations

- Max, min, average calculations
- Array reversal algorithm
- Linear search implementation
- Duplicate removal logic"

git commit -m "feat(day-03): add string manipulation utilities

- String reversal without using reverse()
- Palindrome checker
- Character frequency counter
- Case conversion utilities"

git commit -m "feat(day-03): create matrix operations

- 2D array handling
- Matrix addition
- Matrix transpose
- Diagonal element extraction"

git commit -m "feat(day-03): build student management system

- Stores student records in arrays
- Implements CRUD operations
- Searches and sorts students
- Generates grade sheets

This simulates production student management systems used in colleges."
```

### 📚 Homework
1. Add "Delete student" feature
2. Implement binary search (for sorted arrays)
3. Add "Edit student marks" feature
4. **Challenge:** Sort students by name (alphabetically)

---

## DAY 4: Methods & Code Reusability

### 🌍 Real-World Context
**Why Methods Matter:**
- DRY principle: Don't Repeat Yourself
- Amazon's codebase has millions of methods
- Methods = Building blocks of software
- Testing one method is easier than testing entire program

**Real Example:**
```java
// Without methods (bad):
double total1 = price1 * quantity1 * (1 - discount1);
double total2 = price2 * quantity2 * (1 - discount2);
double total3 = price3 * quantity3 * (1 - discount3);

// With methods (good):
double total1 = calculateTotal(price1, quantity1, discount1);
double total2 = calculateTotal(price2, quantity2, discount2);
double total3 = calculateTotal(price3, quantity3, discount3);
```

### 📚 Morning Theory (9:00 AM - 9:30 AM)
1. Method anatomy: return type, name, parameters
2. Pass by value (Java doesn't have pass by reference!)
3. Method overloading (compile-time polymorphism)
4. Recursion (when to use, when to avoid)
5. Variable scope (local vs instance)

### 💻 Live Coding Demo (9:30 AM - 10:15 AM)

```java
// Show real utility class pattern (like Apache Commons)
class MathUtils {
    // Static methods (no object needed)
    public static int add(int a, int b) { return a + b; }
    public static double add(double a, double b) { return a + b; }

    // Overloading - same name, different parameters
}

// Explain: This is how Java's Math class works!
// Math.max(), Math.min(), Math.sqrt() - all static methods
```

### 🎯 Lab Exercise (10:15 AM - 11:30 AM)

1. **MathOperations.java** (20 mins)
   - isPrime(int n)
   - factorial(int n)
   - fibonacci(int n)
   - gcd(int a, int b)
   - lcm(int a, int b)

2. **StringUtils.java** (20 mins)
   - reverse(String str)
   - isPalindrome(String str)
   - countVowels(String str)
   - capitalize(String str)
   - removeSpaces(String str)

3. **RecursionExamples.java** (30 mins)
   - Factorial using recursion
   - Fibonacci using recursion
   - Sum of digits using recursion
   - Power calculation using recursion
   - **Compare:** Iterative vs Recursive (performance)

4. **MethodOverloading.java** (20 mins)
   - area(double radius) → Circle
   - area(double length, double width) → Rectangle
   - area(double base, double height, boolean isTriangle) → Triangle

### 🏢 Real-World Scenario (11:30 AM - 12:30 PM)
**Scenario:** You're building the backend for a payment gateway (like Razorpay/Stripe).

**Task: PaymentProcessor.java**

```
Create a utility class with methods:

1. validateCard(String cardNumber)
   - Check if 16 digits
   - Luhn algorithm validation
   - Check expiry date

2. calculateGST(double amount)
   - 18% GST
   - Return GST amount

3. calculateDiscount(double amount, String couponCode)
   - "SAVE10" → 10% off
   - "SAVE20" → 20% off
   - "FIRST100" → ₹100 off

4. calculateFinalAmount(double baseAmount, String coupon, boolean isPremium)
   - Apply discount
   - Apply GST
   - If premium: free shipping
   - Else: ₹50 shipping

5. generateTransactionId()
   - Format: TXN-YYYYMMDD-RANDOM5DIGIT
   - Example: TXN-20260115-54321

6. sendSMS(String phone, String message) (simulation)
   - Print "SMS sent to [phone]: [message]"

Real Flow:
User adds item (₹1000) → Applies "SAVE10" → GST added →
Shipping calculated → Final amount shown → Transaction ID generated →
SMS sent
```

**Why This Exercise:**
- Method chaining (real payment flows)
- Input validation (crucial for payments)
- Business logic in methods
- Utility class pattern (used in ALL companies)
- This is EXACTLY how payment gateways work internally!

### 📝 Git Commits

```bash
git commit -m "feat(day-04): create math utility methods

- Implements prime number checker
- Adds factorial calculator
- Creates Fibonacci generator
- Includes GCD and LCM calculators"

git commit -m "feat(day-04): add string utility methods

- String reversal without library
- Palindrome validation
- Vowel counter
- String capitalization"

git commit -m "feat(day-04): demonstrate recursion vs iteration

- Recursive factorial implementation
- Recursive Fibonacci
- Performance comparison
- Stack overflow prevention"

git commit -m "feat(day-04): showcase method overloading

- Area calculation for multiple shapes
- Demonstrates compile-time polymorphism
- Uses different parameter combinations"

git commit -m "feat(day-04): build payment processor utility

- Card validation with Luhn algorithm
- GST and discount calculations
- Transaction ID generation
- Payment flow simulation

This mirrors production payment gateway systems like Razorpay and Stripe."
```

### 📚 Homework
1. Add method: `applyOffer(double amount, String[] coupons)` - apply best coupon
2. Implement EMI calculator: `calculateEMI(double principal, double rate, int months)`
3. Add method: `splitBill(double total, int people)` - split bill among friends
4. **Challenge:** Implement your own `String.split()` method

---

## DAY 5: Introduction to OOP - Classes & Objects

### 🌍 Real-World Context
**Why OOP Matters:**
- Every real-world application is built with OOP
- Google: Thousands of classes (User, Video, Comment, etc.)
- Banking: Account, Customer, Transaction classes
- E-commerce: Product, Order, Cart, User classes

**The "Click" Moment:**
```
Real World          →  OOP
Car                 →  Class
Your Car            →  Object
Color, Model        →  Attributes
Start, Stop         →  Methods
```

### 📚 Morning Theory (9:00 AM - 9:30 AM)
1. Procedural vs OOP thinking
2. Class = Blueprint, Object = Instance
3. Constructor (why and when)
4. `this` keyword (when needed)
5. Encapsulation preview (getters/setters)

### 💻 Live Coding Demo (9:30 AM - 10:15 AM)

```java
// Bad way (procedural):
String studentName = "John";
int studentAge = 20;
String studentCourse = "CS";

// Good way (OOP):
class Student {
    String name;
    int age;
    String course;

    Student(String name, int age, String course) {
        this.name = name;  // 'this' keyword explained
        this.age = age;
        this.course = course;
    }

    void displayInfo() {
        System.out.println(name + " - " + course);
    }
}

Student s1 = new Student("John", 20, "CS");
s1.displayInfo();

// Explain: This is how Facebook stores user data!
```

### 🎯 Lab Exercise (10:15 AM - 11:30 AM)

1. **Book.java** (20 mins)
   - Attributes: title, author, price, ISBN
   - Constructor
   - displayInfo() method
   - Create 3 book objects

2. **BankAccount.java** (30 mins)
   - Attributes: accountNumber, holderName, balance
   - deposit(amount) method
   - withdraw(amount) method
   - checkBalance() method
   - displayAccountInfo() method

3. **Employee.java** (30 mins)
   - Attributes: empId, name, salary, department
   - Constructor overloading (2 constructors)
   - calculateAnnualSalary() method
   - giveRaise(percentage) method
   - displayDetails() method

4. **Product.java** (20 mins)
   - Attributes: productId, name, price, quantity
   - calculateTotal() method
   - applyDiscount(percentage) method
   - isInStock() method

### 🏢 Real-World Scenario (11:30 AM - 12:30 PM)
**Scenario:** You're building a Library Management System (like university libraries use).

**Task: Create 3 Classes**

**1. Book.java**
```
Attributes:
- bookId (String)
- title (String)
- author (String)
- category (String)
- isIssued (boolean)
- issuedTo (String)

Methods:
- issueBook(String studentName)
- returnBook()
- displayBookInfo()
```

**2. Student.java**
```
Attributes:
- studentId (String)
- name (String)
- course (String)
- booksIssued (int)
- maxBooksAllowed (int) = 3

Methods:
- canIssueMore() → boolean
- issueBook()
- returnBook()
- displayStudentInfo()
```

**3. Library.java** (Main class)
```
- Create 5 books
- Create 3 students
- Implement operations:
  1. Issue book to student
  2. Return book
  3. Display all books
  4. Display all students
  5. Search book by title
```

**Real Rules:**
- Student can't issue more than 3 books
- Can't issue same book twice
- Book must be returned before issuing another
- Validate all operations

**Why This Exercise:**
- Multi-class coordination (real systems)
- Object relationships (Student has Books)
- State management (isIssued, booksIssued)
- Business rules implementation
- This is how REAL library software works!

### 📝 Git Commits

```bash
git commit -m "feat(day-05): create Book class with OOP principles

- Defines Book blueprint with attributes
- Implements constructor
- Adds display method
- Demonstrates object creation"

git commit -m "feat(day-05): implement BankAccount class

- Encapsulates account data
- Implements deposit/withdrawal logic
- Validates transactions
- Real banking domain model"

git commit -m "feat(day-05): build Employee class with constructor overloading

- Multiple constructor variations
- Salary calculations
- Raise computation
- HR domain modeling"

git commit -m "feat(day-05): design Product class for e-commerce

- Product attributes and methods
- Discount application logic
- Stock management
- E-commerce domain model"

git commit -m "feat(day-05): create Library Management System

- Book, Student, and Library classes
- Implements book issue/return workflow
- Validates business rules
- Multi-class coordination

This simulates production library management systems used in universities."
```

### 📚 Homework
1. Add `fine calculation` for late returns (₹10/day)
2. Add `reservation` feature - student can reserve issued books
3. Create `Librarian` class with admin operations
4. **Challenge:** Implement `search by author` feature

---

## 🎯 Week 1 Summary: What Students Built

By end of Week 1, each student has in their GitHub:

```
Week-01-Java-Fundamentals/
├── Day-01/
│   ├── HelloWorld.java
│   ├── DataTypesDemo.java
│   ├── Calculator.java
│   ├── TypeConversion.java
│   ├── OperatorsDemo.java
│   └── EmployeeSalaryCalculator.java ⭐
├── Day-02/
│   ├── GradeCalculator.java
│   ├── PatternPrinter.java
│   ├── MenuDrivenCalculator.java
│   └── ATMSimulator.java ⭐
├── Day-03/
│   ├── ArrayOperations.java
│   ├── StringManipulation.java
│   ├── MatrixOperations.java
│   └── StudentManagementSystem.java ⭐
├── Day-04/
│   ├── MathOperations.java
│   ├── StringUtils.java
│   ├── RecursionExamples.java
│   ├── MethodOverloading.java
│   └── PaymentProcessor.java ⭐
└── Day-05/
    ├── Book.java
    ├── BankAccount.java
    ├── Employee.java
    ├── Product.java
    └── LibraryManagementSystem/ ⭐
        ├── Book.java
        ├── Student.java
        └── Library.java

⭐ = Portfolio-worthy projects
```

**Commits:** 30+ commits
**Lines of Code:** ~2000 lines
**Real-World Projects:** 5
**Concepts Mastered:** Variables, Operators, Control Flow, Arrays, Strings, Methods, Classes

---

*This pattern continues for all 12 weeks with increasing complexity and real-world focus.*

**Next:** Week 2-3 will follow same detailed structure (to be created).
