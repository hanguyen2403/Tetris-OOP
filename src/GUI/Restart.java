package GUI;
import Controls.ResetMouse;
import javax.swing.*;
import java.awt.*;
import Variables.Constant;

public class Restart extends JFrame {
    SoundManager sound = new SoundManager("src/resources/Sound/GameOverSound.wav");
    ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("icon/icon.png"));
    public Restart() {

        ResetMouse panel = new ResetMouse( new Rectangle(320 - 30, 400, 250, 50),new Rectangle(320 - 30, 490, 250, 50),this);
        panel.setPreferredSize(new Dimension(960, 640));

        setVisible(true);
        add(panel, BorderLayout.CENTER);
        pack();
        // Set the content pane layout to null
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sound.playSound("src/resources/Sound/GameOverSound.wav");
        setResizable(false);
        setTitle(Constant.TITLE);
        setIconImage(logo.getImage());
        setLocationRelativeTo(null);

    }
}


