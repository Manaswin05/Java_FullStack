package com.mmcoe.pojo;

public class Book{
	private int isbn;
	private String title,author;
	private double price;
	public Book () {}
	public Book(int isbn, String title, String author, double price) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
	}
	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + ", price=" + price + "]";
	}
	public int getIsbn() {
		return isbn;
	}
	public double getPrice() {
		return price;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	//setters
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
