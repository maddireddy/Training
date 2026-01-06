package staticLearning;

public class StaticLeaarning1 {

    // variables and methods are allowed for static
    // We can define static blocks

    StaticLeaarning1(){
        System.out.println("Inside the default Constructor >>> ");
    }

    static int a = 20;
    static String name = "Random";

    static {
        System.out.println("Inside the static Block");
    }

    void display(){
        System.out.println("Hello from Display");
    }

    static void staticDisplay(){
        System.out.println("Inside the staticDisplay method ");
    }
}
