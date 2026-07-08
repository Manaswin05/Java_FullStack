package com.mmcoe.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.mmcoe.dao.OrderDao;
import com.mmcoe.entity.Order;

public class testOrder {
	private static OrderDao dao;
	
	@BeforeAll
	public static void setup() {
		dao = new OrderDao();
	}
	
	@Test
	public void testPlaceOrder() {
		Order ord = new Order(LocalDate.of(2026, 04, 02), 599);
		assertNotNull(dao.placeOrder(ord, 11));
		System.out.println("Order placed with No: " + ord.getOrderNo());
	}
	
	@Test
	public void testFindByCustomer() {
		List<Order> orders = dao.findByCustomer(11);
		assertFalse(orders.isEmpty());
		orders.forEach(System.out::println);
	}
	
	@Test
	public void testFindByDateRange() {
		List<Order> orders = dao.findByDateRange(LocalDate.of(2026, 01, 01), LocalDate.of(2026, 06, 30));
		assertFalse(orders.isEmpty());
		orders.forEach(System.out::println);
	}
}