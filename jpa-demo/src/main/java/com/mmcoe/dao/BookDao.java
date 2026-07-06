package com.mmcoe.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.mmcoe.entity.Book;

public class BookDao {

    private static EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("MyJPA");
    }

    // Save Book
    public Book save(Book b) {
        EntityManager mgr = emf.createEntityManager();
        EntityTransaction txn = mgr.getTransaction();

        try {
            txn.begin();
            mgr.persist(b);
            txn.commit();
            return b;

        } catch (Exception e) {
            e.printStackTrace();
            if (txn.isActive()) {
                txn.rollback();
            }
            return null;
        } finally {
            mgr.close();
        }
    }

    // Find Book
    public Book find(int bookId) {
        EntityManager mgr = emf.createEntityManager();
        try {
            return mgr.find(Book.class, bookId);
        } finally {
            mgr.close();
        }
    }

    // List All Books
    public List<Book> list() {
        EntityManager mgr = emf.createEntityManager();
        try {
            Query query = mgr.createQuery("FROM Book");
            return query.getResultList();
        } finally {
            mgr.close();
        }
    }

    // Delete Book
    public boolean delete(int bookId) {
        EntityManager mgr = emf.createEntityManager();
        EntityTransaction txn = mgr.getTransaction();
        try {
            txn.begin();

            Book c = mgr.find(Book.class, bookId);

            if (c != null) {
                mgr.remove(c);
                txn.commit();
                return true;
            }

            txn.rollback();
            return false;

        } catch (Exception e) {
            e.printStackTrace();

            if (txn.isActive()) {
                txn.rollback();
            }

            return false;

        } finally {
            mgr.close();
        }
    }
}