package GUI;

import java.util.logging.Level;
import java.util.logging.Logger;
public class GameThread extends Thread{
    private GameArea gameArea;
    public int score;

    public int goal;

    public int level;

    public GameThread (GameArea gameArea){
        this.gameArea = gameArea;
    }
    @Override
    public void run() {
        while (true) {
               gameArea.spawnBlock();
            while (gameArea.moveBlockDown()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if(gameArea.isBlockOutOfBounds()) {
                System.out.println("GAME OVER!");
                break;
            }
            gameArea.moveBlockToBackground();
            score += 10*gameArea.clearLines();
        }
    }
}
