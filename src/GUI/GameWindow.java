package GUI;

import Variables.Constant;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GameWindow extends JFrame {
    private GameArea gameArea;

    public GameWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle(Constant.TITLE);

        gameArea = new GameArea();
        this.add(gameArea);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);

        initControls();
    }

    public void startGameThread() {
        new GameThread(gameArea).start();
        if(GameThread.level==6){
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
    public void startLoadedGameThread(int level) {
        GameThread.setLevel(level);
        int goal ;
        int speed;
        switch (level) {
            case 2:
                goal = 100;
                speed=800;
                break;
            case 3:
                goal = 150;
                speed=600;
                break;
            case 4:
                speed=600;
                goal = 400;
                break;
            case 5:
                speed=200;
                goal = 250;
                break;
            default:
                goal=50;
                speed=1000;
                break;
        }
        GameThread.setGoal(goal);
        GameThread.speed=speed;

        gameArea.requestFocus();
       startGameThread();
    }

    public void restart() {
        GameThread.setLevel(1);
        int goal = 50;
        int speed = 1000;

        GameThread.setGoal(goal);
        GameThread.speed = speed;
        GameThread.setScore();

        // Close the current game window
        this.dispose();


        // Remove the game area from the current game window
        this.remove(gameArea);

        // Create a new game area and add it to a new game window
        gameArea = new GameArea();
        this.add(gameArea);

        // Pack the new game window and center it on the screen
        pack();
        setLocationRelativeTo(null);

        // Start a new game thread
        startGameThread();
        // Create a new game window and start a new game thread with level 1
        GameWindow newGame = new GameWindow();
        newGame.startLoadedGameThread(1);

    }
    public void closeWindow() {
        // Dispose of the JFrame to close the window
        dispose();
    }
}