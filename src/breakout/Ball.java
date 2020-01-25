package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * This class is holds all the information for the ball
 *
 * This class is mean to encapsulate most of the ball's behaviors (at least as much as I could fit and make the code still functional). This
 * class contains the ball's behaviors for when it collides with a paddle, a brick, calculates whether or not it's intersecting with a rectangle shape, checks whether
 * the ball passed the paddle/"died," and controls the ball's direction of travel. This class also has multiple getter and setter methods–while I recognize that this
 * is not the best design, I think this is justified because I don't want any outside objects/methods to have the ability. On the other hand, I learned that passing in
 * objects as arguments is not good design because the objects could easily be modified in the method, which not safe. However, I'm not too sure about how to solve this.
 * My class also demonstrates DRY code and "Tell the Other Guy" code. DRY code can be seen when I use the Rectangle object as a parameter to my checkIfRectIntersect
 * method since I chose to have the both paddle and brick classes to extend rectangle, so both can be passed as arguments. The "Tell the Other Guy" principle can be seen
 * when I have the ball control it's bouncing and travel behavior within this class–most of the ball's behavior is encapsulated within this class.
 * @author Michelle Tai
 */
public class Ball{
    private Circle ballCircle;
    private double initialX;
    private double initialY;

    private double xDir = 1;
    private double yDir = 1;
    /**
     * Constructor for ball class to create the ball
     * @param x is the x coordinate for it to start
     * @param y is the y start coordinate
     * @param radius is the radius of the ball
     * @author Michelle Tai
     */
    public Ball(double x, double y, double radius){
        ballCircle = new Circle(x, y, radius);
        setPos(x, y);
        initialX = x;
        initialY = y;
        ballCircle.setFill(Color.SADDLEBROWN);
    }

    /**
     * Sets the center x coordinate
     * @param x is the new center coordinate
     * @author Michelle Tai
     */
    public void setX(double x) {
        ballCircle.setCenterX(x);
    }

    /**
     * Gets the current center x coordinate of the ball
     * @return the current x center coordinate
     * @author Michelle Tai
     */
    public double getX() {
        return ballCircle.getCenterX();
    }

    /**
     * Sets the center y coordinate
     * @param y is the new center coordinate
     * @author Michelle Tai
     */
    public void setY(double y) {
        ballCircle.setCenterY(y);
    }

    /**
     * Gets the current center y coordinate of the ball
     * @return the current y center coordinate
     * @author Michelle Tai
     */
    public double getY() {
        return ballCircle.getCenterY();
    }

    /**
     * set the x and y position together
     * @param x the new x center coordinate
     * @param y the new y center coordinate
     * @author Michelle Tai
     */
    public void setPos(double x, double y) {
        ballCircle.setCenterX(x);
        ballCircle.setCenterY(y);
    }
    /**
     * Get the radius of the ball
     * @return the radius of the ball
     * @author Michelle Tai
     */
    public double getRadius(){
        return ballCircle.getRadius();
    }

    /**
     * Gets the ball's current direction of travel in the x direction
     * @return the current x direction
     * @author Michelle Tai
     */
    public double getXDir(){
        return xDir;
    }

    /**
     * Gets the ball's current direction of travel in the y direction
     * @return the current y direction
     * @author Michelle Tai
     */
    public double getYDir(){
        return yDir;
    }

    /**
     * Sets the ball's current direction of travel in the x direction
     * @param x the new x direction
     * @author Michelle Tai
     */
    public void setXDir(double x){
        xDir = x;
    }

    /**
     * Sets the ball's current direction of travel in the y direction
     * @param y is the new y direction
     * @author Michelle Tai
     */
    public void setYDir(double y){
        yDir = y;
    }

    /**
     * Gets the Shape of the ball, which can be added to a group/node
     * @return the Circle shape of the ball
     * @author Michelle Tai
     */
    public Circle getBallCircle(){
        return ballCircle;
    }

    /**
     * Sets the ball to its original position
     * @author Michelle Tai
     */
    public void resetBall(){
        setPos(initialX, initialY);
    }

    /**
     * Changes the direction of the ball's travel when the ball comes in contact with a brick
     * @param brick is the brick the ball is in contact with
     * @author Michelle Tai
     */
    public void bounceOffBrick(Brick brick){
        if( brick.getBotBound() + ballCircle.getRadius() >= ballCircle.getCenterY() && (ballCircle.getCenterX() > brick.getLeftBound() && ballCircle.getCenterX() < brick.getRightBound())){
            yDir = yDir * -1;
        }
        else if(brick.getTopBound() - ballCircle.getRadius() <= ballCircle.getCenterY() && (ballCircle.getCenterX() > brick.getLeftBound() && ballCircle.getCenterX() < brick.getRightBound())){
            yDir = yDir * -1;
        }
        else{
            xDir = xDir * -1;
        }
        brick.incHitCount();
    }

    /**
     * Changes the direction of the ball's travel when the ball comes in contact with a brick
     * @param paddle is the paddle the ball is in contact with
     * @author Michelle Tai
     */
    public void bounceOffPaddle(Paddle paddle){
        if(paddle.getBotBound() + ballCircle.getRadius() <= ballCircle.getCenterX() || paddle.getTopBound() - ballCircle.getRadius() >= ballCircle.getCenterX()){
            yDir = yDir * -1;
        }

        else if(paddle.getLeftBound() - ballCircle.getRadius() <= ballCircle.getCenterY() || paddle.getRightBound() + ballCircle.getRadius() >= ballCircle.getCenterY()){
            xDir = xDir * -1.15;
        }
    }

    /**
     * Checks whether or not the ball passes a certain point, which makes the ball "die"
     * @param bot the bottom threshold for the ball, and if below, then the ball is "dead"
     * @author Michelle Tai
     */
    public boolean checkIfDead(double bot){
        return (ballCircle.getCenterY() >= bot);
    }

    /**
     * Checks whether the ball intersects with the rectangle
     * @param rect is the Rectangle object that the ball check whether or now it instersects with it
     * @returns boolean true it intersects, false if not
     * @author Michelle Tai
     */
    public boolean checkRectIntersect(Rectangle rect){
        Shape intersection = Shape.intersect(rect, ballCircle);
        if(intersection.getBoundsInLocal().getWidth() != -1){
            return true;
        }
        return false;
    }

    /**
     * Stops the ball
     * @author Michelle Tai
     */
    public void stopBall() {
        this.setPos(this.getX(), this.getY());
        this.setYDir(0);
        this.setXDir(0);
    }
}