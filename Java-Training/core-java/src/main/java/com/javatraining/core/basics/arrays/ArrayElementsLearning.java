package arraysLearning;

import java.util.Arrays;

public class ArrayElementsLearning {

    public static void main(String[] args) {

        int[] learningArray = new int[]{1, 2, 3, 4};

        System.out.println("Get nth  element of an array");
        System.out.println(STR."The first element of an array is \{learningArray[0]}");

        System.out.println("Forward for loop");
        for (int i = 0; i < learningArray.length; i++) {
            System.out.println(STR."learningArray element at index \{i} is \{learningArray[i]}");
        }

        System.out.println("Backward (reverse) for loop");
        for (int i = learningArray.length - 1; i >= 0; i--) {
            System.out.println(STR."learningArray element at index \{i} is \{learningArray[i]}");
        }

        System.out.println("For each loop");
        for (int element : learningArray) {
            System.out.println(STR."learningArray elements are \{element}");
        }

        System.out.println("While forward loop");
        int index = 0;

        while (index < learningArray.length) {
            System.out.println(STR."learningArray element at index \{index} is \{learningArray[index++]}");
        }

        System.out.println("While backward loop");
        int learningArrayLastIndex = learningArray.length - 1;

        while (learningArrayLastIndex >= 0) {
            System.out.println(
                    STR."learningArray element at index \{learningArrayLastIndex} is \{learningArray[learningArrayLastIndex--]}");
        }


        System.out.println("Do While forward loop");
        int ind = 0;
        do {
            System.out.println(STR."learningArray element at index \{ind} is \{learningArray[ind++]}");
        } while (ind < learningArray.length);

        System.out.println("Do While backward loop");
        int learningArrayLastInd = learningArray.length - 1;

        do {
            System.out.println(
                    STR."tutorialArray element at index \{learningArrayLastInd} is \{learningArray[learningArrayLastInd--]}");
        } while (learningArrayLastInd >= 0);


        System.out.println("Get arrays elements using Stream ");
        Arrays.stream(learningArray).forEach(System.out::println);

        System.out.println("Get multidimensional array elements using for loop");

        int[][] multidimensionalArray = new int[][]{
                {1, 2, 3, 4, 5},
                {1, 2, 3, 4, 5},
                {1, 2, 3, 4, 5}};

        for (int i = 0; i < multidimensionalArray.length; i++) {
            for (int j = 0; j < multidimensionalArray[i].length; j++) {
                System.out.println(STR."Element at index  = \{i}-\{j} = \{multidimensionalArray[i][j]}");
            }
        }

        System.out.println("Get multidimensional array elements using for each loop");
        for (int[] firstDimension : multidimensionalArray) {
            for (int secondDimension : firstDimension) {
                System.out.println(secondDimension);
            }
        }

    }

}
