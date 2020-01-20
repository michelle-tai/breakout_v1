package breakout;

import javafx.scene.shape.Rectangle;

/**
 * Also unneeded
 */

public class RectangularSprite implements Sprite{
    Rectangle rect;
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


    Rectangle getRect(){
        return rect;
    }
}
