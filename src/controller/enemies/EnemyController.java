package controller.enemies;

import controller.Body;
import controller.Controller;
import controller.HouseController;
import controller.manager.BodyManager;
import controller.manager.Manager;
import models.CheckPoint;
import models.Model;
import utils.AnimationManager;
import utils.Utils;
import views.Animation;

import java.awt.*;

/**
 * Created by Khuong Duy on 12/17/2016.
 */
public class EnemyController extends Controller implements Body {
    public static double hpMax;
    protected int hp;
    protected EnemyType enemyType;
    private static CheckPoint[] checkPoints = Utils.createCheckpoint();
    private static int speedFly = 2;
    private static int speedNormal = 1;
    private static int speedWalk = 2;
    private static int speedTank = 1;
    private static int speedHorse = 3;
    private static int speedSpeed = 4;
    private static int WIDTH = 25;
    private static int HEIGHT = 35;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }


    public EnemyController(Model model, Animation animation, EnemyType enemyType, int hp) {
        super(model, animation);
        Utils.register(this);
        isAlive = true;
        this.enemyType = enemyType;
        this.hp = hp;
        hpMax = hp;
        BodyManager.instance.register(this);
        EnemyManager.instance.add(this);

    }


    public void moveEnemy(int speed, Animation animationRight, Animation animationLeft, Animation animationDown, Animation animationUp) {
        if (this.getModel().getX() < checkPoints[1].getX() && this.model.getY() < checkPoints[2].getY()) {
            this.model.move(speed, 0);
            if (isAlive) {
                this.view = animationRight;
            }
        } else if (this.getModel().getY() < checkPoints[2].getY() && this.model.getX() < checkPoints[3].getX()) {
            this.model.move(0, speed);
            if (isAlive) {
                this.view = animationDown;
            }

        } else if (this.getModel().getX() < checkPoints[3].getX()) {
            this.model.move(speed, 0);
            if (isAlive) {
                this.view = animationRight;
            }

        } else if (this.getModel().getY() > checkPoints[4].getY() && this.getModel().getX() > checkPoints[3].getX()) {
            this.model.move(0, -speed);
            if (isAlive) {
                this.view = animationUp;
            }
        } else {
            this.model.move(speed, 0);
            if (isAlive) {
                this.view = animationRight;
            }
        }

    }
    public void run() {
        switch (enemyType) {
            case FLY:
                moveEnemy(speedFly,
                        AnimationManager.flyRight,
                        AnimationManager.flyLeft,
                        AnimationManager.flyDown,
                        AnimationManager.flyUp);
                break;
            case SPEED:
                moveEnemy(speedSpeed,
                        AnimationManager.speedRight,
                        AnimationManager.speedLeft,
                        AnimationManager.speedDown,
                        AnimationManager.speedUp);
                break;
            case HORSE:
                moveEnemy(speedHorse,
                        AnimationManager.horseRight,
                        AnimationManager.horseLeft,
                        AnimationManager.horseDown,
                        AnimationManager.horseUp);
                break;
            case NORMAL:
                moveEnemy(speedNormal,
                        AnimationManager.normalRight,
                        AnimationManager.normalLeft,
                        AnimationManager.normalDown,
                        AnimationManager.normalUp);
                break;
            case TANK:
                moveEnemy(speedTank,
                        AnimationManager.tankRight,
                        AnimationManager.tankLeft,
                        AnimationManager.tankDown,
                        AnimationManager.tankUp);
                break;
            case WALK:
                break;


        }

    }

    public void draw(Graphics g) {
        super.draw(g);
        // vẽ máu lại rất đẹp :v

        double leng = this.getModel().getWidth() * 0.8;
        if (hp / hpMax >= 0.6) {
            g.setColor(Color.green);
        } else if (hp / hpMax > 0.3) {
            g.setColor(Color.YELLOW);
        } else
            g.setColor(Color.red);
        g.fillRect(this.model.getX(), this.model.getY(), (int) ((hp / hpMax) * leng), 3);
    }

    public static EnemyController createEnemy(EnemyType type) {
        CheckPoint[] checkPoints = Utils.createCheckpoint();
        switch (type) {
            case NORMAL:
                return new EnemyController(
                        new Model(checkPoints[0].getX(),
                                checkPoints[0].getY(), 35, 35),
                        AnimationManager.normalRight, EnemyType.NORMAL,100);
            case FLY:
                return new EnemyController(
                        new Model(checkPoints[0].getX(),
                                checkPoints[0].getY(), 40, 35),
                        AnimationManager.flyRight, EnemyType.FLY, 100);
            case TANK:
                return new EnemyController(
                        new Model(checkPoints[0].getX(),
                                checkPoints[0].getY(), 40, 35),
                        AnimationManager.tankRight, EnemyType.TANK, 100);
            case HORSE:
                return new EnemyController(
                        new Model(checkPoints[0].getX(),
                                checkPoints[0].getY(), 30, 35),
                        AnimationManager.horseRight, EnemyType.HORSE, 100);
            case SPEED:
                return new EnemyController(
                        new Model(checkPoints[0].getX(),
                                checkPoints[0].getY(), 35, 35),
                        AnimationManager.speedRight, EnemyType.SPEED, 100);
        }
        return null;
    }

    @Override
    public void onContact(Body other) {
        if (other instanceof HouseController) {
            this.isAlive = false;
        }
    }

    @Override
    public Model getModel() {
        return this.model;
    }
}
