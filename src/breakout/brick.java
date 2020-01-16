package breakout;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Brick extends Sprite{
    private int leftBound;
    private int rightBound;
    private int topBound;
    private int botBound;
    private Rectangle rect;
    private int hitCount = 0;
    //width and height from sprite

    public Brick(int x, int y, int width, int height, Paint color){
        spriteWidth = width;
        spriteHeight = height;
        rect = new Rectangle(width, height, color);
        setBrickPos(x, y);
        setBrickBound();
    }

    void setBrickPos(int x, int y){
        this.setX(x);
        this.setY(y);
        rect.setX(x);
        rect.setY(y);
    }

    void setBrickBound(){
        leftBound = x;
        rightBound = x + spriteWidth;
        topBound = y;
        botBound = y - spriteHeight;
    }

    int getLeftBound(){
        return leftBound;
    }

    int getRightBound(){
        return rightBound;
    }

    int getTopBound(){
        return topBound;
    }

    int getBotBound(){
        return botBound;
    }

    void setBrickColor(Paint color){
        rect.setFill(color);
    }
    Rectangle getRect(){
        return rect;
    }

    void setHitCount(int num){
        hitCount = num;
    }
    int getHitCount(){
        return hitCount;
    }



//    public void setColor(Paint color){
//        this.setFill
//    }


}
