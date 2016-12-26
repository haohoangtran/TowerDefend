package utils;

import controller.Body;
import controller.EnemyController;
import controller.TowerController;
import models.CheckPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by DUC THANG on 12/17/2016.
 */
public class Utils {
    public static Vector<EnemyController> enemyControllers = new Vector<>();

    public static Image loadImage(String url) {
        try {
            Image image = ImageIO.read(new File(url));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static CheckPoint[] createCheckpoint() {
        CheckPoint[] checkPoints = new CheckPoint[6];
        checkPoints[0] = new CheckPoint(8, 280);
        checkPoints[1] = new CheckPoint(322, 295);
        checkPoints[2] = new CheckPoint(330, 582);
        checkPoints[3] = new CheckPoint(720, 602);
        checkPoints[4] = new CheckPoint(735, 425);
        checkPoints[5] = new CheckPoint(907, 445);
        return checkPoints;
    }

    public static void register(EnemyController enemyController) {
        enemyControllers.add(enemyController);
    }


}
