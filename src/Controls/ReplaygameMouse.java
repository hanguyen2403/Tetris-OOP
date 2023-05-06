package Controls;

import GUI.GameOverFrame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ReplaygameMouse implements MouseListener {
    private GameOverFrame gameOverFrame;

    public ReplaygameMouse(GameOverFrame gameOverFrame) {
        this.gameOverFrame = gameOverFrame;
    }

    public void mouseClicked(MouseEvent e) {
        gameOverFrame.restartGame();
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
