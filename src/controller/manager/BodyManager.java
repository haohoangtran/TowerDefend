package controller.manager;

import controller.Body;
import models.Model;

import java.util.Iterator;
import java.util.Vector;

/**
 * Created by HieuIt on 12/17/2016.
 */
public class BodyManager {
    private Vector<Body> bodies;

    public static final BodyManager instance=new BodyManager();

    public void register(Body other){
        this.bodies.add(other);
    }

    public BodyManager() {
        bodies=new Vector<>();
    }

    public void checkContact() {
        for (int i = 0; i < bodies.size() - 1; i++) {
            for (int j = i + 1; j < bodies.size(); j++) {
                Body bodyi = bodies.get(i);
                Body bodyj = bodies.get(j);

                Model modeli = bodyi.getModel();
                Model modelj = bodyj.getModel();

                if (modeli.intersects(modelj)) {
                    bodyi.onContact(bodyj);
                    bodyj.onContact(bodyi);
                }
            }
        }
        Iterator<Body> iterator = bodies.iterator();
        while (iterator.hasNext()) {
            Body body = iterator.next();
            if (!body.getModel().isAlive()) {
                iterator.remove();
            }
        }
    }
}
