package Controls;

import GUI.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {
    private Rectangle area;
    private boolean isClicked;
    private int mouseX;
    private int mouseY;

    public MouseHandler(Rectangle area) {
        this.area = area;
    }

    public void mousePressed(MouseEvent e) {
        if (area.contains(e.getPoint())) {
            isClicked = true;
            mouseX = e.getX() - area.x;
            mouseY = e.getY() - area.y;
            handleMouseEvent(e.getComponent().getGraphics());
        }
    }

    public void mouseReleased(MouseEvent e) {
        isClicked = false;
        handleMouseEvent(e.getComponent().getGraphics());
    }

    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX() - area.x;
        mouseY = e.getY() - area.y;
        handleMouseEvent(e.getComponent().getGraphics());
    }

    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX() - area.x;
        mouseY = e.getY() - area.y;
        handleMouseEvent(e.getComponent().getGraphics());
    }

    public void handleMouseEvent(Graphics g) {
        // To be overridden by subclasses
    }
}