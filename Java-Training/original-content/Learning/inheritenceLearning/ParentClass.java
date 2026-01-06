package inheritenceLearning;

public class ParentClass {

    int salary = 100000;

    public void salary(){
        System.out.println("hello from parent salary " + salary);
    }

    public void salary(int salary){
        System.out.println("hello from parent salary " + salary);
        System.out.println("hello from parent salary " + this.salary);
    }
}
