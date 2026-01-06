/**
 * Exception Chaining - Wrapping exceptions
 */
package exception_chaining;
public class Example05_ExceptionChaining {
    public static void main(String[] args) {
        try {
            processData();
        } catch (DataProcessingException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Caused by: " + e.getCause());
            e.printStackTrace();
        }
    }

    static void processData() throws DataProcessingException {
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            throw new DataProcessingException("Failed to process data", e);
        }
    }
}

class DataProcessingException extends Exception {
    public DataProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
