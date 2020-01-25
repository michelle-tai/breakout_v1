package breakout;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import static breakout.Main.*;
/**
 * Changes the level layout by changing the root of the scene.
 * Each level has its own brick grid, ball, paddle, and score
 * @author Michelle Tai
 */
public class levelChooser {
    Group root = new Group();
    Group brickGroup = new Group();
    Group textGroup = new Group();
    Group ballGroup = new Group();
    Group paddleGroup = new Group();
    int rows;
    Brick brickArr[][];
    Paddle paddle;
    Ball ball;
    int lives = 3;
    Text lifeStats = new Text("Lives: " + lives);
    Text losingText = new Text("You lost :(");
    int width;
    int height;
    final int SPACE_BETWEEN = 5;
    int currLevel;

    int score = 0;
    Text scoreStats = new Text("Score: " + score);

    /**
     * Constructor for the levelChooser class to initialize all parts of the level
     * @author Michelle Tai
     * @param w is the width of the level screen
     * @param h is the height of the level screen
     * @param r is the number of rows in the block grid, which corresponds with the level number
     * @para lifeNum is the number of lives you start with on each level
     */
    public levelChooser(int w, int h, int r, int lifeNum){
        root.getChildren().clear();
        rows = r;
        currLevel = r;
        lives = lifeNum;
        width = w;
        height = h;
        if(rows == 0){
            Text startText = new Text("Hi! Welcome to my game.\n Use the left and right arrow keys to move\n" +
                    "Press 1 to start");
            startText.setX(200);
            startText.setY(SIZE_HEIGHT/2);
            textGroup.getChildren().add(startText);
        }
        else{
            initializeBrickGroup(rows);
            initializeText(lifeStats, scoreStats);
            initializePaddleAndBall(width, height);
            root.getChildren().add(brickGroup);
            root.getChildren().add(textGroup);
            root.getChildren().add(ballGroup);
            root.getChildren().add(paddleGroup);
        }


    }
    /**
     * Getter for the root node
     * @return the root node
     * @author Michelle Tai
     */
    public Group getRoot(){
        return root;
    }

    /**
     * Getter for the level's ball
     * @author Michelle Tai
     * @return the ball so that it can be manipulated
     */
    public Ball getBall(){
        return ball;
    }
    /**
     * Getter for the level's paddle
     * @author Michelle Tai
     * @return the paddle of the level so that it can be moved by other methods
     */
    public Paddle getPaddle(){
        return paddle;
    }
    /**
     * Getter for the number of lives left
     * @author Michelle Tai
     * @return the number of lives left
     */
    public int getLives(){
        return lives;
    }

    /**
     * Decreases the number of lives left by 1
     * @author Michelle Tai
     */
    public void decrementLives(){
        lives--;
    }
    /**
     * Increases the number of lives left by 1
     * @author Michelle Tai
     */
    public void incrementLives() {
        lives++;
    }
    /**
     * Gets the current score
     * I tried to use this to make the scores carry over between levels, but it doesn't work
     * @author Michelle Tai
     * @return the current score of the level
     */
    public int getScore(){
        return score;
    }
    /**
     * Resets the brick group
     * @author Michelle Tai
     */
    public void resetBrickGroup(){
        initializeBrickGroup(rows);
    }

    /**
     * Updates the current life stats once a ball "dies"
     * @author Michelle Tai
     */
    public void setLifeStats() {
        lifeStats.setText("Lives: " + getLives());
    }

    /**
     * Updates the score status to reflect the current score, especially after
     * a block is gotten rid of
     * @author Michelle Tai
     */
    public void setScore(int n){
        score = n;
    }

    /**
     * Initializes the brick grid and adds it to the level's brick group
     * @author Michelle Tai
     * @param rows indicates the number of rows present in the grid
     */
    public void initializeBrickGroup(int rows) {
        int xRect = SPACE_BETWEEN;
        int yRect = 0;
        brickArr = new Brick[rows][5];
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < 5; col++){
                brickArr[row][col] = new Brick(xRect, yRect, SIZE_WIDTH / 5 - SPACE_BETWEEN, 100, row + 1);
                xRect += (SIZE_WIDTH / 5);
                brickGroup.getChildren().add(brickArr[row][col]);
            }
            xRect = SPACE_BETWEEN;
            yRect += 100 + SPACE_BETWEEN;
        }
    }

    /**
     * Removes all the nodes of the brickgroup and only adds in the bricks that don't have a hitcount
     * larger than the maximum hit count (aka the hit count needed to clear the brick)
     *
     * @author Michelle Tai
     */
    public void updateBrickArr() {
        brickGroup.getChildren().clear();
        for(int row = 0; row < brickArr.length; row++){
            for(int col = 0; col < brickArr[0].length; col++){
                Brick brick = brickArr[row][col];
//            only add to group if not at max count! so only shows then
                if(ball.checkRectIntersect(brick) && brick.getHitCount() < brick.getMaxHitVal()) {
                    ball.bounceOffBrick(brick);
                    if(brick.getHitCount() == brick.getMaxHitVal()){
                        score = score + brick.getMaxHitVal();
                        scoreStats.setText("Score: " + score);
                    }
                }

                if(brick.getHitCount() < brick.getMaxHitVal()){
                    brickGroup.getChildren().add(brick);
                }

            }
        }
    }

    /**
     * Initializes the status bar of the lives and scores
     * @param lifeText is the text for the lives left
     * @param scoreText is the text for the score
     * @author Michelle Tai
     */
    public void initializeText(Text lifeText, Text scoreText) {
        Rectangle re = new Rectangle(0, SIZE_HEIGHT - STATUS_BAR_SIZE + PADDLE_HEIGHT, SIZE_WIDTH, 10);
        re.setFill(Color.BLACK);
        root.getChildren().add(re);
        lifeText.setX(10);
        lifeText.setY(SIZE_HEIGHT - 2 * PADDLE_HEIGHT);
        scoreText.setX(100);
        scoreText.setY(SIZE_HEIGHT - 2 * PADDLE_HEIGHT);
        textGroup.getChildren().clear();
        textGroup.getChildren().add(lifeText);
        textGroup.getChildren().add(scoreText);
    }

    /**
     * Initializes the ball and the paddle int he level so that it stars in the center each time
     * Adds the ball and paddle to its own group, and adds those groups to the root node
     * @author Michelle Tai
     */
    public void initializePaddleAndBall(int width, int height) {
        double bouncerStartX = width / 2;
        paddle = new Paddle(bouncerStartX - PADDLE_WIDTH / 2, height - STATUS_BAR_SIZE, PADDLE_WIDTH, PADDLE_HEIGHT);
        double bouncerStartY = paddle.getY() - 20;
        ball = new Ball(bouncerStartX, bouncerStartY, BOUNCER_RADIUS);
        paddleGroup.getChildren().add(paddle);
        ballGroup.getChildren().add(ball.getBallCircle());
    }


    /**
     * Makes the ball bounce off the paddle, walls, and bricks by changing their x direction and y direction
     * @param elapsedTime is the time that has elapsed since the start of the game
     * @author Michelle Tai
     */
     public void moveBall(double elapsedTime) {
        if(brickGroup.getChildren().size() == 0){
            ball.stopBall();
            int nextLevel = currLevel + 1;
            Text nextlevel = new Text("Press "+ nextLevel + " to go to the next level");
            nextlevel.setX(100);
            nextlevel.setY(350);
            textGroup.getChildren().add(nextlevel);
        }
        if (ball.checkRectIntersect(paddle)) {
            ball.bounceOffPaddle(paddle);
        }

        else if(ball.getY() + ball.getRadius() >= SIZE_HEIGHT){
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


    /**
     * Checks whether the ball "dies" if it passes a certain point and decrements the lives
     * @author Michelle Tai
     */
    public void checkBall() {
        if(ball.checkIfDead(SIZE_HEIGHT - STATUS_BAR_SIZE + PADDLE_HEIGHT + 10)){
            ball.setX(paddle.getX() + PADDLE_WIDTH/2);
            ball.setY(paddle.getY() - 20);
            ball.setYDir((ball.getYDir() * -1));// 5 so that there isn't weird effects with the paddle
            decrementLives();
            lifeStats.setText("Lives: " + getLives());

        }

    }
    /**
     * checks the lives left and stops the ball when there are no lives left.
     * @author Michelle Tai
     */
    public void checkLives() {
        if(getLives() == 0){
            ball.stopBall();
            losingText.setX(SIZE_WIDTH /2);
            losingText.setY(SIZE_HEIGHT/2);
        }

    }

    /**
     * Changes the level layout
     * @param i is the number of rows to be added to the brick grid and is the level number as well
     * @author Michelle Tai
     */
    public void setLevel(int i) {
        root.getChildren().clear();
        brickGroup.getChildren().clear();
        textGroup.getChildren().clear();
        initializeBrickGroup(rows);
        initializeText(lifeStats, scoreStats);
        initializePaddleAndBall(width, height);
        root.getChildren().add(brickGroup);
        root.getChildren().add(textGroup);

    }


}