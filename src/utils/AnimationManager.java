package utils;

<<<<<<< HEAD
import controller.enemies.EnemyType;
=======
>>>>>>> 70f79ee73bb5f3079ae53a6e3096c1eee75466b3
import views.Animation;

/**
 * Created by tranh on 25/12/2016.
 */
public class AnimationManager {
    //normal
<<<<<<< HEAD
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
=======
    public static final Animation normalRight =
            new Animation("res/Enemy/bahamut/bahamutRight1.png,res/Enemy/bahamut/bahamutRight2.png,res/Enemy/bahamut/bahamutRight3.png,res/Enemy/bahamut/bahamutRight4.png");
    public static final Animation normalDown =
            new Animation("res/Enemy/bahamut/bahamutDown1.png,res/Enemy/bahamut/bahamutDown2.png,res/Enemy/bahamut/bahamutDown3.png,res/Enemy/bahamut/bahamutDown4.png");
    public static final Animation normalUp =
            new Animation("res/Enemy/bahamut/bahamutUp1.png,res/Enemy/bahamut/bahamutUp2.png,res/Enemy/bahamut/bahamutUp3.png,res/Enemy/bahamut/bahamutUp4.png");
    public static final Animation normalLeft =
            new Animation("res/Enemy/bahamut/bahamutLeft1.png,res/Enemy/bahamut/bahamutLeft2.png,res/Enemy/bahamut/bahamutLeft3.png,res/Enemy/bahamut/bahamutLeft4.png");
    //speed
    public static final Animation speedRight =
            new Animation("res/Enemy/leviathan/right1.png,res/Enemy/leviathan/right2.png,res/Enemy/leviathan/right3.png,res/Enemy/leviathan/right4.png");
    public static final Animation speedUp =
            new Animation("res/Enemy/leviathan/up1.png,res/Enemy/leviathan/up2.png,res/Enemy/leviathan/up3.png,res/Enemy/leviathan/up4.png");
    public static final Animation speedLeft =
            new Animation("res/Enemy/leviathan/left1.png,res/Enemy/leviathan/left2.png,res/Enemy/leviathan/left3.png,res/Enemy/leviathan/left4.png");
    public static final Animation speedDown =
            new Animation("res/Enemy/leviathan/down1.png,res/Enemy/leviathan/down2.png,res/Enemy/leviathan/down3.png,res/Enemy/leviathan/down4.png");
    //tank
    public static final Animation tankRight =
            new Animation("res/Enemy/ifrit/right1.png,res/Enemy/ifrit/right2.png,res/Enemy/ifrit/right3.png,res/Enemy/ifrit/right4.png");
    public static final Animation tankUp =
            new Animation("res/Enemy/ifrit/up1.png,res/Enemy/ifrit/up2.png,res/Enemy/ifrit/up3.png,res/Enemy/ifrit/up4.png");
    public static final Animation tankLeft =
            new Animation("res/Enemy/ifrit/left1.png,res/Enemy/ifrit/left2.png,res/Enemy/ifrit/left3.png,res/Enemy/ifrit/left4.png");
    public static final Animation tankDown =
            new Animation("res/Enemy/ifrit/down1.png,res/Enemy/ifrit/down2.png,res/Enemy/ifrit/down3.png,res/Enemy/ifrit/down4.png");
    //horse
    public static final Animation horseRight =
            new Animation("res/Enemy/odin/right1.png,res/Enemy/odin/right2.png,res/Enemy/odin/right3.png,res/Enemy/odin/right4.png");
    public static final Animation horseUp =
            new Animation("res/Enemy/odin/up1.png,res/Enemy/odin/up2.png,res/Enemy/odin/up3.png,res/Enemy/odin/up4.png");
    public static final Animation horseLeft =
            new Animation("res/Enemy/odin/left1.png,res/Enemy/odin/left2.png,res/Enemy/odin/left3.png,res/Enemy/odin/left4.png");
    public static final Animation horseDown =
            new Animation("res/Enemy/odin/down1.png,res/Enemy/odin/down2.png,res/Enemy/odin/down3.png,res/Enemy/odin/down4.png");
    //fly
    public static final Animation flyRight =
            new Animation("res/Enemy/phoenix/right1.png,res/Enemy/phoenix/right2.png,res/Enemy/phoenix/right3.png,res/Enemy/phoenix/right4.png");
    public static final Animation flyUp =
            new Animation("res/Enemy/phoenix/up1.png,res/Enemy/phoenix/up2.png,res/Enemy/phoenix/up3.png,res/Enemy/phoenix/up4.png");
    public static final Animation flyLeft =
            new Animation("res/Enemy/phoenix/left1.png,res/Enemy/phoenix/left2.png,res/Enemy/phoenix/left3.png,res/Enemy/phoenix/left4.png");
    public static final Animation flyDown =
            new Animation("res/Enemy/phoenix/down1.png,res/Enemy/phoenix/down2.png,res/Enemy/phoenix/down3.png,res/Enemy/phoenix/down4.png");




>>>>>>> 70f79ee73bb5f3079ae53a6e3096c1eee75466b3
}
