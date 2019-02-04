package com.kodilla.checkers.src.main.GUI;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.kodilla.checkers.src.main.logicEngine.Board;
import com.kodilla.checkers.src.main.logicEngine.MoveCoords;

public class GUI extends Application {

    private GUILogic guiLogic = new GUILogic();
    private int rowClicked;
    private int colClicked;
    private int row;
    private int col;
    private Board board;

    public void GUIStart(){
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 800, Color.BLACK);
        primaryStage.setScene(scene);
        Group squares = new Group();
        Group checkersActive = new Group();
        Group checkers = new Group();
        Group possibleCheckers = new Group();
        Group mandatoryCheckers = new Group();
        Group texts = new Group();
        checkersActive.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                texts.getChildren().clear();
                squares.getChildren().clear();
                checkers.getChildren().clear();
                checkersActive.getChildren().clear();
                possibleCheckers.getChildren().clear();
                mandatoryCheckers.getChildren().clear();

                board = guiLogic.getBoard();

                col = (int) Math.floor(event.getX()/100);
                row = (int) Math.floor(event.getY()/100);

                squares.getChildren().add(guiLogic.drawSquares());
                checkers.getChildren().add(guiLogic.drawCheckers(board,1));
                checkersActive.getChildren().add(guiLogic.drawActiveCheckers(board,1));
                possibleCheckers.getChildren().add(guiLogic.drawMovesOfActivatedPawn(board.getField(row,col),row,col,0.3));
                mandatoryCheckers.getChildren().add(guiLogic.drawMandatoryCheckers(board,0.3));
                primaryStage.show();
            }
        });
        checkersActive.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                texts.getChildren().clear();
                squares.getChildren().clear();
                checkers.getChildren().clear();
                checkersActive.getChildren().clear();
                possibleCheckers.getChildren().clear();
                mandatoryCheckers.getChildren().clear();

                board = guiLogic.getBoard();

                col = (int) Math.floor(event.getX()/100);
                row = (int) Math.floor(event.getY()/100);
                colClicked = col;
                rowClicked = row;

                squares.getChildren().add(guiLogic.drawSquares());
                checkers.getChildren().add(guiLogic.drawCheckers(board,1));
                checkersActive.getChildren().add(guiLogic.drawActiveCheckers(board,1));
                possibleCheckers.getChildren().add(guiLogic.drawMovesOfActivatedPawn(board.getField(row,col),row, col,0.5));
                primaryStage.show();
            }
        });
        possibleCheckers.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                texts.getChildren().clear();
                squares.getChildren().clear();
                checkers.getChildren().clear();
                checkersActive.getChildren().clear();
                possibleCheckers.getChildren().clear();
                mandatoryCheckers.getChildren().clear();

                col = (int) Math.floor(event.getX()/100);
                row = (int) Math.floor(event.getY()/100);

                board = guiLogic.moveFigure(board,new MoveCoords(rowClicked,row,colClicked,col));

                squares.getChildren().add(guiLogic.drawSquares());
                checkers.getChildren().add(guiLogic.drawCheckers(board,1));
                checkersActive.getChildren().add(guiLogic.drawActiveCheckers(board,1));
                possibleCheckers.getChildren().add(guiLogic.drawMovesOfActivatedPawn(board.getField(row,col),row,col,0.3));
                mandatoryCheckers.getChildren().add(guiLogic.drawMandatoryCheckers(board,0.3));
                if(!guiLogic.checkIfGameInProgress()){
                    try {
                        texts.getChildren().add(guiLogic.getEndText(guiLogic.checkWhoWin()));
                    } catch (Exception e){}
                }
                primaryStage.show();
            }
        });
        board = guiLogic.getBoard();
        texts.getChildren().add(guiLogic.getStartText());
        squares.getChildren().add(guiLogic.drawSquares());
        checkers.getChildren().add(guiLogic.drawCheckers(board,1));
        mandatoryCheckers.getChildren().add(guiLogic.drawMandatoryCheckers(board, 0.3));
        checkersActive.getChildren().add(guiLogic.drawActiveCheckers(board,1));
        root.getChildren().add(squares);
        root.getChildren().add(mandatoryCheckers);
        root.getChildren().add(checkers);
        root.getChildren().add(checkersActive);
        root.getChildren().add(possibleCheckers);
        root.getChildren().add(texts);
        primaryStage.show();
    }
}
