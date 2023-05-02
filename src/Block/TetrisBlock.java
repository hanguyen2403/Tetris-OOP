package Block;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

import Variables.Constant;

import javax.imageio.ImageIO;

public class TetrisBlock {
    private int[][] shape;
    private int[][][] shapes;
    public int currentRotation;
    //phan biet 7 block
    public int getBlock;
    private int x, y;

    public int GetX(){
        if (getBlock == 1) return 4;
        if (getBlock == 4) return 2;
        return 3;
    }
    public int GetY(){
        if (getBlock == 1) return 1;
        if (getBlock == 4) return 2;
        return 2;
    }

    public BufferedImage blockJ, blockL, blockI, blockO, blockZ, blockS, blockT, fullJ, fullL, fullI, fullO, fullZ, fullS, fullT;
    public BufferedImage image;

    public TetrisBlock(int[][] shape, int getBlock) {
        this.shape = shape;
        this.getBlock = getBlock;
        initShapes();
    }
    //Xoay block
    public void initShapes(){
        shapes = new int[4][][];
        for (int i = 0; i < 4; i++){

            int row = shape[0].length;
            int column = shape.length;
            shapes[i] = new int[row][column];

            for(int y = 0; y < row; y++){
                for (int x = 0; x < column; x++){
                    shapes[i][y][x] = shape[column - x -1][y];
                }
            }
            shape = shapes[i];
        }
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    //Spawn Block
    public void Spawn(){

        currentRotation = 0;
        shape = shapes[currentRotation];
        y = -getRow();
        x = (Constant.MAX_SCREEN_COL - getColumn()) / 2;
    }

    //Move Block
    public void moveDown(){
        if (getY() + getRow() < Constant.MAX_SCREEN_ROW) y++;
    }
    public void moveRight(){
        if (getX() + getColumn() < Constant.MAX_SCREEN_COL) x++;
    }
    public void moveLeft(){
        if (getX() > 0) x--;
    }

    public void rotate(){
        currentRotation++;
        if (currentRotation > 3) currentRotation = 0;
        shape = shapes[currentRotation];
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
            blockT = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/block/T.png")));
            fullJ = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/block/blockJ.png")));
            fullI = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/block/blockI.png")));
            fullL = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/block/blockL.png")));
            fullO = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/block/blockO.png")));
            fullS = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/block/blockS.png")));
            fullZ = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/block/blockZ.png")));
            fullT = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/block/blockT.png")));
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

    public BufferedImage Image1(){
        if (getBlock == 1) image = fullI;
        if (getBlock == 2) image = fullJ;
        if (getBlock == 3) image = fullL;
        if (getBlock == 4) image = fullO;
        if (getBlock == 5) image = fullS;
        if (getBlock == 6) image = fullT;
        if (getBlock == 7) image = fullZ;
        return image;
    }

    public int getBottomEdge(){
        return getY() + getRow();
    }
    public int getLeftEdge(){
        return getX();
    }
    public int getRightEdge(){
        return getX() + getColumn();
    }

}
