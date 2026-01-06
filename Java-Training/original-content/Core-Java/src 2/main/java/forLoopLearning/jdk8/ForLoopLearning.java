package forLoopLearning.jdk8;

/*
Java 8 In Java 8, the for loop syntax was extended to support lambda expressions and the new Stream API.
*/

import java.util.Arrays;
import java.util.List;

public class ForLoopLearning {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        names.forEach(name -> System.out.println(name));
    }
}
