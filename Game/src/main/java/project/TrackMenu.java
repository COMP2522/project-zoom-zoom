package project;

import processing.core.PVector;

import java.awt.*;

public class TrackMenu {
  private final GameManager window;
  private static TrackMenu instance;
  private Button track1;
  private Button track2;
  private Button track3;
  private TrackMenu(GameManager gameManager) {
    this.window = gameManager;
  }

  public static TrackMenu getInstance(GameManager window) {
    if (instance == null) {
      instance = new TrackMenu(window);
    }
    return instance;
  }

  public void setUp() {
    track1 = new Button(new PVector((float) (window.displayWidth / 2) - 500, 500), 225, 50,
        "One Player", new Color(52, 152, 235), window);
    track2 = new Button(new PVector((float) (window.displayWidth / 2) - 100, 500), 225, 50,
        "One Player", new Color(52, 152, 235), window);
    track3 = new Button(new PVector((float) (window.displayWidth / 2) + 300, 500), 225, 50,
        "One Player", new Color(52, 152, 235), window);
  }

  public void draw() {
    window.background(64, 64, 64);
    window.text("Zoom Zoom", (float) (window.displayWidth / 2) + 10,window.displayHeight/4);
    track1.update();
    track1.draw();
    track2.update();
    track2.draw();
    track3.update();
    track3.draw();
    if (track1.isClicked() || track2.isClicked() || track3.isClicked()) {
      window.menu = 4;
    }
  }
}
