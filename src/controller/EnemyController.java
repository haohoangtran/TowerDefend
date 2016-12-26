package controller;

import controller.manager.BodyManager;
import models.CheckPoint;
import models.Model;
import utils.Utils;
import views.Animation;
import views.View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.List;

/**
 * Created by Khuong Duy on 12/17/2016.
 */
public class EnemyController extends Controller implements Body {

    public EnemyController(Model model, Animation animation) {
        super(model, animation);
        this.model.setHp(100);
        Utils.register(this);
        BodyManager.instance.register(this);
    }

    public static final Animation rightAnimation = new Animation("res/Enemy/bahamut/bahamutRight1.png,res/Enemy/bahamut/bahamutRight2.png,res/Enemy/bahamut/bahamutRight3.png,res/Enemy/bahamut/bahamutRight4.png");
    public static final Animation downAnimation = new Animation("res/Enemy/bahamut/bahamutDown1.png,res/Enemy/bahamut/bahamutDown2.png,res/Enemy/bahamut/bahamutDown3.png,res/Enemy/bahamut/bahamutDown4.png");
    public static final Animation upAnimation = new Animation("res/Enemy/bahamut/bahamutUp1.png,res/Enemy/bahamut/bahamutUp2.png,res/Enemy/bahamut/bahamutUp3.png,res/Enemy/bahamut/bahamutUp4.png");

    public void run() {

        CheckPoint[] checkPoints = Utils.createCheckpoint();

        if (this.getModel().getX() < checkPoints[1].getX() && this.model.getY() < checkPoints[2].getY()) {
            this.model.move(2, 0);
            if (model.isAlive()) {
                this.animation = rightAnimation;
            }
        } else if (this.getModel().getY() < checkPoints[2].getY() && this.model.getX() < checkPoints[3].getX()) {
            this.model.move(0, 2);
            if (model.isAlive()) {
                this.animation = downAnimation;
            }

        } else if (this.getModel().getX() < checkPoints[3].getX()) {
            this.model.move(2, 0);
            if (model.isAlive()) {
                this.animation = rightAnimation;
            }

        } else if (this.getModel().getY() > checkPoints[4].getY() && this.getModel().getX() > checkPoints[3].getX()) {
            this.model.move(0, -2);
            if (model.isAlive()) {
                this.animation = upAnimation;
            }
        } else {
            this.model.move(2, 0);
            if (model.isAlive()) {
                this.animation = rightAnimation;
            }
        }

    }
    public void drawAnimation(Graphics g) {
        super.drawAnimation(g);
        int percent = this.getModel().getHp() / this.getModel().getWidth();
        if (percent*this.getModel().getWidth()/3 < this.getModel().getWidth()/2) {
            g.setColor(Color.yellow);
            g.fillRect(this.getModel().getX(), this.getModel().getY(), this.getModel().getWidth() * percent / 3, 3);
        }else if(percent*this.getModel().getWidth()/3 < this.getModel().getWidth()/4){
            g.setColor(Color.red);
            g.fillRect(this.getModel().getX(), this.getModel().getY(), this.getModel().getWidth() * percent / 3, 3);
        }else{
            g.setColor(Color.green);
            g.fillRect(this.getModel().getX(), this.getModel().getY(), this.getModel().getWidth() * percent / 3, 3);
        }
    }

    public static EnemyController createEnemy() {
        CheckPoint[] checkPoints = Utils.createCheckpoint();
        EnemyController enemyController = new EnemyController(new Model(checkPoints[0].getX(),
                checkPoints[0].getY(), 33, 48, false, 5, 2, 1, 10),
                new Animation("res/Enemy/bahamut/bahamutRight1.png,res/Enemy/bahamut/bahamutRight2.png,res/Enemy/bahamut/bahamutRight3.png,res/Enemy/bahamut/bahamutRight4.png"));
        return enemyController;
    }

    @Override
    public void onContact(Body other) {
        if (other instanceof BulletTower) {
            this.model.setHp(model.getHp() - 50);
            if (model.getHp() <= 0) {
                this.animation = null;
                this.model.setAlive(false);
            }
        }
        if (other instanceof HouseController){
            this.animation=null;
            this.model.setAlive(false);
        }
    }

    @Override
    public Model getModel() {
        return this.model;
    }
}
