/**
 * ReadWriteLock - Demonstrates: Multiple readers, single writer
 */
package locks;

import java.util.concurrent.locks.*;

public class Example06_ReadWriteLock {
    private static int value = 0;
    private static final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private static final Lock readLock = rwLock.readLock();
    private static final Lock writeLock = rwLock.writeLock();

    public static void main(String[] args) {
        // Multiple readers
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                readLock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " reading: " + value);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    readLock.unlock();
                }
            }).start();
        }

        // Single writer
        new Thread(() -> {
            writeLock.lock();
            try {
                value = 100;
                System.out.println("Writer updated value to: " + value);
            } finally {
                writeLock.unlock();
            }
        }).start();
    }
}
