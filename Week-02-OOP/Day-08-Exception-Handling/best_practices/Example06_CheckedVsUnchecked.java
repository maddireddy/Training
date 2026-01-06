/**
 * Checked vs Unchecked Exceptions
 */
package best_practices;
import java.io.*;
public class Example06_CheckedVsUnchecked {
    public static void main(String[] args) {
        // Checked exception - must handle or declare
        demonstrateChecked();

        // Unchecked exception - optional handling
        demonstrateUnchecked();
    }

    static void demonstrateChecked() {
        try {
            FileReader fr = new FileReader("file.txt");  // Checked
        } catch (FileNotFoundException e) {
            System.out.println("Checked: Must handle IOException");
        }
    }

    static void demonstrateUnchecked() {
        try {
            int[] arr = {1, 2, 3};
            System.out.println(arr[10]);  // Unchecked - RuntimeException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Unchecked: Optional to handle");
        }

        // NullPointerException - unchecked
        String str = null;
        try {
            str.length();
        } catch (NullPointerException e) {
            System.out.println("NPE caught");
        }
    }
}
