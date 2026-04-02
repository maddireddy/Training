/**
 * Try-With-Resources - Auto-closeable resources
 */
package try_catch_finally;
import java.io.*;
public class Example04_TryWithResources {
    public static void main(String[] args) {
        // Old way - manual cleanup
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("test.txt"));
        } catch (IOException e) {
            System.out.println("File not found");
        } finally {
            try { if (reader != null) reader.close(); }
            catch (IOException e) { e.printStackTrace(); }
        }

        // New way - try-with-resources
        try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))) {
            String line = br.readLine();
        } catch (IOException e) {
            System.out.println("Auto-close: File not found");
        }

        // Multiple resources
        try (FileInputStream fis = new FileInputStream("input.txt");
             FileOutputStream fos = new FileOutputStream("output.txt")) {
            // Use streams - automatically closed
        } catch (IOException e) {
            System.out.println("Multiple resources demo");
        }
    }
}
