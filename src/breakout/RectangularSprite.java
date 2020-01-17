package breakout;

import javafx.scene.shape.Rectangle;

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
}
