/**
 * Fixed Deposit Account - locked period, high interest, no withdrawals
 */
public class FixedDepositAccount extends Account {
    private static final double INTEREST_RATE = 0.07; // 7% p.a.
    private int tenureMonths;
    private java.time.LocalDate maturityDate;

    public FixedDepositAccount(String accountNumber, String holderName, double depositAmount, int tenureMonths) {
        super(accountNumber, holderName, depositAmount, "Fixed Deposit");
        this.tenureMonths = tenureMonths;
        this.maturityDate = java.time.LocalDate.now().plusMonths(tenureMonths);
    }

    @Override
    public boolean withdraw(double amount) {
        System.out.println("❌ Withdrawals not allowed from Fixed Deposit before maturity!");
        System.out.println("Maturity Date: " + maturityDate);
        return false;
    }

    @Override
    public double calculateInterest() {
        double interest = balance * INTEREST_RATE * (tenureMonths / 12.0);
        System.out.printf("Interest to be earned (7%% p.a.): ₹%.2f%n", interest);
        return interest;
    }

    public double breakFD() {
        System.out.println("\n⚠️  Breaking Fixed Deposit before maturity!");
        double penaltyRate = 0.02; // 2% penalty
        double penalty = balance * penaltyRate;
        double amount = balance - penalty;

        System.out.printf("Principal Amount: ₹%.2f%n", balance);
        System.out.printf("Penalty (2%%):    ₹%.2f%n", penalty);
        System.out.printf("Amount Returned:  ₹%.2f%n", amount);

        balance = 0;
        return amount;
    }

    public double matureFD() {
        double interest = calculateInterest();
        double maturityAmount = balance + interest;

        System.out.println("\n✅ Fixed Deposit Matured!");
        System.out.printf("Principal: ₹%.2f%n", balance);
        System.out.printf("Interest:  ₹%.2f%n", interest);
        System.out.printf("Maturity Amount: ₹%.2f%n", maturityAmount);

        balance = maturityAmount;
        return maturityAmount;
    }

    @Override
    public void displayAccountInfo() {
        super.displayAccountInfo();
        System.out.printf("Tenure          : %d months%n", tenureMonths);
        System.out.printf("Interest Rate   : %.2f%% p.a.%n", INTEREST_RATE * 100);
        System.out.println("Maturity Date   : " + maturityDate);
        System.out.printf("Maturity Amount : ₹%.2f%n", balance + calculateInterest());
        System.out.println("=".repeat(60));
    }
}
