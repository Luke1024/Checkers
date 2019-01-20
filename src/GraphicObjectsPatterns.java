import javafx.scene.Group;

public class GraphicObjectsPatterns {
    private Group checkerWhite;

    byte[][] pawnPlacement = new byte[8][8];

    byte[][] checkerBoard = {{0,1,0,1,0,1,0,1},
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
    public Group getCheckerGroup(){
        return checkerWhite;
    }
}
