package com.mmcoe.IPLAuction.factory;

import com.mmcoe.IPLAuction.model.Player;

import java.util.function.BiFunction;

public class PlayerFactory {

    // BiFunction: (playerName, role) -> Player
    private static final BiFunction<String, String, Player> playerCreator = (name, role) -> {
        Player player = new Player();
        player.setPlayerName(name.trim());
        player.setRole(role.trim());
        return player;
    };

    public static Player createPlayer(String name, String role, double basePrice) {
        Player player = playerCreator.apply(name, role);
        player.setBasePrice(basePrice);
        return player;
    }

    private PlayerFactory() {} 
}
