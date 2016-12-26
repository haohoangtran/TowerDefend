package controller;

import controller.manager.BodyManager;
import models.Model;
import utils.Utils;
import views.Animation;
import views.View;

import java.awt.*;

/**
 * Created by DUC THANG on 12/17/2016.
 */
public class HouseController extends Controller implements Body{
    private int hp;
    private boolean gameOn = true;

    public boolean isGameOn() {
        return gameOn;
    }

    public HouseController(Model model, Animation animation) {
        super(model, animation);
        BodyManager.instance.register(this);
    }

    public HouseController(Model model, View view) {
        super(model, view);
        this.hp=100;
        BodyManager.instance.register(this);
    }

    @Override
    public void drawView(Graphics g) {
        if (this.model.isAlive()) {
            view.draw(g, this.model);
        } else {
            g.drawImage(Utils.loadImage("res/gameOver.png"),300,200,300,300,null);
        }
    }


    @Override
    public void drawAnimation(Graphics g) {
        if (animation != null) {
            model.setX(830);
            model.setY(330);
            animation.setWidth(120);
            animation.setHeight(120);
            animation.drawExplosion(g, model);
            //animation = null;
            this.gameOn=false;
        }
    }


    public static HouseController createHpFull(int x, int y) {
        return new HouseController(new Model(x, y, 100, 140, false, 0, 10, 0, 0),
                new View(Utils.loadImage("res/Hp/houseHpFull.png")));
    }

    @Override
    public void onContact(Body other) {
        if (other instanceof EnemyController) {
            this.hp -= 25;
            if (hp == 75) {
                this.view.setImage(Utils.loadImage("res/Hp/houseHpMedium.png"));
            }
            if (hp == 50) {
                this.view.setImage(Utils.loadImage("res/Hp/houseHpLow.png"));
            }
            if (hp == 25) {
                this.view.setImage(Utils.loadImage("res/Hp/houseHpEmpty.png"));
            }
            if (this.hp == 0) {
                this.animation = new Animation("res/Hp/explosion1.png,res/Hp/explosion2.png,res/Hp/explosion3.png,res/Hp/explosion4.png");
                this.model.setAlive(false);
            }
        }
    }

}
