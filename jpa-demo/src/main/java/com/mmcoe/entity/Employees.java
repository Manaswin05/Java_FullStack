package com.mmcoe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employees {
	@Id
	@Column(name = "empId")
	private int empId;

	@Column(name = "empName", length = 100, nullable = false)
	private String empName;

    @Column(name = "role", length = 50)
    private String role;

    @Column(name = "salary", precision = 10, scale = 2)
    private double salary;

    @ManyToOne
	@JoinColumn(name = "deptNo")
	private Departments department;

    public Employees() {
    }
    
	public Employees(int empId, String empName, String role, double salary) {
		this.empId = empId;
		this.empName = empName;
		this.role = role;
		this.salary = salary;
	}

	public Employees(int empId, String empName, String role, double salary, Departments department) {
		this.empId = empId;
		this.empName = empName;
		this.role = role;
		this.salary = salary;
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employees [empId=" + empId + ", empName=" + empName + ", role=" + role + 
			   ", salary=" + salary + ", department=" + (department != null ? department.getDeptName() : "None") + "]";
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Departments getDepartment() {
		return department;
	}

	public void setDepartment(Departments department) {
		this.department = department;
	}
    
}