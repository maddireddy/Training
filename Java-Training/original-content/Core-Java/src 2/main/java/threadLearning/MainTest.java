package threadLearning;

// By Extending Thread class -
// By Implementing Runnable Interface - run()

import static java.lang.Thread.sleep;

public class MainTest {

    public static void main(String[] args) {

        System.out.println("Active Threads" + Thread.activeCount());

        RunnableLearning runnableLearning = new RunnableLearning();

        try {
            Thread t1 = new Thread(runnableLearning);
            t1.start();
            t1.setPriority(Thread.MAX_PRIORITY);
            t1.join();

            System.out.println("Active Threads" + Thread.activeCount());

            System.out.println(Thread.currentThread().threadId());

            Thread t2 = new Thread(runnableLearning);
            t2.start();
            t2.setPriority(Thread.NORM_PRIORITY);

            while (!t2.isInterrupted()) {
                System.out.println("not interrupted ");
                sleep(2000);
                //Main thread interrupting child thread
                t2.interrupt();
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
