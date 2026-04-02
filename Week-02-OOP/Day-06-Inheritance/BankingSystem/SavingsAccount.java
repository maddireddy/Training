/**
 * Savings Account - with minimum balance and interest
 */
public class SavingsAccount extends Account {
    private static final double MIN_BALANCE = 1000.00;
    private static final double INTEREST_RATE = 0.04; // 4% per annum

    public SavingsAccount(String accountNumber, String holderName, double initialDeposit) {
        super(accountNumber, holderName, initialDeposit, "Savings Account");

        if (initialDeposit < MIN_BALANCE) {
            System.out.println("⚠️  Warning: Minimum balance for savings account is ₹" + MIN_BALANCE);
        }
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Invalid withdrawal amount!");
            return false;
        }

        // Check minimum balance
        if (balance - amount < MIN_BALANCE) {
            System.out.printf("❌ Withdrawal failed! Minimum balance of ₹%.2f must be maintained.%n", MIN_BALANCE);
            System.out.printf("Available for withdrawal: ₹%.2f%n", balance - MIN_BALANCE);
            return false;
        }

        balance -= amount;
        System.out.printf("✅ Withdrawn ₹%.2f. New balance: ₹%.2f%n", amount, balance);
        return true;
    }

    @Override
    public double calculateInterest() {
        double interest = balance * INTEREST_RATE;
        System.out.printf("Interest earned (4%% p.a.): ₹%.2f%n", interest);
        return interest;
    }

    public void creditInterest() {
        double interest = calculateInterest();
        balance += interest;
        System.out.printf("✅ Interest credited. New balance: ₹%.2f%n", balance);
    }

    @Override
    public void displayAccountInfo() {
        super.displayAccountInfo();
        System.out.printf("Minimum Balance : ₹%.2f%n", MIN_BALANCE);
        System.out.printf("Interest Rate   : %.2f%% p.a.%n", INTEREST_RATE * 100);
        System.out.printf("Available Funds : ₹%.2f%n", balance - MIN_BALANCE);
        System.out.println("=".repeat(60));
    }
}
