package com.kodilla.checkers.src.main.logicEngine;

public class HumanVsHuman {
    private Board board = new Board();
    private AvailableMovesAnalyzer availableMovesAnalyzer = new AvailableMovesAnalyzer();
    private boolean bottomMove = true;
    private Field field;

    public HumanVsHuman(){
        board.startingPositions();
        availableMovesAnalyzer.getAvailableMoves(board,Army.TOP);
        availableMovesAnalyzer.getAvailableMoves(board,Army.BOTTOM);
        gameStart();
    }
    public Board getBoard(){
        return board;
    }

    public Board moveFigure(MoveCoords moveCoords){
        field=board.getField(moveCoords.getRow1(),moveCoords.getCol1());
        board.moveFigure(moveCoords);
        if(field.getArmy()==Army.BOTTOM) {
            if(availableMovesAnalyzer.checkIfPlayerMustJump(board,Army.BOTTOM)==true) {
                bottomMove=true;
            }
        }
        if(field.getArmy()==Army.TOP) {
            if(availableMovesAnalyzer.checkIfPlayerMustJump(board,Army.TOP)==true) {
                bottomMove=false;
            }
        }

        if(bottomMove==false){
            board = availableMovesAnalyzer.getAvailableMoves(board, Army.TOP);
        } else {
            board = availableMovesAnalyzer.getAvailableMoves(board, Army.BOTTOM);
        }
        return board;
    }

    public void gameStart(){
        boolean bottomMove = true;
    }
}
