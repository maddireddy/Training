/**
 * Custom Exceptions
 * Demonstrates: Creating and using custom exception classes
 */
package custom_exceptions;

public class Example03_CustomExceptions {
    public static void main(String[] args) {
        System.out.println("=== Custom Exceptions ===\n");

        BankAccount account = new BankAccount("ACC001", 1000);

        try {
            account.withdraw(500);
            System.out.println("Withdrawal successful");

            account.withdraw(700);  // Throws InsufficientFundsException
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Required: $" + e.getAmountNeeded());
        }

        try {
            account.withdraw(-100);  // Throws InvalidAmountException
        } catch (InvalidAmountException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }
}

class InsufficientFundsException extends Exception {
    private double amountNeeded;

    public InsufficientFundsException(double amount) {
        super("Insufficient funds");
        this.amountNeeded = amount;
    }

    public double getAmountNeeded() {
        return amountNeeded;
    }
}

class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) {
        super(message);
    }
}

class BankAccount {
    private String accountId;
    private double balance;

    public BankAccount(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public void withdraw(double amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount < 0) {
            throw new InvalidAmountException("Amount cannot be negative");
        }
        if (amount > balance) {
            throw new InsufficientFundsException(amount - balance);
        }
        balance -= amount;
        System.out.println("Withdrew $" + amount + ", Balance: $" + balance);
    }
}
