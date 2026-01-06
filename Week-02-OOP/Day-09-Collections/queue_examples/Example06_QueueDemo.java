/**
 * Queue Examples - FIFO operations
 */
package queue_examples;
import java.util.*;
public class Example06_QueueDemo {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();

        // Offer - add to queue
        queue.offer("First");
        queue.offer("Second");
        queue.offer("Third");
        System.out.println("Queue: " + queue);

        // Peek - view front without removing
        String front = queue.peek();
        System.out.println("Front: " + front);
        System.out.println("After peek: " + queue);

        // Poll - remove and return front
        String removed = queue.poll();
        System.out.println("Polled: " + removed);
        System.out.println("After poll: " + queue);

        // PriorityQueue
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(5); pq.offer(1); pq.offer(3);
        System.out.println("\nPriorityQueue: " + pq);

        while (!pq.isEmpty()) {
            System.out.println("Poll: " + pq.poll());
        }
    }
}
