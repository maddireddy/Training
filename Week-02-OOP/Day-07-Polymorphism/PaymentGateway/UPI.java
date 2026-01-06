/**
 * UPI payment implementation
 */
public class UPI implements PaymentMethod {
    private String upiId;
    private String pin;

    public UPI(String upiId, String pin) {
        this.upiId = upiId;
        this.pin = pin;
    }

    @Override
    public boolean validatePaymentDetails() {
        if (!upiId.contains("@")) {
            System.out.println("❌ Invalid UPI ID format");
            return false;
        }
        if (pin.length() != 4) {
            System.out.println("❌ Invalid UPI PIN");
            return false;
        }
        return true;
    }

    @Override
    public boolean processPayment(double amount) {
        if (!validatePaymentDetails()) {
            return false;
        }
        System.out.println("Processing UPI payment...");
        System.out.printf("Amount: ₹%.2f%n", amount);
        System.out.println("UPI ID: " + upiId);
        System.out.println("✅ Payment successful via UPI!");
        return true;
    }

    @Override
    public String getPaymentType() {
        return "UPI";
    }

    @Override
    public String generateTransactionId() {
        return "UPI-" + System.currentTimeMillis();
    }
}
