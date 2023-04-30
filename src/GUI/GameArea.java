package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import Controls.CollisionCheck;
import Variables.Constant;
import Block.TetrisBlock;
//gameAre aka gamePannel
public class GameArea extends JPanel {
    private BufferedImage[][] background;

    //bien block tu pack Block
    public TetrisBlock block;


    /*  public int[][] block1 = {
          {0, 0, 0, 0},
          {1, 1, 1, 1},
          {0, 0, 0, 0},
          {0, 0, 0, 0}
      };*/
    public GameArea(){
        this.setPreferredSize(new Dimension(Constant.WIDTH_BACKGROUND, Constant.HEIGHT_BACKGROUND));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
       // spawnBlock();
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
        block = new TetrisBlock(new int[][] { {1,0}, {1,0}, {1,1}}, 6);
        block.getBlockImage();
        block.Spawn();
    }
    //moveBlockDown
    public boolean moveBlockDown(){
        if (CollisionCheck.checkBottom(block) == false) {
            moveBlockToBackGround();
            return false;
        }
        block.moveDown();
        repaint();
        return true;
    }

    private void moveBlockToBackGround() {
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

