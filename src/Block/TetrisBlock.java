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
    // private Color color;
    //phan biet 7 block
    public int getBlock;

    private int x, y;

    public BufferedImage blockJ, blockL, blockI, blockO, blockZ, blockS, blockT;
    public BufferedImage image;

    public TetrisBlock(int[][] shape, int getBlock) {
        this.shape = shape;
        this.getBlock = getBlock;
    }

    public int[][] getShape() {
        return shape;
    }

  //  public Color getColor() {return color;}

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

    //Spawn Block
    public void Spawn(){
        y = -getRow();
        x = (Constant.MAX_SCREEN_COL - getColumn()) / 2;
    }


    //Move Block
    public void moveDown(){
        if (y <= Constant.MAX_SCREEN_ROW) y++;
    }
    public void moveRight(){
        if (x <= Constant.MAX_SCREEN_COL) x++;
    }
    public void moveLeft(){
        if (x >= 0) x--;
    }
    //Lay anh cho block,
    public void getBlockImage(){
        try{
            blockJ = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/block/J.png")));
            blockI = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/block/I.png")));
            blockL = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/block/L.png")));
            blockO = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/block/O.png")));
            blockS = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/block/S.png")));
            blockZ = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/block/Z.png")));
            blockT = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/block/red1.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public BufferedImage Image(){
        if (getBlock == 1) image = blockI;
        if (getBlock == 2) image = blockJ;
        if (getBlock == 3) image = blockL;
        if (getBlock == 4) image = blockO;
        if (getBlock == 5) image = blockS;
        if (getBlock == 6) image = blockT;
        if (getBlock == 7) image = blockZ;
        return image;
    }

    public int getBottomEdge(){
       return getY() + getRow();
    }

}
