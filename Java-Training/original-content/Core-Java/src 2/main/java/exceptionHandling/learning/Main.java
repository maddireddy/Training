package exceptionHandling.learning;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception{
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(""));// FileNotFoundException
            fileInputStream.read();
        } catch (IOException ecx) {
          ecx.printStackTrace();
          ecx.getMessage();
        } finally {
            System.out.println(" >>> ");
        }
    }
}
