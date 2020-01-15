package breakout;

public class Sprite {

    int x;
    int y;
    int spriteWidth;
    int spriteHeight;

    protected void setX(int x) {
        this.x = x;
    }

    int getX() {
        return x;
    }

    protected void setY(int y) {
        this.y = y;
    }

    int getY() {
        return y;
    }

    int getSpriteWidth() {
        return spriteWidth;
    }

    int getSpriteHeight() {
        return spriteHeight;
    }


}
