package inheritenceLearning;

public class ChildClass extends ParentClass {

    public void salary() {
        System.out.println("hello from child salary " + super.salary);
    }

    public void salary(int salary) {
        System.out.println("hello from child salary " + salary);
        System.out.println("hello from child salary " + super.salary);
    }
}
