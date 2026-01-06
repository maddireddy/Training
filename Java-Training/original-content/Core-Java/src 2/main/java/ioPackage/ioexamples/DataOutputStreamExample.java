package ioPackage.ioexamples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataOutputStreamExample {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataOutputStreamExample.class);

    public static void main(String[] args) {

        final double[] prices = {19.99, 9.99, 15.99, 3.99, 4.99};
        final int[] units = {12, 8, 13, 29, 50};
        final String[] descs = {"Java T-shirt", "Java Mug", "Duke Juggling Dolls", "Java Pin", "Java Key Chain"};

        try (DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream("sample.txt")))) {
            for (int i = 0; i < prices.length; i++) {
                out.writeDouble(prices[i]);
                out.writeInt(units[i]);
                out.writeUTF(descs[i]);
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
