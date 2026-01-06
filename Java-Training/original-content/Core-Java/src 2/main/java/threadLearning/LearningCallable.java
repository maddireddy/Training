package threadLearning;

import java.util.concurrent.Callable;

public class LearningCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 2;
    }
}
