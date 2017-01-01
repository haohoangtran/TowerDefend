package controller.towers;

import controller.Controller;
import controller.enemies.EnemyController;
import controller.enemies.EnemyManager;
import controller.enemies.EnemyType;
import models.Model;
import utils.Utils;
import views.Animation;
import views.SingleView;
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
    private boolean isAlive;
    private int money;
    private int radiusFire;
    private TowerType towerType;

    public int getRadiusFire() {
        return radiusFire;
    }

    public boolean intersectsCircle(Model other) {
        double distance = Math.sqrt(Math.pow(Math.abs(model.getMidX() - other.getMidX()), 2) + Math.pow(Math.abs(model.getMidY() - other.getMidY()), 2));
        return distance < radiusFire;
    }

    public void setRadiusFire(int radiusFire) {
        this.radiusFire = radiusFire;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isFire() {
        return isFire;
    }

    public void setFire(boolean fire) {
        isFire = fire;
    }

    private boolean isFire;

    public TowerController(Model model, Animation animation) {
        super(model, animation);
        bulletTowers = new Vector<>();
    }

    public TowerController(Model model, SingleView view, TowerType towerType) {
        super(model, view);
        this.towerType = towerType;
        isAlive = true;
        bulletTowers = new Vector<>();
    }

    public int sell() {
        setAlive(false);
        return (int) (getMoney() * 0.6);
    }

    public void setMoney(int money) {
        this.money = money;
    }

    private int getMoney() {
        return money;

    }

    @Override
    public void run() {
        timeCount++;
        EnemyController e = EnemyManager.chooseFire(this);

        if (e != null) {
            if (timeCount > 90) {
                switch (towerType) {
                    case SLOW:
                        if (e.enemyType != EnemyType.BOT) {
                            BulletTower bulletTower = BulletTower.createBullet(this.model.getMidX(), this.model.getY(), BulletType.SLOW);
                            bulletTower.setEnemyController(e);
                            bulletTowers.add(bulletTower);
                            timeCount = 0;
                        }
                        break;
                    case NORMAL:
                        if (e.enemyType != EnemyType.BOT) {
                            BulletTower bulletTower1 = BulletTower.createBullet(this.model.getMidX(), this.model.getY(), BulletType.NORMAL);
                            bulletTower1.setEnemyController(e);
                            bulletTowers.add(bulletTower1);
                            timeCount = 0;
                        }
                        break;
                    case FIRE:
                            BulletTower bulletTower2 = BulletTower.createBullet(this.model.getX() - 25, this.model.getY() - 25, BulletType.FIRE);
                            bulletTower2.setEnemyController(e);
                            bulletTowers.add(bulletTower2);
                            timeCount = 0;

                        break;
                }

            }
            for (int i = 0; i < bulletTowers.size(); i++) {
                bulletTowers.get(i).run();
            }
        }


        Iterator<BulletTower> iterator = bulletTowers.iterator();
        while (iterator.hasNext()) {
            BulletTower bulletTower = iterator.next();
            int a = bulletTower.getModel().getX();
            int b = bulletTower.getModel().getY();

            if (!bulletTower.isAlive() || !bulletTower.getEnemyController().isAlive()) {
                switch (towerType) {
                    case FIRE:
                        if (bulletTower.getView().isAnimationReachEnd()) {
                            iterator.remove();
                        }
                        break;
                    case NORMAL:
                        iterator.remove();
                }

            } else {
                bulletTower.run();

            }
        }
    }


    public static TowerController createTower(int x, int y, TowerType towerType) {
        switch (towerType) {
            case NORMAL:
                TowerController t = new TowerController(new Model(x, y, 50, 50), new SingleView(
                        Utils.loadImage("res/PNG/Towers (grey)/TowersLever2.png")), TowerType.NORMAL);
                t.setRadiusFire(100);
                return t;
            case FIRE:
                t = new TowerController(new Model(x, y, 50, 50), new SingleView(
                        Utils.loadImage("res/image590.png")), TowerType.FIRE);
                t.setRadiusFire(100);
                return t;
        }
        return null;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        for (int i = 0; i < bulletTowers.size(); i++) {
            bulletTowers.get(i).draw(g);

        }
    }

    @Override
    public Model getModel() {
        return this.model;
    }
}
