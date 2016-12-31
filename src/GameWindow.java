import controller.scenes.GameScene;
import controller.scenes.MenuScene;
import controller.scenes.SceneListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Stack;
/**
 * Created by DUC THANG on 12/17/2016.
 */
public class GameWindow extends Frame implements Runnable, SceneListener{
    GameScene currenScene;
    BufferedImage backBuffer;
    Stack<GameScene> gameSceneStack;
    public static final int WIDTH=900;
    public static final int HEIGHT=700;
    public GameWindow() {
        gameSceneStack = new Stack<>();
        this.replaceScene(new MenuScene(), false);
        ImageIcon img = new ImageIcon("res/iconGame.png"); //cài icon
        setIconImage(img.getImage());
        setVisible(true);
        setResizable(false);
        setTitle("Mùa đông năm ấy - Amita Team");
        setSize(WIDTH, HEIGHT);
        // cỡ ảnh 930x690
        backBuffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currenScene.mouseClicked(e);

            }

            @Override
            public void mousePressed(MouseEvent e) {
                currenScene.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                currenScene.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    public void replaceScene(GameScene newScene, boolean addToBackStack) {
        if(addToBackStack && currenScene != null) {
            gameSceneStack.push(currenScene);
        }
        currenScene = newScene;
        currenScene.setSceneListener(this);
    }

    public void back() {
        if(!gameSceneStack.isEmpty()) {
            currenScene = gameSceneStack.pop();
        }
    }

    public void update(Graphics g) {

        Graphics backBufferGraphics = backBuffer.getGraphics();
        currenScene.update(backBufferGraphics);
        g.drawImage(backBuffer, 0, 0, WIDTH, HEIGHT, null);
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.repaint();
                Thread.sleep(25);
                currenScene.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
