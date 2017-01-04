package controller.manager;

import controller.BaseController;
import controller.Controller;

import java.awt.*;
import java.io.Serializable;
import java.util.Vector;

/**
 * Created by Songt on 12/18/2016.
 */
public class Manager implements BaseController,Serializable{


    public Vector<Controller> getControllers() {
        return controllers;
    }

    public  static Vector<Controller> controllers=new Vector<>();

    public void draw(Graphics g){
        for (Controller controller : controllers) {
            controller.draw(g);
        }
    }

    @Override
    public void checkContact() {

    }

    public  void remove(Controller controller){
        controllers.remove(controller);
    }
    public  void add(Controller c){
        controllers.add(c);
    }
    public void run(){
        for (Controller controller : controllers) {
            controller.run();
        }
    }
    public void removeAll(){
        controllers=new Vector<>();
    }
}
