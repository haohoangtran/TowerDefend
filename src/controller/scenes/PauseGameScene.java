package controller.scenes;

import controller.scenes.icon.IconGame;
import controller.scenes.icon.PauseButton;

import java.awt.*;
import java.awt.event.MouseEvent;

import static controller.scenes.PlayGameScene.isPause;

/**
 * Created by DUC THANG on 1/1/2017.
 */
public class PauseGameScene extends GameScene implements IconGame {
    private PauseButton pauseButton;

    public PauseGameScene() {
        pauseButton = new PauseButton(350, 300);
    }

    @Override
    public boolean checkMouse() {
        return false;
    }

    @Override
    public void update(Graphics g) {
        if(isPause)
            pauseButton.update(g);
    }

    @Override
    public void run() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(pauseButton.checkMouse() && isPause) {
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
}
