package Main;

import GUI.GameWindow;
import GUI.GameArea;
import Variables.Constant;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
       /* JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle(Constant.TITLE);

        GameArea gameArea = new GameArea();
        window.add(gameArea);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);*/
        new GameWindow();
    }
}