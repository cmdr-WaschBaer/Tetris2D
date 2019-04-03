package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



public class Controller implements Initializable {

    @FXML
    private Button down;

    @FXML
    private Button up;

    @FXML
    private Button left;

    @FXML
    private Button right;


    @FXML
    protected GridPane gamePane;

    final int[][] TSpielfigur = {{0,1,0}, {1,1,1}, {0,0,0},{0,0,0}};
    final int[][] ISpielfigur = {{0,1,0}, {0,1,0}, {0,1,0},{0,1,0}};
    final int[][] OSpielfigur = {{1,1,0}, {1,1,0}, {0,0,0},{0,0,0}};
    final int[][] ZSpielfigur = {{1,1,0}, {0,1,1}, {0,0,0},{0,0,0}};
    final int[][] SSpielfigur = {{0,1,1}, {1,1,0}, {0,0,0},{0,0,0}};
    final int[][] LSpielfigur = {{1,0,0}, {1,0,0}, {1,1,0},{0,0,0}};
    final int[][] JSpielfigur = {{0,0,1}, {0,0,1}, {0,1,1},{0,0,0}};

    boolean newFig = false;


    protected int posY = 0;
    protected int posX = 7;

    protected int[][]field=new int[21][10];
    protected Node[][] playfield = new Node[21][10];

    final static int maxR = 20;
    final static int maxC = 9;
    protected int[][]figur = new int[4][3];
    protected Rectangle[][] figArray = new Rectangle[4][3];




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        figur = randomizedFig();

        for (int y = 0; y < figur.length; y++) {
            for (int x = 0; x < figur[y].length; x++) {
                if (figur[y][x] == 1) {
                    figArray[y][x] = new Rectangle(33, 33, Color.BLACK);
                }
            }
        }

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 3; x++) {
                if(figArray[y][x]!=null) {
                    Rectangle rectangle = new Rectangle(33,33,Color.BLACK);
                    GridPane.setRowIndex(rectangle, posY + y);
                    GridPane.setColumnIndex(rectangle, posX + x);
                    gamePane.getChildren().addAll(rectangle);
                    playfield[posY+y][posX+x] = rectangle;
                }
            }
        }

        registerKeyboardHandler(gamePane);
    }

    private void registerKeyboardHandler(Pane pane) {
        pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                boolean consumed = false;
                newFig = false;

                switch (keyEvent.getCode()) {
                    case DOWN:
                        System.out.println("Key Pressed: " + keyEvent.getCode());
                        evenT("DOWN");
                        consumed = true;
                        break;
                    case LEFT:
                        System.out.println("Key Pressed: " + keyEvent.getCode());
                        evenT("LEFT");
                        consumed = true;
                        break;
                    case RIGHT:
                        System.out.println("Key Pressed: " + keyEvent.getCode());
                        evenT("RIGHT");
                        consumed = true;
                        break;
                    case UP:
                        System.out.println("Key Pressed: " + keyEvent.getCode());
                        evenT("UP");
                        consumed = true;
                        break;

                    case Q:
                        System.out.println("Key Pressed: " + keyEvent.getCode());
                        evenT("Q");
                        consumed = true;
                        break;

                    case E:
                        System.out.println("Key Pressed: " + keyEvent.getCode());
                        evenT("E");
                        consumed = true;
                        break;

                    case ENTER:
                        System.out.println("Key Pressed: " + keyEvent.getCode());
                        evenT("ENTER");
                        consumed = true;
                        break;

                    default:
                        break;
                }
/*
                System.out.println("--------------------------------");
                for(int y=0;y<field.length;y++){
                    System.out.println();
                    for(int x=0;x<field[0].length;x++){
                        System.out.print(field[y][x]);
                    }
                }
*/

                if (consumed) {
                    keyEvent.consume();
                }
            }
        });
        pane.setFocusTraversable(true);
    }

    private boolean evenT(String event){

        boolean endOfField = false;

        switch(event){
            case "DOWN":
                if(!checkForCollision(posY+1,posX)){
                    changePosition(posY,posX,posY+1,posX);
                    posY++;
                }
                if (newFig) {
                    createNewFigurine();
                }
                break;

            case"UP":
                if(!checkForCollision(posY-1,posX)){
                    changePosition(posY,posX,posY-1,posX);
                    posY--;
                }
                if (newFig) {
                    createNewFigurine();
                }
                break;

            case"LEFT":
                if(!checkForCollision(posY,posX-1)){
                    changePosition(posY,posX,posY,posX-1);
                    posX--;
                }
                if (newFig) {
                    createNewFigurine();
                }
                break;

            case"RIGHT":
                if(!checkForCollision(posY,posX+1)){
                    changePosition(posY,posX,posY,posX+1);
                    posX++;
                }
                if (newFig) {
                    createNewFigurine();
                }
                break;

            case "ENTER":
                break;

            case "Q":
                break;

            case "E":
                break;

        }
        return endOfField;
    }

    private void placeNode(int y, int x,int newPosY,int newPosX){
        Rectangle rectangle = new Rectangle(33,33,Color.BLACK);
        GridPane.setRowIndex(rectangle, newPosY );
        GridPane.setColumnIndex(rectangle, newPosX);
        gamePane.getChildren().addAll(rectangle);
        playfield[newPosY][newPosX] = rectangle;
    }

    private void clearPane(){
        Node node = gamePane.getChildren().get(0);
        gamePane.getChildren().clear();
        gamePane.getChildren().add(0,node);
    }

    private void changePosition(int oldPosY,int oldPosX,int newPosY,int newPosX){
        clearPane();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 3; x++) {
                if (figArray[y][x] != null) {
                    playfield[oldPosY + y][oldPosX + x] = null;
                    placeNode(y, x, newPosY + y, newPosX + x);
                }
            }
        }
    }



    @FXML
    private void buttonAction(ActionEvent e) {

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

    protected  boolean checkForCollision(int newRow, int newCol){
        boolean collision = false;
        Node[][]buffer = playfield;

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 3; x++) {
                if(figArray[y][x]!=null) {
                    buffer[posY+y][posX+x] = null;
                }
            }
        }
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 3; x++) {
                if(figArray[y][x]!=null) {
                    if (wallCollision(newCol + x)) {
                        collision = true;
                    } else if (objectCollision(buffer[newRow][newCol+x], newRow + y, newCol + x)) {
                        newFig = true;
                        collision = true;
                    } else if(endOfField(newRow)){
                        newFig = true;
                        collision = true;
                    }
                    else collision = false;
                }
            }
        }
        return collision;
    }

    protected boolean wallCollision(int _newCol){
        if(_newCol>=maxC || _newCol<=0){
            return true;
        }
        return false;
    }

    protected boolean objectCollision(Node buffer,int _newCol, int _newRow){
            if(buffer != null){
                return true;
            }
            else return false;
    }

    protected  boolean endOfField(int _newRow){
        if(_newRow<maxR){
            return false;
        }
        else return true;
    }

    protected void createNewFigurine(){
        randomizedFig();
        posY = 0;
        posX = 3;
        for (int y = 0; y < figArray.length; y++) {
            for (int x = 0; x < figArray[y].length; x++) {
                if(figArray[y][x]!=null) {
                    Rectangle rectangle = new Rectangle(33,33,Color.BLACK);
                    GridPane.setRowIndex(rectangle, posY + y);
                    GridPane.setColumnIndex(rectangle, posX + x);
                    gamePane.getChildren().addAll(rectangle);
                    playfield[posY+y][posX+x] = rectangle;
                }
            }
        }
    }

    public int[][] randomizedFig(){
        int[][] _figur;
        int zufall=(int)Math.round(Math.random()*7);

        switch (zufall){
            case 0:
                _figur = ISpielfigur;
                break;
            case 1:
                _figur = OSpielfigur;
                break;
            case 2:
                _figur = SSpielfigur;
                break;
            case 3:
                _figur = TSpielfigur;
                break;
            case 4:
                _figur = ZSpielfigur;
                break;
            case 5:
                _figur = LSpielfigur;
                break;
            case 6:
                _figur = JSpielfigur;
                break;
            default:
                _figur = OSpielfigur;
                break;
        }

        for (int y = 0; y < _figur.length; y++) {
            for (int x = 0; x < _figur[0].length; x++) {
                if (_figur[y][x] == 1) {
                    figArray[y][x] = new Rectangle(33, 33, Color.BLACK);
                }
            }
        }
        return figur;
    }

}
