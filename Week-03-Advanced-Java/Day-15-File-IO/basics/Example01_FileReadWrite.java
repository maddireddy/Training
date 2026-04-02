/**
 * File Read and Write
 * Demonstrates: FileReader, FileWriter, BufferedReader, BufferedWriter
 */
package basics;

import java.io.*;

public class Example01_FileReadWrite {
    public static void main(String[] args) {
        System.out.println("=== File Read and Write ===\n");

        String fileName = "example.txt";

        // Write to file
        writeToFile(fileName);

        // Read from file
        readFromFile(fileName);

        // Buffered write (more efficient)
        bufferedWrite("buffered.txt");

        // Buffered read
        bufferedRead("buffered.txt");

        // Cleanup
        new File(fileName).delete();
        new File("buffered.txt").delete();
    }

    static void writeToFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Hello, World!\n");
            writer.write("This is a test file.\n");
            writer.write("Java File I/O\n");
            System.out.println("Written to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void readFromFile(String fileName) {
        try (FileReader reader = new FileReader(fileName)) {
            int ch;
            System.out.println("\nReading from " + fileName + ":");
            while ((ch = reader.read()) != -1) {
                System.out.print((char) ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void bufferedWrite(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Line 1");
            writer.newLine();
            writer.write("Line 2");
            writer.newLine();
            writer.write("Line 3");
            System.out.println("\nBuffered write completed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void bufferedRead(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("\nBuffered read:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
