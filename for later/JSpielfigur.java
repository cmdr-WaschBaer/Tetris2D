package sample;


import javafx.scene.shape.Rectangle;

/**
 * Spielfigurtypen als spezialisierte Klassen
 * Spielfigur 3x2
 * JJJ
 * J
 *
 * @author Rene Hippe 2018WiSe-2019WiSe
 * @version 1
 */
class JSpielfigur extends Spielfigur {
    /**
     * Konstruktor J
     *
     * @param _zeile        Zeilen-Koordinate
     * @param _spalte       Spalten-Koordinate
     * @param _rotation     Orientierung der Spielfigur
     */
    JSpielfigur(int _zeile, int _spalte, int _rotation) {

        // Konstruktor der Superklasse Spielfigur aufrufen
        super(0, 3, 0);
        figurine = new Rectangle[][]{{full, full, full}, {empty, empty, empty}};
        dreheAufWinkel(_rotation);
    }
} // ende class