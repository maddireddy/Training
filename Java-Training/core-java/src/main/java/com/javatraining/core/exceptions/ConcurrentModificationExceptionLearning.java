package exceptionHandling;

/*
ConcurrentModificationException is usually thrown by Collection classes.
This exception is thrown when the objects try to modify a resource concurrently.

For example, a thread cannot modify a collection when another thread is accessing it. If we allow two threads,
then those two will simultaneously access the collection, and there will be inconsistencies.
*/

import java.util.ArrayList;
import java.util.Iterator;

public class ConcurrentModificationExceptionLearning {
    public static void main(String[] args) {
        ArrayList<Integer> A_list = new ArrayList<>();
        //add elements to the ArrayList
        A_list.add(10);
        A_list.add(20);
        A_list.add(30);
        Iterator<Integer> it = A_list.iterator();
        while (it.hasNext()) {
            Integer value = it.next();
            System.out.println("ArrayList Value:" + value);
            if (value.equals(30))
                A_list.remove(value);
        }
    }
}
