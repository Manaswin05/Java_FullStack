package com.mmcoe.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.mmcoe.dao.CustomerDao;
import com.mmcoe.entity.Customer;

@TestMethodOrder(OrderAnnotation.class)
public class TestCustomerDao {

    private static CustomerDao dao;

    @BeforeAll
    public static void init() {
        dao = new CustomerDao();
    }

    @Test
    @Order(1)
    public void testSave() {
        Customer c = new Customer(15, "Frank", "Fairfax");
        assertNotNull(dao.save(c));
    }

    @Test
    @Order(2)
    public void testList() {
        List<Customer> customers = dao.list();

        assertFalse(customers.isEmpty());

        customers.forEach(System.out::println);
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