/*Name: Group 17
  Nguyễn Khánh Hà - ITCSIU21004
  Huỳnh Lâm Đăng Khoa - ITCSIU21138
  Nguyễn Bình Phương Huy - ITCSIU21189
  Trần Thanh Nguyên - ITCSIU21093
  Purpose: This is a class to create T block*/


package Block;

public class TBlock extends TetrisBlock{
    public TBlock(){
        super(new int[][] {{0,1},
                           {1,1},
                           {0,1}}, 6);
    }

}
