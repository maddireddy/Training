package intfLearning;

public class InterfaceLearning implements InterfaceTest1, InterfaceTest2 {

    // Interfaces are special kind of Abstract classes and we have to provide the body immediately once we implement an
    //interface

    // Reusability of Interfaces - DBConnection(m1(),m2(),m3()) - MySQL
    // DBConnection(m1(),m2(),m3()) - Oracle, DB2

    // Dynamic Nature of the project will be defined by using interfaces

    public static void main(String[] args) {
        System.out.println("Inside the InterfaceLearning class :::");
        InterfaceLearning interfaceLearning = new InterfaceLearning();
        interfaceLearning.withBodyMethod();
        interfaceLearning.withOutBodyMethod();
        interfaceLearning.display();
    }

    @Override
    public void withBodyMethod() {
        System.out.println("Inside the InterfaceLearning:withBodyMethod ::: ");
    }

    @Override
    public void withOutBodyMethod() {
        System.out.println("Inside the InterfaceLearning:withOutBodyMethod ::: ");
    }

    @Override
    public void display() {
        System.out.println("Inside the InterfaceLearning:display() method ::: ");
    }
}
