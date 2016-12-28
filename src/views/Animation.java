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

//    private Vector<Image> imageVector = new Vector<>();
//    private int index = 0;
//    private int time = 0;
//    private int width = 30;
//    private int height = 30;
//
//    public void setWidth(int width) {
//        this.width = width;
//    }
//
//    public void setHeight(int height) {
//        this.height = height;
//    }
//
//    public Animation(String path) {
//        String[] split = path.split(",");
//        for (int i = 0; i < split.length; i++) {
//            Image image = Utils.loadImage(split[i]);
//            imageVector.add(image);
//        }
//    }
//
//    public void draw(Graphics g, Model model) {
//        if (imageVector != null) {
//            Image image = imageVector.get(index);
//            g.drawImage(image, model.getX(), model.getY(), width, height, null);
//            time++;
//            if (time >10) {
//                time = 0;
//                index++;
//                if (index == imageVector.size()) {
//                    index = 0;
//                }
//            }
//        }
//    }

//    public void drawExplosion(Graphics g, Model model) {
//        if (imageVector != null) {
//            Image image = imageVector.get(index);
//            g.drawImage(image, model.getX(), model.getY(), width, height, null);
//            time++;
//            if (time / 10 == 1) {
//                time = 0;
//                index++;
//                if (index == imageVector.size()) {
//                    imageVector = null;
//                }
//            }
//        }
//    }
}
