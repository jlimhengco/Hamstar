package SoundPlayer;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
    private static final String NYAN = "Sounds/nyan.wav";
    private static final String BLACKHOLE = "Sounds/sparkle.wav";

    public static void playBgMusic() {
        playSound(NYAN, true);
    }

    public static void playBlackHole() {
        playSound(BLACKHOLE, false);
    }
    
    private static void playSound(String fn, boolean loop) {
        new SoundThread(fn, loop).start();
    }
    
    private static class SoundThread extends Thread {
        private String fn;
        private boolean loop;
        
        public SoundThread(String fn, boolean loop) {
            this.fn = fn;
            this.loop = loop;
        }
        
        public void run() {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(fn));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(5f);
                
                if (loop)
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                else
                    clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
