import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        LibraryService libraryService = new LibraryService();
        BookService bookService = new BookService();

        // Request file name from user
        System.out.print("Enter the file name to load books: ");
        String fileName = input.nextLine();

        // Load the books from the file
        try {
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] bookData = line.split(",");
                String barcode = bookData[0];
                String title = bookData[1];
                String author = bookData[2];
                Book book = new Book(barcode, title, author);
                libraryService.addBook(book);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return;
        }

        // Display all books in the library
        libraryService.displayBooks();

        // Remove a book by barcode
        System.out.print("\n Enter the barcode of the book to remove: ");
        String barcode = input.nextLine();
        libraryService.removeByBarcode(barcode);
        libraryService.displayBooks();

        // Remove a book by title
        System.out.print("\n Enter the title of the book to remove: ");
        String titleToRemove = input.nextLine();
        libraryService.removeByTitle(titleToRemove);
        libraryService.displayBooks();

        // Check out a book
        System.out.print("\n Enter the title of the book to check out: ");
        String titleToCheckOut = input.nextLine();
        Book bookToCheckOut = libraryService.findByTitle(titleToCheckOut);
        if (bookToCheckOut != null) {
            bookService.checkOutBook(bookToCheckOut);
        } else {
            System.out.println("Book titled " + titleToCheckOut + " not found.");
        }

        libraryService.displayBooks();

        // Check in a book
        System.out.print("\n Enter the book's title to check in: ");
        String titleToCheckIn = input.nextLine();
        Book bookToCheckIn = libraryService.findByTitle(titleToCheckIn);
        if (bookToCheckIn != null) {
            bookService.checkInBook(bookToCheckIn);
        } else {
            System.out.println("Book titled " + titleToCheckIn + " not found.");
        }

        libraryService.displayBooks();

        input.close();
    }

}