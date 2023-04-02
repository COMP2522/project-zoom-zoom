package project;

import java.awt.*;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * Button, class to place buttons on each menu.
 *
 * @author James Langille
 */
public class Button implements Drawable, Clickable {
  private PVector position;
  private float width;
  private float height;
  private Color color;
  private String title;
  private boolean leftClicked = false;
  private boolean rightClicked = false;
  protected static GameManager window;
  /** Colour constants used throughout the game. */
  protected static final Color RED = new Color(150, 0, 0);
  protected static final Color BLUE = new Color(0, 0, 150);
  protected static final Color GREEN = new Color(0, 150, 0);
  protected static final Color PURPLE = new Color(104, 52, 235);
  protected static final Color YELLOW = new Color(204, 204, 0);

  /**
   * Constructor to create a button object.
   *
   * @param position of button on window
   * @param width of button
   * @param height of button
   * @param title text of button
   * @param color of button
   * @param window of game
   */
  public Button(PVector position, float width, float height,
                String title, Color color, GameManager window) {
    this.position = position;
    this.width = width;
    this.height = height;
    this.title = title;
    this.color = color;
    Button.window = window;
  }

  /**
   * Constructor to create a button object with no title.
   *
   * @param position of button on window
   * @param width of button
   * @param height of button
   * @param color of button
   * @param window of game
   */
  public Button(PVector position, float width, float height, Color color, GameManager window) {
    this.position = position;
    this.width = width;
    this.height = height;
    this.title = null;
    this.color = color;
    Button.window = window;
  }

  /**
   * Constructor to create a button object with no color or text.
   *
   * @param position of button on window
   * @param width of button
   * @param height of button
   * @param window of game
   */
  public Button(PVector position, float width, float height, GameManager window) {
    this.position = position;
    this.width = width;
    this.height = height;
    this.title = null;
    this.color = null;
    Button.window = window;
  }

  /**
   * Draw the buttons onto the screen.
   */
  @Override
  public void draw() {
    // Create and fill in colour for button shape
    if (color != null) {
      window.fill(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    }
    window.rect(position.x, position.y, width, height);
    // Create and fill in colour for text
    window.textAlign(PApplet.CENTER, PApplet.CENTER);
    window.fill(0);
    if (title != null) {
      window.text(title, position.x + (width / 2), position.y + (height / 8));
    }
  }

  @Override
  public void click() {
    boolean leftClick = window.mousePressed && window.mouseButton == PApplet.LEFT;
    boolean rightClick = window.mousePressed && window.mouseButton == PApplet.RIGHT;
    if (mouseWithinDimensions()) {
      // Left click can happen in any menu
      if (leftClick) {
        leftClicked = true;
        // Right click can only happen in 2-Player mode
      } else if (rightClick && window.gameType == 2) {
        rightClicked = true;
      }
    }
  }

  /**
   * mouseWithinDimensions, checks if the mouse is in the button's dimensions.
   *
   * @return true if mouse is within dimensions, else false
   */
  public boolean mouseWithinDimensions() {
    return window.mouseX >= position.x && window.mouseX <= position.x + width
        && window.mouseY >= position.y && window.mouseY <= position.y + height;
  }

  public PVector getPosition() {
    return position;
  }

  public boolean isLeftClicked() {
    return leftClicked;
  }

  public boolean isRightClicked() {
    return rightClicked;
  }
}
