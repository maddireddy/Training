# Day 02: Control Flow & Decision Making

## 🎯 Learning Objectives
- Master if-else statements and decision-making logic
- Understand switch statements and when to use them
- Learn different types of loops (for, while, do-while)
- Implement real-world validation and business rules
- Build production-quality control flow logic

## 🏢 Real-World Context
Every application you use has conditional logic:
- **Amazon**: "If cart total > $25 → free shipping"
- **Netflix**: "If user is premium → unlock HD content"
- **Banking**: "If withdrawal > daily limit → reject transaction"

This day teaches you how to implement these business rules in code.

## 📚 Topics Covered
1. Boolean logic and truth tables
2. if-else statements and nested conditions
3. switch statements for state management
4. for, while, and do-while loops
5. break and continue statements
6. Input validation techniques
7. Business logic implementation

## 💻 Main Project: ATM Machine Simulator

### Overview
A production-quality ATM simulator implementing real banking logic used by banks like HDFC, ICICI, and SBI.

### Features
1. **PIN Authentication**
   - 3-attempt limit
   - Account locking after failed attempts

2. **Balance Inquiry**
   - Check current balance
   - View available balance (excluding minimum balance)
   - See daily withdrawal limit remaining

3. **Cash Withdrawal**
   - Amount validation (multiple of 100)
   - Minimum balance check (₹500)
   - Daily limit enforcement (₹50,000)
   - Real banking rules

4. **Cash Deposit**
   - Amount validation
   - Balance update
   - Transaction recording

5. **Mini Statement**
   - Last 5 transactions
   - Transaction history with timestamps

6. **Change PIN**
   - Current PIN verification
   - New PIN confirmation

### Business Rules Implemented
```
✓ Minimum balance: ₹500
✓ Daily withdrawal limit: ₹50,000
✓ Withdrawal amounts: Multiples of ₹100 only
✓ PIN security: Lock after 3 failed attempts
✓ Transaction tracking: All operations logged
```

## 🚀 How to Run

### Prerequisites
- JDK 8 or higher installed
- Terminal/Command Prompt

### Compilation
```bash
javac ATMSimulator.java
```

### Execution
```bash
java ATMSimulator
```

### Test Credentials
```
PIN: 1234
Initial Balance: ₹50,000
Account: 1234567890
Holder: John Doe
```

## 🧪 Test Scenarios

### Scenario 1: Successful Withdrawal
```
1. Login with PIN: 1234
2. Select option 2 (Withdraw)
3. Enter: 5000
✅ Expected: Withdrawal successful
```

### Scenario 2: Insufficient Balance
```
1. Login with PIN: 1234
2. Select option 2 (Withdraw)
3. Enter: 50000 (leaves less than ₹500)
❌ Expected: Insufficient balance error
```

### Scenario 3: Invalid Amount
```
1. Login with PIN: 1234
2. Select option 2 (Withdraw)
3. Enter: 550 (not multiple of 100)
❌ Expected: Amount must be multiple of ₹100
```

### Scenario 4: Daily Limit Exceeded
```
1. Withdraw ₹30,000 (successful)
2. Withdraw ₹25,000 (exceeds daily limit)
❌ Expected: Daily limit exceeded
```

### Scenario 5: Failed PIN Attempts
```
1. Enter wrong PIN: 0000
2. Enter wrong PIN: 1111
3. Enter wrong PIN: 2222
❌ Expected: Account locked
```

## 🎓 Key Concepts Demonstrated

### 1. Control Flow
```java
// if-else for validation
if (amount <= 0) {
    System.out.println("Invalid amount!");
    return;
}

// switch for menu selection
switch (choice) {
    case 1: checkBalance(); break;
    case 2: withdrawMoney(); break;
    default: System.out.println("Invalid!");
}
```

### 2. Loop Usage
```java
// while loop for menu
while (!exit) {
    displayMenu();
    processChoice();
}

// for loop for transactions
for (int i = 0; i < transactions.size(); i++) {
    System.out.println(transactions.get(i));
}
```

### 3. Input Validation
```java
// Multiple validation checks
if (amount % 100 != 0) return;
if (balance - amount < MIN_BALANCE) return;
if (totalWithdrawn + amount > DAILY_LIMIT) return;
```

## 📝 Practice Exercises

### Exercise 1: Add Transfer Feature
Add money transfer functionality:
- Validate account number
- Check sufficient balance
- Add transaction fee
- Update both accounts

### Exercise 2: Transaction Categories
Categorize transactions:
- Withdrawal
- Deposit
- Transfer
- Balance Inquiry
- Count each category

### Exercise 3: Enhanced Security
Add OTP verification:
- Generate random 6-digit OTP
- User must enter within 3 attempts
- Timeout after 60 seconds

### Exercise 4: Account History
Add date-wise filtering:
- Show today's transactions
- Show last 7 days
- Show custom date range

## 🏆 Challenge Problems

1. **Loan EMI Calculator**: Add loan payment option
2. **Recurring Deposit**: Implement RD creation
3. **Bill Payments**: Add utility bill payment
4. **Mobile Recharge**: Implement recharge feature
5. **Account Statement**: Generate PDF-like text statement

## 💡 Real-World Applications

This exact logic is used in:
- **Banking Apps**: PhonePe, Paytm, Google Pay
- **ATM Machines**: Physical ATMs worldwide
- **POS Terminals**: Card payment machines
- **E-Wallets**: Digital wallet applications

## 🔗 Related Concepts

- **Next**: Arrays & Strings (Day 03)
- **Previous**: Java Basics (Day 01)
- **Advanced**: Exception Handling (Day 08)

## 📖 Additional Resources

- [Java Control Flow Documentation](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/flow.html)
- [Switch Statement Best Practices](https://www.oracle.com/technical-resources/articles/java/ma14-java-se-8-streams.html)
- [Banking Software Design Patterns](https://www.tutorialspoint.com/design_pattern/index.htm)

## ✅ Learning Outcomes Checklist

After completing this day, you should be able to:
- [ ] Write complex if-else logic
- [ ] Choose between switch and if-else
- [ ] Implement input validation
- [ ] Use loops effectively
- [ ] Handle edge cases
- [ ] Apply real-world business rules
- [ ] Build menu-driven applications
- [ ] Manage application state

---

**Remember**: This is how REAL banking software works. Master this, and you're ready for production code! 🚀
