package polyLearning.operatorOverLoading;

public class OperatorOverLoadingLearning {

    public static void main(String[] args) {
        int a = 10; // Integer Class Object
        int b = 20; // Integer Class Object

        // Add Two integer class Objects - Integer Object Only - + >>  10+20 = 30

        int c = a + b;

        System.out.println("Addition " + c );

        String str1= "Hello"; // String Class Object
        String str2 = "How Are you"; // String Class Object

        // Add Two String class Objects - String class Object - String Object - Hello How Are You
        // + - Concatination

        System.out.println(str1 + ", " + str2);
    }
}
