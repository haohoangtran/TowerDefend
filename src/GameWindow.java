import controller.Controller;
import controller.HouseController;
import controller.enemies.EnemyController;
import controller.enemies.EnemyManager;
import controller.manager.CellManager;
import controller.scenes.*;
import controller.towers.TowerController;
import controller.towers.TowerManager;
import controller.towers.TowerType;
import models.Model;
import utils.Utils;
import views.Animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Stack;

/**
 * Created by DUC THANG on 12/17/2016.
 */
public class GameWindow extends Frame implements Runnable, SceneListener {
    GameScene currenScene;
    BufferedImage backBuffer;
    Stack<GameScene> gameSceneStack;
    BufferedWriter bufferedWriter;
    public static int SPEEDGAME = 25;

    public GameWindow() {

        gameSceneStack = new Stack<>();
        this.replaceScene(new LoadGameScene(), false);
        ImageIcon img = new ImageIcon("res/iconGame.png"); //cài icon
        setIconImage(img.getImage());
        setVisible(true);
        setResizable(false);
        setTitle("Mùa đông năm ấy - Amita Team");
        setSize(900, 700);
        // cỡ ảnh 930x690
        backBuffer = new BufferedImage(900, 700, BufferedImage.TYPE_3BYTE_BGR);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

                try {
                    bufferedWriter = new BufferedWriter(new FileWriter(new File("res/Save/all.txt"), false));

                    // moi cell chua tru tren 1 dong + loai tru
                    int k = -1;
                    bufferedWriter.write("tower");
                    bufferedWriter.newLine();
                    for (TowerController towerController : TowerManager.instance.getTowerControllers()) {
                        switch (towerController.getTowerType()) {
                            case FIRE:
                                k = 3;
                                break;
                            case DAME:
                                k = 2;
                                break;
                            case NORMAL:
                                k = 1;
                                break;
                        }
                        if (k != -1) {
                            bufferedWriter.write(towerController.getModel().getX() +" "+towerController.getModel().getY() + " " + k);
                            System.out.println(CellManager.findPosTower(towerController) + " " + k);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                        }
                    }
                    bufferedWriter.write("enemy");
                    bufferedWriter.newLine();
                    for (Controller controller : EnemyManager.instance.getControllers()) {
                        if (controller instanceof EnemyController) {
                            switch (((EnemyController) controller).enemyType) {
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
                            }
                            bufferedWriter.write(controller.getModel().getX() + " "
                                    + controller.getModel().getY() + " " + ((EnemyController) controller).getHp() + " " + k);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                        }
                    }
                    bufferedWriter.write("house");
                    bufferedWriter.newLine();
                    System.out.println("HouseController.instance.getHp() = " + HouseController.instance.getHp());
                    bufferedWriter.write(HouseController.instance.getHp()+"");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    bufferedWriter.write("sound");
                    bufferedWriter.newLine();
                    if (Utils.clip.isRunning()) {
                        bufferedWriter.write("1");
                        bufferedWriter.newLine();
                    } else {
                        bufferedWriter.write("0");
                        bufferedWriter.newLine();
                    }
                    bufferedWriter.flush();
                    bufferedWriter.write("pause");
                    bufferedWriter.newLine();
                    if (PlayGameScene.isPause) {
                        bufferedWriter.write("1");
                        bufferedWriter.newLine();
                    } else {
                        bufferedWriter.write("0");
                        bufferedWriter.newLine();
                    }
                    bufferedWriter.write(PlayGameScene.posString+"");
                    bufferedWriter.newLine();
                    System.out.println(PlayGameScene.posString);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }


                System.exit(0);
            }


            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currenScene.mouseClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                currenScene.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                currenScene.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                currenScene.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public void replaceScene(GameScene newScene, boolean addToBackStack) {
        if (addToBackStack && currenScene != null) {
            gameSceneStack.push(currenScene);
        }
        currenScene = newScene;
        currenScene.setSceneListener(this);
    }

    public void back() {
        if (!gameSceneStack.isEmpty()) {
            currenScene = gameSceneStack.pop();
        }
    }

    public void update(Graphics g) {
        Graphics backBufferGraphics = backBuffer.getGraphics();
        currenScene.update(backBufferGraphics);
        g.drawImage(backBuffer, 0, 0, 900, 700, null);
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.repaint();
                Thread.sleep(SPEEDGAME);
                currenScene.run();
                Point point = this.getLocation();
                Utils.getLocation(MouseInfo.getPointerInfo().getLocation().x - (int) point.getX(),
                        MouseInfo.getPointerInfo().getLocation().y - (int) point.getY());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
