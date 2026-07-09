package com.mmcoe.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.mmcoe.entity.Customer;
import com.mmcoe.entity.Order;

public class OrderDao {
	private EntityManagerFactory emf;

	public OrderDao() {
		emf = Persistence.createEntityManagerFactory("MyJPA");
	}
	
	public Order placeOrder(Order o, int cid) {
		EntityManager mgr = emf.createEntityManager();
		EntityTransaction txn = mgr.getTransaction();
		txn.begin();
		Customer c = mgr.find(Customer.class, cid);
		o.setCustomer(c);
		mgr.persist(o);
		txn.commit();
		return o;
	}
	public List<Order> findByCustomer(int cid){
		EntityManager mgr = emf.createEntityManager();
		Customer c = mgr.find(Customer.class, cid);
		return c.getOrders();
	}
	public List<Order> findByDateRange(LocalDate start, LocalDate end){
		String hql = "FROM Order Where orderDate between :start AND :end";
		EntityManager mgr = emf.createEntityManager();
		Query query = mgr.createQuery(hql);
		query.setParameter("start", start);
		query.setParameter("end", end);
		return query.getResultList();
	}
}
