package recordClasses.jdk17.rules.rule7;

import org.apache.log4j.Logger;

// Top-level or nested:
public record Transaction(String id, double amount) {
    // Static members:
    private static final Logger LOGGER = Logger.getLogger(Transaction.class);

    // Nested class:
    public static record Status(String code, String description) {
    }
}

