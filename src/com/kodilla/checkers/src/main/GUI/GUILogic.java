package com.kodilla.checkers.src.main.GUI;

import javafx.scene.Group;
import logicEngine.*;

public class GUILogic {
    private CheckersDrawingLogic checkersDrawinLogic = new CheckersDrawingLogic();
    private Mode mode = Mode.HUMANvsCOMPUTER;
    private Difficulty difficulty = Difficulty.EASY;
    private CheckersPatterns objectsPatterns = new CheckersPatterns();
    private Logic logic = new Logic(mode, difficulty);
    private Board checkerBoard;
    private Field field;

    public void loadBoard(Board board) {
        checkerBoard = logic.getCheckerBoard();
    }

    public Group drawSquares() {
        return objectsPatterns.drawSquares();
    }

    public Group drawCheckers(Board board, double opacity){
        return checkersDrawinLogic.drawCheckers(board, opacity);
    }
    public Group drawMandatoryCheckers(Board board ,double opacity){
        return checkersDrawinLogic.drawMandatoryCheckers(board, opacity);
    }
    public Group drawMovesOfActivatedPawn(Field field, int row, int col, double opacity){
        return checkersDrawinLogic.drawPossibleMoves(field, row, col, opacity);
    }
    public Group drawMandatoryMovesOfClickedPawn(Field field ,int row, int col, double opacity){
        return checkersDrawinLogic.drawMandatoryPossibleMoves(field, row, col, opacity);
    }
}
