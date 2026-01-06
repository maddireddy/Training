package collectionLearning.set;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetLearning {

    public static void main() {
        int[] count = {14, 43, 10, 55, 30, 22, 40};
        Set<Integer> set = new TreeSet<>();
        try {
            for (int i = 0; i < 6; i++) {
                set.add(count[i]);
            }
            System.out.println(STR."Intial Set=\{set}");

            set.remove(30);
            System.out.println(STR."Values after delete=\{set}");

            System.out.println("Retrieving Values");
            for (Integer integer : set) {
                System.out.println(integer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
