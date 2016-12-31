package controller.scenes;

import utils.Utils;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Hieu It on 12/31/2016.
 */
public class LoadGameScene extends GameScene {
    Image background;
    private int count;
    private int countMax = 100;
    boolean check;
    int timeCount = 0;

    public LoadGameScene() {
        background = Utils.loadImage("res/icon/backgroundMenu.bmp");
    }

    @Override
    public void update(Graphics g) {
        timeCount++;
        g.drawImage(background, 0, 0, 930, 690, null);
        int leng = 4;
        g.setColor(Color.green);
        if (!check) {
            g.drawString(count + "%", 460, 650);
            g.fillRect(270, 660,  count*leng, 8);
            System.out.println((count / countMax) * leng);
            if (count >=100) {
                this.sceneListener.replaceScene(new MenuScene(), false);
            }
        }

        if (timeCount % 3 == 0) {
            count++;
            check = false;
        }
//        if (!check) {
//            if (count % 20 == 0) {
//                String temp = count / 4 + " %";
//                g.drawString(temp, 460, 650);
//            }
//
//            g.fillRect(270, 660, (int) ((count / countMax) * leng), 8);
//            if (count > 410) {
//                this.sceneListener.replaceScene(new MenuScene(), false);
//            }
//        }

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
}
