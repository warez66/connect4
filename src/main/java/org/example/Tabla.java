package org.example;

/**
 * A Tabla osztály reprezentálja a játék tábláját,
 * amely tartalmazza a játékmezőket és a logikát.
 */
public class Tabla {
    private char[][] tabla;
    private int sorok;
    private int oszlopok;

    /**
     * Konstruktor a tábla inicializálásához.
     *
     * @param sorok    A sorok száma.
     * @param oszlopok Az oszlopok száma.
     */
    public Tabla(int sorok, int oszlopok) {
        this.sorok = sorok;
        this.oszlopok = oszlopok;
        tabla = new char[sorok][oszlopok];
        for (int i = 0; i < sorok; i++) {
            for (int j = 0; j < oszlopok; j++) {
                tabla[i][j] = '.'; // Üres mező
            }
        }
    }




    /**
     * Visszaadja a tábla állapotát String[][] formában.
     *
     * @return A tábla másolata String mátrixként.
     */
    public String[][] getBoard() {
        String[][] boardCopy = new String[sorok][oszlopok];
        for (int i = 0; i < sorok; i++) {
            for (int j = 0; j < oszlopok; j++) {
                boardCopy[i][j] = String.valueOf(tabla[i][j]); // A `tabla` adatait másoljuk át
            }
        }
        return boardCopy;
    }
    /**
     * Visszaadja a tábla sorainak számát.
     *
     * @return A sorok száma.
     */

    public int getSorok() {
        return sorok;
    }

    /**
     * Visszaadja a tábla oszlopainak számát.
     *
     * @return Az oszlopok száma.
     */

    public int getOszlopok() {
        return oszlopok;
    }

    /**
     * Beállítja a tábla állapotát egy új állapotra.
     *
     * @param board Az új állapot String mátrixként.
     */
    public void setBoard(String[][] board) {
        for (int i = 0; i < sorok; i++) {
            for (int j = 0; j < oszlopok; j++) {
                this.tabla[i][j] = board[i][j].charAt(0); // A `board` adatait állítjuk be a `tabla`-ban
            }
        }
    }

    /**
     * Lekéri a tábla egy adott mezőjének értékét.
     *
     * @param sor    A mező sora.
     * @param oszlop A mező oszlopa.
     * @return A mező értéke (karakterként).
     */

    public char getErtek(int sor, int oszlop) {
        return tabla[sor][oszlop];
    }

    /**
     * Lerak egy korongot egy adott oszlopba.
     *
     * @param oszlop Az oszlop indexe (0-al kezdődik).
     * @param szin   A lerakott korong színe (pl. 'Y' vagy 'R').
     * @return true, ha a korong sikeresen lerakható, különben false.
     */

    public boolean lehelyez(int oszlop, char szin) {
        for (int i = sorok - 1; i >= 0; i--) {
            if (tabla[i][oszlop] == '.') {
                tabla[i][oszlop] = szin;
                return true;
            }
        }
        return false; // Ha az oszlop tele van
    }

    /**
     * Kiírja a tábla aktuális állapotát a konzolra.
     */

    public void kiir() {
        for (int i = 0; i < sorok; i++) {
            for (int j = 0; j < oszlopok; j++) {
                System.out.print(tabla[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Ellenőrzi, hogy a tábla teljesen tele van-e.
     *
     * @return true, ha a tábla tele van, különben false.
     */

    public boolean isFull() {
        for (int i = 0; i < oszlopok; i++) {
            if (tabla[0][i] == '.') { // Check if top row has any empty spaces
                return false;
            }
        }
        return true;
    }

    /**
     * Ellenőrzi, hogy egy adott színű játékos nyert-e.
     *
     * @param szin A játékos színe.
     * @return true, ha a játékos nyert, különben false.
     */

    public boolean vanNyertes(char szin) {

        for (int i = 0; i < 6; i++) {  // 6 sor
            for (int j = 0; j < 4; j++) {  // 7 oszlop, de csak az első 4-tól 6-ig vizsgáljuk
                if (tabla[i][j] == szin && tabla[i][j + 1] == szin && tabla[i][j + 2] == szin && tabla[i][j + 3] == szin) {
                    return true; // Találtunk egy vízszintes nyereményt
                }
            }
        }

        // Függőleges nyeremény ellenőrzése
        for (int i = 0; i < 3; i++) {  // Csak az első 3 sorig
            for (int j = 0; j < 7; j++) {  // Minden oszlop
                if (tabla[i][j] == szin && tabla[i + 1][j] == szin && tabla[i + 2][j] == szin && tabla[i + 3][j] == szin) {
                    return true; // Találtunk egy függőleges nyereményt
                }
            }
        }

        // Átlós nyeremények ellenőrzése
        for (int i = 0; i < 3; i++) {  // Az első 3 sor
            for (int j = 0; j < 4; j++) {  // Az első 4 oszlop
                // Balról jobbra átló
                if (tabla[i][j] == szin && tabla[i + 1][j + 1] == szin && tabla[i + 2][j + 2] == szin && tabla[i + 3][j + 3] == szin) {
                    return true; // Találtunk egy átlós nyereményt balról jobbra
                }
                // Jobbról balra átló
                if (tabla[i + 3][j] == szin && tabla[i + 2][j + 1] == szin && tabla[i + 1][j + 2] == szin && tabla[i][j + 3] == szin) {
                    return true; // Találtunk egy átlós nyereményt jobbról balra
                }
            }
        }

        return false; // Ha semelyik sem igaz, nincs nyertes
    }

}
