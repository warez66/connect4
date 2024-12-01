package org.example;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class JatekosDiffblueTest {
    /**
     * Test getters and setters.
     * <p>
     * Methods under test:
     * <ul>
     *   <li>{@link Jatekos#Jatekos(String, String, boolean)}
     *   <li>{@link Jatekos#getNev()}
     *   <li>{@link Jatekos#getSzin()}
     *   <li>{@link Jatekos#isAI()}
     * </ul>
     */
    @Test(testName = "Test getters and setters")
    public void testGettersAndSetters() {
        // Arrange and Act
        Jatekos actualJatekos = new Jatekos("Nev", "Szin", true);
        String actualNev = actualJatekos.getNev();
        String actualSzin = actualJatekos.getSzin();

        // Assert
        assertEquals(actualNev, "Nev");
        assertEquals(actualSzin, "Szin");
        assertTrue(actualJatekos.isAI());
    }
}
