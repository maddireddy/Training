package forLoopLearning.jdk5;

/*
Java 5.0 In Java 5.0, the for-each loop (also known as the enhanced for loop) was introduced.
This allows you to iterate over the elements of an array or a collection without using an explicit loop counter
*/

public class ForLoopLearning {

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};
        for (int number : numbers) {
            System.out.println(number);
        }
    }
}
