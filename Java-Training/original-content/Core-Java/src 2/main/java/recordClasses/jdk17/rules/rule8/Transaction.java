package recordClasses.jdk17.rules.rule8;

import org.jetbrains.annotations.NotNull;

public record Transaction(
        @NotNull String id,
        @Positive double amount) {
    // ...
}