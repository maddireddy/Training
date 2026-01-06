package contructorLearning;

public class ConstructorLearning {

    int a = 10;
    int b = 20;

    ConstructorLearning() {
        //Constructor
        //Default Constructor with no Arguments
        //Thumb Rule - Constructor Name should be the class name
        //Should not create any method with the class name - If so it will treat it as a default constructor
        // For Constructors "default" access-modifier is not allowed
    }

    ConstructorLearning(String a) {
        System.out.println("Im in String argumented constructor " + a);
    }

    ConstructorLearning(int a) {
        System.out.println("Single argumented Constructor " + a);
    }

    ConstructorLearning(int a, int b) {
        System.out.println(this.a + " <<>>>" + this.b);
        System.out.println("Argumented vaules " + a + " ::: " + b);
    }

    public static void main(String[] args) {
        ConstructorLearning constructorLearning = new ConstructorLearning();
        int a1 = constructorLearning.a;
        System.out.println("A value ::: " + a1);

        ConstructorLearning constructorLearning1 = new ConstructorLearning(10);
        int b1 = constructorLearning1.b;
        System.out.println("B Value ::: " + b1);

        ConstructorLearning constructorLearning2 = new ConstructorLearning("Siva");
        int b2 = constructorLearning2.b;
        System.out.println("B Value ::: " + b2);

        ConstructorLearning constructorLearning3 = new ConstructorLearning(20, 30);
        int a2 = constructorLearning3.a;
        System.out.println("A Value :: " + a2);
    }
}
