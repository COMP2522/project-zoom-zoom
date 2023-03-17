package project;

import processing.core.PApplet;

import static processing.core.PApplet.minute;
import static processing.core.PApplet.second;

public class Timer {
  private int second;
  private int minute;
  public long startTime = 0;
  public boolean running = false;
  private static Timer instance;

  private Timer() {
  }

  public static Timer getInstance() {
    if (instance == null) {
      instance = new Timer();
    }
    return instance;
  }

  public void showTimer(Window window) {
    long elapsedTime = window.millis() - startTime;
    window.textAlign(PApplet.CENTER, PApplet.CENTER);
    window.textSize(40);
    if (running) {
      window.text(String.format("%02d:%02d.%03d",
          elapsedTime / 1000 / 60, // minutes
          elapsedTime / 1000 % 60, // seconds
          elapsedTime % 1000), (float) window.displayWidth / 2 + 10, (float) window.displayHeight / 20);
    } else {
      window.text("00:00:000", (float) window.displayWidth / 2 + 10, (float) window.displayHeight / 20);
      startTime = 0;
    }
  }
}
