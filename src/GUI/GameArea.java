package GUI;

import javax.swing.*;
import java.awt.*;

import Controls.CollisionCheck;
import Variables.Constant;
import Block.TetrisBlock;
//gameAre aka gamePannel
public class GameArea extends JPanel {

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

        spawnBlock();
        block.getBlockImage();
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
        drawBlock(g2);
    }
    //Ve block
    public void drawBlock(Graphics2D g2){

        int Row = block.getRow();
        int Column = block.getColumn();
        int[][] shape = block.getShape();
       // Color color = block.getColor();

        for (int row = 0; row < Row; row++){
            for (int column = 0; column < Column; column++){
                if (shape[row][column] == 1){
                    int x = (block.getX() + column) * Constant.GridCellSide;
                    int y = (block.getY() + row) * Constant.GridCellSide;
                    g2.drawImage(block.Image(), Constant.CENTER + x, y, Constant.GridCellSide, Constant.GridCellSide, null);
                   //    g2.setColor(color);
                     //  g2.fillRect(Constant.CENTER + block.getX() + column * Constant.GridCellSide, block.getY()* Constant.GridCellSide, Constant.GridCellSide, Constant.GridCellSide);
                       //g2.setColor(Color.BLACK);
                       //g2.drawRect(Constant.CENTER + block.getX() + column * Constant.GridCellSide, block.getY() * Constant.GridCellSide, Constant.GridCellSide, Constant.GridCellSide);
                }
            }
        }
    }
    //Tao block aka spawnBlock
    public void spawnBlock(){
        block = new TetrisBlock(new int[][] { {1,0}, {1,0}, {1,1}}, 6);
        block.Spawn();
    }
    //moveBlockDown
    public void moveBlockDown(){
        if (CollisionCheck.checkBottom(block) == false) return;
        block.moveDown();
        repaint();
    }

}

