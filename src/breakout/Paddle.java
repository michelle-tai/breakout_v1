package breakout;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static breakout.Main.SIZE_WIDTH;

/**
 * This class is holds all the information for a paddle
 * Extends the Rectangle class so that this class can be added directly to the root nodes/groups as a node itself
 * @author Michelle Tai
 */
public class Paddle extends Rectangle {
    private double paddleSpeed = 40;
    private double initialX;
    private double initialY;
    private double leftBound;
    private double rightBound;
    private double topBound;
    private double botBound;

    /**
     * Constructor for the paddle class
     * @param x is x coordinate for where the Rectangle for the paddle should be drawn on the scene
     * @param y is the y coordinate for where the Rectangle for the paddle should be drawn
     * @param width is the width of the paddle
     * @param height is the height of the paddle
     * @author Michelle Tai
     */
    public Paddle(double x, double y, double width, double height) {
        this.setWidth(width);
        this.setHeight(height);
        this.setFill(Color.PERU);
        setPos(x, y);
        setBounds();
        initialX = x;
        initialY = y;
    }


    /**
     * Sets the x and y position of the brick (where the top left edge should be)
     * @param x is the new x position
     * @param y is the new y position
     * @author Michelle Tai
     */
    public void setPos(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    /**
     * Sets the boundaries of the paddle, which is used to determine collisions
     * @author Michelle Tai
     */
    public void setBounds() {
        leftBound = this.getX();
        rightBound = this.getX() + this.getWidth();
        topBound = this.getY();
        botBound = this.getY() - this.getHeight();
    }

    /**
     * Gets the x location of the left edge of the paddle
     * @return the x location of the left edge of the paddle
     * @author Michelle Tai
     */
    public double getLeftBound() {
        return leftBound;
    }

    /**
     * Gets the x location of the right edge of the paddle
     * @return the x location of the right edge of the paddle
     * @author Michelle Tai
     */
    public double getRightBound() {
        return rightBound;
    }

    /**
     * Gets the y location of the top edge of the paddle
     * @return the y location of the top edge of the paddle
     * @author Michelle Tai
     */
    public double getTopBound() {
        return topBound;
    }

    /**
     * Gets the y location of the bottom edge of the paddle
     * @return the y location of the bottom edge of the paddle
     * @author Michelle Tai
     */
    public double getBotBound() {
        return botBound;
    }

    /**
     * Resets the position of the paddle to its original location (set by the constructor)
     */
    public void reset(){
        setPos(initialX, initialY);
    }

    /**
     * Moves the paddle right and left depending on whether the arrow keys were hit or not by the user
     * @param code is any input from the user from the keyboard
     */
    public void handleKeyInput(KeyCode code){
        if (code == KeyCode.RIGHT && (this.getX() < SIZE_WIDTH - this.getWidth())) {
            this.setX(this.getX() + paddleSpeed);

        }
        else if (code == KeyCode.LEFT && (this.getX() > 0)) {
            this.setX(this.getX() - paddleSpeed);
        }
    }


}