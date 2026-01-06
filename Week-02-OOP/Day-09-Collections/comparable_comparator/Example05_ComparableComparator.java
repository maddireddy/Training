/**
 * Comparable and Comparator - Demonstrates: Sorting custom objects
 */
package comparable_comparator;

import java.util.*;

public class Example05_ComparableComparator {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Alice", 85),
            new Student("Bob", 92),
            new Student("Charlie", 78),
            new Student("David", 92)
        );

        // Natural ordering (Comparable)
        Collections.sort(students);
        System.out.println("Sorted by name:");
        students.forEach(System.out::println);

        // Custom Comparator - by score
        Collections.sort(students, new ScoreComparator());
        System.out.println("\nSorted by score:");
        students.forEach(System.out::println);

        // Lambda Comparator
        students.sort((s1, s2) -> Integer.compare(s2.score, s1.score));
        System.out.println("\nSorted by score (descending):");
        students.forEach(System.out::println);

        // Comparator.comparing
        students.sort(Comparator.comparing(Student::getName));
        System.out.println("\nUsing Comparator.comparing:");
        students.forEach(System.out::println);
    }
}

class Student implements Comparable<Student> {
    String name;
    int score;

    Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(Student other) {
        return this.name.compareTo(other.name);
    }

    public String getName() { return name; }
    public int getScore() { return score; }

    @Override
    public String toString() {
        return name + ": " + score;
    }
}

class ScoreComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return Integer.compare(s1.score, s2.score);
    }
}
