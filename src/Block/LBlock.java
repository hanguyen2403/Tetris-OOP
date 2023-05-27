/*Name: Group 17
  Nguyễn Khánh Hà - ITCSIU21004
  Huỳnh Lâm Đăng Khoa - ITCSIU21138
  Nguyễn Bình Phương Huy - ITCSIU21189
  Trần Thanh Nguyên - ITCSIU21093
  Purpose: This is a class to create L block*/


package Block;

public class LBlock extends TetrisBlock{
    public LBlock(){
        super(new int[][] {{1,0},
                           {1,0},
                           {1,1}},3);
    }
}
