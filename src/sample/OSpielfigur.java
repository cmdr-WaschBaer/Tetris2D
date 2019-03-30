package sample;


import javafx.scene.shape.Rectangle;

/**
 * Spielfigurtypen als spezialisierte Klassen
 * Spielfigur 2x2
 * OO
 * OO
 *
 * @author Rene Hippe 2018WiSe-2019WiSe
 * @version 1
 */
class OSpielfigur extends Spielfigur {
    /**
     * Konstruktor O
     *
     * @param _zeile        Zeilen-Koordinate
     * @param _spalte       Spalten-Koordinate
     * @param _rotation     Orientierung der Spielfigur
     */
    OSpielfigur(int _zeile, int _spalte, int _rotation) {
        // Konstruktor der Superklasse Spielfigur aufrufen
        super(0, 3, 0);

        // initialisierung
        figurine = new Rectangle[][]{{full, full}, {full, full}};

        dreheAufWinkel(_rotation);
    }

/**
 * Redefiniton der toString Methode und Aufruf der bisherigen.
 * @return Beschreibung der Spielfigur als String
 */
} // ende class