package Controls;

import Block.TetrisBlock;
import Variables.Constant;
import GUI.GameArea;

public class CollisionCheck {
    public static boolean checkBottom(TetrisBlock block) {
        if (block.getBottomEdge() == Constant.MAX_SCREEN_ROW) {
            return false;
        }
        int[][] shape = block.getShape();
        int Column = block.getColumn();
        int Row = block.getRow();
        for (int column = 0; column < Column; column++ ){
            for (int row = Row - 1; row >= 0; row--){
                if (shape[row][column] != 0){
                    int x = column + block.getX();
                    int y = row + block.getY() + 1;
                    if (y < 0) break;
                    if(GameArea.background[y][x] != null) return false;
                    break;
                }
            }
        }
        return true;
    }
    public static boolean checkLeft(TetrisBlock block) {
        if (block.getLeftEdge() == 0) {
            return false;
        }
        int[][] shape = block.getShape();
        int Column = block.getColumn();
        int Row = block.getRow();
        for (int row = 0; row < Row; row++ ){
            for (int column = 0; column < Column; column++){
                if (shape[row][column] != 0){
                    int x = column + block.getX() -1;
                    int y = row + block.getY();
                    if (y < 0) break;
                    if(GameArea.background[y][x] != null) return false;
                    break;
                }
            }
        }

        return true;
    }
    public static boolean checkRight(TetrisBlock block) {
        if (block.getRightEdge() == Constant.MAX_SCREEN_COL) {
            return false;
        }
        int[][] shape = block.getShape();
        int Column = block.getColumn();
        int Row = block.getRow();
        for (int row = 0; row < Row; row++ ){
            for (int column = Column-1; column >= 0; column--){
                if (shape[row][column] != 0){
                    int x = column + block.getX() + 1;
                    int y = row + block.getY();
                    if (y < 0) break;
                    if(GameArea.background[y][x] != null) return false;
                    break;
                }
            }
        }
        return true;
    }
}
