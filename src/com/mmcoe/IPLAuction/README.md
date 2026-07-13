# IPL Auction System

A menu-driven console application built in **Java** that simulates an IPL player auction. Teams bid on players, budgets are tracked, and results are persisted in a **MySQL** database.

---

## Project Structure

```
com.mmcoe.IPLAuction/
│
├── Main.java                        ← Entry point, menu-driven UI
├── TestIPL.java                     ← All-in-one manual test suite (run to verify everything)
│
├── model/
│   ├── Player.java                  ← Player POJO (id, name, role, basePrice, soldPrice, teamId)
│   └── Team.java                    ← Team POJO (id, name, budget)
│
├── dao/
│   ├── PlayerDAO.java               ← DB operations for Player (save, get, update)
│   └── TeamDAO.java                 ← DB operations for Team (save, get, update, leaderboard)
│
├── db/
│   └── DBConnection.java            ← Singleton DB connection via Supplier<Connection>
│
├── factory/
│   └── PlayerFactory.java           ← Creates Player objects using BiFunction
│
├── service/
│   └── AuctionService.java          ← Core business logic — auction orchestration
│
├── util/
│   └── Validators.java              ← Reusable Predicate / BiPredicate validators
│
└── database.sql                     ← MySQL schema + seed data — run this first!
```

---

## How Each File Works

### `Main.java`
The application entry point. Runs a `while` loop presenting a numbered menu. Each option delegates to a private handler method which calls `AuctionService`. Input is read safely with `try/catch` to handle non-numeric entries.

| Menu Option | What it does |
|---|---|
| 1 Register Team | Calls `service.registerTeam(name, budget)` |
| 2 Register Player | Calls `service.registerPlayer(name, role, basePrice)` |
| 3 Display All Teams | Lists all teams with remaining budget |
| 4 Display All Players | Lists all players with sale status |
| 5 Start Auction | Picks a team + player, enters bid → sold or rejected |
| 6 Team-Wise Players | Shows every player bought by a chosen team |
| 7 Unsold Players | Lists players with no team yet |
| 8 Leaderboard | Ranks teams by remaining budget (SQL aggregation) |
| 9 Exit | Closes scanner and exits |

---

### `model/Player.java`
Plain Java object (POJO) representing a cricket player.

| Field | Type | Meaning |
|---|---|---|
| `playerId` | int | Auto-generated PK from DB |
| `playerName` | String | Full name |
| `role` | String | Batsman / Bowler / All-Rounder / Wicket-Keeper |
| `basePrice` | double | Minimum bid in Crores |
| `soldPrice` | double | Actual amount paid (0 if unsold) |
| `teamId` | int | 0 = unsold, otherwise foreign key to team |

---

### `model/Team.java`
POJO representing an IPL team.

| Field | Type | Meaning |
|---|---|---|
| `teamId` | int | Auto-generated PK from DB |
| `teamName` | String | Team name (e.g., CSK, MI) |
| `budget` | double | Remaining budget in Crores |

---

### `dao/PlayerDAO.java`
Handles all SQL for the `player` table. Demonstrates Java functional interfaces:

- **`Function<ResultSet, Player> rowMapper`** — maps a DB row to a `Player` object.
- **`Consumer<Player> savePlayerConsumer`** — inserts a new player; stores `team_id` as SQL NULL when unsold.

Key methods:
| Method | SQL | Purpose |
|---|---|---|
| `savePlayer(Player)` | INSERT | Register a new player |
| `getAllPlayers()` | SELECT * | All players |
| `getUnsoldPlayers()` | WHERE team_id IS NULL | Players available for auction |
| `getPlayersByTeam(int)` | WHERE team_id = ? | Players bought by a team |
| `updatePlayer(Player)` | UPDATE sold_price + team_id | Mark a player as sold |

---

### `dao/TeamDAO.java`
Handles all SQL for the `team` table. Also uses functional interfaces:

- **`Consumer<Team> saveTeamConsumer`** — inserts a new team.
- **`Function<ResultSet, Team> rowMapper`** — maps a DB row to a `Team` object.

Key methods:
| Method | SQL | Purpose |
|---|---|---|
| `saveTeam(Team)` | INSERT | Register a new team |
| `getAllTeams()` | SELECT * | All teams |
| `getTeamById(int)` | SELECT WHERE team_id = ? | Fetch one team for auction |
| `updateBudget(Team)` | UPDATE budget | Deduct bid from team budget |
| `getLeaderboard()` | JOIN + GROUP BY + ORDER BY | Ranked team standings |

---

### `db/DBConnection.java`
Implements the **Singleton** pattern — only one `DBConnection` instance is created per run.

- Uses a **`Supplier<Connection>`** lambda to open a fresh JDBC connection on each call to `getConnection()`.
- Configure your database credentials here:
  ```java
  private static final String URL  = "jdbc:mysql://localhost:3306/iplauction";
  private static final String USER = "root";
  private static final String PASS = "your_password";
  ```

> ⚠️ Do not commit real passwords to version control. Move credentials to a `.properties` file or environment variable in production.

---

### `factory/PlayerFactory.java`
Implements the **Factory** pattern using a **`BiFunction<String, String, Player>`** lambda.

```java
PlayerFactory.createPlayer("Virat Kohli", "Batsman", 20.0);
```

Trims whitespace from name and role before creating the object. Prevents instantiation via a private constructor.

---

### `service/AuctionService.java`
Orchestrates all business logic. The main auction flow uses a **`BiConsumer<Team, Player>`**:

```java
// Deducts bid from team budget AND assigns player to team — atomically in-memory
private final BiConsumer<Team, Player> auctionPlayer = (team, player) -> {
    team.setBudget(team.getBudget() - player.getSoldPrice());
    player.setTeamId(team.getTeamId());
    playerDAO.updatePlayer(player);
    teamDAO.updateBudget(team);
};
```

`conductAuction()` validation steps (in order):
1. Team exists?
2. Player exists and is unsold?
3. Bid amount is positive?
4. Bid ≥ player's base price?
5. Team can afford the bid?

Only if all pass does it call `auctionPlayer.accept(team, player)`.

---

### `util/Validators.java`
Stateless utility class with reusable lambdas — no instantiation allowed.

| Validator | Type | Checks |
|---|---|---|
| `isNameValid` | `Predicate<String>` | Not null, not blank |
| `isBudgetValid` | `Predicate<Double>` | Not null, > 0 |
| `isBidValid` | `Predicate<Double>` | Not null, > 0 |
| `canAffordPlayer` | `BiPredicate<Double, Double>` | teamBudget ≥ bidAmount > 0 |

---

### `database.sql`
MySQL setup script. Run **once** before the first application launch.

```sql
-- Creates the database
CREATE DATABASE IF NOT EXISTS iplauction;

-- Creates team and player tables with FK relationship
-- Seeds 3 teams (CSK, MI, RCB) and 5 players
```

`team_id` in the `player` table is `NULL` for unsold players (satisfies the foreign-key constraint without needing a dummy row).

---

### `TestIPL.java`
All-in-one manual test suite — no JUnit required. Run it as a plain Java Application to verify every feature end-to-end against the live database.

**13 test sections, ~38 individual assertions:**

| # | Section | What's verified |
|---|---|---|
| 1 | Validators | All 4 validators with valid, invalid, and null inputs — no DB needed |
| 2 | Team Registration | Happy path · empty name · negative budget · zero budget |
| 3 | Player Registration | Happy path · empty name · empty role · zero base price |
| 4 | List All Teams/Players | DB returns at least the rows just inserted |
| 5 | Auction — Happy Path | Player sold, result contains "SOLD" and team name |
| 6 | Bid Below Base Price | Returns error with "below the base price" message |
| 7 | Team Can't Afford | 200 Cr bid on 80 Cr budget → "cannot afford" error |
| 8 | Invalid Team ID | ID 99999 → "Team not found" error |
| 9 | Invalid Player ID | ID 99999 → "Player not found" error |
| 10 | Re-bid on Sold Player | Already-sold player cannot be bid on again |
| 11 | Team-Wise Players | Sold player appears in correct team's roster |
| 12 | Unsold Players | Sold player removed from unsold list; others remain |
| 13 | Leaderboard | Returns ≥ 2 rows ranked by remaining budget |

**Sample output:**
```
==================================================
  IPL AUCTION — ALL-IN-ONE TEST SUITE
==================================================

--- 1. Validator Tests ---
  [PASS] isNameValid: normal name
  [PASS] isNameValid: empty string
  ...
--- 5. Auction — Happy Path ---
  Result: SOLD! TestVirat → TestCSK for 22.0 Cr. Remaining budget: 78.0 Cr.
  [PASS] TestVirat sold to TestCSK
  ...

==================================================
  TEST RESULTS
==================================================
  PASSED : 38
  FAILED : 0
  TOTAL  : 38

  ALL TESTS PASSED ✓
```

> Each run inserts its own test data (TestCSK, TestRCB, TestVirat, etc.) so it works alongside any existing seed data without conflict.

---

## Setup & Run

### Prerequisites
- Java 17+ (uses text blocks and switch expressions)
- MySQL 8.x running locally
- MySQL Connector/J JAR on the classpath

### Step 1 — Set up the database
```sql
-- In MySQL Workbench or CLI:
source database.sql
```

### Step 2 — Add MySQL Connector to classpath
Download `mysql-connector-j-8.x.x.jar` and add it to your Eclipse project:
> Right-click project → Build Path → Add External JARs

### Step 3 — Update credentials
Edit `DBConnection.java` and set your MySQL username and password.

### Step 4 — Run the test suite first
Run `TestIPL.java` as a Java Application to confirm everything is wired up correctly before using the interactive app.

```
Expected output:  ALL TESTS PASSED ✓
```

If any test fails, the output shows exactly which assertion failed and what value was returned.

### Step 5 — Run the interactive app
Run `Main.java` as a Java Application in Eclipse (or via `javac` + `java` from the terminal) for the full menu-driven experience.

---

## Java Concepts Demonstrated

| Concept | Where Used |
|---|---|
| **Singleton pattern** | `DBConnection.getInstance()` |
| **Factory pattern** | `PlayerFactory.createPlayer()` |
| **Functional interfaces** | `Predicate`, `BiPredicate`, `Consumer`, `BiConsumer`, `Function`, `BiFunction`, `Supplier` |
| **Lambda expressions** | Throughout DAO, service, factory, validators |
| **JDBC with PreparedStatement** | All DAO classes |
| **Stream API** | `findUnsoldPlayerById()` in `AuctionService`; ID lookups in `TestIPL` |
| **SQL aggregation** | `getLeaderboard()` — JOIN + GROUP BY + ORDER BY |
| **Exception handling** | RuntimeException wrapping SQLExceptions throughout |
| **Manual test assertions** | `TestIPL` — custom `assertTrue`, `assertFalse`, `assertContains` helpers |

---

## Known Limitations / Possible Enhancements

- `DBConnection` opens a new physical connection per query — a connection pool (HikariCP) would be better for production.
- No re-auction support: once a player is sold they cannot be put back.
- Credentials are hardcoded — should be externalised to `config.properties` or environment variables.
