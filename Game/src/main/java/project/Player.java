package project;

import processing.core.PVector;

import java.awt.*;

/**
 * Player, creates a player sprite to be controlled by the user.
 */
public class Player extends Sprite {
  public Player(PVector position, PVector direction, float size, float speed,
                Color color, Window window) {
    super(position, direction, size, speed, color, window);
  }
}
