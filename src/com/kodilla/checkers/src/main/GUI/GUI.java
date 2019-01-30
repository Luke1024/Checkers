package com.kodilla.checkers.src.main.GUI;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logicEngine.*;

public class GUI extends Application {

    private GUILogic guiLogic = new GUILogic();

    private Field field;
    private Field fieldClicked;
    private int rowClicked;
    private int colClicked;
    private int row;
    private int col;
    private boolean moveFlag;
    Board board;

    public void GUIStart(){
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        Logic checkersLogic = new Logic(Mode.HUMANvsCOMPUTER, Difficulty.EASY);
        Group root = new Group();
        Scene scene = new Scene(root, 800, 800, Color.BLACK);
        primaryStage.setScene(scene);
        Group squares = new Group();
        Group checkers = new Group();
        Group possibleCheckers = new Group();
        Group mandatoryCheckers = new Group();
        checkers.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                squares.getChildren().clear();
                checkers.getChildren().clear();
                possibleCheckers.getChildren().clear();
                mandatoryCheckers.getChildren().clear();

                board = checkersLogic.getCheckerBoard();

                squares.getChildren().add(guiLogic.drawSquares());
                checkers.getChildren().add(guiLogic.drawCheckers(board,1));
                mandatoryCheckers.getChildren().add(guiLogic.drawMandatoryCheckers(board,0.3));
                col = (int) Math.floor(event.getX()/100);
                row = (int) Math.floor(event.getY()/100);
                possibleCheckers.getChildren().add(guiLogic.drawMovesOfActivatedPawn(board.getField(row,col),row,col,0.3));
                primaryStage.show();
            }
        });
        checkers.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                squares.getChildren().clear();
                checkers.getChildren().clear();
                possibleCheckers.getChildren().clear();
                mandatoryCheckers.getChildren().clear();

                board = checkersLogic.getCheckerBoard();

                squares.getChildren().add(guiLogic.drawSquares());
                checkers.getChildren().add(guiLogic.drawCheckers(board,1));
                col = (int) Math.floor(event.getX()/100);
                row = (int) Math.floor(event.getY()/100);
                colClicked = col;
                rowClicked = row;
                mandatoryCheckers.getChildren().add(guiLogic.drawMandatoryMovesOfClickedPawn(board.getField(row,col),row,col,0.5));
                possibleCheckers.getChildren().add(guiLogic.drawMovesOfActivatedPawn(board.getField(row,col),row, col,0.5));
                primaryStage.show();
            }
        });
        possibleCheckers.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                squares.getChildren().clear();
                checkers.getChildren().clear();
                possibleCheckers.getChildren().clear();
                mandatoryCheckers.getChildren().clear();

                board = checkersLogic.getCheckerBoard();

                squares.getChildren().add(guiLogic.drawSquares());
                checkers.getChildren().add(guiLogic.drawCheckers(board,1));
                col = (int) Math.floor(event.getX()/100);
                row = (int) Math.floor(event.getY()/100);
                board = checkersLogic.moveFigure(new MoveCoords(rowClicked,row,colClicked,col));

                squares.getChildren().add(guiLogic.drawSquares());
                checkers.getChildren().add(guiLogic.drawCheckers(board,1));
                mandatoryCheckers.getChildren().add(guiLogic.drawMandatoryCheckers(board,0.3));
                primaryStage.show();
            }
        });
        mandatoryCheckers.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                squares.getChildren().clear();
                checkers.getChildren().clear();
                possibleCheckers.getChildren().clear();
                mandatoryCheckers.getChildren().clear();

                board = checkersLogic.getCheckerBoard();

                squares.getChildren().add(guiLogic.drawSquares());
                checkers.getChildren().add(guiLogic.drawCheckers(board,1));
                col = (int) Math.floor(event.getX()/100);
                row = (int) Math.floor(event.getY()/100);
                board = checkersLogic.moveFigure(new MoveCoords(rowClicked,row,colClicked,col));

                squares.getChildren().add(guiLogic.drawSquares());
                checkers.getChildren().add(guiLogic.drawCheckers(board,1));
                mandatoryCheckers.getChildren().add(guiLogic.drawMandatoryCheckers(board,0.3));
                primaryStage.show();
            }
        });
        board = checkersLogic.getCheckerBoard();
        squares.getChildren().add(guiLogic.drawSquares());
        checkers.getChildren().add(guiLogic.drawCheckers(board,1));
        root.getChildren().add(squares);
        root.getChildren().add(checkers);
        root.getChildren().add(mandatoryCheckers);
        root.getChildren().add(possibleCheckers);
        primaryStage.show();
    }
}
