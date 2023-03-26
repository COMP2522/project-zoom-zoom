package project;

import processing.core.PVector;

import java.awt.*;

/**
 * Sprite, creates sprite objects to be used in GameManager, implements
 * Comparable, Collidable, and Drawable.
 */
public class Sprite implements Comparable, Collidable, Drawable {
  protected PVector position;
  protected PVector direction;
  protected float size;

  protected float speed;
  protected Color color;
  protected GameManager window;

  /**
   * Constructor for sprite objects.
   *
   * @param position of sprite
   * @param direction sprite is going
   * @param size of the sprite
   * @param speed of the sprite
   * @param color of the sprite
   * @param window the sprite is being displayed
   */
  public Sprite(PVector position, PVector direction, float size, float speed,
                Color color, GameManager window) {
    this.position = position;
    this.direction = direction;
    this.size = size;
    this.speed = speed;
    this.window = window;
    this.color = color;
  }

  /**
   * Bounces a sprite object off of the window.
   */
  public void bounce() {
    if (this.position.x <= 0
        || this.position.x >= window.width
        || this.position.y <= 0
        || this.position.y >= window.height) {
      this.direction.rotate(window.HALF_PI);
    }
  }

  public PVector getDirection() {
    return direction.copy();
  }

  public PVector getPosition() {
    return position.copy();
  }

  public void update() {
    this.bounce();
    this.position = this.getPosition().add(this.direction.copy().mult(speed));
  }

  public float getSize() {
    return size;
  }

  public void setSize(float size) {
    this.size = size;
  }

  public float getSpeed() {
    return speed;
  }

  public void setSpeed(float speed) {
    this.speed = speed;
  }


  /**
   * Draws the sprite objects onto the window.
   */
  public void draw() {
    window.pushStyle();
    window.fill(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    window.ellipse(this.position.x, this.position.y, size, size);
    window.popStyle();
  }

  public void setDirection(PVector direction) {
    this.direction = direction;
  }

  @Override
  public int compareTo(Object o) {
    Sprite x = (Sprite) o;

    if (x.size > this.size) {
      return 1;
    } else if (x.size == this.size) {
      return 0;
    } else {
      return -1;
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Sprite) {
      return this.size == ((Sprite) obj).size;
    } else {
      return false;
    }
  }
}