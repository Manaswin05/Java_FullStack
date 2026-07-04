package com.mmcoe.jdbc;

import com.mmcoe.pojo.Book;
import com.mmcoe.service.BookNotFoundException;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        BookDaoCollectionImpl bd = new BookDaoCollectionImpl();

        // Save books to DB
        bd.save(new Book(11, "The Alchemist", "Paulo Coelho", 250));
        bd.save(new Book(12, "Metamorphosis", "Frank Kafka", 210));
        bd.save(new Book(13, "C Programming", "Zubair Shaikh", 150));
        System.out.println("Books saved.");

        // List all
        System.out.println("\nAll books:");
        for (Book b : bd.list()) System.out.println(b);

        // Find
        try {
            System.out.println("\nFound: " + bd.find(12));
        } catch (BookNotFoundException e) {
            e.printStackTrace();
        }

        // Price range
        System.out.println("\nBooks priced 200 - 300:");
        for (Book b : bd.FindBooksByPrice(200, 300)) System.out.println(b);

        // Ordered by title
        System.out.println("\nOrdered by title:");
        bd.listOrderByTitle().forEach(System.out::println);

        // Delete
        try {
            bd.delete(11);
            System.out.println("\nBook 11 deleted.");
        } catch (BookNotFoundException e) {
            e.printStackTrace();
        }
    }
}
