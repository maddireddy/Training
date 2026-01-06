package ioPackage.dataStreams;

import java.io.*;

public class DataStreamsLearning {

    public static void main(String[] args) {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("data.dat"));
             DataInputStream in = new DataInputStream(new FileInputStream("data.dat"))) {
            out.writeInt(123);
            out.writeDouble(45.67);
            out.writeUTF("Hello, world!");

            int intValue = in.readInt();
            double doubleValue = in.readDouble();
            String strValue = in.readUTF();

            System.out.println(STR."Int: \{intValue}");
            System.out.println(STR."Double: \{doubleValue}");
            System.out.println(STR."String: \{strValue}");

            /*System.out.println("Int: " + intValue);
            System.out.println("Double: " + doubleValue);
            System.out.println("String: " + strValue);*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
