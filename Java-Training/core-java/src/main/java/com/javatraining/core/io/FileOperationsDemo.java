package com.javatraining.core.io;

import java.io.*;
import java.nio.file.*;
import java.util.stream.*;

/**
 * Demonstrates file operations in Java
 */
public class FileOperationsDemo {
    public static void main(String[] args) {
        // Using java.nio.file (Java 7+)
        Path path = Paths.get("example.txt");
        
        // Writing to a file
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write("Hello, Java!\n");
            writer.write("This is a sample file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Reading from a file
        try (Stream<String> lines = Files.lines(path)) {
            System.out.println("File content:");
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Copying files
        try {
            Path dest = Paths.get("example_copy.txt");
            Files.copy(path, dest, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("\nFile copied successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
