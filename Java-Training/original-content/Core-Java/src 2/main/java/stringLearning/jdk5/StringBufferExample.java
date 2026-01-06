package stringLearning.jdk5;

/*
To solve the efficiency concern, Java introduced the StringBuffer class, which provides a mutable sequence of characters.
This was a game-changer for string manipulation, especially in scenarios involving frequent modifications.
 */

/*
JDK 5 took string manipulation a step further with the introduction of StringBuilder.
It was similar to StringBuffer in providing a mutable sequence of characters but differed in a crucial aspect.

The major difference between these two is that StringBuilder is a bit faster and more suitable for single-threaded scenarios
as it's not thread-safe. In contrast, StringBuffer is thread-safe and slightly slower due to its synchronized methods,
making it ideal for multi-threaded environments.
Both offer similar APIs, allowing for easy interchangeability based on your thread safety requirements.
*/

public class StringBufferExample {

    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer();
        String[] words = {"Java", "evolves", "with", "time"};

        for (String word : words) {
            stringBuffer.append(word).append(" ");
        }

        System.out.println(stringBuffer.toString().trim());
    }

}
