package GUI;

import Variables.Constant;

import javax.swing.*;

public class GameWindow extends JFrame {
    public GameWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle(Constant.TITLE);

        GameArea gameArea = new GameArea();
        add(gameArea);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    Thread gameThread;

    public void startGameThread(){
        new GameThread().start();
    }


    //UPDATE
    public void Update(){

    }

}
