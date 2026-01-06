package recordClasses.jdk17.rules.rule7;

// Implementing Interfaces:
public record Book(String title, String author) implements Comparable<Book> {
    @Override
    public int compareTo(Book other) {
        return 1;
    }
}
