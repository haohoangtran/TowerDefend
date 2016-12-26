package controller;

import controller.manager.EnemyManager;
import models.CheckPoint;
import models.Model;
import utils.Utils;
import views.Animation;
import views.View;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Khuong Duy on 12/17/2016.
 */
public class TowerController extends Controller {
    private Vector<BulletTower> bulletTowers;
    private static int timeCount = 0;

    public boolean isFire() {
        return isFire;
    }

    public void setFire(boolean fire) {
        isFire = fire;
    }

    private boolean isFire;

    public TowerController(Model model, Animation animation) {
        super(model, animation);
        //bulletTowers = new Vector<>();
    }

    public TowerController(Model model, View view) {
        super(model, view);
        bulletTowers = new Vector<>();
    }

    public int sell() {
        this.model.setAlive(false);
        return (int) (this.model.getMoney() * 0.6);
    }

    @Override
    public void run() {
        EnemyController e = null;
        timeCount++;
        e = EnemyManager.chooseFire(this);
        if (e != null)
        {
            if (timeCount > 60) {
                BulletTower bulletTower = BulletTower.createBullet(this.model.getMidX(), this.model.getY());
                bulletTower.setEnemyController(e);
                bulletTowers.add(bulletTower);
                timeCount = 0;
            }
        }


        /*
            if (e != null) {
                timeCount++;
                if (timeCount > 30) {
                    BulletTower bulletTower = BulletTower.createBullet(this.model.getMidX(), this.model.getY());
                    bulletTower.setEnemyController(e);
                    bulletTowers.add(bulletTower);
                    System.out.println("Tao");
                    timeCount = 0;
                }
            } else{
                Iterator<BulletTower> iterator = bulletTowers.iterator();
                while (iterator.hasNext()) {
                    BulletTower bulletTower = iterator.next();
                    if (!bulletTower.model.isAlive()) {
                        iterator.remove();
                    } else {
                        bulletTower.run();
                    }
                }
            }
            */
        Iterator<BulletTower> iterator = bulletTowers.iterator();
        while (iterator.hasNext()) {
            BulletTower bulletTower = iterator.next();
            int a=bulletTower.getModel().getX();
            int b=bulletTower.getModel().getY();
            if (!bulletTower.model.isAlive() || !bulletTower.getEnemyController().getModel().isAlive()) {
                iterator.remove();
            } else {
                bulletTower.run();
            }

        }
    }


    public static TowerController createTower(int x, int y) {
        return new TowerController(new Model(x, y, 50, 50, 7, 1), new View(
                Utils.loadImage("res/PNG/Towers (grey)/TowersLever2.png")));
    }

    @Override
    public void drawView(Graphics g) {
        super.drawView(g);
        for (int i = 0; i < bulletTowers.size(); i++) {
            bulletTowers.get(i).drawView(g);
        }
    }

    @Override
    public Model getModel() {
        return this.model;
    }
}
