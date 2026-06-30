package com.mmcoe.dao;
import java.util.List;

import com.mmcoe.pojo.Book;
import com.mmcoe.service.BookNotFoundException;

public interface BookDao {
	boolean save(Book b);
	Book find(int isbn) throws BookNotFoundException;
	List<Book> list();
	Boolean delete(int isbn);
	List<Book> FindBooksByPrice(double min, double max);
}
