# Day 06: Inheritance & Polymorphism - Banking System

## 🎯 Learning Objectives
- Master inheritance (single, multilevel, hierarchical)
- Understand and implement polymorphism
- Use abstract classes and methods
- Override methods for different behaviors

## 🏢 Real-World Context
Banking systems use inheritance to manage different account types with shared and unique behaviors.

## 💻 Main Project: Multi-Account Banking System

### Class Hierarchy
- **Account** (Abstract Base Class)
  - SavingsAccount (Min balance ₹1000, 4% interest)
  - CurrentAccount (Overdraft ₹50K, no interest)
  - FixedDepositAccount (7% interest, locked period)

### Features
1. Create different account types
2. Polymorphic withdraw behavior
3. Different interest calculations
4. Money transfer between accounts
5. Account management

## 🚀 How to Run

```bash
cd Week-02-OOP/Day-06-Inheritance/BankingSystem
javac *.java
java BankingSystem
```

## Key Concepts
- Abstract classes and methods
- Method overriding
- Polymorphism
- Constructor chaining (super)
- Protected access modifier

---
**This mirrors real banking software architecture!** 🏦
