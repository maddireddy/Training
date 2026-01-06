package sealedClassLearning.jdk17.classes;

public sealed class Figure
        // The permits clause has been omitted
        // as its permitted classes have been
        // defined in the same file.
{
}

final class CircleSame extends Figure {
    float radius;
}

non-sealed class SquareSame extends Figure {
    float side;
}

sealed class RectangleSame extends Figure {
    float length, width;
}

final class FilledRectangleSame extends RectangleSame {
    int red, green, blue;
}
