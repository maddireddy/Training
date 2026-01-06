package recordClasses.jdk17.rules.rule4;

public record Point(int x, int y) {
    //private double distanceFromOrigin; // Won't compile
}
