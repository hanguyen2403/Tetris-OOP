package GUI;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThread extends Thread {
    private GameArea gameArea;
    private static int score;
    private static int goal = 100;
    private static int level=1;
    private static boolean gameover;
    private static volatile boolean paused = false;

    private JFrame gameAreaFrame;
    private  boolean running;



    public GameThread(GameArea gameArea) {
        this.gameArea = gameArea;

        paused = false;
running=true;

        gameAreaFrame = (JFrame) SwingUtilities.getRoot(gameArea);


        gameAreaFrame.setFocusable(true);
    }

    @Override
    public void run() {
        while (running) {
            gameArea.spawnBlock();
            while (gameArea.moveBlockDown()) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if(gameArea.isBlockOutOfBounds()) {
                gameover=true;
                break;
            }
            gameArea.moveBlockToBackground();
//            gameArea.spawnNextBlock();

            score += 10*gameArea.clearLines();

        }
        if(isGameover()){
            showGameOverScreen();
        }
        if(score>=goal){
            level++;
        }


    }

    public static int getScore() {
        return score;
    }

    public static boolean isGameover() {
        return gameover;
    }



    private void showGameOverScreen() {
        JFrame gameOverFrame = new JFrame("Game Over!");
        gameOverFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
                level=getlevel();
                switch (level){
                    case 2:
                        goal+=50;
                        break;
                    case 3:
                        goal+=100;
                        break;
                    case 4:
                        goal+=150;
                        break;
                    case 5:
                        goal+=200;
                        break;

                }
                gameover = false;
                gameArea.clear();
                gameOverFrame.dispose();
                GameThread gameThread = new GameThread(gameArea);
                gameThread.start();
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 16));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(restartButton);
        buttonPanel.add(exitButton);
        gameOverFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        int x = gameAreaFrame.getX() + (gameAreaFrame.getWidth() - gameOverFrame.getWidth()) / 2;
        int y = gameAreaFrame.getY() + (gameAreaFrame.getHeight() - gameOverFrame.getHeight()) / 2;
        gameOverFrame.setLocation(x, y);

        gameOverFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        gameOverFrame.setVisible(true);
    }

    // Other methods and code


    public static int getGoal() {
        return goal;
    }
    public static int getlevel(){
        return  level;
    }

}
