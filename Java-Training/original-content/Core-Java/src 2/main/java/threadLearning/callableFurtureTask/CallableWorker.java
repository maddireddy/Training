package threadLearning.callableFurtureTask;

import java.util.Random;
import java.util.concurrent.Callable;

public class CallableWorker implements Callable<Integer> {
    public Integer call() throws Exception {
        Random random = new Random();
        int randomNumber = random.nextInt(5);
        Thread.sleep(randomNumber * 1000);
        return randomNumber;
    }
}
