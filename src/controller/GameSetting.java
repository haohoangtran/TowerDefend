package controller;

/**
 * Created by DUC THANG on 1/2/2017.
 */
public class GameSetting {
    private  int width;
    private int height;

    public static final GameSetting instance = new GameSetting(800, 600);

    private GameSetting(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
