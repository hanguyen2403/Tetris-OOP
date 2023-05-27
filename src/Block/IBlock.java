/*Name: Group 17
  Nguyễn Khánh Hà - ITCSIU21004
  Huỳnh Lâm Đăng Khoa - ITCSIU21138
  Nguyễn Bình Phương Huy - ITCSIU21189
  Trần Thanh Nguyên - ITCSIU21093
  Purpose: This is a class to create I block*/

package Block;

public class IBlock extends TetrisBlock {
    public IBlock(){
        super(new int[][] {{1} ,
                           {1} ,
                           {1} ,
                           {1}}, 1);
    }

    @Override
    public void rotate() {
        super.rotate();
        if (this.getColumn() == 1 ) {
            this.setX(this.getX() + 1);
            this.setY(this.getY() - 1);
        } else {
            this.setX(this.getX() - 1);
            this.setY(this.getY() + 1);
        }
    }
}
