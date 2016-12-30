package controller.scenes;

import controller.BaseController;
import controller.CellController;
import controller.HouseController;
import controller.Store;
import controller.enemies.EnemyController;
import controller.enemies.EnemyManager;
import controller.enemies.EnemyType;
import controller.manager.BodyManager;
import controller.manager.CellManager;
import controller.towers.TowerController;
import controller.towers.TowerManager;
import controller.towers.TowerType;

import models.Circle;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import static utils.Utils.loadImage;

/**
 * Created by DUC THANG on 12/28/2016.
 */
public class PlayGameScene extends GameScene {
    public static int timeCount = 0;
    Image background;
    Image backgroundBot;
    Image backgroundTop;
    boolean check;
    Image snow;
    CellController cellController;
    TowerController tower;
    Vector<BaseController> controllers;

    public PlayGameScene() {
        try {
            snow=new ImageIcon(new URL("http://i.imgur.com/2nr0tS3.gif")).getImage();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        controllers = new Vector<>();
        controllers.add(EnemyManager.instance);
        controllers.add(TowerManager.instance);

        backgroundBot = Utils.loadImage("res/bottom.png");
        backgroundTop = Utils.loadImage("res/top.png");

        controllers.add(HouseController.instance);
        background = loadImage("res/Map1.png");
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(background, 0, 100, 930, 690, null);
        g.drawImage(backgroundTop, 0, 33, 930, 70, null);
        g.drawImage(backgroundBot, 0, 690, 930, 210, null);

        for (BaseController controller : controllers) {
            controller.draw(g);
        }
        if (check) {
            CellManager.instance.draw(g);
            CellManager.instance.drawPos(g);
        }
        g.drawImage(snow,0,0,930,900,null);
    }

    @Override
    public void run() {
        timeCount++;// kịch bản nhé :)
        if (timeCount == 60) {
            EnemyManager.instance.add(EnemyController.createEnemy(EnemyType.FLY));
        }
        if (timeCount == 80) {
            EnemyManager.instance.add(EnemyController.createEnemy(EnemyType.NORMAL));
        }
        if (timeCount == 100) {
            EnemyManager.instance.add(EnemyController.createEnemy(EnemyType.HORSE));
        }
        if (timeCount == 130) {
            EnemyManager.instance.add(EnemyController.createEnemy(EnemyType.TANK));
            timeCount = 0;
        }


        for (int i = 0; i < controllers.size(); i++) {
            controllers.get(i).run();
        }
        BodyManager.instance.checkContact();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        check = true;
        System.out.println("press");
        cellController = CellManager.instance.findCell(e.getX(), e.getY());
        if (cellController != null && cellController.getModel().isCanBuild()) {
            tower = TowerController.createTower(cellController.getModel().getX(), cellController.getModel().getY(), TowerType.NORMAL);
            tower.setRadiusFire(100);
            cellController.setTowerController(tower);
            controllers.add(tower);
        }
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("click");
        System.out.println(e.getX() + " " + e.getY());

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        check = false;
        System.out.println("relase");
    }
}
