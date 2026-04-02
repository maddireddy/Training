/**
 * Executor Service
 * Demonstrates: Thread pools, ExecutorService, Callable, Future
 */
package executor;

import java.util.concurrent.*;
import java.util.*;

public class Example06_ExecutorService {
    public static void main(String[] args) throws Exception {
        System.out.println("=== Executor Service ===\n");

        // Fixed thread pool
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit Runnable tasks
        for (int i = 0; i < 5; i++) {
            int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " running on " +
                    Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // Submit Callable tasks with Future
        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int num = i;
            Future<Integer> future = executor.submit(() -> num * num);
            futures.add(future);
        }

        // Get results
        System.out.println("\nResults:");
        for (int i = 0; i < futures.size(); i++) {
            System.out.println("Square of " + i + " = " + futures.get(i).get());
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("\nAll tasks completed!");
    }
}
