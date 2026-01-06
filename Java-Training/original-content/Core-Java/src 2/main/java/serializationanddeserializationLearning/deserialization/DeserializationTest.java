package serializationanddeserializationLearning.deserialization;

import serializationanddeserializationLearning.SerializationUtil;
import serializationanddeserializationLearning.serialization.Employee;

import java.io.IOException;
import java.io.ObjectStreamClass;

public class DeserializationTest {

    public static void main(String[] args) {
        String fileName = "employee.ser";
        Employee empNew = null;

        try {
            empNew = (Employee) SerializationUtil.deserialize(fileName);
            long serialVersionUID = ObjectStreamClass.lookup(empNew.getClass()).getSerialVersionUID();
            System.out.println(serialVersionUID);

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        System.out.println("empNew Object::" + empNew);

    }
}
