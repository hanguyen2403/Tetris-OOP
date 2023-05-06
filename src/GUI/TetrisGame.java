package GUI;

import Controls.ExitMouseHandler;
import Controls.MouseHandler;
import Controls.PrintMouseHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TetrisGame extends JFrame {

    public TetrisGame() {
        JPanel centterPanel = new JPanel();
        JPanel southPanel=new JPanel();
        centterPanel.setBackground(Color.WHITE);
        setSize(960, 640);



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        try {
            // Load the background image
            Image backgroundImage = ImageIO.read(getClass().getResourceAsStream("/block/Picture.png"));
            ImageIcon imageIcon = new ImageIcon(backgroundImage.getScaledInstance(960, 640, Image.SCALE_SMOOTH));
            JLabel imageLabel = new JLabel(imageIcon);
            centterPanel.add(imageLabel);

            MouseHandler mouseHandler = new PrintMouseHandler(new Rectangle(320 - 30, 400, 50, 50),this);
            MouseHandler exitMouseHandler = new ExitMouseHandler(new Rectangle(320-30, 480, 50, 50));

            centterPanel.addMouseListener(mouseHandler);
        centterPanel.addMouseListener(exitMouseHandler);



        } catch (IOException e) {
            e.printStackTrace();
        }


        // Set the content pane layout to null
        add(centterPanel,BorderLayout.CENTER);


        // Add the background to the content pane

        setVisible(true);
    }


}