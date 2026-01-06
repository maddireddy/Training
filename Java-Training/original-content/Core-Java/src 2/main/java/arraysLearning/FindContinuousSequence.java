package arraysLearning;

/*
Find the continuous sequences of numbers in given input . Provide all possible test?
e.g. input 1 2 3 5 9 a 6 7 8 4 @ -5 -7 -3 -2 -1

output
1 2 3
6 7 8
-3 -2 -1
 */

import java.util.Arrays;

public class FindContinuousSequence {

    public static void main(String[] ignoredArgs) {

        //find the continuous sequence number
        String[] inputArr = {"1", "2", "3", "5", "9", "a", "6", "7", "8", "4", "@", "-5", "-7", "-3", "-2", "-1"};

        System.out.println(STR."Original Array Size : \{inputArr.length}");
        //Step1 - Identify Numbers

        int[] array = Arrays.stream(inputArr).filter(in -> {
            boolean out = true;
            try {
                Integer.valueOf(in);
            } catch (Exception exception) {
                out = false;
            }
            return out;
        }).mapToInt(Integer::valueOf).toArray();

        System.out.println(STR."Number array >> \{Arrays.stream(array).count()}");

        //Step2 - Identify If they are in Sequence

        for (int i = 0; i < array.length; i++) {
            StringBuilder result = new StringBuilder();
            for (int j = i + 1; j < array.length; j++) {
                int next = array[i] + 1;

                if (next == array[j]) {
                    if (result.toString().isEmpty())
                        result.append(STR."\{array[i]}::\{array[j]}");
                    else
                        result.append(STR."::\{array[j]}");
                    i = j;
                } else {
                    break;
                }
            }
            if (!result.toString().isEmpty())
                System.out.println(result);
        }


    }
}
