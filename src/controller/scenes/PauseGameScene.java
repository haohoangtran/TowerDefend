package controller.scenes;

import controller.scenes.icon.IconGame;
import controller.scenes.icon.PauseButton;
import controller.scenes.icon.Restart;
import controller.scenes.icon.ResumeGame;
import utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static controller.scenes.PlayGameScene.isPause;

/**
 * Created by DUC THANG on 1/1/2017.
 */
public class PauseGameScene extends GameScene implements IconGame {
    private ResumeGame resumeGame;
    private Restart restart;

    public PauseGameScene() {
        restart = new Restart(450, 300);
        resumeGame = new ResumeGame(250, 300);
    }

    @Override
    public boolean checkMouse() {
        return false;
    }

    @Override
    public void update(Graphics g) {
        if(isPause) {
            restart.update(g);
            resumeGame.update(g);
        }
    }

    @Override
    public void run() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(resumeGame.checkMouse() && isPause) {
            isPause = false;
            this.sceneListener.back();
        }

        if(restart.checkMouse()) {
            Utils.reset();
            isPause = false;
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
        if(e.getKeyCode() == KeyEvent.VK_P && isPause) {
            isPause = false;
            this.sceneListener.back();
        }
    }
}
