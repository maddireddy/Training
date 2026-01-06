/**
 * Thread Interrupt - Demonstrates: Interrupting threads
 */
package basics;

public class Example08_ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("Working...");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted!");
            }
        });

        worker.start();
        Thread.sleep(3000);
        worker.interrupt();  // Interrupt the thread
        worker.join();
        System.out.println("Main thread completed");
    }
}
