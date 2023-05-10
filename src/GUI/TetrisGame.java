package GUI;


import Controls.MouseHandler;
import Controls.PlayMouse;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TetrisGame extends JFrame {
    ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("icon/icon.png"));
    public TetrisGame() {


        PlayMouse panel = new PlayMouse( new Rectangle(320 - 30, 400, 250, 50),new Rectangle(320 - 30, 490, 250, 50),this);
        panel.setPreferredSize(new Dimension(960, 640));

       setVisible(true);
        add(panel, BorderLayout.CENTER);
        pack();
        // Set the content pane layout to null

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setIconImage(logo.getImage());
        setLocationRelativeTo(null);
        // Add the background to the content pane

        setVisible(true);
    }


}
