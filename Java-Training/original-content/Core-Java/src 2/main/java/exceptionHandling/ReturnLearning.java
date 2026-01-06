package exceptionHandling;

import java.io.IOException;

public class ReturnLearning {

    public static void main(String[] args) throws Exception {
        ReturnLearning returnLearning = new ReturnLearning();
        returnLearning.getStirngName();
        returnLearning.display();
    }

    public String getStirngName() {
        System.out.println("inside");
        return "Siva";
    }

    public void display() throws IOException {
        System.out.println("Inside display");
    }
}
