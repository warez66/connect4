package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.example.GameState;
import org.example.HighScore;
import org.example.Tabla;



/**
 * Ez az osztály felelős a Connect 4 játék logikájának kezeléséért.
 * Kezeli a játékosokat, a tábla állapotát, és a játék menetét.
 */
public class Jatek {
    private Tabla tabla; // A játék táblája
    private Jatekos jatekos1; // Az első játékos adatai
    private Jatekos jatekos2; // A második játékos adatai (AI vagy ember)
    private Jatekos aktualisJatekos; // Az aktuális játékos
    private boolean nyertes = false; // Jelzi, hogy van-e nyertes
    public final MenuHandler menuHandler; // Menü kezelő osztály
    private GameState gameState; // A játék aktuális állapota
    private boolean aiMode = false; // Jelzi, hogy a második játékos AI-e
    private int currentPlayerIndex; // Az aktuális játékos indexe

    /**
     * Konstruktor: Új játék inicializálása a megadott játékos nevekkel és AI móddal.
     *
     * @param jatekos1Nev Az első játékos neve.
     * @param jatekos2Nev A második játékos neve.
     * @param isAI Igaz, ha a második játékos egy AI.
     */
    public Jatek(String jatekos1Nev, String jatekos2Nev, boolean isAI) {
        tabla = new Tabla(6, 7);
        jatekos1 = new Jatekos(jatekos1Nev, "Y", false);
        jatekos2 = new Jatekos(jatekos2Nev, "R", isAI);
        aktualisJatekos = jatekos1;
        this.aiMode = isAI;
        gameState = new GameState();
        gameState.setBoard(tabla.getBoard());
        this.menuHandler = new MenuHandler(this); // MenuHandler inicializálása
    }


    /**
     * A játék fő ciklusa.
     * Felváltva hívja a játékosokat, amíg nincs győztes vagy a tábla meg nem telik.
     */
    public void indit() {
        boolean nyertes = false; // Jelzi, hogy vége van-e a játéknak
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        while (!nyertes) {
            tabla.kiir(); // Megjeleníti a tábla aktuális állapotát
            System.out.println("Aktuális játékos: " + aktualisJatekos.getNev() + " (" + aktualisJatekos.getSzin() + ")");

            int oszlop; // Az oszlop, ahová a játékos lépni fog
            if (aiMode && aktualisJatekos.isAI()) {
                // Ha AI a játékos, véletlenszerűen választ egy oszlopot
                oszlop = random.nextInt(tabla.getOszlopok()) + 1; // Random oszlop (1-től kezdődően)
                System.out.println("AI lépése: " + oszlop);
            } else {
                // Emberi játékos bemenete
                System.out.print("Válassz egy oszlopot (1-" + tabla.getOszlopok() + "): ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Hibás bemenet! Adj meg egy számot.");
                    scanner.next(); // Hibás bemenet eltávolítása
                }
                oszlop = scanner.nextInt();
            }

            // Ellenőrzi, hogy az oszlop érvényes-e
            if (oszlop < 1 || oszlop > tabla.getOszlopok()) {
                System.out.println("Érvénytelen oszlop! Próbáld újra.");
                continue;
            }

            if (!tabla.lehelyez(oszlop - 1, aktualisJatekos.getSzin().charAt(0))) {
                System.out.println("Az oszlop tele van! Próbáld újra.");
                continue;
            }

            // Frissítsük a GameState táblázatát
            gameState.setBoard(tabla.getBoard());

            // Ellenőrzi, hogy van-e győztes
            if (tabla.vanNyertes(aktualisJatekos.getSzin().charAt(0))) {
                tabla.kiir();
                System.out.println("Gratulálunk, " + aktualisJatekos.getNev() + " nyert!");
                HighScore highScore = new HighScore();
                highScore.addOrUpdatePlayer(aktualisJatekos.getNev(), 1); // Győztes pontjainak mentése az adatbázisba
                nyertes = true; // Véget vet a játéknak
            } else if (tabla.isFull()) {
                // Ha a tábla tele van, és nincs győztes, döntetlen
                tabla.kiir();
                System.out.println("Döntetlen!");
                nyertes = true;
            }

            // A következő játékos jön
            aktualisJatekos = (aktualisJatekos == jatekos1) ? jatekos2 : jatekos1;
        }

        System.out.println("Játék vége!");
    }

    /**
     * Visszaadja a játék aktuális állapotát.
     *
     * @return A játék állapota `GameState` objektumban.
     */
    public GameState getGameState() {
        GameState gameState = new GameState();

        // Tábla állapotának mentése
        gameState.setBoard(tabla.getBoard());

        // Játékosok mentése
        List<Jatekos> players = new ArrayList<>();
        players.add(jatekos1);
        players.add(jatekos2);
        gameState.setPlayers(players);

        // Aktuális játékos indexének mentése
        gameState.setCurrentPlayerIndex(currentPlayerIndex);

        return gameState;
    }

    /**
     * Beállítja a játék állapotát egy meglévő `GameState` alapján.
     *
     * @param gameState Az új játék állapot.
     */
    public void setGameState(GameState gameState) {
        this.tabla.setBoard(gameState.getBoard()); // Táblázat állapotának beállítása
        List<Jatekos> players = gameState.getPlayers(); // Játékosok beállítása
        this.jatekos1 = players.get(0);
        this.jatekos2 = players.get(1);

        // Aktuális játékos beállítása a fájlból érkező index alapján
        this.currentPlayerIndex = gameState.getCurrentPlayerIndex();
        if (currentPlayerIndex == 0) {
            this.aktualisJatekos = jatekos1;
        } else if (currentPlayerIndex == 1) {
            this.aktualisJatekos = jatekos2;
        } else {
            throw new IllegalStateException("Érvénytelen aktuális játékos index: " + currentPlayerIndex);
        }

        // Debug: Ellenőrizzük a játékosok adatait
        System.out.println("Játékos 1: " + jatekos1.getNev() + ", színe: " + jatekos1.getSzin());
        System.out.println("Játékos 2: " + jatekos2.getNev() + ", színe: " + jatekos2.getSzin());
        System.out.println("Aktuális játékos: " + aktualisJatekos.getNev() + ", színe: " + aktualisJatekos.getSzin());




        // AI mód frissítése
        this.aiMode = jatekos2.isAI();
    }



    /**
     * A fő metódus, amely elindítja az alkalmazást.
     *
     * @param args A parancssori argumentumok.
     */
    public static void main(String[] args) {
        Jatek jatek = new Jatek("Játékos 1", "Gép", true); // Új játék inicializálása
        jatek.menuHandler.displayMenu(); // Menü megjelenítése

    }
}
