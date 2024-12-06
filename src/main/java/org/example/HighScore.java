package org.example;


 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.sql.Statement;

/**
 * A HighScore osztály kezeli a játékosok pontszámainak mentését
 * és frissítését egy SQLite adatbázisban.
 */
public class HighScore {

    private static final String DATABASE_URL = "jdbc:sqlite:highscore.db";

    // Adatbázis inicializálása
    public HighScore() {
        try (Connection conn = DriverManager.getConnection(DATABASE_URL)) {
            if (conn != null) {
                String sql = """
                    CREATE TABLE IF NOT EXISTS scores (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT NOT NULL,
                        wins INTEGER NOT NULL
                    )
                """;

                try (Statement stmt = conn.createStatement()) {
                    stmt.execute(sql);
                }
            }
        } catch (SQLException e) {
            System.out.println("Hiba történt az adatbázis inicializálásakor: " + e.getMessage());
        }
    }

    /**
     * Új játékos hozzáadása az adatbázishoz, vagy ha a játékos már létezik,
     * akkor frissíti a győzelmeinek számát.
     *
     * @param name  A játékos neve.
     * @param points A játékos által szerzett pontok száma.
     */
    public void addOrUpdatePlayer(String name, int points) {
        String selectSql = "SELECT wins FROM scores WHERE name = ?";
        String insertSql = "INSERT INTO scores (name, wins) VALUES (?, ?)";
        String updateSql = "UPDATE scores SET wins = ? WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL)) {
            // Ellenőrizzük, hogy a játékos létezik-e
            try (PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
                selectStmt.setString(1, name);
                ResultSet rs = selectStmt.executeQuery();

                if (rs.next()) {
                    // Ha létezik, frissítjük a pontszámát
                    int currentWins = rs.getInt("wins");
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                        updateStmt.setInt(1, currentWins + points);
                        updateStmt.setString(2, name);
                        updateStmt.executeUpdate();
                    }
                } else {
                    // Ha nem létezik, hozzáadjuk
                    try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                        insertStmt.setString(1, name);
                        insertStmt.setInt(2, points);
                        insertStmt.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Hiba történt a játékos adatainak mentésekor: " + e.getMessage());
        }
    }

    /**
     * Kiírja a legjobb pontszámokat az adatbázisból a konzolra.
     * A játékosokat a győzelmeik száma szerint csökkenő sorrendben jeleníti meg.
     */
    public void printHighScores() {
        String sql = "SELECT name, wins FROM scores ORDER BY wins DESC";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("High Score Tábla:");
            System.out.println("-------------------");

            while (rs.next()) {
                String name = rs.getString("name");
                int wins = rs.getInt("wins");
                System.out.println(name + " - " + wins + " győzelem");
            }
        } catch (SQLException e) {
            System.out.println("Hiba történt a pontszámok lekérdezésekor: " + e.getMessage());
        }
    }
}
