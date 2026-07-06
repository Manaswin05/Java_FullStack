package com.mmcoe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cust")
public class Customer {

    @Id
    @Column(name = "cust_id")
    private int custId;

    @Column(name = "cname", length = 30)
    private String custName;

    private String city;

    public Customer() {
    }

    public Customer(int custId, String custName, String city) {
        this.custId = custId;
        this.custName = custName;
        this.city = city;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Customer [custId=" + custId
                + ", custName=" + custName
                + ", city=" + city + "]";
    }
}