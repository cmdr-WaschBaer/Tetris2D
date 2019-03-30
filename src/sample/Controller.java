package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.scene.input.KeyEvent.KEY_PRESSED;



public class Controller implements Initializable {
    Rectangle full = new Rectangle(33, 33, Color.BLACK);
    @FXML
    private Scene scene;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Pane siderbarRight;

    @FXML
    private Button down;

    @FXML
    private Button up;

    @FXML
    private Button left;

    @FXML
    private Button right;

    @FXML
    private Button rleft;

    @FXML
    private Button rright;

    @FXML
    private Button start;

    @FXML
    protected GridPane gamePane;

    @FXML
    protected GridPane figurine;

    private double newY = 0;
    private double newX = 0;
    String[][] array = new String[21][10];
    Rectangle rect = new Rectangle(33, 33);

    int[][] numarray = {{0, 1, 0}, {1, 1, 1}, {0, 0, 0}};
    Rectangle[][] figArray = new Rectangle[3][3];
    int posY = 0;
    int posX = 3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int y = 0; y < numarray.length; y++) {
            for (int x = 0; x < numarray[y].length; x++) {
                if (numarray[y][x] == 1) {
                    figArray[y][x] = new Rectangle(33, 33, Color.BLACK);
                } else figArray[y][x] = new Rectangle(33, 33, Color.TRANSPARENT);
            }
        }

        for (int y = 0; y < figArray.length; y++) {
            for (int x = 0; x < figArray[y].length; x++) {
                GridPane.setRowIndex(figArray[y][x], posY + y);
                GridPane.setColumnIndex(figArray[y][x], posX + x);
                gamePane.getChildren().addAll(figArray[y][x]);
            }
        }

        keyPressed();
    }

    public void keyPressed() {
        boolean input = true;
        anchorPane.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                if(t.getCode() == KeyCode.UP || t.getCode()== KeyCode.W){
                    evenT("UP");
                }

                if(t.getCode() == KeyCode.DOWN || t.getCode()== KeyCode.S){
                    evenT("DOWN");
                }

                if(t.getCode() == KeyCode.LEFT || t.getCode()== KeyCode.A){
                    evenT("LEFT");
                }

                if(t.getCode() == KeyCode.RIGHT || t.getCode()== KeyCode.D){
                    evenT("RIGHT");
                }
            }
        });
    }


    public void evenT(String event){
        switch(event){
            case "DOWN":
                for (int y = 0; y < figArray.length; y++) {
                    for (int x = 0; x < figArray[y].length; x++) {
                        GridPane.setRowIndex(figArray[y][x], posY + y + 1);
                    }
                }
                posY++;
                break;

            case"UP":
                for (int y = 0; y < figArray.length; y++) {
                    for (int x = 0; x < figArray[y].length; x++) {
                        GridPane.setRowIndex(figArray[y][x], posY + y - 1);
                    }
                }
                posY--;
                break;

            case"LEFT":
                for (int y = 0; y < figArray.length; y++) {
                    for (int x = 0; x < figArray[y].length; x++) {
                        GridPane.setColumnIndex(figArray[y][x], posX + x - 1);
                    }
                }
                posX--;
                break;

            case"RIGHT":
                for (int y = 0; y < figArray.length; y++) {
                    for (int x = 0; x < figArray[y].length; x++) {
                        GridPane.setColumnIndex(figArray[y][x], posX + x + 1);
                    }
                }
                posX++;
                break;

        }
    }

    @FXML
    protected void buttonAction(ActionEvent e) {

        if (e.getSource() == down) {
            evenT("DOWN");
        }

        if (e.getSource() == up) {
            evenT("UP");
        }

        if (e.getSource() == left) {
            evenT("LEFT");
        }

        if (e.getSource() == right) {
            evenT("RIGHT");
        }
    }

    @FXML
    protected void handleKeyPressed(KeyEvent ke){
        System.out.println("Key Pressed: " + ke.getCode());
    }

    protected Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    /*
    public boolean currentFig(String spielZug,Spielfigur _figur,Spielfeld _meinSpielFeld){
        boolean currentFigurine = true;
        boolean currentRound = true;

        // aktuelle figur
        while(currentFigurine){
            //abfrage nach dem Spielzug
            spielZug = naechsterSpielzug();

            // falls abgebrochen werden soll
            if(spielZug.equals("q")){
                System.out.println("vom user abgebrochen!");
                currentRound = false;
                currentFigurine = false;
            }
            else{
                // bewegen der Spielfigur, sowie drucken des Spielfeldes
                _figur.bewegeSpielfigurKomplett(spielZug);
                _meinSpielFeld.printField();
                System.out.println("----------------------------");

                // testen ob kollision im spielfeld auftritt
                if(_figur.spielfigurIstBeweglich){
                    currentFigurine = true;
                }
                else currentFigurine = false;
            }
        }
        return currentRound;
    }

    public void round(String spielZug,Spielfeld meinSpielFeld){
        meinSpielFeld = new Spielfeld();
        boolean currentRound = true;
        boolean currentFigurine = true;

        // schleife über die runden
        while(currentRound){
            Spielfigur figur = new Spielfigur(meinSpielFeld,0,0,0);
            // erzeugung der Zufallsfigur
            figur = (newRandomFigur(meinSpielFeld, figur));

            // testen auf beweglichkeit am startpunkt im Spielfeld
            if(!figur.spielfigurIstBeweglich){
                currentRound = false;
                System.out.println("Game Over :(");
            }

            else{
                //aufruf funktion über die aktuelle Figur
                //rückgabe bestimmt ob runde vorbei ist.
                currentRound = currentFig(spielZug,figur,meinSpielFeld);
            }

            // löschen der vollen zeilen
            meinSpielFeld.loescheAlleVolleZeilen();
            meinSpielFeld.printField();
        }
    }

    public void playGame(){
        Spielfeld meinSpielFeld = new Spielfeld();
        String spielZug = "s";
        boolean roundActive = true;


        System.out.println("Willkommen bei Fivetetris");
        System.out.println("Im folgendem Spiel werden nach und nach"+
                "figuren erscheinen, die vom benutzer jeweils gedreht,"+
                "nach unten, links oder rechts bewegt werden können."+
                "besteht eine zeile nur aus blöcken von Spielfiguren, so wird diese gelöscht"+
                "Das Spiel ist zuende, wenn die oberste Zeile, belegt ist und kein neuer"+
                "Stein mehr spawnen kann.");
        //meinSpielFeld.printField();

        // Schleife über aktives Spiel
        while(roundActive){

            // aufruf der funktion über die runden
            round(spielZug, meinSpielFeld);

            // abfrage ob neugestartet werden soll oder nicht, nach manueller beendigung oder GameOver
            System.out.println("zum neustarten, bitte s drücken, zum beenden w, a, d oder q drücken");
            spielZug = naechsterSpielzug();

            // neustart oder nicht
            if(spielZug.equals("s")){
                roundActive = true;
            }
            else roundActive = false;
        }
    }

    public Spielfigur newRandomFigur(Spielfeld meinSpielFeld,Spielfigur figur){
        int zufall=(int)Math.round(Math.random()*7);

        switch (zufall){
            case 0:
                return figur = new ISpielfigur(meinSpielFeld,0,2,0) ;
            case 1:
                return figur = new OSpielfigur(meinSpielFeld,0,2,0) ;
            case 2:
                return figur = new SSpielfigur(meinSpielFeld,0,2,0) ;
            case 3:
                return figur = new TSpielfigur(meinSpielFeld,0,2,0) ;
            case 4:
                return figur = new ZSpielfigur(meinSpielFeld,0,2,0) ;
            case 5:
                return figur = new LSpielfigur(meinSpielFeld,0,2,0) ;
            case 6:
                return figur = new JSpielfigur(meinSpielFeld,0,2,0) ;
        }
        return figur = new ISpielfigur(meinSpielFeld,0,2,0);
    }

    private String naechsterSpielzug() {
        String eingabe = "";

        anchorPane.addEventFilter(KeyEvent.ANY, keyEvent -> {
            System.out.println(keyEvent);

            if ((keyEvent.getCode() == KeyCode.DOWN || keyEvent.getCode() == KeyCode.S )&& keyEvent.getEventType() == KEY_PRESSED) {
                eingabe = "s";
            }

            if ((keyEvent.getCode() == KeyCode.UP || keyEvent.getCode() == KeyCode.W) && keyEvent.getEventType() == KEY_PRESSED) {
                event("UP");
            }

            if ((keyEvent.getCode() == KeyCode.LEFT || keyEvent.getCode() == KeyCode.A) && keyEvent.getEventType() == KEY_PRESSED) {
                event("LEFT");
            }

            if ((keyEvent.getCode() == KeyCode.RIGHT || keyEvent.getCode() == KeyCode.D) && keyEvent.getEventType() == KEY_PRESSED) {
                event("RIGHT");
            }
        });

        return eingabe;
    }*/

    public void run(){
    }
}
