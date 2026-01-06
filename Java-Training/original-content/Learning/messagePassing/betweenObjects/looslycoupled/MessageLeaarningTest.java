package messagePassing.betweenObjects.looslycoupled;

public class MessageLeaarningTest {

    public static void main(String[] args) {
        ObjectB objectB = new ObjectB(5);
        ObjectA objectA = new ObjectA();
        int result = objectA.sendMessageTo(objectB);
        System.out.println("ObjectB's response: " + result);
    }
}
