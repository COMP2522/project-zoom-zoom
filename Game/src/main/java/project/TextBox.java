package project;

import org.w3c.dom.Text;

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
    window.text("Enter text:", 20, 20);
    if (window.isEditing) {
      window.fill(200);
      window.rect(20, 60, 200, 40);
      window.fill(0);
      window.text(window.inputText, 25, 65, 195, 35);
    } else {
      window.fill(200);
      window.rect(20, 60, 200, 40);
      window.fill(150);
      window.text("Click to edit", 25, 65, 195, 35);
    }
  }
}
