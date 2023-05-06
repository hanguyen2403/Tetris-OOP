package Controls;

import GUI.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ResetMouse extends MouseHandler {
    private JFrame frame;

    public ResetMouse (Rectangle area, JFrame frame) {
        super(area);
        this.frame = frame;
    }

    @Override
    public void handleMouseEvent(MouseEvent e) {
        startNewGame();
    }
    private void startNewGame () {
        try {
            GameWindow gameWindow = new GameWindow();
            gameWindow.restart();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


