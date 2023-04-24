package Block;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.IOException;
import java.util.Objects;

import Variables.Constant;

import javax.imageio.ImageIO;

public class TetrisBlock {
    private int[][] shape;
    private Color color;
    //phan biet 7 block
    public int getBlock;

    private int x, y;

    public BufferedImage blockJ, blockL, blockI, blockO, blockZ, blockS, blockT;

    public TetrisBlock(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;
        x = Constant.Initial_X;
        y = 0;
    }

    public int[][] getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public int getRow(){
        return getShape().length;
    }

    public int getColumn(){
        return getShape()[0].length;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveDown(){
        if (y <= Constant.MAX_SCREEN_ROW) y++;
    }
    public void moveRight(){
        if (x <= Constant.MAX_SCREEN_COL) x++;
    }
    public void moveLeft(){
        if (x >= 0) x--;
    }
    //Lay anh cho block, DANG FIX BUG
    public void getBlockImage(){
        try{
            blockJ = ImageIO.read(getClass().getResourceAsStream("/block/J.png"));
            blockI = ImageIO.read(getClass().getResourceAsStream("/block/I.png"));
            blockL = ImageIO.read(getClass().getResourceAsStream("/block/L.png"));
            blockO = ImageIO.read(getClass().getResourceAsStream("/block/O.png"));
            blockS = ImageIO.read(getClass().getResourceAsStream("/block/S.png"));
            blockZ = ImageIO.read(getClass().getResourceAsStream("/block/Z.png"));
            blockT = ImageIO.read(getClass().getResourceAsStream("/block/red1.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public BufferedImage image = blockJ;

}
