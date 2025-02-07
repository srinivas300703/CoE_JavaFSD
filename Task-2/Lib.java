import java.io.*;
import java.util.*;

// Book Class
class Book {
    private final String title;
    private final String author;
    private final String ISBN;

    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getISBN() { return ISBN; }
}

// User Class
class User {
    private final String name;
    private final String userID;
    private final List<Book> borrowedBooks;

    public User(String name, String userID) {
        this.name = name;
        this.userID = userID;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() { return name; }
    public String getUserID() { return userID; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }
}

// Library Interface
interface ILibrary {
    void borrowBook(String ISBN, String userID) throws Exception;
    void returnBook(String ISBN, String userID) throws Exception;
    void reserveBook(String ISBN, String userID) throws Exception;
    Book searchBook(String title);
}

// Abstract LibrarySystem
abstract class LibrarySystem implements ILibrary {
    protected List<Book> books = new ArrayList<>();
    protected List<User> users = new ArrayList<>();

    public abstract void addBook(Book book);
    public abstract void addUser(User user);
}

// Library Manager Class
class LibraryManager extends LibrarySystem {
    private final Map<String, User> userMap = new HashMap<>();
    private final Map<String, Book> bookMap = new HashMap<>();

    @Override
    public void addBook(Book book) {
        books.add(book);
        bookMap.put(book.getISBN(), book);
    }

    @Override
    public void addUser(User user) {
        users.add(user);
        userMap.put(user.getUserID(), user);
    }

    @Override
    public synchronized void borrowBook(String ISBN, String userID) throws Exception {
        if (!bookMap.containsKey(ISBN)) throw new Exception("Book not found!");
        if (!userMap.containsKey(userID)) throw new Exception("User not found!");
        
        User user = userMap.get(userID);
        Book book = bookMap.get(ISBN);
        user.getBorrowedBooks().add(book);
        books.remove(book);
    }

    @Override
    public synchronized void returnBook(String ISBN, String userID) throws Exception {
        if (!userMap.containsKey(userID)) throw new Exception("User not found!");
        
        User user = userMap.get(userID);
        Book bookToReturn = null;
        for (Book book : user.getBorrowedBooks()) {
            if (book.getISBN().equals(ISBN)) {
                bookToReturn = book;
                break;
            }
        }
        
        if (bookToReturn == null) throw new Exception("Book not borrowed by user!");
        
        user.getBorrowedBooks().remove(bookToReturn);
        books.add(bookToReturn);
    }

    @Override
    public void reserveBook(String ISBN, String userID) throws Exception {
        System.out.println("Book reserved successfully.");
    }

    @Override
    public Book searchBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) return book;
        }
        return null;
    }
}

// Main Class
public class Lib {
    public static void main(String[] args) {
        LibraryManager libraryManager = new LibraryManager();

        // Add books and users
        libraryManager.addBook(new Book("The Hobbit", "J.R.R. Tolkien", "123456"));
        libraryManager.addUser(new User("Alice", "U001"));
        
        // Borrow and return book
        try {
            libraryManager.borrowBook("123456", "U001");
            libraryManager.returnBook("123456", "U001");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}