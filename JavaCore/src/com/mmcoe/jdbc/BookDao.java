package com.mmcoe.jdbc;

import java.util.List;
import java.util.Optional;
import com.mmcoe.pojo.Book;

public interface BookDao {
	boolean save(Book b);
	Optional<Book> find(int isbn);
	List<Book> list();
	boolean delete(int isbn);
	List<Book> findByPrice(double min, double max);
	List<Book> listOrderByTitle();
}