package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TablaTest {

    @Test
    void testTablaInitialization() {
        Tabla tabla = new Tabla(6, 7);
        assertEquals(6, tabla.getSorok());
        assertEquals(7, tabla.getOszlopok());
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                assertEquals('.', tabla.getErtek(i, j));
            }
        }
    }

    @Test
    void testGetBoard() {
        Tabla tabla = new Tabla(6, 7);
        String[][] board = tabla.getBoard();
        assertEquals(6, board.length);
        assertEquals(7, board[0].length);
        for (String[] row : board) {
            for (String cell : row) {
                assertEquals(".", cell);
            }
        }
    }

    @Test
    void testSetBoard() {
        Tabla tabla = new Tabla(6, 7);
        String[][] newBoard = {
                {"R", ".", ".", ".", ".", ".", "."},
                {".", "Y", ".", ".", ".", ".", "."},
                {".", ".", "R", ".", ".", ".", "."},
                {".", ".", ".", "Y", ".", ".", "."},
                {".", ".", ".", ".", "R", ".", "."},
                {".", ".", ".", ".", ".", "Y", "."}
        };
        tabla.setBoard(newBoard);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                assertEquals(newBoard[i][j].charAt(0), tabla.getErtek(i, j));
            }
        }
    }



    @Test
    void testIsFull() {
        Tabla tabla = new Tabla(6, 7);
        assertFalse(tabla.isFull());
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                tabla.lehelyez(j, 'R');
            }
        }
        assertTrue(tabla.isFull());
    }

    @Test
    void testVanNyertes() {
        Tabla tabla = new Tabla(6, 7);
        tabla.lehelyez(0, 'R');
        tabla.lehelyez(1, 'R');
        tabla.lehelyez(2, 'R');
        tabla.lehelyez(3, 'R');
        assertTrue(tabla.vanNyertes('R')); // Horizontal win

        tabla = new Tabla(6, 7);
        for (int i = 0; i < 4; i++) {
            tabla.lehelyez(0, 'Y');
        }
        assertTrue(tabla.vanNyertes('Y')); // Vertical win

        tabla = new Tabla(6, 7);
        tabla.lehelyez(0, 'R');
        tabla.lehelyez(1, '.');
        tabla.lehelyez(1, 'R');
        tabla.lehelyez(2, '.');
        tabla.lehelyez(2, '.');
        tabla.lehelyez(2, 'R');
        tabla.lehelyez(3, '.');
        tabla.lehelyez(3, '.');
        tabla.lehelyez(3, '.');
        tabla.lehelyez(3, 'R');
        assertTrue(tabla.vanNyertes('R')); // Diagonal win
    }
}
