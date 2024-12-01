package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class TablaDiffblueTest {
    /**
     * Test {@link Tabla#getBoard()}.
     * <p>
     * Method under test: {@link Tabla#getBoard()}
     */
    @Test(testName = "Test getBoard()")
    public void testGetBoard() {
        // Arrange and Act
        String[][] actualBoard = (new Tabla(1, 1)).getBoard();

        // Assert
        assertEquals(actualBoard.length, 1);
        assertEquals(actualBoard[0], new String[]{"."});
    }

    /**
     * Test {@link Tabla#getErtek(int, int)}.
     * <ul>
     *   <li>Given {@link Tabla#Tabla(int, int)} with sorok is two and oszlopok is
     * two.</li>
     *   <li>Then return {@code .}.</li>
     * </ul>
     * <p>
     * Method under test: {@link Tabla#getErtek(int, int)}
     */
    @Test(testName = "Test getErtek(int, int); given Tabla(int, int) with sorok is two and oszlopok is two; then return '.'")
    public void testGetErtek_givenTablaWithSorokIsTwoAndOszlopokIsTwo_thenReturnDot() {
        // Arrange, Act and Assert
        assertEquals((new Tabla(2, 2)).getErtek(1, 1), '.');
    }

    /**
     * Test {@link Tabla#isFull()}.
     * <ul>
     *   <li>Given {@link Tabla#Tabla(int, int)} with sorok is one and oszlopok is
     * one.</li>
     *   <li>Then return {@code false}.</li>
     * </ul>
     * <p>
     * Method under test: {@link Tabla#isFull()}
     */
    @Test(testName = "Test isFull(); given Tabla(int, int) with sorok is one and oszlopok is one; then return 'false'")
    public void testIsFull_givenTablaWithSorokIsOneAndOszlopokIsOne_thenReturnFalse() {
        // Arrange, Act and Assert
        assertFalse((new Tabla(1, 1)).isFull());
    }

    /**
     * Test {@link Tabla#isFull()}.
     * <ul>
     *   <li>Given {@link Tabla#Tabla(int, int)} with sorok is one and oszlopok is
     * zero.</li>
     *   <li>Then return {@code true}.</li>
     * </ul>
     * <p>
     * Method under test: {@link Tabla#isFull()}
     */
    @Test(testName = "Test isFull(); given Tabla(int, int) with sorok is one and oszlopok is zero; then return 'true'")
    public void testIsFull_givenTablaWithSorokIsOneAndOszlopokIsZero_thenReturnTrue() {
        // Arrange, Act and Assert
        assertTrue((new Tabla(1, 0)).isFull());
    }

    /**
     * Test getters and setters.
     * <p>
     * Methods under test:
     * <ul>
     *   <li>{@link Tabla#getOszlopok()}
     *   <li>{@link Tabla#getSorok()}
     * </ul>
     */
    @Test(testName = "Test getters and setters")
    public void testGettersAndSetters() {
        // Arrange
        Tabla tabla = new Tabla(1, 1);

        // Act
        int actualOszlopok = tabla.getOszlopok();

        // Assert
        assertEquals(actualOszlopok, 1);
        assertEquals(tabla.getSorok(), 1);
    }

    /**
     * Test {@link Tabla#Tabla(int, int)}.
     * <ul>
     *   <li>When one.</li>
     *   <li>Then return Oszlopok is one.</li>
     * </ul>
     * <p>
     * Method under test: {@link Tabla#Tabla(int, int)}
     */
    @Test(testName = "Test new Tabla(int, int); when one; then return Oszlopok is one")
    public void testNewTabla_whenOne_thenReturnOszlopokIsOne() {
        // Arrange and Act
        Tabla actualTabla = new Tabla(1, 1);

        // Assert
        assertEquals(actualTabla.getOszlopok(), 1);
        assertEquals(actualTabla.getSorok(), 1);
        String[][] board = actualTabla.getBoard();
        assertEquals(board.length, 1);
        assertFalse(actualTabla.isFull());
        assertEquals(board[0], new String[]{"."});
    }
}
