/**
 * HashSet Examples
 * Demonstrates: HashSet operations, uniqueness, no duplicates
 */
package set_examples;

import java.util.*;

public class Example02_HashSet {
    public static void main(String[] args) {
        System.out.println("=== HashSet Examples ===\n");

        // Create HashSet
        HashSet<Integer> numbers = new HashSet<>();

        // Add elements
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(20);  // Duplicate - will not be added
        numbers.add(40);

        System.out.println("Numbers: " + numbers);
        System.out.println("Size: " + numbers.size());

        // Contains
        boolean has20 = numbers.contains(20);
        System.out.println("\nContains 20: " + has20);

        // Remove
        numbers.remove(20);
        System.out.println("After removing 20: " + numbers);

        // Iteration (no guaranteed order)
        System.out.println("\nIterating:");
        for (Integer num : numbers) {
            System.out.println(num);
        }

        // Set operations
        HashSet<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        HashSet<Integer> set2 = new HashSet<>(Arrays.asList(4, 5, 6, 7, 8));

        // Union
        HashSet<Integer> union = new HashSet<>(set1);
        union.addAll(set2);
        System.out.println("\nUnion: " + union);

        // Intersection
        HashSet<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("Intersection: " + intersection);

        // Difference
        HashSet<Integer> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        System.out.println("Difference: " + difference);
    }
}
