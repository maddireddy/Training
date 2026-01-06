package staticLearning;

public class StaticLeaarning1 {

    // variables and methods are allowed for static
    // We can define static blocks

    static int a = 20;
    static String name = "Random";

    static {
        System.out.println("Inside the static Block");
    }

    StaticLeaarning1() {
        System.out.println("Inside the default Constructor >>> ");
    }

    static void staticDisplay() {
        System.out.println("Inside the staticDisplay method ");
    }

    void display() {
        System.out.println("Hello from Display");
    }
}
