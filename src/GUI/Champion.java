package GUI;
import Controls.ResetMouse;
import javax.swing.*;
import java.awt.*;
public class Champion extends JFrame {
    public Champion() {

        ResetMouse panel = new ResetMouse( new Rectangle(320 - 30, 400, 250, 50),new Rectangle(320 - 30, 490, 250, 50),this);
        panel.setPreferredSize(new Dimension(960, 640));

        setVisible(true);
        add(panel, BorderLayout.CENTER);
        pack();
        // Set the content pane layout to null

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }
}


