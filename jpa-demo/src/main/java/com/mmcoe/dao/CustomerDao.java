package com.mmcoe.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.mmcoe.entity.Customer;

public class CustomerDao {
    private static EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("MyJPA");
    }

    // Save Customer
    public Customer save(Customer c) {
        EntityManager mgr = emf.createEntityManager();
        EntityTransaction txn = mgr.getTransaction();

        try {
            txn.begin();

            mgr.persist(c);

            txn.commit();
            return c;

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

    // Find Customer
    public Customer find(int custId) {
        EntityManager mgr = emf.createEntityManager();

        try {
            return mgr.find(Customer.class, custId);

        } finally {
            mgr.close();
        }
    }

    // List All Customers
    public List<Customer> list() {
        EntityManager mgr = emf.createEntityManager();
        try {
            return mgr.createQuery(
                    "From Customer",
                    Customer.class)
                    .getResultList();
        } finally {
            mgr.close();
        }
    }

    // Delete Customer
    public boolean delete(int custId) {
        EntityManager mgr = emf.createEntityManager();
        EntityTransaction txn = mgr.getTransaction();

        try {
            txn.begin();

            Customer c = mgr.find(Customer.class, custId);

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