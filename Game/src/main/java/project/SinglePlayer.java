package project;

import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;

public class SinglePlayer extends PApplet{
  private final GameManager window;
  private static SinglePlayer instance;
  AIPlayer aiPlayer;
  ArrayList<Sprite> sprites;
  Player player1;
  Controls playerControls;
  int minSize = 10;
  int maxSize = 40;
  int[] player1Keys = {87, 83, 65, 68, 20, 16};
  static boolean timerCheck = false;

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
  public void init1Player() {
    sprites = new ArrayList<Sprite>();
    // Create a list of AiNodes
    ArrayList<AiNode> aiNodes = new ArrayList<>();
    aiNodes.add(new AiNode(100, 100));
    aiNodes.add(new AiNode(200, 200));
    aiNodes.add(new AiNode(300, 300));

    player1 = new Player(
        new PVector(window.width / 2, window.height / 2),
        new PVector(50, 1),
        new PVector(10, 10),
        (minSize + 10),
        0.1F,
        new Color(0, 255, 0),
        window);
    playerControls = new Controls(window, player1, player1Keys);
    sprites.add(player1);

    // Add the AI player
    aiPlayer = new AIPlayer(
      new PVector(window.width / 2, window.height / 2),
      new PVector(50, 1),
      new PVector(10, 0),
      (minSize + 10),
      0.1F,
      new Color(255, 0, 0),
      window,
      aiNodes);
    sprites.add(aiPlayer);
  }

  public void draw() {
    window.background(64, 64, 64);
//    if (!timerCheck) {
//      CarModMenu.stopwatch.stopTimer();
//    } else {
//      CarModMenu.stopwatch.showTimer(true);
//    }

    // Move player and AI around the screen.
    for (Sprite sprite : sprites) {
      sprite.update();
      sprite.draw();
      if (sprite instanceof AIPlayer) {
        ((AIPlayer) sprite).updateAI(player1, sprites);
      }
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
