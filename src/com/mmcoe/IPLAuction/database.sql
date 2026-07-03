-- IPL Auction Database Setup
-- Run this script once before starting the application.

CREATE DATABASE IF NOT EXISTS iplauction;

USE iplauction;

-- Drop existing tables (respects FK order)
DROP TABLE IF EXISTS player;
DROP TABLE IF EXISTS team;

-- Teams table
CREATE TABLE team (
    team_id   INT          PRIMARY KEY AUTO_INCREMENT,
    team_name VARCHAR(50)  NOT NULL,
    budget    DOUBLE       NOT NULL
);

-- Players table
CREATE TABLE player (
    player_id   INT          PRIMARY KEY AUTO_INCREMENT,
    player_name VARCHAR(50)  NOT NULL,
    role        VARCHAR(30)  NOT NULL,
    base_price  DOUBLE       NOT NULL,
    sold_price  DOUBLE       DEFAULT 0,
    team_id     INT          DEFAULT NULL,      -- NULL = unsold
    FOREIGN KEY (team_id) REFERENCES team(team_id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);

-- Optional: seed some sample data
INSERT INTO team (team_name, budget) VALUES
    ('CSK',  100.0),
    ('MI',    80.0),
    ('RCB',  120.0);

INSERT INTO player (player_name, role, base_price) VALUES
    ('Virat Kohli',    'Batsman',         20.0),
    ('Jasprit Bumrah', 'Bowler',          15.0),
    ('Hardik Pandya',  'All-Rounder',     18.0),
    ('MS Dhoni',       'Wicket-Keeper',   12.0),
    ('Rohit Sharma',   'Batsman',         17.0);
