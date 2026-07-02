package com.mmcoe.jdbc;

import com.mmcoe.pojo.Book;
import com.mmcoe.service.BookNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BookDaoCollectionImpl implements BookDao {

    // ── save ──────────────────────────────────────────────────────────────────
    @Override
    public boolean save(Book b) {
        String sql = "INSERT INTO workshop (book_id, book_title, book_author, book_price) VALUES (?, ?, ?, ?)";
        try (Connection conn = JdbcFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

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

    // ── find ──────────────────────────────────────────────────────────────────
    @Override
    public Book find(int isbn) throws BookNotFoundException {
        String sql = "SELECT * FROM workshop WHERE book_id = ?";
        try (Connection conn = JdbcFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, isbn);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapRow(rs);
            } else {
                throw new BookNotFoundException("Book not found with ISBN: " + isbn);
            }

        } catch (SQLException e) {
            throw new BookNotFoundException("DB error: " + e.getMessage());
        }
    }

    // ── list ──────────────────────────────────────────────────────────────────
    @Override
    public List<Book> list() {
        String sql = "SELECT * FROM workshop";
        List<Book> books = new ArrayList<>();
        try (Connection conn = JdbcFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                books.add(mapRow(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    // ── listOrderByTitle ──────────────────────────────────────────────────────
    @Override
    public List<Book> listOrderByTitle() {
        String sql = "SELECT * FROM workshop ORDER BY book_title";
        List<Book> books = new ArrayList<>();
        try (Connection conn = JdbcFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                books.add(mapRow(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    // ── delete ────────────────────────────────────────────────────────────────
    @Override
    public Boolean delete(int isbn) throws BookNotFoundException {
        String sql = "DELETE FROM workshop WHERE book_id = ?";
        try (Connection conn = JdbcFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, isbn);
            int rows = stmt.executeUpdate();

            if (rows == 0) {
                throw new BookNotFoundException("Book not found with ISBN: " + isbn);
            }
            return true;

        } catch (SQLException e) {
            throw new BookNotFoundException("DB error: " + e.getMessage());
        }
    }

    // ── FindBooksByPrice ──────────────────────────────────────────────────────
    @Override
    public List<Book> FindBooksByPrice(double min, double max) {
        String sql = "SELECT * FROM workshop WHERE book_price >= ? AND book_price <= ?";
        List<Book> books = new ArrayList<>();
        try (Connection conn = JdbcFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, min);
            stmt.setDouble(2, max);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                books.add(mapRow(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    // ── helper ────────────────────────────────────────────────────────────────
    private Book mapRow(ResultSet rs) throws SQLException {
        int isbn       = rs.getInt("book_id");
        String title   = rs.getString("book_title");
        String author  = rs.getString("book_author");
        double price   = rs.getDouble("book_price");
        return new Book(isbn, title, author, price);
    }
}
