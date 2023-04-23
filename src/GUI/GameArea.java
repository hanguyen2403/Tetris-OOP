package GUI;

import javax.swing.*;
import java.awt.*;
import Variables.Constant;
//gameAre aka gamePannel
public class GameArea extends JPanel {
    public GameArea(){
        this.setPreferredSize(new Dimension(Constant.WIDTH, Constant.HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        //Ve background line cho khung tro choi
        for (int y = 0; y < Constant.MAX_SCREEN_ROW; y++) {
            for (int x = 0; x < Constant.MAX_SCREEN_COL; x++) {
                //Gray
                g.setColor(new Color(70, 70, 70));
                g.drawRect(x * Constant.GridCellSide, y*Constant.GridCellSide, Constant.GridCellSide, Constant.GridCellSide);
            }
        }
        //
    }


}
