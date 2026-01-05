# Day 7: Polymorphism - One Interface, Multiple Implementations

## 🌍 Real-World Context

**Polymorphism** = "Many Forms"

**Real-World Examples:**
- **Payment**: One `pay()` method, multiple implementations (CreditCard, UPI, NetBanking, Cash)
- **Notification**: One `send()` method, multiple types (Email, SMS, Push, WhatsApp)
- **Shape**: One `draw()` method, different shapes (Circle, Square, Triangle)
- **Employee**: One `calculateSalary()` method, different for Manager, Developer, Intern

**The Power:**
```java
// Same code works for ALL payment types!
void processPayment(Payment payment) {
    payment.pay(amount);  // Works for CreditCard, UPI, NetBanking, etc.
}
```

---

## 📚 Two Types of Polymorphism

### 1. **Compile-Time Polymorphism** (Method Overloading)
- **Decision**: Made by compiler
- **How**: Same method name, different parameters
- **Example**: `add(int, int)`, `add(double, double)`, `add(int, int, int)`

### 2. **Runtime Polymorphism** (Method Overriding)
- **Decision**: Made during program execution
- **How**: Child class redefines parent method
- **Example**: `Animal.makeSound()` → Dog: "Bark", Cat: "Meow"

---

## 💻 Topics Covered

1. **Method Overloading** (Compile-time)
   - Different number of parameters
   - Different types of parameters
   - Different order of parameters

2. **Method Overriding** (Runtime)
   - `@Override` annotation
   - Parent reference, child object
   - Dynamic method dispatch

3. **Operator Overloading** (NOT in Java!)
   - Why Java doesn't support it
   - Alternative approaches

4. **Variable Overloading** (Shadowing)
   - When child and parent have same variable name
   - Difference from method overriding

---

## 🏢 Real-World Scenarios

### Scenario 1: Payment Gateway (Like Razorpay/PayPal)
Multiple payment methods, same interface

### Scenario 2: Notification System (Like Firebase)
Send notifications via different channels

### Scenario 3: Shape Drawing (Graphics Applications)
Draw different shapes with same method call

---

## 📝 Git Commits

```bash
git commit -m "feat(day-07): implement method overloading examples

- Demonstrates compile-time polymorphism
- Calculator with overloaded methods
- Different parameter types and counts"

git commit -m "feat(day-07): demonstrate method overriding

- Runtime polymorphism example
- Parent reference with child objects
- Dynamic method dispatch"

git commit -m "feat(day-07): build payment gateway with polymorphism

- Multiple payment types
- Single processPayment() method
- Real-world payment system simulation

This is how Razorpay, PayPal, Stripe implement payment processing."
```

---

## 🎯 Learning Outcomes

- ✅ Understand compile-time vs runtime polymorphism
- ✅ Use method overloading effectively
- ✅ Implement method overriding with `@Override`
- ✅ Design flexible, extensible systems
- ✅ Apply polymorphism in real projects

---

**From Your Learning Repository:** Enhanced versions of your method overloading and overriding examples with real-world context!
