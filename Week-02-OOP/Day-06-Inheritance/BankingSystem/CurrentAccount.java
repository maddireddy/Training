/**
 * Current Account - for businesses, no interest, overdraft facility
 */
public class CurrentAccount extends Account {
    private static final double OVERDRAFT_LIMIT = 50000.00;
    private static final double MONTHLY_FEE = 500.00;

    public CurrentAccount(String accountNumber, String holderName, double initialDeposit) {
        super(accountNumber, holderName, initialDeposit, "Current Account");
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Invalid withdrawal amount!");
            return false;
        }

        // Current account allows overdraft
        if (balance - amount < -OVERDRAFT_LIMIT) {
            System.out.printf("❌ Withdrawal failed! Overdraft limit of ₹%.2f exceeded.%n", OVERDRAFT_LIMIT);
            System.out.printf("Available (including overdraft): ₹%.2f%n", balance + OVERDRAFT_LIMIT);
            return false;
        }

        balance -= amount;
        System.out.printf("✅ Withdrawn ₹%.2f. New balance: ₹%.2f%n", amount, balance);

        if (balance < 0) {
            System.out.println("⚠️  Account is in overdraft. Interest charges may apply.");
        }

        return true;
    }

    @Override
    public double calculateInterest() {
        // Current accounts don't earn interest
        System.out.println("Current accounts do not earn interest.");
        return 0.0;
    }

    public void deductMonthlyFee() {
        balance -= MONTHLY_FEE;
        System.out.printf("Monthly maintenance fee of ₹%.2f deducted. Balance: ₹%.2f%n", MONTHLY_FEE, balance);
    }

    @Override
    public void displayAccountInfo() {
        super.displayAccountInfo();
        System.out.printf("Overdraft Limit : ₹%.2f%n", OVERDRAFT_LIMIT);
        System.out.printf("Monthly Fee     : ₹%.2f%n", MONTHLY_FEE);
        System.out.printf("Available Funds : ₹%.2f%n", balance + OVERDRAFT_LIMIT);

        if (balance < 0) {
            System.out.printf("Overdraft Used  : ₹%.2f%n", Math.abs(balance));
        }

        System.out.println("=".repeat(60));
    }
}
