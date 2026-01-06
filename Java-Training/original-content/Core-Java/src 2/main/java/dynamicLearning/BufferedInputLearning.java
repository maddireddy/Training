package dynamicLearning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferedInputLearning {

    public static void main(String[] args) throws IOException {
        System.out.println("Please input your values :::");

        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String readText = bufferedReader.readLine();
        System.out.println(">>> " + readText);

        System.out.println("Input another text >>>");
        int number = bufferedReader.read();
        System.out.println(number);

        // Error

        System.out.println("Enter a number >>> ");
        String numberValue = bufferedReader.readLine();
        System.out.println(" >>> " + numberValue);

    }
}
