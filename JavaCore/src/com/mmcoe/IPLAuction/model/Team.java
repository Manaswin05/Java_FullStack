package com.mmcoe.IPLAuction.model;

/**
 * Model representing an IPL Team.
 */
public class Team {

    private int    teamId;
    private String teamName;
    private double budget;

    public Team() {}

    public Team(int teamId, String teamName, double budget) {
        this.teamId   = teamId;
        this.teamName = teamName;
        this.budget   = budget;
    }

    // ---------- Getters ----------

    public int    getTeamId()   { return teamId;   }
    public String getTeamName() { return teamName; }
    public double getBudget()   { return budget;   }

    // ---------- Setters ----------

    public void setTeamId(int teamId)       { this.teamId   = teamId;   }
    public void setTeamName(String teamName){ this.teamName = teamName; }
    public void setBudget(double budget)     { this.budget   = budget;   }

    @Override
    public String toString() {
        return String.format("Team[id=%d, name=%-15s, budget=%.1f Cr]",
                teamId, teamName, budget);
    }
}
