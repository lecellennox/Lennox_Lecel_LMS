/* Lecel Lennox 11/03/2024 CEN 3024C Software Dev I
The LibraryGUI class uses JavaFX to create user interaction to provide functions such as
remove book by barcode and title, check out books, check in books, and display books.
*/
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LibraryGUI extends Application {

    private LibraryService libraryService = new LibraryService();
    private TextArea displayArea = new TextArea();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Welcome to Library Management System");

        // Layouts
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        // File input section
        Button uploadBookFile = new Button("Upload Book File");
        uploadBookFile.setOnAction(e -> loadBooksFromFile(primaryStage));

        // Book removal by barcode section
        TextField barcodeInput = new TextField();
        barcodeInput.setPromptText("Input barcode to remove");
        barcodeInput.setPrefWidth(500);
        Button removeBarcodeButton = new Button("Remove by Barcode");

        //Hbox Layout for removal via barcode
        HBox barcodeBox = new HBox(10, barcodeInput, removeBarcodeButton);
        barcodeBox.setAlignment(Pos.CENTER_LEFT);
        removeBarcodeButton.setOnAction(e -> removeBookByBarcode(barcodeInput.getText()));

        // Book removal by title section
        TextField titleInput = new TextField();
        titleInput.setPromptText("Input title to remove");
        titleInput.setPrefWidth(500);
        Button removeTitleButton = new Button("Remove by Title");

        //Hbox Layout for removal via title
        HBox titleBox = new HBox(10, titleInput, removeTitleButton);
        titleBox.setAlignment(Pos.CENTER_LEFT);
        removeTitleButton.setOnAction(e -> removeBookByTitle(titleInput.getText()));

        // Check out
        TextField checkoutInput = new TextField();
        checkoutInput.setPromptText("Input title to check out");
        checkoutInput.setPrefWidth(500);
        Button checkoutButton = new Button("Check Out");

        //Hbox Layout for Check out
        HBox checkoutBox = new HBox(10, checkoutInput, checkoutButton);
        checkoutBox.setAlignment(Pos.CENTER_LEFT);
        checkoutButton.setOnAction(e -> checkOutBook(checkoutInput.getText()));

        //Check in
        TextField checkinInput = new TextField();
        checkinInput.setPromptText("Input title to check in");
        checkinInput.setPrefWidth(500);
        Button checkinButton = new Button("Check In");

        //Hbox Layout for Check in
        HBox checkinBox = new HBox(10, checkinInput, checkinButton);
        checkinBox.setAlignment(Pos.CENTER_LEFT);
        checkinButton.setOnAction(e -> checkInBook(checkinInput.getText()));

        // Exit button
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> primaryStage.close());

        // Display area for books
        displayArea.setEditable(false);
        displayArea.setPrefHeight(300);

        // Add elements to layout
        root.getChildren().addAll(
                uploadBookFile, barcodeBox, titleBox, checkoutBox, checkinBox, displayArea, exitButton
        );

        // Create scene and show stage
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }

    private void loadBooksFromFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Book File");
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] data = line.split(",");
                    Book book = new Book(data[0], data[1], data[2]);
                    libraryService.addBook(book);
                }
                scanner.close();
                displayBooks();
            } catch (FileNotFoundException e) {
                showAlert("Error", "File not found.");
            }
        }
    }

    private void removeBookByBarcode(String barcode) {
        libraryService.removeByBarcode(barcode);
        displayBooks();
    }

    private void removeBookByTitle(String title) {
        libraryService.removeByTitle(title);
        displayBooks();
    }

    private void checkOutBook(String title) {
        Book book = libraryService.findByTitle(title);
        if (book != null) {
            new BookService().checkOutBook(book);
            displayBooks();
        } else {
            showAlert("Error", "Book unavailable.");
        }
    }

    private void checkInBook(String title) {
        Book book = libraryService.findByTitle(title);
        if (book != null) {
            new BookService().checkInBook(book);
            displayBooks();
        } else {
            showAlert("Error", "Book unavailable.");
        }
    }

    private void displayBooks() {
        displayArea.clear();
        displayArea.appendText("Library Books:\n");
        for (Book book : libraryService.getBooks()) {
            displayArea.appendText(book.toString() + "\n");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
