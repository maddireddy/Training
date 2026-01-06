package recordClasses.jdk17.rules.rule9;

import java.io.Serializable;

public record Person(String name, int age) implements Serializable {
}
