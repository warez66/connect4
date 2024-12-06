package org.example;

/**
 * A Jatekos (Játékos) osztály egy játékos adatait tárolja.
 * Tartalmazza a játékos nevét, színét, valamint azt, hogy a játékos mesterséges intelligencia-e.
 */
public class Jatekos {
    private String nev;
    private String szin;
    private boolean isAI;

    /**
     * Konstruktor egy új játékos létrehozásához.
     *
     * @param nev   A játékos neve.
     * @param szin  A játékos színe.
     * @param isAI  Igaz, ha a játékos mesterséges intelligencia.
     */
    public Jatekos(String nev, String szin, boolean isAI) {
        this.nev = nev;
        this.szin = szin;
        this.isAI = isAI;
    }

    /**
     * Visszaadja a játékos nevét.
     *
     * @return A játékos neve.
     */
    public String getNev() {
        return nev;
    }

    /**
     * Beállítja a játékos nevét.
     *
     * @param nev Az új név.
     */
    public void setNev(String nev) {
        this.nev = nev;
    }

    /**
     * Visszaadja a játékos színét.
     *
     * @return A játékos színe.
     */
    public String getSzin() {
        return szin;
    }

    /**
     * Beállítja a játékos színét.
     *
     * @param szin Az új szín.
     */
    public void setSzin(String szin) {
        this.szin = szin;
    }

    /**
     * Ellenőrzi, hogy a játékos mesterséges intelligencia-e.
     *
     * @return Igaz, ha a játékos mesterséges intelligencia.
     */
    public boolean isAI() {
        return isAI;
    }

    /**
     * Beállítja, hogy a játékos mesterséges intelligencia-e.
     *
     * @param isAI Az új mesterséges intelligencia státusz.
     */
    public void setAI(boolean isAI) {
        this.isAI = isAI;
    }
}
