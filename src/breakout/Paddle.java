package breakout;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static breakout.Main.SIZE_WIDTH;

public class Paddle extends Rectangle {

    private double paddleSpeed = 40;
    private double initialX;
    private double initialY;
    private double leftBound;
    private double rightBound;
    private double topBound;
    private double botBound;


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
    public Paddle(double x, double y, double width, double height){
        makePaddle(x, y, width, height);
    }

    void makePaddle(double x, double y, double width, double height){
//        setWidth(width);
//        spriteHeight = height;
//        rect = new Rectangle(width, height, Color.PERU);
        this.setWidth(width);
        this.setHeight(height);
        this.setFill(Color.PERU);
        setPos(x, y);
        setBounds();
        initialX = x;
        initialY = y;
    }
    void handleKeyInput(KeyCode code){
        if (code == KeyCode.RIGHT && (this.getX() < SIZE_WIDTH - this.getWidth())) {
            this.setX(this.getX() + paddleSpeed);

        }
        else if (code == KeyCode.LEFT && (this.getX() > 0)) {
            this.setX(this.getX() - paddleSpeed);
        }
    }

    void reset(){
        setPos(initialX, initialY);
    }

    void setSpeed(int speed){
        paddleSpeed = speed;
    }
}