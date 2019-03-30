package sample;


import javafx.scene.shape.Rectangle;

/**
 * Spielfigurtypen als spezialisierte Klassen
 * Spielfigur 3x2
 * LLL
 * L
 *
 * @author Rene Hippe 2018WiSe-2019WiSe
 * @version 1
 */
class LSpielfigur extends Spielfigur {
    /**
     * Konstruktor L
     *
     * @param _zeile        Zeilen-Koordinate
     * @param _spalte       Spalten-Koordinate
     * @param _rotation     Orientierung der Spielfigur
     */
    LSpielfigur(int _zeile, int _spalte, int _rotation) {

        // Konstruktor der Superklasse Spielfigur aufrufen
        super(0, 3, 0);
        figurine = new Rectangle[][]{{full, full, full}, {full, empty, empty}};
        dreheAufWinkel(_rotation);
    }
} // ende class