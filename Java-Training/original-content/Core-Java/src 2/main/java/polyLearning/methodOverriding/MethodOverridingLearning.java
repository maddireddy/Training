package polyLearning.methodOverriding;

/*
Runtime Polymorphism

The method that is called is determined during the execution of the program.
Hence, method overriding is a run-time polymorphism.
*/

public class MethodOverridingLearning {

    public static void main(String[] args) {

        // create an object of Java class
        JavaLang j1 = new JavaLang();
        j1.displayInfo();

        // create an object of Language class
        Language l1 = new Language();
        l1.displayInfo();
    }
}
