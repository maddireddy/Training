/**
 * Day 04: Payment Processor - Methods & Code Reusability
 * Real-World Payment Gateway System
 *
 * Simulates payment processing systems like Razorpay, Stripe, PayPal
 *
 * Learning Outcomes:
 * - Method creation and calling
 * - Method overloading
 * - Return types and parameters
 * - Utility class pattern
 * - Business logic in methods
 * - Real payment flow implementation
 */

import java.util.Scanner;
import java.util.Random;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PaymentProcessor {

    // Coupon codes
    private static final String COUPON_SAVE10 = "SAVE10";
    private static final String COUPON_SAVE20 = "SAVE20";
    private static final String COUPON_FIRST100 = "FIRST100";

    // Constants
    private static final double GST_RATE = 0.18; // 18%
    private static final double SHIPPING_CHARGE = 50.00;
    private static final double FREE_SHIPPING_THRESHOLD = 500.00;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=".repeat(60));
        System.out.println("          PAYMENT PROCESSOR - CHECKOUT SYSTEM");
        System.out.println("=".repeat(60));

        // Get order details
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();

        System.out.print("Enter product price: ₹");
        double baseAmount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        double subtotal = calculateSubtotal(baseAmount, quantity);
        System.out.printf("\nSubtotal: ₹%.2f\n", subtotal);

        // Apply coupon
        System.out.print("\nEnter coupon code (or press Enter to skip): ");
        String couponCode = scanner.nextLine().trim().toUpperCase();

        double discount = calculateDiscount(subtotal, couponCode);
        double afterDiscount = subtotal - discount;

        if (discount > 0) {
            System.out.printf("Discount Applied: -₹%.2f\n", discount);
            System.out.printf("After Discount: ₹%.2f\n", afterDiscount);
        }

        // Calculate GST
        double gst = calculateGST(afterDiscount);
        System.out.printf("GST (18%%): +₹%.2f\n", gst);

        // Calculate shipping
        System.out.print("Are you a premium member? (Y/N): ");
        boolean isPremium = scanner.nextLine().trim().toUpperCase().equals("Y");

        double shipping = calculateShipping(afterDiscount, isPremium);
        if (shipping > 0) {
            System.out.printf("Shipping: +₹%.2f\n", shipping);
        } else {
            System.out.println("Shipping: FREE ✓");
        }

        // Calculate final amount
        double finalAmount = calculateFinalAmount(afterDiscount, gst, shipping);

        // Display order summary
        displayOrderSummary(productName, quantity, baseAmount, subtotal, discount, gst, shipping, finalAmount);

        // Process payment
        System.out.print("\nProceed to payment? (Y/N): ");
        if (scanner.nextLine().trim().toUpperCase().equals("Y")) {
            processPayment(scanner, finalAmount);
        } else {
            System.out.println("Payment cancelled.");
        }

        scanner.close();
    }

    /**
     * Calculate subtotal
     */
    public static double calculateSubtotal(double price, int quantity) {
        return price * quantity;
    }

    /**
     * Calculate discount based on coupon code
     */
    public static double calculateDiscount(double amount, String couponCode) {
        if (couponCode.isEmpty()) {
            return 0.0;
        }

        switch (couponCode) {
            case COUPON_SAVE10:
                return amount * 0.10; // 10% off
            case COUPON_SAVE20:
                return amount * 0.20; // 20% off
            case COUPON_FIRST100:
                return Math.min(amount, 100.00); // ₹100 off (max)
            default:
                System.out.println("Invalid coupon code!");
                return 0.0;
        }
    }

    /**
     * Calculate GST (18%)
     */
    public static double calculateGST(double amount) {
        return amount * GST_RATE;
    }

    /**
     * Calculate shipping charges
     */
    public static double calculateShipping(double amount, boolean isPremium) {
        if (isPremium || amount >= FREE_SHIPPING_THRESHOLD) {
            return 0.0;
        }
        return SHIPPING_CHARGE;
    }

    /**
     * Calculate final amount (overloaded method)
     */
    public static double calculateFinalAmount(double baseAmount, double gst, double shipping) {
        return baseAmount + gst + shipping;
    }

    /**
     * Calculate final amount with discount (overloaded)
     */
    public static double calculateFinalAmount(double baseAmount, double discount, double gst, double shipping) {
        return baseAmount - discount + gst + shipping;
    }

    /**
     * Display order summary
     */
    public static void displayOrderSummary(String productName, int quantity, double price,
                                          double subtotal, double discount, double gst,
                                          double shipping, double total) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                    ORDER SUMMARY");
        System.out.println("=".repeat(60));
        System.out.println("Product: " + productName);
        System.out.println("Quantity: " + quantity);
        System.out.printf("Price per item: ₹%.2f\n", price);
        System.out.println("-".repeat(60));
        System.out.printf("Subtotal:       ₹%.2f\n", subtotal);
        if (discount > 0) {
            System.out.printf("Discount:       -₹%.2f\n", discount);
        }
        System.out.printf("GST (18%%):      +₹%.2f\n", gst);
        if (shipping > 0) {
            System.out.printf("Shipping:       +₹%.2f\n", shipping);
        } else {
            System.out.println("Shipping:       FREE");
        }
        System.out.println("-".repeat(60));
        System.out.printf("TOTAL:          ₹%.2f\n", total);
        System.out.println("=".repeat(60));
    }

    /**
     * Process payment
     */
    public static void processPayment(Scanner scanner, double amount) {
        System.out.println("\n" + "-".repeat(60));
        System.out.println("                  PAYMENT GATEWAY");
        System.out.println("-".repeat(60));

        System.out.println("Select payment method:");
        System.out.println("1. Credit/Debit Card");
        System.out.println("2. UPI");
        System.out.println("3. Net Banking");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                processCardPayment(scanner, amount);
                break;
            case 2:
                processUPIPayment(scanner, amount);
                break;
            case 3:
                processNetBanking(scanner, amount);
                break;
            default:
                System.out.println("Invalid payment method!");
        }
    }

    /**
     * Process card payment
     */
    public static void processCardPayment(Scanner scanner, double amount) {
        System.out.println("\nEnter card details:");
        System.out.print("Card Number (16 digits): ");
        String cardNumber = scanner.nextLine();

        if (!validateCard(cardNumber)) {
            System.out.println("❌ Invalid card number!");
            return;
        }

        System.out.print("CVV (3 digits): ");
        String cvv = scanner.nextLine();

        System.out.print("Expiry (MM/YY): ");
        String expiry = scanner.nextLine();

        // Simulate payment processing
        System.out.println("\nProcessing payment...");
        simulateProcessing();

        String transactionId = generateTransactionId();
        System.out.println("✅ Payment Successful!");
        System.out.printf("Amount Paid: ₹%.2f\n", amount);
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Card: **** **** **** " + cardNumber.substring(12));

        sendSMS("9876543210", "Payment of ₹" + amount + " successful. Txn: " + transactionId);
    }

    /**
     * Process UPI payment
     */
    public static void processUPIPayment(Scanner scanner, double amount) {
        System.out.print("\nEnter UPI ID: ");
        String upiId = scanner.nextLine();

        if (!validateUPI(upiId)) {
            System.out.println("❌ Invalid UPI ID!");
            return;
        }

        System.out.println("\nProcessing payment...");
        simulateProcessing();

        String transactionId = generateTransactionId();
        System.out.println("✅ Payment Successful!");
        System.out.printf("Amount Paid: ₹%.2f\n", amount);
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("UPI: " + upiId);

        sendSMS("9876543210", "Payment of ₹" + amount + " successful via UPI. Txn: " + transactionId);
    }

    /**
     * Process net banking
     */
    public static void processNetBanking(Scanner scanner, double amount) {
        System.out.println("\nSelect Bank:");
        System.out.println("1. HDFC Bank");
        System.out.println("2. ICICI Bank");
        System.out.println("3. SBI");
        System.out.print("Choice: ");
        int bank = scanner.nextInt();
        scanner.nextLine();

        String[] banks = {"", "HDFC Bank", "ICICI Bank", "SBI"};
        if (bank < 1 || bank > 3) {
            System.out.println("❌ Invalid bank selection!");
            return;
        }

        System.out.println("\nRedirecting to " + banks[bank] + " login page...");
        simulateProcessing();

        String transactionId = generateTransactionId();
        System.out.println("✅ Payment Successful!");
        System.out.printf("Amount Paid: ₹%.2f\n", amount);
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Bank: " + banks[bank]);

        sendSMS("9876543210", "Payment of ₹" + amount + " successful via Net Banking. Txn: " + transactionId);
    }

    /**
     * Validate card number (basic validation)
     */
    public static boolean validateCard(String cardNumber) {
        // Remove spaces
        cardNumber = cardNumber.replaceAll("\\s", "");

        // Check if 16 digits
        if (!cardNumber.matches("\\d{16}")) {
            return false;
        }

        // Luhn Algorithm (checksum validation)
        return luhnCheck(cardNumber);
    }

    /**
     * Luhn Algorithm for card validation
     */
    public static boolean luhnCheck(String cardNumber) {
        int sum = 0;
        boolean alternate = false;

        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(cardNumber.charAt(i));

            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }

            sum += digit;
            alternate = !alternate;
        }

        return (sum % 10 == 0);
    }

    /**
     * Validate UPI ID
     */
    public static boolean validateUPI(String upiId) {
        // Format: username@bankname
        return upiId.matches("^[a-zA-Z0-9._-]+@[a-zA-Z]+$");
    }

    /**
     * Generate transaction ID
     */
    public static String generateTransactionId() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String date = now.format(formatter);

        Random random = new Random();
        int randomNum = 10000 + random.nextInt(90000); // 5-digit random

        return "TXN-" + date + "-" + randomNum;
    }

    /**
     * Send SMS (simulation)
     */
    public static void sendSMS(String phone, String message) {
        System.out.println("\n📱 SMS sent to " + phone + ":");
        System.out.println("   " + message);
    }

    /**
     * Simulate processing delay
     */
    public static void simulateProcessing() {
        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(500);
                System.out.print(".");
            }
            System.out.println();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
