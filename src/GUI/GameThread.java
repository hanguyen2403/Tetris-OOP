package GUI;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThread extends Thread {
    private GameArea gameArea;
    private static int score;
    private static int goal = 50;
    private static int level=1;
    private static boolean gameover;
    private static volatile boolean paused = false;
    public int speed=1000;
    public boolean checkcontrast=false;
    public static  boolean contrast=false;

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

        while (true) {

            gameArea.spawnBlock();
            while (gameArea.moveBlockDown()) {
                try {
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if(gameArea.isBlockOutOfBounds()) {
                gameover=true;
                break;
            }
            if(score==goal&&level<6){
                gameover=true;
                contrast=true;
                checkcontrast=true;
                if(gameover){
                    showGameOverScreen();
                }

                break;

            }else{
                contrast=false;
            }

           gameArea.moveBlockToBackground();
        //  gameArea.spawnNextBlock();

            score += 10*gameArea.clearLines();

             increaseDifficulty();
        }

        if(score>=goal) {
            level++;
        }
        if(level==6){

            level=1;
        }

        if(gameover==true&&checkcontrast==false){
            showGameOverScreen();
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
        gameOverLabel.setForeground(Color.CYAN);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 48));
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverFrame.getContentPane().add(gameOverLabel, BorderLayout.CENTER);

        JButton restartButton = new JButton("CLICK TO PLAY");
        restartButton.setFont(new Font("Arial", Font.PLAIN, 16));
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                score = 0;

                level=getlevel();
                switch (level){
                    case 2:
                        goal=100;
                        break;
                    case 3:
                        goal=150;
                        break;
                    case 4:
                        goal=200;
                        break;
                    case 5:
                        goal=250;
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

        //SAVE BUTTON
        JButton saveButton = new JButton("Save Game");
        saveButton.setFont(new Font("Arial", Font.PLAIN, 16));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGame();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(restartButton);
        buttonPanel.add(exitButton);
        buttonPanel.add(saveButton);
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
    public void increaseDifficulty() {
        switch (level) {
            case 2:
                speed = 800;
                break;
            case 3:
                speed = 600;
                break;
            case 4:
                speed = 400;
                break;
            case 5:
                speed = 200;
                break;
            // Add more cases for additional levels if needed

        }
    }

    public int getSpeed() {
        return speed;
    }

    private void saveGame() {
        int currentLevel = GameThread.getlevel();
        try {
            FileWriter writer = new FileWriter("saved_game.txt");
            writer.write("Level: " + currentLevel + "\n");
            writer.close();
            JOptionPane.showMessageDialog(gameAreaFrame, "Game saved successfully!");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(gameAreaFrame, "Failed to save the game.");
        }
    }



}
