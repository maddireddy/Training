package dynamicLearning;

import java.io.DataInputStream;
import java.io.IOException;

public class DataInputLearning {

    public static void main(String[] args) throws IOException {
        System.out.println("Input Something");
        DataInputStream dataInputStream = new DataInputStream(System.in);
        String value = dataInputStream.readLine();
        System.out.println(value);
    }
}
