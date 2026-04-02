/**
 * Synchronization
 * Demonstrates: synchronized methods, synchronized blocks, race conditions
 */
package synchronization;

public class Example03_Synchronization {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Synchronization ===\n");

        // Without synchronization - race condition
        Counter unsafeCounter = new Counter();
        runCounterTest(unsafeCounter, "Unsafe Counter");

        // With synchronized method
        SynchronizedCounter safeCounter = new SynchronizedCounter();
        runCounterTest(safeCounter, "Synchronized Counter");

        // With synchronized block
        BlockSyncCounter blockCounter = new BlockSyncCounter();
        runCounterTest(blockCounter, "Block Synchronized Counter");
    }

    static void runCounterTest(Counter counter, String name) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) counter.increment();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) counter.increment();
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(name + ": " + counter.getCount());
    }
}

class Counter {
    protected int count = 0;

    public void increment() {
        count++;  // Not atomic!
    }

    public int getCount() {
        return count;
    }
}

class SynchronizedCounter extends Counter {
    @Override
    public synchronized void increment() {
        count++;
    }
}

class BlockSyncCounter extends Counter {
    private final Object lock = new Object();

    @Override
    public void increment() {
        synchronized (lock) {
            count++;
        }
    }
}
