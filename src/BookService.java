import java.time.LocalDate;

//Book Service Class serves to provide check in and check out method
public class BookService {
    // Check out method
    public void checkOutBook(Book book) {
        if (!book.checkedOut()) {
            book.checkOut();
            System.out.println("Book titled '" + book.getTitle() + "' has been checked out. Due date: " + book.getDueDate());
        } else {
            System.out.println("Book titled '" + book.getTitle() + "' is already checked out.");
        }
    }

    // Check in method
    public void checkInBook(Book book) {
        if (book.checkedOut()) {
            book.checkIn();
            System.out.println("Book titled '" + book.getTitle() + "' has been checked in.");
        } else {
            System.out.println("Book titled '" + book.getTitle() + "' was not checked out.");
        }
    }
}