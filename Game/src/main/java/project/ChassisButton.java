package project;

import processing.core.PVector;

import java.awt.*;

/**
 * ChassisButton, button subclass designated for chassis parts.
 *
 * @author James Langille
 */
public class ChassisButton extends Button {

  /** Constants for chassis button. */
  private static final float WIDTH = 200;
  private static final float HEIGHT = 100;
  private static final Color COLOR = new Color(255, 0, 0);


  /**
   * Constructor to create a chassis button object.
   *
   * @param position of button on window
   * @param title    text of button
   * @param window   of game
   */
  public ChassisButton(PVector position, String title, GameManager window) {
    super(position, WIDTH, HEIGHT, title, COLOR, window);
  }
}
