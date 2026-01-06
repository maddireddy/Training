package messagePassing.betweenObjects;

public class ObjectB {

    private int variable;

    public ObjectB(int value){
        variable = value;
    }
    public int message() {
        // doing some calculations with variable
        return variable + 1; // returning response to the message
    }
}
