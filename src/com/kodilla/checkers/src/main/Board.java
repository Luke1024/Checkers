package com.kodilla.checkers.src.main;

import java.util.ArrayList;

class Board {
    private Move move;
    private Field field;
    private Field field2;
    private Field field3;
    private ArrayList<BoardRow> checkerBoard = new ArrayList<>();

    Board() {
        for (int i = 0; i < 8; i++) {
            checkerBoard.add(new BoardRow());
        }
    }

    public Field getField(int row, int col) {
        return checkerBoard.get(row).getField(col);
    }

    public void setField(int row, int col, Field field) {
            checkerBoard.get(row).setField(col, field);
    }

    public void startingPositions(){
        setField(0,1, new Field(Army.TOP, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setField(0,3, new Field(Army.TOP, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setField(0,5, new Field(Army.TOP, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setField(0,7, new Field(Army.TOP, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));

        setField(1,0, new Field(Army.TOP, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setField(1,2, new Field(Army.TOP, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setField(1,4, new Field(Army.TOP, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setField(1,6, new Field(Army.TOP, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));

        setField(2,1, new Field(Army.TOP, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setField(2,3, new Field(Army.TOP, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setField(2,5, new Field(Army.TOP, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setField(2,7, new Field(Army.TOP, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));

        setField(5,0, new Field(Army.BOTTOM, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setField(5,2, new Field(Army.BOTTOM, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setField(5,4, new Field(Army.BOTTOM, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setField(5,6, new Field(Army.BOTTOM, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));

        setField(6,1, new Field(Army.BOTTOM, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setField(6,3, new Field(Army.BOTTOM, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setField(6,5, new Field(Army.BOTTOM, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setField(6,7, new Field(Army.BOTTOM, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));

        setField(7,0, new Field(Army.BOTTOM, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setField(7,2, new Field(Army.BOTTOM, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setField(7,4, new Field(Army.BOTTOM, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        setField(7,6, new Field(Army.BOTTOM, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE));

        getAvailableMoves();
    }

    public void getLeftUpMove(int row, int col) {
        field = getField(row, col);
        try {
            field2 = getField(row - 1, col - 1);
            if(field2.getArmy()==Army.NONE){
                move = Move.AVAILABLE;
            }
            if (field.getArmy() == field2.getArmy()) {
                move = Move.NONE;
            }
            if(field2.getArmy() != field.getArmy() && field2.getArmy()!= Army.NONE){
                try {
                    field3 = getField(row - 2, col - 2);
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
        field.setLeftUp(move);
    }

    public void getRightUpMove(int row, int col){
        field = getField(row, col);
        try {
            field2 = getField(row - 1, col + 1);
            if(field2.getArmy()==Army.NONE){
                move = Move.AVAILABLE;
            }
            if (field.getArmy() == field2.getArmy()) {
                move = Move.NONE;
            }
            if(field2.getArmy()!=field.getArmy() && field2.getArmy()!= Army.NONE){
                try {
                    field3 = getField(row - 2, col + 2);
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
        field.setRightUp(move);
    }

    public void getLeftDownMove(int row, int col){
        field = getField(row, col);
        try {
            field2 = getField(row + 1, col - 1);
            if(field2.getArmy()==Army.NONE){
                move = Move.AVAILABLE;
            }
            if (field.getArmy() == field2.getArmy()) {
                move = Move.NONE;
            }
            if(field2.getArmy()!=field.getArmy() && field2.getArmy()!= Army.NONE){
                try {
                    field3 = getField(row + 2, col - 2);
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
        field.setLeftDown(move);
    }

    public void getRightDownMove(int row, int col){
        field = getField(row, col);
        try {
            field2 = getField(row + 1, col + 1);
            if(field2.getArmy() == Army.NONE){
                move = Move.AVAILABLE;
            }
            if (field.getArmy() == field2.getArmy()) {
                move = Move.NONE;
            }
            if(field2.getArmy()!=field.getArmy() && field2.getArmy()!= Army.NONE){
                try {
                    field3 = getField(row + 2, col + 2);
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
        field.setRightDown(move);
    }

    public void getCoronation(int row, int col){
        field=getField(row, col);
        if(field.getType()==Type.MAN){
            if(row==0 && field.getArmy()==Army.BOTTOM) getField(row,col).setType(Type.KING);
            if(row==7 && field.getArmy()==Army.TOP) getField(row,col).setType(Type.KING);
        }
    }
    public boolean checkIfFigureHasMandatoryMove(Field field){
        boolean removeFlag = false;
        if(field.getLeftDown()==Move.MANDATORY_JUMP){
            removeFlag = true;
        }
        if(field.getRightDown()==Move.MANDATORY_JUMP){
            removeFlag = true;
        }
        if(field.getLeftUp()==Move.MANDATORY_JUMP){
            removeFlag = true;
        }
        if(field.getRightUp()==Move.MANDATORY_JUMP){
            removeFlag = true;
        }
        return removeFlag;
    }
    public boolean checkIfPlayerHasMandatoryMove(Army army){
        boolean playerHasMandatoryMove=false;
        for(int row=0; row<8; row++){
            for(int col=0; col<8; col++){
                field=checkerBoard.get(row).getField(col);
                if(field.getArmy()==army && checkIfFigureHasMandatoryMove(field)==true){
                    playerHasMandatoryMove=true;
                }
            }
        }
        return playerHasMandatoryMove;
    }

    public void removeOtherMovesWhenMandatory(int row, int col){
        if(checkIfFigureHasMandatoryMove(getField(row,col))==true){
            if(getField(row,col).getLeftDown()==Move.AVAILABLE){
                getField(row,col).setLeftDown(Move.NONE);
            }
            if(getField(row,col).getRightDown()==Move.AVAILABLE){
                getField(row,col).setRightDown(Move.NONE);
            }
            if(getField(row,col).getLeftUp()==Move.AVAILABLE){
                getField(row,col).setLeftUp(Move.NONE);
            }
            if(getField(row,col).getRightUp()==Move.AVAILABLE){
                getField(row,col).setRightUp(Move.NONE);
            }
        }
    }

    public void getAvailableMovesForFigure(int row, int col){
        getCoronation(row,col);
        if((getField(row,col).getArmy()== Army.TOP) && (getField(row,col).getType()== Type.MAN)){
            getLeftDownMove(row,col);
            getRightDownMove(row,col);
        }
        if((getField(row,col).getArmy()== Army.BOTTOM) && (getField(row,col).getType()== Type.MAN)){
            getLeftUpMove(row,col);
            getRightUpMove(row,col);
        }
        if(getField(row,col).getType()== Type.KING){
            getLeftDownMove(row,col);
            getRightDownMove(row,col);
            getLeftUpMove(row,col);
            getRightUpMove(row,col);
        }
        removeOtherMovesWhenMandatory(row,col);
    }

    public void getAvailableMoves(){
        for(int row=0; row<8; row++){
            for(int col=0; col<8; col++){
                getAvailableMovesForFigure(row, col);
            }
        }
    }

    public void moveFigure(MoveCoords move){
        field=getField(move.getRow1(), move.getCol1());
        if(move.getRow2()-move.getRow1()==2 && move.getCol2()-move.getCol1()==2){
            removeFigure((move.getRow1()+move.getRow2())/2,(move.getCol1()+move.getCol2())/2);
        }
        setField(move.getRow2(),move.getCol2(),field);
        setField(move.getRow1(), move.getCol1(), new Field(Army.NONE, Type.NONE, Move.NONE, Move.NONE, Move.NONE,Move.NONE));
    }

    public void removeFigure(int row, int col){
        setField(row,col,new Field(Army.NONE,Type.NONE,Move.NONE,Move.NONE,Move.NONE,Move.NONE));
    }
}