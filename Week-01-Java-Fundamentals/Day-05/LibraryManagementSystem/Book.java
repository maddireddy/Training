/**
 * Book class - Represents a book in the library
 */
public class Book {
    // Attributes
    private String bookId;
    private String title;
    private String author;
    private String category;
    private boolean isIssued;
    private String issuedTo;

    // Constructor
    public Book(String bookId, String title, String author, String category) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isIssued = false;
        this.issuedTo = null;
    }

    // Issue book to student
    public boolean issueBook(String studentName) {
        if (isIssued) {
            System.out.println("❌ Book is already issued to " + issuedTo);
            return false;
        }

        this.isIssued = true;
        this.issuedTo = studentName;
        System.out.println("✅ Book issued successfully to " + studentName);
        return true;
    }

    // Return book
    public boolean returnBook() {
        if (!isIssued) {
            System.out.println("❌ Book was not issued!");
            return false;
        }

        System.out.println("✅ Book returned successfully by " + issuedTo);
        this.isIssued = false;
        this.issuedTo = null;
        return true;
    }

    // Display book information
    public void displayBookInfo() {
        System.out.println("\n" + "-".repeat(50));
        System.out.println("Book ID   : " + bookId);
        System.out.println("Title     : " + title);
        System.out.println("Author    : " + author);
        System.out.println("Category  : " + category);
        System.out.println("Status    : " + (isIssued ? "Issued to " + issuedTo : "Available"));
        System.out.println("-".repeat(50));
    }

    // Display in table format
    public void displayInTable() {
        String status = isIssued ? "Issued (" + issuedTo + ")" : "Available";
        System.out.printf("%-10s %-30s %-20s %-15s %-20s%n",
                         bookId, title, author, category, status);
    }

    // Getters
    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public String getIssuedTo() {
        return issuedTo;
    }
}
