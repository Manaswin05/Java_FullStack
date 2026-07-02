package com.mmcoe.jdbc;

import com.mmcoe.pojo.Book;
import com.mmcoe.service.BookNotFoundException;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        BookDaoCollectionImpl bd = new BookDaoCollectionImpl();

        // Save books to DB
        bd.save(new Book(101, "A", "a", 20.0));
        bd.save(new Book(102, "B", "b", 75.0));
        bd.save(new Book(103, "C", "c", 45.0));
        bd.save(new Book(104, "D", "d", 50.0));
        System.out.println("Books saved.");

        // List all books
        System.out.println("\nAll books:");
        List<Book> all = bd.list();
        for (Book b : all) System.out.println(b);

        // Find a book
        try {
            Book found = bd.find(101);
            System.out.println("\nFound: " + found);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Books by price range
        System.out.println("\nBooks priced 40 - 80:");
        List<Book> priceRange = bd.FindBooksByPrice(40.0, 80.0);
        for (Book b : priceRange) System.out.println(b);

        // List ordered by title
        System.out.println("\nBooks ordered by title:");
        List<Book> ordered = bd.listOrderByTitle();
        for (Book b : ordered) System.out.println(b);

        // Delete a book
        try {
            bd.delete(101);
            System.out.println("\nBook 101 deleted.");
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
