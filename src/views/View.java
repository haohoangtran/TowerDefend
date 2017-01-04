package views;

import controller.Controller;
import models.CellModel;
import models.Model;

import java.awt.*;

/**
 * Created by HieuIt on 12/17/2016.
 */
public interface View {
    void draw(Graphics g,Model model);
    boolean isAnimationReachEnd();
}
