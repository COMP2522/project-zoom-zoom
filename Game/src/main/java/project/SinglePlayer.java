package project;

import java.awt.*;
import java.util.ArrayList;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

/**
 * The SinglePlayer class represents the main game mode for single player.
 * It extends the PApplet class from Processing and implements the GameManager interface.
 */
public class SinglePlayer implements Countdownable {

  /**
   * The window instance variable represents the game manager for the current game.
   */
  public final GameManager window;

  /**
   * The instance variable represents the singleton instance of the SinglePlayer class.
   */
  private static SinglePlayer instance;

  /**
   * The bot instance variable represents the semi-computer-controlled player in the game.
   */
  Bot bot;

  /**
   * The sprites instance variable represents
   * the list of sprites (e.g. bot, player itself) in the game.
   */
  ArrayList<Car> sprites;

  /**
   * The player1 instance variable represents
   * the human-controlled player in the game.
   */
  Player player1;

  /**
   * Image used for player 1.
   */
  private PImage player1Car;

  /**
   * The playerControls instance variable represents
   * the controls for the human-controlled player.
   */
  Controls playerControls;

  /**
   * The dash instance variable represents
   * the dashboard for displaying information during the game.
   */
  Dashboard dash;

  /**
   * The stopwatch instance variable represents the timer for the game.
   */
  public Stopwatch stopwatch;

  /**
   * The player1Keys instance variable represents
   * the array of key codes for controlling the human player.
   */
  int[] player1Keys = {87, 83, 65, 68, 20, 16};

  /**
   * The timerCheck instance variable represents
   * a boolean value indicating whether the timer is currently active.
   */
  static boolean timerCheck = true;

  /** Data for the race countdown. */
  private long startTime;
  private long currentTime;
  private int countdown;
  private boolean raceDelay;

  /**
   * The constructor for the SinglePlayer class.
   *
   * @param window The game manager for the current game.
   */
  private SinglePlayer(GameManager window) {
    this.window = window;
  }

  /**
   * The getInstance method returns the singleton instance of the SinglePlayer class.
   * If the instance does not exist, it is created.
   *
   * @param window The game manager for the current game.
   *
   * @return The singleton instance of the SinglePlayer class.
   */
  public static SinglePlayer getInstance(GameManager window) {
    if (instance == null) {
      instance = new SinglePlayer(window);
    }
    return instance;
  }

  /**
   * Initializes the game by setting up the stopwatch, creating the player and bot,
   * adding the player and bot to the list of sprites, and creating the dashboard.
   *
   * @param p1 The player controlled by the user.
   */
  public void init1Player(Player p1) {
    startTime = System.currentTimeMillis();
    stopwatch = Stopwatch.getInstance(window);
    sprites = new ArrayList<Car>();
    player1 = p1;
    playerControls = new Controls(player1, player1Keys);
//    sprites.add(player1);
    player1Car = window.loadImage("Game/images/Player1Car.png");

    ArrayList<PVector> waypoints = new ArrayList<>();
    waypoints.add(new PVector(window.width / 2, window.height / 2));
    waypoints.add(new PVector(50, 1));
    waypoints.add(new PVector(60, 1));
    waypoints.add(new PVector(70, 10));
    waypoints.add(new PVector(80, 1));
    waypoints.add(new PVector(90, 1));

    // Add the AI player
    bot = new Bot(
      window.getStartingPosition(1),
        new PVector(50, 1),
      0.1F,
        new Color(255, 0, 0),
      window,
      waypoints, "B");
    sprites.add(bot);
    dash = new Dashboard(window, player1);
  }

  /**
   * Draw method of SinglePlayer class.
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
      Controls.playerMovement();
      // Move player around the screen.
      player1.draw();
      player1.update();
      drawImage();
      for (Car sprite : sprites) {
        if (sprite instanceof Bot) {
          bot = (Bot) sprite;
          //bot.draw();
          bot.update();
        }
        dash.draw();
        window.text("Laps " + player1.laps + "/ " + 10, window.displayWidth/2, 50);
      }
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
   * drawImage, draws and rotates the image on top of the rectangle.
   */
  public void drawImage() {
    window.pushMatrix();
    window.translate((player1.xpos), (player1.ypos));
    window.imageMode(PConstants.CENTER);
    window.rotate((float) player1.direction);
    window.image(player1Car, (float) 0, (float) 12.5);
    window.popMatrix();
  }

  /**
   * Getter for up key.
   *
   * @return up key of player for in game
   */
  public char getUp() {
    return (char) player1Keys[0];
  }

  /**
   * Getter for brake key.
   *
   * @return brake key of player for in game
   */
  public char getDown() {
    return (char) player1Keys[1];
  }

  /**
   * Getter for left key.
   *
   * @return left key of player for in game
   */
  public char getLeft() {
    return (char) player1Keys[2];
  }

  /**
   * Getter for right key.
   *
   * @return right key of player for in game
   */
  public char getRight() {
    return (char) player1Keys[3];
  }

  /**
   * Getter for boolean variable that checks status of timer.
   *
   * @return boolean variable of timer status of this class
   */
  public boolean isTimerCheck() {
    return timerCheck;
  }

  /**
   * Setter for timerCheck variable that checks status of timer.
   *
   * @param timerCheck boolean variable of timer status of this class
   */
  public void setTimerCheck(boolean timerCheck) {
    this.timerCheck = timerCheck;
  }

  /**
   * Returns the ArrayList of sprites.
   *
   * @return the ArrayList of sprites.
   */
  public ArrayList<Car> getSprites() {
    return sprites;
  }

  /**
   * Sets the ArrayList of sprites.
   *
   * @param sprites the ArrayList of sprites.
   */
  public void setSprites(ArrayList<Car> sprites) {
    this.sprites = sprites;
  }

  /**
   * Returns the Player object for player 1.
   *
   * @return the Player object for player 1.
   */
  public Player getPlayer1() {
    return player1;
  }

  /**
   * Sets the Player object for player 1.
   *
   * @param player1 the Player object for player 1.
   */
  public void setPlayer1(Player player1) {
    this.player1 = player1;
  }

  /**
   * Returns the Controls object for player 1.
   *
   * @return the Controls object for player 1.
   */
  public Controls getPlayerControls() {
    return playerControls;
  }

  /**
   * Sets the Controls object for player 1.
   *
   * @param playerControls the Controls object for player 1.
   */
  public void setPlayerControls(Controls playerControls) {
    this.playerControls = playerControls;
  }

  /**
   * Returns the integer array of keys for player 1.
   *
   * @return the integer array of keys for player 1.
   */
  public int[] getPlayer1Keys() {
    return player1Keys;
  }

  /**
   * Sets the integer array of keys for player 1.
   *
   * @param player1Keys the integer array of keys for player 1.
   */
  public void setPlayer1Keys(int[] player1Keys) {
    this.player1Keys = player1Keys;
  }
}