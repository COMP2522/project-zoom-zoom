package project;

import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;

public class SinglePlayer extends PApplet{
  public static Window window;
  private static SinglePlayer instance;
  ArrayList<Sprite> sprites;
  Player player1;
  Player player2;
  Controls playerControls;
  int minSize = 10;
  int maxSize = 40;
  static int[] player1Keys = {38, 40, 37, 39};
  static int[] player2Keys = {87, 83, 65, 68};
  static boolean timerCheck = false;
  static int check = 1;

  private SinglePlayer(Window window){
    this.window = window;
  }

  public static SinglePlayer getInstance(Window window) {
    if (instance == null) {
      instance = new SinglePlayer(window);
    }
    return instance;
  }

  /**
   * Initializes all sprites needed for a two players game.
   */
  public void init1Player() {
    sprites = new ArrayList<Sprite>();

    player1 = new Player(
        new PVector(window.width / 2, window.height / 2),
        new PVector(50, 1),
        (minSize + 10),
        0.0001F,
        new Color(0, 255, 0),
        window);

    player2 = new Player(
        new PVector(window.width / 2, window.height / 2),
        new PVector(500, 1),
        (minSize + 10),
        0.0001F,
        new Color(0, 255, 0),
        window);
    playerControls = new Controls(window, player1, player2, player1Keys, player2Keys);
    sprites.add(player1);
  }

  public void draw() {
    window.background(64, 64, 64);
    if (!timerCheck) {
      CarModMenu.stopwatch.stopTimer();
    } else {
      CarModMenu.stopwatch.showTimer(true);
    }

    Controls.playerMovement();
    // Move player around the screen.
    for (Sprite sprite : sprites) {
      sprite.update();
      sprite.draw();
    }
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

  public Player getPlayer2() {
    return player2;
  }

  public void setPlayer2(Player player2) {
    this.player2 = player2;
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

  public int[] getPlayer2Keys() {
    return player2Keys;
  }

  public void setPlayer2Keys(int[] player2Keys) {
    this.player2Keys = player2Keys;
  }
}
