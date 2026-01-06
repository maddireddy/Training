package exceptionHandling;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CustomExceptionLearning {

    public static void main(String[] args) throws CutomException {
        try {
            System.out.println("Inside");
            File file = new File("");
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read();
        } catch (IOException exception) {
            throw new CutomException(exception.getMessage());
        }
    }
}
