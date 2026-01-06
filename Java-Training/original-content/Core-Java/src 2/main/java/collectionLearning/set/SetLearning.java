package collectionLearning.set;

import java.util.HashSet;
import java.util.Set;

public class SetLearning {

    public static void main(String args[]) {
        int count[] = {34, 22, 10, 60, 30, 22};
        Set<Integer> set = new HashSet<>();
        try {
            for (int i = 0; i < 5; i++) {
                set.add(count[i]);
            }
            System.out.println(STR."Initial Set=\{set}");

            set.remove(30);
            System.out.println(STR."Values after delete=\{set}");

            System.out.println("Retrieving Values");
            for (Integer integer : set) {
                System.out.println(integer);
            }

        } catch (Exception _exception) {
            _exception.printStackTrace();
        }
    }
}
