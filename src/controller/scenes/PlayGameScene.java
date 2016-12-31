package controller.scenes;

import controller.BaseController;
import controller.CellController;
import controller.HouseController;
import controller.enemies.EnemyController;
import controller.enemies.EnemyManager;
import controller.enemies.EnemyType;
import controller.manager.BodyManager;
import controller.manager.CellManager;
import controller.scenes.icon.BackMenu;
import controller.scenes.icon.IconGame;
import controller.towers.TowerController;
import controller.towers.TowerManager;
import controller.towers.TowerType;

import models.Model;
import utils.Utils;
import views.Animation;

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
public class PlayGameScene extends GameScene implements IconGame {
    public static int timeCount = 0;
    Image background;
    Image backgroundBot;
    Image backgroundTop;
    boolean check;
    Image snow;
    Animation flag, windmill;
    CellController cellController;
    TowerController tower;
    Vector<BaseController> controllers;

    private BackMenu backMenu;

    public PlayGameScene() {
        try {
            snow = new ImageIcon(new URL("http://i.imgur.com/2nr0tS3.gif")).getImage();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        controllers = new Vector<>();
        controllers.add(EnemyManager.instance);
        controllers.add(TowerManager.instance);
        flag = new Animation(Utils.realIInFoder("res/flag"));
        windmill = new Animation(Utils.realIInFoder("res/windmill"));
        backgroundBot = Utils.loadImage("res/bottom.png");
        backgroundTop = Utils.loadImage("res/top.png");

        controllers.add(HouseController.instance);
        background = loadImage("res/Map1.png");
        backMenu = new BackMenu(830, 40);
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(background, 0, 100, 930, 690, null);
        g.drawImage(backgroundTop, 0, 33, 930, 70, null);
        backMenu.update(g);
        for (BaseController controller : controllers) {
            controller.draw(g);
        }

        if (check) {
            CellManager.instance.draw(g);
        }

        flag.draw(g, new Model(20, 560, 60, 60), 2);
        windmill.draw(g, new Model(118, 620, 60, 60), 2);
        g.drawImage(snow, 0, 100, 450, 450, null);
        g.drawImage(snow, 450, 100, 450, 450, null);
        g.drawImage(snow, 450, 450, 450, 450, null);
        g.drawImage(snow, 100, 450, 450, 450, null);
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

        BodyManager.instance.checkContact();

        for (int i = 0; i < controllers.size(); i++) {
            controllers.get(i).run();
        }

        if (!HouseController.instance.isAlive()) {
            this.sceneListener.replaceScene(new GameOverScene(), false);
        }

        backMenu.checkMouse();
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
        CellManager.instance.run();
    }

    public void mouseClicked(MouseEvent e) {
        if(backMenu.checkMouse()) {
            Utils.reset();
            this.sceneListener.back();
        }
    }

    @Override
    public boolean checkMouse() {
        return false;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        check = false;
        System.out.println("relase");
    }

}
