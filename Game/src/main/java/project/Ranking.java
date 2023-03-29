package project;

import org.bson.Document;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Ranking {
  private final GameManager window;
  private static Ranking instance;
  private PImage bg;
  private MongoDB mongoDB;
  private ArrayList<Long> list;
  public static boolean once = true;
  private Ranking(GameManager window) {
    this.window = window;
    list = new ArrayList<Long>();
  }

  public static Ranking getInstance(GameManager window) {
    if (instance == null) {
      instance = new Ranking(window);
    }
    return instance;
  }

  public void setUp() {
    bg = window.loadImage("Game/images/BGImage.png");
    if (once) {
      mongoDB = MongoDB.getInstance();
      for (Document data : mongoDB.queryTop5()) {
        list.add((Long)data.get("time"));
      }
      once = false;
    }
  }

  public void draw() {
    window.image(bg, 0, 0, window.displayWidth, window.displayHeight);
    window.textSize(60);
    window.text("TOP 5", window.displayWidth / 2, window.displayHeight / 2 - 300);
    System.out.println(list.size());
    for (int i = 0; i < list.size(); i++) {
      SimpleDateFormat sdf = new SimpleDateFormat("mm:ss:SSS");
      String time = sdf.format(new Date(list.get(i)));
      window.text(time, window.displayWidth / 2, window.displayHeight / 3 + i * 100);
    }
  }
}
