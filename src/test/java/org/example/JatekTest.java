package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JatekTest {

    @Test
    void testInitialization() {
        Jatek game = new Jatek("Player1", "AI", true);
        assertNotNull(game);
    }




}
