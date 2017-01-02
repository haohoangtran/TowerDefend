package controller;

import models.CheckPoint;
import models.Model;
import views.Animation;
import views.SingleView;
import views.View;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by Khuong Duy on 12/17/2016.
 */
public class Controller implements BaseController,Serializable{
    protected Model model;
    //protected Animation animation;
    protected View view;

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    protected boolean isAlive;


    public Controller(Model model, Animation animation) {
        this.model = model;
        this.view = animation;
    }


    public Controller(Model model, SingleView view) {
        this.model = model;
        this.view = view;
    }

    public Model getModel() {
        return model;
    }

    public void run() {

    }

    public void draw(Graphics g) {
        view.draw(g, model);
    }

}
