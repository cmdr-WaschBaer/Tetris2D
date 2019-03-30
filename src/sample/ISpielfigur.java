package sample;

import javafx.scene.shape.Rectangle;

/**
 * Spielfigurtypen als spezialisierte Klassen
 * Spielfigur 4x1
 * IIII
 *
 * @author Rene Hippe 2018WiSe-2019WiSe
 * @version 1
 */
class ISpielfigur extends Spielfigur {
    /**
     * Konstruktor I
     *
     * @param _zeile        Zeilen-Koordinate
     * @param _spalte       Spalten-Koordinate
     * @param _rotation     Orientierung der Spielfigur
     */
    ISpielfigur(int _zeile, int _spalte, int _rotation) {

        // Konstruktor der Superklasse Spielfigur aufrufen
        super(0, 3, 0);

        figurine = new Rectangle[][]{{full, full, full, full},{empty,empty,empty},{empty,empty,empty}};
        dreheAufWinkel(_rotation);
    }
} // ende class