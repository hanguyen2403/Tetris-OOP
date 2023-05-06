package Controls;



import GUI.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class PrintMouseHandler extends MouseHandler {
    private JFrame frame;

    public PrintMouseHandler(Rectangle area,JFrame frame) {
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

