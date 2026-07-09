package com.mmcoe.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.mmcoe.dao.TeamPlayerDao;
import com.mmcoe.entity.Player;
import com.mmcoe.entity.Team;

public class testPlayerTeam {

	private static TeamPlayerDao pdao;

	@BeforeAll
	public static void init() {
		pdao = new TeamPlayerDao();
	}

	@Test
	public void testSaveTeam() {
		Team t = new Team("KKR","Kolkata Knight Riders","Shah Rukh Khan");
		Team savedTeam = pdao.saveTeam(t);
		assertNotNull(savedTeam);
		System.out.println("Team Saved Successfully");
		System.out.println(savedTeam);
	}

	@Test
	public void testSavePlayer() {
		Player p = new Player(10,"Gautam Gambhir","Batsman",80000000);
		Player savedPlayer = pdao.savePlayer(p, "KKR");
		assertNotNull(savedPlayer);
		System.out.println("Player Saved Successfully");
		System.out.println(savedPlayer);
	}

	@Test
	public void testFindByRole() {
		List<Player> players = pdao.findByRole("Batsman");
		assertNotNull(players);
		System.out.println("Players with role 'Batsman':");
		players.forEach(System.out::println);
	}

	@Test
	public void testFindBidAmountRange() {
		List<Player> players = pdao.findBidAmountRange(100000000, 200000000);
		assertNotNull(players);
		System.out.println("Players with bid amount between 100M to 200M:");
		players.forEach(System.out::println);
	}

}