package forLoopLearning.jdk9;

/*
Java 9 In Java 9, a new variant of the for loop was introduced that allows you to declare the
loop variable inside the loop itself, rather than in the initialization statement
*/

import java.util.List;

public class ForLoopLearning {

    public static void main(String[] args) {
        for (var number : List.of(1, 2, 3, 4, 5)) {
            System.out.println(number);
        }
    }

}
