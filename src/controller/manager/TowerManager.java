package controller.manager;

import controller.TowerController;

import java.awt.*;
import java.util.Vector;

/**
 * Created by Songt on 12/18/2016.
 */
public class TowerManager {
    private Vector<TowerController> towerControllers;

    public TowerManager() {
        towerControllers = new Vector<>();
    }

    public void draw(Graphics g) {
        for (int i = 0; i < towerControllers.size(); i++) {
            towerControllers.get(i).drawView(g);
        }
        System.out.println(towerControllers.size());

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