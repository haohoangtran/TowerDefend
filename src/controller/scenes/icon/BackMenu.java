package controller.scenes.icon;

import controller.scenes.GameScene;
import utils.Utils;

import java.awt.*;
import java.awt.event.MouseEvent;

import static utils.Utils.loadImage;

/**
 * Created by DUC THANG on 12/31/2016.
 */
public class BackMenu extends GameScene implements IconGame {
    Image backMenu;
    private static final int WIDTH = 60;
    private static final int HEIGHT = 60;
    private int x;
    private int y;

    public BackMenu(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void update(Graphics g) {
        if (checkMouse()) {
            backMenu = loadImage("res/icon/backMenu2.png");
        } else {
            backMenu = loadImage("res/icon/backMenu1.png");
        }

        g.drawImage(backMenu, x, y, WIDTH, HEIGHT, null);
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
    public boolean checkMouse() {
        if (Utils.point.getX() >= x && Utils.point.getX() <= x + WIDTH && Utils.point.getY() <= y + HEIGHT && Utils.point.getY() >= y) {
            return true;
        }
        return false;
    }
}
