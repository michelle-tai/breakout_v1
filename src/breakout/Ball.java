package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.w3c.dom.css.Rect;

import static breakout.Main.SIZE_HEIGHT;


//public class Ball extends Sprite{
//    private Circle ballCircle;
//    private int initialX;
//    private int initialY;
//    private int ballSpeed;
//
//    private int xDir = 1;
//    private int yDir = -1;
//
//    public Ball(int x, int y, int radius){
//        createBall(x, y, radius);
//    }
//
//    void createBall(int x, int y, int radius){
//        ballCircle = new Circle(x, y, radius);
//        setPos(x, y);
//        initialX = x;
//        initialY = y;
//        ballCircle.setFill(Color.SADDLEBROWN);
//    }
//
//    void setBallSpeed(int speed){
//        ballSpeed = speed;
//    }
//
//    int getBallSpeed(){
//        return ballSpeed;
//    }
//
//    int getXDir(){
//        return xDir;
//    }
//
//    int getYDir(){
//        return yDir;
//    }
//
//    void setXDir(int x){
//        xDir = x;
//    }
//
//    void setYDir(int y){
//        yDir = y;
//    }
//
//    double getX(){
//        return ballCircle.getCenterX();
//    }
//
//    int getRadius(){
//        return (int) ballCircle.getRadius();
//    }
//
//    void setBallPos(int x, int y){
//        ballCircle.setCenterX(x);
//        ballCircle.setCenterY(y);
//    }
//
//    void bounceOffBrick(Brick rect){
//        changeDirection(rect);
//        rect.decrementHitCount(1);
//    }
//
//    void changeDirection(Sprite rect) {
//        if(rect.getBotBound() + ballCircle.getRadius() <= ballCircle.getCenterX() || rect.getTopBound() - ballCircle.getRadius() >= ballCircle.getCenterX()){
//            xDir = xDir * -1;
//        }
//
//        if(rect.getLeftBound() - ballCircle.getRadius() <= ballCircle.getCenterY() || rect.getRightBound() + ballCircle.getRadius() >= ballCircle.getCenterY()){
//            yDir = yDir * -1;
//        }
//
//    }
//    void bounceOffPaddle(Paddle paddle){
//        changeDirection(paddle);
//    }
//
//    void checkIfDead(){
//        if((ballCircle.getCenterX() + ballCircle.getRadius()) >= SIZE_HEIGHT){
////            xDir = 0;
////            yDir = 0;
////        }
//    }
////    void changeDirection(Sprite rect){
////        if(rect.getBotBound() + ballCircle.getRadius() <= ballCircle.getCenterX() || rect.getTopBound() - ballCircle.getRadius() >= ballCircle.getCenterX()){
////            xDir = xDir * -1;
////        }
////
////        if(rect.getLeftBound() - ballCircle.getRadius() <= ballCircle.getCenterY() || rect.getRightBound() + ballCircle.getRadius() >= ballCircle.getCenterY()){
////            yDir = yDir * -1;
////        }
//    }
//    Circle getBallCircle(){
//        return ballCircle;
//    }
//    boolean checkRectIntersect(Rectangle rect){
//        Shape intersection = Shape.intersect(rect, ballCircle);
//        if(intersection.getBoundsInLocal().getWidth() != -1){
//            return true;
//        }
//        return false;
//    }
//
//    void resetBall(){
//        setPos(initialX, initialY);
//    }
//
//
//}

public class Ball implements Sprite{

    private Circle ballCircle;
    private double initialX;
    private double initialY;
    private double ballSpeed;

    private double xDir = 1;
    private double yDir = -1;

    public Ball(double x, double y, double radius){
        createBall(x, y, radius);
    }

    void createBall(double x, double y, double radius){
        ballCircle = new Circle(x, y, radius);
        setPos(x, y);
        initialX = x;
        initialY = y;
        ballCircle.setFill(Color.SADDLEBROWN);
    }

    @Override
    public void setX(double x) {
        ballCircle.setCenterX(x);
    }

    @Override
    public double getX() {
        return ballCircle.getCenterX();
    }

    @Override
    public void setY(double y) {
        ballCircle.setCenterY(y);
    }

    @Override
    public double getY() {
        return ballCircle.getCenterY();
    }

    @Override
    public void setPos(double x, double y) {
        ballCircle.setCenterX(x);
        ballCircle.setCenterY(y);
    }

    public double getRadius(){
        return ballCircle.getRadius();
    }

    double getBallSpeed(){
        return ballSpeed;
    }

    double getXDir(){
        return xDir;
    }

    double getYDir(){
        return yDir;
    }

    void setXDir(double x){
        xDir = x;
    }

    void setYDir(double y){
        yDir = y;
    }

    void bounceOffBrick(Brick brick){
        if(brick.getBotBound() + ballCircle.getRadius() <= ballCircle.getCenterX() || brick.getTopBound() - ballCircle.getRadius() >= ballCircle.getCenterX()){
            yDir = yDir * -1;
        }

        else if(brick.getLeftBound() - ballCircle.getRadius() <= ballCircle.getCenterY() || brick.getRightBound() + ballCircle.getRadius() >= ballCircle.getCenterY()){
//            yDir = yDir * -1;
            xDir = xDir * -1.15;
        }
        brick.incHitCount();
    }

    void changeDirection(RectangularSprite rect) {
        if(rect.getBotBound() + ballCircle.getRadius() <= ballCircle.getCenterX() || rect.getTopBound() - ballCircle.getRadius() >= ballCircle.getCenterX()){
            yDir = yDir * -1;
        }

        else if(rect.getLeftBound() - ballCircle.getRadius() <= ballCircle.getCenterY() || rect.getRightBound() + ballCircle.getRadius() >= ballCircle.getCenterY()){

            xDir = xDir * -1.15;
        }

    }
    void bounceOffPaddle(Paddle paddle){
        int xConst;
        int yConst;
        if(paddle.getBotBound() + ballCircle.getRadius() <= ballCircle.getCenterX() || paddle.getTopBound() - ballCircle.getRadius() >= ballCircle.getCenterX()){
            yDir = yDir * -1;
        }

        else if(paddle.getLeftBound() - ballCircle.getRadius() <= ballCircle.getCenterY() || paddle.getRightBound() + ballCircle.getRadius() >= ballCircle.getCenterY()){
//            yDir = yDir * -1;
            xDir = xDir * -1.15;
        }
    }

    boolean checkIfDead(){
        return (ballCircle.getCenterX() + ballCircle.getRadius()) >= SIZE_HEIGHT;
    }

    Circle getBallCircle(){
        return ballCircle;
    }
    boolean checkRectIntersect(Rectangle rect){
        Shape intersection = Shape.intersect(rect, ballCircle);
        if(intersection.getBoundsInLocal().getWidth() != -1){
            return true;
        }
        return false;
    }

    void resetBall(){
        setPos(initialX, initialY);
    }


    public void setRadius(double radius) {
        ballCircle.setRadius(radius);
    }
}