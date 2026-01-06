package collectionLearning.set;

import java.util.LinkedHashSet;

public class LinkedHashSetLearning {

    public static void main() {
        int[] count = {34, 22, 10, 60, 30, 22, 25};
        LinkedHashSet<Integer> set = new LinkedHashSet<>();

        try {
            for (int i = 0; i < 7; i++) {
                set.add(count[i]);
            }
            System.out.println(STR."Initial Set=\{set}");

            set.remove(30);
            System.out.println(STR."Values after delete=\{set}");

            System.out.println("Retrieving Values");

            for (Integer integer : set) {
                System.out.println(integer);
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
