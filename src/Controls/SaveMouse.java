package Controls;
import GUI.GameOverFrame;
import GUI.GameThread;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileWriter;
import java.io.IOException;

public class SaveMouse implements MouseListener {
    private GameOverFrame gameOverFrame;

    public SaveMouse(GameOverFrame gameOverFrame) {
        this.gameOverFrame = gameOverFrame;
    }

    public void mouseClicked(MouseEvent e) {
        gameOverFrame.saveGame();
    }


    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

}
