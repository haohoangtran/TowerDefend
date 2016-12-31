package controller.towers;

import controller.BaseController;

import java.awt.*;
import java.util.Vector;

/**
 * Created by HieuIt on 12/18/2016.
 */
public class TowerManager implements BaseController{
    private Vector<TowerController> towerControllers;

    public static final TowerManager instance = new TowerManager();

    private TowerManager() {
        towerControllers = new Vector<>();
    }

    public void draw(Graphics g) {
        for (int i = 0; i < towerControllers.size(); i++) {
            towerControllers.get(i).draw(g);
        }

    }


    public void run() {
        for (int i = 0; i < towerControllers.size(); i++) {
            if (towerControllers.get(i)!=null)
            towerControllers.get(i).run();
        }
    }

    public void add(TowerController t) {
        if (towerControllers != null)
            this.towerControllers.add(t);
    }

    public void remove(TowerController t) {
        this.towerControllers.remove(t);
    }
}