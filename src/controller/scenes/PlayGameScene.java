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

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Key;
import java.util.Vector;

import static utils.Utils.clip;
import static utils.Utils.loadImage;

/**
 * Created by DUC THANG on 12/28/2016.
 */
public class PlayGameScene extends GameScene {
    private Image image1;
    private Image image2;

    public static int timeCount = 0;
    public static int second = 0;
    private static int level = 0;
    private int towerCreate = 1;
    private Image background;
    private Image backgroundTop;
    public static boolean isPause = false;
    private boolean check;
    private Image snow;
    private Animation flag, windmill;
    CellController cellController;
<<<<<<< HEAD
=======
    private TowerController tower;
>>>>>>> e8b4e9638d6f73814551c2e50ddd4f5c9422f6de
    public static Vector<BaseController> controllers;
    private java.util.List<String> spawnEnemy = SpawnEnemy.instance.getListString(SpawnEnemy.instance.allFile.get(level));

    private BackMenu backMenu;
    private PauseGame pauseGame;

    public PlayGameScene() {
        if(!clip.isRunning())
            clip.start();

        try {
            snow = new ImageIcon(new URL("http://i.imgur.com/2nr0tS3.gif")).getImage();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        image1 = Utils.loadImage("res/PNG/Towers (grey)/TowersLever2.png");
        image2 = Utils.loadImage("res/image590.png");

        controllers = new Vector<>();
        controllers.add(EnemyManager.instance);
        controllers.add(TowerManager.instance);

        flag = new Animation(Utils.realIInFoder("res/flag"));
        windmill = new Animation(Utils.realIInFoder("res/windmill"));

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
        g.drawImage(image1, 20, 650, 50, 50, null);
        g.drawImage(image2, 80, 650, 50, 50, null);

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

        if (!isPause) {
            timeCount++;
            if (timeCount > 60) {
                second = timeCount / 60;
            }
            if (second >= spawnEnemy.size() && EnemyManager.instance.isEmpty()) {
                this.sceneListener.replaceScene(new GameVictoryScene(), false);
            }
            if (timeCount % 60 == 0 && second < spawnEnemy.size() - 1) {
                System.out.println(second);
                String[] listNumber = spawnEnemy.get(second).split(",");
                for (String s : listNumber) {

                    try {
                        switch (Integer.parseInt(s)) {
                            case 1:
                                EnemyManager.instance.register(EnemyController.createEnemy(EnemyType.NORMAL));
                                break;
                            case 2:
                                EnemyManager.instance.register(EnemyController.createEnemy(EnemyType.TANK));
                                break;
                            case 3:
                                EnemyManager.instance.register(EnemyController.createEnemy(EnemyType.SPEED));
                                break;
                            case 4:
                                EnemyManager.instance.register(EnemyController.createEnemy(EnemyType.FLY));
                                break;
                            case 5:
                                EnemyManager.instance.register(EnemyController.createEnemy(EnemyType.HORSE));
                                break;
                            case 6:
                                EnemyManager.instance.register(EnemyController.createEnemy(EnemyType.BOT));
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
        cellController = CellManager.instance.findCell(e.getX(), e.getY());
        if (cellController != null && cellController.getModel().isCanBuild() && cellController.getTowerController() == null) {
<<<<<<< HEAD
            TowerController tower=null;
            if (towerCreate == 1) {

                System.out.println("tttttttt");
                tower = TowerController.createTower(cellController.getModel().getX(), cellController.getModel().getY(), TowerType.NORMAL);

            } else if (towerCreate == 2) {
                tower = TowerController.createTower(cellController.getModel().getX(), cellController.getModel().getY(), TowerType.FIRE);

            } else if (towerCreate == 3) {
                tower = TowerController.createTower(cellController.getModel().getX(), cellController.getModel().getY(), TowerType.SLOW);

            }

            if (tower != null&&TotalCoin.instance.existCoin(tower)) {
                if (TotalCoin.instance.existCoin(tower)) {
                    TotalCoin.instance.setCoin(TotalCoin.instance.getCoin() - tower.getCoin());
                    cellController.setTowerController(tower);
                    controllers.add(tower);
                }

            }else {
                tower.setAlive(false);


=======
            if (towerCreate == 1)
                tower = TowerController.createTower(cellController.getModel().getX(), cellController.getModel().getY(), TowerType.NORMAL);
            else if (towerCreate == 2) {
                tower = TowerController.createTower(cellController.getModel().getX(), cellController.getModel().getY(), TowerType.FIRE);
            }
            if (tower != null) {
                cellController.setTowerController(tower);
                controllers.add(tower);
>>>>>>> e8b4e9638d6f73814551c2e50ddd4f5c9422f6de
            }
        }
        //CellManager.instance.run();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_P && !isPause) {
            isPause = true;
            this.sceneListener.replaceScene(new PauseGameScene(), true);
        }

        if (e.getKeyCode() == KeyEvent.VK_2) {
            towerCreate = 2;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            if (clip.isRunning()) {
                clip.stop();
            } else
                clip.start();
        }
        if (e.getKeyCode() == KeyEvent.VK_1) {
            towerCreate = 1;
        }

    }

    public void mouseClicked(MouseEvent e) {
        if (backMenu.checkMouse()) {
            Utils.restartSound();
            Utils.reset();
            this.sceneListener.back();
        }

        if (pauseGame.checkMouse() && !isPause) {
            isPause = true;
            this.sceneListener.replaceScene(new PauseGameScene(), true);

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        check = false;
    }
}
