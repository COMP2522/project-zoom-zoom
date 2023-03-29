package project;

import processing.core.PVector;

import java.awt.*;

/**
 * AerodynamicsButton, button subclass designated for aerodynamics parts.
 *
 * @author James Langille
 */
public class AerodynamicsButton extends Button {
  /** Constants for aerodynamics button. */
  private static final float WIDTH = 200;
  private static final float HEIGHT = 100;
  private static final Color COLOR = new Color(255, 0, 0);
  /**
   * Constructor to create an aerodynamics button object.
   *
   * @param position of button on window
   * @param title    text of button
   * @param window   of game
   */
  public AerodynamicsButton(PVector position, String title, GameManager window) {
    super(position, WIDTH, HEIGHT, title, COLOR, window);
  }
}
