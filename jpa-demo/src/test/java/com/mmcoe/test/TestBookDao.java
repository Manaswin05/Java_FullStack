package com.mmcoe.test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.*;

import com.mmcoe.dao.BookDao;
import com.mmcoe.entity.Book;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestBookDao {

    private static BookDao dao;
    private static int id;

    @BeforeAll
    public static void init() {
        dao = new BookDao();
    }

    @Test
    @Order(1)
    public void testSave() {
        Book b = new Book(15,"XYZ", "ABC", 540.0, 3);
        assertNotNull(dao.save(b));
    }

    @Test
    @Order(2)
    public void testList() {
        List<Book> books = dao.list();
       assertFalse(books.isEmpty());
        books.forEach(System.out::println);
    }

    @Test
    @Order(3)
    public void testFind() {
        assertNotNull(dao.find(15));
    }

    @Test
    @Order(4)
    public void testDelete() {
        assertTrue(dao.delete(15));
    }
}