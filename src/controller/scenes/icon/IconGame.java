package controller.scenes.icon;

import utils.Utils;

import java.awt.*;

import static utils.Utils.loadImage;

/**
 * Created by DUC THANG on 12/31/2016.
 */
public class IconGame {
    protected Image image;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected String urlImage1;
    protected String urlImage2;

    public IconGame(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void update(Graphics g) {
        if (checkMouse()) {
            image = loadImage(urlImage2);
        } else {
            image = loadImage(urlImage1);
        }

        g.drawImage(image, x, y, width, height, null);
    }
     public boolean checkMouse() {
         if (Utils.point.getX() >= x && Utils.point.getX() <= x + width && Utils.point.getY() <= y + height && Utils.point.getY() >= y) {
             return true;
         }
         return false;
     }
}
