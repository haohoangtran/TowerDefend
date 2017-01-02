package controller.scenes;

import controller.scenes.icon.BackMenu;
import controller.scenes.icon.HighScoreButton;
import utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by DUC THANG on 1/2/2017.
 */
public class HightScoreScene extends GameScene {
    private Image background;
    private BackMenu backMenu;

    private static final int BACK_X = 430;
    private static final int BACK_Y = 610;

    public HightScoreScene() {
        background = Utils.loadImage("res/icon/backgroundHighScore.png");
        backMenu = new BackMenu(BACK_X, BACK_Y);
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(background, BACKGROUND_X, BACKGROUND_Y, WIDTH, HEIGHT, null);
        backMenu.update(g);
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
        if(backMenu.checkMouse()) {
            this.sceneListener.back();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }
}
