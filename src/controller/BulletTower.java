package controller;

import controller.manager.BodyManager;
import models.Model;
import utils.Utils;
import views.View;

import java.awt.*;
import java.util.Iterator;

/**
 * Created by tranh on 12/17/2016.
 */
public class BulletTower extends Controller implements Body {
    public EnemyController getEnemyController() {
        return enemyController;
    }

    public void setEnemyController(EnemyController enemyController) {
        this.enemyController = enemyController;
    }

    private EnemyController enemyController;

    public BulletTower(Model model, View view) {
        super(model, view);
        BodyManager.instance.register(this);
    }


    public static int numberRun = 15;

    @Override
    public void run() {
        if (enemyController != null) {
            if (enemyController.getModel().isAlive()) {
                int xE = enemyController.getModel().getX() + enemyController.model.getWidth() / 2;
                int yE = enemyController.getModel().getY() + enemyController.model.getHeight() / 2;
                int x = (xE - this.model.getX());
                int y = (yE - this.model.getY());
                this.model.move(x / numberRun, y / numberRun);
            }
        }
    }

    public static BulletTower createBullet(int x, int y) {
        return new BulletTower(new Model(x, y, 12, 12, 20, 1), new View(Utils.loadImage("res/bullet.png")));
    }

    @Override
    public void drawView(Graphics g) {
        super.drawView(g);
    }

    @Override
    public void onContact(Body other) {
        if (other instanceof EnemyController) {
            this.model.setAlive(false);
        }
    }

    @Override
    public Model getModel() {
        return this.model;
    }
}
