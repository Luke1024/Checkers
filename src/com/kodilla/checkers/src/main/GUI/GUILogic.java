package com.kodilla.checkers.src.main.GUI;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import com.kodilla.checkers.src.main.logicEngine.*;

public class GUILogic {

    private CheckersPatterns checkersPatterns = new CheckersPatterns();
    private Logic logic = new Logic();
    private Field field;

    public Board getBoard(){
        return logic.getCheckerBoard();
    }

    public Board moveFigure(Board board, MoveCoords moveCoords) {
        return logic.moveFigure(board,moveCoords);
    }

    public Group drawSquares() {
        return checkersPatterns.drawSquares();
    }

    public Group drawActiveCheckers(Board board, double opacity){
        Group checkers = new Group();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                field = board.getField(row, col);
                if(logic.isBottomMove() && field.getArmy()== Army.BOTTOM) {
                    checkers.getChildren().add(checkersPatterns.drawingChecker(row, col, field.getArmy(), field.getType(), opacity));
                }
                if(!logic.isBottomMove() && field.getArmy()== Army.TOP){
                    checkers.getChildren().add(checkersPatterns.drawingChecker(row, col, field.getArmy(), field.getType(), opacity));
                }
            }
        }
        return checkers;
    }

    public Group drawCheckers(Board board, double opacity){
        Group checkers = new Group();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                field = board.getField(row, col);
                if(logic.isBottomMove() && field.getArmy()== Army.TOP) {
                    checkers.getChildren().add(checkersPatterns.drawingChecker(row, col, field.getArmy(), field.getType(), opacity));
                }
                if(!logic.isBottomMove() && field.getArmy()== Army.BOTTOM){
                    checkers.getChildren().add(checkersPatterns.drawingChecker(row, col, field.getArmy(), field.getType(), opacity));
                }
            }
        }
        return checkers;
    }

    public Group drawMandatoryCheckers(Board board , double opacity){
        Group mandatoryCheckers = new Group();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                field = board.getField(row, col);
                if (field.getLeftDown() == Move.MANDATORY_JUMP) {
                    mandatoryCheckers.getChildren().add(checkersPatterns.getMandatorySquare(row,col));
                    mandatoryCheckers.getChildren().add(checkersPatterns.drawingChecker(row + 2, col - 2, field.getArmy(), field.getType(), opacity));
                }
                if (field.getRightDown() == Move.MANDATORY_JUMP) {
                    mandatoryCheckers.getChildren().add(checkersPatterns.getMandatorySquare(row,col));
                    mandatoryCheckers.getChildren().add(checkersPatterns.drawingChecker(row + 2, col + 2, field.getArmy(), field.getType(), opacity));
                }
                if (field.getLeftUp() == Move.MANDATORY_JUMP) {
                    mandatoryCheckers.getChildren().add(checkersPatterns.getMandatorySquare(row,col));
                    mandatoryCheckers.getChildren().add(checkersPatterns.drawingChecker(row - 2, col - 2, field.getArmy(), field.getType(), opacity));
                }
                if (field.getRightUp() == Move.MANDATORY_JUMP) {
                    mandatoryCheckers.getChildren().add(checkersPatterns.getMandatorySquare(row,col));
                    mandatoryCheckers.getChildren().add(checkersPatterns.drawingChecker(row - 2, col + 2, field.getArmy(), field.getType(), opacity));
                }
            }
        }
        return mandatoryCheckers;
    }
    public Group drawMovesOfActivatedPawn(Field field, int row, int col, double opacity){
        Group checker = new Group();
        if (field.getLeftDown() == Move.AVAILABLE) {
            checker.getChildren().add(checkersPatterns.drawingChecker(row + 1, col - 1, field.getArmy(), field.getType(), opacity));
        }
        if (field.getLeftDown() == Move.MANDATORY_JUMP) {
            checker.getChildren().add(checkersPatterns.drawingChecker(row + 2, col - 2, field.getArmy(), field.getType(), opacity));
        }
        if (field.getRightDown() == Move.AVAILABLE) {
            checker.getChildren().add(checkersPatterns.drawingChecker(row + 1, col + 1, field.getArmy(), field.getType(), opacity));
        }
        if (field.getRightDown() == Move.MANDATORY_JUMP) {
            checker.getChildren().add(checkersPatterns.drawingChecker(row + 2, col + 2, field.getArmy(), field.getType(), opacity));
        }
        if (field.getLeftUp() == Move.AVAILABLE) {
            checker.getChildren().add(checkersPatterns.drawingChecker(row - 1, col - 1, field.getArmy(), field.getType(), opacity));
        }
        if (field.getLeftUp() == Move.MANDATORY_JUMP) {
            checker.getChildren().add(checkersPatterns.drawingChecker(row - 2, col - 2, field.getArmy(), field.getType(), opacity));
        }
        if (field.getRightUp() == Move.AVAILABLE) {
            checker.getChildren().add(checkersPatterns.drawingChecker(row - 1, col + 1, field.getArmy(), field.getType(), opacity));
        }
        if (field.getRightUp() == Move.MANDATORY_JUMP) {
            checker.getChildren().add(checkersPatterns.drawingChecker(row - 2, col + 2, field.getArmy(), field.getType(), opacity));
        }
        return checker;
    }

    public boolean checkIfGameInProgress(){
        return logic.isGameInProgress();
    }

    public WhoWin checkWhoWin(){
        return logic.getWhoWin();
    }

    public Text getStartText(){
        Text text = new Text(130,435,"White Move");
        text.setFont(new Font(100));
        text.setFill(Color.rgb(255,215,0));


        return text;
    }

    public Group getEndText(WhoWin whoWin) {
        Text text = new Text();
        if (whoWin == WhoWin.BLACK) {
            text = new Text(175,435,"Black Win!");
            text.setFont(new Font(100));
            text.setFill(Color.rgb(150,150,150));
        }
        if (whoWin == WhoWin.WHITE) {
            text = new Text(165,435,"White Win!");
            text.setFont(new Font(100));
            text.setFill(Color.rgb(255,215,0));
        }
        Group texts = new Group();
        texts.getChildren().add(text);
        return texts;
    }
}
