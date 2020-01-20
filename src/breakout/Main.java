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

/**
 * This class is the main driver for my breakout game.
 * @author Michelle Tai
 */
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
    private int yDir = 1;
    Brick brickArr[][];
    private Ball ball;
    private Paddle paddle;
    Group root = new Group();
    Group brickGroup = new Group();
    Group ballPaddleGroup = new Group();
    Group textGroup = new Group();
    int lives;
    Text lifeStats = new Text("Lives: " + lives);
    int level = 1;
    Text losingText = new Text("You lost :(");
    levelChooser lvl;
    int currLevelNum = 1;

    int score = 0;
    Text scoreStats = new Text("Score: " + score);

    boolean ifStart = true;


    /**
     * Initialize what will be displayed and how it will be updated.
     * @author Michelle Tai
     * @author Robert C. Duvall
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
        startGame();
        // create a place to see the shapes
        Scene scene = new Scene(root, width, height, background);
        // respond to input
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return scene;
    }

    /**
     * Crete the splash screen for the game and has the game start on this screen
     */
    private void startGame() {
        Text startText = new Text("Hi! Welcome to my game.\n Use the left and right arrow keys to move\n" +
                "Press 1 to start");
        startText.setX(100);
        startText.setY(SIZE_HEIGHT/2);
        root.getChildren().add(startText);
    }

    /**
     * Change properties of shapes in small ways to animate them over time
     * Updates the game screen over time
     * @author Michelle Tai
     * @param elapsedTime is the time that has passed
     */
    private void step (double elapsedTime) {
        // update "actors" attributes
        if(!ifStart){
            lvl.updateBrickArr();
            lvl.moveBall(elapsedTime);
            lvl.checkBall();
            lvl.checkLives();
        }

    }

    /**
     * handles any user inputs, like pressing a number key or the arrow keys.
     * Moves the paddle right and left when right or left arrow key is pressed
     * The number keys change the level
     * @author Michelle Tai
     * @param code is the user input
     */
    private void handleKeyInput (KeyCode code) {
        if(ifStart){
            if(code == KeyCode.DIGIT1){
                ifStart = false;
                currLevelNum = 1;
                setNewLevel();
            }
        }
        else {
            paddle = lvl.getPaddle();
            paddle.handleKeyInput(code);
            if (code == KeyCode.R) {
                ball.resetBall();
                paddle.reset();
            }

            if (code == KeyCode.DIGIT1) {
                currLevelNum = 1;
                setNewLevel();

            }
            if (code == KeyCode.DIGIT2) {
                currLevelNum = 2;
                setNewLevel();
            }
            if (code == KeyCode.DIGIT3 || code == KeyCode.DIGIT4 || code == KeyCode.DIGIT5 || code == KeyCode.DIGIT6 || code == KeyCode.DIGIT7 || code == KeyCode.DIGIT8 || code == KeyCode.DIGIT9) {
                currLevelNum = 3;
                setNewLevel();
            }

            if (code == KeyCode.L) {
                lvl.incrementLives();
                lvl.setLifeStats();
            }

            if (code == KeyCode.SPACE) {
                //            root.getChildren().clear();
                paddle.reset();
                ball.resetBall();
                brickGroup.getChildren().clear();
                lvl.resetBrickGroup();
            }
        }
    }

    /**
     * Changes the level layout by changing the root of the scene
     * @author Michelle Tai
     */
    private void setNewLevel() {
        int prevLevelScore;
        if(currLevelNum > 1){
            prevLevelScore = lvl.getScore();
        }
        else{
            prevLevelScore = 0;
        }

        lvl = new levelChooser(SIZE_WIDTH, SIZE_HEIGHT, currLevelNum, 3);
        lvl.setScore(prevLevelScore);
        root = lvl.getRoot();
        ball = lvl.getBall();
        paddle = lvl.getPaddle();
        lives = lvl.getLives();
        myScene.setRoot(root);
    }

    /**
     * Start the program.
     * @author Robert C. Duvall
     */
    public static void main (String[] args) {
        launch(args);
    }
}
