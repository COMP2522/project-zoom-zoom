package project;

import processing.core.PApplet;

import java.util.TimerTask;
import java.util.Timer;

public class Stopwatch {
  private final GameManager window;
  public long startTime;
  public boolean running = false;
  private static Stopwatch instance;
  private long elapsedTime = 0;
  private Timer timer;
  private int seconds = 0;
  private Stopwatch(GameManager window) {
    this.window = window;
  }

  public static Stopwatch getInstance(GameManager window) {
    if (instance == null) {
      instance = new Stopwatch(window);
    }
    return instance;
  }

  public void showTimer(boolean check) {
    if (check) {
      start();
    } else {
      stop();
    }
  }

  public void start() {
    timer = new java.util.Timer();
    timer.schedule(new TimerTask() {
      public void run() {
        window.text(String.format("%02d", seconds), (float) window.displayWidth / 10,
            (float) window.displayHeight / 20);
        seconds++;
      }
    }, 0, 1000); // 1000 milliseconds = 1 second
  }

  public void stop() {
    if (timer != null) {
      timer.cancel();
      timer = null;
    }
  }

  public void resetTimer() {
    startTime = this.window.millis();
  }

  public void stopTimer() {
    running = false;
  }

  public long getStartTime() {
    return startTime;
  }

  public void setStartTime(long startTime) {
    this.startTime = startTime;
  }

  public boolean isRunning() {
    return running;
  }

  public void setRunning(boolean running) {
    this.running = running;
  }
}