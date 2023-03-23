package project;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.awt.*;
import java.util.ArrayList;

public class SinglePlayer extends PApplet{
  private final GameManager window;
  private static SinglePlayer instance;
  ArrayList<Sprite> sprites;
  Player player1;
  Player player2;
  Controls playerControls;
  int minSize = 10;
  int maxSize = 40;
  int[] player1Keys = {38, 40, 37, 39};
  int[] player2Keys = {87, 83, 65, 68};


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
   * Initializes all sprites needed for a two players game.
   */
  public void init1Player() {
    sprites = new ArrayList<Sprite>();

    player1 = new Player(
        new PVector(window.width / 2, window.height / 2),
        new PVector(50, 1),
        (minSize + 10),
        0.1F,
        new Color(0, 255, 0),
        window);

    player2 = new Player(
        new PVector(window.width / 2, window.height / 2),
        new PVector(500, 1),
        (minSize + 10),
        0.01F,
        new Color(0, 255, 0),
        window);
    playerControls = new Controls(window, player1, player2, player1Keys, player2Keys);

    sprites.add(player1);
  }

  public void draw() {
    window.background(64, 64, 64);
    Controls.playerMovement();
    // Move player around the screen.
    for (Sprite sprite : sprites) {
      sprite.update();
      sprite.draw();
    }
  }
}
