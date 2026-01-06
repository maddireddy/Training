package objectPack;

public class ObjectCreation {

    int a = 10;
    int b = 20;
    String name = "hello from ObjectCreation";

    public static void main(String[] args) {

        ObjectCreation objectCreation = new ObjectCreation();
        objectCreation.display();

        // AA Proper class with the implemented body methods
        // Interfaces - No Objects going to create
        // Abstract - Partially implemented and unimplemented methods

        // . operator to access fields and methods

    }

    void display() {
        System.out.println("My Object Learning Method" + name);
    }
}
