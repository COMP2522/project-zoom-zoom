package project;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class BGM {

  public static void getBGM(boolean check) {
    try {
      File audioFile = new File("BGM.wav");
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
      Clip clip = AudioSystem.getClip();
      clip.open(audioStream);
      while (check) {
        clip.loop(Clip.LOOP_CONTINUOUSLY); // loop the clip
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
