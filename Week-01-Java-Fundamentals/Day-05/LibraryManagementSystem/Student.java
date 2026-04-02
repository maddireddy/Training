/**
 * Student class - Represents a library member
 */
public class Student {
    // Attributes
    private String studentId;
    private String name;
    private String course;
    private int booksIssued;
    private static final int MAX_BOOKS_ALLOWED = 3;
    private String[] issuedBooks; // Track book IDs

    // Constructor
    public Student(String studentId, String name, String course) {
        this.studentId = studentId;
        this.name = name;
        this.course = course;
        this.booksIssued = 0;
        this.issuedBooks = new String[MAX_BOOKS_ALLOWED];
    }

    // Check if student can issue more books
    public boolean canIssueMore() {
        return booksIssued < MAX_BOOKS_ALLOWED;
    }

    // Issue a book
    public boolean issueBook(String bookId) {
        if (!canIssueMore()) {
            System.out.println("❌ Maximum book limit reached! Cannot issue more books.");
            return false;
        }

        // Check if already has this book
        for (int i = 0; i < booksIssued; i++) {
            if (issuedBooks[i].equals(bookId)) {
                System.out.println("❌ You already have this book!");
                return false;
            }
        }

        issuedBooks[booksIssued] = bookId;
        booksIssued++;
        return true;
    }

    // Return a book
    public boolean returnBook(String bookId) {
        for (int i = 0; i < booksIssued; i++) {
            if (issuedBooks[i].equals(bookId)) {
                // Shift remaining books
                for (int j = i; j < booksIssued - 1; j++) {
                    issuedBooks[j] = issuedBooks[j + 1];
                }
                booksIssued--;
                return true;
            }
        }
        System.out.println("❌ Book not found in your issued list!");
        return false;
    }

    // Display student information
    public void displayStudentInfo() {
        System.out.println("\n" + "-".repeat(50));
        System.out.println("Student ID      : " + studentId);
        System.out.println("Name            : " + name);
        System.out.println("Course          : " + course);
        System.out.println("Books Issued    : " + booksIssued + " / " + MAX_BOOKS_ALLOWED);

        if (booksIssued > 0) {
            System.out.println("Issued Book IDs:");
            for (int i = 0; i < booksIssued; i++) {
                System.out.println("  " + (i + 1) + ". " + issuedBooks[i]);
            }
        }
        System.out.println("-".repeat(50));
    }

    // Display in table format
    public void displayInTable() {
        System.out.printf("%-12s %-25s %-15s %d / %d%n",
                         studentId, name, course, booksIssued, MAX_BOOKS_ALLOWED);
    }

    // Getters
    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public int getBooksIssued() {
        return booksIssued;
    }

    public static int getMaxBooksAllowed() {
        return MAX_BOOKS_ALLOWED;
    }
}
