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

    public boolean checkDrop = false;

    public GameArea(){
        this.setPreferredSize(new Dimension(Constant.WIDTH_BACKGROUND, Constant.HEIGHT_BACKGROUND));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        blocks = new TetrisBlock[]{ new OBlock(), new LBlock(), new IBlock(), new ZBlock(),
                                    new JBlock(), new JBlock(), new SBlock()};
        spawnBlock();
        background = new BufferedImage[Constant.MAX_SCREEN_ROW][Constant.MAX_SCREEN_COL];
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
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
                   //g2.setColor(color);
                   //g2.fillRect(Constant.CENTER + block.getX() + column * Constant.GridCellSide, block.getY()* Constant.GridCellSide, Constant.GridCellSide, Constant.GridCellSide);
                   //g2.setColor(Color.BLACK);
                   //g2.drawRect(Constant.CENTER + block.getX() + column * Constant.GridCellSide, block.getY() * Constant.GridCellSide, Constant.GridCellSide, Constant.GridCellSide);
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
    }

    private void drawGridSquare(Graphics2D g2, BufferedImage image, int x, int y) {
        g2.drawImage(image, x, y, Constant.GridCellSide, Constant.GridCellSide, null);
    }

    //Tao block aka spawnBlock
    public void spawnBlock(){
        Random random = new Random();
        block = blocks[random.nextInt(blocks.length)];
        block.getBlockImage();
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

}

