package com.javatraining.core.basics.variables;

/**
 * Demonstrates variable declarations and data types in Java
 */
public class VariableExamples {
    // Instance variables
    private int instanceVar = 10;
    private static String staticVar = "Static Variable";
    
    public static void main(String[] args) {
        // Local variables
        byte byteVar = 127;
        short shortVar = 32000;
        int intVar = 2000000;
        long longVar = 1234567890123L;
        float floatVar = 3.14f;
        double doubleVar = 3.14159265359;
        char charVar = 'A';
        boolean boolVar = true;
        String stringVar = "Hello, Java!";
        
        // Using var (Java 10+)
        var inferredVar = "Type inferred as String";
        
        // Displaying values
        System.out.println("byte: " + byteVar);
        System.out.println("short: " + shortVar);
        System.out.println("int: " + intVar);
        System.out.println("long: " + longVar);
        System.out.println("float: " + floatVar);
        System.out.println("double: " + doubleVar);
        System.out.println("char: " + charVar);
        System.out.println("boolean: " + boolVar);
        System.out.println("String: " + stringVar);
        System.out.println("Inferred: " + inferredVar);
        
        // Using instance and static variables
        VariableExamples example = new VariableExamples();
        System.out.println("Instance variable: " + example.instanceVar);
        System.out.println("Static variable: " + staticVar);
    }
}
