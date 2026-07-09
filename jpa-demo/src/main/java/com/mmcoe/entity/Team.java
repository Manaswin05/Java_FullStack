package com.mmcoe.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity@Table(name="teams")
public class Team {
	@Id @Column(length = 5)
	private String code;
	@Column(name = "tname", length = 30)
	private String teamName;
	@Column(length = 30)
	private String owner;
	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	private Set<Player> players = new HashSet<Player>();
	public Team() {
	}
	public Team(String code, String teamName, String owner) {
		this.code = code;
		this.teamName = teamName;
		this.owner = owner;
	}
	@Override
	public String toString() {
		return "Team [code=" + code + ", teamName=" + teamName + ", owner=" + owner + "]";
	}
	public String getCode() {
		return code;
	}
	public String getTeamName() {
		return teamName;
	}
	public String getOwner() {
		return owner;
	}
	public Set<Player> getPlayers() {
		return players;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public void setPlayers(Set<Player> players) {
		this.players = players;
	}
	
}
