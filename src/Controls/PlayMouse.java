package Controls;

import GUI.GameWindow;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class PlayMouse extends JPanel implements MouseListener, MouseMotionListener {
    private BufferedImage backgroundImage;
    JFrame frame;
    private boolean isClicked;
    private Point mousePos = new Point(-1, -1);
    private Rectangle area;

    public PlayMouse(Rectangle area,JFrame jFrame) {
        addMouseListener(this);
        addMouseMotionListener(this);
        this.area=area;
this.frame=jFrame;
        // Load the background image
        try {
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/block/Picture.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mouseClicked(MouseEvent e) {
        // Do nothing
    }

    public void mousePressed(MouseEvent e) {
        if (area.contains(e.getPoint())) {
            handleMouseEvent(e);
        }else{
            System.exit(1);
        }
    }
    public void handleMouseEvent(MouseEvent e) {
        frame.dispose();
        startNewGame();
    }
    private void startNewGame() {
        try {
            GameWindow gameWindow = new GameWindow();
            gameWindow.startGameThread();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void mouseReleased(MouseEvent e) {
        isClicked = false;
        repaint();
    }

    public void mouseEntered(MouseEvent e) {
        // Do nothing
    }

    public void mouseExited(MouseEvent e) {
        // Do nothing
    }

    public void mouseMoved(MouseEvent e) {
        mousePos = e.getPoint();
        repaint();
    }

    public void mouseDragged(MouseEvent e) {
        mousePos = e.getPoint();
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        // Draw the circle
        int radius = 40;
        int diameter = radius * 2;
        int x = mousePos.x - radius;
        int y = mousePos.y - radius;

        if (isClicked) {
            g.setColor(Color.CYAN);
            g.fillOval(x, y, diameter, diameter);
        } else {
            g.setColor(new Color(255, 255, 200, 200));
            g.fillOval(x, y, diameter, diameter);
        }
    }


}