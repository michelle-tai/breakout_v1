package breakout;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

//public class Brick extends Sprite{
//    private int hitCount = 0;
//    Rectangle rect;
//    //width and height from sprite
//
//    public Brick(int x, int y, int width, int height, Paint color){
//        makeBrick(x, y, width, height, color);
//    }
//
//    void makeBrick(int x, int y, int width, int height, Paint color){
//        spriteWidth = width;
//        spriteHeight = height;
//        rect = new Rectangle(width, height, color);
//        setBrickPos(x, y);
//        setBrickBound();
//    }
//
//
//
//
//    void setBrickPos(int x, int y){
//        rect.setX(x);
//        rect.setY(y);
//    }
//
//    void setBrickBound(){
//        leftBound = x;
//        rightBound = x + spriteWidth;
//        topBound = y;
//        botBound = y - spriteHeight;
//    }
//
//    void setBrickColor(Paint color){
//        rect.setFill(color);
//    }
//    Rectangle getBrickRect(){
//        return rect;
//    }
//
//    void setHitCount(int num){
//        hitCount = num;
//    }
//    int getHitCount(){
//        return hitCount;
//    }
//
//    void decrementHitCount(int num){
//        hitCount = hitCount - num;
//    }
//
//}

public class Brick extends RectangularSprite{
    private int hitCount = 0;
    //width and height from sprite

    public Brick(double x, double y, double width, double height, Paint color){
        makeBrick(x, y, width, height, color);
    }

    void makeBrick(double x, double y, double width, double height, Paint color){
        super.rect = new Rectangle(width, height, color);
        setPos(x, y);
        setBounds();
    }

    void setHitCount(int num){
        hitCount = num;
    }
    int getHitCount(){
        return hitCount;
    }

    void decrementHitCount(int num){
        hitCount = hitCount - num;
    }

}