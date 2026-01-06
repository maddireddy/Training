package recordClasses.jdk17.rules.rule3;

//Try adding abstract before the record keyword

public record Transaction(String id, double amount) {

    // This won't compile - attempting to change a final field:
    public void changeAmount(double newAmount) {
        //amount = newAmount;
    }


}


