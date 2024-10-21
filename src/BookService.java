/* Lecel Lennox
Software Development - CEN 3024C
10/06/2024
 */

//Book Service Class serves to provide check in and check out method
public class BookService {
    // Check out method
    public boolean checkOutBook(Book book) {
        if (!book.checkedOut()) {
            book.checkOut();
            System.out.println("Book titled '" + book.getTitle() + "' has been checked out. Due date: " + book.getDueDate());
        } else {
            System.out.println("Book titled '" + book.getTitle() + "' is already checked out.");
        }
        return false;
    }

    // Check in method
    public boolean checkInBook(Book book) {
        if (book.checkedOut()) {
            book.checkIn();
            System.out.println("Book titled '" + book.getTitle() + "' has been checked in.");
        } else {
            System.out.println("Book titled '" + book.getTitle() + "' was not checked out.");
        }
        return false;
    }
}