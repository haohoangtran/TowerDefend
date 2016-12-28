import controller.*;
import controller.enemies.EnemyController;
import controller.enemies.EnemyType;
import controller.manager.BodyManager;
import controller.manager.CellManager;
import controller.enemies.EnemyManager;
import controller.towers.TowerController;
import controller.towers.TowerManager;
import models.Circle;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import static utils.Utils.loadImage;

/**
 * Created by DUC THANG on 12/17/2016.
 */
public class GameWindow extends Frame implements Runnable {
    public static int timeCount = 0;
    Image background;
    BufferedImage backBuffer;
    EnemyManager enemyManager;
    HouseController houseController;
    TowerManager towerManager;
    Image backgroundBot;
    Image backgroundTop;
    CellManager cellManager;
    boolean check;
    boolean checkMouse;
    Store store;
    Circle circle;
    CellController cellController;
    TowerController t;
    boolean checkDescription=false;


    public GameWindow() {
        cellManager = new CellManager();
        enemyManager = new EnemyManager();
        towerManager = new TowerManager();
        backgroundBot = Utils.loadImage("res/bottom.png");
        backgroundTop = Utils.loadImage("res/top.png");
        ImageIcon img = new ImageIcon("res/iconGame.png"); //cài icon
        setIconImage(img.getImage());
        setVisible(true);
        setResizable(false);
        setTitle("Mùa đông năm ấy - Amita Team");
        setSize(930, 900);
        // cỡ ảnh 930x690
        store = new Store();
        houseController = HouseController.createHpFull(830, 325);

        backBuffer = new BufferedImage(930, 900, BufferedImage.TYPE_3BYTE_BGR);
        background = loadImage("res/Map1.png");

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
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
        System.out.println(background.getHeight(null));
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("click");
                System.out.println(e.getX() + " " + e.getY());

            }

            @Override
            public void mousePressed(MouseEvent e) {
                check = true;
                System.out.println("press");
                cellController = cellManager.findCell(e.getX(), e.getY());
                if (cellController != null && cellController.getModel().isCanBuild()) {
                    t = TowerController.createTower(cellController.getModel().getX(), cellController.getModel().getY());
                    t.setRadiusFire(100);
                    cellController.setTowerController(t);
                    towerManager.add(t);
                }


            }

            @Override
            public void mouseReleased(MouseEvent e) {
                check = false;
                System.out.println("relase");
                cellController = cellManager.findCell(e.getX(), e.getY());
                if (cellController != null && cellController.getModel().isCanBuild() && circle != null) {
                    t = TowerController.createTower(cellController.getModel().getX(), cellController.getModel().getY());
                    t.setRadiusFire(100);
                    cellController.setTowerController(t);
                    towerManager.add(t);
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    public void update(Graphics g) {

        Graphics backBufferGraphics = backBuffer.getGraphics();

        backBufferGraphics.drawImage(background, 0, 100, 930, 690, null);
        backBufferGraphics.drawImage(backgroundTop, 0, 33, 930, 70, null);
        backBufferGraphics.drawImage(backgroundBot, 0, 690, 930, 210, null);
        enemyManager.drawAnimation(backBufferGraphics);

        houseController.drawView(backBufferGraphics);
        houseController.drawAnimation(backBufferGraphics);
        towerManager.draw(backBufferGraphics);
        if (check) {
            cellManager.draw(backBufferGraphics);
            cellManager.drawPos(backBufferGraphics);

        }if (circle!=null){
            if (!checkDescription){
                backBufferGraphics.setColor(Color.red);
                backBufferGraphics.drawString(circle.getDescription(),100,600);
                checkDescription=true;
            }
        }
        store.draw(backBufferGraphics);
        g.drawImage(backBuffer, 0, 0, 930, 900, null);
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.repaint();
                Thread.sleep(17);
                timeCount++;
                if (timeCount == 60) {
                    enemyManager.add(EnemyController.createEnemy(EnemyType.FLY));
                }
                if (timeCount == 80) {
                    enemyManager.add(EnemyController.createEnemy(EnemyType.NORMAL));
                }
                if (timeCount == 100) {
                    enemyManager.add(EnemyController.createEnemy(EnemyType.HORSE));
                }
                if (timeCount == 130) {
                    enemyManager.add(EnemyController.createEnemy(EnemyType.TANK));
                    timeCount = 0;
                }


                enemyManager.run();
                towerManager.run();
                BodyManager.instance.checkContact();

                if (!houseController.isGameOn()) {
                    break;
                }
                Point p = getLocationOnScreen();
                circle = store.checkMouse(MouseInfo.getPointerInfo().getLocation().x - (int) p.getX(),
                        MouseInfo.getPointerInfo().getLocation().y - (int) p.getY());
                if (circle != null) {
                    System.out.println(circle.getDescription());
                    checkDescription=false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
