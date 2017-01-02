package models;

/**
 * Created by tranh on 12/19/2016.
 */
public class CellModel {
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean isRoad;
    private String describe;
    private boolean isUtil;

    public boolean isUtil() {
        return isUtil;
    }

    public void setUtil(boolean util) {
        isUtil = util;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }


    public boolean isRoad() {
        return isRoad;
    }

    public boolean isCanBuild() {
        return canBuild;
    }

    public void setCanBuild(boolean canBuild) {
        this.canBuild = canBuild;
    }

    private boolean canBuild;

    public void setRoad(boolean road) {
        isRoad = road;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    public int getBottom(){
        return y+height;
    }

    public CellModel(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return "CellModel{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", isRoad=" + isRoad +
                ", canBuild=" + canBuild +
                '}';
    }

    public int getRight(){
        return x+width;

    }
    public int getMidX(){
        return x+width/2;
    }

    public int getMidY() {
        return y + height / 2;
    }
}
