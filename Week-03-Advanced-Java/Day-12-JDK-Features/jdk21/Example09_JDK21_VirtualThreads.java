/**
 * JDK 21 Features - Virtual Threads (Project Loom) - 2023 LTS
 * Key Feature: Lightweight threads for massive concurrency
 */
package jdk21;

import java.time.Duration;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Example09_JDK21_VirtualThreads {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("=== Traditional Platform Threads ===");
        platformThreadExample();

        System.out.println("\n=== Virtual Threads (JDK 21) ===");
        virtualThreadExample();

        System.out.println("\n=== Virtual Thread Pool ===");
        virtualThreadPoolExample();

        System.out.println("\n=== Massive Concurrency Demo ===");
        massiveConcurrencyDemo();
    }

    // Traditional platform threads
    static void platformThreadExample() throws InterruptedException {
        long start = System.currentTimeMillis();

        Thread thread = Thread.ofPlatform().start(() -> {
            System.out.println("Platform thread: " + Thread.currentThread());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.join();
        System.out.println("Platform thread time: " + (System.currentTimeMillis() - start) + "ms");
    }

    // Virtual threads (JDK 21)
    static void virtualThreadExample() throws InterruptedException {
        long start = System.currentTimeMillis();

        Thread vThread = Thread.ofVirtual().start(() -> {
            System.out.println("Virtual thread: " + Thread.currentThread());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        vThread.join();
        System.out.println("Virtual thread time: " + (System.currentTimeMillis() - start) + "ms");
    }

    // Virtual thread executor
    static void virtualThreadPoolExample() throws InterruptedException {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 10).forEach(i -> {
                executor.submit(() -> {
                    System.out.println("Task " + i + " on " + Thread.currentThread());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i;
                });
            });
        } // Auto-close executor
    }

    // Demonstrate massive concurrency with virtual threads
    static void massiveConcurrencyDemo() throws InterruptedException {
        int taskCount = 10000;  // 10K concurrent tasks!

        // With virtual threads - handles 10K tasks easily
        long start = System.currentTimeMillis();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, taskCount).forEach(i -> {
                executor.submit(() -> {
                    try {
                        Thread.sleep(Duration.ofMillis(100));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            });
        }

        long duration = System.currentTimeMillis() - start;
        System.out.println("Completed " + taskCount + " virtual thread tasks in " + duration + "ms");

        // Note: Try running 10K platform threads and see the difference!
        // Platform threads would consume significant memory and be slow
    }
}
