package com.mmcoe.Auction;
import java.util.List;

public class Team {
	private String teamName;
	private double purse;
	private List<Player> squad;

	
	public Team(String teamName, double purse, List<Player> squad) {
		this.teamName = teamName;
		this.purse = purse;
		this.squad = squad;
	}
	public boolean buyPlayer(Player player, double bid) {
		if (bid <= purse) {
			purse -= bid;
			squad.add(player);
			System.out.println(player.getName() + " bought by " + teamName);
			return true;
		}
		System.out.println(teamName + " does not have enough purse.");
		return false;
	}
	public void showSquad() {
		System.out.println("\nTeam : " + teamName);
		System.out.println("Remaining Purse : " + purse + " Cr");
		for (Player p : squad) {
			System.out.println(p);
		}
	}
	public String getTeamName() {
		return teamName;
	}
}
