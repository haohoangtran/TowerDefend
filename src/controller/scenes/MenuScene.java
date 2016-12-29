package controller.scenes;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by DUC THANG on 12/28/2016.
 */
public class MenuScene extends GameScene{

    public MenuScene() {

    }

    @Override
    public void update(Graphics g) {

    }

    @Override
    public void run() {

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
}
