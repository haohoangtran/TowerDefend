package controller.manager;

import controller.BaseController;
import controller.Controller;

import java.awt.*;
import java.util.Vector;

/**
 * Created by Songt on 12/18/2016.
 */
public class Manager implements BaseController{
    public static Vector<Controller> controllers=new Vector<>();

    public void draw(Graphics g){
        for (Controller controller : controllers) {
            controller.draw(g);
        }
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
}
