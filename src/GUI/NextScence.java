package GUI;

import Controls.NextMouse;
import Variables.Constant;

import javax.swing.*;
import java.awt.*;

public class NextScence extends JFrame {
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
        setTitle(Constant.TITLE);
        setIconImage(logo.getImage());
        setLocationRelativeTo(null);


    }
    public void restartGame() {

        int level = gameThread.getLevel();
        int goal=50*level;
        int speed=1000-200*level;
        if(speed<300){
            speed=300;
        }

        GameThread.speed=speed;
        gameThread.setScore();
        gameThread.setGoal(goal);
        gameThread.setGameover();
        gameArea.clear();
        this.dispose();
        gameThread.stopGame();
        gameThread = new GameThread(gameArea);
        gameThread.startGame();
    }




}
