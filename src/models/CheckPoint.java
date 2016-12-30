package models;

/**
 * Created by Khuong Duy on 12/17/2016.
 */
public class CheckPoint {
    private int x;
    private int y;

    public CheckPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
