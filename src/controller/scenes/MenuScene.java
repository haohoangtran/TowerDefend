package controller.scenes;

import controller.CellController;
import controller.HouseController;
import controller.enemies.EnemyController;
import controller.enemies.EnemyType;
import controller.manager.CellManager;
import controller.scenes.icon.ResumeGame;
import controller.scenes.icon.Start;
import controller.towers.TowerController;
import controller.towers.TowerManager;
import controller.towers.TowerType;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.Vector;

import static utils.Utils.getLocation;
import static utils.Utils.loadImage;

/**
 * Created by DUC THANG on 12/28/2016.
 */
public class MenuScene extends GameScene {
    private Image backgroud;
    private Start start;
    private ResumeGame resumeGame;


    private static final int RESUME_X = 330;
    private static final int RESUME_Y = 400;
    private static final int START_X = 330;
    private static final int START_Y = 300;

    public MenuScene() {
        backgroud = loadImage("res/icon/backgroundMenu.bmp");
        start = new Start(START_X, START_Y);

        resumeGame = new ResumeGame(RESUME_X, RESUME_Y);

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
        g.drawImage(backgroud, 0, 0, 930, 690, null);
        start.update(g);
        if (!isEmpty()) {
            resumeGame.update(g);
        }

    }

    @Override
    public void run() {
        checkMouse();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (start.checkMouse()) {
            this.sceneListener.replaceScene(new PlayGameScene(), true);
        }

        if (resumeGame.checkMouse()) {
            resum();
        }
    }
    public void resum(){
        int posT=0, posE=0, posH=0, posS=0, posP=0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("res/Save/all.txt"));
            PlayGameScene playGameScene = new PlayGameScene();
            Vector<String> strings = new Vector<>();
            while (bufferedReader.ready()) {
                String temp = bufferedReader.readLine();
                strings.add(temp);
            }

            for (int i = 0; i < strings.size(); i++) {
                if (strings.get(i).equals( "tower"))
                    posT = i;
                if (strings.get(i).equals("enemy")){
                    posE=i;
                }
                if (strings.get(i).equals("house")){
                    posH=i;
                }
                if (strings.get(i).equals("sound")){
                    posS=i;
                }if (strings.get(i).equals("pause")){
                    posP=i;
                }
            }
            for (int i=1;i<posE;i++){
                String[] s=strings.get(i).split(" ");
                TowerController towerController=null;
                int x=Integer.valueOf(s[0]);
                int y=Integer.valueOf(s[1]);
                switch (Integer.valueOf(s[2])){
                    case 1:
                        towerController=TowerController.createTower(x,y, TowerType.NORMAL);
                        break;
                    case 2:

                        towerController=TowerController.createTower(x,y, TowerType.DAME);
                        break;
                    case 3:
                        towerController=TowerController.createTower(x,y,TowerType.FIRE);
                        break;
                }
                /*
                                case NORMAL:
                                    k = 1;
                                    break;
                                case BOT:
                                    k = 2;
                                    break;
                                case FLY:
                                    k = 3;
                                    break;
                                case SPEED:
                                    k = 4;
                                    break;
                                case TANK:
                                    k = 5;
                                    break;
                                case HORSE:
                                    k = 6;
                                    break;
                 */

//                    TowerManager.instance.add(towerController);
//                    CellController c=CellManager.findCell(towerController.getModel().getX(),towerController.getModel().getY());
//                    c.setTowerController(towerController);
                playGameScene.controllers.add(towerController);

            }
            for (int i = posE+1; i <posH ; i++) {
                String[] s=strings.get(i).split(" ");
                EnemyController enemyController=null;
                int x=Integer.valueOf(s[0]);
                int y=Integer.valueOf(s[1]);
                int hp=Integer.valueOf(s[2]);
                int type=Integer.valueOf(s[3]);
                switch (type){
                    case 1:
                        enemyController=EnemyController.createEnemy(x,y,hp,EnemyType.NORMAL);
                        break;
                    case 2:
                        enemyController=EnemyController.createEnemy(x,y,hp,EnemyType.BOT);
                        break;
                    case 3:
                        enemyController=EnemyController.createEnemy(x,y,hp,EnemyType.FLY);
                        break;
                    case 4:
                        enemyController=EnemyController.createEnemy(x,y,hp,EnemyType.SPEED);
                        break;
                    case 5:
                        enemyController=EnemyController.createEnemy(x,y,hp,EnemyType.TANK);
                        break;
                    case 6:
                        enemyController=EnemyController.createEnemy(x,y,hp,EnemyType.HORSE);
                        break;
                }
                playGameScene.controllers.add(enemyController);
            }
            int hp=Integer.valueOf(strings.get(posH+1));
            HouseController houseController=HouseController.createHpFull(830,325,hp);
            if (strings.get(posS+1).equals("1"))
                PlayGameScene.clip.start();
            else PlayGameScene.clip.stop();

            playGameScene.controllers.add(houseController);
            this.sceneListener.replaceScene(playGameScene,false);

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

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    public boolean checkMouse() {
        return start.checkMouse();
    }
}
