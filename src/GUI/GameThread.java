package GUI;



import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class GameThread extends Thread {
    private GameArea gameArea;
    private static int score;
    public static int goal = 50;
    public static int level = 1;
    public static boolean gameover;
    private static volatile boolean paused = false;
    public static int speed = 1000;


    private JFrame gameAreaFrame;
    public static boolean running;


    public GameThread(GameArea gameArea) {
        this.gameArea = gameArea;
        running = false;

        paused = false;
        gameAreaFrame = (JFrame) SwingUtilities.getRoot(gameArea);
        gameAreaFrame.setFocusable(true);
    }

    @Override
    public void run() {

        while (true) {
            if(speed==0){
                speed=200;
            }
            gameArea.spawnBlock();
            while (gameArea.moveBlockDown()) {
                try {
                    Thread.sleep(speed);

                } catch (InterruptedException e) {
                    Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (gameArea.isBlockOutOfBounds()) {
                gameover = true;
                break;
            }

            gameArea.moveBlockToBackground();
            score += 10 * gameArea.clearLines();
          
            if (score >= goal ) {
                showGameOverScreen();
                break;
            }


        }
        if (score >= goal) {
            level++;
        }else{
            gameAreaFrame.dispose();
            restart();
        }




    }

    public static int getScore() {
        return score;
    }


    public static int getGoal() {
        return goal;
    }

    public static int getLevel() {
        return level;
    }

    public void startGame() {
        if (!running) {
            running = true;
            start();
        }
    }

    public  static void stopGame() {
        running = false;
    }


    public static void setScore() {
        score = 0;
    }

    public static void setGoal(int goals) {
        goal = goals;
    }

    public static void setGameover() {
        gameover = false;
    }

    public static void setLevel(int levels) {
        level = levels;
    }


    private void showGameOverScreen() {
        NextScence gameOverFrame=new NextScence(gameArea,this);
    }

    public void restart() {
        Restart restart = new Restart();
    }
}