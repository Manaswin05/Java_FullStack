package com.mmcoe.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.mmcoe.entity.Employees;
import com.mmcoe.entity.Departments;

public class EmpDeptDao {

    private static EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("MyJPA");
    }

    // Save 
    public Employees saveEmp(Employees em) {
        EntityManager mgr = emf.createEntityManager();
        EntityTransaction txn = mgr.getTransaction();

        try {
            txn.begin();
            mgr.persist(em);
            txn.commit();
            return em;

        } catch (Exception e) {
        	txn.rollback();
            e.printStackTrace();
            return null;
        } finally {
            mgr.close();
        }
    }
    
    public Departments saveDept(Departments dept) {
        EntityManager mgr = emf.createEntityManager();
        EntityTransaction txn = mgr.getTransaction();

        try {
            txn.begin();
            mgr.persist(dept);
            txn.commit();
            return dept;
        } catch (Exception e) {
        	txn.rollback();
            e.printStackTrace();
            return null;
        } finally {
            mgr.close();
        }
    }

    // Find 
    public Employees empFind(int empId) {
        EntityManager mgr = emf.createEntityManager();
        try {
            return mgr.find(Employees.class, empId);
        } finally {
            mgr.close();
        }
    }
    public Departments deptFind(int deptNo) {
        EntityManager mgr = emf.createEntityManager();
        try {
            return mgr.find(Departments.class, deptNo);
        } finally {
            mgr.close();
        }
    }
    // List All
    public List<Employees> EmpList() {
            EntityManager mgr = emf.createEntityManager();
            try {
                return mgr.createQuery("From Employees", Employees.class).getResultList();
            } finally {
                mgr.close();
            }
        }
    
    public List<Departments> deptList() {
    	EntityManager mgr = emf.createEntityManager();
        try {
            Query query = mgr.createQuery("From Departments", Departments.class);
            		return query.getResultList();
        } finally {
            mgr.close();
        }
    }

    // Delete
    public boolean deleteEmp(int empId) {
        EntityManager mgr = emf.createEntityManager();
        EntityTransaction txn = mgr.getTransaction();
        try {
            txn.begin();
            Employees emp = mgr.find(Employees.class, empId);
            if (emp != null) {
                mgr.remove(emp);
                txn.commit();
                return true;
            }
            txn.rollback();
            return false;
        } catch (Exception e) {
        	txn.rollback();
            e.printStackTrace();
            return false;
        } finally {
            mgr.close();
        }
    }
    public boolean deleteDept(int deptNo) {
        EntityManager mgr = emf.createEntityManager();
        EntityTransaction txn = mgr.getTransaction();
        try {
            txn.begin();
            Departments dept = mgr.find(Departments.class, deptNo);
            if (dept != null) {
                mgr.remove(dept);
                txn.commit();
                return true;
            }
            txn.rollback();
            return false;
        } catch (Exception e) {
        	txn.rollback();
            e.printStackTrace();
            return false;
        } finally {
            mgr.close();
        }
    }
}