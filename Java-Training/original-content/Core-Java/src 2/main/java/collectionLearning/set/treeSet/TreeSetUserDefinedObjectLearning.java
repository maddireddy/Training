package collectionLearning.set.treeSet;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
class Employee implements Comparable<Employee> {
    private int id;
    private String name;

    @Override
    public int compareTo(@NotNull Employee o) {
        return 0;
    }
}


public class TreeSetUserDefinedObjectLearning {
    public static void main(String[] args) {
        // Creating a TreeSet of User Defined Objects.

        /*
           The requirement for a TreeSet of user defined objects is that

           1. Either the class should implement the Comparable interface and provide
              the implementation for the compareTo() function.
           2. Or you should provide a custom Comparator while creating the TreeSet.
        */

        SortedSet<Employee> employees = new TreeSet<>();

        // TreeSet uses the compareTo() method of the Employee class to compare two employees and sort them
        employees.add(new Employee(1010, "Rajeev"));
        employees.add(new Employee(1005, "Sachin"));
        employees.add(new Employee(1008, "Chris"));

        System.out.println("Employees (sorted based on Employee class's compareTo() function)");
        System.out.println(employees);

        // Providing a Custom Comparator (This comparator compares the employees based on their Name)
        employees = new TreeSet<>(Comparator.comparing(Employee::getName));

        employees.add(new Employee(1010, "Rajeev"));
        employees.add(new Employee(1005, "Sachin"));
        employees.add(new Employee(1008, "Chris"));

        System.out.println("\nEmployees (sorted based on the supplied Comparator)");
        System.out.println(employees);

    }
}
