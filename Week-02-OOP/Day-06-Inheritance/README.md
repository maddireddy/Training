# Day 6: Inheritance & Code Reusability

## 🌍 Real-World Context

**Why Inheritance Matters:**
- Code reusability: Write once, use everywhere
- Maintainability: Fix a bug in parent, all children benefit
- Real companies use inheritance extensively:
  - Netflix: `Video` → `Movie`, `TVShow`, `Documentary`
  - Banking: `Account` → `SavingsAccount`, `CurrentAccount`, `LoanAccount`
  - E-commerce: `Product` → `Electronics`, `Clothing`, `Books`

### The Problem Inheritance Solves:

**Without Inheritance:**
```java
class Car {
    String brand, model;
    void start() { }
    void stop() { }
}

class Bike {
    String brand, model;  // DUPLICATE CODE!
    void start() { }      // DUPLICATE CODE!
    void stop() { }       // DUPLICATE CODE!
}
```

**With Inheritance:**
```java
class Vehicle {
    String brand, model;
    void start() { }
    void stop() { }
}

class Car extends Vehicle { }  // Gets all Vehicle features FREE!
class Bike extends Vehicle { } // Gets all Vehicle features FREE!
```

---

## 📚 Topics Covered

1. **Single Inheritance** (Parent → Child)
2. **Multilevel Inheritance** (Grandparent → Parent → Child)
3. **Hierarchical Inheritance** (One parent, multiple children)
4. **super keyword** (Access parent class members)
5. **this keyword** (Current object reference)
6. **Method Overriding** (Child redefines parent method)
7. **Type Casting** (Upcasting & Downcasting)

---

## 💻 Hands-On Examples

### Example 1: Employee Hierarchy (Banking Domain)
Real-world scenario from HR/Banking systems

### Example 2: Vehicle Hierarchy
Common interview question

### Example 3: Multilevel Inheritance (Animal Kingdom)
Educational system example

### Example 4: Salary Inheritance Demo
From your Learning repository - Enhanced version

---

## 🏢 Real-World Scenario: Banking Account System

**Task:** Build account hierarchy for HDFC/ICICI bank

```
Account (Parent)
├── SavingsAccount (4% interest, min balance ₹5000)
├── CurrentAccount (No interest, overdraft facility)
└── LoanAccount (EMI, interest calculation)
```

---

## 📝 Git Commits

```bash
git commit -m "feat(day-06): implement single inheritance with employee example"
git commit -m "feat(day-06): add multilevel inheritance with vehicle hierarchy"
git commit -m "feat(day-06): demonstrate method overriding in account system"
git commit -m "feat(day-06): build banking account hierarchy

- Implements Account base class
- SavingsAccount with interest calculation
- CurrentAccount with overdraft
- Real banking business logic"
```

---

## 🎯 Learning Outcomes

By end of Day 6, you will:
- ✅ Understand IS-A relationship
- ✅ Use super and this keywords correctly
- ✅ Override methods effectively
- ✅ Design class hierarchies
- ✅ Apply inheritance in real projects

---

## 📚 Interview Questions

1. Can we achieve multiple inheritance in Java? How?
2. What's the difference between super and this?
3. When to use composition over inheritance?
4. Can constructors be inherited?
5. What is the diamond problem?

---

**Next:** Day 7 - Polymorphism (Compile-time & Runtime)
