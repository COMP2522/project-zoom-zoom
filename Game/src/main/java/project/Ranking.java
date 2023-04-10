package project;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.bson.Document;
import processing.core.PImage;

/**
 * The Ranking class represents the ranking screen.
 */
public class Ranking {
  private final GameManager window;
  private static Ranking instance;
  private PImage bg;
  private Mongodb mongodb;
  private ArrayList<Long> list;
  public static boolean once = true;
  private final float halfDisplayWidth;
  private final float halfDisplayHeight;

  /**
   * The constructor for the Ranking class.
   * It is private to keep it singleton.
   *
   * @param window the window for current game
   */
  private Ranking(GameManager window) {
    this.window = window;
    list = new ArrayList<Long>();
    halfDisplayWidth = window.displayWidth / 2;
    halfDisplayHeight = window.displayHeight / 2;
  }

  /**
   * The getInstance method returns the singleton instance of the Ranking class.
   *
   * @param window the window for current game
   * @return the singleton instance of the Ranking class
   */
  public static Ranking getInstance(GameManager window) {
    if (instance == null) {
      instance = new Ranking(window);
    }
    return instance;
  }

  /**
   * The setup method sets up the background image.
   */
  public void setUp() {
    bg = window.loadImage("Game/images/BGImage.png");
    if (once) {
      mongodb = Mongodb.getInstance();
      for (Document data : mongodb.queryTop5()) {
        list.add((Long) data.get("time"));
      }
      once = false;
    }
  }

  /**
   * The draw method draws the background image and the top 5 times.
   */
  public void draw() {
    window.image(bg, 0, 0, window.displayWidth, window.displayHeight);
    window.textSize(60);
    window.text("TOP 5", halfDisplayWidth, halfDisplayHeight - 300);
    for (int i = 0; i < list.size(); i++) {
      SimpleDateFormat sdf = new SimpleDateFormat("mm:ss:SSS");
      String time = sdf.format(new Date(list.get(i)));
      window.text(time, halfDisplayWidth, window.displayHeight / 3 + i * 100);
    }
  }
}
