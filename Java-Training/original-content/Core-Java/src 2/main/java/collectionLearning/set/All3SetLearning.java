package collectionLearning.set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class All3SetLearning {

    public static void main(String[] args) {
        LinkedHashSet<String> linkedSet = new LinkedHashSet<>();
        TreeSet<String> treeSet = new TreeSet<>();
        HashSet<String> hashSet = new HashSet<>();

        for (String str : Arrays.asList("Geek2", "Geek1", "Geek3")) {
            linkedSet.add(str);
            treeSet.add(str);
            hashSet.add(str);
        }

        System.out.println(STR."LinkedHashSet: \{linkedSet}"); // Maintains insertion order
        System.out.println(STR."TreeSet: \{treeSet}"); // Sorted according to natural order
        System.out.println(STR."HashSet: \{hashSet}"); // No specific order
    }
}
