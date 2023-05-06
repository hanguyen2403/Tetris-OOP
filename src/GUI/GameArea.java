package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import Block.*;
import Controls.CollisionCheck;
import Variables.Constant;

//gameAre aka gamePannel
public class GameArea extends JPanel {
    public static BufferedImage[][] background;

    //bien block tu pack Block
    public TetrisBlock block;
    public TetrisBlock[] blocks;

    public TetrisBlock holdBlock;
    public boolean gameover=false;

    public TetrisBlock[] arrayBlock = new TetrisBlock[4];
    public boolean checkDrop = false;

    public GameArea(){



        this.setPreferredSize(new Dimension(960, 640));

        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        blocks = new TetrisBlock[]{ new OBlock(), new LBlock(), new IBlock(), new ZBlock(),
                                    new JBlock(), new TBlock(), new SBlock()};
        spawnBlock();
        background = new BufferedImage[Constant.MAX_SCREEN_ROW][Constant.MAX_SCREEN_COL];
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//Draw hint
      Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(610+50, 384+23, 320-32-30, 150);


        g2.setColor(Color.WHITE);
        g2.drawRect(660,48,320-32-30,352-32);



        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Monospaced", Font.PLAIN, 16));
        g2.drawString("Instruction for players:", 610+100, 400);
        g2.setFont(new Font("Monospaced", Font.PLAIN, 14));
        g2.setColor(Color.WHITE); // Light Red

        g2.drawString(" → : Move right", 610+65, 430);
        g2.drawString(" ← : Move left", 610+65, 450);
        g2.drawString(" ↓ : Down faster", 610+65, 470);
        g2.drawString(" ↑ : Rotate", 610+65, 490);
        g2.drawString("Space: Block move faster", 610+65, 510);
        g2.drawString("C: Change Block", 610+65, 530);

        g2.setFont(new Font("Monospaced", Font.PLAIN, 20));
        g2.drawString("Next block ",672,32);



        //show GameOVER
        //Show stats
          g2.setColor(Color.WHITE);
        g2.drawRect(75,354,320-85,190);
        g2.setFont(new Font("Serif", Font.PLAIN, 31));
        g2.drawString("Goal : "+GameThread.getGoal(),115,400);
        g2.drawString("Level :"+GameThread.getLevel(),115,400+60);
        g2.drawString("Score :"+GameThread.getScore(),115,400+60+60);



        //Ve background line cho khung tro choi
        for (int y = 0; y < Constant.MAX_SCREEN_ROW; y++) {
            for (int x = 0; x < Constant.MAX_SCREEN_COL; x++) {
                //GrayPink
                g2.setColor(new Color(152, 128, 128));
                g2.fillRect(Constant.CENTER + x * Constant.GridCellSide, y * Constant.GridCellSide, Constant.GridCellSide, Constant.GridCellSide);
                //Gray
                g2.setColor(new Color(70, 70, 70));
                g2.drawRect(Constant.CENTER + x * Constant.GridCellSide, y * Constant.GridCellSide, Constant.GridCellSide, Constant.GridCellSide);
            }
        }




        drawBackground(g2);
//      showNextBlock(g2,64);
        drawBlock(g2);

    }
    //Ve block
    public void drawBlock(Graphics2D g2){
        int Row = block.getRow();
        int Column = block.getColumn();
        int[][] shape = block.getShape();
        BufferedImage image = block.Image();
       // Color color = block.getColor();

        for (int row = 0; row < Row; row++){
            for (int column = 0; column < Column; column++){
                if (shape[row][column] == 1){
                    int x = Constant.CENTER + (block.getX() + column) * Constant.GridCellSide;
                    int y = (block.getY() + row) * Constant.GridCellSide;
                    drawGridSquare(g2, image, x, y);
                }
            }
        }
    }

    private void drawBackground (Graphics2D g2) {
        BufferedImage image;
        for (int row = 0; row < Constant.MAX_SCREEN_ROW; row++) {
            for (int column = 0; column < Constant.MAX_SCREEN_COL; column++) {
                image = background[row][column];
                if (image != null){
                    int x = Constant.CENTER + column * Constant.GridCellSide;
                    int y = row * Constant.GridCellSide;
                    drawGridSquare(g2, image, x, y);
                }
            }
        }
        showNextBlock(g2, 64);
    }

    private void drawGridSquare(Graphics2D g2, BufferedImage image, int x, int y) {
        g2.drawImage(image, x, y, Constant.GridCellSide, Constant.GridCellSide, null);
    }

    private void showfullBlock(Graphics2D g2, BufferedImage image, int x, int y, int width, int height) {
        g2.drawImage(image, x, y, width, height, null);
    }

    //Tao block aka spawnBlock
    public void spawnBlock(){
        Random random = new Random();
        if (arrayBlock[0] == null) {
            arrayBlock[0] = blocks[random.nextInt(blocks.length)];
            arrayBlock[1] = blocks[random.nextInt(blocks.length)];
            arrayBlock[2] = blocks[random.nextInt(blocks.length)];
            arrayBlock[3] = blocks[random.nextInt(blocks.length)];
            block = arrayBlock[0];
        } else {
            arrayBlock[0] = arrayBlock[1];
            arrayBlock[1] = arrayBlock[2];
            arrayBlock[2] = arrayBlock[3];
            arrayBlock[3] = blocks[random.nextInt(blocks.length)];
            block = arrayBlock[0];
        }
//        if (arrayBlock[1] == null) {}
//        if (arrayBlock[2] == null) {}
//        if (arrayBlock[3] == null) {}

//        block = arrayBlock[0];
//        arrayBlock[0] = arrayBlock[1];
//        arrayBlock[1] = arrayBlock[2];
//        arrayBlock[2] = arrayBlock[3];
//        arrayBlock[3] = blocks[random.nextInt(blocks.length)];
        block.getBlockImage();
        arrayBlock[1].getBlockImage();
        arrayBlock[2].getBlockImage();
        arrayBlock[3].getBlockImage();
        block.Spawn();

    }

    //Check khi block day man hinh
    public boolean isBlockOutOfBounds() {
        if(block.getY() < 0) {
            block = null;
            return true;
        }
        return false;
    }

    //moveBlockDown
    public boolean moveBlockDown(){
      //  if (block == null) return ;
        if (CollisionCheck.checkBottom(block) == false) {
            return false;
        }
        block.moveDown();
        checkDrop = false;
        repaint();
        return true;
    }
    public void moveBlockLeft(){
        if (block == null) return;
        if (CollisionCheck.checkLeft(block) == false) return;
        if (checkDrop) return;
        block.moveLeft();
        repaint();
    }
    public void moveBlockRight(){
        if (block == null) return;
        if (CollisionCheck.checkRight(block) == false) return;
        if (checkDrop) return;
        block.moveRight();
        repaint();
    }
    public void moveBlockDownFaster(){
        if (block == null) return;
        if (CollisionCheck.checkBottom(block) == false) return;
        block.moveDown();
        repaint();
      //  }
    }
    public void dropBlock(){
        if (block == null) return;
        while (CollisionCheck.checkBottom(block) == true) {
            block.moveDown();
        }
        checkDrop = true;
        repaint();
    }
    public void RotateBlock(){
        if (block == null) return;
        if (CollisionCheck.checkBottom(block) == false) return;
        block.rotate();

        if(block.getLeftEdge() < 0) block.setX(0);
        if(block.getRightEdge() >= Constant.MAX_SCREEN_COL) block.setX(Constant.MAX_SCREEN_COL - block.getColumn());
        if(block.getBottomEdge() >= Constant.MAX_SCREEN_ROW) block.setY(Constant.MAX_SCREEN_ROW - block.getRow());

        repaint();
    }
    public void ChangeBlock(){
        if (holdBlock == null) {
            holdBlock = block;
            holdBlock.Spawn();
            spawnBlock();
            repaint();
        } else {
            TetrisBlock swap;
            swap = block;
            block = holdBlock;
            holdBlock = swap;
            holdBlock.Spawn();
            repaint();
        }
    }

    public int clearLines() {
        boolean lineFilled;
        int lineCleared = 0;
        for(int row = Constant.MAX_SCREEN_ROW - 1; row >= 0; row--) {
            lineFilled = true;
            for(int column = 0; column < Constant.MAX_SCREEN_COL; column++) {
                if(background[row][column] == null) {
                    lineFilled = false;
                    break;
                }
            }
            if(lineFilled) {
                lineCleared ++;
                clearLine(row);
                shiftDown(row);
                clearLine(0);
                row++;
                repaint();
            }
        }
        return lineCleared;
    }

    private void clearLine(int row) {
        for(int i = 0; i < Constant.MAX_SCREEN_COL; i++) {
            background[row][i] = null;
        }
    }

    private void shiftDown(int row) {
        for(int r = row; r > 0; r--) {
            for(int c = 0; c < Constant.MAX_SCREEN_COL; c++) {
                background[r][c] = background[r - 1][c];
            }
        }
    }

    public void moveBlockToBackground() {
        int[][] shape = block.getShape();
        int Row = block.getRow();
        int Column = block.getColumn();

        int xPos = block.getX();;
        int yPos = block.getY();

        BufferedImage image = block.Image();

        for (int row = 0; row < Row; row++) {
            for (int column = 0; column < Column; column++) {
                if (shape[row][column] != 0) {
                    background[row + yPos][column + xPos] = image;
                }
            }
        }
    }
    public void showNextBlock(Graphics2D g2, int Y){
        for (int i = 1; i < 4; i++){
            int y;
            BufferedImage image = arrayBlock[i].Image1();
            if (arrayBlock[i].getBlock == 1) {y = Y + Constant.GridCellSide;} else {
                y = Y;
            }
            g2.drawImage(image,Constant.SIDE_WIDTH, y, Constant.GridCellSide * arrayBlock[i].GetX(), Constant.GridCellSide * arrayBlock[i].GetY(), null);
            Y+= 96;
        }
    }

    public void clear() {
        for (int row = 0; row < Constant.MAX_SCREEN_ROW; row++) {
            for (int column = 0; column < Constant.MAX_SCREEN_COL; column++) {
                background[row][column] = null;
            }
        }
    }
}

