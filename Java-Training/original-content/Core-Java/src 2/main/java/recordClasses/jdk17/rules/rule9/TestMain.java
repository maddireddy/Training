package recordClasses.jdk17.rules.rule9;

import java.io.*;

public class TestMain {

    public static void main(String[] args) {
        Person siva = new Person("Siva", 1);

        // Serialize 'person' object
        serializeToFile("person.ser", siva);

        // Deserialize into a new 'Person' instance
        Person deserializedPerson = deserializeFromFile("person.ser");
        System.out.println(siva.equals(deserializedPerson)); //should print true
    }

    private static void serializeToFile(String filename, Person person) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(person);
        } catch (IOException e) {
            throw new RuntimeException("Serialization failed: " + e.getMessage(), e);
        }
    }

    private static Person deserializeFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (Person) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Deserialization failed: " + e.getMessage(), e);
        }
    }
}
