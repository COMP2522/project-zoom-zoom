package project;

import processing.core.PVector;

import java.awt.*;

/**
 * EngineButton, button subclass designated for engine parts.
 *
 * @author James Langille
 */
public class EngineButton extends Button {
  /** Constants for engine button. */
  private static final float WIDTH = 200;
  private static final float HEIGHT = 100;
  private static final Color COLOR = new Color(255, 0, 0);

  /**
   * Constructor to create an engine button object.
   *
   * @param position of button on window
   * @param text  of button
   * @param window of game
   */
  public EngineButton(PVector position, String text, GameManager window) {
    super(position, WIDTH, HEIGHT, text, COLOR, window);
  }
}
