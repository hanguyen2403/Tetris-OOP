/*Name: Group 17
  Nguyễn Khánh Hà - ITCSIU21004
  Huỳnh Lâm Đăng Khoa - ITCSIU21138
  Nguyễn Bình Phương Huy - ITCSIU21189
  Trần Thanh Nguyên - ITCSIU21093
  Purpose: This is a class to control the mouse of starting screen */


package Controls;

import GUI.GameWindow;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class PlayMouse extends JPanel implements MouseListener, MouseMotionListener {
    private BufferedImage backgroundImage,playImage,exitImage;
    JFrame frame;
    private boolean isClicked;
    private Point mousePos = new Point(-1, -1);
    private Rectangle area,area2;
    private int play,exit,state;

    public PlayMouse(Rectangle area, Rectangle area2        ,JFrame jFrame) {
        addMouseListener(this);
        addMouseMotionListener(this);
        this.area=area;
        this.frame=jFrame;
        this.area2=area2;
        state=1;
        play=2;
        exit=3;
        // Load the background image
        try {
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/Startscence/noclickbutton.jpg"));
            playImage=ImageIO.read(getClass().getResourceAsStream("/Startscence/clickPlay.jpg"));
            exitImage=ImageIO.read(getClass().getResourceAsStream("/Startscence/click Exit.jpg"));
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
        } else if (area2.contains(e.getPoint())) {
            System.exit(0);

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
        Point point = e.getPoint();
        if (area.contains(point)) {
            state=play;
            repaint();
        } else if (area2.contains(point)) {
            state=exit;
            repaint();
        } else{
            state=1;
            repaint();
        }
    }
    public void mouseDragged(MouseEvent e) {
        mousePos = e.getPoint();
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image
        if(state==1) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
        if(state==play){
            g.drawImage(playImage, 0, 0, getWidth(), getHeight(), this);
        }
        if(state==exit){
            g.drawImage(exitImage, 0, 0, getWidth(), getHeight(), this);
        }
    }


}
