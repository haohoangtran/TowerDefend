package controller.enemies;

import controller.Controller;
import controller.towers.TowerController;
import controller.manager.Manager;

import java.util.Iterator;
import java.util.Vector;

/**
 * Created by HieuIt on 12/18/2016.
 */
public class EnemyManager extends Manager {
    public static  EnemyManager instance = new EnemyManager();
    public  void register(EnemyController enemyController) {
        controllers.add(enemyController);
    }


    private EnemyManager() {
        controllers=new Vector<>();
    }

    public static EnemyController chooseFire(TowerController towerController) {
        if(!towerController.isFire()){
            Iterator<Controller> iterator =controllers.iterator();
            while (iterator.hasNext()){
                Controller controller = iterator.next();
                if(towerController.intersectsCircle(controller.getModel())){
                    return (EnemyController) controller;
                }
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return controllers.size() == 0;
    }

    @Override
    public void run() {
        super.run();
        Iterator<Controller> iterator = controllers.iterator();
        while (iterator.hasNext()) {
            Controller controller = iterator.next();

            if (!controller.isAlive())
                iterator.remove();
        }
    }


}
