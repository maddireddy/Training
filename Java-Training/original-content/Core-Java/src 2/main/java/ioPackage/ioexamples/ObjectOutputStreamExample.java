package ioPackage.ioexamples;

import lombok.Getter;
import lombok.Setter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public class ObjectOutputStreamExample {

    public static void main(String[] args) {
        try (final FileOutputStream fout = new FileOutputStream("employees.txt");
             final ObjectOutputStream out = new ObjectOutputStream(fout)) {
            out.writeInt(12345);
            out.writeObject("Today");
            out.writeObject(new Date());

            out.flush();
            System.out.println("success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

@Getter
@Setter
class EmployeeFile implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;

}