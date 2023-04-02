package project;

import java.awt.*;
import processing.core.PVector;

/**
 * Car, creates sprite objects to be used in GameManager, implements Drawable.
 */
public class Car implements Drawable {
  /** Constant dimension values for cars. */
  protected static final float WIDTH = 50;
  protected static final float HEIGHT = 25;
  /** Other data. */
  protected PVector position;
  protected PVector direction;
  protected float speed;
  protected Color color;
  protected GameManager window;

  /**
   * Constructor for sprite objects.
   *
   * @param position of sprite
   * @param direction sprite is going
   * @param speed of the sprite
   * @param color of the sprite
   * @param window the sprite is being displayed
   */
  public Car(PVector position, PVector direction, float speed,
             Color color, GameManager window) {
    this.position = position;
    this.direction = direction;
    this.speed = speed;
    this.window = window;
    this.color = color;
  }

  /**
   * Constructor for sprite objects.
   *
   * @param position of sprite
   * @param direction sprite is going
   * @param speed of the sprite
   * @param window the sprite is being displayed
   */
  public Car(PVector position, PVector direction, float speed, GameManager window) {
    this.position = position;
    this.direction = direction;
    this.speed = speed;
    this.window = window;
    this.color = null;
  }

  /**
   * Draws the sprite objects onto the window.
   */
  public void draw() {
    if (color != null) {
      window.fill(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    }
    window.rect(this.position.x, this.position.y, WIDTH, HEIGHT);
  }

  public PVector getPosition() {
    return position.copy();
  }

  public void update() {
  }

  public float getSpeed() {
    return speed;
  }
}
