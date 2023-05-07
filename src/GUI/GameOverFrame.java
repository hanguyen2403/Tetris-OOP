package GUI;
import Controls.ExistMouse;
import Controls.ReplaygameMouse;
import Controls.SaveMouse;
import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class GameOverFrame extends JFrame {


    GameArea gameArea;
    GameThread gameThread;

    public GameOverFrame(GameArea gameArea,GameThread gameThread) {
       this.gameArea=gameArea;
       this.gameThread=gameThread;

        setTitle("Game Over!");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(400, 300);
        getContentPane().setBackground(Color.BLACK);
        getContentPane().setLayout(new BorderLayout());

        JLabel gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setForeground(Color.CYAN);
        gameOverLabel.setFont(new Font("Serif", Font.BOLD, 48));
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(gameOverLabel, BorderLayout.CENTER);

        JButton restartButton = new JButton("CLICK TO PLAY");
        restartButton.setFont(new Font("Serif", Font.PLAIN, 16));
        restartButton.addMouseListener(new ReplaygameMouse(this));

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Serif", Font.PLAIN, 16));
        exitButton.addMouseListener(new ExistMouse(this));


        JButton saveButton = new JButton("Save Game");
        saveButton.setFont(new Font("Serif", Font.PLAIN, 16));
        saveButton.addMouseListener(new SaveMouse(this));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(restartButton);
        buttonPanel.add(exitButton);
        buttonPanel.add(saveButton);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);


    }
    public void restartGame() {

        int level = gameThread.getLevel();
        int goal;
        int speed;
        switch (level) {
            case 2:
                goal = 100;
                speed=800;
                break;
            case 3:
                goal = 150;
                speed=600;
                break;
            case 4:
                goal = 200;
                speed=400;
                break;
            case 5:
                goal = 250;
                speed=200;
                break;
            default:

                goal = 50;
                speed=1000;
                break;
        }
        GameThread.speed=speed;
        gameThread.setScore();
        gameThread.setGoal(goal);
        gameThread.setGameover();
        gameArea.clear();
        this.dispose();
        gameThread.stopGame();
        gameThread = new GameThread(gameArea);
        gameThread.startGame();
    }
    public void saveGame() {
        int currentLevel = GameThread.getLevel();
        try {
            FileWriter writer = new FileWriter("saved_game.txt");
            writer.write("Level: " + currentLevel + "\n");
            writer.close();
            JOptionPane.showMessageDialog(this, "Game saved successfully!");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to save the game.");
        }
    }



}