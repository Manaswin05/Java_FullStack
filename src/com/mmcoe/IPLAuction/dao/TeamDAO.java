package com.mmcoe.IPLAuction.dao;

import com.mmcoe.IPLAuction.db.DBConnection;
import com.mmcoe.IPLAuction.model.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Data Access Object for Team.
 * Demonstrates Consumer (save/update) and Function (ResultSet mapping) functional interfaces.
 */
public class TeamDAO {

    private final DBConnection db = DBConnection.getInstance();

    // Consumer<Team>: persists a new team to the database
    private final Consumer<Team> saveTeamConsumer = team -> {
        String sql = "INSERT INTO team (team_name, budget) VALUES (?, ?)";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, team.getTeamName());
            ps.setDouble(2, team.getBudget());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save team: " + e.getMessage(), e);
        }
    };

    // Function<ResultSet, Team>: maps a ResultSet row to a Team object
    private final Function<ResultSet, Team> rowMapper = rs -> {
        try {
            Team t = new Team();
            t.setTeamId(rs.getInt("team_id"));
            t.setTeamName(rs.getString("team_name"));
            t.setBudget(rs.getDouble("budget"));
            return t;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to map team row: " + e.getMessage(), e);
        }
    };

    /** Insert a new team into the database. */
    public void saveTeam(Team team) {
        saveTeamConsumer.accept(team);
    }

    /** Retrieve all teams. */
    public List<Team> getAllTeams() {
        List<Team> teams = new ArrayList<>();
        String sql = "SELECT * FROM team ORDER BY team_id";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                teams.add(rowMapper.apply(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve teams: " + e.getMessage(), e);
        }
        return teams;
    }

    /** Fetch a single team by its ID. Returns null if not found. */
    public Team getTeamById(int teamId) {
        String sql = "SELECT * FROM team WHERE team_id = ?";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, teamId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rowMapper.apply(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch team: " + e.getMessage(), e);
        }
        return null;
    }

    /** Update a team's remaining budget after a purchase. */
    public void updateBudget(Team team) {
        String sql = "UPDATE team SET budget = ? WHERE team_id = ?";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, team.getBudget());
            ps.setInt(2, team.getTeamId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to update budget: " + e.getMessage(), e);
        }
    }

    /**
     * Leaderboard: returns all teams sorted by remaining budget descending,
     * along with the number of players each team has purchased.
     * Uses a SQL JOIN + GROUP BY + ORDER BY for aggregation.
     */
    public List<String> getLeaderboard() {
        List<String> rows = new ArrayList<>();
        String sql = """
                SELECT t.team_id,
                       t.team_name,
                       t.budget,
                       COUNT(p.player_id) AS player_count
                FROM   team t
                LEFT JOIN player p ON p.team_id = t.team_id
                GROUP BY t.team_id, t.team_name, t.budget
                ORDER BY t.budget DESC
                """;
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            int rank = 1;
            while (rs.next()) {
                rows.add(String.format("#%d  %-15s  Budget: %6.1f Cr  Players Bought: %d",
                        rank++,
                        rs.getString("team_name"),
                        rs.getDouble("budget"),
                        rs.getInt("player_count")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch leaderboard: " + e.getMessage(), e);
        }
        return rows;
    }
}
