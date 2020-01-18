package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Brick extends Rectangle{


    private int hitCount = 0;
    //width and height from sprite

//    Rectangle rect;
    private double leftBound;
    private double rightBound;
    private double topBound;
    private double botBound;

    public Brick(double x, double y, double width, double height, Paint color){
         this.setWidth(width);
         this.setHeight(height);
        this.setFill(color);
//        System.out.println(color);
        setPos(x, y);
        setBounds();
    }



    public void setPos(double x, double y) {
        this.setX(x);
        this.setY(y);
    }



    public void setBounds() {
        leftBound = this.getX();
        rightBound = this.getX() + this.getWidth();
        topBound = this.getY();
        botBound = this.getY() - this.getHeight();
    }

    public double getLeftBound() {
        return leftBound;
    }

    public double getRightBound() {
        return rightBound;
    }

    public double getTopBound() {
        return topBound;
    }

    public double getBotBound() {
        return botBound;
    }

//    Rectangle getRect(){
//        return rect;
//    }

    void setHitCount(int num){
        hitCount = num;
    }
    int getHitCount(){
        return hitCount;
    }

    void incHitCount(){
        hitCount++;
    }

}