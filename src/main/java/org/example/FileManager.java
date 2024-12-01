package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
                content.append(line).append("\n");
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
    public void saveGame(String fileName, GameState gameState) {
        StringBuilder content = new StringBuilder();

        // Játékosok adatai
        for (Jatekos jatekos : gameState.getPlayers()) {
            content.append(jatekos.getNev()).append(" ").append(jatekos.getSzin()).append("\n");
        }

        // Játéktábla adatai
        String[][] tabla = gameState.getBoard();
        for (String[] sor : tabla) {
            content.append(String.join(",", sor)).append("\n");
        }

        // Jelenlegi játékos indexe
        content.append(gameState.getCurrentPlayerIndex()).append("\n");

        // Fájlba írás
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content.toString());
        } catch (IOException e) {
            System.out.println("Hiba történt a fájl mentésekor: " + e.getMessage());
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
            int rowIndex = 0;

            // Játékosok betöltése
            for (int i = 0; i < 2; i++) {
                if ((line = reader.readLine()) != null && !line.isBlank()) {
                    String[] parts = line.split(" ");
                    if (parts.length == 2) {
                        boolean isAI = parts[0].equalsIgnoreCase("Gép") || parts[0].equalsIgnoreCase("AI"); // AI ellenőrzés
                        jatekosok.add(new Jatekos(parts[0], parts[1], isAI));
                    } else {
                        throw new IOException("Hibás játékos formátum: " + line);
                    }
                } else {
                    throw new IOException("Hiányos játékos adatok.");
                }
            }

            // Tábla betöltése
            while ((line = reader.readLine()) != null && rowIndex < 6) {
                System.out.println("Beolvasott tábla sor: " + line);
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    tabla[rowIndex++] = parts;
                } else {
                    throw new IOException("Hibás tábla sor: " + line);
                }
            }

            // Játékos indexének betöltése

            while (line != null && line.isBlank()) {
                line = reader.readLine(); // Üres sorok átugrása
            }

            if (line != null) {
                line = line.trim(); // Eltávolítja az esetleges felesleges whitespace-eket
                if (!line.matches("\\d+")) { // Ellenőrzi, hogy a sor csak számokat tartalmaz
                    throw new IOException("Hibás játékos index: " + line);
                }
                try {
                    gameState.setCurrentPlayerIndex(Integer.parseInt(line));
                    System.out.println("Játékos index betöltve: " + line);
                } catch (NumberFormatException e) {
                    throw new IOException("Hibás játékos index, nem lehetett konvertálni: " + line);
                }
            } else {
                throw new IOException("Hiányzó vagy üres játékos index sora.");
            }

        } catch (IOException e) {
            System.out.println("Hiba történt a fájl betöltése során: " + e.getMessage());
            return null;
        }

        gameState.setPlayers(jatekosok);
        gameState.setBoard(tabla);
        return gameState;
    }

}
