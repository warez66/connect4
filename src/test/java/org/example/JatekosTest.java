package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Jatekos class.
 */
public class JatekosTest {

    @Test
    public void testGetNev() {
        Jatekos player = new Jatekos("Player1", "Y", false);
        assertEquals("Player1", player.getNev());
    }

    @Test
    public void testIsAI() {
        Jatekos player = new Jatekos("Player2", "R", true);
        assertTrue(player.isAI());
    }

    @Test
    public void testSetNev() {
        Jatekos player = new Jatekos("Player1", "Y", false);
        player.setNev("NewPlayer");
        assertEquals("NewPlayer", player.getNev());
    }

    @Test
    public void testSetSzin() {
        Jatekos player = new Jatekos("Player1", "Y", false);
        player.setSzin("R");
        assertEquals("R", player.getSzin());
    }

    @Test
    public void testSetAI() {
        Jatekos player = new Jatekos("Player1", "Y", false);
        player.setAI(true);
        assertTrue(player.isAI());
    }
}
