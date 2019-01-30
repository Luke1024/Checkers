package com.kodilla.checkers.src.main.logicEngine;

public class Field {
    Army army;
    Type type;
    Move leftDown;
    Move rightDown;
    Move leftUp;
    Move rightUp;

    public Army getArmy() {
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Move getLeftDown() {
        return leftDown;
    }

    public void setLeftDown(Move leftDown) {
        this.leftDown = leftDown;
    }

    public Move getRightDown() {
        return rightDown;
    }

    public void setRightDown(Move rightDown) {
        this.rightDown = rightDown;
    }

    public Move getLeftUp() {
        return leftUp;
    }

    public void setLeftUp(Move leftUp) {
        this.leftUp = leftUp;
    }

    public Move getRightUp() {
        return rightUp;
    }

    public void setRightUp(Move rightUp) {
        this.rightUp = rightUp;
    }

    public Field(Army army, Type type, Move leftDown, Move rightDown, Move leftUp, Move rightUp) {
        this.army = army;
        this.type = type;
        this.leftDown = leftDown;
        this.rightDown = rightDown;
        this.leftUp = leftUp;
        this.rightUp = rightUp;
    }
}
