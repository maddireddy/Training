/**
 * CountDownLatch
 * Demonstrates: Waiting for multiple threads to complete
 */
package concurrent;

import java.util.concurrent.*;

public class Example02_CountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== CountDownLatch ===\n");

        int workerCount = 3;
        CountDownLatch latch = new CountDownLatch(workerCount);

        for (int i = 0; i < workerCount; i++) {
            int workerId = i + 1;
            new Thread(() -> {
                System.out.println("Worker " + workerId + " starting...");
                try {
                    Thread.sleep((long) (Math.random() * 2000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Worker " + workerId + " completed!");
                latch.countDown();
            }).start();
        }

        System.out.println("Main thread waiting for workers...");
        latch.await();
        System.out.println("All workers completed! Main thread continuing.");
    }
}
