package com.mmcoe.service;
import com.mmcoe.dao.BookDao;
import com.mmcoe.dao.BookDaoCollectionImpl;
import com.mmcoe.pojo.Book;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(int isbn){
		// TODO Auto-generated method stub
		return dao.delete(isbn);
	}

	@Override
	public List<Book> FindBooksByPrice(double min, double max) {
		return dao.FindBooksByPrice(min, max);
	}

}
