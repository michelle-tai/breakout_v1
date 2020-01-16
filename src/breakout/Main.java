package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.List;
import java.util.ArrayList;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main extends Application {
    public static final String TITLE = "Boba Breakout";
    public static final int SIZE_WIDTH = 400;
    public static final int SIZE_HEIGHT = 800;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.CORNSILK;
    public static final int BOUNCER_RADIUS = 10;
    public static int BOUNCER_SPEED = 200; //30
    public static final Paint BOUNCER_COLOR = Color.SADDLEBROWN;
    public static final int PADDLE_HEIGHT = 15;
    public static final int PADDLE_WIDTH = 80;
    public static final Paint PADDLE_COLOR = Color.PERU;
    public static final int PADDLE_SPEED = 10;



    // some things needed to remember during game
    private Scene myScene;
    private Circle myBouncer;
    private Rectangle myPaddle;
    private int xDir = 1;
    private int yDir = -1;
    Brick brickArr[] = new Brick[5];

    /**
     * Initialize what will be displayed and how it will be updated.
     */
    @Override
    public void start (Stage stage) {
        // attach scene to the stage and display it
        myScene = setupGame(SIZE_WIDTH, SIZE_HEIGHT, BACKGROUND);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
        // attach "game loop" to timeline to play it (basically just calling step() method repeatedly forever)
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    Group brickGroup = new Group();


    // Create the game's "scene": what shapes will be in the game and their starting properties
    private Scene setupGame (int width, int height, Paint background) {
        // create one top level collection to organize the things in the scene
        Group root = new Group();


        // make some shapes and set their properties
        // x and y represent the top left corner, so center it in window
        int bouncerStartX = width / 2;
        int bouncerStartY = height - PADDLE_HEIGHT - BOUNCER_RADIUS;
        myPaddle = new Rectangle(width / 2 - PADDLE_WIDTH / 2, height - PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);
        myPaddle.setFill(PADDLE_COLOR);
        myBouncer = new Circle(bouncerStartX, bouncerStartY, BOUNCER_RADIUS, BOUNCER_COLOR);

        int xRect = BOUNCER_RADIUS / 2;
        int yRect = 0;
        for(int i = 0; i < 5; i++){
//            Rectangle brick = new Rectangle(xRect, yRect, SIZE_WIDTH / 5 - BOUNCER_RADIUS, 10);
//            brick.setFill(PADDLE_COLOR);
            Brick brick = new Brick(xRect, yRect, SIZE_WIDTH / 5 - BOUNCER_RADIUS, 10, PADDLE_COLOR);
            xRect+= (SIZE_WIDTH / 5);
            brickGroup.getChildren().add(brick.getRect());
            brickArr[i] = brick;
        }




        // order added to the group is the order in which they are drawn
        root.getChildren().add(myPaddle);
        root.getChildren().add(myBouncer);
        root.getChildren().add(brickGroup);

        // create a place to see the shapes
        Scene scene = new Scene(root, width, height, background);
        // respond to input
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        scene.setOnMouseClicked(e -> handleMouseInput(e.getX(), e.getY()));
        return scene;
    }

    // Change properties of shapes in small ways to animate them over time
    // Note, there are more sophisticated ways to animate shapes, but these simple ways work fine to start
    private void step (double elapsedTime) {
        // update "actors" attributes
        if((myBouncer.getCenterY() + myBouncer.getRadius()) >= SIZE_HEIGHT){
            xDir = 0;
            yDir = 0;
        }
        if((myBouncer.getCenterX() + myBouncer.getRadius()) >= SIZE_WIDTH || (myBouncer.getCenterX() - myBouncer.getRadius()) <= 0){
            xDir = xDir * -1;
        }
        if((myBouncer.getCenterY() + myBouncer.getRadius()) >= SIZE_HEIGHT || (myBouncer.getCenterY() - myBouncer.getRadius()) <= 0){
            yDir = yDir * -1;
        }
        for(int i = 0; i < brickArr.length; i++){
            Brick currBrick = brickArr[i];
            Shape intersection = Shape.intersect(currBrick.getRect(), myBouncer);
            if(intersection.getBoundsInLocal().getWidth() != -1){
                if(currBrick.getBotBound() + BOUNCER_RADIUS <= myBouncer.getCenterX()){
                    xDir = xDir * -1;
                }
                else if(currBrick.getTopBound() - BOUNCER_RADIUS >= myBouncer.getCenterX()){
                    xDir = xDir * -1;
                }
                if(currBrick.getLeftBound() - BOUNCER_RADIUS <= myBouncer.getCenterY()){
                    yDir = yDir * -1;
                }
                else if(currBrick.getRightBound() + BOUNCER_RADIUS >= myBouncer.getCenterY()){
                    yDir = yDir * -1;
                }
                currBrick.setHitCount(-1);
            }


        }
        brickGroup.getChildren().clear();
        for(int i = 0; i< brickArr.length; i++){
            if(brickArr[i].getHitCount() != -1){
                brickGroup.getChildren().add(brickArr[i].getRect());
            }
        }
        myBouncer.setCenterX(myBouncer.getCenterX() + xDir * BOUNCER_SPEED * elapsedTime);
        myBouncer.setCenterY(myBouncer.getCenterY() + yDir * BOUNCER_SPEED * elapsedTime);
    }

    // What to do each time a key is pressed
    private void handleKeyInput (KeyCode code) {
        //moving the paddle left and right
        if (code == KeyCode.RIGHT && (myPaddle.getX() < SIZE_WIDTH - PADDLE_WIDTH)) {
            myPaddle.setX(myPaddle.getX() + PADDLE_SPEED);
        }
        else if (code == KeyCode.LEFT && (myPaddle.getX() > 0)) {
            myPaddle.setX(myPaddle.getX() - PADDLE_SPEED);
        }

        //cheat codes!
    }

    // What to do each time a key is pressed
    private void handleMouseInput (double x, double y) {
//        if (myGrower.contains(x, y)) {
//            myGrower.setScaleX(myGrower.getScaleX() * GROWER_RATE);
//            myGrower.setScaleY(myGrower.getScaleY() * GROWER_RATE);
//        }
    }

    /**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
