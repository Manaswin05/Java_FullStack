package com.mmcoe.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.mmcoe.pojo.Book;

public class BookDaoCollectionImpl implements BookDao {

    private List<Book> books;

    public BookDaoCollectionImpl() {
        this.books = new ArrayList<>(); 
    }

	@Override
	public boolean save (Book b) {
		String sql = "insert into books values (?, ?, ?, ?)";

		try {
			Connection conn = JdbcFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, b.getIsbn());
			stmt.setString(2, b.getTitle());
			stmt.setString(3, b.getAuthor());
			stmt.setDouble(4, b.getPrice());

			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Optional<Book> find(int isbn) {
		String sql = "select * from books where isbn=" + isbn;

		try {
			Connection conn = JdbcFactory.getConnection();
			ResultSet rs = conn.createStatement().executeQuery(sql);
			Book b = null;
			if (rs.next()) {
				b = new Book();
				b.setIsbn(rs.getInt(1));
				b.setTitle(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getDouble(4));
			}
			return Optional.of(b);
		} catch (SQLException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

	@Override
	public List<Book> list() {
		String sql = "select * from books";
		List<Book> books = new ArrayList<Book>();

		try {
			Connection conn = JdbcFactory.getConnection();
			ResultSet rs = conn.createStatement().executeQuery(sql);

			while (rs.next()) {
				Book b = new Book();
				b.setIsbn(rs.getInt(1));
				b.setTitle(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getDouble(4));
				books.add(b);
			}
			return books;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean delete(int isbn) {
		String sql = "delete from books where isbn=" + isbn;

		try {
			Connection conn = JdbcFactory.getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Book> findByPrice(double min, double max) {
		String sql = "select * from books where price between ? and ?";
		List<Book> books = new ArrayList<Book>();

		try {
			Connection conn = JdbcFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, min);
			stmt.setDouble(2, max);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Book b = new Book();
				b.setIsbn(rs.getInt(1));
				b.setTitle(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getDouble(4));
				books.add(b);
			}
			return books;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Book> listOrderByTitle() {
	    List<Book> list = this.list();      
	    list.sort(Comparator.comparing(Book::getTitle));
	    return list;
	}

}