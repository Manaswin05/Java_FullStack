package com.mmcoe.Auction;
import java.util.ArrayList;
import java.util.Scanner;

public class Auction {

   private ArrayList<Player> players;
   private ArrayList<Team> teams;

   public Auction() {

       players = new ArrayList<>();
       teams = new ArrayList<>();

       players.add(new Player("Virat Kohli", "Batsman", 2));
       players.add(new Player("Jasprit Bumrah", "Bowler", 2));
       players.add(new Player("Rohit Sharma", "Batsman", 2));
       players.add(new Player("Hardik Pandya", "All-Rounder", 2));

       teams.add(new Team("CSK", 20));
       teams.add(new Team("MI", 20));
       teams.add(new Team("RCB", 20));
   }

   public void startAuction() {

       Scanner sc = new Scanner(System.in);

       for (Player player : players) {

           System.out.println("\n--------------------------------");
           System.out.println(player);

           System.out.println("Select Team");

           for (int i = 0; i < teams.size(); i++) {
               System.out.println((i + 1) + ". " + teams.get(i).getTeamName());
           }

           int choice = sc.nextInt();

           System.out.print("Enter Bid Amount (Cr): ");
           double bid = sc.nextDouble();

           teams.get(choice - 1).buyPlayer(player, bid);
       }

       System.out.println("\n========== AUCTION OVER ==========");

       for (Team t : teams) {
           t.showSquad();
       }

       sc.close();
   }
   public static void main(String[] args) {
		Auction auction = new Auction();
		auction.startAuction();
	}
}