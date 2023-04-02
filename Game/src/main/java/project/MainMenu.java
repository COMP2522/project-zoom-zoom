package project;

import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

/**
 * MainMenu, menu that appears once the game has been launched.
 *
 * @author James Langille
 */
public class MainMenu implements Drawable {
  // Images
  private PImage bgImage;
  private PImage gameTitle;
  private PImage onePlayerImage;
  private PImage twoPlayerImage;
  private PImage controlsImage;
  private PImage quitImage;
  // Other data
  protected static GameManager window;
  private static MainMenu instance;
  private boolean showTitle = true;
  private static final int clock = 5;

  /**
   * Main menu, private constructor to create a singleton of the class.
   *
   * @param window current window
   */
  private MainMenu(GameManager window) {
    MainMenu.window = window;
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
    window.imageMode(PConstants.CORNER);
    window.rectMode(PConstants.CORNER);
    // Set up buttons
    MainMenuButtons.setupButtons();

    // Set up images
    bgImage = window.loadImage("Game/images/BGImage.png");
    gameTitle = window.loadImage("Game/images/zoomZoom.png");
    onePlayerImage = window.loadImage("Game/images/1Player.png");
    twoPlayerImage = window.loadImage("Game/images/2Player.png");
    controlsImage = window.loadImage("Game/images/Controls.png");
    quitImage = window.loadImage("Game/images/Quit.png");
//    scoreboard = window.loadImage("Game/images/Ranking.jpg");
  }

  /**
   * draw, Draws all objects that are needed for this menu.
   */
  @Override
  public void draw() {
    // Draw images
    window.image(bgImage, 0, 0, window.displayWidth, window.displayHeight);
    MainMenuButtons.drawButtons();
//    window.image(scoreboard, 0, 0, (float) (window.displayWidth / 20) + 100, 825);
    this.showTitle();
//    window.image(onePlayerImage, onePlayer.getPosition().x + 5, onePlayer.getPosition().y + 10);
//
//    window.image(twoPlayerImage, twoPlayer.getPosition().x + 5, twoPlayer.getPosition().y + 10);
    // Draw controls button and image
//    window.image(controlsImage, controls.getPosition().x + 5, controls.getPosition().y + 10);
//    // Draw quit button and image
//    window.image(quitImage, quit.getPosition().x + 60, quit.getPosition().y + 10);
  }

}