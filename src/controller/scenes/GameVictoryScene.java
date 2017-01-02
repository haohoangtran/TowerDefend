package controller.scenes;

import controller.HouseController;
import controller.scenes.icon.Restart;
import utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Hieu It on 12/31/2016.
 */
public class GameVictoryScene extends GameScene {
    private Image background;
    private Restart restart;

    private static final int RESTART_X = 350;
    private static final int RESTART_Y = 500;

    private static final int BACKGROUND_X = 100;
    private static final int BACKGROUND_Y = 50;
    private static final int B_WIDTH = 700;
    private static final int B_HEIGHT = 450;

    private static final int START_X = 200;
    private static final int START_Y = 300;
    private static final int S_WIDTH = 163;
    private static final int S_HEIGHT = 207;


    public GameVictoryScene() {
        Utils.clip.close();
        background = Utils.loadImage("res/gameVictory.png");
        restart = new Restart(RESTART_X, RESTART_Y);
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(background, BACKGROUND_X, BACKGROUND_Y, B_WIDTH, B_HEIGHT,null);

        if (HouseController.instance.getHp()>75){
            g.drawImage(Utils.loadImage("res/victory3.png"),START_X,START_Y,3 * S_WIDTH,S_HEIGHT,null);
        }else if (HouseController.instance.getHp()>=50){
            g.drawImage(Utils.loadImage("res/victory2.png"),START_X,START_Y,2 * S_WIDTH,S_HEIGHT,null);
        }else {
            g.drawImage(Utils.loadImage("res/victory1.png"),START_X,START_Y,S_WIDTH,S_HEIGHT,null);
        }

        restart.update(g);
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
        if(restart.checkMouse()) {
            Utils.reset();
            this.sceneListener.back();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}