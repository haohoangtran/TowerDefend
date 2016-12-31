package views;

import controller.Controller;
import models.CellModel;
import models.Model;

import java.awt.*;

/**
 * Created by DUC THANG on 12/17/2016.
 */
public interface View {
    void draw(Graphics g,Model model);
//    private Image image;
//
//    public View(Image image) {
//        this.image = image;
//    }
//
//    public Image getImage() {
//        return image;
//    }
//
//    public void setImage(Image image) {
//        this.image = image;
//    }
//
//    public void draw(Graphics g, Model model) {
//        g.drawImage(image, model.getX(), model.getY(), model.getWidth(), model.getHeight(), null);
//    }
}
