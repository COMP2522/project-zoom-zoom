package project;

import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;

public class ControlMenu {
  private final GameManager window;
  private static ControlMenu instance;
  public Button soundoff;
  public Button soundon;
  public Button p1Go;
  public Button p1Stop;
  public Button p1Left;
  public Button p1Right;
  public Button p2Go;
  public Button p2Stop;
  public Button p2Left;
  public Button p2Right;
  private boolean check = true;

  private ControlMenu(GameManager window) {
    this.window = window;
  }

  public static ControlMenu getInstance(GameManager window) {
    if (instance == null) {
      instance = new ControlMenu(window);
    }
    return instance;
  }

  public void setup() {
    window.textAlign(PApplet.CENTER, PApplet.CENTER);
    window.textSize(40);
    soundoff = new Button(new PVector((float) (window.displayWidth / 2) + 100, 800), 225,
        50, "SOUND OFF", new Color(200, 50, 50), window);
    soundon = new Button(new PVector((float) (window.displayWidth / 2) - 300, 800), 225,
        50, "SOUND ON", new Color(200, 50, 50), window);
    p1Go = new Button(new PVector((float) (window.displayWidth / 2) - 300, 200), 225,
        50, "ACELERATION", new Color(200, 50, 50), window);
    p1Stop = new Button(new PVector((float) (window.displayWidth / 2) - 300, 300), 225,
        50, "BRAKE", new Color(200, 50, 50), window);
    p1Left = new Button(new PVector((float) (window.displayWidth / 2) - 300, 400), 225,
        50, "LEFT TURN", new Color(200, 50, 50), window);
    p1Right = new Button(new PVector((float) (window.displayWidth / 2) - 300, 500), 225,
        50, "RIGHT TURN", new Color(200, 50, 50), window);
    p2Go = new Button(new PVector((float) (window.displayWidth / 2) + 100, 200), 225,
        50, "ACELERATION", new Color(200, 50, 50), window);
    p2Stop = new Button(new PVector((float) (window.displayWidth / 2) + 100, 300), 225,
        50, "BRAKE", new Color(200, 50, 50), window);
    p2Left = new Button(new PVector((float) (window.displayWidth / 2) + 100, 400), 225,
        50, "LEFT TURN", new Color(200, 50, 50), window);
    p2Right = new Button(new PVector((float) (window.displayWidth / 2) + 100, 500), 225,
        50, "RIGHT TURN", new Color(200, 50, 50), window);
  }

  public void draw() {
    window.background(64, 64, 64);
    window.fill(0);
    window.text("KEY SETTIN", (float) window.displayWidth / 2 + 10, (float) window.displayHeight / 20);
    window.text("SOUND SETTING", (float) window.displayWidth / 2 + 10, 700);
    soundoff.update();
    soundoff.draw();
    soundon.draw();
    soundon.update();
    p1Go.update();
    p1Go.draw();
    p1Stop.update();
    p1Stop.draw();
    p1Left.update();
    p1Left.draw();
    p1Right.update();
    p1Right.draw();
    p2Go.update();
    p2Go.draw();
    p2Stop.update();
    p2Stop.draw();
    p2Left.update();
    p2Left.draw();
    p2Right.update();
    p2Right.draw();
    if (soundoff.isClicked()) {
      BGM.stopBGM(false);
      check = true;
      GameManager.audio = true;
    }
    if (soundon.isClicked() && check && GameManager.audio) {
      BGM.getBGM(true);
      check = false;
    }
    if (p1Go.isClicked()) {
    }
    if (p1Stop.isClicked()) {
    }
    if (p1Left.isClicked()) {
    }
    if (p1Right.isClicked()) {
    }
    if (p2Go.isClicked()) {
    }
    if (p1Stop.isClicked()) {
    }
    if (p1Left.isClicked()) {
    }
    if (p1Right.isClicked()) {
    }
  }
}