package controller.scenes;

import com.sun.scenario.effect.impl.prism.PrImage;
import controller.scenes.icon.Restart;
import utils.Utils;

import javax.sound.sampled.LineUnavailableException;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static utils.Utils.clip;
import static utils.Utils.loadImage;

/**
 * Created by DUC THANG on 12/30/2016.
 */

public class GameOverScene extends GameScene {
    private Restart restart;
    private static final int RESTART_X = 350;
    private static final int RESTART_Y = 450;

    private static final Image gameOver = loadImage("res/icon/gameOver.png");
    private static final int GAMEOVER_X = 160;
    private static final int GAMEOVER_Y = 100;
    private static final int G_WIDTH = 575;
    private static final int G_HEIGHT = 310;
    //private Next next;

    public GameOverScene() {
        Utils.clip.close();
        restart = new Restart(RESTART_X, RESTART_Y);
        //next = new Next(200, 450);
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(gameOver, GAMEOVER_X, GAMEOVER_Y, G_WIDTH, G_HEIGHT, null);
        restart.update(g);
        //next.update(g);
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
        if (restart.checkMouse()) {
            Utils.reset();
            this.sceneListener.back();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }
}
