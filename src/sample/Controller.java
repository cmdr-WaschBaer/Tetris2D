package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
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



    int[][] numarray = {{0, 1, 0}, {1, 1, 1}, {0, 0, 0}};
    Rectangle[][] figArray = new Rectangle[3][3];
    int posY = 0;
    int posX = 7;
    List<NodeParent> list = new ArrayList<>();

    final static int maxR = 20;
    final static int maxC = 9;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        for (int y = 0; y < numarray.length; y++) {
            for (int x = 0; x < numarray[y].length; x++) {
                if (numarray[y][x] == 1) {
                    figArray[y][x] = new Rectangle(33, 33, Color.BLACK);
                }
            }
        }

        for (int y = 0; y < figArray.length; y++) {
            for (int x = 0; x < figArray[y].length; x++) {
                if(figArray[y][x]!=null) {
                    GridPane.setRowIndex(figArray[y][x], posY + y);
                    GridPane.setColumnIndex(figArray[y][x], posX + x);
                    gamePane.getChildren().addAll(figArray[y][x]);
                    list.add(new NodeParent(y, x, figArray[y][x]));
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

                if (consumed) {
                    keyEvent.consume();
                }
            }
        });
        pane.setFocusTraversable(true);
    }

    private void evenT(String event){
        int neuZeile=posY;
        int neuSpalte=posX;
        int maxSpalte = figArray[0].length-1;
        int maxZeile = figArray.length-1;

        int nodeIndex = 0;
        boolean allowed = true;



        switch(event){
            case "DOWN":
                for (int y = 0; y < figArray.length; y++) {
                    for (int x = 0; x < figArray[y].length; x++) {
                        if(figArray[y][x]!=null) {
                            if (posY+y+1 >maxR) {
                                allowed = false;
                            }
                        }
                    }

                }
                if(allowed) {
                        moveVer(+ 1);
                    for (int y = 0; y < figArray.length; y++) {
                        for (int x = 0; x < figArray[y].length; x++) {
                            if (figArray[y][x] != null) {
                                nodeIndex = findNode(posY + y, posY + x);
                                if (nodeIndex != -1) {
                                    list.get(nodeIndex).updateNode(posY+y+1,posX);
                                }
                            }
                        }
                    }
                    posY++;
                }
                break;

            case"UP":
                for (int y = 0; y < figArray.length; y++) {
                    for (int x = 0; x < figArray[y].length; x++) {
                        if(figArray[y][x]!=null) {
                            if (posY+y-1 <0) {
                                allowed = false;
                            }
                        }
                    }

                }
                if(allowed) {
                    moveVer(-1);
                    for (int y = 0; y < figArray.length; y++) {
                        for (int x = 0; x < figArray[y].length; x++) {
                            if (figArray[y][x] != null) {
                                nodeIndex = findNode(posY + y, posY + x);
                                if (nodeIndex != -1) {
                                    list.get(nodeIndex).updateNode(posY+y-1,posX);
                                }
                            }
                        }
                    }
                    posY--;
                }

                break;

            case"LEFT":
                for (int y = 0; y < figArray.length; y++) {
                    for (int x = 0; x < figArray[y].length; x++) {
                        if(figArray[y][x]!=null) {
                            if (posX + x - 1<0) {
                                allowed = false;
                            }
                        }
                    }

                }
                if(allowed) {
                    moveHor(-1);
                    for (int y = 0; y < figArray.length; y++) {
                        for (int x = 0; x < figArray[y].length; x++) {
                            if (figArray[y][x] != null) {
                                nodeIndex = findNode(posY + y, posY + x);
                                if (nodeIndex != -1) {
                                    list.get(nodeIndex).updateNode(posY+y,posX+x-1);
                                }
                            }
                        }
                    }
                    posX--;
                }
                break;

            case"RIGHT":
                for (int y = 0; y < figArray.length; y++) {
                    for (int x = 0; x < figArray[y].length; x++) {
                        if(figArray[y][x]!=null) {
                            if (posX+x+1>maxC) {
                                allowed = false;
                            }
                        }
                    }

                }
                if(allowed) {
                    moveHor(+1);
                    for (int y = 0; y < figArray.length; y++) {
                        for (int x = 0; x < figArray[y].length; x++) {
                            if (figArray[y][x] != null) {
                                nodeIndex = findNode(posY + y, posY + x);
                                if (nodeIndex != -1) {
                                    list.get(nodeIndex).updateNode(posY+y,posX+x+1);
                                }
                            }
                        }
                    }
                    posX++;
                }
                break;

            case "ENTER":
                break;

            case "Q":
                break;

            case "E":
                break;

        }
    }

    private  void moveHor(int direction){
        for (int y = 0; y < figArray.length; y++) {
            for (int x = 0; x < figArray[y].length; x++) {
                if (figArray[y][x] != null) {
                    GridPane.setColumnIndex(figArray[y][x],posX + x+direction);
                }
            }
        }
    }

    private void moveVer(int direction){
        for (int y = 0; y < figArray.length; y++) {
            for (int x = 0; x < figArray[y].length; x++) {
                if (figArray[y][x] != null) {
                    GridPane.setRowIndex(figArray[y][x], posY + y +direction);
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

    protected Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    protected int findNode(int _row,int _col){
        for(int i=0;i<list.size();i++){
         if(list.get(i).getRow()== _row && list.get(i).getColumn()==_col){
             return i;
         }
        }
        return -1;
    }


    public void run(){
    }
}
