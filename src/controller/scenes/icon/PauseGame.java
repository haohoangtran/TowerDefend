package controller.scenes.icon;

import controller.scenes.GameScene;
import utils.Utils;

import java.awt.*;
import java.awt.event.MouseEvent;

import static utils.Utils.loadImage;

/**
 * Created by DUC THANG on 12/31/2016.
 */
public class PauseGame extends GameScene implements IconGame{
    private static final int WIDTH = 40;
    private static final int HEIGHT = 35;
    private Image pause;

    private int x;
    private int y;

    public PauseGame(int x, int y) {
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
            pause = loadImage("res/icon/pause2.png");
        } else {
            pause = loadImage("res/icon/pause1.png");
        }

        g.drawImage(pause, x, y, WIDTH, HEIGHT, null);
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
}
