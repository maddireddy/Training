package arraysLearning;

/*
You have an arrays of zeros (0) and (1) keep all zeros on the left hand side and all 1 to right
hand side ?
e.g. Integer array[] =[1,1,0,1,0] -> output : [0,0,1,1,1]
 */

import java.util.Arrays;
import java.util.stream.Collectors;

public class SortArrayElements {

    public static void main(String[] ignoredArgs) {
        Integer[] array = {1, 1, 0, 1, 0};
        ///-> output : [0,0,1,1,1]
        System.out.println(Arrays.stream(array).sorted().collect(Collectors.toList()));
        //second approach
        swapArrayElement(array);

    }

    private static void swapArrayElement(Integer[] arr) {
        int left = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                int temp = arr[i];
                arr[i] = arr[left];
                arr[left] = temp;
                left++;
            }
        }
        System.out.println(Arrays.toString(arr));

    }
}
