package Controls;



import java.awt.*;
import java.awt.event.MouseEvent;

public class ExitMouseHandler extends MouseHandler {

    private Rectangle bounds;

    public ExitMouseHandler(Rectangle bounds) {
        super(bounds);
        this.bounds = bounds;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point clickPoint = e.getPoint();
        if (bounds.contains(clickPoint)) {
            System.exit(0);
        }
    }
}