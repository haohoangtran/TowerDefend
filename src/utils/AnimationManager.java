package utils;

import controller.enemies.EnemyType;
import views.Animation;

/**
 * Created by tranh on 25/12/2016.
 */
public class AnimationManager {
    //normal
    public static final Animation normalDown =
            new Animation(Utils.loadSheetEnemy(EnemyType.NORMAL, 0));

    public static final Animation normalLeft =
            new Animation(Utils.loadSheetEnemy(EnemyType.NORMAL, 1));

    public static final Animation normalRight =
            new Animation(Utils.loadSheetEnemy(EnemyType.NORMAL, 2));

    public static final Animation normalUp =
            new Animation(Utils.loadSheetEnemy(EnemyType.NORMAL, 3));

    //speed
    public static final Animation speedDown =
            new Animation(Utils.loadSheetEnemy(EnemyType.SPEED, 0));

    public static final Animation speedLeft =
            new Animation(Utils.loadSheetEnemy(EnemyType.SPEED, 1));

    public static final Animation speedRight =
            new Animation(Utils.loadSheetEnemy(EnemyType.SPEED, 2));

    public static final Animation speedUp =
            new Animation(Utils.loadSheetEnemy(EnemyType.SPEED, 3));
    //tank
    public static final Animation tankDown =
            new Animation(Utils.loadSheetEnemy(EnemyType.TANK, 0));

    public static final Animation tankLeft =
            new Animation(Utils.loadSheetEnemy(EnemyType.TANK, 1));

    public static final Animation tankRight =
            new Animation(Utils.loadSheetEnemy(EnemyType.TANK, 2));

    public static final Animation tankUp =
            new Animation(Utils.loadSheetEnemy(EnemyType.TANK, 3));

    //horse
    public static final Animation horseDown =
            new Animation(Utils.loadSheetEnemy(EnemyType.HORSE, 0));

    public static final Animation horseLeft =
            new Animation(Utils.loadSheetEnemy(EnemyType.HORSE, 1));

    public static final Animation horseRight =
            new Animation(Utils.loadSheetEnemy(EnemyType.HORSE, 2));

    public static final Animation horseUp =
            new Animation(Utils.loadSheetEnemy(EnemyType.HORSE, 3));

    //fly
    public static final Animation flyDown =
            new Animation(Utils.loadSheetEnemy(EnemyType.FLY, 0));

    public static final Animation flyLeft =
            new Animation(Utils.loadSheetEnemy(EnemyType.FLY, 1));

    public static final Animation flyRight =
            new Animation(Utils.loadSheetEnemy(EnemyType.FLY, 2));

    public static final Animation flyUp =
            new Animation(Utils.loadSheetEnemy(EnemyType.FLY, 3));
    //windwill
    public static final Animation windwill = new Animation(Utils.realIInFoder("res/windwill"));

    //Bot
    public static final Animation botDown = new Animation(
            Utils.loadSheetEnemy(EnemyType.BOT, 0));
    public static final Animation botLeft = new Animation(
            Utils.loadSheetEnemy(EnemyType.BOT, 1));
    public static final Animation botRight = new Animation(
            Utils.loadSheetEnemy(EnemyType.BOT, 2));
    public static final Animation botUp = new Animation(
            Utils.loadSheetEnemy(EnemyType.BOT, 3));
}
