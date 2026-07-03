package com.mmcoe.IPLAuction;

import com.mmcoe.IPLAuction.model.Player;
import com.mmcoe.IPLAuction.model.Team;
import com.mmcoe.IPLAuction.service.AuctionService;

import static com.mmcoe.IPLAuction.util.Validators.isNameValid;
import static com.mmcoe.IPLAuction.util.Validators.isBudgetValid;
import static com.mmcoe.IPLAuction.util.Validators.isBidValid;
import static com.mmcoe.IPLAuction.util.Validators.canAffordPlayer;

import java.util.List;

public class TestIPL {

	private static int passed = 0;
	private static int failed = 0;

	public static void main(String[] args) {

		banner("IPL AUCTION — ALL-IN-ONE TEST SUITE");

		AuctionService service = new AuctionService();

		section("1. Validator Tests");
		testValidators();

		section("2. Team Registration");

		String t1 = service.registerTeam("TestCSK", 100.0);
		assertContains("Register valid team", t1, "registered successfully");

		String t2 = service.registerTeam("", 100.0);
		assertContains("Empty team name rejected", t2, "ERROR");

		String t3 = service.registerTeam("TestMI", -5.0);
		assertContains("Negative budget rejected", t3, "ERROR");

		String t4 = service.registerTeam("TestMI", 0.0);
		assertContains("Zero budget rejected", t4, "ERROR");

		String t5 = service.registerTeam("TestRCB", 80.0);
		assertContains("Register second valid team", t5, "registered successfully");

		section("3. Player Registration");

		String p1 = service.registerPlayer("TestVirat", "Batsman", 20.0);
		assertContains("Register valid player", p1, "registered successfully");

		String p2 = service.registerPlayer("", "Bowler", 10.0);
		assertContains("Empty player name rejected", p2, "ERROR");

		String p3 = service.registerPlayer("TestBumrah", "", 15.0);
		assertContains("Empty role rejected", p3, "ERROR");

		String p4 = service.registerPlayer("TestDhoni", "Wicket-Keeper", 0.0);
		assertContains("Zero base price rejected", p4, "ERROR");

		String p5 = service.registerPlayer("TestDhoni", "Wicket-Keeper", 12.0);
		assertContains("Register second valid player", p5, "registered successfully");

		String p6 = service.registerPlayer("TestPandya", "All-Rounder", 18.0);
		assertContains("Register third valid player", p6, "registered successfully");

		section("4. Display All Teams & Players");

		List<Team> teams = service.getAllTeams();
		assertTrue("getAllTeams() returns at least 2 teams", teams.size() >= 2);
		System.out.println("  Teams in DB:");
		teams.forEach(t -> System.out.println("    " + t));

		List<Player> players = service.getAllPlayers();
		assertTrue("getAllPlayers() returns at least 3 players", players.size() >= 3);
		System.out.println("  Players in DB:");
		players.forEach(p -> System.out.println("    " + p));

		int cskId = findTeamId(teams, "TestCSK");
		int rcbId = findTeamId(teams, "TestRCB");

		List<Player> unsoldBefore = service.getUnsoldPlayers();
		int viratId = findPlayerId(unsoldBefore, "TestVirat");
		int dhoniId = findPlayerId(unsoldBefore, "TestDhoni");
		int pandyaId = findPlayerId(unsoldBefore, "TestPandya");

		assertTrue("TestCSK found in DB", cskId != -1);
		assertTrue("TestRCB found in DB", rcbId != -1);
		assertTrue("TestVirat found in DB", viratId != -1);
		assertTrue("TestDhoni found in DB", dhoniId != -1);
		assertTrue("TestPandya found in DB", pandyaId != -1);

		section("5. Auction — Happy Path");

		String sold1 = service.conductAuction(cskId, viratId, 22.0);
		assertContains("TestVirat sold to TestCSK", sold1, "SOLD");
		assertContains("Result includes team name", sold1, "TestCSK");
		System.out.println("  Result: " + sold1);

		section("6. Auction — Bid Below Base Price");

		String low = service.conductAuction(cskId, dhoniId, 5.0); // base = 12
		assertContains("Bid below base price rejected", low, "ERROR");
		assertContains("Error mentions base price", low, "below the base price");
		System.out.println("  Result: " + low);

		section("7. Auction — Team Cannot Afford");

		String expensive = service.conductAuction(rcbId, dhoniId, 200.0);
		assertContains("Unaffordable bid rejected", expensive, "ERROR");
		assertContains("Error mentions cannot afford", expensive, "cannot afford");
		System.out.println("  Result: " + expensive);

		section("8. Auction — Invalid Team ID");

		String badTeam = service.conductAuction(99999, dhoniId, 13.0);
		assertContains("Invalid team ID rejected", badTeam, "ERROR");
		assertContains("Error mentions team not found", badTeam, "Team not found");
		System.out.println("  Result: " + badTeam);

		section("9. Auction — Invalid Player ID");

		String badPlayer = service.conductAuction(cskId, 99999, 10.0);
		assertContains("Invalid player ID rejected", badPlayer, "ERROR");
		assertContains("Error mentions player not found", badPlayer, "Player not found");
		System.out.println("  Result: " + badPlayer);

		section("10. Auction — Re-bid on Already Sold Player");

		String reBid = service.conductAuction(cskId, viratId, 25.0);
		assertContains("Re-bid on sold player rejected", reBid, "ERROR");
		System.out.println("  Result: " + reBid);

		section("11. Team-Wise Players");

		List<Player> cskPlayers = service.getPlayersByTeam(cskId);
		assertTrue("TestCSK has at least 1 player", cskPlayers.size() >= 1);
		assertTrue("TestVirat is in TestCSK", cskPlayers.stream().anyMatch(p -> p.getPlayerId() == viratId));
		System.out.println("  TestCSK roster:");
		cskPlayers.forEach(p -> System.out.println("    " + p));

		section("12. Unsold Players");

		List<Player> unsoldAfter = service.getUnsoldPlayers();
		assertTrue("Unsold list does NOT contain TestVirat (sold)",
				unsoldAfter.stream().noneMatch(p -> p.getPlayerId() == viratId));
		assertTrue("Unsold list still contains TestDhoni",
				unsoldAfter.stream().anyMatch(p -> p.getPlayerId() == dhoniId));
		System.out.println("  Unsold players:");
		unsoldAfter.forEach(p -> System.out.println("    " + p));

		section("13. Leaderboard");

		List<String> board = service.getLeaderboard();
		assertTrue("Leaderboard has at least 2 rows", board.size() >= 2);
		System.out.println("  Leaderboard:");
		board.forEach(row -> System.out.println("    " + row));

		banner("TEST RESULTS");
		System.out.printf("  PASSED : %d%n", passed);
		System.out.printf("  FAILED : %d%n", failed);
		System.out.printf("  TOTAL  : %d%n", passed + failed);
		if (failed == 0) {
			System.out.println("\n  ALL TESTS PASSED ✓");
		} else {
			System.out.println("\n  SOME TESTS FAILED — see details above.");
		}
		System.out.println("=".repeat(50));
	}

	private static void testValidators() {
		// isNameValid
		assertTrue("isNameValid: normal name", isNameValid.test("Sachin"));
		assertFalse("isNameValid: empty string", isNameValid.test(""));
		assertFalse("isNameValid: blank string", isNameValid.test("   "));
		assertFalse("isNameValid: null", isNameValid.test(null));

		// isBudgetValid
		assertTrue("isBudgetValid: 50.0", isBudgetValid.test(50.0));
		assertFalse("isBudgetValid: 0.0", isBudgetValid.test(0.0));
		assertFalse("isBudgetValid: -10.0", isBudgetValid.test(-10.0));
		assertFalse("isBudgetValid: null", isBudgetValid.test(null));

		// isBidValid
		assertTrue("isBidValid: 5.0", isBidValid.test(5.0));
		assertFalse("isBidValid: 0.0", isBidValid.test(0.0));
		assertFalse("isBidValid: negative", isBidValid.test(-1.0));

		// canAffordPlayer
		assertTrue("canAffordPlayer: 100 >= 50", canAffordPlayer.test(100.0, 50.0));
		assertTrue("canAffordPlayer: exact match", canAffordPlayer.test(50.0, 50.0));
		assertFalse("canAffordPlayer: 30 < 50", canAffordPlayer.test(30.0, 50.0));
		assertFalse("canAffordPlayer: bid = 0", canAffordPlayer.test(100.0, 0.0));
	}

	private static int findTeamId(List<Team> teams, String name) {
		return teams.stream().filter(t -> t.getTeamName().equalsIgnoreCase(name)).mapToInt(Team::getTeamId).findFirst()
				.orElse(-1);
	}

	private static int findPlayerId(List<Player> players, String name) {
		return players.stream().filter(p -> p.getPlayerName().equalsIgnoreCase(name)).mapToInt(Player::getPlayerId)
				.findFirst().orElse(-1);
	}

	private static void assertContains(String testName, String actual, String expected) {
		if (actual != null && actual.contains(expected)) {
			pass(testName);
		} else {
			fail(testName, "Expected to contain \"" + expected + "\" but got: " + actual);
		}
	}

	private static void assertTrue(String testName, boolean condition) {
		if (condition) {
			pass(testName);
		} else {
			fail(testName, "Expected true but was false");
		}
	}

	private static void assertFalse(String testName, boolean condition) {
		if (!condition) {
			pass(testName);
		} else {
			fail(testName, "Expected false but was true");
		}
	}

	private static void pass(String testName) {
		passed++;
		System.out.printf("  [PASS] %s%n", testName);
	}

	private static void fail(String testName, String reason) {
		failed++;
		System.out.printf("  [FAIL] %s  →  %s%n", testName, reason);
	}

	private static void section(String title) {
		System.out.println("\n--- " + title + " ---");
	}

	private static void banner(String title) {
		System.out.println("\n" + "=".repeat(50));
		System.out.println("  " + title);
		System.out.println("=".repeat(50));
	}
}
