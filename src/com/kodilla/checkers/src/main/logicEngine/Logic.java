package com.kodilla.checkers.src.main.logicEngine;

public class Logic implements CheckersInterface {
    private Board board = new Board();
    private AvailableMovesAnalyzer availableMovesAnalyzer = new AvailableMovesAnalyzer();
    private boolean bottomMove=true;
    private boolean gameInProgress=true;
    private WhoWin whoWin = WhoWin.NONE;
    private boolean inJumpSeriesBottom=false;
    private boolean inJumpSeriesTop=false;

    public boolean isGameInProgress() {
        return gameInProgress;
    }

    public WhoWin getWhoWin() {
        return whoWin;
    }

    public Logic(){
        board.startingPositions();
        board = availableMovesAnalyzer.getAvailableMoves(board, Army.BOTTOM);
    }

    public Board getCheckerBoard(){
        return board;
    }

    public boolean isBottomMove() {
        return bottomMove;
    }

    public Board moveFigure(Board board , MoveCoords moveCoords){
        board.moveFigure(moveCoords);
        if(board.getField(moveCoords.getRow2(),moveCoords.getCol2()).getArmy()== Army.BOTTOM){
            if(availableMovesAnalyzer.checkIfPlayerMustJump(board, Army.BOTTOM) && inJumpSeriesBottom) {
                bottomMove=true;
            } else {
                bottomMove=false;
            }
        }
        if(availableMovesAnalyzer.checkIfPlayerMustJump(board, Army.BOTTOM)){
            inJumpSeriesBottom=true;
        } else {
            inJumpSeriesBottom=false;
        }

        if(board.getField(moveCoords.getRow2(),moveCoords.getCol2()).getArmy()== Army.TOP){
            if(availableMovesAnalyzer.checkIfPlayerMustJump(board, Army.TOP) && inJumpSeriesTop){
                bottomMove=false;
            } else {
                bottomMove=true;
            }
        }
        if(availableMovesAnalyzer.checkIfPlayerMustJump(board, Army.BOTTOM)){
            inJumpSeriesTop=true;
        } else {
            inJumpSeriesBottom=false;
        }
        checkIfGameEnd(board);
        if(!bottomMove){
            availableMovesAnalyzer.getAvailableMoves(board, Army.TOP);
        } else {
            availableMovesAnalyzer.getAvailableMoves(board, Army.BOTTOM);
        }
        return board;
    }

    private void checkIfGameEnd(Board board){
        if(!availableMovesAnalyzer.checkIfPlayerHasAvailableMoves(board, Army.TOP)){
            whoWin= WhoWin.WHITE;
        }
        if(!availableMovesAnalyzer.checkIfPlayerHasAnyPawn(board, Army.TOP)){
            whoWin= WhoWin.WHITE;
        }
        if(!availableMovesAnalyzer.checkIfPlayerHasAvailableMoves(board, Army.BOTTOM)){
            whoWin= WhoWin.BLACK;
        }
        if(!availableMovesAnalyzer.checkIfPlayerHasAnyPawn(board, Army.BOTTOM)){
            whoWin= WhoWin.BLACK;
        }
        gameInProgress=false;
    }
}




