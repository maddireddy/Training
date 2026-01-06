package com.javatraining.core.concurrency;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Demonstrates Java Concurrency features
 */
public class ConcurrencyDemo {
    private static final AtomicInteger counter = new AtomicInteger(0);
    
    public static void main(String[] args) throws Exception {
        // Thread creation
        Thread thread1 = new Thread(() -> 
            System.out.println("Thread 1: " + Thread.currentThread().getName())
        );
        
        // Thread with Runnable
        Runnable task = () -> {
            System.out.println("Thread 2: " + Thread.currentThread().getName());
        };
        Thread thread2 = new Thread(task);
        
        thread1.start();
        thread2.start();
        
        // Thread pool example
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                int count = counter.incrementAndGet();
                System.out.println("Task " + count + " executed by " + Thread.currentThread().getName());
            });
        }
        
        // Shutdown the executor
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);
        
        // CompletableFuture example (Java 8+)
        CompletableFuture.supplyAsync(() -> "Hello")
            .thenApply(s -> s + " World")
            .thenAccept(System.out::println);
    }
}
