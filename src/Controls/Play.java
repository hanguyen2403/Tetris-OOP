package Controls;



import GUI.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Play extends MouseHandler {
    private JFrame frame;

    public Play(Rectangle area, JFrame frame) {
        super(area);
        this.frame = frame;
    }

    @Override
    public void handleMouseEvent(MouseEvent e) {
        frame.dispose();
        startNewGame();
    }
    private void startNewGame () {
        try {
            GameWindow gameWindow = new GameWindow();
            gameWindow.startGameThread();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

