package whileLoopLearning;

/*
the control structure of the while loop is often referred to as a pre-test loop because it checks the
condition/expression before the block is executed. In situations where we always want to execute the code block at least once,
we can employ the do while loop. Java’s do while loop is a variant of the while loop that executes the code block once,
before checking if the condition is true. It will then repeat the loop as long as the condition is true.
*/

public class DoWhileLearning {

    public static void main(String[] args) {
        // Initialize value so that we enter the loop
        double value = 0.0d;

        // Loop while value less than 0.8.
        do {
            // Get random number between 0 and 1.
            value = Math.random();
            System.out.println(value);
        } while (value < 0.8);
    }

}
