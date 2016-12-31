package controller;

import models.Circle;
import models.Model;
import utils.Utils;
import views.View;

import java.awt.*;

/**
 * Created by tranh on 12/21/2016.
 */
public class Store {
    public static int width = 60;
    public static int height = 60;
    public static int sizeTower = 5;
    public Circle[] shopTower = new Circle[sizeTower];
    public static int r = 50;
    public String[] path = {"res/PNG/Towers (grey)/tower_00.png", "res/", "res/PNG/Towers (grey)/TowersLever2.png"};

    public Store() {

        define();
    }

    public void define() {

        shopTower[0] = new Circle(183, 829, r);
        shopTower[0].setPath("res/PNG/Towers (grey)/tower_00.png");
        shopTower[0].setDescription("tao la 0");
        shopTower[1] = new Circle(320, 829, r);
        shopTower[1].setPath("res/PNG/Towers (grey)/TowersLever2.png");
        shopTower[1].setDescription("tao la 1");
        shopTower[2] = new Circle(461, 829, r);
        shopTower[2].setDescription("tao la 2");
        shopTower[3] = new Circle(602, 829, r);
        shopTower[3].setDescription("tao la 3");
        shopTower[4] = new Circle(747, 829, r);
        shopTower[4].setDescription("tao la 4");
    }

//    public TowerController buildTower(int x, int y) {
//
//    }

    public void draw(Graphics g) {
        for (int i = 0; i < shopTower.length; i++) {
            g.setColor(Color.CYAN);
            //g.drawRect((int)shopTower[i].getX(),(int)shopTower[i].getY(),(int)shopTower[i].getWidth(),(int)shopTower[i].getHeight());
            g.drawOval((int) shopTower[i].getX() - r, shopTower[i].getY() - r, r * 2, r * 2);
            g.setColor(Color.red);
            g.drawString("a", shopTower[i].getX(), shopTower[i].getY());
            g.drawImage(Utils.loadImage(shopTower[0].getPath()), shopTower[0].getX() - (int) (shopTower[i].getR() * Math.sqrt(2.0) / 2),
                    shopTower[0].getY() - (int) (shopTower[i].getR() * Math.sqrt(2.0) / 2), (int) (r * Math.sqrt(2.0)), (int) (r * Math.sqrt(2.0)), null);
            g.drawImage(Utils.loadImage(shopTower[1].getPath()), shopTower[1].getX() - (int) (shopTower[i].getR() * Math.sqrt(2.0) / 2),
                    shopTower[0].getY() - (int) (shopTower[i].getR() * Math.sqrt(2.0) / 2), (int) (r * Math.sqrt(2.0)), (int) (r * Math.sqrt(2.0)), null);
        }
    }


    public Circle checkMouse(int x, int y) {
        for (int i = 0; i < shopTower.length; i++) {
            double distance = Math.sqrt(Math.pow(Math.abs(shopTower[i].getX() - x), 2) + Math.pow(Math.abs(shopTower[i].getY() - y), 2));
            if (distance <= r) {
                return shopTower[i];
            }
        }
        return null;
    }


}
