package project;

import java.awt.*;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * Button, class to place buttons on each menu.
 *
 * @author James Langille
 */
public class Button {
  private PVector position;
  private float width;
  private float height;
  private Color color;
  private String title;
  private boolean pressed = false;
  private boolean clicked = false;
  private Window window;

  /**
   * Constructor to create a button object.
   *
   * @param position of button on window
   * @param width of button
   * @param height of button
   * @param title text of button
   * @param color of button
   * @param window screen
   */
  public Button(PVector position, float width, float height,
                String title, Color color, Window window) {
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
  public void draw() {
    // Create and fill in colour for button shape
    window.fill(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    window.rect(position.x, position.y, width, height);

    // Create and fill in colour for text
    window.fill(0);
    window.textAlign(PApplet.CENTER, PApplet.CENTER);
    window.text(title, position.x + (width / 2), position.y + (height / 2));
  }

  /**
   * Update the button's boolean status if they were clicked.
   */
  public void update() {
    // Check if mouse left button has been pressed
    if (window.mousePressed && window.mouseButton == PApplet.LEFT && !pressed) {
      pressed = true;
      // Check if mouse click is in button dimensions
      if (window.mouseX >= position.x && window.mouseX <= position.x + width
          && window.mouseY >= position.y && window.mouseY <= position.y + height) {
        clicked = true;
      }
    } else {
      clicked = false;
      pressed = false;
    }

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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public boolean isPressed() {
    return pressed;
  }

  public void setPressed(boolean pressed) {
    this.pressed = pressed;
  }

  public boolean isClicked() {
    return clicked;
  }

  public void setClicked(boolean clicked) {
    this.clicked = clicked;
  }

}
