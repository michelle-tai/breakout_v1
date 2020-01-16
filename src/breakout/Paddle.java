package breakout;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.awt.event.KeyEvent;

public class Paddle extends Sprite{
    int paddleSpeed;
    private Rectangle rect;
    int initialX;
    int initialY;
    public Paddle(int x, int y, int width, int height){
        spriteWidth = width;
        spriteHeight = height;
        rect = new Rectangle(width, height, Color.PERU);
        setPaddlePos(x, y);
        setPaddleBound();
    }

    protected void setPaddlePos(int x, int y){
        setPos(x, y);
        rect.setX(x);
        rect.setY(y);
    }

    void setPaddleBound(){
        leftBound = x;
        rightBound = x + spriteWidth;
        topBound = y;
        botBound = y - spriteHeight;
    }

    void setPaddleSpeed(int speed){
        paddleSpeed = speed;
    }

    Rectangle getPaddleRect(){
        return rect;
    }

    void setInitialX(int initial){
        initialX = initial;
    }

    void setInitialY(int initial){
        initialY = initial;
    }

    void reset(){
        setPaddlePos(initialX, initialY);
    }
}
