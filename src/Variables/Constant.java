package Variables;

public class Constant {
    public static final int SCALE = 2;
    public static final int ORIGINAL_TILE_SIZE = 16;
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // 32 x 32

    public static final int MAX_SCREEN_COL = 10;
    public static final int MAX_SCREEN_ROW = 20;
    public static final int WIDTH = TILE_SIZE * MAX_SCREEN_COL; //320
    public static final int HEIGHT = TILE_SIZE * MAX_SCREEN_ROW ; //640

    public static final int CENTER = WIDTH; //320
    public static final int WIDTH_BACKGROUND = TILE_SIZE * MAX_SCREEN_COL*3; //960
    public static final int HEIGHT_BACKGROUND = HEIGHT; //640

    public static final int SIDE_WIDTH = 680;
    public static final int GridCellSide = TILE_SIZE; //32
    public static final int Initial_X = GridCellSide * 3;

    public static final String TITLE = "Tetris";
    public static final int FPS = 60;
    public static final int Tera= 1_000_000_000;

}
