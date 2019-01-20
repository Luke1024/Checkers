import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GUI extends Application {

    private Rectangle rect;
    private GraphicObjectsPatterns guiPatterns = new GraphicObjectsPatterns();
    private byte[][] checkerBoard = guiPatterns.getCheckerBoard();
    private Board board = new Board();
    private Field field;

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

    public Group getCheckers(int x, int y, Army army, Type type) {
        Group checker = new Group();
        if (army == Army.TOP) {
            if (type == Type.MAN) {
                for (int i = 0; i < 8; i++) {
                    checker.getChildren().add(new Circle(x, y, 40 - i * 4, Color.rgb(0,0,0)));
                    checker.getChildren().add(new Circle(x, y, 38 - i * 4, Color.rgb(50,50,50)));
                }
            }
            if (type == Type.KING) {
                checker.getChildren().add(new Circle(x, y, 40, Color.rgb(0,0,0)));
                checker.getChildren().add(new Circle(x,y,20,Color.rgb(255,215,0)));
            }
        }
        if (army == Army.BOTTOM) {
            if (type == Type.MAN) {
                for (int i = 0; i < 8; i++) {
                    checker.getChildren().add(new Circle(x, y, 40 - i*4, Color.rgb(205, 133, 63)));
                    checker.getChildren().add(new Circle(x, y, 38 - i*4, Color.rgb(210, 105, 30)));
                }
            }
            if (type == Type.KING) {
                checker.getChildren().add(new Circle(x, y, 40, Color.rgb(205, 133, 63)));
                checker.getChildren().add(new Circle(x,y,20,Color.rgb(255,215,0)));
            }
        }
        return checker;
    }

    public Group getTransparentCheckers(int x, int y, Army army, Type type) {
        Group checker = new Group();
        x=50+100*x;
        y=50+100*y;
        if (army == Army.TOP) {
            if (type == Type.MAN) {
                for (int i = 0; i < 10; i++) {
                    checker.getChildren().add(new Circle(x, y, 40 - i * 2, Color.rgb(0,0,0,0.3)));
                    checker.getChildren().add(new Circle(x, y, 39 - i * 2, Color.rgb(50,50,50,0.3)));
                }
            }
            if (type == Type.KING) {
                checker.getChildren().add(new Circle(x, y, 40, Color.rgb(0,0,0,0.3)));
                checker.getChildren().add(new Circle(x,y,30,Color.rgb(255,215,0,0.3)));
            }
        }
        if (army == Army.BOTTOM) {
            if (type == Type.MAN) {
                for (int i = 0; i < 10; i++) {
                    checker.getChildren().add(new Circle(x, y, 40 - i, Color.rgb(205, 133, 63,0.3)));
                    checker.getChildren().add(new Circle(x, y, 39 - i, Color.rgb(210, 105, 30,0.3)));
                }
            }
            if (type == Type.KING) {
                checker.getChildren().add(new Circle(x, y, 40, Color.rgb(205, 133, 63,0.3)));
                checker.getChildren().add(new Circle(x,y,30,Color.rgb(255,215,0,0.3)));
            }
        }
        return checker;
    }
    public Group drawPossibleMoves(Field field, int x, int y){
        Group checker = new Group();
        if(field.getLeftDown()== Move.AVAILABLE){
            checker.getChildren().add(getTransparentCheckers(x+1,y-1,field.getArmy(),field.getType()));
        }
        if(field.getLeftDown()== Move.MANDATORY_JUMP){
            checker.getChildren().add(getTransparentCheckers(x+2,y-2,field.getArmy(),field.getType()));
        }
        if(field.getRightDown()== Move.AVAILABLE){
            checker.getChildren().add(getTransparentCheckers(x+1,y+1,field.getArmy(),field.getType()));
        }
        if(field.getRightDown()== Move.MANDATORY_JUMP){
            checker.getChildren().add(getTransparentCheckers(x+2,y+2,field.getArmy(),field.getType()));
        }
        if(field.getLeftUp()== Move.AVAILABLE){
            checker.getChildren().add(getTransparentCheckers(x-1,y-1,field.getArmy(),field.getType()));
        }
        if(field.getLeftUp()== Move.MANDATORY_JUMP){
            checker.getChildren().add(getTransparentCheckers(x-2,y-2,field.getArmy(),field.getType()));
        }
        if(field.getRightUp()== Move.AVAILABLE) {
            checker.getChildren().add(getTransparentCheckers(x-1,y+1,field.getArmy(),field.getType()));
        }
        if(field.getRightUp()== Move.MANDATORY_JUMP) {
            checker.getChildren().add(getTransparentCheckers(x-2,y+2,field.getArmy(),field.getType()));
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
        Group checkers = new Group();
        checkers.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int x = (int) Math.floor(event.getX()/100);
                int y = (int) Math.floor(event.getY()/100);
                checkers.getChildren().add(drawPossibleMoves(board.getField(x,y), x, y));
                primaryStage.show();
            }
        });
        checkers.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int x = (int) Math.floor(event.getX()/100);
                int y = (int) Math.floor(event.getY()/100);
                board.
            }
        });
        for(int x=0;x<8;x++){
            for(int y=0;y<8;y++){
                squares.getChildren().add(getField(100*x,100*y, checkerBoard[y][x]));
                field = board.getField(y,x);
                checkers.getChildren().add(getCheckers(100*x+50, 100*y+50, field.getArmy(), field.getType()));
            }
        }

        root.getChildren().add(squares);
        root.getChildren().add(checkers);
        primaryStage.show();
    }
}
