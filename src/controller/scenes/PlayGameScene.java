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

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Vector;

import static utils.Utils.loadImage;

/**
 * Created by DUC THANG on 12/28/2016.
 */
public class PlayGameScene extends GameScene{
    public static int timeCount = 0;
    Image background;
    Image backgroundBot;
    Image backgroundTop;
    CellManager cellManager;
    boolean check;
    CellController cellController;
    TowerController tower;
    Vector<BaseController> controllers;

    public PlayGameScene() {
        controllers = new Vector<>();
        controllers.add(CellManager.instance);

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
            cellManager.draw(g);
            cellManager.drawPos(g);

        }
    }

    @Override
    public void run() {
        timeCount++;// kịch bản nhé :)
        if (timeCount == 60) {
            EnemyManager.instance.add(EnemyController.createEnemy(EnemyType.FLY));
        }
        if(timeCount==80){
            EnemyManager.instance.add(EnemyController.createEnemy(EnemyType.NORMAL));
        }
        if(timeCount==100){
            EnemyManager.instance.add(EnemyController.createEnemy(EnemyType.HORSE));
        }
        if(timeCount==130){
            EnemyManager.instance.add(EnemyController.createEnemy(EnemyType.TANK));
            timeCount=0;
        }


        for (BaseController controller : controllers) {
            controller.run();
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
