package com.kodilla.checkers.src.main;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GraphicObjectsPatterns {

    private byte[][] checkerBoard = {{0,1,0,1,0,1,0,1},
            {1,0,1,0,1,0,1,0},
            {0,1,0,1,0,1,0,1},
            {1,0,1,0,1,0,1,0},
            {0,1,0,1,0,1,0,1},
            {1,0,1,0,1,0,1,0},
            {0,1,0,1,0,1,0,1},
            {1,0,1,0,1,0,1,0}};

    public byte[][] getCheckerBoard() {
        return checkerBoard;
    }

    Rectangle getField(int x, int y, byte typeCode){
        Rectangle rect = new Rectangle();
        if(typeCode==0) rect = new Rectangle(100, 100, Color.rgb(205,170,125));
        if(typeCode==1) rect = new Rectangle(100, 100, Color.rgb(139,105,20));
        //error handling
        if(typeCode<0 || typeCode>1) rect = new Rectangle(100, 100, Color.BLACK);
        rect.setX(x);
        rect.setY(y);
        return rect;
    }
    //public Group assembleCrownPattern(Color color){}

    public Group assembleTopMan(int colInPixels, int rowInPixels, double opacity){
        Group checker = new Group();
        Color darkBlack = Color.rgb(0,0,0,opacity);
        Color lightBlack = Color.rgb(50,50,50,opacity);
        for (int i = 0; i < 8; i++) {
            checker.getChildren().add(new Circle(colInPixels, rowInPixels, 40 - i * 4, darkBlack));
            checker.getChildren().add(new Circle(colInPixels, rowInPixels, 38 - i * 4, lightBlack));
        }
        return checker;
    }

    public Group assembleTopKing(int colInPixels, int rowInPixels, double opacity){
        Group checker = new Group();
        Color darkBlack = Color.rgb(0,0,0,opacity);
        Color gold = Color.rgb(255,215,0, opacity);
        checker.getChildren().add(new Circle(colInPixels,rowInPixels, 40,darkBlack));
        checker.getChildren().add(new Circle(colInPixels,rowInPixels,20,gold));
        return checker;
    }

    public Group assembleBottomMan(int colInPixels, int rowInPixels, double opacity){
        Group checker = new Group();
        Color darkBrown = Color.rgb(205, 133, 63, opacity);
        Color lightBrown = Color.rgb(210, 105, 30, opacity);
        for (int i = 0; i < 8; i++) {
            checker.getChildren().add(new Circle(colInPixels, rowInPixels,40 - i*4,darkBrown));
            checker.getChildren().add(new Circle(colInPixels, rowInPixels,38 - i*4,lightBrown));
        }
        return checker;
    }

    public Group assembleBottomKing(int colInPixels, int rowInPixels, double opacity){
        Group checker = new Group();
        Color darkBrown = Color.rgb(205, 133, 63, opacity);
        Color gold = Color.rgb(255,215,0, opacity);
        checker.getChildren().add(new Circle(colInPixels,rowInPixels, 40,darkBrown));
        checker.getChildren().add(new Circle(colInPixels,rowInPixels,20,gold));
        return checker;
    }

    public Group getCheckers(int row, int col, Army army, Type type, double opacity) {
        Group checker = new Group();
        int rowInPixels = row*100+50;
        int colInPixels = col*100+50;
        if (army == Army.TOP) {
            if(type == Type.MAN) {
                checker.getChildren().add(assembleTopMan(colInPixels, rowInPixels, opacity));
            }
            if(type == Type.KING){
                checker.getChildren().add(assembleTopKing(colInPixels,rowInPixels,opacity));
            }
        }
        if (army == Army.BOTTOM) {
            if (type == Type.MAN) {
                checker.getChildren().add(assembleBottomMan(colInPixels,rowInPixels,opacity));
            }
            if (type == Type.KING) {
                checker.getChildren().add(assembleBottomKing(colInPixels,rowInPixels,opacity));
            }
        }
        return checker;
    }
    public Group drawMoves(Field field, int row, int col, double opacity){
        Group checker = new Group();
        if(field.getLeftDown()== Move.AVAILABLE){
            checker.getChildren().add(getCheckers(row+1,col-1,field.getArmy(),field.getType(), opacity));
        }
        if(field.getLeftDown()== Move.MANDATORY_JUMP){
            checker.getChildren().add(getCheckers(row+2,col-2,field.getArmy(),field.getType(), opacity));
        }
        if(field.getRightDown()== Move.AVAILABLE){
            checker.getChildren().add(getCheckers(row+1,col+1,field.getArmy(),field.getType(), opacity));
        }
        if(field.getRightDown()== Move.MANDATORY_JUMP){
            checker.getChildren().add(getCheckers(row+2,col+2,field.getArmy(),field.getType(), opacity));
        }
        if(field.getLeftUp()== Move.AVAILABLE){
            checker.getChildren().add(getCheckers(row-1,col-1,field.getArmy(),field.getType(), opacity));
        }
        if(field.getLeftUp()== Move.MANDATORY_JUMP){
            checker.getChildren().add(getCheckers(row-2,col-2,field.getArmy(),field.getType(), opacity));
        }
        if(field.getRightUp()== Move.AVAILABLE) {
            checker.getChildren().add(getCheckers(row-1,col+1,field.getArmy(),field.getType(), opacity));
        }
        if(field.getRightUp()== Move.MANDATORY_JUMP) {
            checker.getChildren().add(getCheckers(row-2,col+2,field.getArmy(),field.getType(), opacity));
        }
        return checker;
    }


    public Group drawPossibleMoves(Field field, int row, int col, boolean moveFlag ,double opacity){
        Group checker = new Group();
        if(moveFlag==false && field.getArmy()==Army.BOTTOM){
            checker.getChildren().add(drawMoves(field, row, col, opacity));
        }
        if(moveFlag==true && field.getArmy()==Army.TOP){
            checker.getChildren().add(drawMoves(field, row, col, opacity));
        }
        return checker;
    }

    public Group drawSquares(){
        Group squares = new Group();
        for(int row=0;row<8;row++){
            for(int col=0;col<8;col++){
                squares.getChildren().add(getField(100*row,100*col, checkerBoard[col][row]));
            }
        }
        return squares;
    }
}
