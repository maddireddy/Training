/**
 * Day 07: Payment Gateway - Interfaces & Abstraction
 * PaymentMethod interface for different payment types
 */
public interface PaymentMethod {
    boolean processPayment(double amount);
    String getPaymentType();
    boolean validatePaymentDetails();
    String generateTransactionId();
}
