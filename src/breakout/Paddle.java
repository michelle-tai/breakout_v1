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
public class Paddle implements Sprite {
    Rectangle rect;
    private double paddleSpeed = 10;
    private double initialX;
    private double initialY;
    private double leftBound;
    private double rightBound;
    private double topBound;
    private double botBound;

    @Override
    public void setX(double x) {
        rect.setX(x);

    }
    @Override
    public double getX() {
        return rect.getX();
    }

    @Override
    public void setY(double y) {
        rect.setY(y);
    }

    @Override
    public double getY() {
        return rect.getY();
    }

    @Override
    public void setPos(double x, double y) {
        rect.setX(x);
        rect.setY(y);
    }

    public double getWidth() {
        return rect.getWidth();
    }

    public double getHeight() {
        return rect.getHeight();
    }

    public void setBounds() {
        leftBound = rect.getX();
        rightBound = rect.getX() + rect.getWidth();
        topBound = rect.getY();
        botBound = rect.getY() - rect.getHeight();
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

    Rectangle getRect(){
        return rect;
    }
    public Paddle(double x, double y, double width, double height){
        makePaddle(x, y, width, height);
    }

    void makePaddle(double x, double y, double width, double height){
//        setWidth(width);
//        spriteHeight = height;
        rect = new Rectangle(width, height, Color.PERU);
        setPos(x, y);
        setBounds();
        initialX = x;
        initialY = y;
    }
    void handleKeyInput(KeyCode code){
        if (code == KeyCode.RIGHT && (rect.getX() < SIZE_WIDTH - rect.getWidth())) {
            rect.setX(rect.getX() + paddleSpeed);

        }
        else if (code == KeyCode.LEFT && (rect.getX() > 0)) {
            rect.setX(rect.getX() - paddleSpeed);
        }
    }

    void reset(){
        setPos(initialX, initialY);
    }

    void setSpeed(int speed){
        paddleSpeed = speed;
    }
}