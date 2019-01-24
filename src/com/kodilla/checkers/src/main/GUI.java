package com.kodilla.checkers.src.main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GUI extends Application {

    private GraphicObjectsPatterns guiPatterns = new GraphicObjectsPatterns();
    private Board board = new Board();
    private Field field;
    private Field fieldClicked;
    int rowClicked;
    int colClicked;
    boolean moveFlag;

    public void GUIStart() {
        launch();
    }

    public Group drawCheckers(){
        Group checkers = new Group();
        for(int row=0;row<8;row++){
            for(int col=0;col<8;col++) {
                field = board.getField(row,col);
                checkers.getChildren().add(guiPatterns.getCheckers(row, col, field.getArmy(), field.getType(),1));
            }
        }
        return checkers;
    }
    public boolean switchMoveFlag(boolean moveFlag, Field fieldClicked){
        if(board.checkIfPlayerHasMandatoryMove(fieldClicked.getArmy())==false){
            if(moveFlag==true){
                moveFlag=false;
            } else {
                moveFlag=true;
            }
        }
        return moveFlag;
    }

    @Override
    public void start(Stage primaryStage) {
        board.startingPositions();
        Group root = new Group();
        Scene scene = new Scene(root, 800, 800, Color.BLACK);
        primaryStage.setScene(scene);
        Group squares = new Group();
        Group checkers = new Group();
        Group possibleCheckers = new Group();
        moveFlag = false;
        checkers.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                squares.getChildren().clear();
                checkers.getChildren().clear();
                possibleCheckers.getChildren().clear();
                squares.getChildren().add(guiPatterns.drawSquares());
                checkers.getChildren().add(drawCheckers());
                int col = (int) Math.floor(event.getX()/100);
                int row = (int) Math.floor(event.getY()/100);
                checkers.getChildren().add(guiPatterns.drawPossibleMoves(board.getField(row, col), row, col, moveFlag, 0.2));
                primaryStage.show();
            }
        });
        checkers.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                squares.getChildren().clear();
                checkers.getChildren().clear();
                squares.getChildren().add(guiPatterns.drawSquares());
                checkers.getChildren().add(drawCheckers());
                int col = (int) Math.floor(event.getX()/100);
                int row = (int) Math.floor(event.getY()/100);
                fieldClicked = board.getField(row,col);
                rowClicked = row;
                colClicked = col;
                possibleCheckers.getChildren().add(guiPatterns.drawPossibleMoves(board.getField(row, col), row, col, moveFlag ,0.5));
                primaryStage.show();
            }
        });
        possibleCheckers.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                squares.getChildren().clear();
                checkers.getChildren().clear();
                possibleCheckers.getChildren().clear();
                int col = (int) Math.floor(event.getX()/100);
                int row = (int) Math.floor(event.getY()/100);
                board.moveFigure(new MoveCoords(rowClicked,row,colClicked,col));
                moveFlag = switchMoveFlag(moveFlag,fieldClicked);
                board.getAvailableMoves();
                squares.getChildren().add(guiPatterns.drawSquares());
                checkers.getChildren().add(drawCheckers());
                primaryStage.show();
            }
        });
        squares.getChildren().add(guiPatterns.drawSquares());
        checkers.getChildren().add(drawCheckers());
        root.getChildren().add(squares);
        root.getChildren().add(checkers);
        root.getChildren().add(possibleCheckers);
        primaryStage.show();
    }
}
