package com.mmcoe.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "departments")
public class Departments {
	@Id
	@Column(name = "deptNo")
	private int deptNo;

    @Column(name = "deptName", length = 50, nullable = false)
    private String deptName;

    @Column(name = "deptLocation", length = 100, nullable = false)
    private String deptLocation;

    @OneToMany(mappedBy = "department")
    private List<Employees> employees;
    
    public Departments() {
    }
    
    public Departments(int deptNo, String deptName, String deptLocation) {
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.deptLocation = deptLocation;
	}

	@Override
	public String toString() {
		return "Departments [deptNo=" + deptNo + ", deptName=" + deptName + ", deptLocation=" + deptLocation + "]";
	}

	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptLocation() {
		return deptLocation;
	}

	public void setDeptLocation(String deptLocation) {
		this.deptLocation = deptLocation;
	}

	public List<Employees> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employees> employees) {
		this.employees = employees;
	}
}
