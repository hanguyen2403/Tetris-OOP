package GUI;

import Controls.NextMouse;
import Controls.SoundManager;
import Variables.Constant;

import javax.swing.*;
import java.awt.*;

public class NextScence extends JFrame {
    SoundManager sound = new SoundManager("src/resources/Sound/Victory.wav");
    GameArea gameArea;
    GameThread gameThread;
    ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("icon/icon.png"));

    public NextScence(GameArea gameArea, GameThread gameThread) {
        this.gameArea=gameArea;
        this.gameThread=gameThread;

        NextMouse panel = new NextMouse(this, new Rectangle(320 - 70, 400, 550, 50),new Rectangle(320 - 70, 490, 250+150+150, 50));
        panel.setPreferredSize(new Dimension(960, 640));

        setVisible(true);
        add(panel, BorderLayout.CENTER);
        pack();
        // Set the content pane layout to null

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sound.playSound("src/resources/Sound/Victory.wav");
        setResizable(false);
        setTitle(Constant.TITLE);
        setIconImage(logo.getImage());
        setLocationRelativeTo(null);


    }
    public void NextGame() {
        int currentLevel = gameThread.getLevel();
        int nextLevel = currentLevel + 1;
        int goal = 50 * nextLevel;
        int speed = 1000 - 200 * currentLevel;
        if (speed < 300) {
            speed = 300;
        }
        this.dispose();

        gameThread.stopGame();
        gameThread.interrupt(); // Interrupt the thread to exit any sleeping states
        try {
            gameThread.join(); // Wait for the thread to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        gameThread = new GameThread(gameArea);
        gameThread.setSpeed(speed);
        gameThread.setScore();
        gameThread.setLevel(nextLevel);
        gameThread.setGoal(goal);
        gameThread.setGameover();
        gameArea.clear();
        gameArea.requestFocus();
        gameThread.startGame();
    }





}
