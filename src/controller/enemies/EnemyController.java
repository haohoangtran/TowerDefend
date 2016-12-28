package controller.enemies;

import controller.Body;
import controller.Controller;
import controller.HouseController;
import controller.manager.BodyManager;
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
<<<<<<< HEAD
    private static CheckPoint[] checkPoints = Utils.createCheckpoint();
=======
>>>>>>> 70f79ee73bb5f3079ae53a6e3096c1eee75466b3
    private static int speedFly = 2;
    private static int speedNormal = 1;
    private static int speedWalk = 2;
    private static int speedTank = 1;
    private static int speedHorse = 3;
    private static int speedSpeed = 4;
<<<<<<< HEAD
    private static int WIDTH = 25;
    private static int HEIGHT = 35;
=======
>>>>>>> 70f79ee73bb5f3079ae53a6e3096c1eee75466b3

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
<<<<<<< HEAD
=======

    }

>>>>>>> 70f79ee73bb5f3079ae53a6e3096c1eee75466b3

    }

<<<<<<< HEAD

    public void moveEnemy(int speed, Animation animationRight, Animation animationLeft, Animation animationDown, Animation animationUp) {
        if (this.getModel().getX() < checkPoints[1].getX() && this.model.getY() < checkPoints[2].getY()) {
            this.model.move(speed, 0);
            if (isAlive) {
                this.animation = animationRight;
            }
        } else if (this.getModel().getY() < checkPoints[2].getY() && this.model.getX() < checkPoints[3].getX()) {
            this.model.move(0, speed);
            if (isAlive) {
                this.animation = animationDown;
            }

        } else if (this.getModel().getX() < checkPoints[3].getX()) {
            this.model.move(speed, 0);
            if (isAlive) {
                this.animation = animationRight;
            }

        } else if (this.getModel().getY() > checkPoints[4].getY() && this.getModel().getX() > checkPoints[3].getX()) {
            this.model.move(0, -speed);
            if (isAlive) {
                this.animation = animationUp;
            }
        } else {
            this.model.move(speed, 0);
            if (isAlive) {
                this.animation = animationRight;
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
=======
        CheckPoint[] checkPoints = Utils.createCheckpoint();
        switch (enemyType) {

            case FLY:
                if (this.getModel().getX() < checkPoints[1].getX() && this.model.getY() < checkPoints[2].getY()) {
                    this.model.move(speedFly, 0);
                    if (isAlive) {
                        this.animation = AnimationManager.flyRight;
                    }
                } else if (this.getModel().getY() < checkPoints[2].getY() && this.model.getX() < checkPoints[3].getX()) {
                    this.model.move(0, speedFly);
                    if (isAlive) {
                        this.animation = AnimationManager.flyDown;
                    }

                } else if (this.getModel().getX() < checkPoints[3].getX()) {
                    this.model.move(speedFly, 0);
                    if (isAlive) {
                        this.animation = AnimationManager.flyRight;
                    }

                } else if (this.getModel().getY() > checkPoints[4].getY() && this.getModel().getX() > checkPoints[3].getX()) {
                    this.model.move(0, -speedFly);
                    if (isAlive) {
                        this.animation = AnimationManager.flyUp;
                    }
                } else {
                    this.model.move(speedFly, 0);
                    if (isAlive) {
                        this.animation = AnimationManager.flyRight;
                    }
                }
                break;
            case SPEED:
                if (this.getModel().getX() < checkPoints[1].getX() && this.model.getY() < checkPoints[2].getY()) {
                    this.model.move(speedSpeed, 0);
                    if (isAlive) {
                        this.animation = AnimationManager.speedRight;
                    }
                } else if (this.getModel().getY() < checkPoints[2].getY() && this.model.getX() < checkPoints[3].getX()) {
                    this.model.move(0, speedSpeed);
                    if (isAlive) {
                        this.animation = AnimationManager.speedDown;
                    }

                } else if (this.getModel().getX() < checkPoints[3].getX()) {
                    this.model.move(speedSpeed, 0);
                    if (isAlive) {
                        this.animation = AnimationManager.speedRight;
                    }

                } else if (this.getModel().getY() > checkPoints[4].getY() && this.getModel().getX() > checkPoints[3].getX()) {
                    this.model.move(0, -speedSpeed);
                    if (isAlive) {
                        this.animation = AnimationManager.speedUp;
                    }
                } else {
                    this.model.move(speedSpeed, 0);
                    if (isAlive) {
                        this.animation = AnimationManager.speedRight;
                    }
                }
                break;
            case HORSE:
                if (this.getModel().getX() < checkPoints[1].getX() && this.model.getY() < checkPoints[2].getY()) {
                    this.model.move(speedHorse, 0);
                    if (isAlive) {
                        this.animation = AnimationManager.horseRight;
                    }
                } else if (this.getModel().getY() < checkPoints[2].getY() && this.model.getX() < checkPoints[3].getX()) {
                    this.model.move(0, speedHorse);
                    if (isAlive) {
                        this.animation = AnimationManager.horseDown;
                    }

                } else if (this.getModel().getX() < checkPoints[3].getX()) {
                    this.model.move(speedHorse, 0);
                    if (isAlive) {
                        this.animation = AnimationManager.horseRight;
                    }

                } else if (this.getModel().getY() > checkPoints[4].getY() && this.getModel().getX() > checkPoints[3].getX()) {
                    this.model.move(0, -speedHorse);
                    if (isAlive) {
                        this.animation = AnimationManager.horseUp;
                    }
                } else {
                    this.model.move(speedHorse, 0);
                    if (isAlive) {
                        this.animation = AnimationManager.horseRight;
                    }
                }
                break;
            case NORMAL:
                if (this.getModel().getX() < checkPoints[1].getX() && this.model.getY() < checkPoints[2].getY()) {
                    this.model.move(speedNormal, 0);
                    if (isAlive) {
                        this.animation = AnimationManager.normalRight;
                    }
                } else if (this.getModel().getY() < checkPoints[2].getY() && this.model.getX() < checkPoints[3].getX()) {
                    this.model.move(0, speedNormal);
                    if (isAlive) {
                        this.animation = AnimationManager.normalDown;
                    }

                } else if (this.getModel().getX() < checkPoints[3].getX()) {
                    this.model.move(speedNormal, 0);
                    if (isAlive) {
                        this.animation = AnimationManager.normalRight;
                    }

                } else if (this.getModel().getY() > checkPoints[4].getY() && this.getModel().getX() > checkPoints[3].getX()) {
                    this.model.move(0, -speedNormal);
                    if (isAlive) {
                        this.animation = AnimationManager.normalUp;
                    }
                } else {
                    this.model.move(speedNormal, 0);
                    if (isAlive) {
                        this.animation = AnimationManager.normalRight;
                    }
                }
                break;
            case TANK:
                if (this.getModel().getX() < checkPoints[1].getX() && this.model.getY() < checkPoints[2].getY()) {
                    this.model.move(speedTank, 0);
                    if (isAlive) {
                        this.animation = AnimationManager.tankRight;
                    }
                } else if (this.getModel().getY() < checkPoints[2].getY() && this.model.getX() < checkPoints[3].getX()) {
                    this.model.move(0, speedTank);
                    if (isAlive) {
                        this.animation = AnimationManager.tankDown;
                    }

                } else if (this.getModel().getX() < checkPoints[3].getX()) {
                    this.model.move(speedTank, 0);
                    if (isAlive) {
                        this.animation = AnimationManager.tankRight;
                    }

                } else if (this.getModel().getY() > checkPoints[4].getY() && this.getModel().getX() > checkPoints[3].getX()) {
                    this.model.move(0, -speedTank);
                    if (isAlive) {
                        this.animation = AnimationManager.tankUp;
                    }
                } else {
                    this.model.move(speedTank, 0);
                    if (isAlive) {
                        this.animation = AnimationManager.tankRight;
                    }
                }
                break;
            case WALK:
                if (this.getModel().getX() < checkPoints[1].getX() && this.model.getY() < checkPoints[2].getY()) {
                    this.model.move(speedWalk, 0);
                    if (isAlive) {
                        this.animation = AnimationManager.horseRight;
                    }
                } else if (this.getModel().getY() < checkPoints[2].getY() && this.model.getX() < checkPoints[3].getX()) {
                    this.model.move(0, speedWalk);
                    if (isAlive) {
                        this.animation = AnimationManager.horseDown;
                    }

                } else if (this.getModel().getX() < checkPoints[3].getX()) {
                    this.model.move(speedWalk, 0);
                    if (isAlive) {
                        this.animation = AnimationManager.horseRight;
                    }

                } else if (this.getModel().getY() > checkPoints[4].getY() && this.getModel().getX() > checkPoints[3].getX()) {
                    this.model.move(0, -speedWalk);
                    if (isAlive) {
                        this.animation = AnimationManager.horseUp;
                    }
                } else {
                    this.model.move(speedWalk, 0);
                    if (isAlive) {
                        this.animation = AnimationManager.horseRight;
                    }
                }
                break;


        }

    }
>>>>>>> 70f79ee73bb5f3079ae53a6e3096c1eee75466b3

    public void drawAnimation(Graphics g) {
        super.drawAnimation(g);
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

<<<<<<< HEAD
    public static EnemyController createEnemy(EnemyType type) {
        CheckPoint[] checkPoints = Utils.createCheckpoint();
        switch (type) {
            case NORMAL:
                return new EnemyController(
                        new Model(checkPoints[0].getX(),
                                checkPoints[0].getY(), 40, 40),
                        AnimationManager.normalRight, EnemyType.NORMAL,100);
            case FLY:
                return new EnemyController(
                        new Model(checkPoints[0].getX(),
                                checkPoints[0].getY(), 40, 35),
                        AnimationManager.flyRight, EnemyType.FLY, 100);
            case TANK:
                return new EnemyController(
                        new Model(checkPoints[0].getX(),
                                checkPoints[0].getY(), 35, 35),
                        AnimationManager.tankRight, EnemyType.TANK, 100);
            case HORSE:
                return new EnemyController(
                        new Model(checkPoints[0].getX(),
                                checkPoints[0].getY(), 30, 40),
                        AnimationManager.horseRight, EnemyType.HORSE, 100);
            case SPEED:
                return new EnemyController(
                        new Model(checkPoints[0].getX(),
                                checkPoints[0].getY(), 35, 35),
                        AnimationManager.speedRight, EnemyType.SPEED, 100);
        }
        return null;
=======
    public static EnemyController createEnemy(EnemyType enemyType) {
        CheckPoint[] checkPoints = Utils.createCheckpoint();
        EnemyController enemyController = new EnemyController(new Model(checkPoints[0].getX(),
                checkPoints[0].getY(), 33, 48),
                new Animation("res/Enemy/bahamut/bahamutRight1.png,res/Enemy/bahamut/bahamutRight2.png,res/Enemy/bahamut/bahamutRight3.png,res/Enemy/bahamut/bahamutRight4.png"),
                enemyType,100);
        return enemyController;
>>>>>>> 70f79ee73bb5f3079ae53a6e3096c1eee75466b3
    }

    @Override
    public void onContact(Body other) {
<<<<<<< HEAD
=======
//        if (other instanceof BulletTower) {
//            hp-=50;
//            if (hp <= 0) {
//                this.animation = null;
//                this.isAlive=false;
//            }
//        }
>>>>>>> 70f79ee73bb5f3079ae53a6e3096c1eee75466b3
        if (other instanceof HouseController) {
            this.animation = null;
            this.isAlive = false;
        }
    }

    @Override
    public Model getModel() {
        return this.model;
    }
}
