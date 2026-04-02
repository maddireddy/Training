/**
 * Day 06: Banking System - Main Application
 * Demonstrates Inheritance and Polymorphism
 */
import java.util.Scanner;
import java.util.ArrayList;

public class BankingSystem {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static int accountCounter = 1001;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Add sample accounts
        initializeSampleAccounts();

        boolean exit = false;
        while (!exit) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = getValidInt(scanner);

            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    depositMoney(scanner);
                    break;
                case 3:
                    withdrawMoney(scanner);
                    break;
                case 4:
                    checkBalance(scanner);
                    break;
                case 5:
                    calculateInterest(scanner);
                    break;
                case 6:
                    displayAllAccounts();
                    break;
                case 7:
                    transferMoney(scanner);
                    break;
                case 8:
                    closeAccount(scanner);
                    break;
                case 9:
                    exit = true;
                    System.out.println("\n✅ Thank you for banking with us!");
                    break;
                default:
                    System.out.println("\n❌ Invalid choice!");
            }

            if (!exit) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void initializeSampleAccounts() {
        accounts.add(new SavingsAccount("ACC1001", "Alice Johnson", 10000));
        accounts.add(new CurrentAccount("ACC1002", "Bob's Business", 50000));
        accounts.add(new FixedDepositAccount("ACC1003", "Carol Davis", 100000, 12));
        System.out.println("✅ Bank initialized with 3 sample accounts.");
    }

    private static void displayMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("           NATIONAL BANK - MAIN MENU");
        System.out.println("=".repeat(60));
        System.out.println("1. Create New Account");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Check Balance");
        System.out.println("5. Calculate Interest");
        System.out.println("6. Display All Accounts");
        System.out.println("7. Transfer Money");
        System.out.println("8. Close Account");
        System.out.println("9. Exit");
        System.out.println("=".repeat(60));
    }

    private static void createAccount(Scanner scanner) {
        System.out.println("\n" + "-".repeat(60));
        System.out.println("           CREATE NEW ACCOUNT");
        System.out.println("-".repeat(60));
        System.out.println("Select Account Type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");
        System.out.println("3. Fixed Deposit");
        System.out.print("Choice: ");
        int type = getValidInt(scanner);

        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();

        System.out.print("Enter initial deposit: ₹");
        double deposit = getValidDouble(scanner);

        String accountNum = "ACC" + accountCounter++;

        Account account = null;
        switch (type) {
            case 1:
                account = new SavingsAccount(accountNum, name, deposit);
                break;
            case 2:
                account = new CurrentAccount(accountNum, name, deposit);
                break;
            case 3:
                System.out.print("Enter tenure (months): ");
                int tenure = getValidInt(scanner);
                account = new FixedDepositAccount(accountNum, name, deposit, tenure);
                break;
            default:
                System.out.println("❌ Invalid account type!");
                return;
        }

        if (account != null) {
            accounts.add(account);
            System.out.println("\n✅ Account created successfully!");
            account.displayAccountInfo();
        }
    }

    private static void depositMoney(Scanner scanner) {
        System.out.print("\nEnter account number: ");
        String accNum = scanner.nextLine();

        Account account = findAccount(accNum);
        if (account == null) {
            System.out.println("❌ Account not found!");
            return;
        }

        System.out.print("Enter deposit amount: ₹");
        double amount = getValidDouble(scanner);

        account.deposit(amount);
    }

    private static void withdrawMoney(Scanner scanner) {
        System.out.print("\nEnter account number: ");
        String accNum = scanner.nextLine();

        Account account = findAccount(accNum);
        if (account == null) {
            System.out.println("❌ Account not found!");
            return;
        }

        System.out.print("Enter withdrawal amount: ₹");
        double amount = getValidDouble(scanner);

        account.withdraw(amount); // Polymorphism: different behavior for each account type
    }

    private static void checkBalance(Scanner scanner) {
        System.out.print("\nEnter account number: ");
        String accNum = scanner.nextLine();

        Account account = findAccount(accNum);
        if (account == null) {
            System.out.println("❌ Account not found!");
            return;
        }

        account.displayAccountInfo(); // Polymorphism: displays different info based on account type
    }

    private static void calculateInterest(Scanner scanner) {
        System.out.print("\nEnter account number: ");
        String accNum = scanner.nextLine();

        Account account = findAccount(accNum);
        if (account == null) {
            System.out.println("❌ Account not found!");
            return;
        }

        account.calculateInterest(); // Polymorphism: different calculation for each type
    }

    private static void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("\n❌ No accounts found!");
            return;
        }

        System.out.println("\n" + "=".repeat(90));
        System.out.println("                           ALL ACCOUNTS");
        System.out.println("=".repeat(90));
        System.out.printf("%-12s %-25s %-20s %-15s%n",
                         "Account No", "Holder Name", "Account Type", "Balance");
        System.out.println("-".repeat(90));

        for (Account account : accounts) {
            System.out.printf("%-12s %-25s %-20s ₹%-15.2f%n",
                             account.getAccountNumber(),
                             account.getHolderName(),
                             account.getAccountType(),
                             account.getBalance());
        }
        System.out.println("=".repeat(90));
        System.out.println("Total Accounts: " + accounts.size());
    }

    private static void transferMoney(Scanner scanner) {
        System.out.print("\nEnter source account number: ");
        String fromAcc = scanner.nextLine();

        Account source = findAccount(fromAcc);
        if (source == null) {
            System.out.println("❌ Source account not found!");
            return;
        }

        System.out.print("Enter destination account number: ");
        String toAcc = scanner.nextLine();

        Account destination = findAccount(toAcc);
        if (destination == null) {
            System.out.println("❌ Destination account not found!");
            return;
        }

        System.out.print("Enter transfer amount: ₹");
        double amount = getValidDouble(scanner);

        if (source.withdraw(amount)) {
            destination.deposit(amount);
            System.out.println("\n✅ Transfer successful!");
            System.out.println("From: " + source.getHolderName());
            System.out.println("To: " + destination.getHolderName());
            System.out.printf("Amount: ₹%.2f%n", amount);
        } else {
            System.out.println("❌ Transfer failed!");
        }
    }

    private static void closeAccount(Scanner scanner) {
        System.out.print("\nEnter account number: ");
        String accNum = scanner.nextLine();

        Account account = findAccount(accNum);
        if (account == null) {
            System.out.println("❌ Account not found!");
            return;
        }

        System.out.print("Are you sure you want to close this account? (Y/N): ");
        String confirm = scanner.nextLine().trim().toUpperCase();

        if (confirm.equals("Y")) {
            double balance = account.getBalance();
            accounts.remove(account);
            System.out.println("\n✅ Account closed successfully!");
            System.out.printf("Final balance of ₹%.2f will be returned to the account holder.%n", balance);
        } else {
            System.out.println("Account closure cancelled.");
        }
    }

    private static Account findAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    private static int getValidInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.print("Invalid input! Please enter a number: ");
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private static double getValidDouble(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            scanner.nextLine();
            System.out.print("Invalid input! Please enter a number: ");
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }
}
