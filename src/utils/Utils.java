package utils;

import controller.enemies.EnemyController;
import controller.enemies.EnemyType;
import models.CheckPoint;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import static controller.enemies.EnemyType.*;
import static controller.towers.TowerType.NORMAL;

/**
 * Created by DUC THANG on 12/17/2016.
 */
public class Utils {
    public static Point point = new Point();

    public static BufferedImage loadImage(String url) {
        try {
            return ImageIO.read(new File(url));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String pathImageEnemy(EnemyType enemyType) {
        switch (enemyType) {
            case NORMAL:
                return "res/Enemy/bahamut/bahamut.png";
            case SPEED:
                return "res/Enemy/leviathan/leviathan.png";
            case FLY:
                return "res/Enemy/phoenix/phoenix.png";
            case HORSE:
                return "res/Enemy/odin/odin.png";
            case TANK:
                return "res/Enemy/ifrit/ifrit.png";
        }
        return null;
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


    public static void playSound(String audioUrl, boolean repeat) {

        File soundFile = new File(audioUrl);
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            if (repeat) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clip.loop(0);
            }
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static Vector<BufferedImage> loadSheetEnemy(EnemyType enemyType, int colum) {
        String URL = pathImageEnemy(enemyType);
        Vector<BufferedImage> imageVector = new Vector<>();
        BufferedImage image = Utils.loadImage(URL);

        switch (enemyType) {
            case NORMAL:
            case SPEED:
            case FLY:
                for (int i = 0; i < 4; i++) {
                    int x = i * 96;
                    int y = 96 * colum;
                    BufferedImage subImage = image.getSubimage(x, y, 96, 96);
                    imageVector.add(subImage);
                }
                break;
            case HORSE:
            case TANK:
                for (int i = 0; i < 4; i++) {
                    int x = i * 80;
                    int y = 80 * colum;
                    BufferedImage subImage = image.getSubimage(x, y, 80, 80);
                    imageVector.add(subImage);
                }
                break;
        }


        return imageVector;
    }

    public static void getLocation(int x, int y) {
        point = new Point(x, y);
    }
}
