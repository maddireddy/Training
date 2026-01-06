package exceptionHandling;

import java.util.ArrayList;
import java.util.List;

/*
If an application has too many finalizers, then the class objects having the Finalize method are not reclaimed
by garbage collector immediately but are queued up for finalization at a later time. Sometimes, the finalization
cannot keep up with time, and heap memory is filled up resulting in OutOfMemoryError.

Another reason for OutOfMemoryError is that the heap size specified may be insufficient for the application.

The following code demonstrates the OutOfMemoryError that can occur because of a huge data size declared for an array.
*/


public class OutOfMemoryErrorLearning {

    static List<String> list = new ArrayList<>();

    public static void main(String args[]) throws Exception {
        Integer[] array = new Integer[100000 * 100000];
    }
}
