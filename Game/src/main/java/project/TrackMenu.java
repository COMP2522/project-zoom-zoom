package project;

import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;

public class TrackMenu {
  private final GameManager window;
  private static TrackMenu instance;
  private Button track1;
  private Button track2;
  private Button track3;
  private boolean showTitle = true;
  private static final int clock = 5;

  private PImage bg, title, track1Font, track2Font, track3Font;
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
    bg = window.loadImage("Game/images/BGImage2.png");
    title = window.loadImage("Game/images/Select Track.png");
    track1Font = window.loadImage("Game/images/Track1.png");
    window.textSize(40);
    track1 = new Button(new PVector((float) (window.displayWidth / 2) - 525, 490), 300,
        80, "", new Color(104, 52, 235), window);
    track2 = new Button(new PVector((float) (window.displayWidth / 2) - 125, 490), 300,
        80, "", new Color(64,64,64), window);
    track3 = new Button(new PVector((float) (window.displayWidth / 2) + 325, 490), 300,
        80, "", new Color(64,64,64), window);
  }

  public void draw() {
    window.background(64, 64, 64);
    window.image(bg, 0, 0, window.displayWidth, window.displayHeight);
    if (window.frameCount % clock == 0) {
      showTitle = !showTitle;
    }
    if (showTitle) {
      window.image(title, (float) (window.displayWidth / 2) - 200,window.displayHeight/4);
    }
    track1.update();
    track1.draw();
    window.image(track1Font,(float) (window.displayWidth / 2) - 500, 500);
    track2.update();
    track2.draw();
    track3.update();
    track3.draw();
    if (track1.isClicked()) {
      window.menu = 4;
    }
  }
}
