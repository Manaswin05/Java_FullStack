package com.mmcoe.service;
import com.mmcoe.dao.BookDao;

import com.mmcoe.pojo.Book;

import java.util.Comparator;
import java.util.List;

public class BookServiceImpl implements BookDao {
	private BookDao dao;
	public BookServiceImpl(BookDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean save(Book b) {
		return dao.save(b);
	}
	
	@Override
	public Book find(int isbn) throws BookNotFoundException{
		Book b =  dao.find(isbn);
		if(b==null) {
			return b;
		}else {
			throw new BookNotFoundException("Book not found ISBN : "+ isbn);
		}
	}

	@Override
	public List<Book> list() {
		return null;
	}

	@Override
	public Boolean delete(int isbn) throws BookNotFoundException{
		try {			
			return dao.delete(isbn);
		}catch (BookNotFoundException e) {
			throw new BookNotFoundException("Book not found ISBN : "+ isbn);
		}
	}

	@Override
	public List<Book> FindBooksByPrice(double min, double max) {
		return dao.FindBooksByPrice(min, max);
	}
	@Override 
	public List<Book> listOrderByTitle(){
		Comparator<Book> titleComp = (b1,b2)->b1.getTitle().compareTo(b2.getTitle());
		List<Book> list = dao.list();
		list.sort(titleComp);
		return list;
	}

}
