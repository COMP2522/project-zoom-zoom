package project;

import processing.core.PVector;

/**
 * The TextBox class represents a text box that can be used to input text.
 * It implements the Drawable interface.
 */
public class TextBox implements Drawable {
  private PVector position;
  private float width;
  private float height;
  public String text = "";
  private final GameManager window;

  /**
   * TextBox, constructor for the text box.
   *
   * @param position position of the text box
   * @param width width of the text box
   * @param height height of the text box
   * @param window the window for current game
   */
  public TextBox(PVector position, float width, float height, GameManager window) {
    this.position = position;
    this.width = width;
    this.height = height;
    this.window = window;
  }

  /**
   * draw, draws the text box.
   */
  @Override
  public void draw() {
    window.textAlign(window.LEFT, window.TOP);
    window.textSize(20);
    if (window.isEditing) {
      window.fill(200);
      window.rect(position.x, position.y, width, height);
      window.fill(0);
      window.text(window.inputText, position.x, position.y, width, height);
    } else {
      window.fill(200);
      window.rect(position.x, position.y, width, height);
      window.fill(150);
      window.text("Click to edit", position.x, position.y, width, height);
    }
  }

  /**
   * textBoxClicked, checks if the mouse clicked within the area of the text box.
   */
  public void textBoxClicked() {
    if (window.mouseX >= position.x && window.mouseX <= position.x + width
        && window.mouseY >= position.y && window.mouseY <= position.y + height) {
      window.isEditing = true;
    } else {
      window.isEditing = false;
    }
  }
}
