/**
 * LinkedList Examples - Demonstrates: LinkedList operations, Deque interface
 */
package list_examples;

import java.util.*;

public class Example04_LinkedList {
    public static void main(String[] args) {
        System.out.println("=== LinkedList Examples ===\n");
        LinkedList<String> list = new LinkedList<>();

        // Add elements
        list.add("A"); list.add("B"); list.add("C");
        System.out.println("List: " + list);

        // Add first/last
        list.addFirst("Start");
        list.addLast("End");
        System.out.println("After addFirst/Last: " + list);

        // Get first/last
        System.out.println("\nFirst: " + list.getFirst());
        System.out.println("Last: " + list.getLast());

        // Remove first/last
        list.removeFirst();
        list.removeLast();
        System.out.println("After removeFirst/Last: " + list);

        // Use as Queue
        list.offer("X");  // Add to end
        String polled = list.poll();  // Remove from front
        System.out.println("\nPolled: " + polled);
        System.out.println("After poll: " + list);

        // Use as Stack
        list.push("Top");  // Add to front
        String popped = list.pop();  // Remove from front
        System.out.println("\nPopped: " + popped);
    }
}
