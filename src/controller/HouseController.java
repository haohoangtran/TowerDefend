package controller;

import controller.enemies.EnemyController;
import controller.manager.BodyManager;
import controller.manager.Manager;
import models.Model;
import org.w3c.dom.html.HTMLObjectElement;
import utils.Utils;
import views.Animation;
import views.SingleView;
import views.View;

import java.awt.*;

/**
 * Created by DUC THANG on 12/17/2016.
 */
public class HouseController extends Controller implements Body{
    private int hp;
    public boolean gameOn = true;
    public static final HouseController instance = HouseController.createHpFull(830, 325);
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
        BodyManager.instance.register(this);
    }

    @Override
    public void draw(Graphics g) {
        if (isAlive) {
            view.draw(g, this.model);
        } else {
            g.drawImage(Utils.loadImage("res/gameOver.png"),300,200,300,300,null);
        }
    }


    public static HouseController createHpFull(int x, int y) {
        HouseController h= new HouseController(new Model(x, y, 100, 140),
                new SingleView(Utils.loadImage("res/Hp/houseHpFull.png")));
        h.setAlive(true);
        return h;
    }

    @Override
    public void onContact(Body other) {
        if (other instanceof EnemyController) {
            this.hp -= 25;
            if (hp == 75) {
                this.view = new SingleView(Utils.loadImage("res/Hp/houseHpMedium.png"));
            }
            if (hp == 50) {
                this.view = new SingleView (Utils.loadImage("res/Hp/houseHpLow.png"));
            }
            if (hp == 25) {
                this.view = new SingleView(Utils.loadImage("res/Hp/houseHpEmpty.png"));
            }
            if (this.hp == 0) {
                this.view = new Animation("res/Hp/explosion1.png,res/Hp/explosion2.png,res/Hp/explosion3.png,res/Hp/explosion4.png");
                setAlive(false);
            }
        }
    }

}
