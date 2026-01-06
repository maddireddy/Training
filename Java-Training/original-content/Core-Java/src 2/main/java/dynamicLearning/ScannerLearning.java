package dynamicLearning;

import java.util.Scanner;

public class ScannerLearning {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String value = scanner.next();
        System.out.println(value);

        System.out.println("Input a number");

        int intValue = scanner.nextInt();

        System.out.println(intValue);
    }
}
