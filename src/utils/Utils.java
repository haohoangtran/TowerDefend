package utils;

import controller.HouseController;
import controller.enemies.EnemyManager;
import controller.enemies.EnemyType;
import controller.gifts.TotalCoin;
import controller.manager.BodyManager;
import controller.manager.CellManager;
import controller.scenes.PlayGameScene;
import controller.towers.TowerManager;
import models.CheckPoint;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Vector;

/**
 * Created by DUC THANG on 12/17/2016.
 */
public class Utils {
    public static Point point = new Point();
    public static Clip clip = readFile("res/sound/nennen.wav");

    public static BufferedImage loadImage(String url) {
        try {
            return ImageIO.read(new File(url));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Vector<String> createFactory(String path) {

        try {
            Vector<String> vector = new Vector<>();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String str;
            while ((str=bufferedReader.readLine())!=null){
                vector.add(str);
            }
            return vector;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public static Vector<BufferedImage> loadSheet(String URL,int witdh,int height,int border,int imgeCount){
        Vector<BufferedImage> imageVector = new Vector<>();
        BufferedImage image = Utils.loadImage(URL);
        for (int i = 0; i < imgeCount; i++) {
            int x =i*witdh+border*(i+1);
            int y = border;
            BufferedImage subImage = image.getSubimage(x,y,witdh,height);
            imageVector.add(subImage);
        }

        return imageVector;
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
            case BOT:
                return "res/Enemy/ifrit/ifrit.png";
        }
        return null;
    }

    public static CheckPoint[] createCheckpoint() {
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader("res/CheckPoint/lv1.txt"));

            CheckPoint[] checkPoints = new CheckPoint[Integer.valueOf(bufferedReader.readLine())];
            for (int i = 0; i < checkPoints.length; i++) {
                String[] str=bufferedReader.readLine().split(" ");
                checkPoints[i]=new CheckPoint(Integer.valueOf(str[0]),Integer.valueOf(str[1]));
            }
            return checkPoints;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        }

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

    public static Clip readFile(String path) {
        File sound=new File(path);
        try {
            AudioInputStream inputStream=AudioSystem.getAudioInputStream(sound);
            Clip clip=AudioSystem.getClip();
            clip.open(inputStream);
            return clip;
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void openSound() {
        AudioInputStream inputStream;
        try {
            inputStream = AudioSystem.getAudioInputStream(new File("res/sound/nennen.wav"));
            clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.start();
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
            case BOT:

        }
        return imageVector;
    }

    public static void getLocation(int x, int y) {
        point = new Point(x, y);
    }

    public static Vector<BufferedImage> realIInFoder(String path) {
        try {
            Vector<BufferedImage> bufferedImages = new Vector<>();
            File forder = new File(path);
            if (forder.isDirectory()) {
                for (File file : forder.listFiles()) {
                    bufferedImages.add(ImageIO.read(file));
                }
                return bufferedImages;
            } else
                return null;
        } catch (IOException e) {
            return null;
        }
    }

    public static void reset() {
        clip.close();
        TotalCoin.instance.setCoin(500);
        HouseController.instance = HouseController.createHpFull(830, 325);
        BodyManager.instance.setBodies(new Vector<>());
        BodyManager.instance.register(HouseController.instance);
        BodyManager.instance.register(TotalCoin.instance);
        EnemyManager.instance.removeAll();
        PlayGameScene.second = 0;
        PlayGameScene.timeCount = 0;
        CellManager.instance.reset();
        TowerManager.instance.setTowerControllers(new Vector<>());
    }

    public static void openWebpage(String urlString) {
        try {
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
