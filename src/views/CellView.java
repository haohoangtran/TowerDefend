package views;

import models.CellModel;
import models.Model;

import java.awt.*;

/**
 * Created by tranh on 12/19/2016.
 */
public class CellView {
    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public CellView(Image image) {

        this.image = image;
    }

    public void draw(Graphics g, CellModel model) {
        g.drawImage(image, model.getX(), model.getY(), model.getWidth(), model.getHeight(), null);
    }
}
