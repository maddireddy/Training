/**
 * Object Serialization - Demonstrates: Serializing and deserializing objects
 */
package serialization;

import java.io.*;

public class Example03_Serialization {
    public static void main(String[] args) {
        Person person = new Person("Alice", 30, "alice@example.com");

        // Serialize
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("person.ser"))) {
            oos.writeObject(person);
            System.out.println("Object serialized");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("person.ser"))) {
            Person loaded = (Person) ois.readObject();
            System.out.println("Object deserialized: " + loaded);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Cleanup
        new File("person.ser").delete();
    }
}

class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;
    private transient String email;  // Not serialized

    Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", email='" + email + "'}";
    }
}
