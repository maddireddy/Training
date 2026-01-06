package threadLearning;

public class RunnableLearning implements Runnable {

    // New
    @Override
    public void run() { // Running

        for (int i = 0; i < 10; i++) {
            System.out.println("Inside for Loop");
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    } // terminated
}
