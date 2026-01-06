/**
 * NIO Files
 * Demonstrates: java.nio.file.Files, Path, reading/writing with NIO
 */
package nio;

import java.nio.file.*;
import java.io.IOException;
import java.util.*;

public class Example02_NIOFiles {
    public static void main(String[] args) {
        System.out.println("=== NIO Files ===\n");

        try {
            // Create file
            Path path = Paths.get("nio-example.txt");

            // Write lines
            List<String> lines = Arrays.asList(
                "Line 1",
                "Line 2",
                "Line 3"
            );
            Files.write(path, lines);
            System.out.println("File written");

            // Read all lines
            List<String> readLines = Files.readAllLines(path);
            System.out.println("\nRead lines:");
            readLines.forEach(System.out::println);

            // Read all bytes
            byte[] bytes = Files.readAllBytes(path);
            System.out.println("\nBytes read: " + bytes.length);

            // File info
            System.out.println("\nFile info:");
            System.out.println("Exists: " + Files.exists(path));
            System.out.println("Size: " + Files.size(path) + " bytes");
            System.out.println("Readable: " + Files.isReadable(path));
            System.out.println("Writable: " + Files.isWritable(path));

            // Delete file
            Files.delete(path);
            System.out.println("\nFile deleted");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
