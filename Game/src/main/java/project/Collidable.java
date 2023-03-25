package project;

import processing.core.PVector;

/**
 * Collidable, interface that handles all sprite collisions.
 */
public interface Collidable {

  /**
   * Checks if two sprite objects collided with each other.
   *
   * @param a sprite object
   * @param b sprite object
   * @return true if a collision occured, else false.
   */
  static boolean collided(Sprite a, Sprite b) {
    float distance = PVector.dist(a.getPosition(), b.getPosition());
    if (distance <= (a.getSize() + b.getSize())) {
      return true;
    }
    return false;
  }


}