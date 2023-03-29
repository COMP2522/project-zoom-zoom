package project;

import processing.core.PVector;

import java.awt.*;

/**
 * GearButton, button subclass designated for gear parts.
 *
 * @author James Langille
 */
public class GearButton extends Button {
  /** Constants for gear button. */
  private static final float WIDTH = 200;
  private static final float HEIGHT = 100;
  private static final Color COLOR = new Color(255, 0, 0);

  /**
   * Constructor to create a gear button object.
   *
   * @param position of button on window
   * @param title    text of button
   * @param window   of game
   */
  public GearButton(PVector position, String title, GameManager window) {
    super(position, WIDTH, HEIGHT, title, COLOR, window);
  }
}
