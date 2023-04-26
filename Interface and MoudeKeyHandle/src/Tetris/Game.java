package Tetris;

import java.awt.*;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game {

    private JFrame frame;
    private JButton playButton;
    private JButton exitButton;
    private Window window;

    public Game() {
        frame = new JFrame();

        // Set up top panel with PNG file
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        JPanel topPanel1 = new JPanel();
        topPanel1.setBackground(Color.WHITE);
        try {
            Image image =ImageIO.read(getClass().getResourceAsStream("/Image/Picture.png"));
            Image image1 =ImageIO.read(getClass().getResourceAsStream("/Image/images.png"));
            ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(500, 300, Image.SCALE_SMOOTH));
            JLabel imageLabel = new JLabel(imageIcon);
            ImageIcon imageIcon1 = new ImageIcon(image1.getScaledInstance(500, 300, Image.SCALE_SMOOTH));
            JLabel imageLabel1 = new JLabel(imageIcon1);
            topPanel.add(imageLabel);
            topPanel1.add(imageLabel1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set up bottom panel with buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2, 10, 0));
        playButton = new JButton("Play");
        exitButton = new JButton("Exit");
        Font font = new Font("Arial", Font.BOLD, 20);
        playButton.setFont(font);
        exitButton.setFont(font);
        playButton.setBackground(Color.GREEN);
        exitButton.setBackground(Color.RED);
        playButton.setPreferredSize(new Dimension(100, 50));
        exitButton.setPreferredSize(new Dimension(100, 50));
        playButton.addActionListener(new MouseKeyHandler());
        exitButton.addActionListener(new MouseKeyHandler());
        bottomPanel.add(playButton);
        bottomPanel.add(exitButton);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add panels to frame
        frame.setLayout(new BorderLayout());
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(topPanel1,BorderLayout.CENTER);

        // Set up frame
        frame.setTitle("Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 800);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private class MouseKeyHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == playButton) {
                // Handle Play button click
                frame.dispose();
              window=  new Tetris.Window();
            } else if (e.getSource() == exitButton) {
                // Handle Exit button click
                frame.dispose();
            }
        }

    }

    public static void main(String[] args) {
        new Game();
    }

}
