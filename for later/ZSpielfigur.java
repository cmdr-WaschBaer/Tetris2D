package sample;


import javafx.scene.shape.Rectangle;

/**
 * Spielfigurtypen als spezialisierte Klassen
 * Spielfigur 3x2
 * ZZ
 * ZZ
 *
 * @author Rene Hippe 2018WiSe-2019WiSe
 * @version 1
 */
class ZSpielfigur extends Spielfigur {
    /**
     * Konstruktor Z
     *
     * @param _zeile        Zeilen-Koordinate
     * @param _spalte       Spalten-Koordinate
     * @param _rotation     Orientierung der Spielfigur
     */
    ZSpielfigur(int _zeile, int _spalte, int _rotation) {

        // Konstruktor der Superklasse Spielfigur aufrufen
        super(0, 3, 0);

        // initialisierung
        figurine = new Rectangle[][]{{full, full, empty}, {empty, full, full}};
        dreheAufWinkel(_rotation);
    }
} // ende class