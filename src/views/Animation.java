package views;

import controller.Controller;
import models.CheckPoint;
import models.Model;
import utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * Created by Hieu It on 12/17/2016.
 */
public class Animation implements View{

    private Vector<BufferedImage> images;
    private int imageCount;
    private int count;

    private boolean animationReachEnd = false;

    public boolean isAnimationReachEnd() {
        return animationReachEnd;
    }

    public Animation(Vector<BufferedImage> images) {
        this.images = images;
    }

    public Animation(String path) {
        this.images = new Vector<>();
        String[] split = path.split(",");
        for (int i = 0; i < split.length; i++) {
            BufferedImage image = Utils.loadImage(split[i]);
            images.add(image);
        }
    }

    @Override
    public void draw(Graphics g, Model model) {

        BufferedImage image = images.get(imageCount);
        g.drawImage(image,
                model.getX(),
                model.getY(),
                model.getWidth(),
                model.getHeight(),
                null);
        count++;
        if (count > 8) {
            count = 0;
            imageCount++;
            if (imageCount > images.size() - 1) {
                imageCount = 0;
                animationReachEnd =true;
            }
        }

    }
    public void draw(Graphics g, Model model,int speed){

        BufferedImage image = images.get(imageCount);
        g.drawImage(image,
                model.getX(),
                model.getY(),
                model.getWidth(),
                model.getHeight(),
                null);
        count++;
        if (count > speed) {
            count = 0;
            imageCount++;
            if (imageCount > images.size() - 1) {
                imageCount = 0;
                animationReachEnd =true;
            }
        }
    }

}
