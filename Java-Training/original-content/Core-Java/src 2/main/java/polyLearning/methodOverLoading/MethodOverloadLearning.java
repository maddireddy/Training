package polyLearning.methodOverLoading;

/*
Compile-time Polymorphism

The method that is called is determined by the compiler.
Hence, it is also known as compile-time polymorphism.
 */

public class MethodOverloadLearning {

    public static void main(String[] args) {
        Pattern d1 = new Pattern();

        // call method without any argument
        d1.display();
        System.out.println("\n");

        // call method with a single argument
        d1.display('#');
        System.out.println("\n");

        d1.display("~");

    }
}
