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



}