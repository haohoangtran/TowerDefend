package controller.scenes.icon;

import controller.scenes.GameScene;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by DUC THANG on 12/31/2016.
 */
public class PauseGame extends GameScene implements IconGame{
    private Image pause;
    private int x;
    private int y;

    public PauseGame(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean checkMouse() {
        return false;
    }

    @Override
    public void update(Graphics g) {

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
