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
  private boolean clicked = false;
  private GameManager window;

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
    this.window = window;
  }

  /**
   * Draw the buttons onto the screen.
   */
  @Override
  public void draw() {
    // Create and fill in colour for button shape
    window.fill(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    window.rect(position.x, position.y, width, height);

    // Create and fill in colour for text
    window.fill(0);
    window.textAlign(PApplet.CENTER, PApplet.CENTER);
    window.text(title, position.x + (width / 2), position.y + (height / 2));
  }

  @Override
  public void click() {
    boolean leftClick = window.mousePressed && window.mouseButton == PApplet.LEFT;
    boolean rightClick = window.mousePressed && window.mouseButton == PApplet.RIGHT;
    if (mouseWithinDimensions()) {
      // Left click can happen in any menu
      if (leftClick) {
        clicked = true;
        // Right click can only happen in 2-Player mode
      } else if (rightClick && window.gameType == 2) {
        clicked = true;
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

  public void setPosition(PVector position) {
    this.position = position;
  }

  public float getWidth() {
    return width;
  }

  public void setWidth(float width) {
    this.width = width;
  }

  public float getHeight() {
    return height;
  }

  public void setHeight(float height) {
    this.height = height;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public boolean isClicked() {
    return clicked;
  }
}
