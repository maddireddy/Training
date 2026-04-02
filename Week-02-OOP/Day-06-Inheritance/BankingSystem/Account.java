/**
 * Day 06: Banking System - Inheritance & Polymorphism
 * Base Account class for all account types
 */
public abstract class Account {
    // Common attributes for all accounts
    protected String accountNumber;
    protected String holderName;
    protected double balance;
    protected String accountType;

    // Constructor
    public Account(String accountNumber, String holderName, double initialDeposit, String accountType) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = initialDeposit;
        this.accountType = accountType;
    }

    // Common methods
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Invalid deposit amount!");
            return;
        }
        balance += amount;
        System.out.printf("✅ Deposited ₹%.2f. New balance: ₹%.2f%n", amount, balance);
    }

    // Abstract method - different for each account type
    public abstract boolean withdraw(double amount);

    // Abstract method - different interest rates
    public abstract double calculateInterest();

    // Display account info
    public void displayAccountInfo() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Account Type    : " + accountType);
        System.out.println("Account Number  : " + accountNumber);
        System.out.println("Account Holder  : " + holderName);
        System.out.printf("Current Balance : ₹%.2f%n", balance);
        System.out.println("=".repeat(60));
    }

    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }
}
