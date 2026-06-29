package com.mmcoe.dao;
import com.mmcoe.pojo.Book;
import java.util.List;
import java.util.Vector;

public class BookDaoCollectionImpl implements BookDao {
	private List<Book> books;
	
	public BookDaoCollectionImpl() {
		books = new Vector<Book>();
	}

	public void Display() {
		for(Book b : books) {
			System.out.println(b.toString());
		}
	}
	@Override
	public boolean save(Book b) {
		books.add(b);
		return false;
	}

	@Override
	public Book find(int isbn) {
		for(Book b : books) {
			if(b.getIsbn()==isbn) {
				return b;
			}
		}
		return null;
	}

	@Override
	public List<Book> list() {
		return null;
	}

	public Boolean delete(int isbn) {
		for(Book b : books) {
			if(b.getIsbn()==isbn) {
				books.remove(b);
				return true;
			}
		}
		return false;
	}
	
	public void insert(Book book) {
	    books.add(book);
	}
	
	@Override
	public List<Book> FindBooksByPrice (double min, double max){
		List<Book> result = new Vector<Book>();
		for(Book b : books) {
			if(b.getPrice() <= max && b.getPrice()>=min) {
				result.add(b);
			}
		}
		return result;
	}
}
