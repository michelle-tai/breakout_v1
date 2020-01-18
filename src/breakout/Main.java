package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    public static final String TITLE = "Boba Breakout";
    public static final int SIZE_WIDTH = 400;
    public static final int SIZE_HEIGHT = 600;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.CORNSILK;
    public static final int BOUNCER_RADIUS = 10;
    public static int BOUNCER_SPEED = 200; //30
    public static final int PADDLE_HEIGHT = 15;
    public static final int PADDLE_WIDTH = 80;
    public static final Paint PADDLE_COLOR = Color.PERU;
    public static final int PADDLE_SPEED = 10;
    public static final int STATUS_BAR_SIZE = 100;


    // some things needed to remember during game
    private Scene myScene;
    private int xDir = 1;
    private int yDir = -1;
    Brick brickArr[][];
    private Ball ball;
    private Paddle paddle;
    Group root = new Group();
    Group brickGroup = new Group();
    Group textGroup = new Group();
    int lives;
    Text lifeStats = new Text("Lives: " + lives);
    int level = 1;
    Text losingText = new Text("You lost :(");
    levelChooser lvl;


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
    // Create the game's "scene": what shapes will be in the game and their starting properties
    private Scene setupGame (int width, int height, Paint background) {
        int rows = 2;
        lvl = new levelChooser(width, height, rows, 3);
        root = lvl.getRoot();
        ball = lvl.getBall();
        paddle = lvl.getPaddle();
        lives = lvl.getLives();
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
        lvl.updateBrickArr();
        lvl.moveBall(elapsedTime);
        lvl.checkBall();
        lvl.checkLives();
    }

    // What to do each time a key is pressed
    private void handleKeyInput (KeyCode code) {
        paddle.handleKeyInput(code);
        if(code == KeyCode.R){
            ball.resetBall();
            paddle.reset();
        }

        if(code == KeyCode.L){
            lvl.incrementLives();
            lvl.setLifeStats();
        }

        if(code == KeyCode.SPACE){
//            root.getChildren().clear();
            paddle.reset();
            ball.resetBall();
            brickGroup.getChildren().clear();
            lvl.resetBrickGroup();
        }

        if (code == KeyCode.DIGIT1) {
//            lvl.setLevel(1);
        }
        //cheat codes!
    }
    private void moveBall(double elapsedTime) {
        if(brickGroup.getChildren().size() == 0){
            stopBall();
        }
        if (ball.checkRectIntersect(paddle)) {
            ball.bounceOffPaddle(paddle);
        }

        else if((ball.getY() + ball.getRadius()) >= SIZE_HEIGHT){
            ball.setX(ball.getX());
            ball.setY(ball.getY());
        }
        else if((ball.getX() + ball.getRadius()) >= SIZE_WIDTH || (ball.getX() - ball.getRadius()) <= 0){
            ball.setXDir(ball.getXDir() * -1);
        }
        else if((ball.getBallCircle().getCenterY() - ball.getRadius()) <= 0){
            ball.setYDir(ball.getYDir() * -1);
        }

        ball.setX(ball.getX() + ball.getXDir() * BOUNCER_SPEED * elapsedTime);
        ball.setY(ball.getY() + ball.getYDir() * BOUNCER_SPEED * elapsedTime);
    }

    private void stopBall() {
        ball.setPos(ball.getX(), ball.getY());
        ball.setYDir(0);
        ball.setXDir(0);
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
