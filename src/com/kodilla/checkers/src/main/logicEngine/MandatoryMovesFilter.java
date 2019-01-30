package com.kodilla.checkers.src.main.logicEngine;

public class MandatoryMovesFilter {
    private Field field;

    private boolean checkIfFigureHasMandatoryMove(Field field) {
        boolean removeFlag = false;
        if (field.getLeftDown() == Move.MANDATORY_JUMP) {
            removeFlag = true;
        }
        if (field.getRightDown() == Move.MANDATORY_JUMP) {
            removeFlag = true;
        }
        if (field.getLeftUp() == Move.MANDATORY_JUMP) {
            removeFlag = true;
        }
        if (field.getRightUp() == Move.MANDATORY_JUMP) {
            removeFlag = true;
        }
        return removeFlag;
    }

    public boolean checkIfPlayerHasMandatoryMoves(Board board, Army army) {
        boolean playerHasMandatoryMove = false;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                field = board.getField(row, col);
                if (field.getArmy() == army && checkIfFigureHasMandatoryMove(field) == true) {
                    playerHasMandatoryMove = true;
                }
            }
        }
        return playerHasMandatoryMove;
    }

    public Board filterNonMandatory(Board board, int row, int col) {
        if (checkIfFigureHasMandatoryMove(board.getField(row, col)) == true) {
            if (board.getField(row, col).getLeftDown() == Move.AVAILABLE) {
                board.getField(row, col).setLeftDown(Move.NONE);
            }
            if (board.getField(row, col).getRightDown() == Move.AVAILABLE) {
                board.getField(row, col).setRightDown(Move.NONE);
            }
            if (board.getField(row, col).getLeftUp() == Move.AVAILABLE) {
                board.getField(row, col).setLeftUp(Move.NONE);
            }
            if (board.getField(row, col).getRightUp() == Move.AVAILABLE) {
                board.getField(row, col).setRightUp(Move.NONE);
            }
        }
        return board;
    }
}
