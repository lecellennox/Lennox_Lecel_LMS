package library;
/* Name: Lecel Lennox
Course: Software Dev I CEN 3024C
Date: 09/08/2024
 */

//Book class contains the book's ISBN, title, and author attributes.
public class Book {
    private final String isbn;
    private final String title;
    private final String author;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }
    public String getIsbn(){
        return isbn;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    //toString Method: format in which the system displays input
    @Override
    public String toString(){
        return isbn + ", " + title + ", " + author;
    }
}
