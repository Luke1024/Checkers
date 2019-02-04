package com.kodilla.checkers.src.main.logicEngine;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class Board {
    private ArrayList<BoardRow> checkerBoard = new ArrayList<>();

    Board() {
        for (int i = 0; i < 8; i++) {
            checkerBoard.add(new BoardRow());
        }
    }

    public Field getField(int row, int col) {
        return checkerBoard.get(row).getField(col);
    }

    public void setFieldNew(int row, int col, Field field) {
            checkerBoard.get(row).setFieldNew(col, field);
    }

    public void startingPositions(){
        setFieldNew(0,1, new Field(Army.TOP, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setFieldNew(0,3, new Field(Army.TOP, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setFieldNew(0,5, new Field(Army.TOP, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setFieldNew(0,7, new Field(Army.TOP, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));

        setFieldNew(1,0, new Field(Army.TOP, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setFieldNew(1,2, new Field(Army.TOP, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setFieldNew(1,4, new Field(Army.TOP, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setFieldNew(1,6, new Field(Army.TOP, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));

        setFieldNew(2,1, new Field(Army.TOP, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setFieldNew(2,3, new Field(Army.TOP, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setFieldNew(2,5, new Field(Army.TOP, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setFieldNew(2,7, new Field(Army.TOP, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));

        setFieldNew(5,0, new Field(Army.BOTTOM, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setFieldNew(5,2, new Field(Army.BOTTOM, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setFieldNew(5,4, new Field(Army.BOTTOM, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setFieldNew(5,6, new Field(Army.BOTTOM, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));

        setFieldNew(6,1, new Field(Army.BOTTOM, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setFieldNew(6,3, new Field(Army.BOTTOM, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setFieldNew(6,5, new Field(Army.BOTTOM, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setFieldNew(6,7, new Field(Army.BOTTOM, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));

        setFieldNew(7,0, new Field(Army.BOTTOM, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setFieldNew(7,2, new Field(Army.BOTTOM, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setFieldNew(7,4, new Field(Army.BOTTOM, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setFieldNew(7,6, new Field(Army.BOTTOM, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
    }

    public void moveFigure(MoveCoords move){
        Field field=getField(move.getRow1(), move.getCol1());
        if(abs(move.getRow2()-move.getRow1())==2 && abs(move.getCol2()-move.getCol1())==2){
            removeFigure((move.getRow1()+move.getRow2())/2,(move.getCol1()+move.getCol2())/2);
        }
        setFieldNew(move.getRow2(),move.getCol2(),field);
        setFieldNew(move.getRow1(), move.getCol1(), new Field(Army.NONE, Type.NONE, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
    }

    public void removeFigure(int row, int col){
        setFieldNew(row,col,new Field(Army.NONE, Type.NONE, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
    }
}