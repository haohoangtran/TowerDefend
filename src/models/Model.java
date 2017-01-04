package models;

import javafx.scene.shape.Circle;

import java.awt.*;

/**
 * Created by DUC THANG on 12/17/2016.
 */
public class Model {

    private int x, y;
    private int width, height;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
    public int getBottom(){
        return y+height;
    }
    public int getRight(){
        return x+width;
    }

    public void moveSlow(int dx, int dy){
        this.x +=dx/2;
        this.y +=dy/2;
    }

    public int getMidX(){
        return this.x+this.width/2;
    }



    public boolean intersects(Model other){
        Rectangle rect1 = this.getRect();
        Rectangle rect2 = other.getRect();
        return rect1.intersects(rect2);
    }

    private Rectangle getRect() {
        return new Rectangle(x,y,width,height);
    }

    public Model(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getMidY(){
        return this.y+height/2;
    }

}
