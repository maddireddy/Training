package stringLearning.jdk9;

/*

In Java 9, there has been a significant improvement in how string concatenation is handled at the bytecode level.
The introduction of invokedynamic, a special bytecode instruction, has changed the game.

When concatenating strings using the + operator, Java 9 and later versions use invokedynamic, which delegates
the optimization responsibility to java.lang.invoke.StringConcatFactory#makeConcatWithConstants.
This method is more efficient in optimizing string concatenation. Consider the following code:
 */

import java.util.ArrayList;
import java.util.List;

public class StringLearning {

    public static void main(String[] args) {
        StringLearning sc = new StringLearning();
        sc.run();

        String text = "   Hello Java!   ";
        String trimmed = text.strip();    // "Hello Java!"
        String repeated = text.repeat(2); // "   Hello Java!      Hello Java!   "
        boolean blank = text.isBlank();   // false

        System.out.println(trimmed + " *>>>* " + repeated + " *>>>* " + blank);
    }

    private void run() {
        List<String> ls = new ArrayList<>();
        ls.add("Good Day");

        ArrayList<String> als = new ArrayList<>();
        als.add("Dydh Da");
    }
}

/*
On average, 50% of a typical Java heap may be consumed by String objects.
This will vary from application to application, but on average,
the heap requirement for such a program running with Java 9 is only 75% of that same program running in Java 8.
 */
