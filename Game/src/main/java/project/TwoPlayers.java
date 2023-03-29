package project;

import processing.core.PApplet;

import java.util.ArrayList;

/**
 * It contains methods for initializing and running the game, as well as accessing player controls and timer settings.
 */
public class TwoPlayers {
  /**
   * The window field represents the GameManager object that this game mode is running in.
   */
  private final GameManager window;

  /**
   * The instance field is used for the Singleton design pattern, ensuring that only one instance
   * of this class is created.
   */
  private static TwoPlayers instance;

  /**
   * The sprites field is an ArrayList of Sprite objects that are drawn on the screen during the game.
   */
  ArrayList<Sprite> sprites;

  /**
   * The playerControls field is a Controls object that handles player movement controls.
   */
  Controls playerControls;

  /**
   * The player1 fields represent the Player one object in the game.
   */
  Player player1;

  /**
   * The player2 fields represent the Player two object in the game.
   */
  Player player2;

  /**
   * The stopwatch field represents a Stopwatch object used to keep track of game time.
   */
  Stopwatch stopwatch;

  /**
   * The minSize fields represent the minimum size of the players' circles.
   */
  int minSize = 10;

  /**
   * The maxSize fields represent the maximum size of the players' circles.
   */
  int maxSize = 40;

  /**
   * The player1 Keys instance variable represents the array of key codes for controlling the human player.
   */
  int[] player1Keys = {87, 83, 65, 68, 20, 16};

  /**
   * The player1 Keys instance variable represents the array of key codes for controlling the human player.
   */
  int[] player2Keys = {73, 75, 74, 76, 59, 47};

  /**
   * The timerCheck field is a boolean value that represents whether or not the game timer is currently active.
   */
  boolean timerCheck = true;

  /**
   * The constructor for TwoPlayers class is private to ensure that only one instance of this class can be created.
   * @param window - The GameManager object that this game mode is running in.
   */
  private TwoPlayers(GameManager window){
    this.window = window;
  }

  /**
   * The getInstance method returns the instance of the TwoPlayers class, creating it if it doesn't exist.
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
   * @param p1 - The first Player object.
   * @param p2 - The second Player object.
   */
  public void init2Player(Player p1, Player p2) {
    stopwatch = Stopwatch.getInstance(window);
    sprites = new ArrayList<Sprite>();
    player1 = p1;
    player2 = p2;
    playerControls = new Controls(player1, player2, player1Keys, player2Keys);

    sprites.add(player1);
    sprites.add(player2);
  }

  /**
   * The draw method is called continuously during the game and updates the game state and draws sprites on the screen.
   */
  public void draw() {
    if (timerCheck && stopwatch.getShowTimer()) {
      stopwatch.restartTimer();
      timerCheck = false;
    }
    if (timerCheck) {
      stopwatch.setStartTimer(true);
      timerCheck = false;
    }
    stopwatch.startTimer();
    // Move player around the screen.
    Controls.playerMovement();
    for (Sprite sprite : sprites) {
      sprite.update();
      sprite.draw();
    }
  }

  /**
   * Returns the character representing the key used for Player 2's Up movement.
   *
   * @return the character representing the key used for Player 2's Up movement
   */
  public char getP2Up() {
    return (char) player2Keys[0];
  }

  /**
   * Returns the character representing the key used for Player 2's Down movement.
   *
   * @return the character representing the key used for Player 2's Down movement
   */
  public char getP2Down() {
    return (char) player2Keys[1];
  }

  /**
   * Returns the character representing the key used for Player 2's Left movement.
   *
   * @return the character representing the key used for Player 2's Left movement
   */
  public char getP2Left() {
    return (char) player2Keys[2];
  }

  /**
   * Returns the character representing the key used for Player 2's Right movement.
   *
   * @return the character representing the key used for Player 2's Right movement
   */
  public char getP2Right() {
    return (char) player2Keys[3];
  }

  /**
   * Returns the character representing the key used for Player 1's Up movement.
   *
   * @return the character representing the key used for Player 1's Up movement
   */
  public char getP1Up() {
    return (char) player1Keys[0];
  }

  /**
   * Returns the character representing the key used for Player 1's Down movement.
   *
   * @return the character representing the key used for Player 1's Down movement
   */
  public char getP1Down() {
    return (char) player1Keys[1];
  }

  /**
   * Returns the character representing the key used for Player 1's Left movement.
   *
   * @return the character representing the key used for Player 1's Left movement
   */
  public char getP1Left() {
    return (char) player1Keys[2];
  }

  /**
   * Returns the character representing the key used for Player 1's Right movement.
   *
   * @return the character representing the key used for Player 1's Right movement
   */
  public char getP1Right() {
    return (char) player1Keys[3];
  }

  /**
   * Returns a boolean indicating whether the timer is currently active or not.
   *
   * @return true if the timer is currently active, false otherwise
   */
  public boolean getTimerCheck() {
    return timerCheck;
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