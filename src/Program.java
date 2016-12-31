/**
 * Created by DUC THANG on 12/17/2016.
 */
public class Program {
    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        Thread thread = new Thread(gameWindow);
        thread.run();
        try {
            thread.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
