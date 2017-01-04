package controller.scenes;

import controller.HouseController;
import controller.enemies.EnemyController;
import controller.enemies.EnemyType;
import controller.scenes.icon.*;
import controller.towers.TowerController;
import controller.towers.TowerType;
import utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.Vector;

import static utils.Utils.loadImage;

/**
 * Created by DUC THANG on 12/28/2016.
 */
public class MenuScene extends GameScene {
    private Image backgroud;
    private Start start;
    private ResumeGame resumeGame;
    private Facebook facebook;
    private GuideButton guideButton;
    private HighScoreButton highScoreButton;


    private static final int RESUME_X = 345;
    private static final int RESUME_Y = 400;

    private static final int FACEBOOK_X = 800;
    private static final int FACEBOOK_Y = 50;

    private static final int GUIDE_X = 775;
    private static final int GUIDE_Y = 110;

    private static final int HIGHT_X = 344;
    private static final int HIGHT_Y = 470;

    private static final int START_X = 330;
    private static final int START_Y = 300;


    public MenuScene() {
        backgroud = loadImage("res/icon/backgroundMenu.bmp");
        start = new Start(START_X, START_Y);
        resumeGame = new ResumeGame(RESUME_X, RESUME_Y);
        facebook = new Facebook(FACEBOOK_X, FACEBOOK_Y);
        guideButton = new GuideButton(GUIDE_X, GUIDE_Y);
        highScoreButton = new HighScoreButton(HIGHT_X, HIGHT_Y);
    }

    public boolean isEmpty() {
        File file = new File("res/Save/all.txt");
        if (file.length() == 0) {
            return true;
        }

        return false;
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(backgroud, BACKGROUND_X, BACKGROUND_Y, WIDTH, HEIGHT, null);
        start.update(g);
        facebook.update(g);
        guideButton.update(g);
        highScoreButton.update(g);
        if (!isEmpty()) {
            resumeGame.update(g);
        }
    }

    @Override
    public void run() {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    public void resume() {
        int posT = 0, posE = 0, posH = 0, posS = 0, posP = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("res/Save/all.txt"));
            PlayGameScene playGameScene = new PlayGameScene();
            Vector<String> strings = new Vector<>();
            while (bufferedReader.ready()) {
                String temp = bufferedReader.readLine();
                strings.add(temp);
            }

            for (int i = 0; i < strings.size(); i++) {
                if (strings.get(i).equals("tower"))
                    posT = i;
                if (strings.get(i).equals("enemy")) {
                    posE = i;
                }
                if (strings.get(i).equals("house")) {
                    posH = i;
                }
                if (strings.get(i).equals("sound")) {
                    posS = i;
                }
                if (strings.get(i).equals("pause")) {
                    posP = i;
                }
            }
            for (int i = 1; i < posE; i++) {
                String[] s = strings.get(i).split(" ");
                TowerController towerController = null;
                int x = Integer.valueOf(s[0]);
                int y = Integer.valueOf(s[1]);
                switch (Integer.valueOf(s[2])) {
                    case 1:
                        towerController = TowerController.createTower(x, y, TowerType.NORMAL);
                        break;
                    case 2:

                        towerController = TowerController.createTower(x, y, TowerType.DAME);
                        break;
                    case 3:
                        towerController = TowerController.createTower(x, y, TowerType.FIRE);
                        break;
                }
                playGameScene.controllers.add(towerController);

            }
            for (int i = posE + 1; i < posH; i++) {
                String[] s = strings.get(i).split(" ");
                EnemyController enemyController = null;
                int x = Integer.valueOf(s[0]);
                int y = Integer.valueOf(s[1]);
                int hp = Integer.valueOf(s[2]);
                int type = Integer.valueOf(s[3]);
                switch (type) {
                    case 1:
                        enemyController = EnemyController.createEnemy(x, y, hp, EnemyType.NORMAL);
                        break;
                    case 2:
                        enemyController = EnemyController.createEnemy(x, y, hp, EnemyType.BOT);
                        break;
                    case 3:
                        enemyController = EnemyController.createEnemy(x, y, hp, EnemyType.FLY);
                        break;
                    case 4:
                        enemyController = EnemyController.createEnemy(x, y, hp, EnemyType.SPEED);
                        break;
                    case 5:
                        enemyController = EnemyController.createEnemy(x, y, hp, EnemyType.TANK);
                        break;
                    case 6:
                        enemyController = EnemyController.createEnemy(x, y, hp, EnemyType.HORSE);
                        break;
                }
                playGameScene.controllers.add(enemyController);
            }
            int hp = Integer.valueOf(strings.get(posH + 1));
            HouseController houseController = HouseController.createHpFull(830, 325, hp);
            if (strings.get(posS + 1).equals("1"))
                Utils.clip.start();
            else Utils.clip.stop();

            playGameScene.controllers.add(houseController);
            this.sceneListener.replaceScene(playGameScene, true);

            System.out.println(strings);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (start.checkMouse()) {
            Utils.playSound("res/sound/menu.wav",false);
            this.sceneListener.replaceScene(new PlayGameScene(), true);

        }

        if (resumeGame.checkMouse()) {
            resume();
        }

        if(facebook.checkMouse()) {
            Utils.openWebpage("https://www.facebook.com/groups/577054122491100/?fref=ts");
        }

        if(guideButton.checkMouse()) {
            this.sceneListener.replaceScene(new GameInstructionScene(), true);
        }

        if(highScoreButton.checkMouse()) {
            this.sceneListener.replaceScene(new HightScoreScene(), true);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }
}
