package polyLearning.methodOverriding;

public class JavaLang extends Language{

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Java Programming Language");
    }
}
