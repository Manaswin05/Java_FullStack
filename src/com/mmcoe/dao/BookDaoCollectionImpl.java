package com.mmcoe.dao;

import com.mmcoe.pojo.Book;
import com.mmcoe.service.BookNotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BookDaoCollectionImpl implements BookDao {

    private List<Book> books;

    public BookDaoCollectionImpl() {
        this.books = new ArrayList<>();  // Fix: was assigning null to itself
    }

    public void Display() {
        for (Book b : books) {
            System.out.println(b.toString());
        }
    }

    @Override
    public boolean save(Book b) {
        books.add(b);
        return true;
    }

    @Override
    public Book find(int isbn) throws BookNotFoundException {  // Fix: added throws clause
        for (Book b : books) {
            if (b.getIsbn() == isbn) {
                return b;
            }
        }
        throw new BookNotFoundException("Book not found with ISBN: " + isbn);
    }

    @Override
    public List<Book> list() {
        return new ArrayList<>(books);
    }

    @Override
    public Boolean delete(int isbn) throws BookNotFoundException {  // Fix: added throws clause
        for (Book b : books) {
            if (b.getIsbn() == isbn) {
                books.remove(b);
                return true;
            }
        }
        throw new BookNotFoundException("Book not found with ISBN: " + isbn);
    }

    @Override
    public List<Book> FindBooksByPrice(double min, double max) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getPrice() >= min && b.getPrice() <= max) {
                result.add(b);
            }
        }
        return result;
    }

    @Override
    public List<Book> listOrderByTitle() {  // Fix: was missing from interface implementation
        Comparator<Book> titleComp = (b1, b2) -> b1.getTitle().compareTo(b2.getTitle());
        List<Book> sorted = new ArrayList<>(books);
        sorted.sort(titleComp);
        return sorted;
    }
}
