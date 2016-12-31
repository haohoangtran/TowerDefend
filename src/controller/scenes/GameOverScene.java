package controller.scenes;

import controller.scenes.icon.BackMenu;
import controller.scenes.icon.IconGame;
import controller.scenes.icon.Next;
import utils.Utils;

import java.awt.*;
import java.awt.event.MouseEvent;

import static utils.Utils.loadImage;

/**
 * Created by DUC THANG on 12/30/2016.
 */

public class GameOverScene extends GameScene implements IconGame {
    private BackMenu backMenu;


    public GameOverScene() {
        backMenu = new BackMenu(500, 450);

    }

    @Override
    public void update(Graphics g) {
        g.drawImage(loadImage("res/icon/gameOver.png"), 160, 100, 575, 310, null);
        backMenu.update(g);

    }

    @Override
    public void run() {
        backMenu.checkMouse();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (backMenu.checkMouse()) {
            Utils.reset();
            this.sceneListener.back();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    public boolean checkMouse() {
       return false;
    }
}
