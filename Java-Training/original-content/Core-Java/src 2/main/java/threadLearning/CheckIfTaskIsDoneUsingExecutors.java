package threadLearning;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

public class CheckIfTaskIsDoneUsingExecutors {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        System.out.println("Thread main started");

        ExecutorService executorService = newSingleThreadExecutor(new NamedThreadFactory());
        Future<Integer> result = executorService.submit(new CallableLearning(50));

        while (!result.isDone()) {
            System.out.println("Task is still processing");
            Thread.sleep(500l);
        }

        System.out.println(STR."Task is finished: \{result.isDone()}");
        System.out.println(STR."Task result is: \{result.get()}");

        executorService.shutdown();

        System.out.println("Thread main finished");
    }
}
