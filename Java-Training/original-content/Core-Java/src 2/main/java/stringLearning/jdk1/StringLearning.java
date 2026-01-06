package stringLearning.jdk1;

public class StringLearning {

    public static void main(String[] args) {

        /*
        In JDK 1, Java introduced the String class as an immutable sequence of characters,
        a choice that was made keeping reliability and security in mind. Immutable strings are thread-safe,
        allowing safe use across multiple threads in multi-threaded applications—their predictability and resistance
        to tampering secure sensitive data like network addresses and file paths. Java's string pooling,
        enabled by this immutability, efficiently stores only one copy of each unique string, reducing memory usage.

        String concatenation in JDK 1 primarily uses the + operator
         */

        String name = "Siva";
        String greeting = "Hello, " + name + "!";

        System.out.println(greeting);

        /*
        This method had efficiency concerns, especially for multiple concatenations.
        Each + operator usage in concatenation resulted in the creation of a new String object.
        This was particularly inefficient in scenarios like loops where concatenating a list of strings would create a
        new String object at every iteration, causing substantial performance overhead and increased memory usage.
         */

        /*
        The below code will create seven string objects to construct one.
         */

        String[] words = {"Java", "is", "cool"};
        String sentence = "";

        for (String word : words) {
            sentence = sentence + word + " "; // Inefficient concatenation
        }

        System.out.println(sentence.trim());

    }
}
