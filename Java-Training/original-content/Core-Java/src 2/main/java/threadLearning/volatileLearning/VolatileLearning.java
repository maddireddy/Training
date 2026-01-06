package threadLearning.volatileLearning;

public class VolatileLearning {

    private volatile boolean flag = false;

    public static void main(String[] args) {
        VolatileLearning volatileLearning = new VolatileLearning();
        volatileLearning.startThreads();
    }

    private void startThreads() {
        Thread writerThread = new Thread(() -> {
            try {
                Thread.sleep(1000);
                flag = true;  // Updating the volatile variable
                System.out.println("Flag is set to true.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread readerThread = new Thread(() -> {
            while (!flag) {
                // Reading the volatile variable
            }
            System.out.println("Flag is now true.");
        });

        writerThread.start();
        readerThread.start();
    }
}
