package org.example;


/**
 * A Jatekos osztály reprezentálja a játékosokat,
 * akik részt vesznek a játékban.
 */
public class Jatekos {
    private final String nev;
    private final String szin;
    private final boolean isAI;

    public Jatekos(String nev, String szin, boolean isAI) {
        this.nev = nev;
        this.szin = szin;
        this.isAI = isAI;
    }

    public String getNev() {
        return nev;
    }

    public String getSzin() {
        return szin;
    }

    public boolean isAI() {
        return isAI;
    }
}
