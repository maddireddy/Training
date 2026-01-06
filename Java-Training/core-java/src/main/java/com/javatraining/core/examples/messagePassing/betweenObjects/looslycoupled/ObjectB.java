package messagePassing.betweenObjects.looslycoupled;

public class ObjectB implements MessageSender{

    private int variable;

    public ObjectB(int value){
        variable = value;
    }

    @Override
    public int message() {
        // doing some calculations with variable
        return variable + 1; // returning response to the message
    }

}
