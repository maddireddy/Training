package swithchStatement.jdk21.patternMatching;

/*
It has introduced a new feature for switch i.e called pattern matching.
You can match patterns in a case label.

In other words you can pass objects in switch condition and this object can be
checked for different types in switch case labels.

*/

public class PatternSwitchLearning {

    public static void main(String[] args) {

        int i = 10;
        PatternSwitchLearning patternSwitchLearning = new PatternSwitchLearning();
        patternSwitchLearning.getMatch(i);

    }

    /*
    In this example passing an object to the switch condition.
    This was not possible until Java 21. And then this object can be checked for a particular data type and
    assigned to a variable as well.

    For example consider the case :
    case Integer i- > "It is an integer";
    The passed object is checked for the type “Integer” and then assigned to the variable “i” if it is an integer.
    And through the arrow operator the string “It is an integer ” is returned .
     */

    public Object getMatch(Object obj) {
        return switch (obj) {
            case Integer i -> "It is an integer";
            case String s -> "It is a string";
            case Employee s -> "It is a Employee";
            default -> "It is none of the known data types";
        };
    }


}
