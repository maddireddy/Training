/**
 * TreeSet - Sorted set
 */
package set_examples;
import java.util.*;
public class Example08_TreeSet {
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();

        set.add(50); set.add(10); set.add(30); set.add(20); set.add(40);
        System.out.println("TreeSet (sorted): " + set);

        // First and last
        System.out.println("First: " + set.first());
        System.out.println("Last: " + set.last());

        // Ceiling and floor
        System.out.println("Ceiling of 25: " + set.ceiling(25));
        System.out.println("Floor of 25: " + set.floor(25));

        // Higher and lower
        System.out.println("Higher than 30: " + set.higher(30));
        System.out.println("Lower than 30: " + set.lower(30));

        // Subset
        SortedSet<Integer> subset = set.subSet(20, 50);
        System.out.println("Subset [20, 50): " + subset);
    }
}
