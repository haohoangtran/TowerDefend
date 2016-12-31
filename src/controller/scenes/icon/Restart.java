package controller.scenes.icon;

import controller.scenes.GameScene;
import utils.Utils;

import java.awt.*;
import java.awt.event.MouseEvent;

import static utils.Utils.loadImage;

/**
 * Created by DUC THANG on 1/1/2017.
 */
public class Restart extends GameScene implements IconGame {
    private Image restart;
    private static final int WIDTH = 180;
    private static final int HEIGHT = 55;
    private int x;
    private int y;

    public Restart(int x, int y) {
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
            restart = loadImage("res/icon/restart2.png");
        } else {
            restart = loadImage("res/icon/restart1.png");
        }

        g.drawImage(restart, x, y, WIDTH, HEIGHT, null);
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
}
