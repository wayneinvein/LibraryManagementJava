//source code 

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Book class representing a library book
class Book {
    String id, title, author, genre, status;

    // Constructor to initialize book details
    public Book(String id, String title, String author, String genre, String status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        setStatus(status); // Ensure status is valid before assigning
    }

    // Method to validate and set book status
    public void setStatus(String status) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            status = status.trim(); // remove spaces
            // Ensure status is either 'Available' or 'Checked Out'
            if (status.equalsIgnoreCase("Available") || status.equalsIgnoreCase("Checked Out")) {
                this.status = status;
                break; // Exit loop if status is valid
            } else {
                System.out.print("Invalid status! Please enter 'Available' or 'Checked Out': ");
                status = scanner.nextLine(); // Prompt user for valid input
            }
        }
    }
    
    // Override toString to provide a formatted book description
    public String toString() {
        return id + " | " + title + " | " + author + " | " + genre + " | " + status;
    }
}

public class MyLibrary {
    static List<Book> books = new ArrayList<>(); // List to store books
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            // Display menu options for library management
            System.out.println("\nJAVA BASED LIBRARY MANAGEMENT SYSTEM: ");
            System.out.println("Select an option: ");
            System.out.println("1. Add Book  \n2. View Books  \n3. Search Book  \n4. Update Book  \n5. Delete Book  \n6. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume leftover newline character

            // Execute the corresponding function based on user choice
            switch (choice) {
                case 1: addBook(); break;
                case 2: viewBooks(); break;
                case 3: searchBook(); break;
                case 4: updateBook(); break;
                case 5: deleteBook(); break;
                case 6: System.out.println("Goodbye!"); return;
                default: System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // Method to add a book to the collection
    static void addBook() {
        System.out.print("ID: ");
        String id = scanner.nextLine().trim();

        // Check if book ID already exists
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).id.equals(id)) {
                System.out.println("Book ID already exists!");
                return;
            }
        }

        //takes title
        System.out.print("Title: ");
        String title = scanner.nextLine().trim();
        //ensures title cannot be left empty
        if (title.isEmpty()) { System.out.println("Title cannot be empty!"); return; }

        //takes author
        System.out.print("Author: ");
        String author = scanner.nextLine().trim();
        //ensures author cannot be left empty
        if (author.isEmpty()) { System.out.println("Author cannot be empty!"); return; }

        System.out.print("Genre: ");
        String genre = scanner.nextLine().trim();

        System.out.print("Status (Available/Checked Out): ");
        String status = scanner.nextLine().trim();

        // Add the book to the list after validation
        books.add(new Book(id, title, author, genre, status));
        System.out.println("Book added!");
    }

    // Method to display all books in the collection
    static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (int i = 0; i < books.size(); i++) {
                System.out.println(books.get(i));
            }
        }
    }

    // Method to search for a book by ID or Title
    static void searchBook() {
        System.out.print("Enter ID or Title: ");
        String query = scanner.nextLine().trim();

        boolean found = false;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).id.equalsIgnoreCase(query) || books.get(i).title.equalsIgnoreCase(query)) {
                System.out.println("Book found: " + books.get(i));
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Book not found!");
        }
    }

    // Method to update book details based on ID
    static void updateBook() {
        System.out.print("Enter Book ID: ");
        String id = scanner.nextLine().trim();

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).id.equals(id)) {
                System.out.print("New Title (Enter to skip): ");
                String title = scanner.nextLine().trim();
                if (!title.isEmpty()) books.get(i).title = title;

                System.out.print("New Author (Enter to skip): ");
                String author = scanner.nextLine().trim();
                if (!author.isEmpty()) books.get(i).author = author;

                System.out.print("New Genre (Enter to skip): ");
                String genre = scanner.nextLine().trim();
                if (!genre.isEmpty()) books.get(i).genre = genre;

                System.out.print("New Status (Available/Checked Out): ");
                String status = scanner.nextLine().trim();
                books.get(i).setStatus(status); // Validate and update status

                System.out.println("Book updated!");
                return;
            }
        }
        System.out.println("Book not found!");
    }

    // Method to delete a book from the collection
    static void deleteBook() {
        System.out.print("Enter Book ID: ");
        String id = scanner.nextLine().trim();

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).id.equals(id)) {
                books.remove(i); // Remove book from the list
                System.out.println("Book deleted!");
                return;
            }
        }
        System.out.println("Book not found!");
    }
}
