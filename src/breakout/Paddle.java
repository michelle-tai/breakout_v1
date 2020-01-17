package breakout;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static breakout.Main.SIZE_WIDTH;

//public class Paddle implements Sprite, rectangularSprite{
//    int paddleSpeed = 10;
//    private Rectangle paddleRect;
//    int initialX;
//    int initialY;
//    public Paddle(int x, int y, int width, int height){
//        makePaddle(x, y, width, height);
//    }
//
//    void makePaddle(int x, int y, int width, int height){
//        spriteWidth = width;
//        spriteHeight = height;
//        paddleRect = new Rectangle(width, height, Color.PERU);
//        setPaddlePos(x, y);
//        setPaddleBound();
//    }
//
//    int
//
//    protected void setPaddlePos(int x, int y){
//        setPos(x, y);
//        paddleRect.setX(x);
//        paddleRect.setY(y);
//    }
//
//
//    void setPaddleBound(){
//        leftBound = x;
//        rightBound = x + spriteWidth;
//        topBound = y;
//        botBound = y - spriteHeight;
//    }
//
//    void setPaddleSpeed(int speed){
//        paddleSpeed = speed;
//    }
//
//    Rectangle getPaddleRect(){
//        return paddleRect;
//    }
//
//    void setInitialX(int initial){
//        initialX = initial;
//    }
//
//    void setInitialY(int initial){
//        initialY = initial;
//    }
//
//    void handleKeyInput(KeyCode code){
//        if (code == KeyCode.RIGHT && (paddleRect.getX() < SIZE_WIDTH - paddleRect.getWidth())) {
//            paddleRect.setX(paddleRect.getX() + paddleSpeed);
//
//        }
//        else if (code == KeyCode.LEFT && (paddleRect.getX() > 0)) {
//            paddleRect.setX(paddleRect.getX() - paddleSpeed);
//        }
//    }
//
//    void reset(){
//        setPaddlePos(initialX, initialY);
//    }
//}
public class Paddle extends RectangularSprite {

    private double paddleSpeed = 10;
    private double initialX;
    private double initialY;

    public Paddle(double x, double y, double width, double height){
        makePaddle(x, y, width, height);
    }

    void makePaddle(double x, double y, double width, double height){
//        setWidth(width);
//        spriteHeight = height;
        super.rect = new Rectangle(width, height, Color.PERU);
        setPos(x, y);
        setBounds();
        initialX = x;
        initialY = y;
    }
    void handleKeyInput(KeyCode code){
        if (code == KeyCode.RIGHT && (super.rect.getX() < SIZE_WIDTH - super.rect.getWidth())) {
            super.rect.setX(super.rect.getX() + paddleSpeed);

        }
        else if (code == KeyCode.LEFT && (super.rect.getX() > 0)) {
            super.rect.setX(super.rect.getX() - paddleSpeed);
        }
    }

    void reset(){
        setPos(initialX, initialY);
    }

    void setSpeed(int speed){
        paddleSpeed = speed;
    }
}