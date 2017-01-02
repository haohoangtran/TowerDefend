package controller.gifts;

import controller.Body;
import controller.Controller;
import controller.manager.BodyManager;
import models.Model;
import utils.Utils;
import views.Animation;
import views.SingleView;

import java.awt.*;

/**
 * Created by Hieu It on 12/31/2016.
 */
public class CoinController extends Controller implements Body {
    private static int WITDH = 20;
    private static int HEIGHT = 30;
    public static int SPEED = 10;

    public static CoinController createCoin(int x, int y) {
        return new CoinController(new Model(x, y, WITDH, HEIGHT),
                new Animation(Utils.loadSheet("res/anmationCoin.png", 116, 200, 0, 6)));
    }

    @Override
    public void draw(Graphics g) {
        if (isAlive()) {
            this.view.draw(g, model);
        }
    }

    public void move() {
        int x = TotalCoin.instance.getModel().getX() - this.getModel().getX();
        int y = TotalCoin.instance.getModel().getY() - this.getModel().getY();
        double length = Math.sqrt(x * x + y * y);
        double steps = length / (double) SPEED;
        this.model.move((int) (x / steps), (int) (y / steps));
    }

    @Override
    public void run() {
        super.run();
        move();
    }

    public CoinController(Model model, Animation animation) {
        super(model, animation);
        BodyManager.instance.register(this);
    }

    @Override
    public void onContact(Body other) {
        if (other instanceof TotalCoin) {
            System.out.println("AAAAAAAAAA");
            TotalCoin.instance.setCoin(TotalCoin.instance.getCoin() + 50);
            this.setAlive(false);
        }

    }
}
