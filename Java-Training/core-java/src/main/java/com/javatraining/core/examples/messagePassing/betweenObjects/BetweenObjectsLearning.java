package messagePassing.betweenObjects;

public class BetweenObjectsLearning {

    public static void main(String[] args) {
        ObjectA objectA = new ObjectA();
        int result = objectA.sendMessageToB();
        System.out.println("ObjectB's response: " + result);
    }
}
