package controller.scenes;

import controller.HouseController;
import controller.scenes.GameScene;
import controller.scenes.icon.Next;
import controller.scenes.icon.Restart;
import utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * Created by Hieu It on 12/31/2016.
 */
public class GameVictoryScene extends GameScene {
    private Image backgroud;
    private Restart restart;
    public GameVictoryScene() {
        backgroud = Utils.loadImage("res/gameVictory.png");
        restart = new Restart(350, 500);
    }




    @Override
    public void update(Graphics g) {
        g.drawImage(backgroud,100,50,700,450,null);
        restart.update(g);

        if (HouseController.instance.getHp()>75){
            g.drawImage(Utils.loadImage("res/victory3.png"),200,300,489,207,null);
        }else if (HouseController.instance.getHp()>=50){
            g.drawImage(Utils.loadImage("res/victory2.png"),200,300,326,207,null);
        }else {
            g.drawImage(Utils.loadImage("res/victory1.png"),200,300,163,207,null);

        }
    }

    @Override
    public void run() {
        restart.checkMouse();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(restart.checkMouse()) {
            Utils.reset();
            this.sceneListener.back();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}