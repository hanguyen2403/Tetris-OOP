package Block;

import Block.TetrisBlock;
public class ZShape extends TetrisBlock {
    public ZShape() {
        super(new int[][]{{1, 1, 0},
                          {0, 1, 1}}, 7);
    }
}