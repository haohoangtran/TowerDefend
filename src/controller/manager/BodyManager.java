package controller.manager;

import controller.BaseController;
import controller.Body;
import models.Model;

import java.awt.*;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by HieuIt on 12/17/2016.
 */
public class BodyManager implements BaseController{
    private Vector<Body> bodies;

    public static final BodyManager instance=new BodyManager();

    public void register(Body other){
        this.bodies.add(other);
    }
    public void remove(Body other){
        bodies.remove(other);
    }

    public BodyManager() {
        bodies=new Vector<>();
    }

    public void run() {
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
            if (!body.isAlive()) {
                iterator.remove();
            }
        }
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void checkContact() {

    }

    public Vector<Body> getBodies() {
        return bodies;
    }

    public void setBodies(Vector<Body> bodies) {
        this.bodies = bodies;
    }
}
