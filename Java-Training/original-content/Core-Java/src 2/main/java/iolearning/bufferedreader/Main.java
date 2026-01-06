package iolearning.bufferedreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            System.out.println(STR."Output ::: \{bufferedReader.readLine()}");
            //System.out.println(STR."Output ::: \{bufferedReader.read()}"); - Reading a file

            Scanner scanner = new Scanner(System.in);
            int readInt  = scanner.nextInt();
            System.out.println(">>> " + readInt);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
