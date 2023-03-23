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
  int minSize = 10;
  int maxSize = 40;
  int[] player1Keys = {38, 40, 37, 39};
  int[] player2Keys = {87, 83, 65, 68};

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
    sprites.add(player2);
  }

  public void draw() {
    window.background(255,255,0);
    // Move player around the screen.
    Controls.playerMovement();
    for (Sprite sprite : sprites) {
      sprite.update();
      sprite.draw();
    }
  }
}
