/**
 * Atomic Variables
 * Demonstrates: AtomicInteger, AtomicLong, AtomicReference
 */
package concurrent;

import java.util.concurrent.atomic.*;

public class Example01_AtomicVariables {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Atomic Variables ===\n");

        // AtomicInteger
        AtomicInteger atomicCounter = new AtomicInteger(0);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                atomicCounter.incrementAndGet();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                atomicCounter.incrementAndGet();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("AtomicInteger count: " + atomicCounter.get());

        // AtomicLong
        AtomicLong atomicLong = new AtomicLong(0);
        atomicLong.addAndGet(100);
        System.out.println("AtomicLong: " + atomicLong.get());

        // AtomicReference
        AtomicReference<String> atomicRef = new AtomicReference<>("Initial");
        atomicRef.set("Updated");
        System.out.println("AtomicReference: " + atomicRef.get());

        // Compare and set
        boolean updated = atomicRef.compareAndSet("Updated", "Final");
        System.out.println("CAS successful: " + updated);
        System.out.println("Value: " + atomicRef.get());
    }
}
