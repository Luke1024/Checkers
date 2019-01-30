package com.kodilla.checkers.src.main.logicEngine;

public class Logic implements CheckersInterface {
    private HumanVsHuman humanVsHuman = new HumanVsHuman();
    private Mode mode;
    private Difficulty difficulty;

    public Logic(Mode mode, Difficulty difficulty) {
        this.mode = mode;
        this.difficulty = difficulty;
    }

    public Board getCheckerBoard(){
        return humanVsHuman.getBoard();
    }

    public Board moveFigure(MoveCoords moveCoords){
        return humanVsHuman.moveFigure(moveCoords);
    }
}




