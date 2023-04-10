package project;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * The BGM class is responsible for handling background music in the program.
 */
public class Bgm {

  /**
   * The clip variable holds the audio clip that is currently playing.
   */
  private Clip clip;

  private static Bgm instance;

  private Bgm() {}

  /**
   * The getInstance method returns the singleton instance of the BGM class.
   * If the instance does not exist, it is created.
   *
   * @return The singleton instance of the BGM class.
   */
  public static Bgm getInstance() {
    if (instance == null) {
      instance = new Bgm();
    }
    return instance;
  }

  /**
   * The getBGM method loads the audio file and plays the background music.
   *
   * @param check - a boolean value indicating whether the music should be played or not
   */
  public void getBgm(boolean check) {
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
   *
   * @param check - a boolean value indicating whether the music should be stopped or not
   */
  public void stopBgm(boolean check) {
    if (!check) {
      clip.close();
    }
  }
}
