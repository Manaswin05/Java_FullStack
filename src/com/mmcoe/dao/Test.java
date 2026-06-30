package com.mmcoe.dao;
import com.mmcoe.pojo.Book;

public class Test {

	public static void main(String[] args) {
		BookDaoCollectionImpl bd = new BookDaoCollectionImpl();
		bd.save(new Book(1,"A","a",20.0));
		bd.save(new Book(1,"B","b",75.0));
		bd.save(new Book(1,"C","c",45.0));
		bd.save(new Book(1,"D","d",50.0));
		bd.Display();
	}
}
