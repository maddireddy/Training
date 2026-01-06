package dataBinding.dynamicBinding;

/*
In Dynamic binding compiler doesn’t decide the method to be called.
Overriding is a perfect example of dynamic binding.
In overriding both parent and child classes have the same method.

Methods are not static in this code.
During compilation, the compiler has no idea as to which print has to
be called since the compiler goes only by referencing variable not by the type of object,
and therefore the binding would be delayed to runtime and therefore the corresponding
version of the print will be called based on type on an object.
 */

public class DynamicBindingLearning {

    // Method inside main class
    public static void main(String[] args) {

        // Creating object of inner class 1
        // with reference to constructor of super class
        superclass A = new superclass();

        // Creating object of inner class 1
        // with reference to constructor of sub class
        superclass B = new subclass();

        // Calling print() method over above objects
        A.print();
        B.print();
    }

    // Static nested inner class
    // Class 1
    public static class superclass {

        // Method of inner class 1
        void print() {

            // Print statement
            System.out.println(
                    "print in superclass is called");
        }
    }

    // Static nested inner class
    // Class 2
    public static class subclass extends superclass {

        // Method of inner class 2
        @Override
        void print() {

            // Print statement
            System.out.println(
                    "print in subclass is called");
        }
    }
}
