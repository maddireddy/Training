package ioPackage.ioexamples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileOutputStreamExample {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(FileOutputStreamExample.class);

    public static void main(String[] args) {
        File file = new File("sample.txt");
        String content = "This is the text content";

        try (OutputStream fop = new FileOutputStream(file)) {

            // if file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            // get the content in bytes
            byte[] contentInBytes = content.getBytes();

            fop.write(contentInBytes);
            System.out.println("Done");

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
