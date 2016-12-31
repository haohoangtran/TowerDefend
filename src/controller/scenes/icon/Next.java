package controller.scenes.icon;

import controller.scenes.GameScene;
import utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static utils.Utils.loadImage;

/**
 * Created by DUC THANG on 12/31/2016.
 */
public class Next extends GameScene implements IconGame {
    private Image next;
    private int x;
    private int y;
    private final int WIDTH = 200;
    private final int HEIGHT = 95;

    public Next(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean checkMouse() {
        if (Utils.point.getX() >= x && Utils.point.getX() <= x + WIDTH && Utils.point.getY() <= y + HEIGHT && Utils.point.getY() >= y) {
            return true;
        }
        return false;
    }

    @Override
    public void update(Graphics g) {
        if (checkMouse()) {
            next = loadImage("res/icon/next2.png");
        } else {
            next = loadImage("res/icon/next1.png");
        }

        g.drawImage(next, x, y, WIDTH, HEIGHT, null);
    }

    @Override
    public void run() {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }
}
