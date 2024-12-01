package org.example;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class JatekDiffblueTest {

    /**
     * Test {@link Jatek#indit()}.
     * <p>
     * Method under test: {@link Jatek#indit()}
     */
    @Test(testName = "Test indit()")
    @Ignore("TODO: Complete this test")
    public void testIndit() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange
        // TODO: Populate arranged inputs
        Jatek jatek = null;

        // Act
        jatek.indit();

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Test {@link Jatek#getGameState()}.
     * <p>
     * Method under test: {@link Jatek#getGameState()}
     */
    @Test(testName = "Test getGameState()")
    public void testGetGameState() {
        // Arrange and Act
        GameState actualGameState = (new Jatek("Jatekos1 Nev", "Jatekos2 Nev", true)).getGameState();

        // Assert
        assertEquals(actualGameState.getCurrentPlayerIndex(), 0);
        assertEquals(actualGameState.getPlayers().size(), 2);
        String[][] board = actualGameState.getBoard();
        assertEquals(board.length, 6);
        assertEquals((board[0]).length, 7);
        assertEquals((board[1]).length, 7);
        assertEquals((board[2]).length, 7);
        assertEquals((board[3]).length, 7);
        assertEquals((board[4]).length, 7);
        assertEquals((board[5]).length, 7);
    }

    /**
     * Test {@link Jatek#setGameState(GameState)}.
     * <ul>
     *   <li>When {@link GameState#GameState()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link Jatek#setGameState(GameState)}
     */
    @Test(testName = "Test setGameState(GameState); when GameState()")
    @Ignore("TODO: Complete this test")
    public void testSetGameState_whenGameState() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.charAt(int)" because "board[i][j]" is null
        //       at org.example.Tabla.setBoard(Tabla.java:40)
        //       at org.example.Jatek.setGameState(Jatek.java:111)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        Jatek jatek = new Jatek("Jatekos1 Nev", "Jatekos2 Nev", true);

        // Act
        jatek.setGameState(new GameState());
    }

    /**
     * Test {@link Jatek#Jatek(String, String, boolean)}.
     * <p>
     * Method under test: {@link Jatek#Jatek(String, String, boolean)}
     */
    @Test(testName = "Test new Jatek(String, String, boolean)")
    public void testNewJatek() {
        // Arrange, Act and Assert
        GameState gameState = (new Jatek("Jatekos1 Nev", "Jatekos2 Nev", true)).getGameState();
        assertEquals(gameState.getCurrentPlayerIndex(), 0);
        assertEquals(gameState.getPlayers().size(), 2);
        String[][] board = gameState.getBoard();
        assertEquals(board.length, 6);
        assertEquals((board[0]).length, 7);
        assertEquals((board[1]).length, 7);
        assertEquals((board[2]).length, 7);
        assertEquals((board[3]).length, 7);
        assertEquals((board[4]).length, 7);
        assertEquals((board[5]).length, 7);
    }
}
