/**
 * Byte Streams - Demonstrates: FileInputStream, FileOutputStream
 */
package streams;

import java.io.*;

public class Example04_ByteStreams {
    public static void main(String[] args) {
        String fileName = "data.bin";

        // Write bytes
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            byte[] data = {65, 66, 67, 68, 69};  // A, B, C, D, E
            fos.write(data);
            System.out.println("Bytes written");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Read bytes
        try (FileInputStream fis = new FileInputStream(fileName)) {
            int b;
            System.out.print("Read bytes: ");
            while ((b = fis.read()) != -1) {
                System.out.print((char) b + " ");
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

        new File(fileName).delete();
    }
}
