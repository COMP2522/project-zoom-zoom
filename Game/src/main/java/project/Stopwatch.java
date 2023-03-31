package project;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * The Stopwatch class is used to create a timer that measures the time elapsed between
 * the start and stop of a game. It provides methods to start, stop and restart the timer,
 * as well as to display the elapsed time in a specific format.
 */
public class Stopwatch {

  /*
   * The GameManager object that will use the Stopwatch object.
   */
  private final GameManager window;

  /*
   * The Stopwatch object that will be created as a singleton.
   */
  private static Stopwatch instance;

  /*
   * A boolean flag that indicates whether the timer should start.
   */
  public boolean startTimer;

  /*
   * A boolean flag that indicates whether the timer should be displayed.
   */
  public boolean showTimer;

  /*
   * The start time of the timer in milliseconds.
   */
  private long startTime = 0;

  public long getCurrentTime() {
    return currentTime;
  }

  /*
   *The current time of the timer in milliseconds.
   */
  public long currentTime = 0;

  /*
   * Constructor for the Stopwatch class. Initializes the showTimer flag to false and
   * assigns the GameManager object to the window variable.
   * @param window the GameManager object that will use the Stopwatch object.
   */
  private Stopwatch(GameManager window) {
    showTimer = false;
    this.window = window;
  }

  /*
   * Returns the Stopwatch object created as a singleton. If the object has not been
   * created yet, it creates a new instance and returns it.
   * @param window the GameManager object that will use the Stopwatch object.
   * @return the Stopwatch object.
   */
  public static Stopwatch getInstance(GameManager window) {
    if (instance == null) {
      instance = new Stopwatch(window);
    }
    return instance;
  }

  /*
   * Displays the elapsed time on the game window. If the startTimer flag is true,
   * it sets the startTime to the current system time in milliseconds and sets the
   * startTimer flag to false. Then it calculates the currentTime by subtracting the
   * startTime from the current system time in milliseconds. Finally, it formats the
   * currentTime into a specific format and displays it on the game window.
   */
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

  /*
   * Starts the timer and displays the elapsed time on the game window.
   */
  public void startTimer() {
    showTimer = true;
    showTimer();
  }

  /*
   *  Stops the timer and removes the elapsed time from the game window.
   */
  public void stopTimer() {
    showTimer = false;
  }

  /*
   * Restarts the timer by setting the startTimer flag to true and removing the elapsed
   * time from the game window.
   */
  public void restartTimer() {
    startTimer = true;
  }

  /**
   * Sets the startTimer flag to the specified value.
   * @param time the value to set the startTimer flag to.
   */
  public void setStartTimer(boolean time) {
    this.startTimer = time;
  }

  /**
   * Returns the opposite value of the startTimer flag.
   * @return the opposite value of the startTimer flag.
   */
  public boolean getStartTimer() {
    return !startTimer;
  }

  /**
   * Returns the value of the showTimer flag.
   * @return the value of the showTimer flag.
   */
  public boolean getShowTimer() {
    return !showTimer;
  }

  /**
   * Sets the showTimer flag to the specified value.
   * @param showTimer the value to set the showTimer flag to.
   */
  public void setShowTimer(boolean showTimer) {
    this.showTimer = showTimer;
  }
}