/**
 * Thread Lifecycle
 * Demonstrates: Thread states, lifecycle transitions
 */
package basics;

public class Example02_ThreadLifecycle {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Thread Lifecycle ===\n");

        // Create thread (NEW state)
        Thread thread = new Thread(() -> {
            System.out.println("Thread running");

            // RUNNABLE state
            try {
                Thread.sleep(1000);  // TIMED_WAITING
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Thread finishing");
        });

        System.out.println("1. NEW: " + thread.getState());

        // Start thread (RUNNABLE state)
        thread.start();
        Thread.sleep(100);
        System.out.println("2. RUNNABLE: " + thread.getState());

        // Thread is sleeping (TIMED_WAITING)
        Thread.sleep(200);
        System.out.println("3. TIMED_WAITING: " + thread.getState());

        // Wait for completion (TERMINATED)
        thread.join();
        System.out.println("4. TERMINATED: " + thread.getState());

        // Demonstrate BLOCKED state
        demonstrateBlockedState();

        // Demonstrate WAITING state
        demonstrateWaitingState();
    }

    static void demonstrateBlockedState() throws InterruptedException {
        System.out.println("\n=== BLOCKED State ===");

        Object lock = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 1 acquired lock");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 2 acquired lock");
            }
        });

        t1.start();
        Thread.sleep(100);
        t2.start();
        Thread.sleep(100);

        System.out.println("Thread 2 state (waiting for lock): " + t2.getState());

        t1.join();
        t2.join();
    }

    static void demonstrateWaitingState() throws InterruptedException {
        System.out.println("\n=== WAITING State ===");

        Thread t1 = new Thread(() -> {
            try {
                Thread.currentThread().join();  // Wait indefinitely
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        Thread.sleep(100);
        System.out.println("Thread state (waiting): " + t1.getState());

        // Interrupt to stop waiting
        t1.interrupt();
        t1.join();
    }
}
