package GUI;



import Variables.Constant;

import java.awt.*;
import javax.swing.*;

public class Window extends JFrame {
    public GameArea gameArea;

    public Window() {




     //   Board board = new Board();//Cái này để sau này add cái board game
       // add(board);

        // Create labels
        JLabel hold = new JLabel("Hold");
        JLabel goal = new JLabel("Goal");
        JLabel level = new JLabel("Level");
        JButton next = new JButton("Next");

        JLabel score = new JLabel("Score");

        // Set label colors
        hold.setBackground(Color.YELLOW);
        goal.setBackground(Color.GREEN);
        level.setBackground(Color.CYAN);
        next.setBackground(Color.PINK);
        score.setBackground(Color.ORANGE);

        // Set label fonts
        Font font = new Font("Arial", Font.BOLD, 20);
        hold.setFont(font);
        goal.setFont(font);
        level.setFont(font);
        next.setFont(font);
        score.setFont(font);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle(Constant.TITLE);

        gameArea = new GameArea();
        new GameThread(gameArea).start();

        JPanel gameAreaPanel = new JPanel(new GridBagLayout());
        gameAreaPanel.setBackground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gameAreaPanel.add(gameArea, gbc);

        // Add the game area panel to the center of the window
        add(gameAreaPanel, BorderLayout.CENTER);

        // Create label panels
        JPanel holdPanel = new JPanel();
        holdPanel.setLayout(new BorderLayout());
        holdPanel.add(hold, BorderLayout.CENTER);
        holdPanel.setBackground(Color.YELLOW);
        holdPanel.setPreferredSize(new Dimension(100, 100));

        JPanel goalPanel = new JPanel();
        goalPanel.setLayout(new BorderLayout());
        goalPanel.add(goal, BorderLayout.CENTER);
        goalPanel.setBackground(Color.GREEN);
        goalPanel.setPreferredSize(new Dimension(100, 100));

        JPanel levelPanel = new JPanel();
        levelPanel.setLayout(new BorderLayout());
        levelPanel.add(level, BorderLayout.CENTER);
        levelPanel.setBackground(Color.CYAN);
        levelPanel.setPreferredSize(new Dimension(100, 100));

        JPanel nextPanel = new JPanel();
        nextPanel.add(next);
        nextPanel.setBackground(Color.BLUE);
        nextPanel.setPreferredSize(new Dimension(100, 100));

        // Add panels to label panel
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(3,1));
        labelPanel.add(holdPanel);
        labelPanel.add(goalPanel);
        labelPanel.add(levelPanel);
        add(labelPanel, BorderLayout.WEST);

        // Add next panel to frame
        add(nextPanel, BorderLayout.EAST);

        // Add score panel to frame
        JPanel scorePanel = new JPanel();
        scorePanel.add(score);
        scorePanel.setBackground(Color.ORANGE);
        scorePanel.setPreferredSize(new Dimension(320, 50));
        add(scorePanel, BorderLayout.NORTH);

        setTitle("Tetris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(950, 730);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
    public static void main(String[] args) {
        new Window();
    }
}