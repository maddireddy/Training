package com.javatraining.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A simple Hello World program to demonstrate the basic structure of a Java application.
 */
public class HelloWorld {
    private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    public static void main(String[] args) {
        logger.info("Starting HelloWorld application");
        System.out.println("Hello, World!");
        
        // Demonstrate some basic Java features
        String name = "Java Developer";
        int version = 17;
        
        System.out.printf("Welcome to Java %d, %s!%n", version, name);
        
        // Using Java 17 features
        String textBlock = """
                This is a text block in Java 17.
                It allows for multi-line strings without concatenation.
                Current Java version: %d
                """.formatted(version);
                
        System.out.println(textBlock);
        
        // Using var from Java 10+
        var message = "This is a message using 'var' keyword";
        System.out.println(message);
        
        logger.info("HelloWorld application completed successfully");
    }
}
