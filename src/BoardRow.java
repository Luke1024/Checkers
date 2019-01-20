import java.util.ArrayList;

public class BoardRow {
    private ArrayList<Field> row = new ArrayList<Field>();
    BoardRow(){
        for(int i=0;i<8;i++){
            row.add(new Field(Army.NONE, Type.NONE, Move.NONE, Move.NONE, Move.NONE, Move.NONE));
        }
    }
    public Field getField(int index){
        return row.get(index);
    }
    public void setField(int index, Field field){
        row.add(index, field);
    }
}
