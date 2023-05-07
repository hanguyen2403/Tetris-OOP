package Controls;

import GUI.GameOverFrame;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ExistMouse implements MouseListener {
    private final GameOverFrame gameOverFrame;

    public ExistMouse(GameOverFrame gameOverFrame) {
        this.gameOverFrame = gameOverFrame;
    }

    public void mouseClicked(MouseEvent e) {
        System.exit(0);
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
