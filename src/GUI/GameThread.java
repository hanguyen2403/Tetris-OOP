package GUI;

public class GameThread extends Thread{
    public void run() {
       while (true) {
           System.out.println("Loop");
       }
    }
}