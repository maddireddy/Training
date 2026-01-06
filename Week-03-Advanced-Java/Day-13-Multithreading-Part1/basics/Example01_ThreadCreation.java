/**
 * Thread Creation Methods
 * Demonstrates: Thread class, Runnable interface, lambda threads
 */
package basics;

public class Example01_ThreadCreation {
    public static void main(String[] args) {
        System.out.println("=== Thread Creation Methods ===\n");
        System.out.println("Main thread: " + Thread.currentThread().getName());

        // Method 1: Extending Thread class
        System.out.println("\n=== Method 1: Extending Thread ===");
        MyThread thread1 = new MyThread("Worker-1");
        thread1.start();

        // Method 2: Implementing Runnable
        System.out.println("\n=== Method 2: Implementing Runnable ===");
        MyRunnable runnable = new MyRunnable("Worker-2");
        Thread thread2 = new Thread(runnable);
        thread2.start();

        // Method 3: Anonymous Runnable
        System.out.println("\n=== Method 3: Anonymous Runnable ===");
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous thread: " + Thread.currentThread().getName());
            }
        });
        thread3.start();

        // Method 4: Lambda expression (Java 8+)
        System.out.println("\n=== Method 4: Lambda Expression ===");
        Thread thread4 = new Thread(() -> {
            System.out.println("Lambda thread: " + Thread.currentThread().getName());
            for (int i = 0; i < 3; i++) {
                System.out.println("Lambda count: " + i);
            }
        });
        thread4.start();

        // Method 5: Method reference
        System.out.println("\n=== Method 5: Method Reference ===");
        Thread thread5 = new Thread(Example01_ThreadCreation::printMessage);
        thread5.start();

        // Wait for threads to complete
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nAll threads completed!");
    }

    static void printMessage() {
        System.out.println("Method reference thread: " + Thread.currentThread().getName());
    }
}

class MyThread extends Thread {
    private String name;

    public MyThread(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " started");
        for (int i = 0; i < 3; i++) {
            System.out.println(name + ": " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name + " finished");
    }
}

class MyRunnable implements Runnable {
    private String name;

    public MyRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " started");
        for (int i = 0; i < 3; i++) {
            System.out.println(name + ": " + i);
        }
        System.out.println(name + " finished");
    }
}
