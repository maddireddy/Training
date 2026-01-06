package dynamicLearning;

public class ConsoleLeaarning {

    public static void main(String[] args) {
        System.out.println("Input Something");
        char[] password = System.console().readPassword();
        System.out.println("Pass" + password);
    }
}
