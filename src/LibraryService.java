/* Lecel Lennox
Software Development - CEN 3024C
10/06/2024

Library Service Class serves to provide methods to manage the library's collection.
Methods includes adding, removing, finding, and displaying books are used.
HashMap was incorporated to manage library data.
*/
import java.util.HashMap;

public class LibraryService {
    // HashMap to store books, key = barcode
    private HashMap<String, Book> books;


    public LibraryService() {
        books = new HashMap<>();
    }

    // Add book method
    public void addBook(Book book) {
        if (!books.containsKey(book.getBarcode())) {
            books.put(book.getBarcode(), book);
            System.out.println("Book added: " + book.getTitle());
        } else {
            System.out.println("Book with barcode " + book.getBarcode() + " already exists.");
        }
    }

    // Remove book by barcode method
    public void removeByBarcode(String barcode) {
        if (books.containsKey(barcode)) {
            books.remove(barcode);
            System.out.println("Book with barcode " + barcode + " was removed.");
        } else {
            System.out.println("Book with barcode " + barcode + " not found.");
        }
    }

    // Remove book by title method
    public void removeByTitle(String title) {
        Book bookToRemove = null;
        String trimmedTitle = title.trim();  // trim user's input to eliminate spaces

        for (Book book : books.values()) {
            // Compare user's input with the trimmed title and ignore case
            if (book.getTitle().trim().equalsIgnoreCase(trimmedTitle)) {
                bookToRemove = book;
                break;
            }
        }

        if (bookToRemove != null) {
            books.remove(bookToRemove.getBarcode());
            System.out.println("Book titled '" + title + "' was removed.");
        } else {
            System.out.println("Book titled '" + title + "' not found.");
        }
    }

    // Method to find a book by title (helpful for checking out and checking in books)
    public Book findByTitle(String title) {
        String normalizedTitle = title.trim().toLowerCase();
        for (Book book : books.values()) {
            if (book.getTitle().trim().toLowerCase().equals(normalizedTitle)) {
                return book;
            }
        }
        return null; // if book not found, return null
    }

    // Display all books method
    public void displayBooks() {
        System.out.println("\n Library contains the following books:");
        for (Book book : books.values()) {
            System.out.println(book);
        }
    }
}
