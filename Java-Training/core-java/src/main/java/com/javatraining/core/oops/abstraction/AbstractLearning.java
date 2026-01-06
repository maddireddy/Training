package abstractLearning;

public abstract class AbstractLearning extends AbstrctTest {

    public static void main(String[] args) {
        System.out.println("Inside the Abstract Learning class");

        // Compiler will through if we try to create a Object for Abstract class - No Objects for Abstract Class
    }

    abstract void withOutBodyMethod();
}
