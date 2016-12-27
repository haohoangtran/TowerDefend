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
    private static int speedFly = 2;
    private static int speedNormal = 1;
    private static int speedWalk = 2;
    private static int speedTank = 1;
    private static int speedHorse = 3;
    private static int speedSpeed = 4;

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

    }


    public void run() {

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

    public static EnemyController createEnemy(EnemyType enemyType) {
        CheckPoint[] checkPoints = Utils.createCheckpoint();
        EnemyController enemyController = new EnemyController(new Model(checkPoints[0].getX(),
                checkPoints[0].getY(), 33, 48),
                new Animation("res/Enemy/bahamut/bahamutRight1.png,res/Enemy/bahamut/bahamutRight2.png,res/Enemy/bahamut/bahamutRight3.png,res/Enemy/bahamut/bahamutRight4.png"),
                enemyType,100);
        return enemyController;
    }

    @Override
    public void onContact(Body other) {
//        if (other instanceof BulletTower) {
//            hp-=50;
//            if (hp <= 0) {
//                this.animation = null;
//                this.isAlive=false;
//            }
//        }
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
