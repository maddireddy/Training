/**
 * Wait and Notify
 * Demonstrates: Inter-thread communication, wait(), notify(), notifyAll()
 */
package communication;

public class Example05_WaitNotify {
    public static void main(String[] args) {
        System.out.println("=== Wait and Notify ===\n");

        Message message = new Message();

        Thread producer = new Thread(() -> {
            try {
                message.produce("Hello from Producer!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                message.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        consumer.start();
        try { Thread.sleep(100); } catch (InterruptedException e) {}
        producer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Message {
    private String content;
    private boolean hasMessage = false;

    public synchronized void produce(String msg) throws InterruptedException {
        while (hasMessage) {
            wait();  // Wait until consumed
        }

        this.content = msg;
        hasMessage = true;
        System.out.println("Produced: " + msg);
        notify();  // Notify consumer
    }

    public synchronized void consume() throws InterruptedException {
        while (!hasMessage) {
            wait();  // Wait until produced
        }

        System.out.println("Consumed: " + content);
        hasMessage = false;
        notify();  // Notify producer
    }
}
