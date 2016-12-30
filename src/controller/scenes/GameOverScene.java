package controller.scenes;

import controller.HouseController;
import controller.enemies.EnemyManager;
import controller.manager.BodyManager;
import utils.Utils;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Vector;

import static utils.Utils.loadImage;

/**
 * Created by DUC THANG on 12/30/2016.
 */

public class GameOverScene extends GameScene {
    Image backMenu;
    Image replay;
    private static final int WIDTH = 60;
    private static final int HEIGHT = 60;
    private static final int MENU_X = 500;
    private static final int Y = 450;
    private static final int REPLAY_X = 300;


    public GameOverScene() {
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(loadImage("res/icon/gameOver.png"), 160, 100, 575, 310, null);
        if (checkMouse()) {
            backMenu = loadImage("res/icon/backMenu2.png");
            replay = loadImage("res/icon/replay1.png");
            //g.drawImage(replay, REPLAY_X, Y, WIDTH, HEIGHT, null);
        } else {
            backMenu = loadImage("res/icon/backMenu1.png");
            replay = loadImage("res/icon/replay2.png");
            //g.drawImage(replay, REPLAY_X, Y, WIDTH + 5, HEIGHT + 5, null);
        }

        g.drawImage(backMenu, MENU_X, Y, WIDTH, HEIGHT, null);
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
        if (checkMouse()) {
            reset();
            this.sceneListener.back();
        }
    }

    public void reset() {
        HouseController.instance=HouseController.createHpFull(830,325);
        BodyManager.instance.setBodies(new Vector<>());
        BodyManager.instance.register(HouseController.instance);
        EnemyManager.instance.removeAll();
    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    public boolean checkMouse() {
        if (Utils.point.getX() >= MENU_X && Utils.point.getX() <= MENU_X + WIDTH && Utils.point.getY() <= Y + HEIGHT && Utils.point.getY() >= Y) {
            return true;
        }
        return false;

    }
}
