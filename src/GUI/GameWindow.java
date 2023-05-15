package GUI;

import Variables.Constant;

import Variables.Constant;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import Controls.SoundManager;

public class GameWindow extends JFrame {
    SoundManager sound = new SoundManager("src/resources/Sound/start.wav");
    private GameArea gameArea;
    GameThread gameThread;
    ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("icon/icon.png"));

    public GameWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sound.playSound("src/resources/Sound/start.wav");
        setResizable(false);
        setTitle(Constant.TITLE);
        setIconImage(logo.getImage());

        gameArea = new GameArea();
        this.add(gameArea);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);

        initControls();
    }

    public void startGameThread() {
        new GameThread(gameArea).start();
        if (GameThread.level == 6) {
            this.dispose();
        }

    }

    public void initControls() {
        InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getRootPane().getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "space");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, 0), "c");

        actionMap.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.moveBlockRight();
            }
        });
        actionMap.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.moveBlockLeft();
            }
        });
        actionMap.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.moveBlockDownFaster();
            }
        });
        actionMap.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.RotateBlock();
            }
        });
        actionMap.put("space", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.dropBlock();
            }
        });
        actionMap.put("c", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.ChangeBlock();
            }
        });

        this.requestFocus();
    }


    public void restart() {
        GameThread.setLevel(1);
        int goal = 50;
        int speed = 1000;


        GameThread.setGoal(goal);
        GameThread.speed = speed;
        GameThread.setScore();
        gameArea.requestFocus();
        startGameThread();
    }

    public void nextGame() {

        int level = gameThread.getLevel();
        int goal=50*level;
        int speed;
        if(level<5) {
            speed = 1000 - 200 * level;
        }else{
            speed=200;
        }
        GameThread.setGoal(goal);
        GameThread.speed = speed;
        GameThread.setScore();
        gameArea.requestFocus();
        startGameThread();
    }
}
