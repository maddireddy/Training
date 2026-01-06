package arraysLearning;

public class ObjectArrayLearning {

    public static void main(String[] args) {

        int[] primitiveArray = new int[5];
        Integer[] objectArray = new Integer[5];

        Object obj1 = primitiveArray; // Allowed, all arrays are objects
        Object[] objArray1 = objectArray; // Allowed, Integer[] is a subtype of Object[]

        // Object[] objArray2 = primitiveArray; // Not allowed, int is a primitive type
    }
}
