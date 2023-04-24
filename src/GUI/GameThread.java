package GUI;

public class GameThread extends Thread{
    private GameArea gameArea;

    public GameThread (GameArea gameArea){
        this.gameArea = gameArea;
    }
    public void run() {
        while (true) {

            try {
                gameArea.moveBlockDown();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
