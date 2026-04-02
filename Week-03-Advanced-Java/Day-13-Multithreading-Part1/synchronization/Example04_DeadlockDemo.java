/**
 * Deadlock Demonstration
 * Demonstrates: Deadlock scenario and prevention
 */
package synchronization;

public class Example04_DeadlockDemo {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        System.out.println("=== Deadlock Demo ===\n");

        // Uncomment to see deadlock:
        // demonstrateDeadlock();

        // Deadlock prevention
        demonstrateDeadlockPrevention();
    }

    static void demonstrateDeadlock() {
        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1: Locked lock1");
                sleep(100);
                synchronized (lock2) {
                    System.out.println("Thread 1: Locked lock2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread 2: Locked lock2");
                sleep(100);
                synchronized (lock1) {
                    System.out.println("Thread 2: Locked lock1");
                }
            }
        });

        t1.start();
        t2.start();
    }

    static void demonstrateDeadlockPrevention() {
        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1: Locked lock1");
                sleep(100);
                synchronized (lock2) {
                    System.out.println("Thread 1: Locked lock2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock1) {  // Same order as t1
                System.out.println("Thread 2: Locked lock1");
                sleep(100);
                synchronized (lock2) {
                    System.out.println("Thread 2: Locked lock2");
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("No deadlock - both threads completed!");
    }

    static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
