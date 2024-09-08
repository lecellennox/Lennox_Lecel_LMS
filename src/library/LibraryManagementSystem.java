package library;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/* Name: Lecel Lennox
Course: Software Dev I CEN 3024C
Date: 09/08/2024
 */

//This class inherits JFrame to create GUI
public class LibraryManagementSystem extends JFrame{

    private final List<Book> bookArrayList = new ArrayList<>();
    private final JTextField isbnField;
    private final JTextField titleField;
    private final JTextField authorField;

    public LibraryManagementSystem() {

        //JFrame Constructors
        setTitle("Welcome to Library Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        setLayout(new BorderLayout());

        //User panels: purpose is to label user related areas.
        JPanel userPanel = new JPanel(new GridLayout(5,2));

        userPanel.add(new JLabel ("Book ISBN:"));
        isbnField = new JTextField();
        userPanel.add(isbnField);
        isbnField.setBackground(new Color(225, 235, 246));

        userPanel.add(new JLabel ("Book Title:"));
        titleField = new JTextField();
        userPanel.add(titleField);
        titleField.setBackground(new Color(225, 235, 246));

        userPanel.add(new JLabel ("Book Author:"));
        authorField = new JTextField();
        userPanel.add(authorField);
        authorField.setBackground(new Color(225, 235, 246));

        add(userPanel, BorderLayout.CENTER);

        //Add Book, Remove Book, Search Book, and List all Books button
        JButton addBtn = new JButton("Add Book");
        addBtn.setBackground(new Color(176, 180, 225, 255));
        userPanel.add(addBtn);

        JButton removeBtn = new JButton("Remove Book");
        removeBtn.setBackground(new Color(176, 180, 225));
        userPanel.add(removeBtn);

        JButton listBtn = new JButton("List All Books");
        listBtn.setBackground(new Color(176, 180, 225));
        userPanel.add(listBtn);

        JButton searchBtn = new JButton("Search Book");
        searchBtn.setBackground(new Color(176, 180, 225));
        userPanel.add(searchBtn);

        //Adds Action Listener to each button
        addBtn.addActionListener(new AddBookAction());
        removeBtn.addActionListener(new RemoveBookAction());
        listBtn.addActionListener(new ListBooksAction());
        searchBtn.addActionListener(new SearchBookAction());

        setVisible(true);

    }
    //Add Book Action listener - this triggers the addition of book to the list with confirmation message or an error duplicate message
    private class AddBookAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String isbn = isbnField.getText();
            String title = titleField.getText();
            String author = authorField.getText();

            boolean duplicateExists = false;

            for (Book book : bookArrayList) {
                if (book.getIsbn().equals(isbn)) {
                    duplicateExists = true;
                    break;
                }
            }
            //If statement with JOptionPane to interact with user to confirm added book or to display error duplicate message.
            if (duplicateExists) {

                JOptionPane.showMessageDialog(null, "Error, duplicate submission!", "Error Message", JOptionPane.ERROR_MESSAGE);
            } else {
                bookArrayList.add(new Book(isbn, title, author));
                JOptionPane.showMessageDialog(null, "Book Added to the System!");
            }
        }
    }
    // Remove Book Action Listener - this triggers removing the book from the list and for JOptionPane to display a confirmation message.
    private class RemoveBookAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String isbn = isbnField.getText();

            //removeIf method- purpose is to remove book that matches the input
            bookArrayList.removeIf(book -> book.getIsbn().equals(isbn));
            JOptionPane.showMessageDialog(null, "Book Removed from the System!");
        }
    }
    //Search Book Action Listener - this displays the search result or an error message.
    private class SearchBookAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            //input is lower cased and compared to matching books from the list
            String searchBook = titleField.getText().toLowerCase();

            StringBuilder searchResults = new StringBuilder();
            for (Book book : bookArrayList) {
                String bookTitle = book.getTitle().toLowerCase();
                String bookAuthor = book.getAuthor().toLowerCase();

                //if book is found, system returns the toString format
                if (bookTitle.contains(searchBook) || bookAuthor.contains(searchBook)) {
                    searchResults.append(book.toString()).append("\n");
                }
            }

            //If statement with JOptionPane to interact with user to provide Search Result or display error message.
            if (!searchResults.isEmpty()) {
                JOptionPane.showMessageDialog(null, searchResults.toString(), "Result", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Book not in the system.", "Search Result", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // List of Books Action listener - this triggers a new window to display list.
    private class ListBooksAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFrame listFrame = new JFrame("List of Books");
            listFrame.setSize(500, 500);
            JTextArea listDisplayArea = new JTextArea();

            //The list is only for show and not editable.
            listDisplayArea.setEditable(false);
            listDisplayArea.setBackground(new Color(230,230,250));

            StringBuilder displayText = new StringBuilder();
            for (Book book : bookArrayList) {
                displayText.append(book).append("\n");
            }

            //Displays the list that calls for the toString format
            listDisplayArea.setText(displayText.toString());
            listFrame.add(new JScrollPane(listDisplayArea), BorderLayout.CENTER);
            listFrame.setVisible(true);

        }
    }
}
