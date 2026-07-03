package com.mmcoe.IPLAuction.service;

import com.mmcoe.IPLAuction.dao.PlayerDAO;
import com.mmcoe.IPLAuction.dao.TeamDAO;
import com.mmcoe.IPLAuction.factory.PlayerFactory;
import com.mmcoe.IPLAuction.model.Player;
import com.mmcoe.IPLAuction.model.Team;
import com.mmcoe.IPLAuction.util.Validators;

import java.util.List;
import java.util.function.BiConsumer;


public class AuctionService {

    private final TeamDAO   teamDAO   = new TeamDAO();
    private final PlayerDAO playerDAO = new PlayerDAO();

    private final BiConsumer<Team, Player> auctionPlayer = (team, player) -> {
        team.setBudget(team.getBudget() - player.getSoldPrice());
        player.setTeamId(team.getTeamId());
        playerDAO.updatePlayer(player);
        teamDAO.updateBudget(team);
    };

    public String registerTeam(String name, double budget) {
        if (!Validators.isNameValid.test(name)) {
            return "ERROR: Team name cannot be empty.";
        }
        if (!Validators.isBudgetValid.test(budget)) {
            return "ERROR: Budget must be greater than 0.";
        }
        Team team = new Team();
        team.setTeamName(name.trim());
        team.setBudget(budget);
        teamDAO.saveTeam(team);
        return "Team '" + name.trim() + "' registered successfully!";
    }

    public List<Team> getAllTeams() {
        return teamDAO.getAllTeams();
    }

    public String registerPlayer(String name, String role, double basePrice) {
        if (!Validators.isNameValid.test(name)) {
            return "ERROR: Player name cannot be empty.";
        }
        if (!Validators.isNameValid.test(role)) {
            return "ERROR: Role cannot be empty.";
        }
        if (!Validators.isBudgetValid.test(basePrice)) {
            return "ERROR: Base price must be greater than 0.";
        }
        Player player = PlayerFactory.createPlayer(name, role, basePrice);
        playerDAO.savePlayer(player);
        return "Player '" + name.trim() + "' registered successfully!";
    }

    public List<Player> getAllPlayers() {
        return playerDAO.getAllPlayers();
    }

    public List<Player> getUnsoldPlayers() {
        return playerDAO.getUnsoldPlayers();
    }

    public List<Player> getPlayersByTeam(int teamId) {
        return playerDAO.getPlayersByTeam(teamId);
    }

    public String conductAuction(int teamId, int playerId, double bidAmount) {

        Team   team   = teamDAO.getTeamById(teamId);
        Player player = findUnsoldPlayerById(playerId);

        if (team == null) {
            return "ERROR: Team not found.";
        }
        if (player == null) {
            return "ERROR: Player not found or already sold.";
        }
        if (!Validators.isBidValid.test(bidAmount)) {
            return "ERROR: Bid amount must be positive.";
        }
        if (bidAmount < player.getBasePrice()) {
            return String.format("ERROR: Bid %.1f Cr is below the base price of %.1f Cr.",
                    bidAmount, player.getBasePrice());
        }
        if (!Validators.canAffordPlayer.test(team.getBudget(), bidAmount)) {
            return String.format("ERROR: %s cannot afford %.1f Cr. Remaining budget: %.1f Cr.",
                    team.getTeamName(), bidAmount, team.getBudget());
        }

        player.setSoldPrice(bidAmount);
        auctionPlayer.accept(team, player);   // BiConsumer in action — also deducts from team.budget

        return String.format(
                "SOLD! %s → %s for %.1f Cr. Remaining budget: %.1f Cr.",
                player.getPlayerName(),
                team.getTeamName(),
                bidAmount,
                team.getBudget());   // budget already updated by auctionPlayer
    }

    public List<String> getLeaderboard() {
        return teamDAO.getLeaderboard();
    }

    private Player findUnsoldPlayerById(int playerId) {
        return playerDAO.getUnsoldPlayers()
                .stream()
                .filter(p -> p.getPlayerId() == playerId)
                .findFirst()
                .orElse(null);
    }
}