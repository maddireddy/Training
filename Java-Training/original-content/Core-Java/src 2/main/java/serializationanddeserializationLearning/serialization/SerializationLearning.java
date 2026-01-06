package serializationanddeserializationLearning.serialization;

import serializationanddeserializationLearning.SerializationUtil;

import java.io.IOException;
import java.io.ObjectStreamClass;

public class SerializationLearning {

    public static void main(String[] args) {
        String fileName = "employee.ser";

        Employee emp = new Employee();
        emp.setId(100);
        emp.setName("Siva");
        emp.setSalary(5000);
        //emp.setPassword("JJJ");

        //serialize to file
        try {
            SerializationUtil.serialize(emp, fileName);
            long serialVersionUID = ObjectStreamClass.lookup(emp.getClass()).getSerialVersionUID();
            System.out.println(serialVersionUID);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("emp Object::" + emp);
    }

}
