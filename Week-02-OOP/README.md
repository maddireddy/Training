# Week 02: Object-Oriented Programming (Days 6-10)

## 🎯 Week Overview
Master Object-Oriented Programming concepts with real-world projects. Build production-quality systems using inheritance, polymorphism, interfaces, and collections.

## 📅 Daily Breakdown

### Day 06: Inheritance & Polymorphism
**Main Project**: Banking System with Account Hierarchy
- Create base `Account` class
- Implement `SavingsAccount`, `CurrentAccount`, `FixedDepositAccount`
- Override methods for different account behaviors
- Calculate interest rates differently per account type

**Real-World Application**: Actual banking software architecture

**Key Files**:
- `BankingSystem/Account.java` (base class)
- `BankingSystem/SavingsAccount.java`
- `BankingSystem/CurrentAccount.java`
- `BankingSystem/Bank.java` (main application)

---

### Day 07: Abstraction & Interfaces
**Main Project**: Payment Gateway System
- Create `PaymentMethod` interface
- Implement `CreditCard`, `DebitCard`, `UPI`, `NetBanking`
- Abstract `Payment` class
- Process payments through different methods

**Real-World Application**: E-commerce payment processing (Razorpay/Stripe style)

**Key Files**:
- `PaymentGateway/PaymentMethod.java` (interface)
- `PaymentGateway/Payment.java` (abstract class)
- `PaymentGateway/CreditCard.java`, `UPI.java`, etc.
- `PaymentGateway/PaymentProcessor.java` (main)

---

### Day 08: Packages & Exception Handling
**Main Project**: User Registration System with Validation
- Create custom exceptions (InvalidEmailException, WeakPasswordException)
- Package structure: `com.training.users`, `com.training.exceptions`
- Comprehensive input validation
- Try-catch-finally blocks
- Exception chaining

**Real-World Application**: User management in web applications

**Key Files**:
- `src/com/training/users/User.java`
- `src/com/training/users/UserService.java`
- `src/com/training/exceptions/InvalidEmailException.java`
- `src/com/training/Main.java`

---

### Day 09: Collections Framework - Part 1
**Main Project**: Shopping Cart System
- Use `ArrayList` for cart items
- Use `HashMap` for product catalog
- Use `HashSet` for wish list (unique items)
- Implement `Comparable` for product sorting
- Use `Comparator` for multiple sorting options

**Real-World Application**: E-commerce shopping cart (Amazon/Flipkart style)

**Key Files**:
- `ShoppingCart/Product.java` (implements Comparable)
- `ShoppingCart/Cart.java` (uses ArrayList)
- `ShoppingCart/Store.java` (uses HashMap)
- `ShoppingCart/WishList.java` (uses HashSet)
- `ShoppingCart/ShoppingApp.java` (main)

---

### Day 10: Collections Framework - Part 2
**Main Project**: Order Management System
- Use `TreeMap` for sorted orders
- Use `PriorityQueue` for order processing
- Use `LinkedHashMap` for maintaining insertion order
- Implement inventory management
- Order status tracking

**Real-World Application**: Restaurant/Food delivery order management

**Key Files**:
- `OrderManagement/Order.java`
- `OrderManagement/OrderQueue.java` (PriorityQueue)
- `OrderManagement/Inventory.java` (TreeMap)
- `OrderManagement/OrderService.java`
- `OrderManagement/RestaurantApp.java` (main)

---

## 🎓 Week Learning Outcomes

By the end of Week 02, you will be able to:
- ✅ Design class hierarchies using inheritance
- ✅ Implement polymorphism for flexible code
- ✅ Use interfaces for abstraction
- ✅ Create and handle custom exceptions
- ✅ Organize code with packages
- ✅ Choose appropriate collection types
- ✅ Implement sorting with Comparable/Comparator
- ✅ Build production-ready OOP systems

## 🏆 Week 02 Projects Summary

| Day | Project | Concepts | Lines of Code |
|-----|---------|----------|---------------|
| 06  | Banking System | Inheritance, Polymorphism | ~500 |
| 07  | Payment Gateway | Interfaces, Abstract Classes | ~400 |
| 08  | User Registration | Exceptions, Packages | ~350 |
| 09  | Shopping Cart | List, Set, Map, Sorting | ~600 |
| 10  | Order Management | Advanced Collections | ~550 |

**Total**: 5 production-ready projects, ~2400 lines of code

## 💡 Real-World Connections

- **Banking System**: Used by HDFC, ICICI, SBI
- **Payment Gateway**: Razorpay, Stripe, PayPal architecture
- **User Registration**: Every web application
- **Shopping Cart**: Amazon, Flipkart, Walmart
- **Order Management**: Swiggy, Zomato, Uber Eats

## 📝 Assessment Criteria

### Daily Assessment (20%)
- Code submission
- Functionality
- Code quality

### Mini Project (30%)
- Day 09 Shopping Cart (comprehensive)

### Week-End Project (50%)
- Integrate all concepts into an E-commerce system
- Combine Banking, Payment, Shopping Cart
- Full CRUD operations
- Exception handling throughout

## 🚀 Getting Started

```bash
# Navigate to Week 02
cd Week-02-OOP

# Each day has its own directory
cd Day-06-Inheritance

# Compile and run
javac BankingSystem/*.java
java BankingSystem.Bank
```

## 📚 Prerequisites

Before starting Week 02:
- Complete Week 01 (Java Fundamentals)
- Understand classes and objects
- Comfortable with methods and arrays
- Basic Java syntax mastery

## 🎯 Success Tips

1. **Understand Before Coding**: Read the problem thoroughly
2. **Plan Class Structure**: Draw UML diagrams
3. **Start Simple**: Basic implementation first
4. **Refactor**: Improve code iteratively
5. **Test Thoroughly**: Try all scenarios
6. **Real-World Thinking**: Connect to actual applications

---

**Ready to build production-quality OOP systems? Let's start Day 06!** 🚀
