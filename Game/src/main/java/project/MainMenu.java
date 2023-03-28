package project;

import java.awt.*;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

/**
 * MainMenu, menu that appears once the game has been launched.
 *
 * @author James Langille
 */
public class MainMenu implements Drawable {
  // Buttons
  private Button onePlayer;
  private Button twoPlayer;
  private Button controls;
  private Button quit;
  // Images
  private PImage bgImage;
  private PImage gameTitle;
  private PImage onePlayerImage;
  private PImage twoPlayerImage;
  private PImage controlsImage;
  private PImage quitImage;
  // Other data
  private final GameManager window;
  private static MainMenu instance;
  private boolean showTitle = true;
  private static final int clock = 5;

  /**
   * Main menu, private constructor to create a singleton of the class.
   *
   * @param window current window
   */
  private MainMenu(GameManager window) {
    this.window = window;
  }

  /**
   * getInstance, method that creates a singleton of the class.
   *
   * @param window window class
   * @return a main menu object
   */
  public static MainMenu getInstance(GameManager window) {
    if (instance == null) {
      instance = new MainMenu(window);
    }
    return instance;
  }

  /**
   * showTitle, shows the game title every half second.
   */
  private void showTitle() {
    if (window.frameCount % clock == 0) {
      // Sets to opposite boolean expression every second
      showTitle = !showTitle;
    }
    if (showTitle) {
      window.image(gameTitle, window.displayWidth / 3 - 40, window.displayHeight / 4);
    }
  }

  /**
   * setup, Sets up all objects needed for the main menu.
   */
  public void setup() {

    // Set up buttons
    onePlayer = new Button(new PVector((float) (window.displayWidth / 2) - 100, 500), 225, 50,
        "", new Color(0, 150, 0), window);
    twoPlayer = new Button(new PVector((float) (window.displayWidth / 2) - 100, 600), 225, 50,
        "", new Color(0, 0, 150), window);
    controls = new Button(new PVector((float) (window.displayWidth / 2) - 100, 700), 225, 50,
        "", new Color(104, 52, 235), window);
    quit = new Button(new PVector((float) (window.displayWidth / 2) - 100, 800), 225, 50,
        "", new Color(150, 0, 0), window);

    // Set up images
    bgImage = window.loadImage("Game/images/BGImage.png");
    gameTitle = window.loadImage("Game/images/zoomZoom.png");
    onePlayerImage = window.loadImage("Game/images/1Player.png");
    twoPlayerImage = window.loadImage("Game/images/2Player.png");
    controlsImage = window.loadImage("Game/images/Controls.png");
    quitImage = window.loadImage("Game/images/Quit.png");
  }

  /**
   * draw, Draws all objects that are needed for this menu.
   */
  @Override
  public void draw() {
    // Draw images
    window.image(bgImage, 0, 0, window.displayWidth, window.displayHeight);
    this.showTitle();
    // Draw 1-Player button and image
    onePlayer.draw();
    onePlayer.click();
    if (onePlayer.isLeftClicked()) {
      // Change menu to one player game
      window.gameType = 1;
      window.menu = 5;
    }
    window.image(onePlayerImage, onePlayer.getPosition().x + 5, onePlayer.getPosition().y + 10);
    // Draw 2-Player button and image
    twoPlayer.draw();
    twoPlayer.click();
    if (twoPlayer.isLeftClicked()) {
      // Change menu to two player game
      window.gameType = 2;
      window.menu = 5;
    }
    window.image(twoPlayerImage, twoPlayer.getPosition().x + 5, twoPlayer.getPosition().y + 10);
    // Draw controls button and image
    controls.draw();
    controls.click();
    if (controls.isLeftClicked()) {
      window.menu = 3;
    }
    window.image(controlsImage, controls.getPosition().x + 5, controls.getPosition().y + 10);
    // Draw quit button and image
    quit.draw();
    quit.click();
    if (quit.isLeftClicked()) {
      window.exit();
    }
    window.image(quitImage, quit.getPosition().x + 60, quit.getPosition().y + 10);
  }

}