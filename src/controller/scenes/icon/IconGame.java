package controller.scenes.icon;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by DUC THANG on 12/31/2016.
 */
public interface IconGame {
    public void update(Graphics g);
    public void run();
    public void mouseClicked(MouseEvent e);
    public boolean checkMouse();
}
