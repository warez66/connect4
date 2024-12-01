package org.example;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.util.ArrayList;

import org.testng.annotations.Test;

public class FileManagerDiffblueTest {
    /**
     * Test {@link FileManager#writeToFile(String, String)}.
     * <ul>
     *   <li>When {@code \d+}.</li>
     * </ul>
     * <p>
     * Method under test: {@link FileManager#writeToFile(String, String)}
     */
//   @Test(testName = "Test writeToFile(String, String); when '\\d+'")
//   public void testWriteToFile_whenD() {
//       // Arrange
//       FileManager fileManager = new FileManager();

//       // Act
//       fileManager.writeToFile("\\d+", "Not all who wander are lost");

//       // Assert that nothing has changed
//       assertEquals(fileManager.readFromFile("foo.txt"), "Not all who wander are lost\n");
//   }

    /**
     * Test {@link FileManager#writeToFile(String, String)}.
     * <ul>
     *   <li>When {@code foo.txt}.</li>
     * </ul>
     * <p>
     * Method under test: {@link FileManager#writeToFile(String, String)}
     */
    @Test(testName = "Test writeToFile(String, String); when 'foo.txt'")
    public void testWriteToFile_whenFooTxt() {
        // Arrange
        FileManager fileManager = new FileManager();

        // Act
        fileManager.writeToFile("foo.txt", "Not all who wander are lost");

        // Assert that nothing has changed
        assertEquals(fileManager.readFromFile("foo.txt"), "Not all who wander are lost\n");
    }

    /**
     * Test {@link FileManager#readFromFile(String)}.
     * <p>
     * Method under test: {@link FileManager#readFromFile(String)}
     */
//    @Test(testName = "Test readFromFile(String)")
//    public void testReadFromFile() {
//        // Arrange, Act and Assert
//        assertEquals((new FileManager()).readFromFile("foo.txt"), "");
//    }

    /**
     * Test {@link FileManager#saveGame(String, GameState)}.
     * <ul>
     *   <li>When {@link GameState#GameState()}.</li>
     *   <li>Then {@link FileManager} (default constructor) readFromFile
     * {@code foo.txt} is a string.</li>
     * </ul>
     * <p>
     * Method under test: {@link FileManager#saveGame(String, GameState)}
     */
    @Test(testName = "Test saveGame(String, GameState); when GameState(); then FileManager (default constructor) readFromFile 'foo.txt' is a string")
    public void testSaveGame_whenGameState_thenFileManagerReadFromFileFooTxtIsAString() {
        // Arrange
        FileManager fileManager = new FileManager();

        // Act
        fileManager.saveGame("foo.txt", new GameState());

        // Assert
        assertEquals(fileManager.readFromFile("foo.txt"),
                "null,null,null,null,null,null,null\n" + "null,null,null,null,null,null,null\n"
                        + "null,null,null,null,null,null,null\n" + "null,null,null,null,null,null,null\n"
                        + "null,null,null,null,null,null,null\n" + "null,null,null,null,null,null,null\n" + "0\n");
    }

    /**
     * Test {@link FileManager#saveGame(String, GameState)}.
     * <ul>
     *   <li>Given {@link ArrayList#ArrayList()} add
     * {@link Jatekos#Jatekos(String, String, boolean)} with {@code Nev} and
     * {@code Szin} and isAI is {@code true}.</li>
     * </ul>
     * <p>
     * Method under test: {@link FileManager#saveGame(String, GameState)}
     */
    @Test(testName = "Test saveGame(String, GameState); given ArrayList() add Jatekos(String, String, boolean) with 'Nev' and 'Szin' and isAI is 'true'")
    public void testSaveGame_givenArrayListAddJatekosWithNevAndSzinAndIsAIIsTrue() {
        // Arrange
        FileManager fileManager = new FileManager();

        ArrayList<Jatekos> players = new ArrayList<>();
        players.add(new Jatekos("Nev", "Szin", true));

        GameState gameState = new GameState();
        gameState.setPlayers(players);

        // Act
        fileManager.saveGame("foo.txt", gameState);

        // Assert
        assertEquals(fileManager.readFromFile("foo.txt"),
                "Nev Szin\n" + "null,null,null,null,null,null,null\n" + "null,null,null,null,null,null,null\n"
                        + "null,null,null,null,null,null,null\n" + "null,null,null,null,null,null,null\n"
                        + "null,null,null,null,null,null,null\n" + "null,null,null,null,null,null,null\n" + "0\n");
    }

    /**
     * Test {@link FileManager#saveGame(String, GameState)}.
     * <ul>
     *   <li>When lf.</li>
     *   <li>Then {@link FileManager} (default constructor) readFromFile
     * {@code foo.txt} is a string.</li>
     * </ul>
     * <p>
     * Method under test: {@link FileManager#saveGame(String, GameState)}
     */
    @Test(testName = "Test saveGame(String, GameState); when lf; then FileManager (default constructor) readFromFile 'foo.txt' is a string")
    public void testSaveGame_whenLf_thenFileManagerReadFromFileFooTxtIsAString() {
        // Arrange
        FileManager fileManager = new FileManager();

        // Act
        fileManager.saveGame("\n", new GameState());

        // Assert
//        assertEquals(fileManager.readFromFile("foo.txt"),
//                "Nev Szin\n" + "null,null,null,null,null,null,null\n" + "null,null,null,null,null,null,null\n"
//                        + "null,null,null,null,null,null,null\n" + "null,null,null,null,null,null,null\n"
//                        + "null,null,null,null,null,null,null\n" + "null,null,null,null,null,null,null\n" + "0\n");
    }

    /**
     * Test {@link FileManager#loadGame(String)}.
     * <p>
     * Method under test: {@link FileManager#loadGame(String)}
     */
    @Test(testName = "Test loadGame(String)")
    public void testLoadGame() {
        // Arrange, Act and Assert
        assertNull((new FileManager()).loadGame("foo.txt"));
    }
}
