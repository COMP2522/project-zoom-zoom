package project;


import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;

/**
 * MainMenu, menu that appears once the game has been launched.
 *
 * @author James Langille
 */
public class MainMenu {
  private final Window window;
  private static MainMenu instance;
  private Button onePlayer;
  private Button twoPlayer;
  private Button controls;
  private Button quit;

  private MainMenu(Window window) {
    this.window = window;
  }

  /**
   * Singleton constructor for main menu.
   *
   * @param window window class
   * @return a main menu object
   */
  public static MainMenu getInstance(Window window) {
    if (instance == null) {
      instance = new MainMenu(window);
    }
    return instance;
  }

  public Window getWindow() {
    return window;
  }

  // Set up buttons
  public void setup() {
    window.textAlign(PApplet.CENTER, PApplet.CENTER);
    window.textSize(40);

    onePlayer = new Button(new PVector((float) (window.displayWidth / 2) - 100, 500), 225, 50,
        "One Player", new Color(52, 152, 235), window);
    twoPlayer = new Button(new PVector((float) (window.displayWidth / 2) - 100, 600), 225, 50,
        "Two players", new Color(52, 73, 235), window);
    controls = new Button(new PVector((float) (window.displayWidth / 2) - 100, 700), 225,
        50, "Controls", new Color(104, 52, 235), window);
    quit = new Button(new PVector((float) (window.displayWidth / 2) - 100, 800), 225,
        50, "Quit", new Color(200, 50, 50), window);
  }

  public void draw() {
    window.background(64, 64, 64);
    window.fill(0);
    window.text("Zoom Zoom", window.displayWidth / 2 + 10,window.displayHeight/4);
    onePlayer.draw();
    onePlayer.update();
    twoPlayer.draw();
    twoPlayer.update();
    if (onePlayer.isClicked()) {
      // Initialize one player game
      window.init1Player();
      // Change menu to one player game
      window.menu = 1;
    }
    if (twoPlayer.isClicked()) {
      // Initialize two player game
      window.init2Player();
      // Change menu to two player game
      window.menu = 2;
    }
    controls.draw();
    controls.update();
    if (controls.isClicked()) {
      window.menu = 3;
    }
    quit.draw();
    quit.update();
    if (quit.isClicked()) {
      window.exit();
    }
  }

}
