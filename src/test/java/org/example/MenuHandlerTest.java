package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuHandlerTest {

    private MenuHandler menuHandler;
    private Jatek mockJatek;
    private FileManager mockFileManager;

    @BeforeEach
    void setUp() {

        mockJatek = Mockito.mock(Jatek.class); // Mockolt Jatek objektum létrehozása
        menuHandler = new MenuHandler(mockJatek); // MenuHandler inicializálása a mock Jatek példánnyal
        mockFileManager = Mockito.mock(FileManager.class);  // Mockolt FileManager példány létrehozása
        menuHandler.setFileManager(mockFileManager); // FileManager hozzárendelése a MenuHandler-hez
    }

    @Test
    void testSaveGame() throws Exception {
        // Szimulált bemenet a fájl neve számára
        String inputFileName = "testSaveFile.txt";
        ByteArrayInputStream in = new ByteArrayInputStream((inputFileName + "\n").getBytes());
        System.setIn(in);

        // Szimulált kimenet elfogása
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // Mockolt GameState létrehozása
        GameState mockGameState = new GameState();
        when(mockJatek.getGameState()).thenReturn(mockGameState);

        // Metódus tesztelése
        menuHandler.saveGame();

        // Ellenőrzés, hogy a fájlmentés megtörtént-e
        verify(mockFileManager).saveGame(eq(inputFileName), eq(mockGameState));
        assertTrue(out.toString().contains("Játék mentve.")); // Konzol kimenet ellenőrzése
    }

    @Test
    void testLoadGame() throws Exception {
        // Szimulált bemenet a fájl neve számára
        String inputFileName = "testLoadFile.txt";
        ByteArrayInputStream in = new ByteArrayInputStream((inputFileName + "\n").getBytes());
        System.setIn(in);

        // Szimulált kimenet elfogása
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // Mockolt GameState létrehozása
        GameState mockGameState = new GameState();
        when(mockFileManager.loadGame(inputFileName)).thenReturn(mockGameState);

        // Mock Jatek metódusainak viselkedésének beállítása
        doNothing().when(mockJatek).setGameState(any(GameState.class));

        // Metódus tesztelése
        menuHandler.loadGame();

        // Ellenőrzés, hogy a fájlbetöltés megtörtént-e
        verify(mockFileManager).loadGame(eq(inputFileName));
        verify(mockJatek).setGameState(eq(mockGameState));
        assertTrue(out.toString().contains("Játék sikeresen betöltve!")); // Konzol kimenet ellenőrzése
    }


}
