package controller.scenes.icon;

import controller.scenes.GameScene;
import utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static utils.Utils.loadImage;

/**
 * Created by DUC THANG on 1/1/2017.
 */
public class Restart extends IconGame {
    public Restart(int x, int y) {
        super(x, y);
        urlImage1 = "res/icon/restart1.png";
        urlImage2 = "res/icon/restart2.png";
        this.width = 180;
        this.height = 55;
    }
}
