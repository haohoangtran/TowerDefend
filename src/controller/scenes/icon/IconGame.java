package controller.scenes.icon;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by DUC THANG on 12/31/2016.
 */
public interface IconGame {
    void update(Graphics g);
    void run();
    void mouseClicked(MouseEvent e);
    boolean checkMouse();
}
