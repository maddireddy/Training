package whileLoopLearning;

/*
Breaks are often useful for exiting while loops prematurely. Breaks are often a feature of while-true loops,
which use a true literal in place of a condition so that the loop is terminated by one or more break statements,
rather than the testing condition.
 */

public class InfiniteLoop {
    public static void main(String[] args) {

        // Loop infinitely.
        while (true) {

            //Get random number between 0 and 1.
            double value = Math.random();
            System.out.println(value);

            // Break if greater tan 0.8.
            if (value >= 0.8) {
                break;
            }
        }
    }
}
