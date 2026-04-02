/**
 * ArrayList Examples
 * Demonstrates: ArrayList operations, methods, iteration
 */
package list_examples;

import java.util.*;

public class Example01_ArrayList {
    public static void main(String[] args) {
        System.out.println("=== ArrayList Examples ===\n");

        // Create ArrayList
        ArrayList<String> fruits = new ArrayList<>();

        // Add elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Date");
        System.out.println("Fruits: " + fruits);

        // Add at index
        fruits.add(1, "Avocado");
        System.out.println("After insert: " + fruits);

        // Get element
        String first = fruits.get(0);
        System.out.println("\nFirst fruit: " + first);

        // Set element
        fruits.set(0, "Apricot");
        System.out.println("After set: " + fruits);

        // Remove element
        fruits.remove("Banana");
        System.out.println("After remove: " + fruits);

        // Remove by index
        fruits.remove(0);
        System.out.println("After remove(0): " + fruits);

        // Size
        System.out.println("\nSize: " + fruits.size());

        // Contains
        boolean hasCherry = fruits.contains("Cherry");
        System.out.println("Has Cherry: " + hasCherry);

        // Index of
        int index = fruits.indexOf("Cherry");
        System.out.println("Index of Cherry: " + index);

        // Iteration
        System.out.println("\nIteration:");
        for (String fruit : fruits) {
            System.out.println("- " + fruit);
        }

        // forEach with lambda
        System.out.println("\nUsing forEach:");
        fruits.forEach(fruit -> System.out.println("* " + fruit));

        // Sort
        Collections.sort(fruits);
        System.out.println("\nSorted: " + fruits);

        // Clear
        fruits.clear();
        System.out.println("After clear, size: " + fruits.size());
    }
}
