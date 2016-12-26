package controller;

import controller.towers.TowerController;
import models.CellModel;

import java.awt.*;

/**
 * Created by tranh on 12/19/2016.
 */
public class CellController {
    private CellModel model;
    TowerController towerController;

    public TowerController getTowerController() {
        return towerController;
    }

    public void setTowerController(TowerController towerController) {
        this.towerController = towerController;
    }

    public CellController(CellModel model) {
        this.model = model;
    }

    public CellModel getModel() {
        return model;
    }

    public void run() {

    }

    public void drawView(Graphics g) {
        if (model.isRoad())
            g.setColor(Color.red);
        else if (model.isCanBuild())
            g.setColor(Color.green);
        else if (model.isCreate())
            return;
        else
            g.setColor(Color.BLUE);
        g.drawRect(model.getX(), model.getY(), model.getWidth(), model.getHeight());
    }
    public void drawPos(Graphics g,int i){
        g.drawString(i+"",model.getX()+10,model.getY()+10);
    }


    public void drawTower(Graphics g) {
        //view.draw(g, model);
        if(towerController!=null)
        towerController.drawView(g);
    }

    public static CellController createCell(int x, int y) {
        return new CellController(new CellModel(x, y, 50, 50));
    }
    public static CellController createCellBottom(int x,int y){
        return new CellController(new CellModel(x,y,100,100));
    }
    public void drawText(Graphics g,String s){
        g.setColor(Color.red);
        Font font=new Font("Times new Roman",Font.BOLD,40);
        g.setFont(font);
        System.out.println("Ve text");
        g.drawString(s,20,700);
    }

}
