package inheritenceLearning;

public class SingleInheritenceLearning {

    public static void main(String[] args) {

        //Parent

        ParentClass parentClass = new ParentClass();
        parentClass.salary();
        parentClass.salary(100000);

        System.out.println("--------------------------");

        //Child

        ChildClass childClass = new ChildClass();
        childClass.salary();
        childClass.salary(20000);


        System.out.println("--------------------------");

        //Set Salary

        //Default Casting
        ParentClass parentClass1 = new ChildClass();
        parentClass1.salary();
        parentClass1.salary(100000);

        System.out.println("--------------------------");

        // Explicit Type Casting - Right hand side object should be the parent/bigger object
        ChildClass childClass1 = (ChildClass) new ParentClass();
        childClass1.salary();
        childClass1.salary(20000);


    }
}
