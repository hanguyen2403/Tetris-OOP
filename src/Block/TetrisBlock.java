package Block;

import java.awt.*;
import Variables.Constant;

public class TetrisBlock {
    private int[][] shape;
    private Color color;

    private int x, y;

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

    public void moveDown(){
        if (y <= Constant.MAX_SCREEN_ROW) y++;
    }
    public void moveRight(){
        if (x <= Constant.MAX_SCREEN_COL) x++;
    }
    public void moveLeft(){
        if (x >= 0) x--;
    }
}
