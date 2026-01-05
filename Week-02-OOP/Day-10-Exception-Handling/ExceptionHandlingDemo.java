/**
 * Day 10 - Exception Handling with Real-World Scenarios
 *
 * Enhanced version from your Learning repository
 * (Original: exceptionHandling/ExceptionLearning1.java)
 *
 * Real-World Context:
 * Every production application handles exceptions:
 * - File operations (FileNotFoundException)
 * - Database connections (SQLException)
 * - Network calls (IOException)
 * - User input (NumberFormatException)
 * - API calls (TimeoutException)
 *
 * Without exception handling, applications crash!
 * With proper handling, applications recover gracefully.
 *
 * This example demonstrates:
 * - try-catch-finally blocks
 * - Multiple catch blocks
 * - Resource cleanup in finally
 * - Real database and file operations
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * File and Database Operations with Exception Handling
 */
class FileAndDBOperations {

    /**
     * Process file and database operations
     * Demonstrates proper exception handling
     *
     * @throws Exception if operations fail critically
     */
    public void processOperations() throws Exception {
        System.out.println("🔄 Starting file and database operations...\n");

        FileInputStream fileInputStream = null;
        Connection connection = null;

        try {
            // ==========================================
            // 1. FILE OPERATIONS
            // ==========================================
            System.out.println("1. FILE OPERATIONS:");
            System.out.println("-".repeat(50));

            // This will fail if file doesn't exist
            File file = new File("Presentation on java preety.pptx");

            if (!file.exists()) {
                System.out.println("❌ File not found: " + file.getName());
                System.out.println("   Creating dummy file for demonstration...");

                // In real world, handle this gracefully
                throw new IOException("File not found: " + file.getName());
            }

            fileInputStream = new FileInputStream(file);
            int count = fileInputStream.read();
            System.out.println("✅ File read successfully");
            System.out.println("   First byte value: " + count);

            // ==========================================
            // 2. DATE OPERATIONS
            // ==========================================
            Date date = new Date();
            System.out.println("✅ Current timestamp: " + date);
            System.out.println();

            // ==========================================
            // 3. DATABASE OPERATIONS
            // ==========================================
            System.out.println("2. DATABASE OPERATIONS:");
            System.out.println("-".repeat(50));

            // In real applications, use proper connection strings
            // This will fail without a real database
            try {
                connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/learning",
                    "root",
                    "password"
                );

                Statement statement = connection.createStatement();
                statement.execute("INSERT INTO LEARNING VALUES (1, 'Test', 'Data')");

                System.out.println("✅ Database operation successful");

            } catch (SQLException sqlEx) {
                // Handle database-specific errors
                System.out.println("❌ Database error occurred:");
                System.out.println("   Error Code: " + sqlEx.getErrorCode());
                System.out.println("   SQL State: " + sqlEx.getSQLState());
                System.out.println("   Message: " + sqlEx.getMessage());

                // In production: Log error, notify admin, retry, or use fallback
                throw new SQLException("Database operation failed", sqlEx);
            }

        } catch (IOException ioException) {
            // Handle file-related errors
            System.out.println("\n❌ FILE ERROR:");
            System.out.println("   Message: " + ioException.getMessage());
            System.out.println("   Action: Logging error, notifying user");

            // Rethrow to caller
            throw new IOException("File operation failed", ioException);

        } catch (SQLException sqlException) {
            // Handle database errors
            System.out.println("\n❌ DATABASE ERROR:");
            System.out.println("   Message: " + sqlException.getMessage());
            System.out.println("   Action: Logging error, using cache/fallback");

            // Rethrow to caller
            throw new SQLException("Database operation failed", sqlException);

        } finally {
            // ==========================================
            // CLEANUP: ALWAYS EXECUTES!
            // ==========================================
            System.out.println("\n3. CLEANUP (Finally Block):");
            System.out.println("-".repeat(50));
            System.out.println("🧹 Cleaning up resources (finally block always runs)");

            // Close file stream
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                    System.out.println("✅ File stream closed");
                } catch (IOException e) {
                    System.out.println("⚠️  Error closing file stream: " + e.getMessage());
                }
            }

            // Close database connection
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("✅ Database connection closed");
                } catch (SQLException e) {
                    System.out.println("⚠️  Error closing DB connection: " + e.getMessage());
                }
            }

            System.out.println("✅ Cleanup completed");
        }
    }
}

/**
 * Demonstrates try-with-resources (Java 7+)
 * Automatic resource management - No need for explicit finally!
 */
class ModernExceptionHandling {

    public void processFileModern(String filename) {
        System.out.println("\n4. MODERN APPROACH (Try-with-resources):");
        System.out.println("-".repeat(50));

        // Resources automatically closed!
        try (FileInputStream fis = new FileInputStream(filename)) {

            int data = fis.read();
            System.out.println("✅ File processed: " + filename);

        } catch (IOException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        // No finally needed! fis.close() called automatically
        System.out.println("✅ Resource auto-closed (no explicit finally needed)");
    }
}

/**
 * Custom Exception for business logic
 */
class InsufficientBalanceException extends Exception {
    private double balance;
    private double amount;

    public InsufficientBalanceException(double balance, double amount) {
        super("Insufficient balance! Available: ₹" + balance + ", Required: ₹" + amount);
        this.balance = balance;
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public double getShortfall() {
        return amount - balance;
    }
}

/**
 * Banking operations with custom exceptions
 */
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException(balance, amount);
        }

        balance -= amount;
        System.out.println("✅ Withdrawn: ₹" + amount);
        System.out.println("   New balance: ₹" + balance);
    }
}

/**
 * Main Demo Class
 */
public class ExceptionHandlingDemo {

    public static void main(String[] args) {

        System.out.println("=".repeat(70));
        System.out.println("         EXCEPTION HANDLING - PRODUCTION PATTERNS");
        System.out.println("=".repeat(70));

        // ==========================================
        // 1. FILE & DATABASE OPERATIONS
        // ==========================================
        System.out.println("\nPART 1: FILE & DATABASE EXCEPTION HANDLING");
        System.out.println("=".repeat(70));

        FileAndDBOperations ops = new FileAndDBOperations();

        try {
            ops.processOperations();
        } catch (Exception e) {
            System.out.println("\n⚠️  MAIN EXCEPTION HANDLER:");
            System.out.println("   " + e.getMessage());
            System.out.println("   Application continues to run...");
        }

        // ==========================================
        // 2. MODERN TRY-WITH-RESOURCES
        // ==========================================
        System.out.println("\n\nPART 2: MODERN EXCEPTION HANDLING");
        System.out.println("=".repeat(70));

        ModernExceptionHandling modern = new ModernExceptionHandling();
        modern.processFileModern("test.txt");

        // ==========================================
        // 3. CUSTOM EXCEPTIONS
        // ==========================================
        System.out.println("\n\nPART 3: CUSTOM BUSINESS EXCEPTIONS");
        System.out.println("=".repeat(70));

        BankAccount account = new BankAccount(5000);

        try {
            System.out.println("Attempting to withdraw ₹7000 from account with ₹5000...");
            account.withdraw(7000);

        } catch (InsufficientBalanceException e) {
            System.out.println("\n❌ TRANSACTION FAILED:");
            System.out.println("   " + e.getMessage());
            System.out.println("   Shortfall: ₹" + e.getShortfall());
            System.out.println("   Action: Offer loan or overdraft facility");
        }

        // ==========================================
        // 4. EXCEPTION HIERARCHY
        // ==========================================
        System.out.println("\n\nPART 4: EXCEPTION TYPES");
        System.out.println("=".repeat(70));

        demonstrateExceptionTypes();

        System.out.println("\n" + "=".repeat(70));
        System.out.println("End of Exception Handling Demo");
        System.out.println("=".repeat(70));
    }

    /**
     * Demonstrates different exception types
     */
    private static void demonstrateExceptionTypes() {

        // 1. Unchecked Exception (RuntimeException)
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("1. Arithmetic Exception (Unchecked): " + e.getMessage());
        }

        // 2. NullPointerException (Unchecked)
        try {
            String str = null;
            str.length();
        } catch (NullPointerException e) {
            System.out.println("2. Null Pointer Exception (Unchecked): Object is null");
        }

        // 3. ArrayIndexOutOfBoundsException (Unchecked)
        try {
            int[] arr = {1, 2, 3};
            int value = arr[5];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("3. Array Index Exception (Unchecked): Index out of range");
        }

        // 4. NumberFormatException (Unchecked)
        try {
            int num = Integer.parseInt("abc");
        } catch (NumberFormatException e) {
            System.out.println("4. Number Format Exception (Unchecked): Invalid number format");
        }
    }
}

/*
 * EXCEPTION HANDLING BEST PRACTICES:
 *
 * 1. CATCH SPECIFIC EXCEPTIONS FIRST:
 *    ✅ catch (FileNotFoundException e) { }
 *    ✅ catch (IOException e) { }
 *    ✅ catch (Exception e) { }
 *
 * 2. USE TRY-WITH-RESOURCES (Java 7+):
 *    ✅ try (FileInputStream fis = new FileInputStream("file")) { }
 *    No need for explicit close()!
 *
 * 3. ALWAYS CLEAN UP IN FINALLY:
 *    - Close files
 *    - Close database connections
 *    - Release locks
 *
 * 4. DON'T SWALLOW EXCEPTIONS:
 *    ❌ catch (Exception e) { }  // Silent failure - BAD!
 *    ✅ catch (Exception e) { log(e); throw e; }
 *
 * 5. USE CUSTOM EXCEPTIONS FOR BUSINESS LOGIC:
 *    ✅ InsufficientBalanceException
 *    ✅ InvalidOrderException
 *    ✅ ProductOutOfStockException
 *
 * 6. LOG EXCEPTIONS:
 *    ✅ logger.error("Payment failed", exception);
 *
 * EXCEPTION HIERARCHY:
 *
 * Throwable
 * ├── Error (JVM errors - don't catch)
 * │   ├── OutOfMemoryError
 * │   └── StackOverflowError
 * └── Exception
 *     ├── IOException (Checked)
 *     ├── SQLException (Checked)
 *     └── RuntimeException (Unchecked)
 *         ├── NullPointerException
 *         ├── ArithmeticException
 *         └── ArrayIndexOutOfBoundsException
 *
 * CHECKED vs UNCHECKED:
 *
 * CHECKED (Must handle or declare):
 * - IOException
 * - SQLException
 * - FileNotFoundException
 * - ClassNotFoundException
 *
 * UNCHECKED (Optional to handle):
 * - NullPointerException
 * - ArithmeticException
 * - ArrayIndexOutOfBoundsException
 * - NumberFormatException
 *
 * REAL-WORLD APPLICATIONS:
 *
 * - API calls (handle network errors)
 * - File uploads (handle I/O errors)
 * - Database queries (handle SQL errors)
 * - Payment processing (handle business exceptions)
 * - User input validation (handle format errors)
 */
