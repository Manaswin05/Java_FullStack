package com.mmcoe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity @Table(name = "players")
public class Player {
	@Id @Column(name = "jno")
	private int jerseyNo;
	@Column(name = "pname", length = 30)
	private String  name;
	@Column(name = "p_role", length = 20)
	private String role;
	@Column(name = "bid_amount")
	private double bidAmount;
	
	@ManyToOne
	@JoinColumn(name = "code")
	private Team team;
	
	public Player() {
	}
	public Player(int jerseyNo, String name, String role, double bidAmount) {
		this.jerseyNo = jerseyNo;
		this.name = name;
		this.role = role;
		this.bidAmount = bidAmount;
	}
	//Getters & Setters
	public int getJerseyNo() {
		return jerseyNo;
	}
	public String getName() {
		return name;
	}
	public String getRole() {
		return role;
	}
	public double getBidAmount() {
		return bidAmount;
	}
	public Team getTeam() {
		return team;
	}
	public void setJerseyNo(int jerseyNo) {
		this.jerseyNo = jerseyNo;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setBidAmount(double bidAmount) {
		this.bidAmount = bidAmount;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	@Override
	public String toString() {
		return "Player [jerseyNo=" + jerseyNo + ", name=" + name + ", role=" + role + ", bidAmount=" + bidAmount + "]";
	}
	
}
