package threadLearning.threadMethodLearning;

public class ThreadMethodsLearning {

    volatile int k = 30;

    public static void main(String[] args) throws InterruptedException {
        new ThreadMethodsLearning().hello();
    }

    public void hello() {
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                synchronized (ThreadMethodsLearning.class) {
                    k = 40;
                }
                System.out.println("Inside 2nd Thread");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });

        Thread thread2 = new Thread(() -> {
            if (k == 40) {
                System.out.println("Inside 2nd Thread with value" + k);
            }
        });

        thread1.start();
        thread2.start();
    }
}
