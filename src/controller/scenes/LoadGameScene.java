package controller.scenes;

import utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Hieu It on 12/31/2016.
 */
public class LoadGameScene extends GameScene {
    private Image background;

    private int count;
    private boolean check = true;
    int timeCount = 0;

    public LoadGameScene() {
        background = Utils.loadImage("res/icon/backgroundMenu.bmp");
    }

    @Override
    public void update(Graphics g) {
        timeCount++;
        g.drawImage(background, BACKGROUND_X, BACKGROUND_Y, WIDTH, HEIGHT, null);
        int leng = 4;
        g.setColor(Color.green);
        if (!check) {
            g.drawString(count + "%", 460, 650);
            g.fillRect(270, 660, count * leng, 8);
            if (count >= 100) {
                this.sceneListener.replaceScene(new MenuScene(), false);
            }
        }

        count++;
        check = false;
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

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }
}
