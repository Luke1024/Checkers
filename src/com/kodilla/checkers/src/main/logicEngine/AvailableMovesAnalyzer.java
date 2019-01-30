package com.kodilla.checkers.src.main.logicEngine;

public class AvailableMovesAnalyzer {
    private Field field;
    private Field field2;
    private Field field3;
    private Move move;
    private CoronationAnalyzer coronationAnalyzer = new CoronationAnalyzer();
    private MandatoryMovesFilter mandatoryMovesFilter = new MandatoryMovesFilter();

    private Move getDirectionalMove(Board board,int row, int col, int normalMoveRow, int normalMoveCol, int jumpRow, int jumpCol){
        field = board.getField(row, col);
        try {
            field2 = board.getField(row + normalMoveRow, col + normalMoveCol);
            if(field2.getArmy()== Army.NONE){
                move = Move.AVAILABLE;
            }
            if (field.getArmy() == field2.getArmy()) {
                move = Move.NONE;
            }
            if(field2.getArmy() != field.getArmy() && field2.getArmy()!= Army.NONE){
                try {
                    field3 = board.getField(row + jumpRow, col + jumpCol);
                    if (field3.getArmy() == Army.NONE) {
                        move = Move.MANDATORY_JUMP;
                    } else {
                        move = Move.NONE;
                    }
                } catch (Exception e) {
                    move = Move.NONE;
                }
            }
        } catch (Exception e) {
            move = Move.NONE;
        }
        return move;
    }

    private Board getLeftUpMove(Board board ,int row, int col) {
        board.getField(row,col).setLeftUp(getDirectionalMove(board,row,col,-1,-1,-2,-2));
        return board;
    }
    private Board getRightUpMove(Board board ,int row, int col){
        board.getField(row,col).setRightUp(getDirectionalMove(board,row,col,-1,1,-2,2));
        return board;
    }
    private Board getLeftDownMove(Board board ,int row, int col){
        board.getField(row,col).setLeftDown(getDirectionalMove(board,row,col,1,-1,2,-2));
        return board;
    }
    private Board getRightDownMove(Board board ,int row, int col){
        board.getField(row,col).setRightDown(getDirectionalMove(board,row,col,1,1,2,2));
        return board;
    }

    private Board getAvailableMovesForFigure(Board board ,int row, int col, Army army) {
        board = coronationAnalyzer.getCoronation(board,row, col);
        field = board.getField(row, col);
        if (field.getArmy() == army) {
            if ((field.getArmy() == Army.TOP) && (field.getType() == Type.MAN)) {
                board = getLeftDownMove(board,row, col);
                board = getRightDownMove(board,row, col);
            }
            if ((field.getArmy() == Army.BOTTOM) && (field.getType() == Type.MAN)) {
                board = getLeftUpMove(board ,row, col);
                board = getRightUpMove(board ,row, col);
            }
            if (field.getType() == Type.KING) {
                board = getLeftDownMove(board, row, col);
                board = getRightDownMove(board ,row, col);
                board = getLeftUpMove(board ,row, col);
                board = getRightUpMove(board ,row, col);
            }
        }
        return board;
    }

    public Board getAvailableMoves(Board board ,Army army){
        for(int row=0; row<8; row++){
            for(int col=0; col<8; col++){
                board = getAvailableMovesForFigure(board ,row, col, army);
                board = mandatoryMovesFilter.filterNonMandatory(board, row, col);
            }
        }
        return board;
    }

    public boolean checkIfPlayerMustJump(Board board, Army army){
        board = getAvailableMoves(board, army);
        return mandatoryMovesFilter.checkIfPlayerHasMandatoryMoves(board, army);
    }
}
