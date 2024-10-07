/* Lecel Lennox
Software Development - CEN 3024C
10/06/2024
 */
import java.time.LocalDate;

//Book class contains the properties of a book
public class Book {
    private String barcode;
    private String title;
    private String author;
    private boolean checkedOut;
    private LocalDate dueDate;


    public Book(String barcode, String title, String author) {
        this.barcode = barcode;
        this.title = title.trim();
        this.author = author;
        this.checkedOut = false; // by default the book is not checked out
        this.dueDate = null; // no due date unless checked out
    }

    public String getBarcode() {
        return barcode;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean checkedOut() {
        return checkedOut;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }

    public void checkOut() {
        if (!checkedOut) {
            checkedOut = true;
            dueDate = LocalDate.now().plusWeeks(4); //due date set 4 weeks from current date
        }
    }

    public void checkIn() {
        if (checkedOut) {
            checkedOut = false;
            dueDate = null;//due date is cleared on check in
        }
    }

    // toString prints the format details of the books
    @Override
    public String toString() {
        return "Book [Barcode: " + barcode + ", Title: " + title + ", Author: " + author +
                ", Status: " + (checkedOut ? "Checked Out" : "Available") +
                ", Due Date: " + (dueDate != null ? dueDate : "N/A") + "]";
    }
}