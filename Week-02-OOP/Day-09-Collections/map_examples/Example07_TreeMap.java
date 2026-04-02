/**
 * TreeMap - Sorted map
 */
package map_examples;
import java.util.*;
public class Example07_TreeMap {
    public static void main(String[] args) {
        TreeMap<String, Integer> map = new TreeMap<>();

        map.put("Charlie", 30);
        map.put("Alice", 25);
        map.put("Bob", 28);
        map.put("David", 32);

        System.out.println("TreeMap (sorted by key): " + map);

        // First and last
        System.out.println("First key: " + map.firstKey());
        System.out.println("Last key: " + map.lastKey());

        // Submap
        SortedMap<String, Integer> subMap = map.subMap("Alice", "Charlie");
        System.out.println("SubMap: " + subMap);

        // Head and tail maps
        SortedMap<String, Integer> headMap = map.headMap("Charlie");
        System.out.println("HeadMap: " + headMap);
    }
}
