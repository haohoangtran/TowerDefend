package controller.scenes;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by DUC THANG on 12/28/2016.
 */
public abstract class GameScene {
    protected SceneListener sceneListener;
    public static int SPEEDGAME = 25;

    public void setSceneListener(SceneListener sceneListener) {
        this.sceneListener = sceneListener;
    }

    public abstract void update(Graphics g);
    public  abstract void run();
    public abstract void mouseClicked(MouseEvent e);
    public abstract void mouseReleased(MouseEvent e);
    public abstract void mousePressed(MouseEvent e);
    public abstract void keyPressed(KeyEvent e);
}
