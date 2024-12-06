package org.example;

import java.util.Scanner;

import org.example.FileManager;

/**
 * A MenuHandler osztály kezeli a játék menüjét, beleértve
 * az új játék indítását, a mentést, a betöltést és a kilépést.
 */
public class MenuHandler {
    private  FileManager fileManager;
    private Jatek jatek;

    /**
     * Konstruktor a MenuHandler osztály példányosításához.
     *
     * @param jatek A játék példánya, amelyet a menü kezel.
     */
    public MenuHandler(Jatek jatek) {
        this.jatek = jatek;
        this.fileManager = new FileManager();

        // Inicializálja a HighScore adatbázist
        new HighScore();

    }
    /**
     * Megjeleníti a főmenüt, és kezeli a felhasználói választásokat.
     */

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("1. Játék indítása");
            System.out.println("2. Játék mentése");
            System.out.println("3. Játék betöltése");
            System.out.println("4. High Score megtekintése");
            System.out.println("5. Kilépés");

            System.out.print("Választás: ");


            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> startNewGame(); // Új játék indítása
                    case 2 -> saveGame();
                    case 3 -> loadGame();

                    case 4 -> {
                        HighScore highScore = new HighScore();
                        highScore.printHighScores();
                        System.out.println("-------------------");
                    }
                    case 5 -> {
                        System.out.println("Kilépés...");
                        exit = true;
                    }
                    default -> System.out.println("Érvénytelen választás! Próbáld újra.");
                }
            } catch (Exception e) {
                System.out.println("Hibás bemenet! Kérlek, adj meg egy számot.");
                scanner.nextLine(); // Hibás bemenet átugrása
            }
        }
    }

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    /**
     * Új játék indítása. A felhasználótól bekéri a szükséges adatokat
     * a játék beállításához.
     */

    private void startNewGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Add meg az első játékos nevét: ");
        String jatekos1Nev = scanner.nextLine();

        String ellenfelValasztas;
        while (true) {
            System.out.print("A második játékos gép legyen (igen/nem)? ");
            ellenfelValasztas = scanner.nextLine().trim().toLowerCase();
            if (ellenfelValasztas.equals("igen") || ellenfelValasztas.equals("nem")) {
                break; // Helyes bemenet esetén kilépünk a ciklusból
            } else {
                System.out.println("Hibás bemenet! Csak 'igen' vagy 'nem' válaszokat fogadunk el.");
            }
        }

        String jatekos2Nev;
        boolean isAI = ellenfelValasztas.equals("igen"); // "igen" esetén AI lesz a második játékos

        if (isAI) {
            jatekos2Nev = "Gép";
        } else {
            System.out.print("Add meg a második játékos nevét: ");
            jatekos2Nev = scanner.nextLine();
        }

        // Új játék inicializálása
        jatek = new Jatek(jatekos1Nev, jatekos2Nev, isAI);
        jatek.indit();
    }


    /**
     * A jelenlegi játékállás mentése egy fájlba.
     *
     * @throws IOException Ha hiba történik a fájl írása közben.
     */
    public void saveGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Add meg a fájl nevét a mentéshez: ");
        String fileName = scanner.nextLine();

        if (fileName.isEmpty()) {
            System.out.println("Hiba: Nem adtál meg fájlnevet. A mentés megszakadt.");
            return;
        }

        GameState gameState = jatek.getGameState();

        fileManager.saveGame(fileName, gameState); // FileManager használata
        System.out.println("Játék mentve.");
    }

    /**
     * Egy korábban mentett játékállás betöltése egy fájlból.
     *
     * @throws IOException Ha hiba történik a fájl olvasása közben.
     */
    public void loadGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Add meg a fájl nevét a betöltéshez: ");
        String fileName = scanner.nextLine();
        GameState loadedState = fileManager.loadGame(fileName);
        if (loadedState != null) {
            jatek.setGameState(loadedState); // Frissíti a játék állapotát
            System.out.println("Játék sikeresen betöltve!");
            jatek.indit(); // Újraindítja a játékot a betöltött állapottal
        } else {
            System.out.println("Hiba történt a játék betöltése során.");
        }
    }
}
