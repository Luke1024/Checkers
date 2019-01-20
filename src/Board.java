import java.util.ArrayList;

class Board {
    private Move move;
    private Move leftUp;
    private Move rightUp;
    private Move leftDown;
    private Move rightDown;
    private Field field;

    private ArrayList<BoardRow> checkerBoard = new ArrayList<BoardRow>();

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
        Field fieldTop = new Field(Army.TOP, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE);
        Field fieldBottom = new Field(Army.BOTTOM, Type.MAN, Move.NONE, Move.NONE, Move.NONE, Move.NONE);

        setField(0,1, fieldTop);
        setField(0,3, fieldTop);
        setField(0,5, fieldTop);
        setField(0,7, fieldTop);

        setField(1,0, fieldTop);
        setField(1,2, fieldTop);
        setField(1,4, fieldTop);
        setField(1,6, fieldTop);

        setField(2,1, fieldTop);
        setField(2,3, fieldTop);
        setField(2,5, fieldTop);
        setField(2,7, fieldTop);

        setField(5,0, fieldBottom);
        setField(5,2, fieldBottom);
        setField(5,4, fieldBottom);
        setField(5,6, fieldBottom);

        setField(6,1, fieldBottom);
        setField(6,3, fieldBottom);
        setField(6,5, fieldBottom);
        setField(6,7, fieldBottom);

        setField(7,0, fieldBottom);
        setField(7,2, fieldBottom);
        setField(7,4, fieldBottom);
        setField(7,6, fieldBottom);

        getAvailableMoves();
    }

    Move getLeftUpMove(int x, int y) {
        if (getField(x, y).getArmy() == Army.TOP && getField(x, y).getArmy() == Army.BOTTOM) {
            if (getField(x, y).getArmy() == getField(x - 1, y - 1).getArmy()) {
                move = Move.NONE;
            } else {
                if (getField(x - 2, y - 2).getArmy() == Army.NONE) {
                    move = Move.MANDATORY_JUMP;
                } else move = Move.NONE;
            }
        } else move = Move.AVAILABLE;
        return move;
    }

    Move getRightUpMove(int x, int y){
        if (getField(x, y).getArmy() == Army.TOP && getField(x, y).getArmy() == Army.BOTTOM) {
            if (getField(x, y).getArmy() == getField(x - 1, y + 1).getArmy()) {
                move = Move.NONE;
            } else {
                if (getField(x - 2, y + 2).getArmy() == Army.NONE) {
                    move = Move.MANDATORY_JUMP;
                } else move = Move.NONE;
            }
        } else move = Move.AVAILABLE;
        return move;
    }
    Move getLeftDownMove(int x, int y){
        if (getField(x, y).getArmy() == Army.TOP && getField(x, y).getArmy() == Army.BOTTOM) {
            if (getField(x, y).getArmy() == getField(x + 1, y - 1).getArmy()) {
                move = Move.NONE;
            } else {
                if (getField(x + 2, y - 2).getArmy() == Army.NONE) {
                    move = Move.MANDATORY_JUMP;
                } else move = Move.NONE;
            }
        } else move = Move.AVAILABLE;
        return move;
    }
    Move getRightDownMove(int x, int y){
        if (getField(x, y).getArmy() == Army.TOP && getField(x, y).getArmy() == Army.BOTTOM) {
            if (getField(x, y).getArmy() == getField(x + 1, y + 1).getArmy()) {
                move = Move.NONE;
            } else {
                if (getField(x + 2, y + 2).getArmy() == Army.NONE) {
                    move = Move.MANDATORY_JUMP;
                } else move = Move.NONE;
            }
        } else move = Move.AVAILABLE;
        return move;
    }

    public void getCoronation(int x, int y){
        field=getField(x,y);
        if(field.getType()==Type.MAN){
            if(x==0 && field.getArmy()==Army.BOTTOM) getField(x,y).setType(Type.KING);
            if(x==7 && field.getArmy()==Army.TOP) getField(x,y).setType(Type.KING);
        }
    }

    public void getAvailableMovesForFigure(int x, int y){
        getCoronation(x,y);
        if(getField(x,y).getArmy()== Army.TOP && getField(x,y).getType()== Type.MAN){
            getField(x,y).setLeftDown(getLeftDownMove(x,y));
            getField(x,y).setRightDown(getRightDownMove(x,y));
        }
        if(getField(x,y).getArmy()== Army.BOTTOM && getField(x,y).getType()== Type.MAN){
            getField(x,y).setLeftDown(getLeftUpMove(x,y));
            getField(x,y).setRightDown(getRightUpMove(x,y));
        }
        if(getField(x,y).getType()== Type.KING){
            getField(x,y).setLeftDown(getLeftDownMove(x,y));
            getField(x,y).setRightDown(getRightDownMove(x,y));
            getField(x,y).setLeftUp(getLeftUpMove(x,y));
            getField(x,y).setRightUp(getRightUpMove(x,y));
        }
    }

    public void getAvailableMoves(){
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                getAvailableMovesForFigure(x,y);
            }
        }
    }
}