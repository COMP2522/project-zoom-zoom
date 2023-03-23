package project;

import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ControlMenu {
  private final Window window;
  private static ControlMenu instance;

  public Button audioButton;

  public Button test;

  private boolean check = true;

  private ControlMenu(Window window) {
    this.window = window;
  }

  public static ControlMenu getInstance(Window window) {
    if (instance == null) {
      instance = new ControlMenu(window);
    }
    return instance;
  }

  public void setup() {
    window.textAlign(PApplet.CENTER, PApplet.CENTER);
    window.textSize(40);
    audioButton = new Button(new PVector((float) (window.displayWidth / 2) + 100, 800), 225,
        50, "SOUND OFF", new Color(200, 50, 50), window);
    test = new Button(new PVector((float) (window.displayWidth / 2) - 300, 800), 225,
        50, "SOUND ON", new Color(200, 50, 50), window);
  }

  public void draw() {
    window.background(64, 64, 64);
    window.fill(0);
    window.text("Setting", (float) window.displayWidth / 2 + 10, (float) window.displayHeight / 20);
    audioButton.update();
    audioButton.draw();
    test.draw();
    test.update();
    if (audioButton.isClicked()) {
      BGM.stopBGM(false);
      check = true;
      Window.audio = true;
    }
    if (test.isClicked() && check && Window.audio) {
      BGM.getBGM(true);
      check = false;
    }
  }
}