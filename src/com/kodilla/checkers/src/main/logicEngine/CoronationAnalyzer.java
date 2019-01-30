package com.kodilla.checkers.src.main.logicEngine;

public class CoronationAnalyzer {
    private Field field;

    public Board getCoronation(Board board,int row, int col){
        field= board.getField(row, col);
        if(field.getType()== Type.MAN){
            if(row==0 && field.getArmy()== Army.BOTTOM) board.getField(row,col).setType(Type.KING);
            if(row==7 && field.getArmy()== Army.TOP) board.getField(row,col).setType(Type.KING);
        }
        return board;
    }
}
