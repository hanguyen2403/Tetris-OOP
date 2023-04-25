package Controls;

import Block.TetrisBlock;
import Variables.Constant;

public class CollisionCheck {
    public static boolean checkBottom(TetrisBlock block) {
        if (block.getBottomEdge() == Constant.MAX_SCREEN_ROW) {
            return false;
        } else {
            return true;
        }
    }
}
