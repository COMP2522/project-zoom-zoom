package project;

import java.util.ArrayList;

public class SinglePlayer {
  private final Window window;
  private static SinglePlayer instance;

  private SinglePlayer(Window window){
    this.window = window;
  }

  public static SinglePlayer getInstance(Window window) {
    if (instance == null) {
      instance = new SinglePlayer(window);
    }
    return instance;
  }

  public void draw() {
    background(64, 64, 64);
    // Move player around the screen.
    Controls.playerMovement();
    for (Sprite sprite : sprites) {
      sprite.update();
      sprite.draw();
    }
    ArrayList<Sprite> toRemove = new ArrayList<Sprite>();
    for (Sprite enemy : enemies) {
      if (Collidable.collided(player1, enemy)) {
        toRemove.add(enemy);
      }

    }
    for (Sprite enemy : toRemove) {
      // TODO: implement compareTo and equals to make this work
      if (enemy.compareTo(player1) > 0 || enemy.equals(player1)) {
        enemies.remove(enemy);
        sprites.remove(enemy);
      }
    }
  }
}
