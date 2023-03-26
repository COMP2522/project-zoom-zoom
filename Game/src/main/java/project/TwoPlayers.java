package project;

import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;

public class TwoPlayers extends PApplet {
  private final GameManager window;
  private static TwoPlayers instance;
  ArrayList<Sprite> sprites;
  Controls playerControls;
  Player player1;
  Player player2;
  Stopwatch stopwatch;
  int minSize = 10;
  int maxSize = 40;
  int[] player1Keys = {87, 83, 65, 68, 20, 16};
  int[] player2Keys = {73, 75, 74, 76, 59, 47};
  boolean timerCheck = true;

  private TwoPlayers(GameManager window){
    this.window = window;
  }

  public static TwoPlayers getInstance(GameManager window) {
    if (instance == null) {
      instance = new TwoPlayers(window);
    }
    return instance;
  }

  public void init2Player() {
    stopwatch = Stopwatch.getInstance(window);
    sprites = new ArrayList<Sprite>();

    player1 = new Player(
//        new PVector(window.width / 2, window.height / 2),
        window.getStartingPosition(2, 1),
        new PVector(50, 1),
        (minSize + 10),
        0.01F,
        new Color(0, 255, 0),
        window);

    player2 = new Player(
//        new PVector(window.width / 2, window.height / 2),
        window.getStartingPosition(2, 2),
        new PVector(500, 1),
        (minSize + 10),
        0.01F,
        new Color(0, 255, 0),
        window);
    playerControls = new Controls(player1, player2, player1Keys, player2Keys);

    sprites.add(player1);
    sprites.add(player2);
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
    // Move player around the screen.
    Controls.playerMovement();
    for (Sprite sprite : sprites) {
      sprite.update();
      sprite.draw();
    }
  }
  public char getP2Up() {
    return (char) player2Keys[0];
  }
  public char getP2Down() {
    return (char) player2Keys[1];
  }
  public char getP2Left() {
    return (char) player2Keys[2];
  }
  public char getP2Right() {
    return (char) player2Keys[3];
  }
  public char getP1Up() {
    return (char) player1Keys[0];
  }
  public char getP1Down() {
    return (char) player1Keys[1];
  }
  public char getP1Left() {
    return (char) player1Keys[2];
  }
  public char getP1Right() {
    return (char) player1Keys[3];
  }
  public boolean getTimerCheck() {
    return timerCheck;
  }

  public void setTimerCheck(boolean timerCheck) {
    this.timerCheck = timerCheck;
  }
}