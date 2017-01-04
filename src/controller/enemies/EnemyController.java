package controller.enemies;

import controller.Body;
import controller.Controller;
import controller.HouseController;
import controller.gifts.CoinController;
import controller.manager.BodyManager;
import controller.scenes.PlayGameScene;
import models.CheckPoint;
import models.Model;
import utils.AnimationManager;
import utils.Utils;
import views.Animation;

import java.awt.*;
import java.util.Vector;

/**
 * Created by HieuIt on 12/17/2016.
 */
public class EnemyController extends Controller implements Body {
    public double hpMax;
    protected int hp;

    public EnemyType enemyType;
    private static CheckPoint[] checkPoints = Utils.createCheckpoint();
    private int count = 0;
    private int slowCount = 0;

    public boolean slow = false;

    private static int speedFly = 4;
    private static int speedNormal = 4;
    private static int speedTank = 2;
    private static int speedHorse = 4;
    private static int speedSpeed = 6;
    private static int speedBot = 4;
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
        isAlive = true;
        this.enemyType = enemyType;
        this.hp = hp;
        hpMax = hp;
        BodyManager.instance.register(this);

    }


    public void moveEnemy(int speed, Animation animationRight, Animation animationLeft, Animation animationDown, Animation animationUp) {
        count++;
        if (count >= 3) {
            if (this.getModel().getX() < checkPoints[1].getX() && this.model.getY() < checkPoints[2].getY()) {
                if (slow) {
                    model.moveSlow(speed, 0);
                    slowCount++;
                    if (slowCount > 5) {
                        slowCount = 0;
                        slow = false;
                    }
                } else {
                    this.model.move(speed, 0);
                }
                if (isAlive) {
                    this.view = animationRight;
                }
            } else if (this.getModel().getY() < checkPoints[2].getY() && this.model.getX() < checkPoints[3].getX()) {
                if (slow) {
                    model.moveSlow(0, speed);
                    slowCount++;
                    if (slowCount > 5) {
                        slowCount = 0;
                        slow = false;
                    }
                } else {
                    this.model.move(0, speed);
                }
                if (isAlive) {
                    this.view = animationDown;
                }

            } else if (this.getModel().getX() < checkPoints[3].getX()) {
                if (slow) {
                    model.moveSlow(speed, 0);
                    slowCount++;
                    if (slowCount > 5) {
                        slowCount = 0;
                        slow = false;
                    }
                } else {
                    this.model.move(speed, 0);
                }
                if (isAlive) {
                    this.view = animationRight;
                }

            } else if (this.getModel().getY() > checkPoints[4].getY() && this.getModel().getX() > checkPoints[3].getX()) {
                if (slow) {
                    model.moveSlow(0, -speed);
                    slowCount++;
                    if (slowCount > 5) {
                        slowCount = 0;
                        slow = false;
                    }
                } else {
                    this.model.move(0, -speed);
                }
                if (isAlive) {
                    this.view = animationUp;
                }
            } else {
                if (slow) {
                    model.moveSlow(speed, 0);
                    slowCount++;
                    if (slowCount > 5) {
                        slowCount = 0;
                        slow = false;
                    }
                } else {
                    this.model.move(speed, 0);
                }
                if (isAlive) {
                    this.view = animationRight;
                }
            }
            count = 0;
        }

    }

    public void moveEnemyFly(int speed) {
        count++;
        if (count > 3) {
            if (slow) {
                model.moveSlow(speed, 0);
                slowCount++;
                if (slowCount > 5) {
                    slowCount = 0;
                    slow = false;
                }
            } else {
                this.model.move(speed, 0);
            }
            count = 0;

        }
    }

    public void run() {

        switch (enemyType) {
            case FLY:
                moveEnemyFly(speedFly);
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
            case BOT:
                moveEnemy(speedTank,
                        AnimationManager.tankRight,
                        AnimationManager.tankLeft,
                        AnimationManager.tankDown,
                        AnimationManager.tankUp);
                break;
        }

    }

    public void draw(Graphics g) {
        super.draw(g);

        double leng = this.getModel().getWidth() * 0.8;
        g.setColor(Color.red);
        g.fillRect(this.model.getX(), this.model.getY(), (int) leng, 3);
        g.setColor(Color.green);
        g.fillRect(this.model.getX(), this.model.getY(), (int) ((hp / hpMax) * leng), 3);

    }

    public EnemyType getEnemyType() {
        return enemyType;
    }

    public static EnemyController createEnemy(EnemyType type) {

        CheckPoint[] checkPoints = Utils.createCheckpoint();

        int hpNormal = 100;
        int hpFLy = 30;
        int hpTank = 300;
        int hpHores = 100;
        int hpSpeed = 100;
        int hpBot = 800;
        switch (type) {
            case NORMAL://1
                return new EnemyController(
                        new Model(checkPoints[0].getX(),
                                checkPoints[0].getY(), 35, 35),
                        AnimationManager.normalRight, EnemyType.NORMAL, hpNormal);
            case FLY://2
                return new EnemyController(
                        new Model(0, HouseController.instance.getModel().getMidY(), 40, 35),
                        AnimationManager.flyRight, EnemyType.FLY, hpFLy);
            case TANK://3
                return new EnemyController(
                        new Model(checkPoints[0].getX(),
                                checkPoints[0].getY(), 40, 35),
                        AnimationManager.tankRight, EnemyType.TANK, hpTank);
            case HORSE://4
                return new EnemyController(
                        new Model(checkPoints[0].getX(),
                                checkPoints[0].getY(), 30, 35),
                        AnimationManager.horseRight, EnemyType.HORSE, hpHores);
            case SPEED://5
                return new EnemyController(
                        new Model(checkPoints[0].getX(),
                                checkPoints[0].getY(), 35, 35),
                        AnimationManager.speedRight, EnemyType.SPEED, hpSpeed);
            case BOT://6
                return new EnemyController(new Model(checkPoints[0].getX(),
                        checkPoints[0].getY(), 60, 60),
                        AnimationManager.tankRight, EnemyType.BOT, hpBot);
        }
        return null;
    }
    public static EnemyController createEnemy(int type){
        switch (type){
            case 1:
                return createEnemy(EnemyType.NORMAL);

            case 2:
                return createEnemy(EnemyType.FLY);

            case 3:
                return createEnemy(EnemyType.TANK);

            case 4:
                return createEnemy(EnemyType.HORSE);

            case 5:
               return createEnemy(EnemyType.SPEED);

            case 6:
                return createEnemy(EnemyType.BOT);

        }
       return null;
    }
    public static EnemyController createEnemy(int x,int y,int hp,EnemyType type){
        switch (type) {
            case NORMAL:
                return new EnemyController(
                        new Model(x,
                                y, 35, 35),
                        AnimationManager.normalRight, EnemyType.NORMAL, hp);
            case FLY:

                return new EnemyController(
                        new Model(x, y, 40, 35),
                        AnimationManager.flyRight, EnemyType.FLY, hp);
            case TANK:

                return new EnemyController(
                        new Model(x,
                                y, 40, 35),
                        AnimationManager.tankRight, EnemyType.TANK, hp);
            case HORSE:

                return new EnemyController(
                        new Model(x,
                                y, 30, 35),
                        AnimationManager.horseRight, EnemyType.HORSE, hp);
            case SPEED:

                return new EnemyController(
                        new Model(x,
                                y, 35, 35),
                        AnimationManager.speedRight, EnemyType.SPEED, hp);
            case BOT:

                return new EnemyController(new Model(x,
                        y, 60, 60),
                        AnimationManager.tankRight, EnemyType.BOT, hp);
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
