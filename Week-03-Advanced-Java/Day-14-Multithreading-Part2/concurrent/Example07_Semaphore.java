/**
 * Semaphore - Limiting concurrent access
 */
package concurrent;
import java.util.concurrent.*;
public class Example07_Semaphore {
    private static Semaphore semaphore = new Semaphore(2);  // 2 permits

    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            int id = i;
            new Thread(() -> {
                try {
                    System.out.println("Thread " + id + " waiting...");
                    semaphore.acquire();
                    System.out.println("Thread " + id + " acquired permit");
                    Thread.sleep(2000);
                    System.out.println("Thread " + id + " releasing permit");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}
