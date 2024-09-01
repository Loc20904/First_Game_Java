
package Pk_Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class sound {

    Clip clip;
    URL soundURL[]=new URL[20];
    
    
    public sound()
    {
        soundURL[0]=getClass().getResource("/more/music2.wav");
        soundURL[1]=getClass().getResource("/more/pickkey.wav");
        soundURL[2]=getClass().getResource("/more/doorOpen.wav");
        soundURL[3]=getClass().getResource("/more/victorySound.wav");
        soundURL[4]=getClass().getResource("/more/music.wav");
        soundURL[5]=getClass().getResource("/more/swingSword.wav");
        soundURL[6]=getClass().getResource("/more/hitmonster.wav");
        soundURL[7]=getClass().getResource("/more/receivedamage.wav");
        soundURL[8]=getClass().getResource("/more/levelup.wav");
        soundURL[9]=getClass().getResource("/more/burning.wav");
    }
    
    public void setFile(int i)
    {
        try {
            //InputStream inps=getClass().getResourceAsStream(soundURL[i]);
            AudioInputStream ads = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ads);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(sound.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("File sound not support by Java");
        } catch (IOException ex) {
            System.out.println("Load file fail");
        }
    }
    
    public void play()
    {
        clip.start();
    }
    public void soundLoop()
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop()
    {
        clip.stop();
    }
}
