import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.sql.Time;

public class GUI extends Application {

    private Rectangle rect;
    private GraphicObjectsPatterns guiPatterns = new GraphicObjectsPatterns();
    private byte[][] checkerBoard = guiPatterns.getCheckerBoard();
    private Board board = new Board();
    private Field field;
    private Field fieldClicked;
    boolean moveFlag;
    int rowClicked;
    int colClicked;

    public void GUIStart() {
        launch();
    }

    Rectangle getField(int x, int y, byte typeCode){
        if(typeCode==0) rect = new Rectangle(100, 100, Color.rgb(205,170,125));
        if(typeCode==1) rect = new Rectangle(100, 100, Color.rgb(139,105,20));
        //error handling
        if(typeCode<0 || typeCode>1) rect = new Rectangle(100, 100, Color.BLACK);
        rect.setX(x);
        rect.setY(y);
        return rect;
    }

    public Group getCheckers(int row, int col, Army army, Type type, double opacity) {
        Group checker = new Group();
        if (army == Army.TOP) {
            if (type == Type.MAN) {
                for (int i = 0; i < 8; i++) {
                    checker.getChildren().add(new Circle(100*col+50, 100*row+50, 40 - i * 4, Color.rgb(0,0,0, opacity)));
                    checker.getChildren().add(new Circle(100*col+50, 100*row+50, 38 - i * 4, Color.rgb(50,50,50, opacity)));
                }
            }
            if (type == Type.KING) {
                checker.getChildren().add(new Circle(100*col+50, 100*row+50, 40, Color.rgb(0,0,0, opacity)));
                checker.getChildren().add(new Circle(100*col+50,100*row+50,20,Color.rgb(255,215,0, opacity)));
            }
        }
        if (army == Army.BOTTOM) {
            if (type == Type.MAN) {
                for (int i = 0; i < 8; i++) {
                    checker.getChildren().add(new Circle(100*col+50, 100*row+50, 40 - i*4, Color.rgb(205, 133, 63, opacity)));
                    checker.getChildren().add(new Circle(100*col+50, 100*row+50, 38 - i*4, Color.rgb(210, 105, 30, opacity)));
                }
            }
            if (type == Type.KING) {
                checker.getChildren().add(new Circle(100*col+50, 100*row+50, 40, Color.rgb(205, 133, 63, opacity)));
                checker.getChildren().add(new Circle(100*col+50, 100*row+50,20,Color.rgb(255,215,0, opacity)));
            }
        }
        return checker;
    }

    public Group drawPossibleMoves(Field field, int row, int col, double opacity){
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

    @Override
    public void start(Stage primaryStage) {
        board.startingPositions();
        Group root = new Group();
        Scene scene = new Scene(root, 800, 800, Color.BLACK);
        primaryStage.setScene(scene);
        Group squares = new Group();
        Group checkersWhite = new Group();
        Group checkersBlack = new Group();
        Group possibleWhite = new Group();
        Group possibleBlack = new Group();
        Group checkersTransparent = new Group();
        checkersWhite.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                squares.getChildren().clear();
                checkersWhite.getChildren().clear();
                possibleWhite.getChildren().clear();
                for(int row=0;row<8;row++){
                    for(int col=0;col<8;col++){
                        squares.getChildren().add(getField(100*row,100*col, checkerBoard[col][row]));
                        field = board.getField(row,col);
                        checkersWhite.getChildren().add(getCheckers(row, col, field.getArmy(), field.getType(),1));
                    }
                }
                int col = (int) Math.floor(event.getX()/100);
                int row = (int) Math.floor(event.getY()/100);
                checkers.getChildren().add(drawPossibleMoves(board.getField(row, col), row, col, 0.2));
                primaryStage.show();
            }
        });
        checkers.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                squares.getChildren().clear();
                checkers.getChildren().clear();
                for(int row=0;row<8;row++){
                    for(int col=0;col<8;col++){
                        squares.getChildren().add(getField(100*row,100*col, checkerBoard[col][row]));
                        field = board.getField(row,col);
                        checkers.getChildren().add(getCheckers(row, col, field.getArmy(), field.getType(),1));
                    }
                }
                int col = (int) Math.floor(event.getX()/100);
                int row = (int) Math.floor(event.getY()/100);
                fieldClicked = board.getField(row,col);
                rowClicked = row;
                colClicked = col;
                possible.getChildren().add(drawPossibleMoves(board.getField(row, col), row, col, 0.5));
                primaryStage.show();
            }
        });
        possible.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                squares.getChildren().clear();
                checkers.getChildren().clear();
                possible.getChildren().clear();
                int col = (int) Math.floor(event.getX()/100);
                int row = (int) Math.floor(event.getY()/100);
                board.moveFigure(new MoveCoords(rowClicked,row,colClicked,col));
                board.getAvailableMoves();
                for(row=0;row<8;row++){
                    for(col=0;col<8;col++){
                        squares.getChildren().add(getField(100*row,100*col, checkerBoard[col][row]));
                        field = board.getField(row,col);
                        checkers.getChildren().add(getCheckers(row, col, field.getArmy(), field.getType(),1));
                    }
                }
                primaryStage.show();
            }
        });

        for(int row=0;row<8;row++){
            for(int col=0;col<8;col++){
                squares.getChildren().add(getField(100*row,100*col, checkerBoard[col][row]));
                field = board.getField(row,col);
                checkers.getChildren().add(getCheckers(row, col, field.getArmy(), field.getType(),1));
            }
        }

        root.getChildren().add(squares);
        root.getChildren().add(checkers);
        root.getChildren().add(possible);
        primaryStage.show();
    }
}
