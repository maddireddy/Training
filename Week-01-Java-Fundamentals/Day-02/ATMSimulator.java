/**
 * Day 02: ATM Machine Simulator
 * Real-World Banking Application
 *
 * This program simulates an actual ATM machine with real banking logic.
 * Used in production banking systems like HDFC, ICICI, and SBI.
 *
 * Features:
 * - Check Balance
 * - Withdraw Money (with daily limit)
 * - Deposit Money
 * - Mini Statement (last 5 transactions)
 * - PIN Security (lock after 3 failed attempts)
 *
 * Learning Outcomes:
 * - Control flow (if-else, switch)
 * - Loops (while, for)
 * - Input validation
 * - State management
 * - Business logic implementation
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ATMSimulator {

    // Account details
    private static final String ACCOUNT_HOLDER = "John Doe";
    private static final String ACCOUNT_NUMBER = "1234567890";
    private static final int CORRECT_PIN = 1234;
    private static double balance = 50000.00;

    // Banking rules
    private static final double MINIMUM_BALANCE = 500.00;
    private static final double DAILY_WITHDRAWAL_LIMIT = 50000.00;
    private static final int MAX_PIN_ATTEMPTS = 3;

    // Transaction tracking
    private static double totalWithdrawnToday = 0.00;
    private static ArrayList<String> transactionHistory = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // PIN Authentication
        if (!authenticateUser(scanner)) {
            System.out.println("\n❌ Account locked due to multiple failed attempts!");
            System.out.println("Please visit nearest branch or call customer care.");
            scanner.close();
            return;
        }

        // Main ATM Menu
        boolean exit = false;
        while (!exit) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = getValidChoice(scanner);

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    withdrawMoney(scanner);
                    break;
                case 3:
                    depositMoney(scanner);
                    break;
                case 4:
                    miniStatement();
                    break;
                case 5:
                    changePin(scanner);
                    break;
                case 6:
                    exit = true;
                    System.out.println("\n✅ Thank you for using our ATM!");
                    System.out.println("Please collect your card.");
                    break;
                default:
                    System.out.println("\n❌ Invalid choice! Please try again.");
            }

            if (!exit) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    /**
     * Authenticate user with PIN
     * Real banking logic: Lock account after 3 failed attempts
     */
    private static boolean authenticateUser(Scanner scanner) {
        System.out.println("=".repeat(50));
        System.out.println("       WELCOME TO NATIONAL BANK ATM");
        System.out.println("=".repeat(50));
        System.out.println("Account: " + ACCOUNT_NUMBER);
        System.out.println("Holder: " + ACCOUNT_HOLDER);
        System.out.println("=".repeat(50));

        int attempts = 0;
        while (attempts < MAX_PIN_ATTEMPTS) {
            System.out.print("\nEnter your 4-digit PIN: ");
            int enteredPin = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (enteredPin == CORRECT_PIN) {
                System.out.println("\n✅ PIN verified successfully!");
                addTransaction("PIN Authentication", 0.00);
                return true;
            } else {
                attempts++;
                int remaining = MAX_PIN_ATTEMPTS - attempts;
                if (remaining > 0) {
                    System.out.println("❌ Incorrect PIN! " + remaining + " attempts remaining.");
                }
            }
        }
        return false;
    }

    /**
     * Display ATM menu
     */
    private static void displayMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("              ATM MAIN MENU");
        System.out.println("=".repeat(50));
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw Money");
        System.out.println("3. Deposit Money");
        System.out.println("4. Mini Statement");
        System.out.println("5. Change PIN");
        System.out.println("6. Exit");
        System.out.println("=".repeat(50));
    }

    /**
     * Get valid menu choice
     */
    private static int getValidChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            scanner.nextLine(); // Consume invalid input
            System.out.print("Invalid input! Please enter a number (1-6): ");
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return choice;
    }

    /**
     * Check account balance
     */
    private static void checkBalance() {
        System.out.println("\n" + "-".repeat(50));
        System.out.println("           BALANCE INQUIRY");
        System.out.println("-".repeat(50));
        System.out.printf("Current Balance: ₹%.2f\n", balance);
        System.out.printf("Available Balance: ₹%.2f\n", balance - MINIMUM_BALANCE);
        System.out.printf("Daily Withdrawal Remaining: ₹%.2f\n",
                         DAILY_WITHDRAWAL_LIMIT - totalWithdrawnToday);
        System.out.println("-".repeat(50));

        addTransaction("Balance Inquiry", 0.00);
    }

    /**
     * Withdraw money with validation
     * Real banking rules implemented
     */
    private static void withdrawMoney(Scanner scanner) {
        System.out.println("\n" + "-".repeat(50));
        System.out.println("           CASH WITHDRAWAL");
        System.out.println("-".repeat(50));
        System.out.printf("Available Balance: ₹%.2f\n", balance - MINIMUM_BALANCE);
        System.out.printf("Daily Limit Remaining: ₹%.2f\n",
                         DAILY_WITHDRAWAL_LIMIT - totalWithdrawnToday);
        System.out.println("-".repeat(50));

        System.out.print("Enter amount to withdraw (multiple of 100): ₹");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        // Validation 1: Amount must be positive
        if (amount <= 0) {
            System.out.println("❌ Invalid amount! Please enter a positive value.");
            return;
        }

        // Validation 2: Amount must be multiple of 100
        if (amount % 100 != 0) {
            System.out.println("❌ Amount must be in multiples of ₹100!");
            return;
        }

        // Validation 3: Check minimum balance
        if (balance - amount < MINIMUM_BALANCE) {
            System.out.println("❌ Insufficient balance!");
            System.out.printf("Minimum balance of ₹%.2f must be maintained.\n", MINIMUM_BALANCE);
            return;
        }

        // Validation 4: Check daily limit
        if (totalWithdrawnToday + amount > DAILY_WITHDRAWAL_LIMIT) {
            System.out.println("❌ Daily withdrawal limit exceeded!");
            System.out.printf("You can only withdraw ₹%.2f more today.\n",
                            DAILY_WITHDRAWAL_LIMIT - totalWithdrawnToday);
            return;
        }

        // Process withdrawal
        balance -= amount;
        totalWithdrawnToday += amount;

        System.out.println("\n✅ Withdrawal Successful!");
        System.out.println("-".repeat(50));
        System.out.printf("Amount Withdrawn: ₹%.2f\n", amount);
        System.out.printf("Current Balance: ₹%.2f\n", balance);
        System.out.println("-".repeat(50));
        System.out.println("Please collect your cash.");

        addTransaction("Cash Withdrawal", -amount);
    }

    /**
     * Deposit money
     */
    private static void depositMoney(Scanner scanner) {
        System.out.println("\n" + "-".repeat(50));
        System.out.println("           CASH DEPOSIT");
        System.out.println("-".repeat(50));

        System.out.print("Enter amount to deposit: ₹");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        // Validation: Amount must be positive
        if (amount <= 0) {
            System.out.println("❌ Invalid amount! Please enter a positive value.");
            return;
        }

        // Process deposit
        balance += amount;

        System.out.println("\n✅ Deposit Successful!");
        System.out.println("-".repeat(50));
        System.out.printf("Amount Deposited: ₹%.2f\n", amount);
        System.out.printf("Current Balance: ₹%.2f\n", balance);
        System.out.println("-".repeat(50));

        addTransaction("Cash Deposit", amount);
    }

    /**
     * Display mini statement (last 5 transactions)
     */
    private static void miniStatement() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           MINI STATEMENT");
        System.out.println("=".repeat(50));
        System.out.println("Account: " + ACCOUNT_NUMBER);
        System.out.println("Holder: " + ACCOUNT_HOLDER);
        System.out.println("=".repeat(50));

        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("\nLast " + Math.min(5, transactionHistory.size()) + " Transactions:");
            System.out.println("-".repeat(50));

            // Show last 5 transactions (or fewer if less than 5)
            int startIndex = Math.max(0, transactionHistory.size() - 5);
            for (int i = startIndex; i < transactionHistory.size(); i++) {
                System.out.println((i + 1) + ". " + transactionHistory.get(i));
            }
        }

        System.out.println("-".repeat(50));
        System.out.printf("Current Balance: ₹%.2f\n", balance);
        System.out.println("=".repeat(50));
    }

    /**
     * Change PIN (Simulation)
     */
    private static void changePin(Scanner scanner) {
        System.out.println("\n" + "-".repeat(50));
        System.out.println("           CHANGE PIN");
        System.out.println("-".repeat(50));

        System.out.print("Enter current PIN: ");
        int currentPin = scanner.nextInt();
        scanner.nextLine();

        if (currentPin != CORRECT_PIN) {
            System.out.println("❌ Incorrect current PIN!");
            return;
        }

        System.out.print("Enter new PIN: ");
        int newPin = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Confirm new PIN: ");
        int confirmPin = scanner.nextInt();
        scanner.nextLine();

        if (newPin != confirmPin) {
            System.out.println("❌ PINs do not match!");
            return;
        }

        System.out.println("\n✅ PIN changed successfully!");
        System.out.println("Note: In real application, this would update the database.");
        System.out.println("For this simulation, please restart and use PIN: " + CORRECT_PIN);

        addTransaction("PIN Change Request", 0.00);
    }

    /**
     * Add transaction to history
     */
    private static void addTransaction(String type, double amount) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = now.format(formatter);

        String transaction = String.format("%s | %s | ₹%.2f",
                                           timestamp, type, amount);
        transactionHistory.add(transaction);
    }
}
