package stringLearning.jdk12;

/*
During the releases of JDK 12 to 15, Java focused on incremental improvements and refinements in string handling.
These versions introduced several new methods and enhancements to the String class, making string operations more intuitive and efficient.
JDK 12 introduced new methods to the String class, further simplifying common string operations.
*/
public class StringLearning {

    public static void main(String[] args) {
        String text = "Java\nEvolution";
        String indentedText = text.indent(4);  // Adds four spaces to the beginning of each line
// Result: "    Java\n    Evolution"

        String transformed = text.transform(s -> new StringBuilder(s).reverse().toString());
// Result: "noitulovE\navaJ"

        System.out.println(indentedText + " *** " + transformed);
    }
}
