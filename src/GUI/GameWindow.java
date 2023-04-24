package GUI;

import Variables.Constant;

import javax.swing.*;

public class GameWindow extends JFrame {
    public GameArea gameArea;
    public GameWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle(Constant.TITLE);

        gameArea = new GameArea();
        this.add(gameArea);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);

    }

    //  Thread gameThread;

    public void startGameThread(){
        new GameThread(gameArea).start();
    }
    //UPDATE
    public void Update(){

    }
}
