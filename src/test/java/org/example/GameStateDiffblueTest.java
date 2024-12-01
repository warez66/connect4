package org.example;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

public class GameStateDiffblueTest {
    /**
     * Test getters and setters.
     * <p>
     * Methods under test:
     * <ul>
     *   <li>{@link GameState#GameState(List, String[][], int)}
     *   <li>{@link GameState#setBoard(String[][])}
     *   <li>{@link GameState#setCurrentPlayerIndex(int)}
     *   <li>{@link GameState#setPlayers(List)}
     *   <li>{@link GameState#getBoard()}
     *   <li>{@link GameState#getCurrentPlayerIndex()}
     *   <li>{@link GameState#getPlayers()}
     * </ul>
     */
    @Test(testName = "Test getters and setters")
    public void testGettersAndSetters() {
        // Arrange and Act
        GameState actualGameState = new GameState(new ArrayList<>(), new String[][]{new String[]{"Board"}}, 1);
        String[][] board = new String[][]{new String[]{"Board"}};
        actualGameState.setBoard(board);
        actualGameState.setCurrentPlayerIndex(1);
        ArrayList<Jatekos> players = new ArrayList<>();
        actualGameState.setPlayers(players);
        String[][] actualBoard = actualGameState.getBoard();
        int actualCurrentPlayerIndex = actualGameState.getCurrentPlayerIndex();
        List<Jatekos> actualPlayers = actualGameState.getPlayers();

        // Assert that nothing has changed
        assertEquals(actualCurrentPlayerIndex, 1);
        assertEquals(actualBoard.length, 1);
        assertTrue(actualPlayers.isEmpty());
        assertSame(actualPlayers, players);
        assertSame(actualBoard, board);
        assertEquals(actualBoard[0], new String[]{"Board"});
    }

    /**
     * Test {@link GameState#GameState()}.
     * <p>
     * Method under test: {@link GameState#GameState()}
     */
    @Test(testName = "Test new GameState()")
    public void testNewGameState() {
        // Arrange and Act
        GameState actualGameState = new GameState();

        // Assert
        assertEquals(actualGameState.getCurrentPlayerIndex(), 0);
        String[][] board = actualGameState.getBoard();
        assertEquals(board.length, 6);
        assertEquals((board[0]).length, 7);
        assertEquals((board[1]).length, 7);
        assertEquals((board[2]).length, 7);
        assertEquals((board[3]).length, 7);
        assertEquals((board[4]).length, 7);
        assertEquals((board[5]).length, 7);
        assertTrue(actualGameState.getPlayers().isEmpty());
    }
}
