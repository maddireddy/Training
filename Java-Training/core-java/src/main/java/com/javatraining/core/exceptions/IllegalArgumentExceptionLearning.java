package exceptionHandling;

/*
IllegalArgumentException is thrown when illegal or invalid arguments are passed to the method.
 */

public class IllegalArgumentExceptionLearning {
    public static void main(String[] args) {
        setMarks(45);
        setMarks(101);
    }

    public static void setMarks(int marks) {
        if (marks < 0 || marks > 100)  //throw exception if marks are not in range
            throw new IllegalArgumentException(Integer.toString(marks));
        else
            System.out.println("Marks Entered: " + marks);
    }
}
