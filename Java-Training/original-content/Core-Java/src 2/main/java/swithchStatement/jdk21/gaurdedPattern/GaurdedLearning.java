package swithchStatement.jdk21.gaurdedPattern;

/*

 */

import swithchStatement.jdk21.patternMatching.Employee;

public class GaurdedLearning {

    public static void main(String[] args) {

        GaurdedLearning gaurdedLearning = new GaurdedLearning();
        Employee employee = new Employee();
        employee.setFname("IT");

        Object it = gaurdedLearning.getMatch(employee);
        System.out.println(it);

    }

    public Object getMatch(Object obj) {
        return switch (obj) {
            case Integer i -> "It is an integer";
            case String s -> "It is a string";
            case Employee employee when employee.getFname().equals("IT") -> "IT Employee";
            default -> "It is none of the known data types";
        };
    }
}
