package com.javatraining.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for HelloWorld
 */
class HelloWorldTest {

    @Test
    void testMain() {
        // This is a simple test to verify the class can be instantiated
        HelloWorld helloWorld = new HelloWorld();
        assertNotNull(helloWorld, "HelloWorld instance should not be null");
        
        // Test that the main method runs without exceptions
        assertDoesNotThrow(() -> HelloWorld.main(new String[]{}));
    }
    
    @Test
    void testStringFormatting() {
        String expected = "Welcome to Java 17, Test User!";
        String actual = String.format("Welcome to Java %d, %s!", 17, "Test User");
        assertEquals(expected, actual, "String formatting should match expected output");
    }
}
