/**
 * Volatile Keyword - Ensures visibility
 */
package synchronization;
public class Example09_VolatileKeyword {
    private static volatile boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        Thread writer = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
            flag = true;
            System.out.println("Flag set to true");
        });

        Thread reader = new Thread(() -> {
            while (!flag) {
                // Waiting for flag to become true
            }
            System.out.println("Flag is true!");
        });

        reader.start();
        writer.start();

        reader.join();
        writer.join();
        System.out.println("Completed");
    }
}
