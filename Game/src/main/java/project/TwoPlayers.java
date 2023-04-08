package project;

import java.util.ArrayList;
import processing.core.PConstants;
import processing.core.PImage;

/**
 * It contains methods for initializing and running the game,
 * as well as accessing player controls and timer settings.
 */
public class TwoPlayers implements Countdownable {
  /**
   * The window field represents the GameManager object
   * that this game mode is running in.
   */
  private final GameManager window;

  /**
   * The instance field is used for the Singleton design pattern,
   * ensuring that only one instance of this class is created.
   */
  private static TwoPlayers instance;

  /**
   * The sprites field is an ArrayList of car
   * objects that are drawn on the screen during the game.
   */
  ArrayList<Car> cars;

  /**
   * The player1 fields represent the Player one object in the game.
   */
  Player player1;

  /**
   * Image used for player 1.
   */
  PImage player1Car;

  /**
   * The player2 fields represent the Player two object in the game.
   */
  Player player2;

  /**
   * Image used for player 2.
   */
  PImage player2Car;

  /**
   * The stopwatch field represents a Stopwatch object used to keep track of game time.
   */
  Stopwatch stopwatch;
  /**
   * The timerCheck field is a boolean value that represents
   * whether or not the game timer is currently active.
   */
  boolean timerCheck = true;
  /** Data for the race countdown. */
  private long startTime;
  private long currentTime;
  private int countdown;
  private boolean raceDelay;
  /**
   * The dash instance variable represents
   * the dashboard for displaying information during the game.
   */
  Dashboard dash;

  /**
   * The constructor for TwoPlayers class is private
   * to ensure that only one instance of this class can be created.
   *
   * @param window - The GameManager object that this game mode is running in.
   */
  private TwoPlayers(GameManager window) {
    this.window = window;
  }

  /**
   * The getInstance method returns the instance
   * of the TwoPlayers class, creating it if it doesn't exist.
   *
   * @param window - The GameManager object that this game mode is running in.
   * @return The instance of the TwoPlayers class.
   */
  public static TwoPlayers getInstance(GameManager window) {
    if (instance == null) {
      instance = new TwoPlayers(window);
    }
    return instance;
  }

  /**
   * The init2Player method initializes the game with two Player objects.
   *
   * @param p1 - The first Player object.
   * @param p2 - The second Player object.
   */
  public void init2Player(Player p1, Player p2) {
    startTime = System.currentTimeMillis();
    stopwatch = Stopwatch.getInstance(window);
    cars = new ArrayList<Car>();
    // Load player and images
    player1 = p1;
    player2 = p2;
    player1Car = window.loadImage("Game/images/Player1Car.png");
    player2Car = window.loadImage("Game/images/Player2Car.png");
    // Load controls
    ControlCommandInvoker.p1Commands();
    ControlCommandInvoker.p2Commands();
    cars.add(player1);
    cars.add(player2);
    dash = new Dashboard(window, player1, player2);
  }

  /**
   * The draw method is called continuously during the game
   * and updates the game state and draws sprites on the screen.
   */
  public void draw() {
    createCountdown();
    if (!raceDelay) {
      if (timerCheck && stopwatch.getShowTimer()) {
        stopwatch.restartTimer();
        timerCheck = false;
      }
      if (timerCheck) {
        stopwatch.setStartTimer(true);
        timerCheck = false;
      }
      stopwatch.startTimer();
      // Move player around the screen
      ControlCommandInvoker.player1Movement();
      ControlCommandInvoker.player2Movement();
      for (Car sprite : cars) {
        sprite.update();
        sprite.draw();
        drawP1Car();
        drawP2Car();
      }
      dash.draw();
      dash.drawTwoPlayer();
    }
  }

  /**
   * createCountdown, starts a 3-second countdown before the race begins.
   */
  @Override
  public void createCountdown() {
    currentTime = System.currentTimeMillis() - startTime;
    int threeThousand = 3000;
    int largeFont = 200;
    int x = window.displayWidth / 2;
    int y = window.displayHeight / 2;
    if (currentTime < threeThousand) {
      raceDelay = true;
      countdown = (int) (threeThousand - currentTime);
      window.textAlign(PConstants.CENTER, PConstants.CENTER);
      window.textSize(largeFont);
      window.text(countdown, x, y);
    } else {
      raceDelay = false;
    }
  }

  /**
   * drawP1Car, draws and rotates the image on top of the rectangle.
   */
  public void drawP1Car() {
    window.pushMatrix();
    window.translate((player1.position.x + Car.WIDTH) / 2, (player1.position.y + Car.HEIGHT) / 2);
    window.imageMode(PConstants.CENTER);
    window.rotate((float) player1.direction);
    window.image(player1Car, (float) 0, (float) 12.5);
    window.popMatrix();
  }

  /**
   * drawP2Car, draws and rotates the image on top of the rectangle.
   */
  public void drawP2Car() {
    window.pushMatrix();
    window.translate((player2.position.x + Car.WIDTH) / 2, (player2.position.y + Car.HEIGHT) / 2);
    window.imageMode(PConstants.CENTER);
    window.rotate((float) player2.direction);
    window.image(player2Car, (float) 0, (float) 10);
    window.popMatrix();
  }
  /**
   * Sets the boolean value indicating whether the timer is currently active or not.
   *
   * @param timerCheck the new value to set for the timerCheck field
   */
  public void setTimerCheck(boolean timerCheck) {
    this.timerCheck = timerCheck;
  }
}