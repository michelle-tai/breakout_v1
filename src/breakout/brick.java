package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import static breakout.Main.BACKGROUND;

/**
 * This class is holds all the information for a brick
 * Extends the Rectangle class so that this class can be added directly to the root nodes/groups as a node itself
 * @author Michelle Tai
 */

public class Brick extends Rectangle{
    private int hitCount = 0;
    private int maxHit = 0;
    private double leftBound;
    private double rightBound;
    private double topBound;
    private double botBound;

    /**
     * Constructor for the ball class
     * @param x is x coordinate for where the Rectangle for the brick should be drawn on the scene
     * @param y is the y coordinate for where the Rectangle for the brick should be drawn
     * @param width is the width of the block
     * @param height is the height of the block
     * @param maxHitVal is the number of hits it takes to clear the block
     * @author Michelle Tai
     */
    public Brick(double x, double y, double width, double height, int maxHitVal){
         this.setWidth(width);
         this.setHeight(height);
        this.setStroke(BACKGROUND);
        maxHit = maxHitVal;
        if(maxHit == 1){
            this.setFill(Color.PINK);
        }
        else if(maxHit == 2){
            this.setFill(Color.ALICEBLUE);
        }
        else if(maxHit == 3){
            this.setFill(Color.GREEN);
        }
        setPos(x, y);
        setBounds();
    }
    /**
     * Get the number of hits it takes to clear the block
     * @return the number of hits it takes to clear the block
     * @author Michelle Tai
     */
    int getMaxHitVal(){
        return maxHit;
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
     * Sets the boundaries of the block, which is used to determine collisions
     * @author Michelle Tai
     */
    public void setBounds() {
        leftBound = this.getX();
        rightBound = this.getX() + this.getWidth();
        topBound = this.getY();
        botBound = this.getY() + this.getHeight();
    }
    /**
     * Gets the x location of the left edge of the block
     * @return the x location of the left edge of the block
     * @author Michelle Tai
     */
    public double getLeftBound() {
        return leftBound;
    }

    /**
     * Gets the x location of the right edge of the block
     * @return the x location of the right edge of the block
     * @author Michelle Tai
     */
    public double getRightBound() {
        return rightBound;
    }

    /**
     * Gets the y location of the top edge of the block
     * @return the y location of the top edge of the block
     * @author Michelle Tai
     */
    public double getTopBound() {
        return topBound;
    }

    /**
     * Gets the y location of the bottom edge of the block
     * @return the y location of the bottom edge of the block
     * @author Michelle Tai
     */
    public double getBotBound() {
        return botBound;
    }

    /**
     * Gets number of times the block has been hit by the ball
     * @return number of times the block has been hit by the ball
     * @author Michelle Tai
     */
    public int getHitCount() {
        return hitCount;
    }
    /**
     * Increases the hit count by 1
     * @author Michelle Tai
     */
    public void incHitCount(){
        hitCount++;
        System.out.println(leftBound + " " +hitCount);

    }

}