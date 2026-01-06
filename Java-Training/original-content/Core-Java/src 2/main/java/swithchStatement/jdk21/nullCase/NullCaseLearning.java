package swithchStatement.jdk21.nullCase;
/*
You could never pass a null value to switch statements prior to Java 17 without a Null pointer exception being thrown.
Java 17 allows you to handle it this way
case null -> "It is a null object";
If you have the above switch expression you will never get Null Pointer exception if the object you pass is null.
 */

import java.util.Collection;

public class NullCaseLearning {

    static void get(Collection c) {
        switch (c) {
            case null, default -> System.out.println("Did you call the get with a null?");
        }
    }

    public static void main(String[] argv) {
        get(null);
    }
}
