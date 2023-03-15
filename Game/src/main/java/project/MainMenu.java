package project;


import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;

/**
 * MainMenu, menu that appears once the game has been launched.
 *
 * @author James Langille
 */
public class MainMenu extends PApplet {
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

    onePlayer = new Button(new PVector((float) (window.displayWidth / 2.125) - 40, 500), 200, 50,
        "One Player", new Color(255, 0, 0), window);
    twoPlayer = new Button(new PVector((float) (window.displayWidth / 2.125) - 50, 600), 225, 50,
        "Two players", new Color(255, 0, 0), window);
    controls = new Button(new PVector((float) (window.displayWidth / 2.125), 700), 150,
        50, "Controls", new Color(200, 50, 50), window);
    quit = new Button(new PVector((float) (window.displayWidth / 2.125), 800), 175,
        50, "Quit", new Color(200, 50, 50), window);
  }

  public void draw() {
    window.background(0, 255, 150);
    window.fill(0);
    window.text("Zoom Zoom", window.displayWidth / 2,200);
    onePlayer.draw();
    onePlayer.update();
    twoPlayer.draw();
    twoPlayer.update();
    if (onePlayer.isClicked() || twoPlayer.isClicked()) {
//      Button test = new Button(new PVector((float) (window.displayWidth / 3.125), 500), 100, 50, "Test",
//          new Color(255, 0, 0), window);
//      test.draw();
        window.menu = 1;
    }
    controls.draw();
    quit.draw();
    quit.update();
    if (quit.isClicked()) {
      window.exit();
    }
  }

}
