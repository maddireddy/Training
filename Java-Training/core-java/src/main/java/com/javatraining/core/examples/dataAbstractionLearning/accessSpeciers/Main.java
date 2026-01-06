

package dataAbstractionLearning.accessSpeciers;

import dataAbstractionLearning.defaultLearning.DefaultLearning;

public class Main {

    public static void main(String[] args) {

        DefaultLearning defaultLearning = new DefaultLearning();
        System.out.println(defaultLearning.a);
        System.out.println(defaultLearning.b);
        defaultLearning.display();
    }
}
