package controller.enemies;

import controller.Body;
import controller.Controller;
import controller.HouseController;
import controller.manager.BodyManager;
import models.CheckPoint;
import models.Model;
import utils.Utils;
import views.Animation;

import java.awt.*;

/**
 * Created by Khuong Duy on 12/17/2016.
 */
public class EnemyController extends Controller implements Body {
    public static double hpMax;
    protected int hp;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }



    public EnemyController(Model model, Animation animation,int hp) {
        super(model, animation);
        Utils.register(this);
        isAlive=true;
        this.hp=hp;
        hpMax=hp;
        BodyManager.instance.register(this);
    }

    public static final Animation bahamutRightAnimation = new Animation("res/Enemy/bahamut/bahamutRight1.png,res/Enemy/bahamut/bahamutRight2.png,res/Enemy/bahamut/bahamutRight3.png,res/Enemy/bahamut/bahamutRight4.png");
    public static final Animation bahamutDownAnimation = new Animation("res/Enemy/bahamut/bahamutDown1.png,res/Enemy/bahamut/bahamutDown2.png,res/Enemy/bahamut/bahamutDown3.png,res/Enemy/bahamut/bahamutDown4.png");
    public static final Animation bahamutUpAnimation = new Animation("res/Enemy/bahamut/bahamutUp1.png,res/Enemy/bahamut/bahamutUp2.png,res/Enemy/bahamut/bahamutUp3.png,res/Enemy/bahamut/bahamutUp4.png");

    public void run() {

        CheckPoint[] checkPoints = Utils.createCheckpoint();

        if (this.getModel().getX() < checkPoints[1].getX() && this.model.getY() < checkPoints[2].getY()) {
            this.model.move(2, 0);
            if (isAlive) {
                this.animation = bahamutRightAnimation;
            }
        } else if (this.getModel().getY() < checkPoints[2].getY() && this.model.getX() < checkPoints[3].getX()) {
            this.model.move(0, 2);
            if (isAlive) {
                this.animation = bahamutDownAnimation;
            }

        } else if (this.getModel().getX() < checkPoints[3].getX()) {
            this.model.move(2, 0);
            if (isAlive) {
                this.animation = bahamutRightAnimation;
            }

        } else if (this.getModel().getY() > checkPoints[4].getY() && this.getModel().getX() > checkPoints[3].getX()) {
            this.model.move(0, -2);
            if (isAlive) {
                this.animation = bahamutUpAnimation;
            }
        } else {
            this.model.move(2, 0);
            if (isAlive) {
                this.animation = bahamutRightAnimation;
            }
        }

    }
    public void drawAnimation(Graphics g) {
        super.drawAnimation(g);
        // vẽ máu lại rất đẹp :v

        double leng=this.getModel().getWidth();
        if(hp/hpMax>=0.6){
            g.setColor(Color.green);
        } else if(hp/hpMax>0.3){
            g.setColor(Color.YELLOW);
        }else
            g.setColor(Color.red);
        g.fillRect(this.model.getX(),this.model.getY(),(int)((hp/hpMax)*leng),3);
    }

    public static EnemyController createEnemy() {
        CheckPoint[] checkPoints = Utils.createCheckpoint();
        EnemyController enemyController = new EnemyController(new Model(checkPoints[0].getX(),
                checkPoints[0].getY(), 33, 48),
                new Animation("res/Enemy/bahamut/bahamutRight1.png,res/Enemy/bahamut/bahamutRight2.png,res/Enemy/bahamut/bahamutRight3.png,res/Enemy/bahamut/bahamutRight4.png"),
                100);
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
        if (other instanceof HouseController){
            this.animation=null;
            this.isAlive=false;
        }
    }

    @Override
    public Model getModel() {
        return this.model;
    }
}
