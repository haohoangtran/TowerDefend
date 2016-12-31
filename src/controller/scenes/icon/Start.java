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
public class Start extends GameScene implements IconGame {
    private Image start;
    private int x;
    private int y;

    private static final int WIDTH = 200;
    private static final int HEIGHT = 95;

    public Start(int x, int y) {
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
        if(checkMouse()) {
            start = loadImage("res/icon/start2.png");
        }
        else {
            start = loadImage("res/icon/start1.png");
        }
        g.drawImage(start, x, y, WIDTH, HEIGHT, null);
    }

    @Override
    public void run() {
        checkMouse();
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
