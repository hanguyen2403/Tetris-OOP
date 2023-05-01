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
    // private Color color;
    //phan biet 7 block
    public int getBlock;

    private int x, y;

    public BufferedImage blockJ, blockL, blockI, blockO, blockZ, blockS, blockT;
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

    public void setX(int newX) {x = newX;}

    public int getY() {
        return y;
    }

    public void setY(int newY) {y = newY;}

    //Spawn Block
    public void Spawn(){
        Random random = new Random();
        currentRotation = random.nextInt(shapes.length);
        shape = shapes[currentRotation];
        y = -getRow();
        x = random.nextInt(Constant.MAX_SCREEN_COL - getColumn());
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
    public int getLeftEdge(){
        return getX();
    }
    public int getRightEdge(){
        return getX() + getColumn();
    }

}
