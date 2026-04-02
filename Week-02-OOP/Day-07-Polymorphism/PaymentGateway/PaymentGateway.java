/**
 * Day 07: Payment Gateway System - Main Application
 * Demonstrates Interfaces and Polymorphism
 */
import java.util.Scanner;
import java.util.ArrayList;

public class PaymentGateway {
    private static ArrayList<String> transactionHistory = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=".repeat(60));
        System.out.println("        PAYMENT GATEWAY SYSTEM");
        System.out.println("=".repeat(60));

        System.out.print("Enter amount to pay: ₹");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("\nSelect Payment Method:");
        System.out.println("1. Credit Card");
        System.out.println("2. UPI");
        System.out.println("3. Net Banking");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        PaymentMethod paymentMethod = null;

        switch (choice) {
            case 1:
                System.out.print("Enter card number: ");
                String cardNum = scanner.nextLine();
                System.out.print("Enter CVV: ");
                String cvv = scanner.nextLine();
                System.out.print("Enter expiry (MM/YY): ");
                String expiry = scanner.nextLine();
                System.out.print("Enter cardholder name: ");
                String name = scanner.nextLine();
                paymentMethod = new CreditCard(cardNum, cvv, expiry, name);
                break;
            case 2:
                System.out.print("Enter UPI ID: ");
                String upiId = scanner.nextLine();
                System.out.print("Enter PIN: ");
                String pin = scanner.nextLine();
                paymentMethod = new UPI(upiId, pin);
                break;
            default:
                System.out.println("❌ Invalid choice!");
                scanner.close();
                return;
        }

        // Polymorphism in action
        if (paymentMethod.processPayment(amount)) {
            String txnId = paymentMethod.generateTransactionId();
            System.out.println("Transaction ID: " + txnId);
            transactionHistory.add(paymentMethod.getPaymentType() + " - ₹" + amount + " - " + txnId);
        }

        scanner.close();
    }
}
