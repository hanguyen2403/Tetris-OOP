// Source: https://youtu.be/Layeo3Jrkks

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AudioPlayer {
    private String soundsFolder = "Game Sounds" + File.separator;
    private String clearLinePath = soundsFolder + "clear.wav"; // Declare a clear line sound game
    private String gameoverPath = soundsFolder + "GameOverSound.wav"; // Declare a game over sound from the folder

    private Clip clearLineSound, gameoverSound;

    public AudioPlayer() {
        try {
            clearLineSound = AudioSystem.getClip();
            gameoverSound = AudioSystem.getClip();

            clearLineSound.open(AudioSystem.getAudioInputStream(new File(clearLinePath).getAbsoluteFile()));

            gameoverSound.open(AudioSystem.getAudioInputStream(new File(clearLinePath).getAbsoluteFile()));


        } catch (LineUnavailableException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level .SEVERE,null, ex);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE,null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE,null, ex);
        }
    }

    public void playClearLine() {
        clearLineSound.start();
    }

    public void playGameOver() {
        gameoverSound.setFramePosition(0);
        gameoverSound.start();
    }
}
