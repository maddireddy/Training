/**
 * Day 05: Library Management System
 * Introduction to OOP - Classes and Objects
 *
 * This is a real-world library management system demonstrating:
 * - Classes and Objects
 * - Encapsulation
 * - Object relationships
 * - Real-world business logic
 */

import java.util.Scanner;

public class Library {
    // Arrays to store books and students
    private static final int MAX_BOOKS = 100;
    private static final int MAX_STUDENTS = 50;

    private static Book[] books = new Book[MAX_BOOKS];
    private static Student[] students = new Student[MAX_STUDENTS];

    private static int bookCount = 0;
    private static int studentCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize with sample data
        initializeSampleData();

        boolean exit = false;
        while (!exit) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    addStudent(scanner);
                    break;
                case 3:
                    displayAllBooks();
                    break;
                case 4:
                    displayAllStudents();
                    break;
                case 5:
                    issueBook(scanner);
                    break;
                case 6:
                    returnBook(scanner);
                    break;
                case 7:
                    searchBookByTitle(scanner);
                    break;
                case 8:
                    searchBookByAuthor(scanner);
                    break;
                case 9:
                    displayAvailableBooks();
                    break;
                case 10:
                    displayIssuedBooks();
                    break;
                case 11:
                    exit = true;
                    System.out.println("\n✅ Thank you for using Library Management System!");
                    break;
                default:
                    System.out.println("\n❌ Invalid choice! Please try again.");
            }

            if (!exit) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    /**
     * Initialize with sample data
     */
    private static void initializeSampleData() {
        // Add sample books
        books[0] = new Book("B001", "Clean Code", "Robert C. Martin", "Programming");
        books[1] = new Book("B002", "Java Programming", "Herbert Schildt", "Programming");
        books[2] = new Book("B003", "The Alchemist", "Paulo Coelho", "Fiction");
        books[3] = new Book("B004", "1984", "George Orwell", "Fiction");
        books[5] = new Book("B005", "Sapiens", "Yuval Noah Harari", "History");
        bookCount = 5;

        // Add sample students
        students[0] = new Student("S001", "Alice Johnson", "Computer Science");
        students[1] = new Student("S002", "Bob Smith", "Information Technology");
        students[2] = new Student("S003", "Carol Davis", "Electronics");
        studentCount = 3;

        System.out.println("✅ Library initialized with " + bookCount + " books and " + studentCount + " students.");
    }

    /**
     * Display main menu
     */
    private static void displayMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("        LIBRARY MANAGEMENT SYSTEM - MAIN MENU");
        System.out.println("=".repeat(60));
        System.out.println("1.  Add New Book");
        System.out.println("2.  Add New Student");
        System.out.println("3.  Display All Books");
        System.out.println("4.  Display All Students");
        System.out.println("5.  Issue Book to Student");
        System.out.println("6.  Return Book");
        System.out.println("7.  Search Book by Title");
        System.out.println("8.  Search Book by Author");
        System.out.println("9.  Display Available Books");
        System.out.println("10. Display Issued Books");
        System.out.println("11. Exit");
        System.out.println("=".repeat(60));
    }

    /**
     * Add a new book
     */
    private static void addBook(Scanner scanner) {
        if (bookCount >= MAX_BOOKS) {
            System.out.println("\n❌ Library is full! Cannot add more books.");
            return;
        }

        System.out.println("\n" + "-".repeat(60));
        System.out.println("                   ADD NEW BOOK");
        System.out.println("-".repeat(60));

        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();

        // Check if book ID exists
        if (findBookByIdIndex(bookId) != -1) {
            System.out.println("❌ Book ID already exists!");
            return;
        }

        System.out.print("Enter Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Author: ");
        String author = scanner.nextLine();

        System.out.print("Enter Category: ");
        String category = scanner.nextLine();

        books[bookCount] = new Book(bookId, title, author, category);
        bookCount++;

        System.out.println("\n✅ Book added successfully!");
    }

    /**
     * Add a new student
     */
    private static void addStudent(Scanner scanner) {
        if (studentCount >= MAX_STUDENTS) {
            System.out.println("\n❌ Student limit reached!");
            return;
        }

        System.out.println("\n" + "-".repeat(60));
        System.out.println("                ADD NEW STUDENT");
        System.out.println("-".repeat(60));

        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        // Check if student ID exists
        if (findStudentByIdIndex(studentId) != -1) {
            System.out.println("❌ Student ID already exists!");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Course: ");
        String course = scanner.nextLine();

        students[studentCount] = new Student(studentId, name, course);
        studentCount++;

        System.out.println("\n✅ Student added successfully!");
    }

    /**
     * Display all books
     */
    private static void displayAllBooks() {
        if (bookCount == 0) {
            System.out.println("\n❌ No books in library!");
            return;
        }

        System.out.println("\n" + "=".repeat(100));
        System.out.println("                              ALL BOOKS");
        System.out.println("=".repeat(100));
        System.out.printf("%-10s %-30s %-20s %-15s %-20s%n",
                         "Book ID", "Title", "Author", "Category", "Status");
        System.out.println("-".repeat(100));

        for (int i = 0; i < bookCount; i++) {
            books[i].displayInTable();
        }

        System.out.println("=".repeat(100));
        System.out.println("Total Books: " + bookCount);
    }

    /**
     * Display all students
     */
    private static void displayAllStudents() {
        if (studentCount == 0) {
            System.out.println("\n❌ No students registered!");
            return;
        }

        System.out.println("\n" + "=".repeat(80));
        System.out.println("                        ALL STUDENTS");
        System.out.println("=".repeat(80));
        System.out.printf("%-12s %-25s %-15s %-15s%n",
                         "Student ID", "Name", "Course", "Books Issued");
        System.out.println("-".repeat(80));

        for (int i = 0; i < studentCount; i++) {
            students[i].displayInTable();
        }

        System.out.println("=".repeat(80));
        System.out.println("Total Students: " + studentCount);
    }

    /**
     * Issue book to student
     */
    private static void issueBook(Scanner scanner) {
        System.out.print("\nEnter Student ID: ");
        String studentId = scanner.nextLine();

        int studentIndex = findStudentByIdIndex(studentId);
        if (studentIndex == -1) {
            System.out.println("❌ Student not found!");
            return;
        }

        Student student = students[studentIndex];

        if (!student.canIssueMore()) {
            System.out.println("❌ Student has reached maximum book limit!");
            return;
        }

        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();

        int bookIndex = findBookByIdIndex(bookId);
        if (bookIndex == -1) {
            System.out.println("❌ Book not found!");
            return;
        }

        Book book = books[bookIndex];

        if (book.isIssued()) {
            System.out.println("❌ Book is already issued to " + book.getIssuedTo());
            return;
        }

        // Issue book
        if (student.issueBook(bookId) && book.issueBook(student.getName())) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("              BOOK ISSUED SUCCESSFULLY");
            System.out.println("=".repeat(60));
            System.out.println("Book    : " + book.getTitle());
            System.out.println("Student : " + student.getName());
            System.out.println("Date    : " + java.time.LocalDate.now());
            System.out.println("=".repeat(60));
        }
    }

    /**
     * Return book
     */
    private static void returnBook(Scanner scanner) {
        System.out.print("\nEnter Book ID: ");
        String bookId = scanner.nextLine();

        int bookIndex = findBookByIdIndex(bookId);
        if (bookIndex == -1) {
            System.out.println("❌ Book not found!");
            return;
        }

        Book book = books[bookIndex];

        if (!book.isIssued()) {
            System.out.println("❌ Book was not issued!");
            return;
        }

        String studentName = book.getIssuedTo();

        // Find student and remove book from their list
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getName().equals(studentName)) {
                students[i].returnBook(bookId);
                break;
            }
        }

        book.returnBook();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("             BOOK RETURNED SUCCESSFULLY");
        System.out.println("=".repeat(60));
        System.out.println("Book       : " + book.getTitle());
        System.out.println("Returned By: " + studentName);
        System.out.println("Date       : " + java.time.LocalDate.now());
        System.out.println("=".repeat(60));
    }

    /**
     * Search book by title
     */
    private static void searchBookByTitle(Scanner scanner) {
        System.out.print("\nEnter title to search: ");
        String title = scanner.nextLine().toLowerCase();

        System.out.println("\n" + "=".repeat(100));
        System.out.println("                          SEARCH RESULTS");
        System.out.println("=".repeat(100));

        boolean found = false;
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getTitle().toLowerCase().contains(title)) {
                if (!found) {
                    System.out.printf("%-10s %-30s %-20s %-15s %-20s%n",
                                     "Book ID", "Title", "Author", "Category", "Status");
                    System.out.println("-".repeat(100));
                    found = true;
                }
                books[i].displayInTable();
            }
        }

        if (!found) {
            System.out.println("No books found matching: " + title);
        }
        System.out.println("=".repeat(100));
    }

    /**
     * Search book by author
     */
    private static void searchBookByAuthor(Scanner scanner) {
        System.out.print("\nEnter author name: ");
        String author = scanner.nextLine().toLowerCase();

        System.out.println("\n" + "=".repeat(100));
        System.out.println("                          SEARCH RESULTS");
        System.out.println("=".repeat(100));

        boolean found = false;
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getAuthor().toLowerCase().contains(author)) {
                if (!found) {
                    System.out.printf("%-10s %-30s %-20s %-15s %-20s%n",
                                     "Book ID", "Title", "Author", "Category", "Status");
                    System.out.println("-".repeat(100));
                    found = true;
                }
                books[i].displayInTable();
            }
        }

        if (!found) {
            System.out.println("No books found by author: " + author);
        }
        System.out.println("=".repeat(100));
    }

    /**
     * Display available books
     */
    private static void displayAvailableBooks() {
        System.out.println("\n" + "=".repeat(100));
        System.out.println("                         AVAILABLE BOOKS");
        System.out.println("=".repeat(100));

        boolean found = false;
        for (int i = 0; i < bookCount; i++) {
            if (!books[i].isIssued()) {
                if (!found) {
                    System.out.printf("%-10s %-30s %-20s %-15s%n",
                                     "Book ID", "Title", "Author", "Category");
                    System.out.println("-".repeat(100));
                    found = true;
                }
                System.out.printf("%-10s %-30s %-20s %-15s%n",
                                 books[i].getBookId(), books[i].getTitle(),
                                 books[i].getAuthor(), books[i].getCategory());
            }
        }

        if (!found) {
            System.out.println("No books available at the moment.");
        }
        System.out.println("=".repeat(100));
    }

    /**
     * Display issued books
     */
    private static void displayIssuedBooks() {
        System.out.println("\n" + "=".repeat(100));
        System.out.println("                          ISSUED BOOKS");
        System.out.println("=".repeat(100));

        boolean found = false;
        for (int i = 0; i < bookCount; i++) {
            if (books[i].isIssued()) {
                if (!found) {
                    System.out.printf("%-10s %-30s %-20s %-25s%n",
                                     "Book ID", "Title", "Author", "Issued To");
                    System.out.println("-".repeat(100));
                    found = true;
                }
                System.out.printf("%-10s %-30s %-20s %-25s%n",
                                 books[i].getBookId(), books[i].getTitle(),
                                 books[i].getAuthor(), books[i].getIssuedTo());
            }
        }

        if (!found) {
            System.out.println("No books are currently issued.");
        }
        System.out.println("=".repeat(100));
    }

    // Utility methods
    private static int findBookByIdIndex(String bookId) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getBookId().equals(bookId)) {
                return i;
            }
        }
        return -1;
    }

    private static int findStudentByIdIndex(String studentId) {
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getStudentId().equals(studentId)) {
                return i;
            }
        }
        return -1;
    }
}
