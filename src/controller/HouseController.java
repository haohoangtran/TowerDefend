package controller;

import controller.enemies.EnemyController;
import controller.manager.BodyManager;
import models.Model;
import utils.Utils;
import views.Animation;
import views.SingleView;

import java.awt.*;

/**
 * Created by HieuIt on 12/17/2016.
 */
public class HouseController extends Controller implements Body{
    private int hp;
    public double hpMax;
    public boolean gameOn = true;
    public static  HouseController instance = HouseController.createHpFull(830, 325);
    public boolean isGameOn() {
        return gameOn;
    }

    private HouseController(Model model, Animation animation) {
        super(model, animation);
        isAlive=true;
        BodyManager.instance.register(this);
    }

    private HouseController(Model model, SingleView view) {
        super(model, view);
        this.view =view;
        this.hp=100;
        this.hpMax =100;
        BodyManager.instance.register(this);
    }

    @Override
    public void draw(Graphics g) {
        if (isAlive) {
            view.draw(g, this.model);
        }
        double leng = this.getModel().getWidth() * 0.5;
        if (hp / hpMax > 0.75) {
            g.setColor(Color.green);
        } else if (hp / hpMax > 0.25) {
            g.setColor(Color.YELLOW);
        } else
            g.setColor(Color.red);
        g.fillRect(this.model.getX(), this.model.getY(), (int) ((hp / hpMax) * leng), 3);
    }


    public static HouseController createHpFull(int x, int y) {
        HouseController h= new HouseController(new Model(x-20, y-20, 160, 170),
                new SingleView(Utils.loadImage("res/houseController.png")));
        h.setAlive(true);
        return h;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public void onContact(Body other) {
        if (other instanceof EnemyController) {
            if (!view.isAnimationReachEnd()) {
                this.view = new Animation("res/houseController1.png,res/houseController2.png,res/houseController3.png");
            }else {
                this.view = new SingleView(Utils.loadImage("res/houseController.png"));
            }
            this.hp -= 15;
            if (this.hp <=-15) {
                setAlive(false);
            }
        }
    }

}
