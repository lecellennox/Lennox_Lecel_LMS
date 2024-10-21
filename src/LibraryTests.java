/*Lecel Lennox Cen 3024C Software Development I
Library Management System Unit Tests
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class LibraryTests {
    private LibraryService libraryService;
    private BookService bookService;
    private Book book1;

    @BeforeEach
    public void setUp() {
        libraryService = new LibraryService();
        bookService = new BookService();
        book1 = new Book("009", "Iron Flame", "Rebecca Yarros");
        Book book2 = new Book("010", "Before the coffee gets cold", "Toshikazu Kawaguchi");
        libraryService.addBook(book1);
        libraryService.addBook(book2);
    }
    @Test
    //Add to database test
    public void testAddBook() {
        Book book3 = new Book("011", "Divergent", "Veronica Roth");
        libraryService.addBook(book3);
        assertNotNull(libraryService.findByTitle("Divergent"));
    }

    @Test
    //Remove from database by barcode test
    public void testRemoveBookByBarcode() {
        // Find the book to check if its available
        assertNotNull(libraryService.findByTitle("Iron Flame"), "Find the book before removing it");

        // Remove by barcode
        libraryService.removeByBarcode("009");

        //Check that the book is no longer in the list
        assertNull(libraryService.findByTitle("Iron Flame"), "Book isn't locatable once it's removed.");
    }


    @Test
    //Remove from database by title test
    public void testRemoveBookByTitle() {
        // Verify that the book is present before removal
        assertNotNull(libraryService.findByTitle("Before the coffee gets cold"), "Find the book before removing it");

        // Remove by title
        libraryService.removeByTitle("Before the coffee gets cold");

        // Verify that the book is no longer present
        assertNull(libraryService.findByTitle("Before the coffee gets cold"), "Book isn't locatable once it's removed.");
    }

    @Test
    //Check out test
    public void testCheckOutBookSetsDueDate() {
        //book with no due date
        assertNull(book1.getDueDate(), "Prior to check out, due date is null");

        // Checkout book
        bookService.checkOutBook(book1);

        // get the due date
        assertNotNull(book1.getDueDate(), "Post check out, due date is not null");

        // due date is set 4 weeks from check out date
        LocalDate expectedDueDate = LocalDate.now().plusWeeks(4);

        //compare
        assertEquals(expectedDueDate, book1.getDueDate(), "Due 4 weeks from today.");
    }


    @Test
    //Check in test
    public void testCheckInBookSetsDueDateToNull() {
        // Check out the book first
        bookService.checkOutBook(book1);

        // check in book
        bookService.checkInBook(book1);

        // due date is now null
        assertNull(book1.getDueDate(), "Post check in, due date is not null");
    }
}
