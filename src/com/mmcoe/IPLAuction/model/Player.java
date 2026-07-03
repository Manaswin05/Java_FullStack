package com.mmcoe.IPLAuction.model;

public class Player {

    private int    playerId;
    private String playerName;
    private String role;
    private double basePrice;
    private double soldPrice;
    private int    teamId;  

    public Player() {}

    public Player(String playerName, String role, double basePrice) {
        this.playerName = playerName;
        this.role       = role;
        this.basePrice  = basePrice;
    }

    // ---------- Getters ----------

    public int    getPlayerId()   { return playerId;   }
    public String getPlayerName() { return playerName; }
    public String getRole()       { return role;       }
    public double getBasePrice()  { return basePrice;  }
    public double getSoldPrice()  { return soldPrice;  }
    public int    getTeamId()     { return teamId;     }

    // ---------- Setters ----------

    public void setPlayerId(int playerId)       { this.playerId   = playerId;   }
    public void setPlayerName(String playerName){ this.playerName = playerName; }
    public void setRole(String role)             { this.role       = role;       }
    public void setBasePrice(double basePrice)   { this.basePrice  = basePrice;  }
    public void setSoldPrice(double soldPrice)   { this.soldPrice  = soldPrice;  }
    public void setTeamId(int teamId)            { this.teamId     = teamId;     }

    @Override
    public String toString() {
        return String.format("Player[id=%d, name=%-20s, role=%-15s, base=%.1f Cr, sold=%.1f Cr, teamId=%d]",
                playerId, playerName, role, basePrice, soldPrice, teamId);
    }
}
