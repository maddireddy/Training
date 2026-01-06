package recordClasses.jdk17.rules.rule5;

public record Coordinates(double latitude, double longitude) {
    // This works, matches the auto-generated accessor
    public double latitude() {
        return latitude;
    }

    // This won't compile - type mismatch
    //public String latitude() { return Double.toString(latitude); }
}
