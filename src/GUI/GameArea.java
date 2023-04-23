package GUI;

import javax.swing.*;
import java.awt.*;
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
        this.setPreferredSize(new Dimension(Constant.WIDTH, Constant.HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        spawnBlock();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Ve background line cho khung tro choi
        for (int y = 0; y < Constant.MAX_SCREEN_ROW; y++) {
            for (int x = 0; x < Constant.MAX_SCREEN_COL; x++) {
                //Gray
                g.setColor(new Color(70, 70, 70));
                g.drawRect(x * Constant.GridCellSide, y * Constant.GridCellSide, Constant.GridCellSide, Constant.GridCellSide);
            }
        }
        drawBlock(g);
    }
        //Ve block
    public void drawBlock(Graphics g){

        int Row = block.getRow();
        int Column = block.getColumn();
        Color color = block.getColor();

        for (int row = 0;row < Row; row++){
            for (int column = 0; column < Column; column++){
                if (block.getShape()[row][column] == 1){
                    g.setColor(color);
                    g.fillRect(Constant.Initial_X + column * Constant.GridCellSide, row * Constant.GridCellSide, Constant.GridCellSide, Constant.GridCellSide);
                    g.setColor(Color.BLACK);
                    g.drawRect(Constant.Initial_X + column * Constant.GridCellSide, row * Constant.GridCellSide, Constant.GridCellSide, Constant.GridCellSide);
                }
            }
        }
    }
        //Tao block aka spawnBlock
        public void spawnBlock(){
            block = new TetrisBlock(new int[][] { {0, 0, 0, 0},{1, 1, 1, 1},{0, 0, 0, 0},{0, 0, 0, 0} }, Color.blue);
        }
}

