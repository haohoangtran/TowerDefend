package controller.scenes;

import controller.BaseController;
import controller.CellController;
import controller.HouseController;
import controller.enemies.*;
import controller.gifts.TotalCoin;
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
import java.util.*;

import static utils.Utils.clip;
import static utils.Utils.loadImage;
import static utils.Utils.point;

/**
 * Created by DUC THANG on 12/28/2016.
 */
public class PlayGameScene extends GameScene {
    public static int timeCount = 0;
    public static int second = 0;
    public static int level = 0;
    private Vector<String> stringStage = Utils.createFactory("res/Stage/allstage.txt");
    int towerCreate = 1;
    int posString = 0;

    private Image background;
    private Image backgroundTop;

    public static boolean isPause = false;

    private boolean check;
    private Image snow;
    private Animation flag, windmill;

    CellController cellController;
    CellController find;

    public static Vector<BaseController> controllers;
    java.util.List<String> spawnEnemy = SpawnEnemy.instance.getListString(SpawnEnemy.instance.allFile.get(level));

    private BackMenu backMenu;
    private PauseGame pauseGame;
    private boolean checkStage = false;
    private boolean checkCell = false;

    public PlayGameScene() {
        if (!clip.isOpen() || !clip.isRunning())
            clip.start();
        try {
            snow = new ImageIcon(new URL("http://i.imgur.com/2nr0tS3.gif")).getImage();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        controllers = new Vector<>();
        controllers.add(EnemyManager.instance);
        controllers.add(TowerManager.instance);
        controllers.add(TotalCoin.instance);
        controllers.add(BodyManager.instance);
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
        backMenu.update(g);
        pauseGame.update(g);
        for (BaseController controller : controllers) {
            controller.draw(g);
        }

        if (check) {
            CellManager.instance.draw(g);
        }

        if (checkCell && find.getTowerController() != null) {
            g.setColor(Color.red);
            int r = (int) (find.getTowerController().getRadiusFire() * 1.5);
            int x = find.getModel().getMidX() - r / 2 - 3;
            int y = find.getModel().getMidY() - r / 2 - 3;

            Image image = loadImage("res/Bullet/Fire/image 7415.png");
            g.drawImage(image, x, y, r, r, null);
        }

        flag.draw(g, new Model(20, 560, 60, 60), 2);
        windmill.draw(g, new Model(118, 620, 60, 60), 2);
        g.drawImage(snow, 0, 100, 450, 450, null);
        g.drawImage(snow, 450, 100, 450, 450, null);
        g.drawImage(snow, 450, 450, 450, 450, null);
        g.drawImage(snow, 100, 450, 450, 450, null);
        if (checkStage) {
            g.setFont(new Font("Time new Romans", Font.BOLD, 36));
            g.setColor(Color.red);
            g.drawString(stringStage.get(posString ), 300, 500);
        }
    }

    @Override
    public void run() {
        if (!isPause) {

            //bat dau sua
            if (timeCount % 60 == 0) {
                String str = stringStage.get(posString);// lấy string từ file
                System.out.println("str = " + str);
                if (str.isEmpty()) {
                    // nếu nó trống thì cộng lên
                    posString++;
                    return;
                } else if (str.charAt(0) == 'S') {
                    //các cửa bắt đầu bằng Stage
                    checkStage = true;
                    if (EnemyManager.instance.isEmpty()) {
                        // nếu hết enemy mới đọc tiếp(Đang dở hơi)
                        posString++;
                    }
                } else {
                    checkStage = false;
                    System.out.println();
                    EnemyController e = EnemyController.createEnemy(Integer.valueOf(str));
                    if (e != null) {
                        EnemyManager.instance.register(e);
                    }
                    posString++;
                }

                if (posString == stringStage.size() - 2) {
                    this.sceneListener.replaceScene(new GameVictoryScene(), false);
                }
            }
            timeCount++;
        }
        for (int i = 0; i < controllers.size(); i++) {
            controllers.get(i).run();
        }
        if (!HouseController.instance.isAlive()) {
            this.sceneListener.replaceScene(new GameOverScene(), false);
        }

        find = CellManager.findCell((int) point.getX(), (int) point.getY());
        if (find != null) {
            if (find.getTowerController() != null) {
                checkCell = true;
            }
        } else
            checkCell = false;

    }
    /*
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

            for (int i = 0; i < controllers.size(); i++) {
                controllers.get(i).run();
            }
            if (!HouseController.instance.isAlive()) {
                this.sceneListener.replaceScene(new GameOverScene(), false);
            }

            find = CellManager.findCell((int) point.getX(), (int) point.getY());
            if (find != null) {
                if (find.getTowerController() != null) {
                    checkCell = true;
                }
            } else
                checkCell = false;
     */


    @Override
    public void mousePressed(MouseEvent e) {
        check = true;
        cellController = CellManager.instance.findCell(e.getX(), e.getY());
        if (cellController != null && cellController.getModel().isCanBuild() && cellController.getTowerController() == null) {
            TowerController tower = null;
            if (towerCreate == 1) {

                System.out.println("tttttttt");
                tower = TowerController.createTower(cellController.getModel().getX(), cellController.getModel().getY(), TowerType.NORMAL);

            } else if (towerCreate == 2) {
                tower = TowerController.createTower(cellController.getModel().getX(), cellController.getModel().getY(), TowerType.FIRE);

            } else if (towerCreate == 3) {
                tower = TowerController.createTower(cellController.getModel().getX(), cellController.getModel().getY(), TowerType.SLOW);

            }

            if (tower != null && TotalCoin.instance.existCoin(tower)) {
                if (TotalCoin.instance.existCoin(tower)) {
                    TotalCoin.instance.setCoin(TotalCoin.instance.getCoin() - tower.getCoin());
                    cellController.setTowerController(tower);
                    controllers.add(tower);
                }

            } else {
                tower.setAlive(false);


            }
        }
        if (backMenu.checkMouse()) {
            Utils.reset();
            this.sceneListener.replaceScene(new MenuScene(), false);
        }

        if (pauseGame.checkMouse() && !isPause) {
            isPause = true;
            this.sceneListener.replaceScene(new PauseGameScene(), true);
        }
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
        if (e.getKeyCode() == KeyEvent.VK_3) {
            towerCreate = 3;
        }

    }

    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        check = false;
    }
}
