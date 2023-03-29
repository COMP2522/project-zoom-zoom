package project;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * The BGM class is responsible for handling background music in the program.
 */
public class BGM {

  /**
   * The clip variable holds the audio clip that is currently playing.
   */
  private static Clip clip;

  /**
   * The getBGM method loads the audio file and plays the background music.
   * @param check - a boolean value indicating whether the music should be played or not
   * @throws Exception if the audio file cannot be loaded or if there is an error playing the audio
   */
  public static void getBGM(boolean check) {
    try {
      File audioFile = new File("tokyodrift.wav");
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
      clip = AudioSystem.getClip();
      clip.open(audioStream);
      if (check) {
        clip.start();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * The stopBGM method stops the background music from playing.
   * @param check - a boolean value indicating whether the music should be stopped or not
   */
  public static void stopBGM(boolean check) {
    if (!check) {
      clip.close();
    }
  }
}