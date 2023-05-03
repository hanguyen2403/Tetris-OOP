package GUI;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameThread extends Thread {
    private GameArea gameArea;
    private static int score;
    private static int goal = 100;
    private int level;
    private static boolean gameover;
    private static volatile boolean paused = false;

    private JFrame gameAreaFrame;
    private Object pauseLock;

    public GameThread(GameArea gameArea) {
        this.gameArea = gameArea;
      
        paused = false;
        pauseLock = new Object();

        gameAreaFrame = (JFrame) SwingUtilities.getRoot(gameArea);
        gameAreaFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    togglePause();
                }
            }
        });
        gameAreaFrame.setFocusable(true);
    }

    @Override
    public void run() {
        while (true) {
            if (!paused) {
                gameArea.spawnBlock();
                while (gameArea.moveBlockDown()) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (gameArea.isBlockOutOfBounds()) {
                    gameover = true;
                    break;
                }
                gameArea.moveBlockToBackground();
                score += 10 * gameArea.clearLines();
            } else {
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if (isGameover()) {
            showGameOverScreen();
        }
    }

    public static int getScore() {
        return score;
    }

    public static boolean isGameover() {
        return gameover;
    }

    public void togglePause() {
        paused = !paused;
        if (!paused) {
            synchronized (pauseLock) {
                pauseLock.notify();
            }
            gameAreaFrame.requestFocusInWindow();
        }
    }

    private void showGameOverScreen() {
        JFrame gameOverFrame = new JFrame("Game Over");
        gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameOverFrame.setSize(400, 300);
        gameOverFrame.getContentPane().setBackground(Color.BLACK);
        gameOverFrame.getContentPane().setLayout(new BorderLayout());

        JLabel gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setForeground(Color.WHITE);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverFrame.getContentPane().add(gameOverLabel, BorderLayout.CENTER);

        JButton restartButton = new JButton("Restart");
        restartButton.setFont(new Font("Arial", Font.PLAIN, 16));
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                score = 0;
                gameover = false;
                gameArea.clear();
                gameOverFrame.dispose();
                GameThread gameThread = new GameThread(gameArea);
                gameThread.start();
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(restartButton);
        gameOverFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        int x = gameAreaFrame.getX() + (gameAreaFrame.getWidth() - gameOverFrame.getWidth()) / 2;
        int y = gameAreaFrame.getY() + (gameAreaFrame.getHeight() - gameOverFrame.getHeight()) / 2;
        gameOverFrame.setLocation(x, y);

        gameOverFrame.setVisible(true);
    }

    public static int getGoal() {
        return goal;
    }

}