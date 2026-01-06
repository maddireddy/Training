package threadLearning;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableLearningTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("Thread main started");

        //ExecutorService executorService = Executors.newFixedThreadPool(5);

        ExecutorService executorService = Executors.newFixedThreadPool(5, new NamedThreadFactory());

        List<Future<Integer>> returnedValues = executorService.invokeAll(Arrays.asList(
                new CallableLearning(50),
                new CallableLearning(40),
                new CallableLearning(30),
                new CallableLearning(20),
                new CallableLearning(10)));

        for (Future<Integer> value : returnedValues) {
            System.out.println(value.get());
        }

        executorService.shutdown();

        System.out.println("Thread main finished");
    }
}
