package breakout;

interface Sprite{
    void setX(double x);
    double getX();
    void setY(double y);
    double getY();
    void setPos(double x, double y);

}
//
//public class Sprite {
//
//    int x;
//    int y;
//    int spriteWidth;
//    int spriteHeight;
//    int leftBound;
//    int rightBound;
//    int topBound;
//    int botBound;
//
//    protected void setX(int x) {
//        this.x = x;
//    }
//
//    int getX() {
//        return x;
//    }
//
//    protected void setY(int y) {
//        this.y = y;
//    }
//
//    int getY() {
//        return y;
//    }
//
//    void setPos(int x, int y){
//        setX(x);
//        setY(y);
//    }
//
//    int getSpriteWidth() {
//        return spriteWidth;
//    }
//
//    int getSpriteHeight() {
//        return spriteHeight;
//    }
//
//    int getLeftBound(){
//        return leftBound;
//    }
//
//    int getRightBound(){
//        return rightBound;
//    }
//
//    int getTopBound(){
//        return topBound;
//    }
//
//    int getBotBound(){
//        return botBound;
//    }
//
//}
