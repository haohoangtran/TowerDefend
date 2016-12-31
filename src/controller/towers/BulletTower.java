package controller.towers;

import controller.Body;
import controller.Controller;
import controller.enemies.EnemyController;
import controller.manager.BodyManager;
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
    public BulletType type;
    private int time;

    public BulletTower(Model model, SingleView view, int atk) {
        super(model, view);
        this.atk = atk;
        isAlive=true;
        BodyManager.instance.register(this);
    }

    public BulletTower(Model model, Animation animation, BulletType type){
        super(model,animation);
        this.type = type;
        isAlive=true;
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
        isAlive = true;
        BodyManager.instance.register(this);
    }
    public View getView(){
        return view;
    }


    public static int numberRun = 6;

    @Override
    public void run() {
        time++;
        switch (type){
            case FIRE:
                break;
            default:
                if (enemyController != null) {
                    int xE = enemyController.getModel().getX() + enemyController.getModel().getWidth() / 2;
                    int yE = enemyController.getModel().getY() + enemyController.getModel().getHeight() / 2;
                    int x = (xE - this.model.getX());
                    int y = (yE - this.model.getY());
                    this.model.move(x / numberRun, y / numberRun);
                }
        }
            if (view.isAnimationReachEnd()) {
                setAlive(false);
            }

    }

    public static BulletTower createBullet(int x, int y, BulletType type) {
        switch (type) {
            case NORMAL:
                BulletTower b = new BulletTower(new Model(x, y, 12, 12), new SingleView(Utils.loadImage("res/bullet.png")), 10);
                b.setAtk(10);
                return b;
            case SLOW:
                BulletTower b2 = new BulletTower(new Model(x, y, 12, 12), new SingleView(Utils.loadImage("res/bullet.png")), 10);
                b2.setAtk(10);
                return b2;
            case FIRE:
                BulletTower b3 =new BulletTower(new Model(x,y,100,100),new Animation(Utils.realIInFoder("res/Bullet/Fire")),BulletType.FIRE);
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
                        ((EnemyController) other).setAlive(false);
                    }
                case SLOW:
                    setAlive(false);
                    ((EnemyController) other).setHp(((EnemyController) other).getHp() - atk);
                    ((EnemyController) other).slow=true;
                    if (((EnemyController) other).getHp() <= 0) {
                        ((EnemyController) other).setAlive(false);
                    }
                case FIRE:
                    setAlive(false);
                    ((EnemyController) other).setHp(((EnemyController) other).getHp() - atk);
                    ((EnemyController) other).slow=true;
                    if (((EnemyController) other).getHp() <= 0) {
                        ((EnemyController) other).setAlive(false);
                    }
            }
        }
    }

    @Override
    public Model getModel() {
        return this.model;
    }


}
