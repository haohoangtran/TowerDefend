package controller.scenes;

import controller.scenes.icon.Start;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import static utils.Utils.loadImage;

/**
 * Created by DUC THANG on 12/28/2016.
 */
public class MenuScene extends GameScene{
    private Image backgroud;
    private Start start;


    private static final int START_X = 330;
    private static final int START_Y = 300;

    public MenuScene() {
        backgroud = loadImage("res/icon/backgroundMenu.bmp");
        start = new Start(330, 300);
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(backgroud, 0, 0, 930, 690, null);
        start.update(g);

    }

    @Override
    public void run() {
        checkMouse();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(checkMouse()) {
            this.sceneListener.replaceScene(new PlayGameScene(), true);
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
        return start.checkMouse();
    }
}
