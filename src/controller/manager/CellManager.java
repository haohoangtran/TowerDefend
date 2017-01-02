package controller.manager;

import controller.BaseController;
import controller.CellController;
import controller.towers.TowerController;
import utils.AnimationManager;
import views.Animation;
import views.View;

import java.awt.*;
import java.io.*;
import java.util.HashSet;
import java.util.Vector;

/**
 * Created by tranh on 12/19/2016.
 */
public class CellManager implements BaseController {
    public  static Vector<CellController> cellControllers=new Vector<>();
    int[] road;
    int[] build;


    public static final CellManager instance = new CellManager();

    private CellManager() {
        createCell();
    }

    private void createCell() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("res/Save/Map.txt")));
            String str = bufferedReader.readLine();
            String[] strNumber = str.split(",");
            Vector<Integer> integers = new Vector<>();
            for (int i = 0; i < strNumber.length; i++) {
                integers.add(Integer.valueOf(strNumber[i]));
            }
            road = new int[integers.size()];
            for (int i = 0; i < integers.size(); i++) {
                road[i] = integers.get(i);
            }
            integers.clear();
            str=bufferedReader.readLine();
            strNumber=str.split(",");
            for (int i = 0; i < strNumber.length; i++) {
                integers.add(Integer.valueOf(strNumber[i]));
            }
            build = new int[integers.size()];
            for (int i = 0; i < integers.size(); i++) {
                build[i] = integers.get(i);
            }

            int posX = 10;
            int posY = 160;//60+35 toa do da x la 35
            while (posX < 900) {
                cellControllers.add(CellController.createCell(posX, 100));
                posX += 60;
            }
            int size = cellControllers.size();
            for (int i = 0; i < size; i++) {
                while (posY < 650) {
                    this.cellControllers.add(CellController.createCell(cellControllers.get(i).getModel().getX(), posY));
                    posY += 60;
                }
                posY = 160;
            }
            for (int i = 0; i < road.length; i++) {
                cellControllers.get(road[i]).getModel().setRoad(true);
            }
            for (int i = 0; i < build.length; i++) {
                cellControllers.get(build[i]).getModel().setCanBuild(true);
            }
            for (CellController cellController : cellControllers) {
                if (!cellController.getModel().isCanBuild() && !cellController.getModel().isRoad()) {
                    cellController.getModel().setUtil(true);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add(CellController c) {
        this.cellControllers.add(c);
    }

    public void remove(CellController c) {
        this.cellControllers.remove(c);
    }

    @Override
    public void run() {

    }

    public void reset() {
        for (int i = 0; i < cellControllers.size(); i++) {
            cellControllers.get(i).setTowerController(null);
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < cellControllers.size(); i++) {
            if (!cellControllers.get(i).getModel().isUtil()&&!cellControllers.get(i).getModel().isRoad())
                cellControllers.get(i).draw(g);
        }
    }

    @Override
    public void checkContact() {

    }

    public static CellController findCell(int x, int y) {
        for (int i = 0; i < cellControllers.size(); i++) {
            if (x >= cellControllers.get(i).getModel().getX() && x <= cellControllers.get(i).getModel().getRight() &&
                    y <= cellControllers.get(i).getModel().getBottom() && y >= cellControllers.get(i).getModel().getY()) {
                return cellControllers.get(i);
            }
        }
        return null;
    }
    public static int findPosTower(TowerController towerController){
        for (int i = 0; i < cellControllers.size(); i++) {
            if(cellControllers.get(i).getTowerController()==towerController){
                return i;
            }
        }
        return -1;
    }

    public void drawPos(Graphics g) {
        for (int i = 0; i < cellControllers.size(); i++) {
            cellControllers.get(i).drawPos(g, i);
        }
    }
}
