package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    @Test
    void testGameStateInitialization() {
        GameState gameState = new GameState();
        assertNotNull(gameState.getPlayers());
        assertNotNull(gameState.getBoard());
        assertEquals(0, gameState.getCurrentPlayerIndex());
    }

    @Test
    void testSetAndGetPlayers() {
        GameState gameState = new GameState();
        Jatekos player1 = new Jatekos("Player1", "R", false);
        Jatekos player2 = new Jatekos("Player2", "Y", false);

        gameState.setPlayers(List.of(player1, player2));
        assertEquals(2, gameState.getPlayers().size());
    }

    @Test
    void testSetAndGetBoard() {
        String[][] expectedBoard = {
                {".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", "."},
                {"Y", "R", ".", ".", ".", ".", "."}
        };


        GameState gameState = new GameState();
        gameState.setBoard(expectedBoard);
        String[][] actualBoard = gameState.getBoard();

        assertArrayEquals(expectedBoard, actualBoard); // Ellenőrizd az elvárt és a tényleges tábla egyezését
    }

}
