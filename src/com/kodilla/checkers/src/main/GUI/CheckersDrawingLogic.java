package com.kodilla.checkers.src.main.GUI;

import javafx.scene.Group;
import logicEngine.*;

public class CheckersDrawingLogic {
    CheckersPatterns checkersPatterns = new CheckersPatterns();

    public Group drawCheckers(Board board, double opacity) {
        Group checkers = new Group();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Field field = board.getField(row, col);
                checkers.getChildren().add(checkersPatterns.drawingChecker(row, col, field.getArmy(), field.getType(), opacity));
            }
        }
        return checkers;
    }

    public Group drawMandatoryCheckers(Board board ,double opacity) {
        //draw every mandatory checker without overlapping (logic engine showing available mandatory moves for given player)
        Group mandatoryCheckers = new Group();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Field field = board.getField(row, col);
                drawMandatoryPossibleMoves(field,row,col,opacity);
            }
        }
        return mandatoryCheckers;
    }

    public Group drawPossibleMoves(Field field, int row, int col, double opacity){
        Group checker = new Group();
        if (field.getLeftDown() == Move.AVAILABLE) {
            checker.getChildren().add(checkersPatterns.drawingChecker(row + 1, col - 1, field.getArmy(), field.getType(), opacity));
        }
        if (field.getRightDown() == Move.AVAILABLE) {
            checker.getChildren().add(checkersPatterns.drawingChecker(row + 1, col + 1, field.getArmy(), field.getType(), opacity));
        }
        if (field.getLeftUp() == Move.AVAILABLE) {
            checker.getChildren().add(checkersPatterns.drawingChecker(row - 1, col - 1, field.getArmy(), field.getType(), opacity));
        }
        if (field.getRightUp() == Move.AVAILABLE) {
            checker.getChildren().add(checkersPatterns.drawingChecker(row - 1, col + 1, field.getArmy(), field.getType(), opacity));
        }
        return checker;
    }

    public Group drawMandatoryPossibleMoves(Field field, int row, int col, double opacity){
        Group checker = new Group();
        if (field.getLeftDown() == Move.MANDATORY_JUMP) {
            checker.getChildren().add(checkersPatterns.drawMandatoryChecker(row + 2, col - 2, field.getArmy(), field.getType(), opacity));
        }
        if (field.getRightDown() == Move.MANDATORY_JUMP) {
            checker.getChildren().add(checkersPatterns.drawMandatoryChecker(row + 2, col + 2, field.getArmy(), field.getType(), opacity));
        }
        if (field.getLeftUp() == Move.MANDATORY_JUMP) {
            checker.getChildren().add(checkersPatterns.drawMandatoryChecker(row - 2, col - 2, field.getArmy(), field.getType(), opacity));
        }
        if (field.getRightUp() == Move.MANDATORY_JUMP) {
            checker.getChildren().add(checkersPatterns.drawMandatoryChecker(row - 2, col + 2, field.getArmy(), field.getType(), opacity));
        }
        return checker;
    }
}
