package controller.gifts;

import controller.Body;
import controller.Controller;
import controller.manager.BodyManager;
import controller.towers.TowerController;
import models.Model;
import utils.Utils;
import views.SingleView;

import java.awt.*;

/**
 * Created by Hieu It on 12/31/2016.
 */
public class TotalCoin extends Controller implements Body {
    private int coin = 500;

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public static TotalCoin instance = create(630, 40);

    public static TotalCoin create(int x, int y){
        return new TotalCoin(new Model(x,y,40,40),
                new SingleView(Utils.loadImage("res/iconCoin.png")));
    }

    private TotalCoin(Model model, SingleView view) {
        super(model, view);
        BodyManager.instance.register(this);
    }

    public boolean existCoin(TowerController towerController){
        return this.coin - towerController.getCoin() > -1;
    }
    @Override
    public void draw(Graphics g) {
        super.draw(g);
        String temp = coin + " $";
        g.setColor(Color.GREEN);
        g.drawString(temp,690,60);
    }

    @Override
    public void onContact(Body other) {
        if (other instanceof CoinController){
            System.out.println("BBBBBBB");
        }

    }
}
