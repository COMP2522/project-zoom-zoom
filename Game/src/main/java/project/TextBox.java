package project;

import org.w3c.dom.Text;

import static processing.core.PConstants.CENTER;

public class TextBox {
  private final GameManager window;
  private static TextBox instance;
  private TextBox(GameManager window) {
    this.window = window;
  }

  public static TextBox getInstance(GameManager window) {
    if (instance == null) {
      instance = new TextBox(window);
    }
    return instance;
  }

  public void draw() {
    window.textAlign(window.LEFT, window.TOP);
    window.textSize(20);
    if (window.isEditing) {
      window.fill(200);
      window.rect(window.displayWidth - 380, 500, 200, 40);
      window.fill(0);
      window.text(window.inputText, window.displayWidth - 375, 505, 195, 35);
    } else {
      window.fill(200);
//      window.rect(20, 60, 200, 40);
      window.rect(window.displayWidth - 380, 500, 200, 40);
      window.fill(150);
      window.text("Click to edit", window.displayWidth - 375, 505, 195, 35);
    }
  }
}
