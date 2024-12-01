package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * A játék állapotát reprezentáló osztály.
 * Tartalmazza a játékosok listáját, a táblázat állapotát, és az aktuális játékos indexét.
 */
public class GameState {
    private List<Jatekos> players;
    private String[][] board; // Játéktábla állapota
    private int currentPlayerIndex;

    /**
     * Alapértelmezett konstruktor, amely inicializálja az üres játék állapotot.
     */
    public GameState(List<Jatekos> players, String[][] board, int currentPlayerIndex) {
        this.players = players;
        this.board = board;
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public GameState() {
        this.players = new ArrayList<>();
        this.board = new String[6][7]; // Példa Connect-4 tábla
        this.currentPlayerIndex = 0;
    }

    /**
     * Visszaadja a játékosok listáját.
     *
     *
     *  @return A játékosok listája.
     */
    public List<Jatekos> getPlayers() {
        return players;
    }

    /**
     * Beállítja a játékosok listáját.
     *
     * @param players A játékosok listája.
     */
    public void setPlayers(List<Jatekos> players) {
        this.players = players;
    }

    /**
     * Visszaadja a játéktábla állapotát.
     *
     * @return A játéktábla állapota.
     */
    public String[][] getBoard() {
        return board;
    }

    /**
     * Beállítja a játéktábla állapotát.
     *
     * @param board Az új játéktábla állapot.
     */
    public void setBoard(String[][] board) {
        this.board = board;
    }

    /**
     * Visszaadja az aktuális játékos indexét.
     *
     * @return Az aktuális játékos indexe.
     */
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    /**
     * Beállítja az aktuális játékos indexét.
     *
     * @param currentPlayerIndex Az új játékos index.
     */
    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }
}
