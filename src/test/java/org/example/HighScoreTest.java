package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HighScoreTest {

    @Test
    void testHighScoreInitialization() {
        HighScore highScore = new HighScore();
        assertNotNull(highScore);
    }

    @Test
    void testAddOrUpdatePlayer() {
        HighScore highScore = new HighScore();
        highScore.addOrUpdatePlayer("Player1", 1);

        // Assert that the player was added or updated (integration with DB required for full testing)
    }

    @Test
    void testPrintHighScores() {
        HighScore highScore = new HighScore();
        highScore.addOrUpdatePlayer("Player1", 3);
        highScore.addOrUpdatePlayer("Player2", 5);

        // Test that the scores are correctly printed
    }
}
