package project;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Stopwatch {
  private final GameManager window;
  private static Stopwatch instance;
  public boolean startTimer;
  public boolean showTimer;
  private long startTime = 0;
  private long currentTime = 0;
  private Stopwatch(GameManager window) {
    showTimer = false;
    this.window = window;
  }

  public static Stopwatch getInstance(GameManager window) {
    if (instance == null) {
      instance = new Stopwatch(window);
    }
    return instance;
  }

  private void showTimer() {
    window.textSize(40);
    if (startTimer) {
      startTime = System.currentTimeMillis();
      startTimer = false;
    }
    if (showTimer) {
      currentTime = System.currentTimeMillis() - startTime;
      SimpleDateFormat sdf = new SimpleDateFormat("mm:ss:SSS");
      String time = sdf.format(new Date(currentTime));
      window.fill(255,255,255);
      window.text(time, (float) (window.displayWidth - 200), (float) (window.displayWidth / 30));
    } else {
      SimpleDateFormat sdf = new SimpleDateFormat("mm:ss:SSS");
      String time = sdf.format(new Date(currentTime));
      window.text(time, (float) (window.displayWidth - 200), (float) (window.displayHeight / 30));
    }
  }

  public void startTimer() {
    showTimer = true;
    showTimer();
  }

  public void stopTimer() {
    showTimer = false;
  }

  public void restartTimer() {
    startTimer = true;
  }

  public void setStartTimer(boolean time) {
    this.startTimer = time;
  }

  public boolean getStartTimer() {
    return !startTimer;
  }

  public boolean getShowTimer() {
    return showTimer;
  }

  public void setShowTimer(boolean showTimer) {
    this.showTimer = showTimer;
  }
}