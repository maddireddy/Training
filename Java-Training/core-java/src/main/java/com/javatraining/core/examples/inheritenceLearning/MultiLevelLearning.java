package inheritenceLearning;

public class MultiLevelLearning {

    public static void main(String[] args) {

        //Parent

        ParentClass parentClass = new ParentClass();
        parentClass.salary();
        parentClass.salary(100000);

        //Child

        ChildClass childClass = new ChildClass();
        childClass.salary();
        parentClass.salary(200000);

        //Child

        SubChildClass subChildClass = new SubChildClass();
        subChildClass.salary();
        subChildClass.salary(300000);

        subChildClass.salary = 400000;
        subChildClass.salary();
        subChildClass.salary(50000);

        //Set Salary

        //Scenario1 - TypeCasting
        ChildClass childClass1 = (ChildClass) new ParentClass();

        //Scenario2
        ParentClass parentClass1 = new ChildClass();

    }
}
