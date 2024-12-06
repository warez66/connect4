package org.example;


import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {

    private final FileManager fileManager = new FileManager();

    @Test
    void testWriteToFileAndReadFromFile() throws IOException {
        String fileName = "testFile.txt";
        String content = "Teszt tartalom";

        // Írás a fájlba
        fileManager.writeToFile(fileName, content);

        // Olvasás a fájlból
        String readContent = fileManager.readFromFile(fileName);

        // Ellenőrzés
        assertEquals(content, readContent);

        // Takarítás
        Files.deleteIfExists(new File(fileName).toPath());
    }

    @Test
    void testSaveGame() throws IOException {
        String fileName = "testSaveGame.txt";

        // Létrehozunk egy teszt GameState objektumot
        GameState gameState = new GameState();
        Jatekos player1 = new Jatekos("Player1", "Y", false);
        Jatekos player2 = new Jatekos("Player2", "R", true);
        gameState.setPlayers(List.of(player1, player2));
        gameState.setBoard(new String[][]{
                {".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", "."},
                {"Y", ".", ".", ".", ".", ".", "R"}
        });
        gameState.setCurrentPlayerIndex(0);

        // Mentés fájlba
        fileManager.saveGame(fileName, gameState);

        // Ellenőrizzük, hogy a fájl létrejött-e
        File savedFile = new File(fileName);
        assertTrue(savedFile.exists());

        // Takarítás
        Files.deleteIfExists(savedFile.toPath());
    }

    @Test
    void testLoadGameWithValidData() throws IOException {
        String fileName = "testLoadGame.txt";

        // Teszt fájl tartalma
        String content = """
                Player1 Y
                Player2 R
                .,.,.,.,.,.,.
                .,.,.,.,.,.,.
                .,.,.,.,.,.,.
                .,.,.,.,.,.,.
                .,.,.,.,.,.,.
                Y,.,.,.,.,.,R
                0
                """;

        // Fájl létrehozása
        fileManager.writeToFile(fileName, content);

        // Betöltés
        GameState loadedGame = fileManager.loadGame(fileName);

        // Ellenőrzés
        assertNotNull(loadedGame);
        assertEquals(2, loadedGame.getPlayers().size());
        assertEquals("Player1", loadedGame.getPlayers().get(0).getNev());
        assertEquals("Player2", loadedGame.getPlayers().get(1).getNev());
        assertEquals("Y", loadedGame.getBoard()[5][0]);
        assertEquals("R", loadedGame.getBoard()[5][6]);
        assertEquals(0, loadedGame.getCurrentPlayerIndex());

        // Takarítás
        Files.deleteIfExists(new File(fileName).toPath());
    }




}
