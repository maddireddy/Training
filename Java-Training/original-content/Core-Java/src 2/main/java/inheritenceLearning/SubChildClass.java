package inheritenceLearning;

public class SubChildClass extends ChildClass {
    int salary;

    public void salary() {
        System.out.println("hello from sub-child salary " + salary);
    }

    public void salary(int salary) {
        System.out.println("hello from sub-child salary " + salary);
        System.out.println("hello from sub-child salary " + this.salary);
    }

}
