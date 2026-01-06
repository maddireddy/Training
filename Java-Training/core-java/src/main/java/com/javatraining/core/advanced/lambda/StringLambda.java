package stringLearning.jdk8;

import java.util.Arrays;
import java.util.List;

/*
Moving on, JDK 6 and JDK 7 continued to refine string handling, focusing more on performance optimizations
rather than introducing new APIs. The major leap in string manipulation came with JDK 8, which introduced
lambda expressions and the Stream API, revolutionizing how developers could handle data, including strings.

With JDK 8, operations on collections of strings became more concise and expressive due to lambda expressions and streams.
 */

public class StringLambda {

    public static void main(String[] args) {
        List words = Arrays.asList("Java", "is", "evolving");

        //Un-comment with JDK8

        /*String combined = words.stream()
                .map(String::toUpperCase)
                .collect(Collectors.joining(" "));*/
        //System.out.println(combined);
    }

    /*

    In this example, we transform a list of strings into a single concatenated string.
    Each word is converted to uppercase, and then they are joined.
    This approach is more readable and eliminates the need for manual iteration and string concatenation.
    Besides, JEP 192 reduces the Java heap live-data set by enhancing the G1 garbage collector so that duplicate instances of String are automatically and continuously deduplicated.
     */
}
