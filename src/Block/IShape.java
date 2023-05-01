package Block;

import Block.TetrisBlock;
public class IShape extends TetrisBlock {
    public IShape() {
        super(new int[][]{{1, 1, 1, 1}}, 1);
    }

    @Override
    public void rotate() {
        super.rotate();
        if(this.getColumn() == 1) {
            this.setX(this.getX() + 1);
            this.setY(this.getY() - 1);
        }
        else {
            this.setX(this.getX() - 1);
            this.setY(this.getY() + 1);
        }
    }
}
