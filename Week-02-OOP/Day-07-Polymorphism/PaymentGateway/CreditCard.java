/**
 * CreditCard payment implementation
 */
public class CreditCard implements PaymentMethod {
    private String cardNumber;
    private String cvv;
    private String expiryDate;
    private String cardHolderName;

    public CreditCard(String cardNumber, String cvv, String expiryDate, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
        this.cardHolderName = cardHolderName;
    }

    @Override
    public boolean validatePaymentDetails() {
        // Validate card number length
        if (cardNumber.length() != 16) {
            System.out.println("❌ Invalid card number length");
            return false;
        }
        // Validate CVV
        if (cvv.length() != 3) {
            System.out.println("❌ Invalid CVV");
            return false;
        }
        return true;
    }

    @Override
    public boolean processPayment(double amount) {
        if (!validatePaymentDetails()) {
            return false;
        }
        System.out.println("Processing Credit Card payment...");
        System.out.printf("Amount: ₹%.2f%n", amount);
        System.out.println("Card: **** **** **** " + cardNumber.substring(12));
        System.out.println("✅ Payment successful via Credit Card!");
        return true;
    }

    @Override
    public String getPaymentType() {
        return "Credit Card";
    }

    @Override
    public String generateTransactionId() {
        return "CC-" + System.currentTimeMillis();
    }
}
