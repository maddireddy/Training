package messagePassing.betweenObjects.looslycoupled;

public class ObjectA {

    public int sendMessageTo(MessageSender sender) {
        int result = sender.message(); // ObjectA is sending a message to a generic sender
        return result;
    }
}
