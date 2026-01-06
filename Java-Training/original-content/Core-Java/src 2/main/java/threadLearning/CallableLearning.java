package threadLearning;

import java.util.concurrent.Callable;

import static java.lang.Thread.sleep;

public class CallableLearning implements Callable<Integer> {
    private int n;

    public CallableLearning(int n) {
        this.n = n;
    }

    public Integer call() {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            System.out.println(STR."[\{Thread.currentThread().getName()}] Adding \{i}");
            sum += i;
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return sum;
    }
}
