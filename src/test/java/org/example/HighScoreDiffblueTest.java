package org.example;

import org.testng.annotations.Test;

public class HighScoreDiffblueTest {
    /**
     * Test {@link HighScore#addOrUpdatePlayer(String, int)}.
     * <ul>
     *   <li>Given {@link HighScore} (default constructor).</li>
     *   <li>When {@code Name}.</li>
     * </ul>
     * <p>
     * Method under test: {@link HighScore#addOrUpdatePlayer(String, int)}
     */
    @Test(testName = "Test addOrUpdatePlayer(String, int); given HighScore (default constructor); when 'Name'")
    public void testAddOrUpdatePlayer_givenHighScore_whenName() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange and Act
        (new HighScore()).addOrUpdatePlayer("Name", 2);
    }

    /**
     * Test {@link HighScore#printHighScores()}.
     * <p>
     * Method under test: {@link HighScore#printHighScores()}
     */
    @Test(testName = "Test printHighScores()")
    public void testPrintHighScores() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange and Act
        (new HighScore()).printHighScores();
    }

    /**
     * Test new {@link HighScore} (default constructor).
     * <p>
     * Method under test: default or parameterless constructor of {@link HighScore}
     */
    @Test(testName = "Test new HighScore (default constructor)")
    public void testNewHighScore() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange and Act
        new HighScore();
    }
}
