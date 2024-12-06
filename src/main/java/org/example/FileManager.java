package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A fájlkezelésért felelős osztály.
 * Felelős a játék mentéséért és betöltéséért fájlokba.
 */
public class FileManager {

    /**
     * Fájlba írja a megadott tartalmat.
     *
     * @param fileName A fájl neve, amelybe az adatokat írni kell.
     * @param content A fájlba írandó tartalom.
     */
    public void writeToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {  // false a felülíráshoz
            writer.write(content);
        } catch (IOException e) {
            System.out.println("Hiba történt a fájl írásakor: " + e.getMessage());
        }
    }

    /**
     * Beolvas egy fájlból tartalmat, és visszaadja azt szövegként.
     *
     * @param fileName A fájl neve, amelyből az adatokat olvasni kell.
     * @return A fájl tartalma szövegként.
     */
    public String readFromFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (content.length() > 0) {
                    content.append("\n");
                }
                content.append(line);
            }
        } catch (IOException e) {
            System.out.println("Hiba történt a fájl beolvasásakor: " + e.getMessage());
        }
        return content.toString();
    }


    /**
     * Elmenti a játék állapotát egy fájlba.
     *
     * @param fileName A fájl neve, amelybe a játék állapotát menteni kell.
     * @param gameState A játék állapota.
     */

    // In FileManager.java
    public void saveGame(String fileName, GameState gameState) {
        StringBuilder content = new StringBuilder();

        // Save players' information
        List<Jatekos> jatekosok = gameState.getPlayers();
        if (jatekosok != null && !jatekosok.isEmpty()) {
            for (Jatekos jatekos : jatekosok) {
                content.append(jatekos.getNev()).append(" ").append(jatekos.getSzin()).append("\n");

            }
        } else {
            System.out.println("Hiba: Nincsenek játékosok a GameState-ben.");
            System.out.println(gameState.getPlayers() + "jatekos nevek");
            return;
        }

        // Save board state
        String[][] tabla = gameState.getBoard();
        for (String[] sor : tabla) {
            content.append(String.join(",", sor)).append("\n");
        }

        // Save current player index
        content.append(gameState.getCurrentPlayerIndex()).append("\n");

        // Write to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) { // false -> overwrite
            writer.write(content.toString());
            System.out.println("Játék sikeresen mentve a(z) " + fileName + " fájlba.");
        } catch (IOException e) {
            System.out.println("Hiba történt a fájl mentése során: " + e.getMessage());
        }
    }






    /**
     * Betölt egy játék állapotot egy fájlból.
     *
     * @param fileName A fájl neve, amelyből a játék állapotát betölteni kell.
     * @return A betöltött játék állapot.
     */
    public GameState loadGame(String fileName) {
        GameState gameState = new GameState();
        List<Jatekos> jatekosok = new ArrayList<>();
        String[][] tabla = new String[6][7];

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            // Játékosok betöltése
            for (int i = 0; i < 2; i++) {
                line = reader.readLine(); // Sor beolvasása
                if (line != null && !line.isBlank()) {
                    String[] parts = line.split(" "); // Név és szín elválasztása
                    if (parts.length == 2) {
                        if (i == 1 && (parts[0].equalsIgnoreCase("AI") || parts[0].equalsIgnoreCase("Gép"))) {
                            jatekosok.add(new Jatekos(parts[0], parts[1], true)); // AI beállítva
                        } else {
                            jatekosok.add(new Jatekos(parts[0], parts[1], false)); // Emberi játékos
                        }
                    } else {
                        throw new IOException("Hibás játékos formátum: " + line);
                    }
                } else {
                    throw new IOException("Hiányos játékos adatok.");
                }
            }

            // Tábla betöltése
            for (int i = 0; i < 6; i++) {
                line = reader.readLine();
                if (line != null && !line.isBlank()) {
                    String[] parts = line.split(",");
                    if (parts.length == 7) {
                        tabla[i] = parts;
                    } else {
                        throw new IOException("Hibás tábla sor: " + line);
                    }
                } else {
                    throw new IOException("Hiányos tábla sorok.");
                }
            }

            // Játékos index betöltése
            line = reader.readLine();
            while (line != null && line.isBlank()) {
                line = reader.readLine(); // Üres sorok átugrása
            }

            if (line != null) {
                try {
                    gameState.setCurrentPlayerIndex(Integer.parseInt(line.trim()));
                } catch (NumberFormatException e) {
                    throw new IOException("Hibás játékos index: " + line);
                }
            } else {
                throw new IOException("Hiányzó játékos index.");
            }

        } catch (IOException e) {
            System.out.println("Hiba történt a fájl betöltése során: " + e.getMessage());
            return null;
        }

        // GameState objektum beállítása
        gameState.setPlayers(jatekosok);
        gameState.setBoard(tabla);
        gameState.setAiMode(jatekosok.get(1).isAI()); // AI mód beállítása

        return gameState;
    }




}
