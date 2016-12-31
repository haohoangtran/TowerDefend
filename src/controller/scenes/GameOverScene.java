package controller.scenes;

import controller.scenes.icon.IconGame;
import controller.scenes.icon.Restart;
import utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import static utils.Utils.loadImage;

/**
 * Created by DUC THANG on 12/30/2016.
 */

public class GameOverScene extends GameScene implements IconGame {
    private Restart restart;
    //private Next next;

    public GameOverScene() {
        restart = new Restart(350, 450);
        //next = new Next(200, 450);
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(loadImage("res/icon/gameOver.png"), 160, 100, 575, 310, null);
        restart.update(g);
        //next.update(g);
    }

    @Override
    public void run() {
        restart.checkMouse();
        //next.checkMouse();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (restart.checkMouse()) {
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

    @Override
    public void keyPressed(KeyEvent e) {

    }

    public boolean checkMouse() {
       return false;
    }
}
