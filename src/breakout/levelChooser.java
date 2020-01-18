package breakout;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import static breakout.Main.*;

public class levelChooser {
    Group root = new Group();
    Group brickGroup = new Group();
    Group textGroup = new Group();
    int rows;
    Brick brickArr[][];
    Paddle paddle;
    Ball ball;
    int lives = 3;
    Text lifeStats = new Text("Lives: " + lives);
    Text losingText = new Text("You lost :(");

    public levelChooser(int width, int height, int r, int lifeNum){
        rows = r;
        lives = lifeNum;
        initializeBrickGroup(rows);
        initializeText(lifeStats);
        initializePaddleAndBall(width, height);
        root.getChildren().add(paddle);
        root.getChildren().add(ball.getBallCircle());
        root.getChildren().add(brickGroup);
        root.getChildren().add(textGroup);
    }

    Group getRoot(){
        return root;
    }
    Group getBrickGroup(){
        return brickGroup;
    }
    Group getTextGroup(){
        return textGroup;
    }
    Ball getBall(){
        return ball;
    }
    Paddle getPaddle(){
        return paddle;
    }
    int getLives(){
        return lives;
    }
    void decrementLives(){
        lives--;
    }
    void setRows(int n){
        rows = n;
    }

    void resetBrickGroup(){
        initializeBrickGroup(rows);
    }

    private void initializeBrickGroup(int rows) {
        int xRect = 0;
        int yRect = 0;
        Paint fill = Color.BLACK;
        brickArr = new Brick[rows][5];
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < 5; col++){
                brickArr[row][col] = new Brick(xRect, yRect, SIZE_WIDTH / 5, 100, fill);
                xRect += (SIZE_WIDTH / 5);
                brickGroup.getChildren().add(brickArr[row][col]);
            }
            xRect = 0;
            yRect += 100;
        }
    }

    private void initializeText(Text text) {
        Rectangle re = new Rectangle(0, SIZE_HEIGHT - STATUS_BAR_SIZE + PADDLE_HEIGHT, SIZE_WIDTH, 10);
        re.setFill(Color.BLACK);
        root.getChildren().add(re);
        text.setX(10);
        text.setY(SIZE_HEIGHT - 2 * PADDLE_HEIGHT);
        textGroup.getChildren().add(text);
    }

    private void initializePaddleAndBall(int width, int height) {
        double bouncerStartX = width / 2;
        paddle = new Paddle(bouncerStartX - PADDLE_WIDTH / 2, height - STATUS_BAR_SIZE, PADDLE_WIDTH, PADDLE_HEIGHT);
        double bouncerStartY = paddle.getY() - 20;
        ball = new Ball(bouncerStartX, bouncerStartY, BOUNCER_RADIUS);
    }

    void updateBrickArr() {
        brickGroup.getChildren().clear();
        for(int row = 0; row < brickArr.length; row++){
            for(int col = 0; col < brickArr[0].length; col++){
                Brick brick = brickArr[row][col];
//            only add to group if not at max count! so only shows then
                if(brick.getHitCount() < 1){
                    brickGroup.getChildren().add(brick);
                }
                else{
                    continue;
                }
                if(ball.checkRectIntersect(brick) && brick.getHitCount() < 1){
                    ball.bounceOffBrick(brick);
                }
            }
        }
    }

     void moveBall(double elapsedTime) {
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

    void stopBall() {
        ball.setPos(ball.getX(), ball.getY());
        ball.setYDir(0);
        ball.setXDir(0);
    }

    public void checkBall() {
        if(ball.checkIfDead(SIZE_HEIGHT - STATUS_BAR_SIZE + PADDLE_HEIGHT + 20)){
            ball.setX(paddle.getX() + PADDLE_WIDTH/2);
            ball.setY(paddle.getY() - 20);
            ball.setYDir((ball.getYDir() * -1));// 5 so that there isn't weird effects with the paddle
            decrementLives();
            lifeStats.setText("Lives: " + getLives());

        }

    }

    public void checkLives() {
        if(getLives() == 0){
            stopBall();
            losingText.setX(SIZE_WIDTH /2);
            losingText.setY(SIZE_HEIGHT/2);
            textGroup.getChildren().add(losingText);
            textGroup.getChildren().removeAll(losingText);
        }

    }

    public void incrementLives() {
        lives++;
    }

    public void setLifeStats() {
        lifeStats.setText("Lives: " + getLives());
    }
}