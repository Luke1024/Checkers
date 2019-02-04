package com.kodilla.checkers.src.main.logicEngine;

public class AvailableMovesAnalyzer {
    private Field field;
    private Move move;

    private Board getCoronation(Board board, int row, int col){
        field= board.getField(row, col);
        if(field.getType()== Type.MAN){
            if(row==0 && field.getArmy()== Army.BOTTOM) board.getField(row,col).setType(Type.KING);
            if(row==7 && field.getArmy()== Army.TOP) board.getField(row,col).setType(Type.KING);
        }
        return board;
    }

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

    private boolean checkIfFigureHasAvailableMoves(Field field) {
        boolean figureHasAvailableMoves = false;
        if (field.getLeftDown() != Move.NONE) {
            figureHasAvailableMoves = true;
        }
        if (field.getRightDown() != Move.NONE) {
            figureHasAvailableMoves = true;
        }
        if (field.getLeftUp() != Move.NONE) {
            figureHasAvailableMoves = true;
        }
        if (field.getRightUp() != Move.NONE) {
            figureHasAvailableMoves = true;
        }
        return figureHasAvailableMoves;
    }

    private boolean checkIfPlayerHasMandatoryMoves(Board board, Army army) {
        boolean playerHasMandatoryMove = false;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                field = board.getField(row, col);
                if (field.getArmy() == army && checkIfFigureHasMandatoryMove(field)) {
                    playerHasMandatoryMove = true;
                }
            }
        }
        return playerHasMandatoryMove;
    }

    private Board filterNonMandatory(Board board, Army army) {
        if (checkIfPlayerHasMandatoryMoves(board,army)) {
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
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
            }
        }
        return board;
    }

    private Move getDirectionalMove(Board board, int row, int col, int normalMoveRow, int normalMoveCol, int jumpRow, int jumpCol){
        Field fieldOneStepFurther;
        Field fieldTwoStepsFurther;
        field = board.getField(row, col);
        try {
            fieldOneStepFurther = board.getField(row + normalMoveRow, col + normalMoveCol);
            if(fieldOneStepFurther.getArmy()== Army.NONE){
                move = Move.AVAILABLE;
            }
            if (field.getArmy() == fieldOneStepFurther.getArmy()) {
                move = Move.NONE;
            }
            if(fieldOneStepFurther.getArmy() != field.getArmy() && fieldOneStepFurther.getArmy() != Army.NONE){
                try {
                    fieldTwoStepsFurther = board.getField(row + jumpRow, col + jumpCol);
                    if (fieldTwoStepsFurther.getArmy() == Army.NONE) {
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

    private Board getLeftUpMove(Board board , int row, int col) {
        board.getField(row,col).setLeftUp(getDirectionalMove(board,row,col,-1,-1,-2,-2));
        return board;
    }
    private Board getRightUpMove(Board board , int row, int col){
        board.getField(row,col).setRightUp(getDirectionalMove(board,row,col,-1,1,-2,2));
        return board;
    }
    private Board getLeftDownMove(Board board , int row, int col){
        board.getField(row,col).setLeftDown(getDirectionalMove(board,row,col,1,-1,2,-2));
        return board;
    }
    private Board getRightDownMove(Board board , int row, int col){
        board.getField(row,col).setRightDown(getDirectionalMove(board,row,col,1,1,2,2));
        return board;
    }

    private Board getAvailableMovesForFigure(Board board , int row, int col, Army army) {
        getCoronation(board,row, col);
        field = board.getField(row, col);
        if (field.getArmy() == army) {
            if ((field.getArmy() == Army.TOP) && (field.getType() == Type.MAN)) {
                getLeftDownMove(board,row, col);
                getRightDownMove(board,row, col);
            }
            if ((field.getArmy() == Army.BOTTOM) && (field.getType() == Type.MAN)) {
                getLeftUpMove(board ,row, col);
                getRightUpMove(board ,row, col);
            }
            if (field.getType() == Type.KING) {
                getLeftDownMove(board, row, col);
                getRightDownMove(board ,row, col);
                getLeftUpMove(board ,row, col);
                getRightUpMove(board ,row, col);
            }
        }
        return board;
    }

    private Board resetAvailableMoves(Board board, int row, int col){
        board.getField(row,col).setLeftDown(Move.NONE);
        board.getField(row,col).setRightDown(Move.NONE);
        board.getField(row,col).setLeftUp(Move.NONE);
        board.getField(row,col).setRightUp(Move.NONE);
        return board;
    }

    public Board getAvailableMoves(Board board , Army army){
        for(int row=0; row<8; row++){
            for(int col=0; col<8; col++){
                resetAvailableMoves(board, row, col);
                getAvailableMovesForFigure(board ,row, col, army);
            }
        }
        filterNonMandatory(board, army);
        return board;
    }

    public boolean checkIfPlayerMustJump(Board board, Army army){
        getAvailableMoves(board, army);
        return checkIfPlayerHasMandatoryMoves(board, army);
    }

    public boolean checkIfPlayerHasAvailableMoves(Board board, Army army) {
        boolean playerHasAvailableMoves = false;
        getAvailableMoves(board, army);
        for(int row=0; row<8; row++) {
            for (int col = 0; col < 8; col++) {
                field = board.getField(row,col);
                if (checkIfFigureHasAvailableMoves(field) && field.getArmy()==army) {
                    playerHasAvailableMoves = true;
                }
            }
        }
        return playerHasAvailableMoves;
    }
    public boolean checkIfPlayerHasAnyPawn(Board board, Army army) {
        boolean playerHasAvailablePawn = false;
        for(int row=0; row<8; row++) {
            for (int col = 0; col < 8; col++) {
                if(board.getField(row,col).getArmy()==army){
                    playerHasAvailablePawn = true;
                }
            }
        }
        return playerHasAvailablePawn;
    }
}
