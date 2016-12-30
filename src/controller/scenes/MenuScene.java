package controller.scenes;

import utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static utils.Utils.loadImage;

/**
 * Created by DUC THANG on 12/28/2016.
 */
public class MenuScene extends GameScene{
    private Image backgroud;
    private Image start;

    private static final int WIDTH_S = 200;
    private static final int HEIGHT_S = 95;
    private static final int START_X = 330;
    private static final int START_Y = 300;

    public MenuScene() {
        backgroud = loadImage("res/icon/backgroundMenu.bmp");
    }

    @Override
    public void update(Graphics g) {
        if(checkMouse()) {
            start = loadImage("res/icon/start2.png");
        }
        else {
            start = loadImage("res/icon/start1.png");
        }

        g.drawImage(backgroud, 0, 0, 930, 690, null);
        g.drawImage(start, START_X, START_Y, WIDTH_S, HEIGHT_S, null);
    }

    @Override
    public void run() {
        checkMouse();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
            this.sceneListener.replaceScene(new PlayGameScene(), true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    public boolean checkMouse() {
        if (Utils.point.getX() >= START_X && Utils.point.getX() <= START_X + WIDTH_S && Utils.point.getY() <= START_Y + HEIGHT_S && Utils.point.getY() >= START_Y) {
            return true;
        }
        return false;

    }
}
