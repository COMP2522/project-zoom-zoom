package project;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;

public class SinglePlayer extends PApplet{
  public final GameManager window;
  private static SinglePlayer instance;
  Bot bot;
  ArrayList<Sprite> sprites;
  Player player1;
  Controls playerControls;
  Dashboard dash;
  public Stopwatch stopwatch;
  int minSize = 10;
  int maxSize = 40;
  int[] player1Keys = {87, 83, 65, 68, 20, 16};
  static boolean timerCheck = true;

  private SinglePlayer(GameManager window){
    this.window = window;
  }

  public static SinglePlayer getInstance(GameManager window) {
    if (instance == null) {
      instance = new SinglePlayer(window);
    }
    return instance;
  }

  /**
   * Initializes all sprites needed for a one player game.
   */
  public void init1Player(Player p1) {
    stopwatch = Stopwatch.getInstance(window);
    sprites = new ArrayList<Sprite>();
    player1 = p1;
    playerControls = new Controls(player1, player1Keys);
    sprites.add(player1);

    ArrayList<PVector> waypoints = new ArrayList<>();
    waypoints.add(new PVector(window.width / 2, window.height / 2));
    waypoints.add(new PVector(50, 1));
    waypoints.add(new PVector(60, 1));
    waypoints.add(new PVector(70, 10));
    waypoints.add(new PVector(80, 1));
    waypoints.add(new PVector(90, 1));


    // Add the AI player
    bot = new Bot(
      window.getStartingPosition(1, 1),
      new PVector(50, 1),
      (minSize + 10),
      0.1F,
      new Color(255, 0, 0),
      window,
      waypoints);
    sprites.add(bot);
    dash = new Dashboard(window, player1, window.displayWidth / 8, window.displayHeight / 20);
  }

  public void draw() {
    if (timerCheck && !stopwatch.getShowTimer()) {
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
    for (Sprite sprite : sprites) {
      sprite.update();
      sprite.draw();
      if (sprite instanceof Bot) {
        bot = (Bot) sprite;
        bot.update();
        bot.draw();
      }
      dash.draw();
    }
  }

  public char getUp() {
    return (char) player1Keys[0];
  }
  public char getDown() {
    return (char) player1Keys[1];
  }
  public char getLeft() {
    return (char) player1Keys[2];
  }
  public char getRight() {
    return (char) player1Keys[3];
  }

  public boolean isTimerCheck() {
    return timerCheck;
  }

  public void setTimerCheck(boolean timerCheck) {
    this.timerCheck = timerCheck;
  }

  public ArrayList<Sprite> getSprites() {
    return sprites;
  }

  public void setSprites(ArrayList<Sprite> sprites) {
    this.sprites = sprites;
  }

  public Player getPlayer1() {
    return player1;
  }

  public void setPlayer1(Player player1) {
    this.player1 = player1;
  }


  public Controls getPlayerControls() {
    return playerControls;
  }

  public void setPlayerControls(Controls playerControls) {
    this.playerControls = playerControls;
  }

  public int getMinSize() {
    return minSize;
  }

  public void setMinSize(int minSize) {
    this.minSize = minSize;
  }

  public int getMaxSize() {
    return maxSize;
  }

  public void setMaxSize(int maxSize) {
    this.maxSize = maxSize;
  }

  public int[] getPlayer1Keys() {
    return player1Keys;
  }

  public void setPlayer1Keys(int[] player1Keys) {
    this.player1Keys = player1Keys;
  }
}