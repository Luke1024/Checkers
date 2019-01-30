package com.kodilla.checkers.src.main.logicEngine;

import java.util.ArrayList;

public class BoardRow {
    private ArrayList<Field> row = new ArrayList<>();
    BoardRow(){
        for(int i=0;i<8;i++){
            row.add(new Field(Army.NONE, Type.NONE, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        }
    }
    public Field getField(int index){
        return row.get(index);
    }
    public void setFieldNew(int index, Field field){
        row.set(index, field);
    }
}
