package staticLearning;

public class StaticLearning {

    // static is a keyword in Java

    /*
        2 Area's
    1. Method Area(methods - JVM - method)
    2. Static Area
    -------------------

    Priority - Static

    int a;
    int b;

    static int b; > static area > load will start with static things

    ...

    static blocks will take the priority than main method
    static varaible can be accessed in a static way only
    non-static variables/methods can be accessed by creating a Object

    static variable and static methods can be accessed without creating an instance

    */

    static int b;

    // Block
    static {
        b = 20;
        System.out.println("Hello from static >>> " + b);
    }

    int a;

    //Method
    public static void main(String[] args) {
        StaticLearning staticLearning = new StaticLearning();
        b = 40;
        staticLearning.a = 30;
        System.out.println("Hello from Static Learning >>> " + b);
        staticLearning.display();
    }

    public void display() {
        System.out.println("Hi");
    }
}
