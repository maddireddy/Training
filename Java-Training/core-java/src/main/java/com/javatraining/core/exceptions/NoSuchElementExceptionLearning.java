package exceptionHandling;

import java.util.HashSet;
import java.util.Set;

public class NoSuchElementExceptionLearning {
    public static void main(String[] args) {
        Set hash_Set = new HashSet();  //create an empty hashSet.
        //This throws NoSuchElementException  since hashSet is empty
        hash_Set.iterator().next();
    }
}
