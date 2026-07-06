package com.mmcoe.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.mmcoe.dao.EmpDeptDao;
import com.mmcoe.entity.Departments;
import com.mmcoe.entity.Employees;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestEmpDept {

    private static EmpDeptDao dao;

    @BeforeAll
    public static void init() {
        dao = new EmpDeptDao();
    }

    @Test
    @Order(1)
    public void testDeptSave() {
        // First save a department
        Departments dept = new Departments(60, "IT Support", "Mumbai");
        assertNotNull(dao.saveDept(dept));
        System.out.println("Department saved: " + dept);
    }

    @Test
    @Order(2)
    public void testEmpSave() {
        // Find the department first
        Departments dept = dao.deptFind(60);
        
        // Create employee with proper data types matching the database
        Employees emp = new Employees(101, "ABC", "XYZ", 380.0);
        System.out.println("Employee saved: " + emp);
        assertNotNull(dao.saveEmp(emp));
    }

    @Test
    @Order(3)
    public void testDeptList() {
        List<Departments> deptList = dao.deptList();
        assertFalse(deptList.isEmpty());
        System.out.println("All Departments:");
        deptList.forEach(System.out::println);
    }

    @Test
    @Order(4)
    public void testEmpList() {
        List<Employees> empList = dao.EmpList();
        assertFalse(empList.isEmpty());
        System.out.println("All Employees:");
        empList.forEach(System.out::println);
    }

    @Test
    @Order(5)
    public void testEmpFind() {
        Employees emp = dao.empFind(101); // Changed from 106 to 101 (the ID we created)
        assertNotNull(emp);
        System.out.println("Found Employee: " + emp);
    }

    @Test
    @Order(6)
    public void testDeptFind() {
        Departments dept = dao.deptFind(60);
        assertNotNull(dept);
        System.out.println("Found Department: " + dept);
    }

    @Test
    @Order(7)
    public void testDeleteEmp() {
        assertTrue(dao.deleteEmp(101)); // Changed from 106 to 101
        System.out.println("Employee deleted successfully");
    }

    @Test
    @Order(8)
    public void testDeleteDept() {
        assertTrue(dao.deleteDept(60));
        System.out.println("Department deleted successfully");
    }
}