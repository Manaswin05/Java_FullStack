package com.mmcoe.service;
import com.mmcoe.dao.BookDaoCollectionImpl;
import com.mmcoe.service.BookServiceImpl;
import com.mmcoe.service.BookNotFoundException;
import java.util.List;

import com.mmcoe.dao.BookDao;
import com.mmcoe.pojo.Book;

public class TestBookService implements BookDao {
	public static void main(String[] args) {
		BookDao dao = new BookDaoCollectionImpl();
		BookServiceImpl service = new BookServiceImpl(dao);
		service.save(new Book(11,"The Alchemist","Paulo Coelho",250));
		service.save(new Book(12,"Metamorphosis","Frank Kafka",210));
		service.save(new Book(13,"C Programming","Zubair Shaikh",150));
		for(Book b : service.list()) {
			System.out.println(b);
		}
		try {
			System.out.println(service.find(12));
		}catch (BookNotFoundException e){
			e.printStackTrace();			
		}
		
		for(Book b : service.FindBooksByPrice(200, 300)) {
			System.out.println(b);
		}
		try {
			service.delete(15);
		}
		catch (BookNotFoundException e){
			e.printStackTrace();			
		}
	}
}