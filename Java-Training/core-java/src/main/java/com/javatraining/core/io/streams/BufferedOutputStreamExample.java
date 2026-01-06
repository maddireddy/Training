package ioPackage.ioexamples;

import java.io.*;

public class BufferedOutputStreamExample {
    public static void main(String[] args) {
        File file = new File("sample.txt");
        String content = "This is the text content";

        try (OutputStream out = new FileOutputStream(file);
             BufferedOutputStream bout = new BufferedOutputStream(out);) {

            // if file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            // get the content in bytes
            bout.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
