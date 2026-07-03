package com.mmcoe.IPLAuction.dao;

import com.mmcoe.IPLAuction.db.DBConnection;
import com.mmcoe.IPLAuction.model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Data Access Object for Player.
 * Demonstrates Consumer (save/update) and Function (ResultSet mapping) functional interfaces.
 */
public class PlayerDAO {

    private final DBConnection db = DBConnection.getInstance();

    // Function<ResultSet, Player>: maps a DB row to a Player object
    private final Function<ResultSet, Player> rowMapper = rs -> {
        try {
            Player p = new Player();
            p.setPlayerId(rs.getInt("player_id"));
            p.setPlayerName(rs.getString("player_name"));
            p.setRole(rs.getString("role"));
            p.setBasePrice(rs.getDouble("base_price"));
            p.setSoldPrice(rs.getDouble("sold_price"));
            p.setTeamId(rs.getInt("team_id"));
            return p;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to map player row: " + e.getMessage(), e);
        }
    };

    // Consumer<Player>: inserts a new player record
    private final Consumer<Player> savePlayerConsumer = player -> {
        String sql = "INSERT INTO player (player_name, role, base_price, sold_price, team_id) "
                   + "VALUES (?, ?, ?, ?, ?)";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, player.getPlayerName());
            ps.setString(2, player.getRole());
            ps.setDouble(3, player.getBasePrice());
            ps.setDouble(4, player.getSoldPrice());
            // team_id 0 means unsold — store as NULL to satisfy FK constraint
            if (player.getTeamId() == 0) {
                ps.setNull(5, java.sql.Types.INTEGER);
            } else {
                ps.setInt(5, player.getTeamId());
            }
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save player: " + e.getMessage(), e);
        }
    };

    /** Insert a new player. */
    public void savePlayer(Player player) {
        savePlayerConsumer.accept(player);
    }

    /** Retrieve all players. */
    public List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM player ORDER BY player_id";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                players.add(rowMapper.apply(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve players: " + e.getMessage(), e);
        }
        return players;
    }

    /**
     * Retrieve only players who have NOT been sold yet (team_id = 0 or NULL).
     * Used for the auction and unsold players report.
     */
    public List<Player> getUnsoldPlayers() {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM player WHERE team_id IS NULL ORDER BY player_id";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                players.add(rowMapper.apply(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve unsold players: " + e.getMessage(), e);
        }
        return players;
    }

    /** Retrieve all players that belong to a specific team. */
    public List<Player> getPlayersByTeam(int teamId) {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM player WHERE team_id = ? ORDER BY player_id";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, teamId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    players.add(rowMapper.apply(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve players by team: " + e.getMessage(), e);
        }
        return players;
    }

    /**
     * Update a player's sold price and team assignment after a successful bid.
     */
    public void updatePlayer(Player player) {
        String sql = "UPDATE player SET sold_price = ?, team_id = ? WHERE player_id = ?";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, player.getSoldPrice());
            ps.setInt(2,    player.getTeamId());
            ps.setInt(3,    player.getPlayerId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to update player: " + e.getMessage(), e);
        }
    }
}
