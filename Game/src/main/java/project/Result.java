package project;

import processing.core.PConstants;
import processing.core.PImage;

import java.util.ArrayList;

public class Result {

  private final GameManager window;

  private static Result instance;

  private PImage bg;

  private Result(GameManager window) {
    this.window = window;
  }

  public static Result getInstance(GameManager window) {
    if (instance == null) {
      instance = new Result(window);
    }
    return instance;
  }

  public void setUp() {
    bg = window.loadImage("Game/images/BGImage.png");
    window.translate(window.displayWidth / 2, window.displayHeight / 2);
    window.imageMode(PConstants.CENTER);
  }

  public void draw() {
    window.image(bg, 0, 0, window.displayWidth, window.displayHeight);
    window.textSize(60);
    window.text("RESULT", window.displayWidth / 8, window.displayHeight / 8);
  }
}
