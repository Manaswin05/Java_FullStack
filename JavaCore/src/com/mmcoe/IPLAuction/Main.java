package com.mmcoe.IPLAuction;

import com.mmcoe.IPLAuction.model.Player;
import com.mmcoe.IPLAuction.model.Team;
import com.mmcoe.IPLAuction.service.AuctionService;

import java.util.List;
import java.util.Scanner;

/**
 * Entry point — menu-driven IPL Auction application.
 */
public class Main {

    private static final AuctionService service = new AuctionService();
    private static final Scanner        scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("================================================");
        System.out.println("          WELCOME TO IPL AUCTION SYSTEM         ");
        System.out.println("================================================");

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt("Enter your choice");
            switch (choice) {
                case 1  -> registerTeam();
                case 2  -> registerPlayer();
                case 3  -> displayTeams();
                case 4  -> displayPlayers();
                case 5  -> startAuction();
                case 6  -> teamWisePlayers();
                case 7  -> displayUnsoldPlayers();
                case 8  -> displayLeaderboard();
                case 9  -> { System.out.println("\nThank you. Goodbye!"); running = false; }
                default -> System.out.println("  Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    // -----------------------------------------------------------------------
    // Menu
    // -----------------------------------------------------------------------

    private static void printMainMenu() {
        System.out.println();
        System.out.println("========== IPL AUCTION MENU ==========");
        System.out.println("  1. Register Team");
        System.out.println("  2. Register Player");
        System.out.println("  3. Display All Teams");
        System.out.println("  4. Display All Players");
        System.out.println("  5. Start Auction");
        System.out.println("  6. Team-Wise Players");
        System.out.println("  7. Unsold Players");
        System.out.println("  8. Leaderboard");
        System.out.println("  9. Exit");
        System.out.println("======================================");
    }

    // -----------------------------------------------------------------------
    // Option handlers
    // -----------------------------------------------------------------------

    private static void registerTeam() {
        System.out.println("\n--- Register Team ---");
        String name   = readString("Enter team name");
        double budget = readDouble("Enter budget (Crores)");
        System.out.println(service.registerTeam(name, budget));
    }

    private static void registerPlayer() {
        System.out.println("\n--- Register Player ---");
        String name      = readString("Enter player name");
        String role      = readString("Enter role (Batsman / Bowler / All-Rounder / Wicket-Keeper)");
        double basePrice = readDouble("Enter base price (Crores)");
        System.out.println(service.registerPlayer(name, role, basePrice));
    }

    private static void displayTeams() {
        System.out.println("\n--- All Teams ---");
        List<Team> teams = service.getAllTeams();
        if (teams.isEmpty()) {
            System.out.println("  No teams registered yet.");
            return;
        }
        teams.forEach(t -> System.out.printf("  [%d] %-20s  Budget: %.1f Cr%n",
                t.getTeamId(), t.getTeamName(), t.getBudget()));
    }

    private static void displayPlayers() {
        System.out.println("\n--- All Players ---");
        List<Player> players = service.getAllPlayers();
        if (players.isEmpty()) {
            System.out.println("  No players registered yet.");
            return;
        }
        printPlayerTable(players);
    }

    private static void startAuction() {
        System.out.println("\n--- Start Auction ---");

        // Show available teams
        List<Team> teams = service.getAllTeams();
        if (teams.isEmpty()) { System.out.println("  No teams registered. Please register teams first."); return; }

        // Show unsold players
        List<Player> unsold = service.getUnsoldPlayers();
        if (unsold.isEmpty()) { System.out.println("  No unsold players available."); return; }

        System.out.println("\nAvailable Teams:");
        teams.forEach(t -> System.out.printf("  [%d] %-20s  Budget: %.1f Cr%n",
                t.getTeamId(), t.getTeamName(), t.getBudget()));

        int teamId = readInt("Choose Team ID");

        System.out.println("\nAvailable Players:");
        printPlayerTable(unsold);

        int playerId = readInt("Choose Player ID");
        double bid   = readDouble("Enter Bid Amount (Crores)");

        String result = service.conductAuction(teamId, playerId, bid);
        System.out.println("\n  >> " + result);
    }

    private static void teamWisePlayers() {
        System.out.println("\n--- Team-Wise Players ---");
        List<Team> teams = service.getAllTeams();
        if (teams.isEmpty()) { System.out.println("  No teams registered."); return; }

        teams.forEach(t -> System.out.printf("  [%d] %s%n", t.getTeamId(), t.getTeamName()));
        int teamId = readInt("Enter Team ID");

        List<Player> players = service.getPlayersByTeam(teamId);
        if (players.isEmpty()) {
            System.out.println("  No players found for this team.");
        } else {
            printPlayerTable(players);
        }
    }

    private static void displayUnsoldPlayers() {
        System.out.println("\n--- Unsold Players ---");
        List<Player> unsold = service.getUnsoldPlayers();
        if (unsold.isEmpty()) {
            System.out.println("  All players have been sold!");
        } else {
            printPlayerTable(unsold);
        }
    }

    private static void displayLeaderboard() {
        System.out.println("\n--- Leaderboard (by Remaining Budget) ---");
        List<String> rows = service.getLeaderboard();
        if (rows.isEmpty()) {
            System.out.println("  No data available.");
        } else {
            rows.forEach(row -> System.out.println("  " + row));
        }
    }

    // -----------------------------------------------------------------------
    // Display helper
    // -----------------------------------------------------------------------

    private static void printPlayerTable(List<Player> players) {
        System.out.printf("  %-5s %-22s %-18s %-12s %-12s %-8s%n",
                "ID", "Name", "Role", "Base (Cr)", "Sold (Cr)", "Team ID");
        System.out.println("  " + "-".repeat(80));
        players.forEach(p -> System.out.printf("  %-5d %-22s %-18s %-12.1f %-12.1f %-8d%n",
                p.getPlayerId(), p.getPlayerName(), p.getRole(),
                p.getBasePrice(), p.getSoldPrice(), p.getTeamId()));
    }

    // -----------------------------------------------------------------------
    // Input helpers
    // -----------------------------------------------------------------------

    private static int readInt(String prompt) {
        while (true) {
            System.out.print("  " + prompt + ": ");
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  Please enter a valid integer.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print("  " + prompt + ": ");
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  Please enter a valid number.");
            }
        }
    }

    private static String readString(String prompt) {
        System.out.print("  " + prompt + ": ");
        return scanner.nextLine();
    }
}
