package controller.towers;

import controller.Body;
import controller.Controller;
import controller.enemies.EnemyController;
import controller.manager.BodyManager;
import models.Model;
import utils.Utils;
import views.SingleView;
import views.View;

import java.awt.*;

/**
 * Created by tranh on 12/17/2016.
 */
public class BulletTower extends Controller implements Body {
    private int atk;

    public BulletTower(Model model, SingleView view, int atk) {
        super(model, view);
        this.atk = atk;
    }

    public int getAtk() {

        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public EnemyController getEnemyController() {
        return enemyController;
    }

    public void setEnemyController(EnemyController enemyController) {
        this.enemyController = enemyController;
    }

    private EnemyController enemyController;

    public BulletTower(Model model, SingleView view) {
        super(model, view);
        isAlive=true;
        BodyManager.instance.register(this);
    }


    public static int numberRun = 6;

    @Override
    public void run() {
        if (enemyController != null) {
                int xE = enemyController.getModel().getX() + enemyController.getModel().getWidth() / 2;
                int yE = enemyController.getModel().getY() + enemyController.getModel().getHeight() / 2;
                int x = (xE - this.model.getX());
                int y = (yE - this.model.getY());
                this.model.move(x / numberRun, y / numberRun);
        }
    }

    public static BulletTower createBullet(int x, int y) {
        BulletTower b= new BulletTower(new Model(x, y, 12, 12), new SingleView(Utils.loadImage("res/bullet.png")));
        b.setAtk(10);
        return b;
    }


    @Override
    public void onContact(Body other) {
        if (other instanceof EnemyController) {
            setAlive(false);
            ((EnemyController) other).setHp(((EnemyController) other).getHp()-atk);
            if (((EnemyController) other).getHp() <= 0) {
                ((EnemyController) other).setAlive(false);
            }
        }
    }

    @Override
    public Model getModel() {
        return this.model;
    }


}
