package messagePassing.betweenObjects;

public class ObjectA {

    public int sendMessageToB() {
        ObjectB objectB = new ObjectB(5);
        int result = objectB.message(); // ObjectA is sending a message to ObjectB
        return result;
    }
}
