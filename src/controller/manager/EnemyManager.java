package controller.manager;

import controller.Controller;
import controller.EnemyController;
import controller.TowerController;

import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Songt on 12/18/2016.
 */
public class EnemyManager extends Manager {
    public static EnemyController chooseFire(TowerController towerController) {
        if(!towerController.isFire()){
            Iterator<Controller> iterator =controllers.iterator();
            while (iterator.hasNext()){
                Controller controller = iterator.next();
                if(towerController.getModel().intersectsCircle(controller.getModel())){
                    return (EnemyController) controller;
                }
                //iterator.remove();
            }

//            for (Controller controller : controllers) {
//                if(towerController.getModel().intersectsCircle(controller.getModel())){
//                    return (EnemyController) controller;
//                }
//            }
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

            if (!controller.getModel().isAlive())
                iterator.remove();
        }

    }
}
