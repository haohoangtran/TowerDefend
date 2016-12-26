package controller.manager;

import controller.Controller;

import java.awt.*;
import java.util.Vector;

/**
 * Created by Songt on 12/18/2016.
 */
public class Manager {
    public static Vector<Controller> controllers;

    public Manager() {

        this.controllers = new Vector<>();
    }
    public void drawAnimation(Graphics g){
        for (int i = 0; i < controllers.size(); i++) {
            controllers.get(i).drawAnimation(g);
        }
    }
    public void drawView(Graphics g){
        for (Controller controller : controllers) {
            controller.drawView(g);
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
