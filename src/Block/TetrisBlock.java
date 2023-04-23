package Block;

import java.awt.*;

public class TetrisBlock {
    private int[][] shape;
    private Color color;

    public TetrisBlock(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;
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
}
