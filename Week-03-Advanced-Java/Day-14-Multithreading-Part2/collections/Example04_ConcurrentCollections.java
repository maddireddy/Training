/**
 * Concurrent Collections
 * Demonstrates: ConcurrentHashMap, CopyOnWriteArrayList, BlockingQueue
 */
package collections;

import java.util.concurrent.*;
import java.util.*;

public class Example04_ConcurrentCollections {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Concurrent Collections ===\n");

        // ConcurrentHashMap
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        map.forEach((k, v) -> System.out.println(k + " = " + v));

        // CopyOnWriteArrayList
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("One");
        list.add("Two");
        list.add("Three");

        System.out.println("\nCopyOnWriteArrayList: " + list);

        // BlockingQueue
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

        // Producer
        new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    String item = "Item-" + i;
                    queue.put(item);
                    System.out.println("Produced: " + item);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // Consumer
        new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    String item = queue.take();
                    System.out.println("Consumed: " + item);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(4000);
        System.out.println("\nDemo completed!");
    }
}
