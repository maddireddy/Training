package threadLearning;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolTest {

    public static void main(String[] args) {

        System.out.println("Thread main started");

        //ExecutorService executorService = Executors.newFixedThreadPool(5);
        //ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new RunnableLearning());
        executorService.execute(new RunnableLearning());
        executorService.execute(new RunnableLearning());
        executorService.execute(new RunnableLearning());
        executorService.execute(new RunnableLearning());

        executorService.shutdown();

        System.out.println("Thread main finished");

    }
}
