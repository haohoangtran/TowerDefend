package models;

import javafx.scene.shape.Circle;

import java.awt.*;

/**
 * Created by DUC THANG on 12/17/2016.
 */
public class Model {
    private int x, y;
    private int width, height;
    protected boolean isAlive = true;
    private boolean isFly=false;
    protected int speed;
    private int hp;
    protected int radiusFire;
    public int getHp() {
        return hp;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    protected int atk;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    protected int money;

    public Model(int x, int y, int width, int height, int speed, int atk) {
        this.x = x;
        this.y = y;
        this.isAlive=true;
        this.hp=1;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.atk = atk;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public Model(int x, int y, int width, int height, boolean isFly, int speed, int hp, int atk, int money) {
        this.x = x;
        this.y = y;
        this.width = width;

        this.height = height;
        this.isFly = isFly;
        this.speed = speed;
        this.hp = hp;
        this.atk = atk;
        this.money = money;
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
    public int getMidX(){
        return this.x+this.width/2;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public int getRadiusFire() {
        return radiusFire;
    }

    public void setRadiusFire(int radiusFire) {
        this.radiusFire = radiusFire;
    }

    public Rectangle getRect(){
        return new Rectangle(x,y,width,height);
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean intersects(Model other){
        Rectangle rect1 = this.getRect();
        Rectangle rect2 = other.getRect();
        return rect1.intersects(rect2);
    }
    public boolean intersectsCircle(Model other){
        double distance = Math.sqrt(Math.pow(Math.abs(this.getMidX()-other.getMidX()),2)+Math.pow(Math.abs(this.getMidY()-other.getMidY()),2));
        return distance<radiusFire;
    }
    public int getMidY(){
        return this.y+height/2;
    }

}
