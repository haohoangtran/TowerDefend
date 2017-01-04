package controller.towers;

import controller.Body;
import controller.Controller;
import controller.enemies.EnemyController;
import controller.enemies.EnemyManager;
import controller.enemies.EnemyType;
import controller.gifts.CoinController;
import controller.manager.BodyManager;
import controller.scenes.PlayGameScene;
import models.Model;
import utils.Utils;
import views.Animation;
import views.SingleView;
import views.View;

import java.awt.*;

/**
 * Created by tranh on 12/17/2016.
 */
public class BulletTower extends Controller implements Body {
    private int atk;
    private BulletType type;

    public BulletTower(Model model, SingleView view, int atk) {
        super(model, view);
        this.atk = atk;
        isAlive = true;
        BodyManager.instance.register(this);
    }

    public BulletTower(Model model, Animation animation, BulletType type) {
        super(model, animation);
        this.type = type;
        Utils.playSound("res/sound/fire1.wav",false);
        isAlive = true;
        BodyManager.instance.register(this);
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

    public BulletTower(Model model, SingleView view, BulletType type) {

        super(model, view);

        this.type = type;
            Utils.playSound("res/sound/fire.wav", false);

        isAlive = TowerController.isBulletAlive;
        BodyManager.instance.register(this);
    }

    public View getView() {
        return view;
    }


    public static int numberRun = 12;

    @Override
    public void draw(Graphics g) {
        if (isAlive()) {
            super.draw(g);
        }
    }

    @Override
    public void checkContact() {

    }

    @Override
    public void run() {
        switch (type) {
            case FIRE:
                break;
            default:
                if (enemyController != null && enemyController.enemyType != EnemyType.BOT) {
                    int xE = enemyController.getModel().getX() + enemyController.getModel().getWidth() / 2;
                    int yE = enemyController.getModel().getY() + enemyController.getModel().getHeight() / 2;
                    int x = (xE - this.model.getX());
                    int y = (yE - this.model.getY());
                    this.model.move(x / numberRun, y / numberRun);
                }
                break;
        }
        if (view.isAnimationReachEnd()) {
            this.setAlive(false);
        }

    }

    public static BulletTower createBullet(int x, int y, BulletType type) {
        switch (type) {
            case NORMAL:
                BulletTower b = new BulletTower(new Model(x, y, 12, 12), new SingleView(Utils.loadImage("res/bullet.png")), BulletType.NORMAL);
                b.setAtk(10);
                return b;
            case SLOW:
                BulletTower b2 = new BulletTower(new Model(x, y, 12, 12), new SingleView(Utils.loadImage("res/bullet.png")), BulletType.SLOW);
                b2.setAtk(10);
                return b2;
            case FIRE:
                BulletTower b3 = new BulletTower(new Model(x, y, 100, 100), new Animation(Utils.realIInFoder("res/Bullet/Fire")), BulletType.FIRE);
                b3.setAtk(10);
                return b3;

        }
        return null;
    }

    @Override
    public void onContact(Body other) {
        if (other instanceof EnemyController) {
            switch (this.type) {
                case NORMAL:
                    setAlive(false);
                    ((EnemyController) other).setHp(((EnemyController) other).getHp() - atk);
                    if (((EnemyController) other).getHp() <= 0) {
                        PlayGameScene.controllers.add(CoinController.createCoin(this.getModel().getX(), this.getModel().getY()));
                        if (((EnemyController) other).enemyType!=EnemyType.FLY) {
                            Utils.playSound("res/sound/die.wav", false);
                        }else {

                            Utils.playSound("res/sound/Die_a.wav", false);
                        }
                        ((EnemyController) other).setAlive(false);
                    }
                    break;
                case SLOW:

                    setAlive(false);
                    ((EnemyController) other).slow = true;

                    break;
                case FIRE:
                    BodyManager.instance.remove(this);
                    for (int i = 0; i < EnemyManager.instance.getControllers().size(); i++) {
                        EnemyController enemyController = (EnemyController) EnemyManager.instance.getControllers().get(i);
                        int x = this.model.getX() + 25 - enemyController.getModel().getX();
                        int y = this.model.getY() + 25 - enemyController.getModel().getY();
                        double r = Math.sqrt(x * x + y * y);
                        if (r <= 100) {
                            enemyController.setHp(enemyController.getHp() - atk);
                            if (enemyController.getHp() <= 0) {
                                PlayGameScene.controllers.add(CoinController.createCoin(this.getModel().getX(), this.getModel().getY()));

                                Utils.playSound("res/sound/die.wav",false);
                                if (((EnemyController) other).enemyType!=EnemyType.FLY) {
                                    Utils.playSound("res/sound/die.wav", false);
                                }else {

                                    Utils.playSound("res/sound/Die_a.wav", false);
                                }
                                enemyController.setAlive(false);
                            }
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public Model getModel() {
        return this.model;
    }


}
