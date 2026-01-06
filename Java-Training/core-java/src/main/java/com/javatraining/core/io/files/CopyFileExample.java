package ioPackage.fileoperations.fileexamples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class CopyFileExample {
    private static final Logger LOGGER = LoggerFactory.getLogger(CopyFileExample.class);

    public static void main(String[] args) {
        copyFile();
    }

    public static void copyFile() {
        try (InputStream inStream = new FileInputStream("sample1.txt");
             OutputStream outStream = new FileOutputStream("sample2.txt")) {
            byte[] buffer = new byte[1024];
            int length;
            // copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }
        } catch (IOException e1) {
            LOGGER.error(e1.getMessage());
        }
    }
}
