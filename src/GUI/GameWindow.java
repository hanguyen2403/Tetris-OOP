package GUI;

import Variables.Constant;

import javax.swing.*;
import java.awt.event.ActionEvent;

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

        initControls();
    }

    //  Thread gameThread;

    public void startGameThread(){
        new GameThread(gameArea).start();
    }

    public void initControls(){
        InputMap inputMap = this.getRootPane().getInputMap();
        ActionMap actionMap = this.getRootPane().getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "left");
        inputMap.put(KeyStroke.getKeyStroke("UP"), "up");
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "down");
        inputMap.put(KeyStroke.getKeyStroke("SPACE"), "space");

     //   inputMap.put(KeyStroke.getKeyStroke(""));



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


    }


}
