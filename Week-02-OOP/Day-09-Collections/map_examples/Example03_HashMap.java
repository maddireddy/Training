/**
 * HashMap Examples
 * Demonstrates: HashMap operations, key-value pairs
 */
package map_examples;

import java.util.*;

public class Example03_HashMap {
    public static void main(String[] args) {
        System.out.println("=== HashMap Examples ===\n");

        // Create HashMap
        HashMap<String, Integer> scores = new HashMap<>();

        // Put key-value pairs
        scores.put("Alice", 95);
        scores.put("Bob", 87);
        scores.put("Charlie", 92);
        scores.put("David", 88);

        System.out.println("Scores: " + scores);

        // Get value
        int aliceScore = scores.get("Alice");
        System.out.println("\nAlice's score: " + aliceScore);

        // Get or default
        int eveScore = scores.getOrDefault("Eve", 0);
        System.out.println("Eve's score (default): " + eveScore);

        // Contains key
        boolean hasBob = scores.containsKey("Bob");
        System.out.println("\nHas Bob: " + hasBob);

        // Contains value
        boolean has95 = scores.containsValue(95);
        System.out.println("Has score 95: " + has95);

        // Update value
        scores.put("Alice", 98);  // Updates existing
        System.out.println("\nUpdated Alice: " + scores.get("Alice"));

        // putIfAbsent
        scores.putIfAbsent("Eve", 90);
        scores.putIfAbsent("Alice", 100);  // Won't update
        System.out.println("After putIfAbsent: " + scores);

        // Remove
        scores.remove("David");
        System.out.println("\nAfter remove: " + scores);

        // Size
        System.out.println("Size: " + scores.size());

        // Iterate keys
        System.out.println("\nKeys:");
        for (String name : scores.keySet()) {
            System.out.println("- " + name);
        }

        // Iterate values
        System.out.println("\nValues:");
        for (Integer score : scores.values()) {
            System.out.println("- " + score);
        }

        // Iterate entries
        System.out.println("\nEntries:");
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        // forEach
        System.out.println("\nUsing forEach:");
        scores.forEach((name, score) ->
            System.out.println(name + " scored " + score));
    }
}
