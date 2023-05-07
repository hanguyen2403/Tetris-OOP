package GUI;


import Controls.MouseHandler;
import Controls.PlayMouse;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TetrisGame extends JFrame {

    public TetrisGame() {

        PlayMouse panel = new PlayMouse( new Rectangle(320 - 30, 400, 150, 120),this);
        panel.setPreferredSize(new Dimension(960, 640));

       setVisible(true);
        add(panel, BorderLayout.CENTER);
        pack();
        // Set the content pane layout to null

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Add the background to the content pane

        setVisible(true);
    }


}