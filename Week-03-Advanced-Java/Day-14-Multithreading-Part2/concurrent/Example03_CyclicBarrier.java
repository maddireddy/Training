/**
 * CyclicBarrier
 * Demonstrates: Synchronization point for multiple threads
 */
package concurrent;

import java.util.concurrent.*;

public class Example03_CyclicBarrier {
    public static void main(String[] args) {
        System.out.println("=== CyclicBarrier ===\n");

        int parties = 3;
        CyclicBarrier barrier = new CyclicBarrier(parties, () -> {
            System.out.println("All threads reached barrier! Proceeding...\n");
        });

        for (int i = 0; i < parties; i++) {
            int threadId = i + 1;
            new Thread(() -> {
                try {
                    System.out.println("Thread " + threadId + " doing work...");
                    Thread.sleep((long) (Math.random() * 2000));
                    System.out.println("Thread " + threadId + " waiting at barrier");
                    barrier.await();
                    System.out.println("Thread " + threadId + " continuing after barrier");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
