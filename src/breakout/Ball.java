package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;



public class Ball implements Sprite{

    private Circle ballCircle;
    private double initialX;
    private double initialY;
    private double ballSpeed;

    private double xDir = 1;
    private double yDir = 1;

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
//        if(brick.getBotBound() + ballCircle.getRadius() <= ballCircle.getCenterY() || brick.getTopBound() - ballCircle.getRadius() >= ballCircle.getCenterY()){
//            yDir = yDir * -1;
//        }
        //if within left and right, then must change in y dir

        //if both top and bottom are smaller, than change dir
//        System.out.println(brick.getBotBound() + ballCircle.getRadius());
//        System.out.println(brick.getTopBound() - ballCircle.getRadius());
//        System.out.println(brick.getLeftBound() - ballCircle.getRadius());
//        System.out.println(brick.getRightBound() + ballCircle.getRadius());
//        System.out.println(ballCircle.getCenterX());
//        System.out.println(ballCircle.getCenterY());
        if( brick.getBotBound() + ballCircle.getRadius() >= ballCircle.getCenterY() && (ballCircle.getCenterX() > brick.getLeftBound() && ballCircle.getCenterX() < brick.getRightBound())){
            yDir = yDir * -1;
//            System.out.println("y");
        }
        else if(brick.getTopBound() - ballCircle.getRadius() <= ballCircle.getCenterY() && (ballCircle.getCenterX() > brick.getLeftBound() && ballCircle.getCenterX() < brick.getRightBound())){
            yDir = yDir * -1;
//            System.out.println("y");
        }
        else{
            xDir = xDir * -1;
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

    boolean checkIfDead(double bot){
        return (ballCircle.getCenterY() >= bot);
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