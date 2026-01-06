package staticLearning;

public class Testing {

    public static void main(String[] args) {
        StaticLeaarning1 staticLeaarning1 = new StaticLeaarning1();

        // We can directly access static feilds/ methods

        int a = StaticLeaarning1.a;

        staticLeaarning1.display();

        StaticLeaarning1.staticDisplay();
    }
}
