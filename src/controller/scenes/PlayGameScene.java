package controller.scenes;

import controller.BaseController;
import controller.CellController;
import controller.HouseController;
import controller.enemies.EnemyController;
import controller.enemies.EnemyManager;
import controller.enemies.EnemyType;
import controller.enemies.SpawnEnemy;
import controller.manager.BodyManager;
import controller.manager.CellManager;
import controller.scenes.icon.*;
import controller.towers.TowerController;
import controller.towers.TowerManager;
import controller.towers.TowerType;

import models.Model;
import utils.Utils;
import views.Animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Key;
import java.util.Vector;

import static utils.Utils.loadImage;

/**
 * Created by DUC THANG on 12/28/2016.
 */
public class PlayGameScene extends GameScene implements IconGame {
    public static int timeCount = 0;
    public static int second = 0;
    public static int level = 0;
    Image background;
    Image backgroundBot;
    Image backgroundTop;
    static boolean isPause = false;
    boolean check;
    Image snow;
    Animation flag, windmill;
    CellController cellController;
    TowerController tower;
    Vector<BaseController> controllers;
    java.util.List<String> spawnEnemy = SpawnEnemy.instance.getListString(SpawnEnemy.instance.allFile.get(level));

    private BackMenu backMenu;
    private PauseGame pauseGame;

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
        pauseGame = new PauseGame(40, 50);
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(background, 0, 100, 930, 690, null);
        g.drawImage(backgroundTop, 0, 33, 930, 70, null);
        backMenu.update(g);
        pauseGame.update(g);
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
        if(!isPause) {
            timeCount++;
            if (timeCount > 60 ) {
                second = timeCount / 60;
            }
            if(second>=spawnEnemy.size() && EnemyManager.instance.isEmpty()){
                this.sceneListener.replaceScene(new GameVictoryScene(),false);
            }
            if (timeCount % 60 == 0 && second<spawnEnemy.size()-1) {
                System.out.println(second);
                String[] listNumber = spawnEnemy.get(second).split(",");
                for (String s : listNumber) {

                    try {
                        switch (Integer.parseInt(s)) {
                            case 1:
                                EnemyManager.instance.add(EnemyController.createEnemy(EnemyType.NORMAL));
                                break;
                            case 2:
                                EnemyManager.instance.add(EnemyController.createEnemy(EnemyType.TANK));
                                break;
                            case 3:
                                EnemyManager.instance.add(EnemyController.createEnemy(EnemyType.SPEED));
                                break;
                            case 4:
                                EnemyManager.instance.add(EnemyController.createEnemy(EnemyType.FLY));
                                break;
                            case 5:
                                EnemyManager.instance.add(EnemyController.createEnemy(EnemyType.HORSE));
                                break;
                        }
                    } catch (Exception e) {
                        System.out.println("bo qua");
                    }
                }
            }

            BodyManager.instance.checkContact();

            for (int i = 0; i < controllers.size(); i++) {
                controllers.get(i).run();
            }

            if (!HouseController.instance.isAlive()) {
                this.sceneListener.replaceScene(new GameOverScene(), false);
            }
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
        check = true;
        System.out.println("press");
        cellController = CellManager.instance.findCell(e.getX(), e.getY());
        if (cellController != null && cellController.getModel().isCanBuild()) {
                tower = TowerController.createTower(cellController.getModel().getX(), cellController.getModel().getY(), TowerType.FIRE);
                tower.setRadiusFire(100);
                cellController.setTowerController(tower);
                controllers.add(tower);

        }
        CellManager.instance.run();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_P && !isPause) {
            isPause = true;
            this.sceneListener.replaceScene(new PauseGameScene(), true);
        }

        if(e.getKeyCode() == KeyEvent.VK_W) {
            this.sceneListener.replaceScene(new GameVictoryScene(), false);
        }

        if(e.getKeyCode() == KeyEvent.VK_UP){
            SPEEDGAME--;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            SPEEDGAME++;
        }
    }

    public void mouseClicked(MouseEvent e) {
        if(backMenu.checkMouse()) {
            Utils.reset();
            this.sceneListener.back();
        }

        if(pauseGame.checkMouse() && !isPause) {
            isPause = true;
            this.sceneListener.replaceScene(new PauseGameScene(), true);
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
