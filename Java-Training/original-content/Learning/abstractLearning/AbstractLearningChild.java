package abstractLearning;

public class AbstractLearningChild extends AbstrctTest{
    @Override
    void withOutBodyMethod() {
        System.out.println("Inside the AbstractLearningChild class :: withOutBody method");
    }

    public static void main(String[] args) {
        AbstractLearningChild abstractLearningChild = new AbstractLearningChild();
        abstractLearningChild.withBodyMethod();
        abstractLearningChild.withOutBodyMethod();
    }
}
