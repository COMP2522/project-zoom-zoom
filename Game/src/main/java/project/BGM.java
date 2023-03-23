package project;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class BGM {

  private static Clip clip;

  public static void getBGM(boolean check) {
    try {
      File audioFile = new File("tokyodrift.wav");
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
      clip = AudioSystem.getClip();
      clip.open(audioStream);
      // while loop causes some problems but I get why its there to loop clip
//      while (check) {
//        clip.loop(Clip.LOOP_CONTINUOUSLY); // loop the clip
//      }
      if (check)
        clip.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void stopBGM(boolean check) {
    if (!check) {
      clip.close();
    }
  }
}