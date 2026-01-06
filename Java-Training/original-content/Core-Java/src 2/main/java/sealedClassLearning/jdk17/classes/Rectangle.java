package sealedClassLearning.jdk17.classes;

public sealed class Rectangle extends Shape permits FilledRectangle {
    public double length, width;
}
