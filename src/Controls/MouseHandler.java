package Controls;

import GUI.GameWindow;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {
    private Rectangle area;

    public MouseHandler(Rectangle area) {
        this.area = area;
    }

    public void mousePressed(MouseEvent e) {
        if (area.contains(e.getPoint())) {
            handleMouseEvent(e);
        }
    }

    public void handleMouseEvent(MouseEvent e) {
        // To be overridden by subclasses
    }

}
